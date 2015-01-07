package model;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;


import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
/*
 * this defines the local or competitor parts. pCode is the key in the sql table.
this class has getters and setters for the variables.
 */
public class Part {
	
	String pCode,stPCode,name ,description,groupId,imagePath,
	longDesc,leadTime, specialty, finish, grade, uom, sterile,usage,origin,manufacturer,pType; 
	public String getpType() {
		return pType;
	}
	public void setpType(String pType) {
		this.pType = pType;
	}
	double retailPrice, t1Price, t2Price, t3Price, t4Price,
	t5Price, t6Price, t7Price, t8Price, t9Price, t10Price;
	boolean isConfigurable, isParent;
	public boolean isParent() {
		return isParent;
	}
	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}
	public void setParent(String isParent) {
		if (Integer.valueOf(isParent)== 1) {
			this.isParent= true;
		} else {
			this.isParent= false;
		}
	}
	public String getUsage() {
		return usage;
	}
	public void setUsage(String usage) {
		this.usage = usage;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getLongDesc() {
		return longDesc;
	}
	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}
	public String getLeadTime() {
		return leadTime;
	}
	public void setLeadTime(String leadTime) {
		this.leadTime = leadTime;
	}
	public String getSpecialty() {
		return specialty;
	}
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	public String getFinish() {
		return finish;
	}
	public void setFinish(String finish) {
		this.finish = finish;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public String getSterile() {
		return sterile;
	}
	public void setSterile(String sterile) {
		this.sterile = sterile;
	}
	public double getRetailPrice() {
		return retailPrice;
	}
	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}
	public double getT1Price() {
		return t1Price;
	}
	public void setT1Price(double t1Price) {
		this.t1Price = t1Price;
	}
	public double getT2Price() {
		return t2Price;
	}
	public void setT2Price(double t2Price) {
		this.t2Price = t2Price;
	}
	public double getT3Price() {
		return t3Price;
	}
	public void setT3Price(double t3Price) {
		this.t3Price = t3Price;
	}
	public double getT4Price() {
		return t4Price;
	}
	public void setT4Price(double t4Price) {
		this.t4Price = t4Price;
	}
	public double getT5Price() {
		return t5Price;
	}
	public void setT5Price(double t5Price) {
		this.t5Price = t5Price;
	}
	public double getT6Price() {
		return t6Price;
	}
	public void setT6Price(double t6Price) {
		this.t6Price = t6Price;
	}
	public double getT7Price() {
		return t7Price;
	}
	public void setT7Price(double t7Price) {
		this.t7Price = t7Price;
	}
	public double getT8Price() {
		return t8Price;
	}
	public void setT8Price(double t8Price) {
		this.t8Price = t8Price;
	}
	public double getT9Price() {
		return t9Price;
	}
	public void setT9Price(double t9Price) {
		this.t9Price = t9Price;
	}
	public double getT10Price() {
		return t10Price;
	}
	public void setT10Price(double t10Price) {
		this.t10Price = t10Price;
	}
	
	
//	public StreamedContent getImage() {
//		return image;
//	}
//	public void setImage(StreamedContent image) {
//		this.image = image;
//	}
//	public void setImage() throws IOException { //i m not sure if this is working!
//		File fi = new File("/home/iman/workspace/9.jpg");
//		//File fi = new File(path);
//		byte[] fileContent = Files.readAllBytes(fi.toPath());
//		InputStream is = new ByteArrayInputStream(fileContent);
//		this.image =  new DefaultStreamedContent(is);
//	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getpCode() {
		return pCode;
	}
	public void setpCode(String pCode) {
		this.pCode = pCode;
	}
	public String getStPCode() {
		return stPCode;
	}
	public void setStPCode(String stPCode) {
		this.stPCode = stPCode;
	}
	public String getPartName() {
		return name;
	}
	public void setPartName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public boolean isConfigurable() {
		return isConfigurable;
	}
	public void setConfigurable(boolean isConfigurable) {
		this.isConfigurable = isConfigurable;
	}

}
