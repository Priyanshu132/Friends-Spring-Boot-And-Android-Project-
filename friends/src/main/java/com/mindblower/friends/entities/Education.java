package com.mindblower.friends.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "education")
public class Education {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = false,length = 100)
	private String name;
	
	private String place;
	
	@Column(nullable = false)
	private String visibility;
	
	private String from_month;
	private String till_month;
	private String from_year;
	private String till_year;
	
	
	
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
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	public String getFrom_month() {
		return from_month;
	}
	public void setFrom_month(String from_month) {
		this.from_month = from_month;
	}
	public String getTill_month() {
		return till_month;
	}
	public void setTill_month(String till_month) {
		this.till_month = till_month;
	}
	public String getFrom_year() {
		return from_year;
	}
	public void setFrom_year(String from_year) {
		this.from_year = from_year;
	}
	public String getTill_year() {
		return till_year;
	}
	public void setTill_year(String till_year) {
		this.till_year = till_year;
	}
	
	
}
