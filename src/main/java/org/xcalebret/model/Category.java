package org.xcalebret.model;

import java.io.Serializable;
import java.util.List;

public class Category implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private List<Task> tasks;
	private int order;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Task> getTasks() {
		return tasks;
	}
	
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	public int getOrder() {
		return order;
	}
	
	public void setOrder(int order) {
		this.order = order;
	}
	
	
}
