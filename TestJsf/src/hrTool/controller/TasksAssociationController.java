package hrTool.controller;

import hrTool.model.Task;
import hrTool.model.Taskassociation;
import hrTool.model.Team;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class TasksAssociationController {

	private EntityManagerFactory emf = null;


	public TasksAssociationController(EntityManagerFactory emf){
		this.emf=emf;
	}

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	

	// returns a list of task associations
	public List <Taskassociation> getTasksByEmployee(int employeeId){

		List <Taskassociation> result = new LinkedList<Taskassociation>();

		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery cq = cb.createQuery();
		Root <Taskassociation> rt = cq.from(Taskassociation.class);
		cq.where(
				cb.equal(rt.get("employeeId"), employeeId)
				);
		Query q = em.createQuery(cq);

		result =  q.getResultList();

		return result;
	}

		// adds a new task association
		public void addTaskAssociation(Taskassociation taskassociation){
			EntityManager t = getEntityManager();
			t.getTransaction().begin();
			t.persist(taskassociation);
			t.getTransaction().commit();
		}
	
		// deletes a task association
		public void deleteTaskAssociations(int id) {
			
			List <Taskassociation> result = new LinkedList<Taskassociation>();
			EntityManager em = getEntityManager();
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery cq = cb.createQuery();
			Root <Taskassociation> rt = cq.from(Taskassociation.class);
			cq.where(
					cb.equal(rt.get("taskId"), id)
					);
			Query q = em.createQuery(cq);

			result =  q.getResultList();
			
			for (Taskassociation taskassociation : result) {
				deleteTaskAssoc(taskassociation);
			}
				  
		}
		
		public void deleteTaskAssoc(Taskassociation taskToDelete) {
			EntityManager t = getEntityManager();

			Taskassociation task= t.find(Taskassociation.class, taskToDelete.getId());

			t.getTransaction().begin();
			t.remove(task);
			t.getTransaction().commit();
		}

		// updates a task association
		public void updateTaskAssociation(int id, int employeeId, int taskId){

			EntityManager em = getEntityManager();
			Taskassociation t = em.find(Taskassociation.class, id);

			em.getTransaction().begin();

			t.setEmployeeId(employeeId);
			t.setTaskId(taskId);

			em.getTransaction().commit();
		}

}
