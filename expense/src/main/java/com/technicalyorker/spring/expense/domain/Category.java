package com.technicalyorker.spring.expense.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.technicalyorker.spring.expense.util.ExpenseUtil;

@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@OneToOne
	@JoinColumn(name = "parent_id")
	private Category parent;
	@OneToMany(mappedBy = "parent")
	private List<Category> children = new ArrayList<>();
	@Column(unique = true)
	private String name;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "ExpenseType_Category_Map", joinColumns = @JoinColumn(referencedColumnName = "id", name = "exp_id"), inverseJoinColumns = @JoinColumn(referencedColumnName = "id", name = "cat_id"))
	private List<ExpenseType> expenseTypes = new ArrayList<>();

	public Category() {
	}

	public Category(String name) {
		super();
		this.name = name;
	}

	public List<ExpenseType> getExpenseTypes() {
		return expenseTypes;
	}

	public void setExpenseTypes(List<ExpenseType> expenseTypes) {
		this.expenseTypes = expenseTypes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
		parent.getChildren().add(this);
	}

	public List<Category> getChildren() {
		return children;
	}

	public void setChildren(List<Category> children) {
		this.children = children;
	}

	public String getDescription() {
		return getDescription(this);
	}

	private String getDescription(Category c) {
		if (c.getParent() == null) {
			return c.getName();
		}
		return getDescription(c.getParent()) + ">" + c.getName();
	}

	public List<Category> getRootList() {
		List<Category> list = new ArrayList<>();
		getRoot(this, list);
		return list;
	}

	private void getRoot(Category r, List<Category> list) {
		for (Category c : r.getChildren()) {
			if (c.getChildren().size() == 0 && ExpenseUtil.atleastOneElement(c.getExpenseTypes())) {
				list.add(c);
			} else {
				getRoot(c, list);
			}
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", children=" + children + ", name=" + name + ", description=" + getDescription()
				+ "]";
	}
}
