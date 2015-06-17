package hrTool.views;

import hrTool.beans.Employee;
import hrTool.beans.Task;
import hrTool.beans.Team;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

@ManagedBean(name="employeesView")
@ViewScoped
public class Employees implements Serializable{

	private LinkedList <Employee> employees;
	private Employee employeeToEdit;
	private Employee employeeToDelete;
	private Employee employeeToAdd;
	
	private int employeeIdToEdit;
	
	private String newName;
	private String newTeam;
	private int newDaysOff;
	private int newSpecialDaysOff;
	private Date newJoinDate;
	private Date newEndDate;
	private LinkedList <String> newAssociatedTasks;
	
	private LinkedList <SelectItem> teams;
	private LinkedList <SelectItem> tasks;
	

	public LinkedList<SelectItem> getTasks() {
		return tasks;
	}
	public void setTasks(LinkedList<SelectItem> tasks) {
		this.tasks = tasks;
	}
	public LinkedList<SelectItem> getTeams() {
		return teams;
	}
	public void setTeams(LinkedList<SelectItem> teams) {
		this.teams = teams;
	}
	public LinkedList<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(LinkedList<Employee> employees) {
		this.employees = employees;
	}
	public Employee getEmployeeToEdit() {
		return employeeToEdit;
	}
	public void setEmployeeToEdit(Employee employeeToEdit) {
		this.employeeToEdit = employeeToEdit;
	}
	public Employee getEmployeeToDelete() {
		return employeeToDelete;
	}
	public void setEmployeeToDelete(Employee employeeToDelete) {
		this.employeeToDelete = employeeToDelete;
	}
	public Employee getEmployeeToAdd() {
		return employeeToAdd;
	}
	public void setEmployeeToAdd(Employee employeeToAdd) {
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
	public String getNewTeam() {
		return newTeam;
	}
	public void setNewTeam(String newTeam) {
		this.newTeam = newTeam;
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
	 	employees = new LinkedList<Employee>();
	 	teams=new LinkedList<SelectItem>();
	 	tasks = new LinkedList<SelectItem>();
        createEmployees(3);
        createTeams(3);
        createTasks(30);
        System.out.println("Employees INIT...");
    }
	
	 private void createTeams(int number){
		 for (int i=0; i<number; i++){
			 Team team = new Team();
			 team.setName("A"+i);
			 team.setNumberOfEmployees(i+1);
			 team.setNumberOfEmployeesOnVacation(i);
			 team.setFormedDate(new Date());
			 team.setId(i);
			 team.setCompanyId(i+100);
			 teams.add(new SelectItem(team.getName(), team.getName()));
		 }
		 
	 }

	private void createEmployees(int number){
		 for (int i=0; i<number; i++){
			 Employee emp = new Employee();
			 
			 emp.setCompanyId(i+100);
			 emp.setId(i);
			 emp.setName("A"+i);
			 emp.setAssociatedTasks(new LinkedList<String>());
			 emp.setDaysOff(5+i);
			 emp.setEndDate(new Date());
			 emp.setJoinDate(new Date());
			 emp.setSpecialDaysOff(6);
			 emp.setTeam("B"+i);
			 emp.setPendingRequests(new LinkedList<String>());
			 
			 
			 employees.add(emp);
		 }
		 
	 }
	
	private void createTasks(int number){
		 for (int i=0; i<number; i++){
			 Task task = new Task();
			 
			 task.setCompanyId(i+100);
			 task.setId(i);
			 task.setCode("T"+i);
			 task.setName("Task"+i);
			 task.setDescription("Description"+i);
			 task.setStartDate(new Date());
			 task.setEndDate(new Date());
			 
			 
			 tasks.add(new SelectItem(task.getCode(), task.getCode()));
		 }
		 
	 }
	
	
	 
	 public void remove() {
		    try {
		    	System.out.println("Deleting: " + employeeToDelete.getName());
		        employees.remove(employeeToDelete);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
	 
	 public void edit(){
		 System.out.println("Editing: " + employeeToEdit.getName());
	 }
	 
	 public void save(){
		
		 System.out.println("Saving...");
		 
		 for (Employee emp : employees) {
			if(emp.getId()==employeeToEdit.getId()){
				emp.setName(newName);
				emp.setTeam(newTeam);
				emp.setDaysOff(newDaysOff);
				emp.setSpecialDaysOff(newSpecialDaysOff);
				emp.setJoinDate(newJoinDate);
				emp.setEndDate(newEndDate);
				emp.setAssociatedTasks(newAssociatedTasks);
				break;
			}
		}
		 
	 }
	 
	 public void add(){
		 System.out.println("Adding team with name: " + employeeToAdd.getName());
		 for (Employee emp : employees) {
			if(emp.getName().equals(employeeToAdd.getName())){
				return;
			}
		}
		employees.add(employeeToAdd);
	 }
	 
	 public void createEmployee(){
		 System.out.println("Creating employee...");
		 employeeToAdd = new Employee();
	 }
	
	
}
