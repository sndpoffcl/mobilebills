package com.cg.billing.beans;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int customerId;


	private String firstName, lastName, emailID, dateOfBirth;
	@AttributeOverrides({
	    @AttributeOverride(name="city", column= @Column(name="billingCity")),
	    @AttributeOverride(name="state", column= @Column(name="billingState")),
	    @AttributeOverride(name="pinCode", column= @Column(name="billingPinCode"))
	  })
	@Embedded
	private Address billingAddress;
	@AttributeOverrides({
	    @AttributeOverride(name="city", column= @Column(name="homeCity")),
	    @AttributeOverride(name="state", column= @Column(name="homeState")),
	    @AttributeOverride(name="pinCode", column= @Column(name="homePincode"))
	  })
	@Embedded
	private Address homeAddress;
	
	@OneToMany(mappedBy="customer")
	@MapKey
	private Map<Long, PostpaidAccount> postpaidAccounts = new HashMap<>();
	
	public Customer() {}

	public Customer(String firstName, String lastName, String emailID, String dateOfBirth, Address billingAddress,
			Address homeAddress, Map<Long, PostpaidAccount> postpaidAccounts) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailID = emailID;
		this.dateOfBirth = dateOfBirth;
		this.billingAddress = billingAddress;
		this.homeAddress = homeAddress;
		this.postpaidAccounts = postpaidAccounts;
	}

	public Customer(int customerID, String firstName, String lastName, String emailID, String dateOfBirth,
			Address billingAddress, Address homeAddress, Map<Long, PostpaidAccount> postpaidAccounts) {
		super();
		this.customerId = customerID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailID = emailID;
		this.dateOfBirth = dateOfBirth;
		this.billingAddress = billingAddress;
		this.homeAddress = homeAddress;
		this.postpaidAccounts = postpaidAccounts;
	}

	public int getCustomerID() {
		return customerId;
	}

	public void setCustomerID(int customerID) {
		this.customerId = customerID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

	public Map<Long, PostpaidAccount> getPostpaidAccounts() {
		return postpaidAccounts;
	}

	public void setPostpaidAccounts(Map<Long, PostpaidAccount> postpaidAccounts) {
		this.postpaidAccounts = postpaidAccounts;
	}

	
}