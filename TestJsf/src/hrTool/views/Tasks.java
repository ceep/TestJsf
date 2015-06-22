package hrTool.views;

import hrTool.application.Application;
import hrTool.controller.TasksAssociationController;
import hrTool.controller.TasksController;
import hrTool.controller.TeamController;
import hrTool.controller.WorkedHoursController;
import hrTool.model.Task;
import hrTool.model.Team;
import hrTool.wrappers.TaskWrapper;

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

	private List <TaskWrapper> tasks;
	private TaskWrapper taskToEdit;
	private TaskWrapper taskToAdd;
	private TaskWrapper taskToDelete;

	private int taskIdToEdit;
	private String newName;
	private Date newStartDate;
	private Date newEndDate;
	private String newCode;
	private String newDescription;
	private int newTotalHours;
	private String newTeamId;

	private LinkedList <SelectItem> teams;





	public String getNewTeamId() {
		return newTeamId;
	}




	public void setNewTeamId(String newTeamId) {
		this.newTeamId = newTeamId;
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




	public List<TaskWrapper> getTasks() {

		tasks.clear();

		// need to set the company
		List <Task> tasksList = new TasksController(Application.getInstance().getEntityManagerFactory()).getTasksByCompany(1);

		for (Task task : tasksList) {
			TaskWrapper tr = new TaskWrapper();
			tr.setTask(task);
			if(task.getTeamId()!=-1)
				tr.setTeamName(new TeamController(Application.getInstance().getEntityManagerFactory()).getTeam(task.getTeamId()).getName());
			else
				tr.setTeamName("None");
			tasks.add(tr);
		}


		return tasks;
	}




	public void setTasks(List<TaskWrapper> tasks) {
		this.tasks = tasks;
	}





	public TaskWrapper getTaskToEdit() {
		return taskToEdit;
	}




	public void setTaskToEdit(TaskWrapper taskToEdit) {
		this.taskToEdit = taskToEdit;
	}




	public TaskWrapper getTaskToAdd() {
		return taskToAdd;
	}




	public void setTaskToAdd(TaskWrapper taskToAdd) {
		this.taskToAdd = taskToAdd;
	}




	public TaskWrapper getTaskToDelete() {
		return taskToDelete;
	}




	public void setTaskToDelete(TaskWrapper taskToDelete) {
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
		tasks = new LinkedList<TaskWrapper>();
		teams=new LinkedList<SelectItem>();
		System.out.println("Tasks INIT...");
	}



	public void remove() {
		try {
			System.out.println("Deleting: " + taskToDelete.getTask().getName() );
			// delete from tasks table
			new TasksController(Application.getInstance().getEntityManagerFactory()).deleteTask(taskToDelete.getTask());
			// delete from tasks associations table
			new TasksAssociationController(Application.getInstance().getEntityManagerFactory()).deleteTaskAssociations(taskToDelete.getTask().getTaskId());
			// delete from worked hours table
			new WorkedHoursController(Application.getInstance().getEntityManagerFactory()).deleteWorkedHours(taskToDelete.getTask().getTaskId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void edit(){
		System.out.println("Editing: " + taskToEdit.getTask().getName());
	}

	public void save(){

		System.out.println("Saving...");
		new TasksController(Application.getInstance().getEntityManagerFactory()).updateTask(taskIdToEdit, newCode, newDescription, newName, newTotalHours, newStartDate, newEndDate, Integer.parseInt(newTeamId));


	}

	public void add(){
		System.out.println("Adding task with name: " + taskToAdd.getTask().getName()+ " and value = " + taskToAdd.getTask().getTeamId());

		// need to set the company 
		taskToAdd.getTask().setCompanyId(1);

		new TasksController(Application.getInstance().getEntityManagerFactory()).addTask(taskToAdd.getTask());
	}

	public void createTask(){
		System.out.println("Creating task...");
		taskToAdd = new TaskWrapper();
		taskToAdd.setTask(new Task());
		taskToAdd.setTeamName(new String());
	}


}
