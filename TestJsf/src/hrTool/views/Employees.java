package hrTool.views;

import hrTool.application.Application;
import hrTool.controller.EmployeeController;
import hrTool.controller.TasksController;
import hrTool.controller.TeamController;
import hrTool.model.Employee;
import hrTool.model.Request;
import hrTool.model.Task;
import hrTool.model.Team;
import hrTool.wrappers.EmployeeWrapper;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

@ManagedBean(name="employeesView")
@ViewScoped
public class Employees implements Serializable{

	private List <EmployeeWrapper> employees;
	
	
	
	private EmployeeWrapper employeeToEdit;
	private EmployeeWrapper employeeToDelete;
	private EmployeeWrapper employeeToAdd;
	
	private int employeeIdToEdit;
	
	private String newName;
	private int newTeamId;
	private int newDaysOff;
	private int newSpecialDaysOff;
	private Date newJoinDate;
	private Date newEndDate;
	private LinkedList <String> newAssociatedTasks;
	
	private LinkedList <SelectItem> teams;
	private LinkedList <SelectItem> tasks;
	

	public LinkedList<SelectItem> getTasks() {
		tasks.clear();
		// need to set the company id
		List <Task> ta = new TasksController(Application.getInstance().getEntityManagerFactory()).getTasksByCompany(1);
		for (Task task : ta) {
			tasks.add(new SelectItem(task.getTaskId()+"", task.getCode()));
		}
		return tasks;
	}
	public void setTasks(LinkedList<SelectItem> tasks) {
		this.tasks = tasks;
	}
	public LinkedList<SelectItem> getTeams() {
		teams.clear();
		// need to set the company
		List<Team> teamList = new TeamController(Application.getInstance().getEntityManagerFactory()).getTeamsByCompany(1);
		for (Team team : teamList) {
			teams.add(new SelectItem(team.getTeamId()+"", team.getName()));
		}
		return teams;
	}
	public void setTeams(LinkedList<SelectItem> teams) {
		this.teams = teams;
	}
	public List<EmployeeWrapper> getEmployees() {
		employees.clear();
		// need to set the company
		List <Employee> temp = new EmployeeController(Application.getInstance().getEntityManagerFactory()).getEmployeesByCompany(1);
		for (Employee employee : temp) {
			EmployeeWrapper er = new EmployeeWrapper();
			er.setEmployee(employee);
			employees.add(er);
		}
		return employees;
	}
	public void setEmployees(List<EmployeeWrapper> employees) {
		this.employees = employees;
	}
	public EmployeeWrapper getEmployeeToEdit() {
		return employeeToEdit;
	}
	public void setEmployeeToEdit(EmployeeWrapper employeeToEdit) {
		this.employeeToEdit = employeeToEdit;
	}
	public EmployeeWrapper getEmployeeToDelete() {
		return employeeToDelete;
	}
	public void setEmployeeToDelete(EmployeeWrapper employeeToDelete) {
		this.employeeToDelete = employeeToDelete;
	}
	public EmployeeWrapper getEmployeeToAdd() {
		return employeeToAdd;
	}
	public void setEmployeeToAdd(EmployeeWrapper employeeToAdd) {
		this.employeeToAdd = employeeToAdd;
	}
	public int getEmployeeIdToEdit() {
		return employeeIdToEdit;
	}
	public void setEmployeeIdToEdit(int employeeIdToEdit) {
		this.employeeIdToEdit = employeeIdToEdit;
	}
	public String getNewName() {
		return newName;
	}
	public void setNewName(String newName) {
		this.newName = newName;
	}
	public int getNewTeamId() {
		return newTeamId;
	}
	public void setNewTeamId(int newTeamId) {
		this.newTeamId = newTeamId;
	}
	public int getNewDaysOff() {
		return newDaysOff;
	}
	public void setNewDaysOff(int newDaysOff) {
		this.newDaysOff = newDaysOff;
	}
	public int getNewSpecialDaysOff() {
		return newSpecialDaysOff;
	}
	public void setNewSpecialDaysOff(int newSpecialDaysOff) {
		this.newSpecialDaysOff = newSpecialDaysOff;
	}
	public Date getNewJoinDate() {
		return newJoinDate;
	}
	public void setNewJoinDate(Date newJoinDate) {
		this.newJoinDate = newJoinDate;
	}
	public Date getNewEndDate() {
		return newEndDate;
	}
	public void setNewEndDate(Date newEndDate) {
		this.newEndDate = newEndDate;
	}
	public LinkedList<String> getNewAssociatedTasks() {
		return newAssociatedTasks;
	}
	public void setNewAssociatedTasks(LinkedList<String> newAssociatedTasks) {
		this.newAssociatedTasks = newAssociatedTasks;
	}
	
	
	@PostConstruct
    public void init() {
	 	employees = new LinkedList<EmployeeWrapper>();
	 	teams=new LinkedList<SelectItem>();
	 	tasks = new LinkedList<SelectItem>();
        System.out.println("Employees INIT...");
    }
	
	
	
	
	 
	 public void remove() {
		    try {
		    	System.out.println("Deleting: " + employeeToDelete.getEmployee().getName());
		        new EmployeeController(Application.getInstance().getEntityManagerFactory()).deleteEmployee(employeeToDelete.getEmployee());
		        
		        // need to remove from requests
		        // need to remove from daysOff
		        // need to remove from specialDaysOff
		        // need to remove from taskassociations
		        // need to remove from workedhours
		        
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
	 
	 public void edit(){
		 System.out.println("Editing: " + employeeToEdit.getEmployee().getName());
	 }
	 
	 public void save(){
		 System.out.println("Saving...");
		 new EmployeeController(Application.getInstance().getEntityManagerFactory()).updateEmployee(employeeIdToEdit, 
				 	newName, newDaysOff, newSpecialDaysOff, newTeamId, newJoinDate, newEndDate);
	 }
	 
	 public void add(){
		 System.out.println("Adding emp  with name: " + employeeToAdd.getEmployee().getName() + "and id of team: " + employeeToAdd.getEmployee().getTeamId());
		 
		 employeeToAdd.getEmployee().setCompanyId(1);
		 new EmployeeController(Application.getInstance().getEntityManagerFactory()).addEmployee(employeeToAdd.getEmployee());
	 }
	 
	 public void createEmployee(){
		 System.out.println("Creating employee...");
		 employeeToAdd = new EmployeeWrapper();
		 employeeToAdd.setEmployee(new Employee());
		 employeeToAdd.setTeamName("");
		 employeeToAdd.setTasks(new LinkedList<String>());
	 }
	
	
}
