package com.cg.billing.beans;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
@Entity

public class Bill {
	@SequenceGenerator(name="bill", initialValue=1, allocationSize=10000,sequenceName="billSequence")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="bill")
	@Id
	private int billID; 
	private int noOfLocalSMS, noOfStdSMS, noOfLocalCalls, noOfStdCalls, internetDataUsageUnits;
	private String billMonth;
	private float totalBillAmount, localSMSAmount, stdSMSAmount, localCallAmount, stdCallAmount, internetDataUsageAmount,stateGST,centralGST;
   @ManyToOne
	private PostpaidAccount postpaidAccount;
public Bill() {}
public Bill(int noOfLocalSMS, int noOfStdSMS, int noOfLocalCalls, int noOfStdCalls, int internetDataUsageUnits,
		String billMonth, float stateGST, float centralGST, PostpaidAccount postpaidAccount) {
	super();
	this.noOfLocalSMS = noOfLocalSMS;
	this.noOfStdSMS = noOfStdSMS;
	this.noOfLocalCalls = noOfLocalCalls;
	this.noOfStdCalls = noOfStdCalls;
	this.internetDataUsageUnits = internetDataUsageUnits;
	this.billMonth = billMonth;
	this.stateGST = stateGST;
	this.centralGST = centralGST;
	this.postpaidAccount = postpaidAccount;
}
public Bill(int billID, int noOfLocalSMS, int noOfStdSMS, int noOfLocalCalls, int noOfStdCalls,
		int internetDataUsageUnits, String billMonth, float totalBillAmount, float localSMSAmount, float stdSMSAmount,
		float localCallAmount, float stdCallAmount, float internetDataUsageAmount, float stateGST, float centralGST,
		PostpaidAccount postpaidAccount) {
	super();
	this.billID = billID;
	this.noOfLocalSMS = noOfLocalSMS;
	this.noOfStdSMS = noOfStdSMS;
	this.noOfLocalCalls = noOfLocalCalls;
	this.noOfStdCalls = noOfStdCalls;
	this.internetDataUsageUnits = internetDataUsageUnits;
	this.billMonth = billMonth;
	this.totalBillAmount = totalBillAmount;
	this.localSMSAmount = localSMSAmount;
	this.stdSMSAmount = stdSMSAmount;
	this.localCallAmount = localCallAmount;
	this.stdCallAmount = stdCallAmount;
	this.internetDataUsageAmount = internetDataUsageAmount;
	this.stateGST = stateGST;
	this.centralGST = centralGST;
	this.postpaidAccount = postpaidAccount;
}
public int getBillID() {
	return billID;
}
public void setBillID(int billID) {
	this.billID = billID;
}
public int getNoOfLocalSMS() {
	return noOfLocalSMS;
}
public void setNoOfLocalSMS(int noOfLocalSMS) {
	this.noOfLocalSMS = noOfLocalSMS;
}
public int getNoOfStdSMS() {
	return noOfStdSMS;
}
public void setNoOfStdSMS(int noOfStdSMS) {
	this.noOfStdSMS = noOfStdSMS;
}
public int getNoOfLocalCalls() {
	return noOfLocalCalls;
}
public void setNoOfLocalCalls(int noOfLocalCalls) {
	this.noOfLocalCalls = noOfLocalCalls;
}
public int getNoOfStdCalls() {
	return noOfStdCalls;
}
public void setNoOfStdCalls(int noOfStdCalls) {
	this.noOfStdCalls = noOfStdCalls;
}
public int getInternetDataUsageUnits() {
	return internetDataUsageUnits;
}
public void setInternetDataUsageUnits(int internetDataUsageUnits) {
	this.internetDataUsageUnits = internetDataUsageUnits;
}
public String getBillMonth() {
	return billMonth;
}
public void setBillMonth(String billMonth) {
	this.billMonth = billMonth;
}
public float getTotalBillAmount() {
	return totalBillAmount;
}
public void setTotalBillAmount(float totalBillAmount) {
	this.totalBillAmount = totalBillAmount;
}
public float getLocalSMSAmount() {
	return localSMSAmount;
}
public void setLocalSMSAmount(float localSMSAmount) {
	this.localSMSAmount = localSMSAmount;
}
public float getStdSMSAmount() {
	return stdSMSAmount;
}
public void setStdSMSAmount(float stdSMSAmount) {
	this.stdSMSAmount = stdSMSAmount;
}
public float getLocalCallAmount() {
	return localCallAmount;
}
public void setLocalCallAmount(float localCallAmount) {
	this.localCallAmount = localCallAmount;
}
public float getStdCallAmount() {
	return stdCallAmount;
}
public void setStdCallAmount(float stdCallAmount) {
	this.stdCallAmount = stdCallAmount;
}
public float getInternetDataUsageAmount() {
	return internetDataUsageAmount;
}
public void setInternetDataUsageAmount(float internetDataUsageAmount) {
	this.internetDataUsageAmount = internetDataUsageAmount;
}
public float getStateGST() {
	return stateGST;
}
public void setStateGST(float stateGST) {
	this.stateGST = stateGST;
}
public float getCentralGST() {
	return centralGST;
}
public void setCentralGST(float centralGST) {
	this.centralGST = centralGST;
}
public PostpaidAccount getPostpaidAccount() {
	return postpaidAccount;
}
public void setPostpaidAccount(PostpaidAccount postpaidAccount) {
	this.postpaidAccount = postpaidAccount;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + billID;
	result = prime * result + ((billMonth == null) ? 0 : billMonth.hashCode());
	result = prime * result + Float.floatToIntBits(centralGST);
	result = prime * result + Float.floatToIntBits(internetDataUsageAmount);
	result = prime * result + internetDataUsageUnits;
	result = prime * result + Float.floatToIntBits(localCallAmount);
	result = prime * result + Float.floatToIntBits(localSMSAmount);
	result = prime * result + noOfLocalCalls;
	result = prime * result + noOfLocalSMS;
	result = prime * result + noOfStdCalls;
	result = prime * result + noOfStdSMS;
	result = prime * result + ((postpaidAccount == null) ? 0 : postpaidAccount.hashCode());
	result = prime * result + Float.floatToIntBits(stateGST);
	result = prime * result + Float.floatToIntBits(stdCallAmount);
	result = prime * result + Float.floatToIntBits(stdSMSAmount);
	result = prime * result + Float.floatToIntBits(totalBillAmount);
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Bill other = (Bill) obj;
	if (billID != other.billID)
		return false;
	if (billMonth == null) {
		if (other.billMonth != null)
			return false;
	} else if (!billMonth.equals(other.billMonth))
		return false;
	if (Float.floatToIntBits(centralGST) != Float.floatToIntBits(other.centralGST))
		return false;
	if (Float.floatToIntBits(internetDataUsageAmount) != Float.floatToIntBits(other.internetDataUsageAmount))
		return false;
	if (internetDataUsageUnits != other.internetDataUsageUnits)
		return false;
	if (Float.floatToIntBits(localCallAmount) != Float.floatToIntBits(other.localCallAmount))
		return false;
	if (Float.floatToIntBits(localSMSAmount) != Float.floatToIntBits(other.localSMSAmount))
		return false;
	if (noOfLocalCalls != other.noOfLocalCalls)
		return false;
	if (noOfLocalSMS != other.noOfLocalSMS)
		return false;
	if (noOfStdCalls != other.noOfStdCalls)
		return false;
	if (noOfStdSMS != other.noOfStdSMS)
		return false;
	if (postpaidAccount == null) {
		if (other.postpaidAccount != null)
			return false;
	} else if (!postpaidAccount.equals(other.postpaidAccount))
		return false;
	if (Float.floatToIntBits(stateGST) != Float.floatToIntBits(other.stateGST))
		return false;
	if (Float.floatToIntBits(stdCallAmount) != Float.floatToIntBits(other.stdCallAmount))
		return false;
	if (Float.floatToIntBits(stdSMSAmount) != Float.floatToIntBits(other.stdSMSAmount))
		return false;
	if (Float.floatToIntBits(totalBillAmount) != Float.floatToIntBits(other.totalBillAmount))
		return false;
	return true;
}
@Override
public String toString() {
	return "Bill [billID=" + billID + ", noOfLocalSMS=" + noOfLocalSMS + ", noOfStdSMS=" + noOfStdSMS
			+ ", noOfLocalCalls=" + noOfLocalCalls + ", noOfStdCalls=" + noOfStdCalls + ", internetDataUsageUnits="
			+ internetDataUsageUnits + ", billMonth=" + billMonth + ", totalBillAmount=" + totalBillAmount
			+ ", localSMSAmount=" + localSMSAmount + ", stdSMSAmount=" + stdSMSAmount + ", localCallAmount="
			+ localCallAmount + ", stdCallAmount=" + stdCallAmount + ", internetDataUsageAmount="
			+ internetDataUsageAmount + ", stateGST=" + stateGST + ", centralGST=" + centralGST + "]";
}
   
}