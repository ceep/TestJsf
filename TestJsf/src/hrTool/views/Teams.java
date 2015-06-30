package hrTool.views;

import hrTool.application.Application;
import hrTool.controller.EmployeeController;
import hrTool.controller.TasksController;
import hrTool.controller.TeamController;
import hrTool.model.Team;
import hrTool.wrappers.TeamWrapper;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="teamsView")
@ViewScoped
public class Teams implements Serializable{

	private LinkedList <TeamWrapper> teams;
	private TeamWrapper teamToEdit;
	private TeamWrapper teamToDelete;
	private TeamWrapper teamToAdd;

	private int teamIdToEdit;
	private String newName;
	private Date newformedDate;
	
	


	public Date getNewformedDate() {
		return newformedDate;
	}

	public void setNewformedDate(Date newformedDate) {
		this.newformedDate = newformedDate;
	}

	public TeamWrapper getTeamToAdd() {
		return teamToAdd;
	}

	public void setTeamToAdd(TeamWrapper teamToAdd) {
		this.teamToAdd = teamToAdd;
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public int getTeamIdToEdit() {
		return teamIdToEdit;
	}

	public void setTeamIdToEdit(int teamIdToEdit) {
		this.teamIdToEdit = teamIdToEdit;
	}

	public TeamWrapper getTeamToDelete() {
		return teamToDelete;
	}

	public void setTeamToDelete(TeamWrapper teamToDelete) {
		this.teamToDelete = teamToDelete;
	}

	public TeamWrapper getTeamToEdit() {
		return teamToEdit;
	}

	public void setTeamToEdit(TeamWrapper teamToEdit) {
		this.teamToEdit = teamToEdit;
	}

	public LinkedList<TeamWrapper> getTeams() {

		teams.clear();
		// need to set the company
		List <Team> temp = new TeamController(Application.getInstance().getEntityManagerFactory()).getTeamsByCompany(1);
		for (Team team : temp) {
			TeamWrapper tr = new TeamWrapper();
			tr.setTeam(team);
			teams.add(tr);
		}
		return teams;
	}

	public void setTeams(LinkedList<TeamWrapper> teams) {
		this.teams = teams;
	}

	@PostConstruct
	public void init() {
		teams = new LinkedList<TeamWrapper>();
		//System.out.println("Teams INIT...");
	}

	public void remove() {
		try {
			//System.out.println("Deleting: " + teamToDelete.getTeam().getName());
			new TeamController(Application.getInstance().getEntityManagerFactory()).deleteTeam(teamToDelete.getTeam());
			
			// need to delete from other tables the reference to that team
			// employee
			new EmployeeController(Application.getInstance().getEntityManagerFactory()).setTeamIdForEmployeesInTeam(teamToDelete.getTeam().getTeamId());
			// task
			new TasksController(Application.getInstance().getEntityManagerFactory()).setTeamIdForTask(teamToDelete.getTeam().getTeamId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void edit(){
		//System.out.println("Editing: " + teamToEdit.getTeam().getName());
	}

	public void save(){

		//System.out.println("Saving...");

		new TeamController(Application.getInstance().getEntityManagerFactory()).updateTeam(teamIdToEdit, newName, newformedDate);


	}

	public void add(){
		//System.out.println("Adding team with name: " + teamToAdd.getTeam().getName());
		//need to set company Id
		teamToAdd.getTeam().setCompanyId(1);
		
		new TeamController(Application.getInstance().getEntityManagerFactory()).addTeam(teamToAdd.getTeam());
		
	}

	public void createTeam(){
		//System.out.println("Creating team...");
		teamToAdd = new TeamWrapper();
		teamToAdd.setTeam(new Team());
	}


}
