package com.jagadeeshp.vo;

public class GenericMessageVO {
	
	private String status = "FAILURE";
	private String statusDescription = "";
	private Integer affectedRows = 0;
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getAffectedRows() {
		return affectedRows;
	}
	public void setAffectedRows(Integer affectedRows) {
		this.affectedRows = affectedRows;
	}
	public String getStatusDescription() {
		return statusDescription;
	}
	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}
	
	
	

}
