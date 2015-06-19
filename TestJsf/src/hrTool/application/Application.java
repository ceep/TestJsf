package hrTool.application;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Application implements Serializable {

	private static Application application;
	private EntityManagerFactory entityManagerFactory;
	
	
	
	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	private Application(){
		entityManagerFactory = Persistence.createEntityManagerFactory("TestJsf");
	}
	
	public static Application getInstance(){
		if(application==null){
			synchronized(Application.class){
				if(application==null)
					application=new Application();
			}
		}
		return application;
	}
	
	public void destroy(){
		entityManagerFactory.close();
	}
    
}
