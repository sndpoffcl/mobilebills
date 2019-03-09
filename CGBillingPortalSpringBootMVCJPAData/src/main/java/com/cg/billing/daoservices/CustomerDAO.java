package com.cg.billing.daoservices;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.billing.beans.Customer;

public interface CustomerDAO  extends JpaRepository<Customer, Integer>{
	
}
