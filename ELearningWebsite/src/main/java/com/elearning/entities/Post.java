package com.elearning.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Post")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Integer Id;

	@Column(name = "name")
	private String Name;

	@Column(columnDefinition = "TEXT", name = "content_HTML")
	private String contentHTML;

	@Column(columnDefinition = "TEXT", name = "content_MarkDown")
	private String contentMarkDown;

	@Column(name = "image")
	private String image;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Post() {

	}

	public Post(Integer id, String name, String contentHTML, String contentMarkDown, String image) {
		super();
		Id = id;
		this.contentHTML = contentHTML;
		this.contentMarkDown = contentMarkDown;
		this.image = image;
	}

}
