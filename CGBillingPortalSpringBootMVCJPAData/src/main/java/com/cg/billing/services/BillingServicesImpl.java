package com.cg.billing.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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


enum BillMonths{
	January, February , March , April , May, June , July , August , September , October, November , December ;
}

@Component("billingServices")
public class BillingServicesImpl implements BillingServices{
	
	@Autowired
	CustomerDAO customerDaoServices ;
	@Autowired
	BillDAO billDaoServices  ;
	@Autowired
	PlanDAO planDaoServices  ;
	@Autowired
	PostpaidAccountDAO posDaoServices ;

	@Override
	public List<Plan> getPlanAllDetails() {
		return planDaoServices.findAll();
	}

	@Override
	public int acceptCustomerDetails(String firstName, String lastName, String emailID, String dateOfBirth,
			String billingAddressCity, String billingAddressState, int billingAddressPinCode, String homeAddressCity,
			String homeAddressState, int homeAddressPinCode) {
		
		Address billingAddress= new Address(billingAddressCity,billingAddressState,billingAddressPinCode);
		Address homeAddress = new Address(homeAddressCity,homeAddressState,homeAddressPinCode);
		Customer customer  = new Customer(firstName , lastName , emailID , dateOfBirth,billingAddress , homeAddress ,new HashMap<Long, PostpaidAccount>() );
		customer =  customerDaoServices.save(customer);
		return customer.getCustomerID();
	}

	@Override
	public long openPostpaidMobileAccount(int customerID, int planID)
			throws PlanDetailsNotFoundException, CustomerDetailsNotFoundException {
		
		PostpaidAccount posAccount = new PostpaidAccount(getCustomerDetails(customerID), getPlanDetails(planID), new HashMap<Integer,Bill>());
		posAccount =  posDaoServices.save(posAccount);
		return posAccount.getMobileNo();
	}
	
	@Override
	public Plan getPlanDetails(int planId) throws PlanDetailsNotFoundException {
		Plan plan = planDaoServices.findById(planId).orElseThrow(
				()-> new PlanDetailsNotFoundException("No plans found for plan id :" + planId));
		return plan;
	}

	@Override
	public double generateMonthlyMobileBill(int customerID, long mobileNo, String billMonth, int noOfLocalSMS,
			int noOfStdSMS, int noOfLocalCalls, int noOfStdCalls, int internetDataUsageUnits)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, InvalidBillMonthException {
		
		double costOfLocalSms,costOfLocalCalls,costOfStdSms,costOfStdCalls,costOfDataUsage;
		
		Plan plan = getCustomerDetails(customerID).getPostpaidAccounts().get(mobileNo).getPlan();
		//calculating local sms rate
		if(noOfLocalSMS<plan.getFreeLocalSMS()) {
			 costOfLocalSms = 0;
		}else {
			 costOfLocalSms = (noOfLocalSMS-plan.getFreeLocalSMS())*plan.getLocalSMSRate();
		}
		//calculating local calls rate
		if(noOfLocalCalls<plan.getFreeLocalCalls()) {
			 costOfLocalCalls = 0;
		}else {
			 costOfLocalCalls = (noOfLocalCalls-plan.getFreeLocalCalls())*plan.getLocalCallRate();
		}
		//calculating std sms rate
		if(noOfStdSMS<plan.getFreeStdSMS()) {
			 costOfStdSms = 0;
		}else {
			 costOfStdSms = (noOfStdSMS-plan.getFreeStdSMS())*plan.getStdSMSRate();
		}
		// calculating std call rate
		if(noOfStdCalls<plan.getFreeStdCalls()) {
			 costOfStdCalls = 0;
		}else {
			 costOfStdCalls = (noOfStdCalls-plan.getFreeStdCalls())*plan.getStdCallRate();
		}
		//calculating data usage charges
		if(internetDataUsageUnits<plan.getFreeInternetDataUsageUnits()) {
			 costOfDataUsage = 0;
		}else {
			 costOfDataUsage = (internetDataUsageUnits-plan.getFreeInternetDataUsageUnits())*plan.getInternetDataUsageRate();
		}
		
		double totalBillBeforeTaxes =  costOfDataUsage + costOfLocalCalls + costOfLocalSms + costOfStdCalls + costOfStdSms ; 
		double cgst = 0.05*totalBillBeforeTaxes;
		double sgst = 0.05*totalBillBeforeTaxes;
		
		double totalBillAfterTaxes = totalBillBeforeTaxes + cgst + sgst ; 
		
		Bill newBill = new Bill(noOfLocalSMS , noOfStdSMS , noOfLocalCalls , noOfStdCalls , internetDataUsageUnits , billMonth , totalBillAfterTaxes , costOfLocalSms , costOfStdSms , costOfLocalCalls , costOfStdCalls , costOfDataUsage , sgst , cgst);
		newBill = billDaoServices.save(newBill);
		return newBill.getTotalBillAmount();
	}

	@Override
	public Customer getCustomerDetails(int customerID) throws CustomerDetailsNotFoundException {
		Customer customer = customerDaoServices.findById(customerID).orElseThrow(
				()-> new CustomerDetailsNotFoundException("Customer details not found" + customerID));
			return customer;
		
	}

	@Override
	public List<Customer> getAllCustomerDetails() {
		return customerDaoServices.findAll();
	}

	@Override
	public PostpaidAccount getPostPaidAccountDetails(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException {
		PostpaidAccount postpaidAccount = getCustomerDetails(customerID).getPostpaidAccounts().get(mobileNo);
		if(postpaidAccount==null)
			throw new PostpaidAccountNotFoundException();
		else
			return postpaidAccount;
	}

	@Override
	public List<PostpaidAccount> getCustomerAllPostpaidAccountsDetails(int customerID)
			throws CustomerDetailsNotFoundException {
		
		return  (List<PostpaidAccount>) getCustomerDetails(customerID).getPostpaidAccounts().values();
	}

	@Override
	public Bill getMobileBillDetails(int customerID, long mobileNo, String billMonth)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, InvalidBillMonthException,
			BillDetailsNotFoundException {
		
		for (BillMonths month : BillMonths.values()) {
			if (month.name().equals(billMonth)) {
				Bill bill = getCustomerDetails(customerID).getPostpaidAccounts().get(mobileNo).getBills().get(billMonth);
				if(bill==null)
					throw new BillDetailsNotFoundException();
				else 
					return bill;
			}
		}
			throw new InvalidBillMonthException();
	}

	@Override
	public List<Bill> getCustomerPostPaidAccountAllBillDetails(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException {

		return (List<Bill>) getCustomerDetails(customerID).getPostpaidAccounts().get(mobileNo).getBills().values();
	}

	@Override
	public boolean changePlan(int customerID, long mobileNo, int planID)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, PlanDetailsNotFoundException {
		getCustomerDetails(customerID).getPostpaidAccounts().get(mobileNo).setPlan(getPlanDetails(planID));
		return true;
	}

	@Override
	public boolean closeCustomerPostPaidAccount(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException {
		
		PostpaidAccount posAccount = getCustomerDetails(customerID).getPostpaidAccounts().get(mobileNo);
		posDaoServices.deleteById(posAccount.getMobileNo());
		return true;
	}

	@Override
	public boolean removeCustomerDetails(int customerID) throws CustomerDetailsNotFoundException {
		Customer customer = getCustomerDetails(customerID);
		customerDaoServices.deleteById(customer.getCustomerID());
		return true;
	}

	@Override
	public Plan getCustomerPostPaidAccountPlanDetails(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, PlanDetailsNotFoundException {
		
		return getCustomerDetails(customerID).getPostpaidAccounts().get(mobileNo).getPlan();
	}
	
	

}
