package com.ravi.runner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.ravi.entity.CitizenPlan;
import com.ravi.repo.CitizenPlanRepo;

@Component
public class DataRunner  implements ApplicationRunner{
	
	@Autowired
	private CitizenPlanRepo repo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		
		// for delete the record first then inserted
		
		repo.deleteAll();
		
		//cash plan
		CitizenPlan c1 = new CitizenPlan();
		c1.setCitizenName("John");
		c1.setGender("Male");
		c1.setPlanName("Cash");
		c1.setPlanStatus("Approved");
		c1.setPlanStartDate(LocalDate.now());
		c1.setPlanEndDate(LocalDate.now().plusMonths(6));
		c1.setBenefitAmt(5000.00);
		
		//cash denied
		CitizenPlan c2 = new CitizenPlan();
		c2.setCitizenName("Smith");
		c2.setGender("Male");
		c2.setPlanName("Cash");
		c2.setPlanStatus("Denied");
		c2.setDenialReason("Rental income");
		
		//cash terminated
		CitizenPlan c3 = new CitizenPlan();
		c3.setCitizenName("Alin");
		c3.setGender("Fe-Male");
		c3.setPlanName("Cash");
		c3.setPlanStatus("Terminated");
		c3.setBenefitAmt(5000.00);
		c3.setPlanStartDate(LocalDate.now().minusMonths(4));
		c3.setPlanEndDate(LocalDate.now().plusMonths(6));
		c3.setDenialReason("Employee");
		
		
		//Food plan
				CitizenPlan c4 = new CitizenPlan();
				c4.setCitizenName("Petter");
				c4.setGender("Male");
				c4.setPlanName("Food");
				c4.setPlanStatus("Approved");
				c4.setPlanStartDate(LocalDate.now());
				c4.setPlanEndDate(LocalDate.now().plusMonths(6));
				c4.setBenefitAmt(4000.00);
				
				//Food denied
				CitizenPlan c5 = new CitizenPlan();
				c5.setCitizenName("Robert");
				c5.setGender("Male");
				c5.setPlanName("Food");
				c5.setPlanStatus("Denied");
				c5.setDenialReason("Property Income");
				
				//Food terminated
				CitizenPlan c6 = new CitizenPlan();
				c6.setCitizenName("Chaty");
				c6.setGender("Fe-Male");
				c6.setPlanName("Food");
				c6.setPlanStatus("Terminated");
				c6.setBenefitAmt(6000.00);
				c6.setPlanStartDate(LocalDate.now().minusMonths(4));
				c6.setPlanEndDate(LocalDate.now().plusMonths(6));
				c6.setDenialReason(" Government Employee");
				
				//medical plan
				CitizenPlan c7 = new CitizenPlan();
				c7.setCitizenName("David");
				c7.setGender("Male");
				c7.setPlanName("Medical");
				c7.setPlanStatus("Approved");
				c7.setPlanStartDate(LocalDate.now());
				c7.setPlanEndDate(LocalDate.now().plusMonths(6));
				c7.setBenefitAmt(3000.00);
				
				//cash denied
				CitizenPlan c8 = new CitizenPlan();
				c8.setCitizenName("Steve");
				c8.setGender("Male");
				c8.setPlanName("Medical");
				c8.setPlanStatus("Denied");
				c8.setDenialReason("Rental income");
				
				//cash terminated
				CitizenPlan c9 = new CitizenPlan();
				c9.setCitizenName("Orlen");
				c9.setGender("Fe-Male");
				c9.setPlanName("Medical");
				c9.setPlanStatus("Terminated");
				c9.setBenefitAmt(6000.00);
				c9.setPlanStartDate(LocalDate.now().minusMonths(4));
				c9.setPlanEndDate(LocalDate.now().plusMonths(6));
				c9.setDenialReason("Gov Job");
				
				//Employment  plan
				CitizenPlan c10 = new CitizenPlan();
				c10.setCitizenName("Trump");
				c10.setGender("Male");
				c10.setPlanName("Employment");
				c10.setPlanStatus("Employment");
				c10.setPlanStartDate(LocalDate.now());
				c10.setPlanEndDate(LocalDate.now().plusMonths(6));
				c10.setBenefitAmt(9000.00);
				
				//Employment denied
				CitizenPlan c11 = new CitizenPlan();
				c11.setCitizenName("Darvin");
				c11.setGender("Male");
				c11.setPlanName("Employment");
				c11.setPlanStatus("Denied");
				c11.setDenialReason("OverAge");
				
				//Employment terminated
				CitizenPlan c12 = new CitizenPlan();
				c12.setCitizenName("Neel");
				c12.setGender("Fe-Male");
				c12.setPlanName("Employment");
				c12.setPlanStatus("Terminated");
				c12.setBenefitAmt(7000.00);
				c12.setPlanStartDate(LocalDate.now().minusMonths(4));
				c12.setPlanEndDate(LocalDate.now().plusMonths(6));
				c12.setDenialReason("Gov Job");
				
				List<CitizenPlan> list = Arrays.asList(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12);
				repo.saveAll(list);
	}

}
