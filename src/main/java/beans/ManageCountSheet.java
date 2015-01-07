package beans;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import model.CountSheet;
import model.User;

import com.google.gson.Gson;

import dao.UserDAO;
/*
 * this class is set to manage the count sheets. not used in this project yet.
 */
@ManagedBean
@RequestScoped
public class ManageCountSheet {
	public ManageCountSheet() {
		owner = new User();
		HttpSession session = Util.getSession();
        owner = UserDAO.getUser((String)session.getAttribute("username"));
        countSheet = new CountSheet();
        countSheet.setOwner(owner);
        list();
	}
	String csName;
	public String getCsName() {
		return csName;
	}

	public void setCsName(String csName) {
		this.csName = csName;
	}
	User owner;
	CountSheet countSheet;
	ArrayList<String> csList;
	
	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public ArrayList<String> getCsList() {
		return csList;
	}

	public void setCsList(ArrayList<String> csList) {
		this.csList = csList;
	}

	public CountSheet getCountSheet() {
		return countSheet;
	}
	
	public void setCountSheet(CountSheet countSheet) {
		this.countSheet = countSheet;
	}
	
	public void list(){
		ArrayList<String> list = new ArrayList();
		File folder = new File("/home/iman/workspace/NovoProject/CountSheetRepo/" + owner.getEmail());
		try {
			for (File fileEntry : folder.listFiles()) {
	            list.add(fileEntry.getName());
			}
			setCsList(list);
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	// this method loads the count sheet from file. needs to be completed
	public void load(){ // not sure if should define csId as method signature or class variable and set by setcsId
		Gson gson = new Gson();
		Path path = Paths.get("/home/iman/workspace/NovoProject/CountSheetRepo/"+owner.getEmail()+"/"+csName);
		String csJson;		
		try {
			csJson = Files.readAllLines(path, Charset.forName("UTF-8")).get(0);
			System.out.println(csJson);
			CountSheet cs2 = gson.fromJson(csJson, CountSheet.class);
			setCountSheet(cs2);	
			FacesContext.getCurrentInstance().getExternalContext().redirect("countSheet.xhtml");
		} catch (IOException e) {// needs json exception too
			System.out.println("CountSheet does not exist");
		}
	}
	// this method saves the countsheet object on a JSON object which is saved to a file
	// make sure the directory exists
	public void save(){
		
		Gson gson = new Gson();
		String gsonCountSheet = gson.toJson(countSheet);
		boolean dirMade = new File("CountSheetRepo/"+owner.getEmail()).mkdirs();
		System.out.println(dirMade);
		try(PrintWriter out = new PrintWriter(new BufferedWriter(
				new FileWriter("/home/iman/workspace/NovoProject/CountSheetRepo/"
						+owner.getEmail()+"/"+countSheet.getCsName(), false)))) { 
		    out.println(gsonCountSheet);
		}catch (IOException e) {
			System.out.println("error in save "+e);
		}
	}
	// generates a new countsheet
	public void newSheet(){ // new countSheet Id
		UUID csId = UUID.randomUUID();
		countSheet.setId(csId.toString()); // this countsheet has id and owner set. rest are empty
		countSheet.setCsName("tempCS");
//		Gson gson = new Gson();
//		String gsonCountSheet = gson.toJson(countSheet);
//		boolean dirMade = new File("CountSheetRepo/"+owner.getEmail()).mkdirs();
//		try(PrintWriter out = new PrintWriter(new BufferedWriter(
//				new FileWriter("/home/iman/workspace/NovoProject/CountSheetRepo/"
//						+owner.getEmail()+"/"+countSheet.getCsName(), true)))) {
//		    out.println(gsonCountSheet);
//		}catch (IOException e) {
//			System.out.println("error in create new sheet "+e);
//		}
		
		try { // redirect to new countsheet
			FacesContext.getCurrentInstance().getExternalContext().redirect("countSheet.xhtml");// change this
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
