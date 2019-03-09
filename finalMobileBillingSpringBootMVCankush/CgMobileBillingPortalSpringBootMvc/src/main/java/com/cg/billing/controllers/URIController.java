package com.cg.billing.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cg.billing.beans.Bill;
import com.cg.billing.beans.Customer;
import com.cg.billing.beans.Plan;
import com.cg.billing.beans.PostpaidAccount;

@Controller
public class URIController {
	private Customer customer;
	private Bill bill;
	private PostpaidAccount postpaidAccount;
	private Plan plan;

	@RequestMapping(value="/")
	public String getIndexPage() {
		return "Index";
	}
	@RequestMapping("/addCustomer")
	public String getAddCustomer() {
		return "getAddCustomer";
	}
	@RequestMapping("/findCustomer")
	public String getFindCustomer() {
		return "findCustomerDetails";
	}
	@RequestMapping("/openAccount")
	public String getOpenAccount() {
		return "openPostpaidAccount";
	}
	@RequestMapping("/getPostpaidDetails")
	public String getPostpaidDetails() {
		return "getPostpaidDetails";
	}
	@RequestMapping("/getAllPostpaidAccounts")
	public String getAllPostpaidAccount() {
		return "getAllPostpaidAccount";
	}
	@RequestMapping("/getChangePlan")
	public String getPlanChange() {
		return "getChangePlan";
	}
	@RequestMapping("/getPlanDetails")
	public String getPlanDetails() {
		return "getPlanDetails";
	}
	@RequestMapping("/getPostpaidAccountPlanDetails")
	public String getPostpaidAccountPlanDetails() {
		return "getPostpaidAccountPlanDetails";
	}
	@RequestMapping("/getClosePostpaidAccount")
	public String getClosePostpaidAccount() {
		return "getClosePostpaidAccount";
	}
	@RequestMapping("/getRemoveCustomer")
	public String getRemoveCustomer() {
		return "getRemoveCustomer";
	}
	@RequestMapping("/getCreatePlan")
	public String getCreatePlan() {
		return "getCreatePlan";
	}
	@RequestMapping("/getGenerateBill")
	public String getGenerateBill() {
		return "getGenerateBill";
	}
	@RequestMapping("/getBillDetails")
	public String getBillDetails() {
		return "getBillDetails";
	}
	@RequestMapping("/getAllBillDetails")
	public String getAllBillDetails() {
		return "getAllBillDetails";
	}
	
	
	
	
	
	
	
	
	@ModelAttribute
	public Customer getCustomer() {
		customer=new Customer();
		return customer;
	}
	@ModelAttribute
	public Plan getPlan() {
		plan=new Plan();
		return plan;}

	@ModelAttribute
	public PostpaidAccount getPostpaid() {
		postpaidAccount=new PostpaidAccount();
		return postpaidAccount;
	}
	@ModelAttribute
	public Bill getAssociate() {
		bill=new Bill();
		return bill;
	}
}

