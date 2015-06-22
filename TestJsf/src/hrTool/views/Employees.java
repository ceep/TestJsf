package hrTool.views;

import hrTool.application.Application;
import hrTool.controller.DaysOffController;
import hrTool.controller.EmployeeController;
import hrTool.controller.RequestsController;
import hrTool.controller.SpecialDaysOffController;
import hrTool.controller.TasksAssociationController;
import hrTool.controller.TasksController;
import hrTool.controller.TeamController;
import hrTool.controller.WorkedHoursController;
import hrTool.model.Employee;
import hrTool.model.Request;
import hrTool.model.Task;
import hrTool.model.Taskassociation;
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
	private List <String> newTaskCodes;

	private LinkedList <SelectItem> teams;
	private LinkedList <SelectItem> tasks;

	
	

	public List<String> getNewTaskCodes() {
		return newTaskCodes;
	}
	public void setNewTaskCodes(List<String> newTaskCodes) {
		this.newTaskCodes = newTaskCodes;
	}
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
			new RequestsController(Application.getInstance().getEntityManagerFactory()).deleteRequestsByEmployee(employeeToDelete.getEmployee().getEmployeeId());
			// need to remove from daysOff
			new DaysOffController(Application.getInstance().getEntityManagerFactory()).deleteDaysOffForEmployee(employeeToDelete.getEmployee().getEmployeeId());
			// need to remove from specialDaysOff
			new SpecialDaysOffController(Application.getInstance().getEntityManagerFactory()).deleteDaysOffForEmployee(employeeToDelete.getEmployee().getEmployeeId());
			// need to remove from taskassociations
			for (Taskassociation ta : new TasksAssociationController(Application.getInstance().getEntityManagerFactory()).getTasksByEmployee(employeeToDelete.getEmployee().getEmployeeId())) {
				new TasksAssociationController(Application.getInstance().getEntityManagerFactory()).deleteTaskAssoc(ta);
			}
			// need to remove from workedhours
			new WorkedHoursController(Application.getInstance().getEntityManagerFactory()).deleteWorkedHoursForEmployee(employeeToDelete.getEmployee().getEmployeeId());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void edit(){
		System.out.println("Editing: " + employeeToEdit.getEmployee().getName());
		newTaskCodes = new LinkedList<String>();
		employeeToEdit.setNewTaskCodes(new LinkedList<String>());
	}

	public void save(){
		System.out.println("Saving...");
		new EmployeeController(Application.getInstance().getEntityManagerFactory()).updateEmployee(employeeIdToEdit, 
				newName, newDaysOff, newSpecialDaysOff, newTeamId, newJoinDate, newEndDate);
		
		// need to update tasksAssociations by deleting existing task associations and adding the correct ones
		for (Taskassociation ta : new TasksAssociationController(Application.getInstance().getEntityManagerFactory()).getTasksByEmployee(employeeToEdit.getEmployee().getEmployeeId())) {
			new TasksAssociationController(Application.getInstance().getEntityManagerFactory()).deleteTaskAssoc(ta);
		}

		for (String code : newTaskCodes) {
			Taskassociation ta = new Taskassociation();
			ta.setCompanyId(employeeToEdit.getEmployee().getCompanyId());
			ta.setEmployeeId(employeeToEdit.getEmployee().getEmployeeId());
			ta.setTaskId(Integer.parseInt(code));
			new TasksAssociationController(Application.getInstance().getEntityManagerFactory()).addTaskAssociation(ta);
		}

	}

	public void add(){
		System.out.println("Adding emp  with name: " + employeeToAdd.getEmployee().getName() + "and id of team: " + employeeToAdd.getEmployee().getTeamId());
		// need to set company id
		employeeToAdd.getEmployee().setCompanyId(1);
		new EmployeeController(Application.getInstance().getEntityManagerFactory()).addEmployee(employeeToAdd.getEmployee());

		List <String>	taskCodes = employeeToAdd.getTaskCodes(); 

		for (String code : taskCodes) {
			Taskassociation ta = new Taskassociation();
			ta.setCompanyId(employeeToAdd.getEmployee().getCompanyId());
			ta.setEmployeeId(employeeToAdd.getEmployee().getEmployeeId());
			ta.setTaskId(Integer.parseInt(code));
			new TasksAssociationController(Application.getInstance().getEntityManagerFactory()).addTaskAssociation(ta);
		}

	}

	public void createEmployee(){
		System.out.println("Creating employee...");
		employeeToAdd = new EmployeeWrapper();
		employeeToAdd.setEmployee(new Employee());
		employeeToAdd.setTeamName("");
		employeeToAdd.setTasks(new LinkedList<String>());
		employeeToAdd.setTaskCodes(new LinkedList<String>());
	}


}
