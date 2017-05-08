package cn.edu.sdut.softlab.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the team database table.
 * 
 */
@Entity
@Table(name="team")
@NamedQuery(name="Team.findAll", query="SELECT t FROM Team t")
public class Team implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TEAM_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TEAM_ID_GENERATOR")
	private int id;

	@Lob
	private String introduce;

	private String name;

	//bi-directional many-to-one association to ItemBank
	@OneToMany(mappedBy="team", fetch=FetchType.EAGER)
	private Set<ItemBank> itemBanks;

	//bi-directional many-to-one association to Student
	@OneToMany(mappedBy="team", fetch=FetchType.EAGER)
	private Set<Student> students;

	//bi-directional many-to-one association to Teacher
	@ManyToOne
	private Teacher teacher;

	public Team() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIntroduce() {
		return this.introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<ItemBank> getItemBanks() {
		return this.itemBanks;
	}

	public void setItemBanks(Set<ItemBank> itemBanks) {
		this.itemBanks = itemBanks;
	}

	public ItemBank addItemBank(ItemBank itemBank) {
		getItemBanks().add(itemBank);
		itemBank.setTeam(this);

		return itemBank;
	}

	public ItemBank removeItemBank(ItemBank itemBank) {
		getItemBanks().remove(itemBank);
		itemBank.setTeam(null);

		return itemBank;
	}

	public Set<Student> getStudents() {
		return this.students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public Student addStudent(Student student) {
		getStudents().add(student);
		student.setTeam(this);

		return student;
	}

	public Student removeStudent(Student student) {
		getStudents().remove(student);
		student.setTeam(null);

		return student;
	}

	public Teacher getTeacher() {
		return this.teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

}