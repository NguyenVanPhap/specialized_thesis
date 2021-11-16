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

	@Column(columnDefinition = "TEXT", name = "content_HTML")
	private String contentHTML;

	@Column(columnDefinition = "TEXT", name = "content_MarkDown")
	private String contentMarkDown;

	public Integer getGrammarId() {
		return grammarid;
	}

	public void setGrammarId(Integer grammarid) {
		this.grammarid = grammarid;
	}

	public String getGrammarName() {
		return grammarname;
	}

	public void setTenbaigrammar(String grammarname) {
		this.grammarname = grammarname;
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

	public Grammar(Integer grammarid, String grammarname, String contentHTML, String contentMarkDown) {
		super();
		this.grammarid = grammarid;
		this.grammarname = grammarname;

		this.contentHTML = contentHTML;
		this.contentMarkDown = contentMarkDown;
	}


}
