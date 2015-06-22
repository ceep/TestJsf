package hrTool.beans;

import java.io.Serializable;
import java.util.LinkedList;

public class DashBoardAggregator implements Serializable{

	private String name;
	private int numberOfEmployeesOnVacation;
	private LinkedList <String> tasks;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getNumberOfEmployeesOnVacation() {
		return numberOfEmployeesOnVacation;
	}
	public void setNumberOfEmployeesOnVacation(int numberOfEmployeesOnVacation) {
		this.numberOfEmployeesOnVacation = numberOfEmployeesOnVacation;
	}
	public LinkedList<String> getTasks() {
		return tasks;
	}
	public void setTasks(LinkedList<String> tasks) {
		this.tasks = tasks;
	}
	
	
	
	
	
}
