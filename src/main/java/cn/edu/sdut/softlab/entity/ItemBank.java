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
@NamedQueries({
	@NamedQuery(name="ItemBank.findAll", query="SELECT i FROM ItemBank i"),
	@NamedQuery(name="ItemBank.findByQuestion",query="SELECT i FROM ItemBank i WHERE i.question = :question "),
	@NamedQuery(name="ItemBank.findByQid",query="SELECT i FROM ItemBank i WHERE i.id = :qid "),
	@NamedQuery(name="ItemBank.findByTeamId",query="SELECT i FROM ItemBank i WHERE i.team.id = :teamId "),
	@NamedQuery(name="ItemBank.findByTeam",query="SELECT i FROM ItemBank i WHERE i.team = :team ")})
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
	
	@Override
	public String toString() {
	    return question;
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

		if (!(other instanceof ItemBank))
			return false;

		
		final ItemBank s = (ItemBank) other;
		
		if (question == null) {

			if (s.getQuestion() != null) {
				return false;
			}

		} else if (!question.equals(s.getQuestion())) {
			return false;
		}
		

		//几项检查都没问题 返回true
		return true;
	}

	@Override
	public int hashCode() {  //hashCode主要是用来提高hash系统的查询效率。当hashCode中不进行任何操作时，可以直接让其返回 一常数，或者不进行重写。
		final int prime = 314159;
		int result = 271828;
		result = prime * result + ((question == null) ? 0 : question.hashCode());
//		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	
	
	
	

}