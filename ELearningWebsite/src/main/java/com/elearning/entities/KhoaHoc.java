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
	
	@Column(name = "anhkhoahoc")
	private String anhkhoahoc;
	


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

	public String getAnhKhoaHoc() {
		return anhkhoahoc;
	}
	public void setAnhKhoaHoc(String anhkhoahoc) {
		this.anhkhoahoc = anhkhoahoc;
	}
	public KhoaHoc() {
		
	}

	public KhoaHoc(Integer khoahocid, String tenkhoahoc, String anhkhoahoc) {
		super();
		this.khoahocid = khoahocid;
		this.tenkhoahoc= tenkhoahoc;
		this.anhkhoahoc = anhkhoahoc;
	}


	
	

}
