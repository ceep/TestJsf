package hrTool.beans;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped

public class Login {



	private String username;

	private String password;



	public String getUsername() { return username; }

	public void setUsername(String username) { this.username = username; }



	public String getPassword() { return password; }

	public void setPassword(String password) { this.password = password; }


	public String login(){
		System.out.println("Logging in user:" + username + " and pass: " + password);

		
		boolean valid = false;
		// should set the value of valid after we check the DB
		
		
		if (valid) {
			HttpSession session = SessionBean.getSession();
			session.setAttribute("userid", "3");
			return "index";
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Login Error",
							"Please enter correct username and Password"));
			return "login";
		}
	}

	public String logout() {
		HttpSession session = SessionBean.getSession();
		session.invalidate();
		//System.out.println("Logout is called!...");
		return "login";
	}

}