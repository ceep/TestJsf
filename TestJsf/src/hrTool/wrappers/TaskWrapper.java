package hrTool.wrappers;

import hrTool.model.Task;

import java.io.Serializable;

public class TaskWrapper implements Serializable{

	private Task task;
	private String teamName;
	
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	
	
	
}
