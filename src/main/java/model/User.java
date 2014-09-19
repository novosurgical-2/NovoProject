package model;

public class User {
    private String email,lastName,firstName,address, city, institution, title, gpo, department, orgType, password;
	private long phone;
	private boolean isSubscriber;
	private int priceTier;
	

	public int getPriceTier() {
		return priceTier;
	}
	public void setPriceTier(int priceTier) {
		this.priceTier = priceTier;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getInstitution() {
		return institution;
	}
	public void setInstitution(String institution) {
		this.institution = institution;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getGpo() {
		return gpo;
	}
	public void setGpo(String gpo) {
		this.gpo = gpo;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getOrgType() {
		return orgType;
	}
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public boolean isSubscriber() {
		return isSubscriber;
	}
	public void setSubscriber(boolean isSubscriber) {
		this.isSubscriber = isSubscriber;
	}
	

}
