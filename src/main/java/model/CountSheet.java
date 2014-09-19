package model;

import java.util.ArrayList;

public class CountSheet {
	ArrayList<CSItem> items;
	User owner;// institution, 
	String id, physicianName,csName,companyId, comments;
	public CountSheet() {
		items = new ArrayList<CSItem>();
		CSItem item = new CSItem();
		item.setQuantity(10);
		items.add(item);
	}
	
	public String getPhysicianName() {
		return physicianName;
	}
	public void setPhysicianName(String physicianName) {
		this.physicianName = physicianName;
	}
	public String getCsName() {
		return csName;
	}
	public void setCsName(String csName) {
		this.csName = csName;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public ArrayList<CSItem> getItems() {
		return items;
	}
	public void setItems(ArrayList<CSItem> items) {
		this.items = items;
	}

	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}
 class CSItem{
	 public CSItem() {
		// TODO Auto-generated constructor stub
	}
	 Part part;
	 int quantity;
	 String  notes,localId, category, ex1, ex2, ex3, ex4;
	public Part getPart() {
		return part;
	}
	public void setPart(Part part) {
		this.part = part;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getLocalId() {
		return localId;
	}
	public void setLocalId(String localId) {
		this.localId = localId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getEx1() {
		return ex1;
	}
	public void setEx1(String ex1) {
		this.ex1 = ex1;
	}
	public String getEx2() {
		return ex2;
	}
	public void setEx2(String ex2) {
		this.ex2 = ex2;
	}
	public String getEx3() {
		return ex3;
	}
	public void setEx3(String ex3) {
		this.ex3 = ex3;
	}
	public String getEx4() {
		return ex4;
	}
	public void setEx4(String ex4) {
		this.ex4 = ex4;
	} 
	
}
