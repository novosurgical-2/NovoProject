package beans;

import java.io.IOException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import model.Part;

import org.primefaces.event.SelectEvent;

import dao.PartDAO;

/*
 * SearchBean: there are different fields on this class:
		○ partList: the first list that has results
		○ finalList: this is the final results
		○ selectedPart: the item which is clicked on by user
		○ relatedPart: the parts with the same group id
		○ search: this method calls the partDAO.search
		○ select: this method will add the selected part to the final list which is going to be shown on the next page. 
		after that it open the next page (partDetails.xhtml) with the part information
		○ selectRelated: this method redirects the user to relatedPart.xhtml page.
		the relatedPart.xhtml page show the data on the relatedPart object.
 */
@ManagedBean(name = "searchBean")
@SessionScoped
public class SearchBean {
	String searchInput;
	ArrayList<Part> partList,finalList;
	public ArrayList<Part> getFinalList() {
		return finalList;
	}

	public void setFinalList(ArrayList<Part> finalList) {
		this.finalList = finalList;
	}
	public Part getRelatedPart() {
		return relatedPart;
	}

	public void setRelatedPart(Part relatedPart) {
		this.relatedPart = relatedPart;
	}
	Part selectedPart, relatedPart; 
	
	public Part getSelectedPart() {
		return selectedPart;
	}

	public void setSelectedPart(Part selectedPart) {
		this.selectedPart = selectedPart;
	}

	public ArrayList<Part> getPartList() {
		return partList;
	}

	public void setPartList(ArrayList<Part> partList) {
		this.partList = partList;
	}
	
	public String getSearchInput() {
		return searchInput;
	}
	public void setSearchInput(String searchInput) {
		this.searchInput = searchInput;
	}

	public void search(){
		ArrayList<Part> fullParts = PartDAO.search(searchInput);
		ArrayList<Part> shortParts = new ArrayList<Part>();
		// for each in full part if parent add to shortparts
		for (Part part : fullParts) {
			if (part.isParent())
				shortParts.add(part);
		}
		System.out.println("fullparts size: "+fullParts.size());
		System.out.println("shortparts size: "+shortParts.size());
		if (shortParts.size()>0) {
			setPartList(shortParts);
		} else {
			setPartList(fullParts);
		}
	}
	public String select(SelectEvent event){
		System.out.println(selectedPart.getPartName());
		finalList = new ArrayList();
		finalList.addAll(PartDAO.selectPart(selectedPart.getpCode(), selectedPart.getGroupId()));

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("partDetails.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "select";
	}
	public void selectRelated(SelectEvent event){ // not in use anymore
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("relatedPart.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
