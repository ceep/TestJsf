package hrTool.controller;

import hrTool.model.Task;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class TasksController {

	private EntityManagerFactory emf = null;


	public TasksController(EntityManagerFactory emf){
		this.emf=emf;
	}

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	
	// returns a list of tasks inside a company
		public List <Task> getTasksById(int taskId){

			List <Task> result = new LinkedList<Task>();

			EntityManager em = getEntityManager();
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery cq = cb.createQuery();
			Root <Task> rt = cq.from(Task.class);
			cq.where(
					cb.equal(rt.get("taskId"), taskId)
					);
			Query q = em.createQuery(cq);

			result =  q.getResultList();

			return result;
		}
	

	// returns a list of tasks inside a company
	public List <Task> getTasksByCompany(int companyId){

		List <Task> result = new LinkedList<Task>();

		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery cq = cb.createQuery();
		Root <Task> rt = cq.from(Task.class);
		cq.where(
				cb.equal(rt.get("companyId"), companyId)
				);
		Query q = em.createQuery(cq);

		result =  q.getResultList();

		return result;
	}

		// adds a new task
		public void addTask(Task task){
			EntityManager t = getEntityManager();
			t.getTransaction().begin();
			t.persist(task);
			t.getTransaction().commit();
		}
	
		// deletes a task
		public void deleteTask(Task taskToDelete) {
			EntityManager t = getEntityManager();

			Task task= t.find(Task.class, taskToDelete.getTaskId());

			t.getTransaction().begin();
			t.remove(task);
			t.getTransaction().commit();
		}

		// updates a task
		public void updateTask(int taskId, String code, String description, String name, int totalHours, Date startDate, Date endDate, int teamId){

			EntityManager em = getEntityManager();
			Task t = em.find(Task.class, taskId);

			em.getTransaction().begin();

			t.setCode(code);
			t.setDescription(description);
			t.setName(name);
			t.setTotalHours(totalHours);
			t.setStartDate(startDate);
			t.setEndDate(endDate);
			t.setTeamId(teamId);

			em.getTransaction().commit();
		}
		
		// returns a list of tasks inside a company
		public List <Task> getTasksByTeam(int teamId){

			List <Task> result = new LinkedList<Task>();

			EntityManager em = getEntityManager();
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery cq = cb.createQuery();
			Root <Task> rt = cq.from(Task.class);
			cq.where(
					cb.equal(rt.get("teamId"), teamId)
					);
			Query q = em.createQuery(cq);

			result =  q.getResultList();

			return result;
		}
		
		public String getTaskCode(int taskId){
			List <Task> result = new LinkedList<Task>();
			EntityManager em = getEntityManager();
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery cq = cb.createQuery();
			Root <Task> rt = cq.from(Task.class);
			cq.where(
					cb.equal(rt.get("taskId"), taskId)
					);
			Query q = em.createQuery(cq);

			result =  q.getResultList();
			
			return result.get(0).getCode();
		}

}
