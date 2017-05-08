package cn.edu.sdut.softlab.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the record database table.
 * 
 */
@Entity
@Table(name="record")
@NamedQuery(name="Record.findAll", query="SELECT r FROM Record r")
public class Record implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="RECORD_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RECORD_ID_GENERATOR")
	private int id;

	@Column(name="question_id")
	private int questionId;

	private String status;

	@Column(name="student_id")
	private int studentId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date time;

	public Record() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getStudentId() {
		return this.studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}