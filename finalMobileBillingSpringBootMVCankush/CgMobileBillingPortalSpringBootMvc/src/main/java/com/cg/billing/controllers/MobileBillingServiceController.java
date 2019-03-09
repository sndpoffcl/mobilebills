package com.cg.billing.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cg.billing.beans.Bill;
import com.cg.billing.beans.Customer;
import com.cg.billing.beans.Plan;
import com.cg.billing.beans.PostpaidAccount;
import com.cg.billing.exceptions.BillDetailsNotFoundException;
import com.cg.billing.exceptions.CustomerDetailsNotFoundException;
import com.cg.billing.exceptions.InvalidBillMonthException;
import com.cg.billing.exceptions.PlanDetailsNotFoundException;
import com.cg.billing.exceptions.PostpaidAccountNotFoundException;
import com.cg.billing.services.BillingServices;

@RestController
public class MobileBillingServiceController {
	@Autowired
	private BillingServices billingServices;
	@RequestMapping("/allCustomers")
	public ModelAndView showAllCustomers() {
		java.util.List<Customer> customer= billingServices.getAllCustomerDetails();
		return new ModelAndView("showAllCustomers", "customer", customer);
	}
	@RequestMapping("/oneCustomer")
	public ModelAndView oneCustomer(@RequestParam int customerID) throws CustomerDetailsNotFoundException{
		Customer customer= billingServices.getCustomerDetails(customerID);
		return new ModelAndView("showCustomerDetails", "customer", customer);
	}
	@RequestMapping("/showOpenAccount")
	public ModelAndView openAccount(@RequestParam int customerID,  int planID) throws CustomerDetailsNotFoundException,PlanDetailsNotFoundException{
		 long mobileNo= billingServices.openPostpaidMobileAccount(customerID, planID);
		return new ModelAndView("showPostpaidAccountOpen", "mobileNo", mobileNo);
	}
	@RequestMapping("/showPostpaidDetails")
	public ModelAndView showpostpaidDetails(@RequestParam int customerID,  int mobileNo) throws CustomerDetailsNotFoundException,PostpaidAccountNotFoundException{
		 PostpaidAccount postpaidAccount= billingServices.getPostPaidAccountDetails(customerID, mobileNo);
		return new ModelAndView("showPostpaidAccountDetails", "postpaidAccount", postpaidAccount);
	}
	@RequestMapping("/showAllPostpaidAccount")
	public ModelAndView showAllpostpaidAccount(@RequestParam int customerID) throws CustomerDetailsNotFoundException{
		List <PostpaidAccount> postpaidAccount= billingServices.getCustomerAllPostpaidAccountsDetails(customerID);
		return new ModelAndView("showAllPostpaidAccount", "postpaidAccount", postpaidAccount);
	}
	@RequestMapping("/showChangePlan")
	public ModelAndView showChangePlan(@RequestParam int customerID,  int mobileNo, int planID)throws CustomerDetailsNotFoundException,PostpaidAccountNotFoundException,PlanDetailsNotFoundException{
        boolean success=billingServices.changePlan(customerID, mobileNo, planID);
        return new ModelAndView("showChangePlan","success",success);
	}
	@RequestMapping("/showPlanDetails")
	public ModelAndView showChangePlan(@RequestParam int planID)throws CustomerDetailsNotFoundException,PostpaidAccountNotFoundException,PlanDetailsNotFoundException{
        Plan plan=billingServices.getPlanDetails(planID);
        return new ModelAndView("showPlanDetails","plan",plan);
	}
	@RequestMapping("/showPostpaidAccountPlanDetails")
	public ModelAndView showPostpaidAccountPlanDetails(@RequestParam int customerID,  int mobileNo) throws CustomerDetailsNotFoundException,PostpaidAccountNotFoundException, PlanDetailsNotFoundException{
		 Plan plan= billingServices.getCustomerPostPaidAccountPlanDetails(customerID, mobileNo);
		return new ModelAndView("showPostpaidAccountPlanDetails", "plan", plan);
	}
	@RequestMapping("/showClosePostpaidAccount")
	public ModelAndView showClosePostpaidAccountPlanDetails(@RequestParam int customerID, int mobileNo) throws CustomerDetailsNotFoundException,PostpaidAccountNotFoundException, PlanDetailsNotFoundException{
		 boolean success= billingServices.closeCustomerPostPaidAccount(customerID, mobileNo);
		return new ModelAndView("showClosePostpaidAccount", "success", success);
	}
	@RequestMapping("/showRemoveCustomer")
	public ModelAndView showRemoveCustomer(@RequestParam int customerID) throws CustomerDetailsNotFoundException{
		boolean success= billingServices.removeCustomerDetails(customerID);
		return new ModelAndView("showRemoveCustomer", "success", success);
	}
	@RequestMapping("/showAllPlanDetails")
	public ModelAndView showAllPlanDetails() {
		List<Plan> plan= billingServices.getAllPlanDetails();
		return new ModelAndView("showAllPlanDetails", "plan", plan);
	}
	@RequestMapping("/showAddCustomer")
	public ModelAndView showAddCustomer(@RequestParam String firstName, String lastName, String emailID, String dateOfBirth, String homeCity,String homeState,int homePinCode,String billingAddressCity,String billingAddressState,int billingAddressPinCode ) {
		int customerId= billingServices.acceptCustomerDetails(firstName, lastName, emailID, dateOfBirth, homeCity, homeState, homePinCode, billingAddressCity, billingAddressState, billingAddressPinCode);
		return new ModelAndView("showAddCustomer","customerId",customerId);
	}
	@RequestMapping("/showCreatePlan")
	public ModelAndView showCreatePlan(@RequestParam int monthlyRental, int freeLocalCalls, int freeStdCalls, int freeLocalSMS, int freeStdSMS,
			int freeInternetDataUsageUnits, float localCallRate, float stdCallRate, float localSMSRate,
			float stdSMSRate, float internetDataUsageRate, String planCircle, String planName) {
		int planID= billingServices.createPlan(monthlyRental, freeLocalCalls, freeStdCalls, freeLocalSMS, freeStdSMS, freeInternetDataUsageUnits, localCallRate, stdCallRate, localSMSRate, stdSMSRate, internetDataUsageRate, planCircle, planName);
		return new ModelAndView("showCreatePlan","planID",planID);
	}
	@RequestMapping("/showBillDetails")
	public ModelAndView showBillDetails(@RequestParam int customerID,int mobileNo,String billMonth) throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, InvalidBillMonthException, BillDetailsNotFoundException{
		Bill bill= billingServices.getMobileBillDetails(customerID, mobileNo, billMonth);
		return new ModelAndView("showBillDetails", "bill", bill);
	}
	@RequestMapping("/showAllBillDetails")
	public ModelAndView showAllBillDetails(@RequestParam int customerID,int mobileNo) throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException,BillDetailsNotFoundException{
		List<Bill>bill= billingServices.getCustomerPostPaidAccountAllBillDetails(customerID, mobileNo);
		return new ModelAndView("showBillDetails", "bill", bill);
	}
	@RequestMapping("/showGenerateBill")
	public ModelAndView showGenerateBill(@RequestParam int customerID,int mobileNo ,String billMonth,int noOfLocalSMS,int noOfStdSMS,int noOfLocalCalls,int noOfStdCalls,int internetDataUsageUnits) throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException,BillDetailsNotFoundException, InvalidBillMonthException{
		Double amount= billingServices.generateMonthlyMobileBill(customerID, mobileNo, billMonth, noOfLocalSMS, noOfStdSMS, noOfLocalCalls, noOfStdCalls, internetDataUsageUnits);
		return new ModelAndView("showGenerateBill", "amount",amount);
	}
}

