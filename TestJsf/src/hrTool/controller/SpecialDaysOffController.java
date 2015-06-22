package hrTool.controller;

import hrTool.model.Specialdaysoff;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class SpecialDaysOffController {

	private EntityManagerFactory emf = null;


	public SpecialDaysOffController(EntityManagerFactory emf){
		this.emf=emf;
	}

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	

	// returns a list of daysOff
	public List <Specialdaysoff> getDaysOffByEmployee(int employeeId){

		List <Specialdaysoff> result = new LinkedList<Specialdaysoff>();

		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery cq = cb.createQuery();
		Root <Specialdaysoff> rt = cq.from(Specialdaysoff.class);
		cq.where(
				cb.equal(rt.get("employeeId"), employeeId)
				);
		Query q = em.createQuery(cq);

		result =  q.getResultList();

		return result;
	}

		// adds a new day off
		public void addDayOff(Specialdaysoff dayOff){
			EntityManager t = getEntityManager();
			t.getTransaction().begin();
			t.persist(dayOff);
			t.getTransaction().commit();
		}
		
		
		// delete the special days off for a certain employee
		public void deleteDaysOffForEmployee(int employeeId) {
			
			List <Specialdaysoff> result = new LinkedList<Specialdaysoff>();
			EntityManager em = getEntityManager();
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery cq = cb.createQuery();
			Root <Specialdaysoff> rt = cq.from(Specialdaysoff.class);
			cq.where(
					cb.equal(rt.get("employeeId"), employeeId)
					);
			Query q = em.createQuery(cq);

			result =  q.getResultList();
			
			for (Specialdaysoff day : result) {
				delete(day);
			}
				  
		}
		
		public void delete(Specialdaysoff dayOff) {
			EntityManager t = getEntityManager();

			Specialdaysoff task= t.find(Specialdaysoff.class, dayOff.getId());

			t.getTransaction().begin();
			t.remove(task);
			t.getTransaction().commit();
		}

		

}
