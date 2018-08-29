package com.zzm.hd.model;
//default package

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


/**
* PubDataCollectdb entity. @author MyEclipse Persistence Tools
*/
@Entity
@Table(name = "PUB_DATA_COLLECTDB", schema = "PUBUSER", uniqueConstraints = @UniqueConstraint(columnNames = {
		"OWNER", "RESID", "FILENAME", "SRCID" }))
public class PubDataCollectdb implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String srcid;
	private String filename;
	private byte[] data;
	private Timestamp modifytime;
	private Long resid;
	private String owner;
	private Integer shared;
	private Integer ifdefault;
	private String note;
	private Integer typeid;

	// Constructors

	/** default constructor */
	public PubDataCollectdb() {
	}

	/** minimal constructor */
	public PubDataCollectdb(String srcid, String filename, Long resid) {
		this.srcid = srcid;
		this.filename = filename;
		this.resid = resid;
	}

	/** full constructor */
	public PubDataCollectdb(String srcid, String filename, byte[] data,
			Timestamp modifytime, Long resid, String owner, Integer shared,
			Integer ifdefault, String note,Integer typeid) {
		this.srcid = srcid;
		this.filename = filename;
		this.data = data;
		this.modifytime = modifytime;
		this.resid = resid;
		this.owner = owner;
		this.shared = shared;
		this.ifdefault = ifdefault;
		this.note = note;
		this.typeid = typeid;
	}

	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "SRCID", nullable = false, length = 36)
	public String getSrcid() {
		return this.srcid;
	}

	public void setSrcid(String srcid) {
		this.srcid = srcid;
	}

	@Column(name = "FILENAME", nullable = false, length = 200)
	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Column(name = "DATA")
	public byte[] getData() {
		return this.data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	@Column(name = "MODIFYTIME")
	public Timestamp getModifytime() {
		return this.modifytime;
	}

	public void setModifytime(Timestamp modifytime) {
		this.modifytime = modifytime;
	}

	@Column(name = "RESID", nullable = false, precision = 13, scale = 0)
	public Long getResid() {
		return this.resid;
	}

	public void setResid(Long resid) {
		this.resid = resid;
	}

	@Column(name = "OWNER", length = 32)
	public String getOwner() {
		return this.owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Column(name = "SHARED", precision = 22, scale = 0)
	public Integer getShared() {
		return this.shared;
	}

	public void setShared(Integer shared) {
		this.shared = shared;
	}

	@Column(name = "IFDEFAULT", precision = 1, scale = 0)
	public Integer getIfdefault() {
		return this.ifdefault;
	}

	public void setIfdefault(Integer ifdefault) {
		this.ifdefault = ifdefault;
	}

	
	@Column(name = "TYPEID", precision = 3, scale = 0)
	public Integer getTypeid() {
		return this.typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	@Column(name = "NOTE", length = 100)
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result
				+ ((filename == null) ? 0 : filename.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((ifdefault == null) ? 0 : ifdefault.hashCode());
		result = prime * result
				+ ((modifytime == null) ? 0 : modifytime.hashCode());
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + ((resid == null) ? 0 : resid.hashCode());
		result = prime * result + ((shared == null) ? 0 : shared.hashCode());
		result = prime * result + ((srcid == null) ? 0 : srcid.hashCode());
		result = prime * result + ((typeid == null) ? 0 : typeid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PubDataCollectdb other = (PubDataCollectdb) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (filename == null) {
			if (other.filename != null)
				return false;
		} else if (!filename.equals(other.filename))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ifdefault == null) {
			if (other.ifdefault != null)
				return false;
		} else if (!ifdefault.equals(other.ifdefault))
			return false;
		if (modifytime == null) {
			if (other.modifytime != null)
				return false;
		} else if (!modifytime.equals(other.modifytime))
			return false;
		if (note == null) {
			if (other.note != null)
				return false;
		} else if (!note.equals(other.note))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (resid == null) {
			if (other.resid != null)
				return false;
		} else if (!resid.equals(other.resid))
			return false;
		if (shared == null) {
			if (other.shared != null)
				return false;
		} else if (!shared.equals(other.shared))
			return false;
		if (srcid == null) {
			if (other.srcid != null)
				return false;
		} else if (!srcid.equals(other.srcid))
			return false;
		if (typeid == null) {
			if (other.typeid != null)
				return false;
		} else if (!typeid.equals(other.typeid))
			return false;
		return true;
	}

}