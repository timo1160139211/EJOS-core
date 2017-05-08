package cn.edu.sdut.softlab.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the item_bank database table.
 * 
 */
@Entity
@Table(name="item_bank")
@NamedQuery(name="ItemBank.findAll", query="SELECT i FROM ItemBank i")
public class ItemBank implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ITEM_BANK_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ITEM_BANK_ID_GENERATOR")
	private int id;

	@Lob
	private String answer;

	@Temporal(TemporalType.TIMESTAMP)
	private Date deadline;

	private int diffculty;

	@Lob
	private String introduce;

	private String path;

	@Lob
	private String question;

	private String result;

	@Temporal(TemporalType.TIMESTAMP)
	private Date time;

	//bi-directional many-to-one association to Achievement
	@OneToMany(mappedBy="itemBank", fetch=FetchType.EAGER)
	private Set<Achievement> achievements;

	//bi-directional many-to-one association to Team
	@ManyToOne
	private Team team;

	public ItemBank() {
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

	public Date getDeadline() {
		return this.deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public int getDiffculty() {
		return this.diffculty;
	}

	public void setDiffculty(int diffculty) {
		this.diffculty = diffculty;
	}

	public String getIntroduce() {
		return this.introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Set<Achievement> getAchievements() {
		return this.achievements;
	}

	public void setAchievements(Set<Achievement> achievements) {
		this.achievements = achievements;
	}

	public Achievement addAchievement(Achievement achievement) {
		getAchievements().add(achievement);
		achievement.setItemBank(this);

		return achievement;
	}

	public Achievement removeAchievement(Achievement achievement) {
		getAchievements().remove(achievement);
		achievement.setItemBank(null);

		return achievement;
	}

	public Team getTeam() {
		return this.team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

}