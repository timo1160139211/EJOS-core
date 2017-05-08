package cn.edu.sdut.softlab.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the information database table.
 * 
 */
@Entity
@Table(name="information")
@NamedQuery(name="Information.findAll", query="SELECT i FROM Information i")
public class Information implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="INFORMATION_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="INFORMATION_ID_GENERATOR")
	private int id;

	@Lob
	private String content;

	private String level;

	private String status;

	@Temporal(TemporalType.TIMESTAMP)
	private Date time;

	@Lob
	private String title;

	//bi-directional many-to-one association to Student
	@ManyToOne
	private Student student;

	public Information() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}