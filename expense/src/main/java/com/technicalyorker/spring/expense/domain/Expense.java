package com.technicalyorker.spring.expense.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class Expense {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@OneToOne
	@JoinColumn(name = "cat_id")
	private Category cat;
	@Transient
	private Category rootCat;
	private float amount;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar date = Calendar.getInstance();
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	@Transient
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
	@OneToOne
	@JoinColumn(name = "currency_id")
	private Currency currency;
	@Enumerated(EnumType.ORDINAL)
	@OneToOne
	private ExpenseType expenseType;

	private String description;

	public String getFormattedDate() {
		return sdf.format(date.getTime());
	}

	public void setFormattedDate(String formattedDate) {
		try {
			date.setTime(sdf.parse(formattedDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Category getCat() {
		return cat;
	}

	public void setCat(Category cat) {
		this.cat = cat;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		if (date != null) {
			this.date = date;
		}
	}

	public ExpenseType getExpenseType() {
		return expenseType;
	}

	public void setExpenseType(ExpenseType mode) {
		this.expenseType = mode;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Category getRootCat() {
		return rootCat;
	}

	public void setRootCat(Category rootCat) {
		this.rootCat = rootCat;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		Expense other = (Expense) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Expense [id=" + id + ", cat=" + cat + ", amount=" + amount + ", user=" + user + ", date="
				+ getFormattedDate() + "]";
	}
}
