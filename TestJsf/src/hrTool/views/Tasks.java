package hrTool.views;

import hrTool.beans.Task;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="tasksView")
@ViewScoped
public class Tasks implements Serializable{

	private LinkedList <Task> tasks;
	private Task taskToEdit;
	private Task taskToAdd;
	private Task taskToDelete;
	
	private int taskIdToEdit;
	private String newName;
	private Date newStartDate;
	private Date newEndDate;
	private String newCode;
	private String newDescription;
	
	
	
	
	public LinkedList<Task> getTasks() {
		return tasks;
	}




	public void setTasks(LinkedList<Task> tasks) {
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
        createTasks(5);
        System.out.println("Tasks INIT...");
    }
	
	
	private void createTasks(int number){
		 for (int i=0; i<number; i++){
			 Task task = new Task();
			 task.setCode("C"+i);
			 task.setName("A"+i);
			 task.setDescription("Desc"+i);
			 task.setEndDate(new Date());
			 task.setStartDate(new Date());
			 task.setId(i);
			 task.setCompanyId(i+100);
			 tasks.add(task);
		 }
		 
	 }
	
	public void remove() {
	    try {
	    	System.out.println("Deleting: " + taskToDelete.getName());
	        tasks.remove(taskToDelete);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
 
	 public void edit(){
		 System.out.println("Editing: " + taskToEdit.getName());
	 }
	 
	 public void save(){
		
		 System.out.println("Saving...");
		 
		 for (Task task : tasks) {
			if(task.getId()==taskIdToEdit){
				task.setName(newName);
				task.setCode(newCode);
				task.setDescription(newDescription);
				task.setStartDate(newStartDate);
				task.setEndDate(newEndDate);
				break;
			}
		}
		 
	 }
	 
	 public void add(){
		 System.out.println("Adding task with name: " + taskToAdd.getName());
		 for (Task task : tasks) {
			if(task.getName().equals(taskToAdd.getName())){
				FacesContext context = FacesContext.getCurrentInstance();
		        context.addMessage(null, new FacesMessage("Error",  "Name already exists") );
				return;
			}
		}
		tasks.add(taskToAdd);
	 }
	 
	 public void createTask(){
		 System.out.println("Creating task...");
		 taskToAdd = new Task();
	 }
 
	
}
