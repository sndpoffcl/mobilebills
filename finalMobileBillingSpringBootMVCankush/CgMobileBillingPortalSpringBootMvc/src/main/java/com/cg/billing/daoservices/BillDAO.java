package com.cg.billing.daoservices;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.cg.billing.beans.Bill;

public interface BillDAO extends JpaRepository<Bill, Integer>{
	@Query("from Bill b where b.postpaidAccount.mobileNo=:mobileNo and billMonth=:billMonth")
	public Bill findParticularBill(@Param("mobileNo")long mobileNo,@Param("billMonth")String billMonth);
	@Query("from Bill b where b.postpaidAccount.mobileNo=:mobileNo")
	public List<Bill>findAllBills(@Param("mobileNo")long mobileNo);
}
