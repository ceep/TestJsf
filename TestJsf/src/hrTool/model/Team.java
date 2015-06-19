package hrTool.model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the team database table.
 * 
 */
@Entity
public class Team implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int teamId;

	private int companyId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date formedDate;

	private String name;

	public Team() {
	}

	public int getTeamId() {
		return this.teamId;
	}

	public void setTeamId(int id) {
		this.teamId = id;
	}

	public int getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public Date getFormedDate() {
		return this.formedDate;
	}

	public void setFormedDate(Date formedDate) {
		this.formedDate = formedDate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}