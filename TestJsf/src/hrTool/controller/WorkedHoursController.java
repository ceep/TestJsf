package hrTool.controller;

import hrTool.model.Task;
import hrTool.model.Taskassociation;
import hrTool.model.Team;
import hrTool.model.Workedhour;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class WorkedHoursController {

	private EntityManagerFactory emf = null;


	public WorkedHoursController(EntityManagerFactory emf){
		this.emf=emf;
	}

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	

	// returns a list of task associations
	public List <Workedhour> getWorkedHoursByEmployee(int employeeId){

		List <Workedhour> result = new LinkedList<Workedhour>();

		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery cq = cb.createQuery();
		Root <Workedhour> rt = cq.from(Workedhour.class);
		cq.where(
				cb.equal(rt.get("employeeId"), employeeId)
				);
		Query q = em.createQuery(cq);

		result =  q.getResultList();

		return result;
	}

		// adds a new worked hour
		public void addWorkedHours(Workedhour workedHour){
			EntityManager t = getEntityManager();
			t.getTransaction().begin();
			t.persist(workedHour);
			t.getTransaction().commit();
		}
		
		
		// delete the worked hours for a certain employee
		public void deleteWorkedHoursForEmployee(int employeeId) {
			
			List <Workedhour> result = new LinkedList<Workedhour>();
			EntityManager em = getEntityManager();
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery cq = cb.createQuery();
			Root <Workedhour> rt = cq.from(Workedhour.class);
			cq.where(
					cb.equal(rt.get("employeeId"), employeeId)
					);
			Query q = em.createQuery(cq);

			result =  q.getResultList();
			
			for (Workedhour hours : result) {
				delete(hours);
			}
				  
		}
	
		// deletes the worked hours for a certain task
		public void deleteWorkedHours(int taskId) {
			
			List <Workedhour> result = new LinkedList<Workedhour>();
			EntityManager em = getEntityManager();
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery cq = cb.createQuery();
			Root <Workedhour> rt = cq.from(Workedhour.class);
			cq.where(
					cb.equal(rt.get("taskId"), taskId)
					);
			Query q = em.createQuery(cq);

			result =  q.getResultList();
			
			for (Workedhour hours : result) {
				delete(hours);
			}
				  
		}
		
		public void delete(Workedhour hours) {
			EntityManager t = getEntityManager();

			Workedhour task= t.find(Workedhour.class, hours.getId());

			t.getTransaction().begin();
			t.remove(task);
			t.getTransaction().commit();
		}

		

}
