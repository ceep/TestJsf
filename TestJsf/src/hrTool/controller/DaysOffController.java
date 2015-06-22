package hrTool.controller;

import hrTool.model.Daysoff;
import hrTool.model.Workedhour;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class DaysOffController {

	private EntityManagerFactory emf = null;


	public DaysOffController(EntityManagerFactory emf){
		this.emf=emf;
	}

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	

	// returns a list of daysOff
	public List <Daysoff> getDaysOffByEmployee(int employeeId){

		List <Daysoff> result = new LinkedList<Daysoff>();

		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery cq = cb.createQuery();
		Root <Daysoff> rt = cq.from(Daysoff.class);
		cq.where(
				cb.equal(rt.get("employeeId"), employeeId)
				);
		Query q = em.createQuery(cq);

		result =  q.getResultList();

		return result;
	}

		// adds a new day off
		public void addDayOff(Daysoff dayOff){
			EntityManager t = getEntityManager();
			t.getTransaction().begin();
			t.persist(dayOff);
			t.getTransaction().commit();
		}
		
		
		// delete the days off for a certain employee
		public void deleteDaysOffForEmployee(int employeeId) {
			
			List <Daysoff> result = new LinkedList<Daysoff>();
			EntityManager em = getEntityManager();
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery cq = cb.createQuery();
			Root <Daysoff> rt = cq.from(Daysoff.class);
			cq.where(
					cb.equal(rt.get("employeeId"), employeeId)
					);
			Query q = em.createQuery(cq);

			result =  q.getResultList();
			
			for (Daysoff day : result) {
				delete(day);
			}
				  
		}
		
		public void delete(Daysoff dayOff) {
			EntityManager t = getEntityManager();

			Daysoff task= t.find(Daysoff.class, dayOff.getId());

			t.getTransaction().begin();
			t.remove(task);
			t.getTransaction().commit();
		}

		

}
