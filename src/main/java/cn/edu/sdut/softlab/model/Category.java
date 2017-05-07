/*
 * Copyright 2016 Su Baochen and individual contributors by the 
 * @authors tag. See the copyright.txt in the distribution for
 * a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.edu.sdut.softlab.model;

import java.io.Serializable;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Su Baochen
 */
@ManagedBean(name = "Category")
@Entity
@Table(name = "category")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c"),
		@NamedQuery(name = "Category.findById", query = "SELECT c FROM Category c WHERE c.id = :id"),
		@NamedQuery(name = "Category.findByName", query = "SELECT c FROM Category c WHERE c.name = :name") })
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Size(max = 128)
	@Column(name = "name")
	private String name;
	@OneToMany(mappedBy = "category")
	private Set<Item> itemSet;

	public Category() {
	}

	public Category(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlTransient
	public Set<Item> getItemSet() {
		return itemSet;
	}

	public void setItemSet(Set<Item> itemSet) {
		this.itemSet = itemSet;
	}

	@Override
	public String toString() {
		// return "cn.edu.sdut.softlab.model.Category[ id=" + id + " ]";
		return name;
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

		if (!(other instanceof Category))
			return false;

		
		final Category c = (Category) other;

		if (id == null) {

			if (c.getId() != null) {
				return false;
			}

		} else if (!id.equals(c.getId())) {
			return false;
		}
		
/*		if (name == null) {

			if (c.getName() != null) {
				return false;
			}

		} else if (!name.equals(c.getName())) {
			return false;
		}
*/		

		//几项检查都没问题 返回true
		return true;
	}

	@Override
	public int hashCode() {  //hashCode主要是用来提高hash系统的查询效率。当hashCode中不进行任何操作时，可以直接让其返回 一常数，或者不进行重写。
		final int prime = 29;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}




}
