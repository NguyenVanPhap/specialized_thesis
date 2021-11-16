package com.elearning.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "grammar")
public class Grammar {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "grammarid", nullable = false)
	private Integer grammarid;
	
	@Column(name = "grammarname")
	private String grammarname;
	
	@Column(name = "grammarimage")
	private String grammarimage;
	
	@Column(columnDefinition = "TEXT",name="content_HTML")
	private String contentHTML;
	
	@Column(columnDefinition = "TEXT",name="content_MarkDown")
	private String contentMarkDown;

	public Integer getGrammarid() {
		return grammarid;
	}

	public void setGrammarid(Integer baigrammarid) {
		this.grammarid = baigrammarid;
	}

	public String getGrammarname() {
		return grammarname;
	}

	public void setGrammarname(String tenbaigrammar) {
		this.grammarname = tenbaigrammar;
	}

	public String getGrammarimage() {
		return grammarimage;
	}

	public void setGrammarimage(String anhbaigrammar) {
		this.grammarimage = anhbaigrammar;
	}


	public String getContentHTML() {
		return contentHTML;
	}

	public void setContentHTML(String contentHTML) {
		this.contentHTML = contentHTML;
	}

	public String getContentMarkDown() {
		return contentMarkDown;
	}

	public void setContentMarkDown(String contentMarkDown) {
		this.contentMarkDown = contentMarkDown;
	}

	public Grammar() {
		
	}

	public Grammar(Integer grammarid, String grammarname, String grammarimage, String contentHTML,
			String contentMarkDown) {
		super();
		this.grammarid = grammarid;
		this.grammarname = grammarname;
		this.grammarimage = grammarimage;
		this.contentHTML = contentHTML;
		this.contentMarkDown = contentMarkDown;
	}


	
	

}
