package hrTool.views;

import hrTool.application.Application;
import hrTool.controller.TasksAssociationController;
import hrTool.controller.TasksController;
import hrTool.controller.TeamController;
import hrTool.controller.WorkedHoursController;
import hrTool.model.Task;
import hrTool.model.Taskassociation;
import hrTool.model.Team;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

@ManagedBean(name="tasksView")
@ViewScoped
public class Tasks implements Serializable{

	private List <Task> tasks;
	private Task taskToEdit;
	private Task taskToAdd;
	private Task taskToDelete;
	
	private int taskIdToEdit;
	private String newName;
	private Date newStartDate;
	private Date newEndDate;
	private String newCode;
	private String newDescription;
	private int newTotalHours;
	private String newTeam;

	private LinkedList <SelectItem> teams;
	
	
	public String getNewTeam() {
		return newTeam;
	}




	public void setNewTeam(String newTeam) {
		this.newTeam = newTeam;
	}

	
	
	
	public LinkedList<SelectItem> getTeams() {
		teams.clear();
		
		// need to set the company
		List <Team> teamList =new TeamController(Application.getInstance().getEntityManagerFactory()).getTeamsByCompany(1);
		for (Team team : teamList) {
			teams.add(new SelectItem(team.getTeamId()+"", team.getName()));
		}
		
		return teams;
	}




	public void setTeams(LinkedList<SelectItem> teams) {
		this.teams = teams;
	}




	public int getNewTotalHours() {
		return newTotalHours;
	}




	public void setNewTotalHours(int newTotalHours) {
		this.newTotalHours = newTotalHours;
	}




	public List<Task> getTasks() {
		// need to set the company
		tasks=new TasksController(Application.getInstance().getEntityManagerFactory()).getTasksByCompany(1);
		
		return tasks;
	}




	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	




	public Task getTaskToEdit() {
		return taskToEdit;
	}




	public void setTaskToEdit(Task taskToEdit) {
		this.taskToEdit = taskToEdit;
	}




	public Task getTaskToAdd() {
		return taskToAdd;
	}




	public void setTaskToAdd(Task taskToAdd) {
		this.taskToAdd = taskToAdd;
	}




	public Task getTaskToDelete() {
		return taskToDelete;
	}




	public void setTaskToDelete(Task taskToDelete) {
		this.taskToDelete = taskToDelete;
	}




	public int getTaskIdToEdit() {
		return taskIdToEdit;
	}




	public void setTaskIdToEdit(int taskIdToEdit) {
		this.taskIdToEdit = taskIdToEdit;
	}




	public String getNewName() {
		return newName;
	}




	public void setNewName(String newName) {
		this.newName = newName;
	}




	public Date getNewStartDate() {
		return newStartDate;
	}




	public void setNewStartDate(Date newStartDate) {
		this.newStartDate = newStartDate;
	}




	public Date getNewEndDate() {
		return newEndDate;
	}




	public void setNewEndDate(Date newEndDate) {
		this.newEndDate = newEndDate;
	}




	public String getNewCode() {
		return newCode;
	}




	public void setNewCode(String newCode) {
		this.newCode = newCode;
	}




	public String getNewDescription() {
		return newDescription;
	}




	public void setNewDescription(String newDescription) {
		this.newDescription = newDescription;
	}




	@PostConstruct
    public void init() {
	 	tasks = new LinkedList<Task>();
	 	teams=new LinkedList<SelectItem>();
        System.out.println("Tasks INIT...");
    }
	
	
	
	public void remove() {
	    try {
	    	System.out.println("Deleting: " + taskToDelete.getName() );
	    	// delete from tasks table
	        new TasksController(Application.getInstance().getEntityManagerFactory()).deleteTask(taskToDelete);
	        // delete from tasks associations table
	        new TasksAssociationController(Application.getInstance().getEntityManagerFactory()).deleteTaskAssociations(taskToDelete.getTaskId());
	        // delete from worked hours table
	        new WorkedHoursController(Application.getInstance().getEntityManagerFactory()).deleteWorkedHours(taskToDelete.getTaskId());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
 
	 public void edit(){
		 System.out.println("Editing: " + taskToEdit.getName());
	 }
	 
	 public void save(){
		
		 System.out.println("Saving...");
		 new TasksController(Application.getInstance().getEntityManagerFactory()).updateTask(taskIdToEdit, newCode, newDescription, newName, newTotalHours, newStartDate, newEndDate);
		 
		 
	 }
	 
	 public void add(){
		System.out.println("Adding task with name: " + taskToAdd.getName()+ " and value = " + taskToAdd.getTeamId());
		
		// need to set the company 
		taskToAdd.setCompanyId(1);
		
		new TasksController(Application.getInstance().getEntityManagerFactory()).addTask(taskToAdd);
	 }
	 
	 public void createTask(){
		 System.out.println("Creating task...");
		 taskToAdd = new Task();
	 }
 
	
}
