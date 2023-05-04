package com.ravi.service;


import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.ravi.entity.CitizenPlan;
import com.ravi.repo.CitizenPlanRepo;
import com.ravi.request.SearchRequest;
import com.ravi.util.EmailUtils;
import com.ravi.util.ExcelGenerator;
import com.ravi.util.PdfGenerator;
@Service
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	private CitizenPlanRepo planRepo;
	
	@Autowired
	private ExcelGenerator excelGenerator;
	
	@Autowired
	private PdfGenerator  pdfGenerator;
	
	@Autowired
	private EmailUtils emailUtils;
	
	@Override
	public List<String> getPlanNames() {
		return  planRepo.getPlanName();
		
	}

	@Override
	public List<String> getPlanStatuses() {
		
		return planRepo.getPlanStatus();
	}

	@Override
	public List<CitizenPlan> search(SearchRequest request) {
		
		CitizenPlan entity = new CitizenPlan();
		
		if(null != request.getPlaneName() && !"" .equals(request.getPlaneName())) {
			entity.setPlanName(request.getPlaneName());
		}
		
		if(null != request.getPlaneStatus() && !"" .equals(request.getPlaneStatus())) {
			entity.setPlanStatus(request.getPlaneStatus());
		}
		if(null != request.getGender() && !"" .equals(request.getGender())) {
			entity.setGender(request.getGender());
		}
		
		if(null != request.getStartDate() && !"".equals(request.getStartDate())) {
			String startDate= request.getStartDate();
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			  //String date = "16/08/2016";

			  //convert String to LocalDate
			  LocalDate localDate = LocalDate.parse(startDate, formatter);
			  entity.setPlanStartDate(localDate);
		
		}
		
		if(null != request.getEndDate() && !"".equals(request.getEndDate())) {
			String endDate= request.getStartDate();
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			  //String date = "16/08/2016";

			  //convert String to LocalDate
			  LocalDate localDate = LocalDate.parse(endDate, formatter);
			  entity.setPlanEndDate(localDate);
		
		}
		
		return planRepo.findAll(Example.of(entity));
		 
	}

	@Override
	public boolean exportExcel(HttpServletResponse response) throws Exception {
		
		File f = new File("Plans.xls");
		
		List<CitizenPlan> plans = planRepo.findAll();
			excelGenerator.generate(response, plans,f);
		String subject ="test mail subject";
		String body = "<h1>test mail body</h1>";
		String to ="ravimohale22@gmail.com";
		
		
		emailUtils.sendMail(subject, body, to,f);
		
		f.delete();
			
		return true ;
	}

	@Override
	public boolean exportPdf(HttpServletResponse response) throws Exception {
		File f = new File("Plans.pdf");
		
		List<CitizenPlan> plans = planRepo.findAll();
		pdfGenerator.generate(response, plans,f);
		String subject ="test mail subject";
		String body = "<h1>test mail body</h1>";
		String to ="ravimohale22@gmail.com";
		emailUtils.sendMail(subject, body, to,f);
		
		f.delete();
		return true;
	}

	

	

}
