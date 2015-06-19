package hrTool.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the employee database table.
 * 
 */
@Entity
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int employeeId;

	private int companyId;

	private int daysOff;

	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date joinDate;

	private String name;

	private int specialDaysOff;

	private int teamId;


	public Employee() {
	}

	public int getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(int id) {
		this.employeeId = id;
	}

	public int getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getDaysOff() {
		return this.daysOff;
	}

	public void setDaysOff(int daysOff) {
		this.daysOff = daysOff;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getJoinDate() {
		return this.joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSpecialDaysOff() {
		return this.specialDaysOff;
	}

	public void setSpecialDaysOff(int specialDaysOff) {
		this.specialDaysOff = specialDaysOff;
	}

	public int getTeamId() {
		return this.teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

}