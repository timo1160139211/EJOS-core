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
@NamedQueries({
    @NamedQuery(name="Team.findAll", query="SELECT t FROM Team t"),
    @NamedQuery(name="Team.findById",query="SELECT t FROM Team t WHERE t.id = :id"),
    @NamedQuery(name="Team.findByName",query="SELECT t FROM Team t WHERE t.name = :name")
})
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
	
	@Override
	public String toString() {
	    return "team[Id=" + id + "]";
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

		if (!(other instanceof Team))
			return false;

		
		final Team t = (Team) other;

		if (name == null) {

			if (t.getName() != null) {
				return false;
			}

		} else if (!name.equals(t.getName())) {
			return false;
		}
		
		//几项检查都没问题 返回true
		return true;
	}

	@Override
	public int hashCode() {  //hashCode主要是用来提高hash系统的查询效率。当hashCode中不进行任何操作时，可以直接让其返回 一常数，或者不进行重写。
		final int prime = 29;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode()+id);
		return result;
	}
	
	

}