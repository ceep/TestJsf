package hrTool.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;

public class Employee implements Serializable{

	
	private int id;
	private int companyId;
	private String team;
	private String name;
	private int daysOff;
	private int specialDaysOff;
	private Date joinDate;
	private Date endDate;
	private LinkedList <String> associatedTasks;
	private LinkedList <String> pendingRequests;
	
	public LinkedList<String> getAssociatedTasks() {
		return associatedTasks;
	}
	public void setAssociatedTasks(LinkedList<String> associatedTasks) {
		this.associatedTasks = associatedTasks;
	}
	public LinkedList<String> getPendingRequests() {
		return pendingRequests;
	}
	public void setPendingRequests(LinkedList<String> pendingRequests) {
		this.pendingRequests = pendingRequests;
	}
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
	public String getTeam() {
		if(team==null) team = new String();
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDaysOff() {
		return daysOff;
	}
	public void setDaysOff(int daysOff) {
		this.daysOff = daysOff;
	}
	public int getSpecialDaysOff() {
		return specialDaysOff;
	}
	public void setSpecialDaysOff(int specialDaysOff) {
		this.specialDaysOff = specialDaysOff;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
	
}
