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
	  @NamedQuery(name = "Student.findByStuNO", query = "SELECT s FROM Student s WHERE s.studentNum = :stuNO"),
	  @NamedQuery(name = "Student.findByStuNOAndPassword", query = "SELECT s FROM Student s WHERE s.studentNum = :stuNO and s.password = :password"),
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

	
	

	/*
	 * 重写equals必须注意： 1 自反性：对于任意的引用值x，x.equals(x)一定为true 2 对称性：对于任意的引用值x 和
	 * y，当x.equals(y)返回true，y.equals(x)也一定返回true 3
	 * 传递性：对于任意的引用值x、y和ｚ，如果x.equals(y)返回true，并且y.equals(z)也返回true，那么x.equals(z)
	 * 也一定返 回 true 4 一致性：对于任意的引用值x 和 y，如果用于equals比较的对象信息没有被修改，
	 * 多次调用x.equals(y)要么一致地返回true，要么一致地返回false 5
	 * 非空性：对于任意的非空引用值x，x.equals(null)一定返回false
	 * 
	 * 请注意： 重写equals方法后最好重写hashCode方法，否则两个等价对象可能得到不同的hashCode,这在集合框架中使用可能产生严重后果
	 */

	/*
	 * 1.重写equals方法修饰符必须是public,因为是重写的Object的方法. 2.参数类型必须是Object.
	 */
	@Override
	public boolean equals(Object other) {

		if (this == other) { // 先检查是否其自反性，后比较 obj 是否为空。这样效率高
			return true;
		}

		if (other == null) {
			return false;
		}

		if (!(other instanceof Student))
			return false;

		
		final Student s = (Student) other;

		if (studentNum == null) {

			if (s.getStudentNum() != null) {
				return false;
			}

//		} else if (!studentNum.equals(s.getId())) {
		} else if (!studentNum.equals(s.getStudentNum())) {
			return false;
		}
		
		if (name == null) {

			if (s.getName() != null) {
				return false;
			}

		} else if (!name.equals(s.getName())) {
			return false;
		}
		
		//几项检查都没问题 返回true
		return true;
	}

	@Override
	public int hashCode() {  //hashCode主要是用来提高hash系统的查询效率。当hashCode中不进行任何操作时，可以直接让其返回 一常数，或者不进行重写。
		final int prime = 29;
		int result = 1;
		result = prime * result + ((studentNum == null) ? 0 : studentNum.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

}