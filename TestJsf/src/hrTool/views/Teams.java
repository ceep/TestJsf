package hrTool.views;

import hrTool.beans.Team;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="teamsView")
@ViewScoped
public class Teams implements Serializable{

	private LinkedList <Team> teams;
	private Team teamToEdit;
	private Team teamToDelete;
	private Team teamToAdd;
	
	private int teamIdToEdit;
	private String newName;


	public Team getTeamToAdd() {
		return teamToAdd;
	}

	public void setTeamToAdd(Team teamToAdd) {
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

	public Team getTeamToDelete() {
		return teamToDelete;
	}

	public void setTeamToDelete(Team teamToDelete) {
		this.teamToDelete = teamToDelete;
	}

	public Team getTeamToEdit() {
		return teamToEdit;
	}

	public void setTeamToEdit(Team teamToEdit) {
		this.teamToEdit = teamToEdit;
	}

	public LinkedList<Team> getTeams() {
		System.out.println("Getting teams...");
		return teams;
	}

	public void setTeams(LinkedList<Team> teams) {
		this.teams = teams;
	}
	
	 @PostConstruct
	    public void init() {
		 	teams = new LinkedList<Team>();
	        createTeams(5);
	        System.out.println("Teams INIT...");
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
			 teams.add(team);
		 }
		 
	 }
	 
	 public void remove() {
		    try {
		    	System.out.println("Deleting: " + teamToDelete.getName());
		        teams.remove(teamToDelete);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
	 
	 public void edit(){
		 System.out.println("Editing: " + teamToEdit.getName());
	 }
	 
	 public void save(){
		
		 System.out.println("Saving...");
		 
		 for (Team team : teams) {
			if(team.getId()==teamIdToEdit){
				team.setName(newName);
				break;
			}
		}
		 
	 }
	 
	 public void add(){
		 System.out.println("Adding team with name: " + teamToAdd.getName());
		 for (Team team : teams) {
			if(team.getName().equals(teamToAdd.getName())){
				FacesContext context = FacesContext.getCurrentInstance();
		        context.addMessage(null, new FacesMessage("Error",  "Name already exists") );
				return;
			}
		}
		teams.add(teamToAdd);
	 }
	 
	 public void createTeam(){
		 System.out.println("Creating team...");
		 teamToAdd = new Team();
	 }
	 
	
}
