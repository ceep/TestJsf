package hrTool.wrappers;

import hrTool.application.Application;
import hrTool.controller.RequestsController;
import hrTool.controller.TasksAssociationController;
import hrTool.controller.TasksController;
import hrTool.controller.TeamController;
import hrTool.model.Employee;
import hrTool.model.Request;
import hrTool.model.Task;
import hrTool.model.Taskassociation;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class EmployeeWrapper implements Serializable{

	private Employee employee;
	private String teamName;
	
	private int requests;
	
	private List <String> tasks;
	private List <String> taskCodes;
	private List <String> newTaskCodes;
	
	public List<String> getNewTaskCodes() {
		return newTaskCodes;
	}


	public void setNewTaskCodes(List<String> newTaskCodes) {
		this.newTaskCodes = newTaskCodes;
	}


	public List<String> getTaskCodes() {
		return taskCodes;
	}


	public void setTaskCodes(List<String> taskCodes) {
		this.taskCodes = taskCodes;
	}


	public EmployeeWrapper(){
		tasks = new LinkedList<String>();
	}
	
	
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public String getTeamName() {
		
		if(employee.getTeamId()!=-1)
			teamName = new TeamController(Application.getInstance().getEntityManagerFactory()).getTeam(employee.getTeamId()).getName();
		else
			teamName = "None";
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public List<String> getTasks() {
		tasks.clear();
		
		List <Taskassociation> ta = new TasksAssociationController(Application.getInstance().getEntityManagerFactory()).getTasksByEmployee(employee.getEmployeeId());		
		
		for (Taskassociation taskassociation : ta) {
			
			List <Task> temp = new TasksController(Application.getInstance().getEntityManagerFactory()).getTasksById(taskassociation.getTaskId());
			
			for (Task task : temp) {
				tasks.add(task.getCode());
			}
		}
		
		return tasks;
	}
	public void setTasks(List<String> tasks) {
		this.tasks = tasks;
	}
	public int getRequests() {
		requests = new RequestsController(Application.getInstance().getEntityManagerFactory()).getNumberOfRequestsByEmployee(employee.getEmployeeId());
		return requests;
	}
	public void setRequests(int requests) {
		this.requests = requests;
	}
	
	
	
	
	
	
}
