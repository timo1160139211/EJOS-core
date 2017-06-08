package cn.edu.sdut.softlab.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the achievements database table.
 * 
 */
@Entity
@Table(name="achievements")
@NamedQueries({
	  @NamedQuery(name="Achievement.findAll", query="SELECT a FROM Achievement a"),
	  @NamedQuery(name = "Achievement.findById", query = "SELECT a FROM Achievement a WHERE a.id = :id")})
public class Achievement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ACHIEVEMENTS_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ACHIEVEMENTS_ID_GENERATOR")
	private int id;

	@Lob
	private String answer;

	@Column(name="answer_path")
	private String answerPath;

	private String result;

	private int score;

	//bi-directional many-to-one association to ItemBank
	@ManyToOne
	@JoinColumn(name="question_id")
	private ItemBank itemBank;

	//bi-directional many-to-one association to Student
	@ManyToOne
	private Student student;

	public Achievement() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getAnswerPath() {
		return this.answerPath;
	}

	public void setAnswerPath(String answerPath) {
		this.answerPath = answerPath;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public ItemBank getItemBank() {
		return this.itemBank;
	}

	public void setItemBank(ItemBank itemBank) {
		this.itemBank = itemBank;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}