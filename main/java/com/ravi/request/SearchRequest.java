package com.ravi.request;



import lombok.Data;
@Data
public class SearchRequest {
	private String planeName;
	private String planeStatus;
	private String gender;
	private String startDate;
	private String endDate;

}
