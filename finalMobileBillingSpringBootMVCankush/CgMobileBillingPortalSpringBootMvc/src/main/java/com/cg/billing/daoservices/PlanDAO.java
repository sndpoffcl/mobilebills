package com.cg.billing.daoservices;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cg.billing.beans.Plan;
public interface PlanDAO extends JpaRepository<Plan, Integer>{
}
