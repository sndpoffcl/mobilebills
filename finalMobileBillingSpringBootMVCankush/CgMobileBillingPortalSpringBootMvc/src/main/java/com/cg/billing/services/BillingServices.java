package com.cg.billing.services;
import java.util.List;
import com.cg.billing.beans.Bill;
import com.cg.billing.beans.Customer;
import com.cg.billing.beans.Plan;
import com.cg.billing.beans.PostpaidAccount;
import com.cg.billing.exceptions.BillDetailsNotFoundException;
import com.cg.billing.exceptions.CustomerDetailsNotFoundException;
import com.cg.billing.exceptions.InvalidBillMonthException;
import com.cg.billing.exceptions.PlanDetailsNotFoundException;
import com.cg.billing.exceptions.PostpaidAccountNotFoundException;
public interface BillingServices {
	
	int acceptCustomerDetails(String firstName, String lastName, String emailID, String dateOfBirth, String homeCity,String homeState,int homePinCode,String billingAddressCity,String billingAddressState,int billingAddressPinCode);

	long openPostpaidMobileAccount(int customerID, int planID) 
			throws PlanDetailsNotFoundException,CustomerDetailsNotFoundException;
	
	int createPlan(int monthlyRental, int freeLocalCalls, int freeStdCalls, int freeLocalSMS, int freeStdSMS,
			int freeInternetDataUsageUnits, float localCallRate, float stdCallRate, float localSMSRate,
			float stdSMSRate, float internetDataUsageRate, String planCircle, String planName);
	
	Plan getPlanDetails(int planID)throws PlanDetailsNotFoundException;
	
	Customer getCustomerDetails(int customerID)
			throws CustomerDetailsNotFoundException;
	
	List<Customer>  getAllCustomerDetails() ;
	
	PostpaidAccount getPostPaidAccountDetails(int customerID, long mobileNo) 
			throws CustomerDetailsNotFoundException, 
			PostpaidAccountNotFoundException;
	
	List<PostpaidAccount> getCustomerAllPostpaidAccountsDetails(int customerID)
			throws CustomerDetailsNotFoundException;
	
	Bill getMobileBillDetails(int customerID, long mobileNo, String billMonth)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, 
			InvalidBillMonthException, BillDetailsNotFoundException;
	
	List<Bill>   getCustomerPostPaidAccountAllBillDetails(int customerID, long mobileNo) 
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException;
	
	boolean changePlan(int customerID, long mobileNo, int planID)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, PlanDetailsNotFoundException;
	
	boolean closeCustomerPostPaidAccount(int customerID, long mobileNo) 
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException;
	
	boolean removeCustomerDetails(int customerID) 
			throws CustomerDetailsNotFoundException;
	
	Plan getCustomerPostPaidAccountPlanDetails(int customerID, long mobileNo) 
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException,PlanDetailsNotFoundException ;
	
	public List<Plan> getAllPlanDetails();

	double  generateMonthlyMobileBill(int customerID, long mobileNo, String billMonth, int noOfLocalSMS, int noOfStdSMS, int noOfLocalCalls, int noOfStdCalls,int internetDataUsageUnits) 
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, 
			InvalidBillMonthException;
}