package hrTool.controller;

import hrTool.model.Request;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class RequestsController {

	private EntityManagerFactory emf = null;


	public RequestsController(EntityManagerFactory emf){
		this.emf=emf;
	}

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	
	// returns a list of requests from an employee
		public int getNumberOfRequestsByEmployee(int employeeId){

			EntityManager em = getEntityManager();
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery cq = cb.createQuery();
			Root <Request> rt = cq.from(Request.class);
			cq.where(
					cb.equal(rt.get("employeeId"), employeeId)
					);
			Query q = em.createQuery(cq);

			return q.getResultList().size();
		}
	

		public void deleteRequestsByEmployee(int employeeId){

			List <Request> result = new LinkedList<Request>();
			
			EntityManager em = getEntityManager();
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery cq = cb.createQuery();
			Root <Request> rt = cq.from(Request.class);
			cq.where(
					cb.equal(rt.get("employeeId"), employeeId)
					);
			Query q = em.createQuery(cq);

			result = q.getResultList();
			
			for (Request req : result) {
				delete(req);
			}
		}
	

		public void delete(Request req) {
			EntityManager t = getEntityManager();

			Request request= t.find(Request.class, req.getId());

			t.getTransaction().begin();
			t.remove(request);
			t.getTransaction().commit();
		}
		
}
