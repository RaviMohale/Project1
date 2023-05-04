package com.ravi.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ravi.entity.CitizenPlan;



public interface CitizenPlanRepo extends JpaRepository<CitizenPlan, Integer>{
	
	@Query("select distinct(planName)from CitizenPlan ")
	public List<String> getPlanName();
	
	@Query("select distinct(planStatus)from CitizenPlan")
	public List<String> getPlanStatus();

}
