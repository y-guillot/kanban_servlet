package org.xcalebret.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Kanban implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Date createdOn;
	private Date closedOn;
	private List<Category> categories;
	
	public Date getCreatedOn() {
		return createdOn;
	}
	
	public void setCreatedOn(Date openedOn) {
		this.createdOn = openedOn;
	}
	
	public Date getClosedOn() {
		return closedOn;
	}
	
	public void setClosedOn(Date closedOn) {
		this.closedOn = closedOn;
	}
	
	public List<Category> getCategories() {
		return categories;
	}
	
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
}
