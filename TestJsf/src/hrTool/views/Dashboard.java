package hrTool.views;

import hrTool.application.Application;
import hrTool.beans.DashBoardAggregator;
import hrTool.controller.DaysOffController;
import hrTool.controller.EmployeeController;
import hrTool.controller.SpecialDaysOffController;
import hrTool.controller.TasksController;
import hrTool.controller.TeamController;
import hrTool.controller.WorkedHoursController;
import hrTool.model.Daysoff;
import hrTool.model.Employee;
import hrTool.model.Specialdaysoff;
import hrTool.model.Task;
import hrTool.model.Team;
import hrTool.model.Workedhour;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

@ManagedBean(name="dashboardView")
@ViewScoped
public class Dashboard implements Serializable{

	private LinkedList <DashBoardAggregator> dashboards;
	
	
	 public LinkedList<DashBoardAggregator> getDashboards() {
		 
		 dashboards.clear();
		 
		// need to set the company
		List <Team> teamsList = new TeamController(Application.getInstance().getEntityManagerFactory()).getTeamsByCompany(1);
		
		int numberOfEmployeesOnVacation=0;
		
		Date todayDate = new Date();
		todayDate.setHours(0);
		todayDate.setMinutes(0);
		todayDate.setSeconds(0);
		
		
		Calendar tomorrow = Calendar.getInstance();
		tomorrow.setTime(new Date());
		tomorrow.add(Calendar.DATE, 1);
		
		Date tomorrowDate = tomorrow.getTime();
		
		for (Team team : teamsList) {
			
			numberOfEmployeesOnVacation=0;
			
			DashBoardAggregator da = new DashBoardAggregator();
			da.setName(team.getName());
			
			// creating the list of remaining tasks
			LinkedList <String> taskCodes = new LinkedList<String>();
			
			// get the employees in a team
			List <Employee> employeeList = new EmployeeController(Application.getInstance().getEntityManagerFactory()).getEmployeesByTeam(team.getCompanyId(), team.getTeamId());
			
			Hashtable taskIds = new Hashtable();
			
			// get all the tasks for a certain team
			List <Task> tasksList = new TasksController(Application.getInstance().getEntityManagerFactory()).getTasksByTeam(team.getTeamId());
			
			// store the task ids and total hours per task
			for (Task task : tasksList) {
				taskIds.put(task.getTaskId(), task.getTotalHours());
			}
			
			// iterate through all the employees inside the team
			for (Employee employee : employeeList) {
				// get all the worked hours for a certain employee
				List <Workedhour> workedHoursList = new WorkedHoursController(Application.getInstance().getEntityManagerFactory()).getWorkedHoursByEmployee(employee.getEmployeeId());
				
				// if the list of tasks contains the tasks from the workedhours table, we decrease the total hours per task
				for (Workedhour workedhour : workedHoursList) {
					if(taskIds.containsKey(workedhour.getTaskId())){
						taskIds.put(workedhour.getTaskId(), ((Integer) taskIds.get(workedhour.getTaskId()))-workedhour.getHours());
					}
				}
			
			}
			
			// iterate through all the tasks
			Enumeration<Integer> enumKey = taskIds.keys();
			while(enumKey.hasMoreElements()) {
			    Integer key = enumKey.nextElement();
			    int val = (Integer) taskIds.get(key);
			    // if the number of hours per task is higher than 0, we add the task code to the remaining tasks list
			    if(val>0){
			    	taskCodes.add(new TasksController(Application.getInstance().getEntityManagerFactory()).getTaskCode(key));
			    }
			}
			
			// adding the remaining tasks
			da.setTasks(taskCodes);
			
			// we iterate through all the employees and check if they are on vacation
			for (Employee employee : employeeList) {
				// look for days off
				List <Daysoff> daysOff = new DaysOffController(Application.getInstance().getEntityManagerFactory()).getDaysOffByEmployee(employee.getEmployeeId());		
				for (Daysoff dayOff : daysOff) {
					if(dayOff.getDay().after(todayDate) && dayOff.getDay().before(tomorrowDate))
						numberOfEmployeesOnVacation++;
				}
				
				// look for special days off
				List <Specialdaysoff> specialDaysOff = new SpecialDaysOffController(Application.getInstance().getEntityManagerFactory()).getDaysOffByEmployee(employee.getEmployeeId());		
				for (Specialdaysoff dayOff : specialDaysOff) {
					if(dayOff.getDay().after(todayDate) && dayOff.getDay().before(tomorrowDate))
						numberOfEmployeesOnVacation++;
				}
				
			}
						
			// adding the employees on vacation
			da.setNumberOfEmployeesOnVacation(numberOfEmployeesOnVacation);
			
			dashboards.add(da);
		}
		 
		return dashboards;
	}





	public void setDashboards(LinkedList<DashBoardAggregator> dashboards) {
		this.dashboards = dashboards;
	}





	@PostConstruct
	    public void init() {
		 	dashboards = new LinkedList<DashBoardAggregator>();
	        System.out.println("Dashboard INIT...");
	    }

	
}
