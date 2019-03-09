package com.cg.billing.services;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.cg.billing.beans.Address;
import com.cg.billing.beans.Bill;
import com.cg.billing.beans.Customer;
import com.cg.billing.beans.Plan;
import com.cg.billing.beans.PostpaidAccount;
import com.cg.billing.daoservices.BillDAO;
import com.cg.billing.daoservices.CustomerDAO;
import com.cg.billing.daoservices.PlanDAO;
import com.cg.billing.daoservices.PostpaidAccountDAO;
import com.cg.billing.exceptions.BillDetailsNotFoundException;
import com.cg.billing.exceptions.CustomerDetailsNotFoundException;
import com.cg.billing.exceptions.InvalidBillMonthException;
import com.cg.billing.exceptions.PlanDetailsNotFoundException;
import com.cg.billing.exceptions.PostpaidAccountNotFoundException;
@Component("billingServices")
public class BillingServicesImpl implements BillingServices {
    @Autowired
	BillDAO billDAO;
    @Autowired
    CustomerDAO customerDAO;
    @Autowired
    PlanDAO planDAO;
    @Autowired
    PostpaidAccountDAO postpaidAccountDAO;
	@Override
	public int acceptCustomerDetails(String firstName, String lastName, String emailID, String dateOfBirth, String homeCity,String homeState,int homePinCode,String billingAddressCity,String billingAddressState,int billingAddressPinCode) {
		Address homeAddress= new Address(homeCity,homeState,homePinCode);
		Address billingAddress=new Address(billingAddressCity,billingAddressState,billingAddressPinCode);
		List<Address> address=new ArrayList<Address>();
		address.add(homeAddress);
		address.add(billingAddress);
		Customer customer=new Customer(firstName,lastName,emailID,dateOfBirth, address);
        customerDAO.save(customer);
		return customer.getCustomerID();
	}
	@Override
	public long openPostpaidMobileAccount(int customerID, int planID)
			throws PlanDetailsNotFoundException, CustomerDetailsNotFoundException {
	     Plan plan = getPlanDetails(planID);
	     if(plan ==null)
	     throw new PlanDetailsNotFoundException("Please enter correct plan id.");
	     Customer customer= getCustomerDetails(customerID);
		 PostpaidAccount postPaidAccount = new PostpaidAccount(plan, customer);
		 postpaidAccountDAO.save(postPaidAccount);
		 return postPaidAccount.getMobileNo();
	}
	@Override
	public double generateMonthlyMobileBill(int customerID, long mobileNo, String billMonth, int noOfLocalSMS,
			int noOfStdSMS, int noOfLocalCalls, int noOfStdCalls, int internetDataUsageUnits)throws CustomerDetailsNotFoundException,PostpaidAccountNotFoundException{
        int centralGST=9;
		int stateGST=9;
		Customer customer = getCustomerDetails(customerID);
		if(customer==null)
		throw new CustomerDetailsNotFoundException("Sorry no customer found");
		Map<Long, PostpaidAccount> postPaidAccount = customer.getPostpaidAccounts();
		if(postPaidAccount==null)
		throw new PostpaidAccountNotFoundException("Customer has no plans");
		PostpaidAccount postpaidAcc=postPaidAccount.get(mobileNo);		
        Bill bill = new Bill(noOfLocalSMS, noOfStdSMS, noOfLocalCalls, noOfStdCalls, internetDataUsageUnits, billMonth, stateGST, centralGST,postpaidAcc);
		float amount=0.0f;
		Plan plan =postpaidAcc.getPlan();	
		if(noOfLocalCalls>plan.getFreeLocalCalls()) {
			bill.setLocalCallAmount((noOfLocalCalls-plan.getFreeLocalCalls())*plan.getLocalCallRate());
			amount =amount+bill.getLocalCallAmount();
		}
		if(noOfLocalSMS>plan.getFreeLocalSMS()) {
			bill.setLocalSMSAmount((noOfLocalSMS-plan.getFreeLocalSMS())*plan.getLocalSMSRate());
			amount=amount+bill.getLocalSMSAmount();
		}
		if(noOfStdCalls>plan.getFreeStdCalls()) {
			bill.setStdCallAmount((noOfStdCalls-plan.getFreeStdCalls())*plan.getStdCallRate());
			amount=amount+bill.getStdCallAmount();
		}
		if(noOfStdSMS>plan.getFreeStdSMS()) {
			bill.setStdSMSAmount((noOfStdSMS-plan.getFreeStdSMS())*plan.getStdSMSRate());
			amount=amount+bill.getStdSMSAmount();
		}
		if(internetDataUsageUnits>plan.getFreeInternetDataUsageUnits()) {
			bill.setInternetDataUsageAmount((internetDataUsageUnits-plan.getFreeInternetDataUsageUnits())*plan.getInternetDataUsageRate());
		    amount=amount+bill.getInternetDataUsageAmount();
		}
		amount=amount+plan.getMonthlyRental()+(amount*stateGST)/100+(amount*centralGST)/100;
		bill.setTotalBillAmount(amount);
		billDAO.save(bill);
        return amount; 
	}
	@Override
	public Customer getCustomerDetails(int customerID) throws CustomerDetailsNotFoundException {
	   return customerDAO.findById(customerID).orElseThrow(()->new CustomerDetailsNotFoundException("Sorry this customer ID is not registered with us"));
	}
	@Override
	public List<Customer> getAllCustomerDetails() {
		return customerDAO.findAll();
	}
	@Override
	public PostpaidAccount getPostPaidAccountDetails(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException{
		Customer customer = getCustomerDetails(customerID);
		if(customer==null)
		throw new CustomerDetailsNotFoundException("Sorry no customer with this id found");
		 if(!customer.getPostpaidAccounts().containsKey(mobileNo))
			 throw new PostpaidAccountNotFoundException("Customer has no plans");
		return postpaidAccountDAO.findById(mobileNo).orElseThrow(()->new PostpaidAccountNotFoundException("No mobile number found"));
	}
	@Override
	public List<PostpaidAccount> getCustomerAllPostpaidAccountsDetails(int customerID)
			throws CustomerDetailsNotFoundException {
		Customer customer = getCustomerDetails(customerID);
		if(customer==null)
		throw new CustomerDetailsNotFoundException("Sorry no customer found");
		return postpaidAccountDAO.findAll();
	}
	@Override
	public Bill getMobileBillDetails(int customerID, long mobileNo, String billMonth)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, InvalidBillMonthException,
			BillDetailsNotFoundException {
		Customer customer = getCustomerDetails(customerID);
		if(customer==null)
		throw new CustomerDetailsNotFoundException("Sorry no customer found");
		Map<Long, PostpaidAccount> postPaidAccount = customer.getPostpaidAccounts();
		if(postPaidAccount==null)
		throw new PostpaidAccountNotFoundException("Customer has no plans");
		PostpaidAccount postpaidAcc=postPaidAccount.get(mobileNo);
		Map<Integer,Bill> bills =postpaidAcc.getBills();
		bills.get(billMonth);
		return billDAO.findParticularBill(mobileNo, billMonth);
	}
	@Override
	public List<Bill> getCustomerPostPaidAccountAllBillDetails(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException {
		Customer customer = getCustomerDetails(customerID);
		if(customer==null)
		throw new CustomerDetailsNotFoundException("Sorry no customer found");
		Map<Long, PostpaidAccount> postPaidAccount = customer.getPostpaidAccounts();
		if(postPaidAccount==null)
		throw new PostpaidAccountNotFoundException("Customer has no plans");
		PostpaidAccount postpaidAcc=postPaidAccount.get(mobileNo);		
		return billDAO.findAllBills(mobileNo);
	}
	@Override
	public boolean changePlan(int customerID, long mobileNo, int planID)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, PlanDetailsNotFoundException {
		Plan plan = getPlanDetails(planID);
		if(plan==null)
			throw new PlanDetailsNotFoundException("No Plan with this Id exist");
		PostpaidAccount postpaidAccount =getPostPaidAccountDetails(customerID, mobileNo);
		postpaidAccount.setPlan(plan);
		postpaidAccountDAO.save(postpaidAccount);
		return true;
	}
    @Override
	public boolean closeCustomerPostPaidAccount(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException {
		Customer customer = getCustomerDetails(customerID);
		if(customer==null)
		throw new CustomerDetailsNotFoundException("Sorry no customer found");
		if(customer.getPostpaidAccounts().containsKey(mobileNo))
		   postpaidAccountDAO.deleteById(mobileNo);
		 else throw new PostpaidAccountNotFoundException("Customer has no plans");
		 return true;
	}
    @Override
	public boolean removeCustomerDetails(int customerID) throws CustomerDetailsNotFoundException {
    	Customer customer = getCustomerDetails(customerID);
		if(customer==null)
		throw new CustomerDetailsNotFoundException("Sorry no customer found");
		customerDAO.deleteById(customerID);
		return true;
	}
    @Override
	public Plan getCustomerPostPaidAccountPlanDetails(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, PlanDetailsNotFoundException {
			 PostpaidAccount postPaidAccount = getPostPaidAccountDetails(customerID, mobileNo);
		return postPaidAccount.getPlan();
	}
	@Override
	public int createPlan(int monthlyRental, int freeLocalCalls, int freeStdCalls, int freeLocalSMS, int freeStdSMS,
			int freeInternetDataUsageUnits, float localCallRate, float stdCallRate, float localSMSRate,
			float stdSMSRate, float internetDataUsageRate, String planCircle, String planName) {
	    Plan plan =new Plan(monthlyRental, freeLocalCalls, freeStdCalls, freeLocalSMS, freeStdSMS,
            freeInternetDataUsageUnits,localCallRate,stdCallRate,localSMSRate,stdSMSRate,internetDataUsageRate,planCircle,planName);
		planDAO.save(plan);
		return plan.getPlanID();
	}
	@Override
	public Plan getPlanDetails(int planID) throws PlanDetailsNotFoundException {
		return planDAO.findById(planID).orElseThrow(()->new PlanDetailsNotFoundException("This plan ID is not valid"));
	}
	@Override
	public List<Plan> getAllPlanDetails() {
		return planDAO.findAll();
	}
}
