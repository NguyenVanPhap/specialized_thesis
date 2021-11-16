package com.elearning.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "KhoaHoc")
public class KhoaHoc {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "khoahocid", nullable = false)
	private Integer khoahocid;

	@Column(name = "tenkhoahoc")
	private String tenkhoahoc;

	@Column(name = "targetuser")
	private String targetuser;

	@Column(name = "content")
	private String content;
	
	
	
	public Integer getKhoaHocId() {
		return khoahocid;
	}

	public void setKhoaHocId(Integer khoahocid) {
		this.khoahocid = khoahocid;
	}

	public String getTenKhoaHoc() {
		return tenkhoahoc;
	}

	public void setTenKhoHoc(String tenkhoahoc) {
		this.tenkhoahoc = tenkhoahoc;
	}

	public KhoaHoc() {

	}

	public String getTargetUser() {
		return targetuser;
	}

	public void setTargetUser(String targetuser) {
		this.targetuser = targetuser;
	}

	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public KhoaHoc(Integer khoahocid, String tenkhoahoc, String targetuser) {
		super();
		this.khoahocid = khoahocid;
		this.tenkhoahoc = tenkhoahoc;
		this.targetuser = targetuser;
	}

}
