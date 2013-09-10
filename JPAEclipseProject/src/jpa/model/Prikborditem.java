package jpa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the prikborditems database table.
 * 
 */
@Entity
@Table(name="prikborditems")
public class Prikborditem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String contenttype;

	private int depth;

	private String description;

	private String foldertype;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastupdatedatetime;

	private String parentalrating;

	private int parentid;

	private String posterfile;

	private String reference;

	private String referencetype;

	private String title;

	//bi-directional many-to-one association to Prikbord
	@ManyToOne
	@JoinColumn(name="prikbordid")
	private Prikbord prikbord;

	public Prikborditem() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContenttype() {
		return this.contenttype;
	}

	public void setContenttype(String contenttype) {
		this.contenttype = contenttype;
	}

	public int getDepth() {
		return this.depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFoldertype() {
		return this.foldertype;
	}

	public void setFoldertype(String foldertype) {
		this.foldertype = foldertype;
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

	public int getParentid() {
		return this.parentid;
	}

	public void setParentid(int parentid) {
		this.parentid = parentid;
	}

	public String getPosterfile() {
		return this.posterfile;
	}

	public void setPosterfile(String posterfile) {
		this.posterfile = posterfile;
	}

	public String getReference() {
		return this.reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getReferencetype() {
		return this.referencetype;
	}

	public void setReferencetype(String referencetype) {
		this.referencetype = referencetype;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Prikbord getPrikbord() {
		return this.prikbord;
	}

	public void setPrikbord(Prikbord prikbord) {
		this.prikbord = prikbord;
	}

}