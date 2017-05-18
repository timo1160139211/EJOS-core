package cn.edu.sdut.softlab.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.Set;


/**
 * The persistent class for the teacher database table.
 * 
 */
@Entity
@Table(name="teacher")
@NamedQueries({
	  @NamedQuery(name = "Teacher.findAll", query = "SELECT t FROM Teacher t"),
	  @NamedQuery(name = "Teacher.findByTeaNOAndPassword", query = "SELECT t FROM Teacher t WHERE t.teacherNum = :teaNO and t.password = :password"),
	  @NamedQuery(name = "Teacher.findByTeaNO", query = "SELECT t FROM Teacher t WHERE t.teacherNum = :teaNO")})
public class Teacher implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TEACHER_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TEACHER_ID_GENERATOR")
	private int id;

	@Column(name="id_card")
	private String idCard;

	private String name;

	private String password;

	@Column(name="teacher_num")
	private BigInteger teacherNum;

	private BigInteger tel;

	//bi-directional many-to-one association to Team
	@OneToMany(mappedBy="teacher", fetch=FetchType.EAGER)
	private Set<Team> teams;

	public Teacher() {
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

	public BigInteger getTeacherNum() {
		return this.teacherNum;
	}

	public void setTeacherNum(BigInteger teacherNum) {
		this.teacherNum = teacherNum;
	}

	public BigInteger getTel() {
		return this.tel;
	}

	public void setTel(BigInteger tel) {
		this.tel = tel;
	}

	public Set<Team> getTeams() {
		return this.teams;
	}

	public void setTeams(Set<Team> teams) {
		this.teams = teams;
	}

	public Team addTeam(Team team) {
		getTeams().add(team);
		team.setTeacher(this);

		return team;
	}

	public Team removeTeam(Team team) {
		getTeams().remove(team);
		team.setTeacher(null);

		return team;
	}

}