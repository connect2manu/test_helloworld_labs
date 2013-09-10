package jpa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the prikbord database table.
 * 
 */
@Entity
public class Prikbord implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private int accountnumber;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastupdatedatetime;

	private String parentalrating;

	private String posterfile;

	private String title;

	//bi-directional many-to-one association to Prikborditem
	@OneToMany(mappedBy="prikbord")
	private List<Prikborditem> prikborditems;

	public Prikbord() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccountnumber() {
		return this.accountnumber;
	}

	public void setAccountnumber(int accountnumber) {
		this.accountnumber = accountnumber;
	}

	public Date getLastupdatedatetime() {
		return this.lastupdatedatetime;
	}

	public void setLastupdatedatetime(Date lastupdatedatetime) {
		this.lastupdatedatetime = lastupdatedatetime;
	}

	public String getParentalrating() {
		return this.parentalrating;
	}

	public void setParentalrating(String parentalrating) {
		this.parentalrating = parentalrating;
	}

	public String getPosterfile() {
		return this.posterfile;
	}

	public void setPosterfile(String posterfile) {
		this.posterfile = posterfile;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Prikborditem> getPrikborditems() {
		return this.prikborditems;
	}

	public void setPrikborditems(List<Prikborditem> prikborditems) {
		this.prikborditems = prikborditems;
	}

	public Prikborditem addPrikborditem(Prikborditem prikborditem) {
		getPrikborditems().add(prikborditem);
		prikborditem.setPrikbord(this);

		return prikborditem;
	}

	public Prikborditem removePrikborditem(Prikborditem prikborditem) {
		getPrikborditems().remove(prikborditem);
		prikborditem.setPrikbord(null);

		return prikborditem;
	}

}