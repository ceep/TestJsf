package hrTool.model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the specialdaysoff database table.
 * 
 */
@Entity
public class Specialdaysoff implements Serializable {
	private static final long serialVersionUID = 1L;

	private int companyId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date day;

	private int employeeId;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;

	public Specialdaysoff() {
	}

	public int getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public Date getDay() {
		return this.day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public int getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

}