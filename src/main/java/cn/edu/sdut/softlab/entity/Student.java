package cn.edu.sdut.softlab.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.Set;


/**
 * The persistent class for the student database table.
 * 
 */
@Entity
@Table(name="student")
@NamedQueries({
	  @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s"),
	  @NamedQuery(name = "Student.findById", query = "SELECT s FROM Student s WHERE s.id = :id")})
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="STUDENT_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="STUDENT_ID_GENERATOR")
	private int id;

	@Column(name="id_card")
	private String idCard;

	private String name;

	private String password;

	@Column(name="student_num")
	private BigInteger studentNum;

	//bi-directional many-to-one association to Achievement
	@OneToMany(mappedBy="student", fetch=FetchType.EAGER)
	private Set<Achievement> achievements;

	//bi-directional many-to-one association to Information
	@OneToMany(mappedBy="student", fetch=FetchType.EAGER)
	private Set<Information> informations;

	//bi-directional many-to-one association to Team
	@ManyToOne
	private Team team;

	public Student() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdCard() {
		return this.idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BigInteger getStudentNum() {
		return this.studentNum;
	}

	public void setStudentNum(BigInteger studentNum) {
		this.studentNum = studentNum;
	}

	public Set<Achievement> getAchievements() {
		return this.achievements;
	}

	public void setAchievements(Set<Achievement> achievements) {
		this.achievements = achievements;
	}

	public Achievement addAchievement(Achievement achievement) {
		getAchievements().add(achievement);
		achievement.setStudent(this);

		return achievement;
	}

	public Achievement removeAchievement(Achievement achievement) {
		getAchievements().remove(achievement);
		achievement.setStudent(null);

		return achievement;
	}

	public Set<Information> getInformations() {
		return this.informations;
	}

	public void setInformations(Set<Information> informations) {
		this.informations = informations;
	}

	public Information addInformation(Information information) {
		getInformations().add(information);
		information.setStudent(this);

		return information;
	}

	public Information removeInformation(Information information) {
		getInformations().remove(information);
		information.setStudent(null);

		return information;
	}

	public Team getTeam() {
		return this.team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

}