package hrTool.views;

import hrTool.beans.DashBoardAggregator;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="dashboardView")
@ViewScoped
public class Dashboard implements Serializable{

	private LinkedList <DashBoardAggregator> dashboards;
	
	

	
	
	 public LinkedList<DashBoardAggregator> getDashboards() {
		return dashboards;
	}





	public void setDashboards(LinkedList<DashBoardAggregator> dashboards) {
		this.dashboards = dashboards;
	}





	@PostConstruct
	    public void init() {
		 	dashboards = new LinkedList<DashBoardAggregator>();
	        System.out.println("Dashboard INIT...");
	        
	        createDashboardAggregators(4, 20);
	    }





	private void createDashboardAggregators(int number, int number2) {
		for(int i=0; i<number;i++){
			DashBoardAggregator dba = new DashBoardAggregator();
			dba.setName("Name"+i);
			dba.setNumberOfEmployees(i+1);
			dba.setNumberOfEmployeesOnVacation(i);
			LinkedList<String> tasks = new LinkedList<String>();
			for(int j=0; j<number2; j++)
				tasks.add("T"+i+j);
			dba.setTasks(tasks);
			dashboards.add(dba);
		}
		
	}
	
	
	
	
	
}
