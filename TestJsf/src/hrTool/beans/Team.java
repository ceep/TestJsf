package hrTool.beans;

import java.io.Serializable;
import java.util.Date;

public class Team implements Serializable{
	
	private String name;
	private int numberOfEmployees;
	private int numberOfEmployeesOnVacation;
	private Date formedDate;
	private int id;
	private int companyId;
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumberOfEmployees() {
		return numberOfEmployees;
	}
	public void setNumberOfEmployees(int numberOfEmployees) {
		this.numberOfEmployees = numberOfEmployees;
	}
	public int getNumberOfEmployeesOnVacation() {
		return numberOfEmployeesOnVacation;
	}
	public void setNumberOfEmployeesOnVacation(int numberOfEmployeesOnVacation) {
		this.numberOfEmployeesOnVacation = numberOfEmployeesOnVacation;
	}
	public Date getFormedDate() {
		if(formedDate==null) return new Date();
		return formedDate;
	}
	public void setFormedDate(Date formedDate) {
		this.formedDate = formedDate;
	}
	
	
	
}
