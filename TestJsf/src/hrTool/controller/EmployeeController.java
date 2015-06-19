package hrTool.controller;

import hrTool.model.Employee;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class EmployeeController {

	private EntityManagerFactory emf = null;


	public EmployeeController(EntityManagerFactory emf){
		this.emf=emf;
	}

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	// adds an employee to the table
	public void addEmployee(Employee emp){
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		em.persist(emp);
		em.getTransaction().commit();
	}

	// counts the employees from a company inside a certain team
	public int getEmployeesByTeamCount(int companyId, int teamId){

		int result;

		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery cq = cb.createQuery();
		Root <Employee> rt = cq.from(Employee.class);
		cq.select(cb.count(rt));
		cq.where(
				cb.equal(rt.get("companyId"), companyId),
				cb.equal(rt.get("teamId"), teamId)
				);
		Query q = em.createQuery(cq);

		result = Integer.parseInt(q.getSingleResult().toString());

		return result;
	}

	// counts the employees from a company 
	public int getEmployeesByCompanyCount(int companyId){

		int result;

		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery cq = cb.createQuery();
		Root <Employee> rt = cq.from(Employee.class);
		cq.select(cb.count(rt));
		cq.where(
				cb.equal(rt.get("companyId"), companyId)
				);
		Query q = em.createQuery(cq);

		result = Integer.parseInt(q.getSingleResult().toString());

		return result;
	}

	// returns a list of employees inside a company that belong to a certain team
	public List <Employee> getEmployeesByTeam(int companyId, int teamId){

		List <Employee> result = new LinkedList<Employee>();

		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery cq = cb.createQuery();
		Root <Employee> rt = cq.from(Employee.class);
		cq.where(
				cb.equal(rt.get("companyId"), companyId),
				cb.equal(rt.get("teamId"), teamId)
				);
		Query q = em.createQuery(cq);

		result =  q.getResultList();

		return result;
	}

	// returns a list of employees inside a company
	public List <Employee> getEmployeesByCompany(int companyId){

		List <Employee> result = new LinkedList<Employee>();

		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery cq = cb.createQuery();
		Root <Employee> rt = cq.from(Employee.class);
		cq.where(
				cb.equal(rt.get("companyId"), companyId)
				);
		Query q = em.createQuery(cq);

		result =  q.getResultList();

		return result;
	}

	// deletes an employee
	public void deleteEmployee(Employee emp) {
		EntityManager em = getEntityManager();

		Employee employee = em.find(Employee.class, emp.getEmployeeId());

		em.getTransaction().begin();
		em.remove(employee);
		em.getTransaction().commit();
	}

	// updates an employee if we know the employee ID
	public void updateEmployee(int employeeId, String name, int daysOff, int specialDaysOff, int teamId, Date joinDate, Date endDate ){

		EntityManager em = getEntityManager();
		Employee employee = em.find(Employee.class, employeeId);

		em.getTransaction().begin();

		employee.setDaysOff(daysOff);
		employee.setName(name);
		employee.setSpecialDaysOff(specialDaysOff);
		employee.setTeamId(teamId);
		employee.setJoinDate(joinDate);
		employee.setEndDate(endDate);

		em.getTransaction().commit();
	}

}
