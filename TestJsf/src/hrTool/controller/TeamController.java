package hrTool.controller;

import hrTool.model.Task;
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

public class TeamController {

	private EntityManagerFactory emf = null;


	public TeamController(EntityManagerFactory emf){
		this.emf=emf;
	}

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	

	// returns a list of tasks inside a company
	public List <Team> getTeamsByCompany(int companyId){

		List <Team> result = new LinkedList<Team>();

		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery cq = cb.createQuery();
		Root <Task> rt = cq.from(Team.class);
		cq.where(
				cb.equal(rt.get("companyId"), companyId)
				);
		Query q = em.createQuery(cq);

		result =  q.getResultList();

		return result;
	}

		// adds a new team
		public void addTeam(Team team){
			EntityManager t = getEntityManager();
			t.getTransaction().begin();
			t.persist(team);
			t.getTransaction().commit();
		}
	
		// deletes a team
		public void deleteTeam(Team teamToDelete) {
			EntityManager t = getEntityManager();

			Team team= t.find(Team.class, teamToDelete.getTeamId());

			t.getTransaction().begin();
			t.remove(team);
			t.getTransaction().commit();
		}

		// updates a team if we know the team ID
		public void updateTeam(int teamId, String name, Date formedDate){

			EntityManager em = getEntityManager();
			Team t = em.find(Team.class, teamId);

			em.getTransaction().begin();

			t.setName(name);
			t.setFormedDate(formedDate);

			em.getTransaction().commit();
		}
		
		// returns the entire team Name
		public Team getTeam(int teamId){
			EntityManager em = getEntityManager();
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery cq = cb.createQuery();
			Root <Task> rt = cq.from(Team.class);
			cq.where(
					cb.equal(rt.get("teamId"), teamId)
					);
			Query q = em.createQuery(cq);
			return (Team) q.getSingleResult();
		}

}
