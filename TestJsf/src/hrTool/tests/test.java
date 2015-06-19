package hrTool.tests;
import hrTool.controller.EmployeeController;
import hrTool.controller.TasksController;
import hrTool.model.Employee;

import java.util.Date;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;


public class test {

	
	private static EntityManagerFactory entityManager;
	
	static {
		entityManager = Persistence.createEntityManagerFactory("TestJsf");
	}
	
	
	
	
	@Test
	public void gebtc(){
		new EmployeeController(entityManager).getEmployeesByTeamCount(1, 1);
	}
	
	
	
	
	@Test
	public void gebt(){
		System.out.println("Employees by team: " + new EmployeeController(entityManager).getEmployeesByTeam(1, 1).get(0).getName()); 
	}
	
	@Test
	public void gebc(){
		System.out.println("Employees by company: " + new EmployeeController(entityManager).getEmployeesByCompany(1).size()); 
	}
	
	
	@Test
	public void deleteEmployee(){
		
		Employee emp = new Employee();
		emp.setCompanyId(1);
		emp.setDaysOff(2);
		emp.setJoinDate(new Date());
		emp.setEndDate(new Date());
		emp.setName("Banel");
		emp.setSpecialDaysOff(5);
		emp.setTeamId(3);
		
		new EmployeeController(entityManager).addEmployee(emp);
		new EmployeeController(entityManager).deleteEmployee(emp); 
		
		System.out.println("Deleted!");
	}

	@Test
	public void gtbc(){
		System.out.println("Tasks by company: " + new TasksController(entityManager).getTasksByCompany(1).size()); 
	}
	
	
}
