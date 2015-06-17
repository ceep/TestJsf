package hrTool;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;


@ManagedBean
@SessionScoped
public class PageChooser implements Serializable {

    private String page;

    public String getPage() {
    	if(page==null) page = "Dashboard";
    	System.out.println("PageChooser GET with page link: " + page);
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	@PostConstruct
    public void init() {
		if(page==null) page = "Dashboard";
        System.out.println("PageChooser INIT with page set to: " + page);
    }

    
}
