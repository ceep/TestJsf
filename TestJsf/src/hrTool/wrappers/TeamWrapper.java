package hrTool.wrappers;

import hrTool.application.Application;
import hrTool.controller.EmployeeController;
import hrTool.controller.RequestsController;
import hrTool.controller.TasksAssociationController;
import hrTool.controller.TasksController;
import hrTool.controller.TeamController;
import hrTool.model.Employee;
import hrTool.model.Task;
import hrTool.model.Taskassociation;
import hrTool.model.Team;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class TeamWrapper implements Serializable{

	private Team team;
	private int numberOfEmployees;
	
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	public int getNumberOfEmployees() {
		numberOfEmployees = new EmployeeController(Application.getInstance().getEntityManagerFactory()).getEmployeesByTeam(team.getCompanyId(), team.getTeamId()).size();
		return numberOfEmployees;
	}
	public void setNumberOfEmployees(int numberOfEmployees) {
		this.numberOfEmployees = numberOfEmployees;
	}
	
	
}
