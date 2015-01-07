package beans;


import java.io.File;
import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import misc.DataUpload;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
/*
 * ManageTables: this class take care of the admin page. it upload parts from file
 * this class makes use of DataUpload class.
 */
@ManagedBean
@ViewScoped
public class ManageTables {
	
    
   public ManageTables() {
		super();
		this.type = "user";
	}

private UploadedFile file;
   String type;

   public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}

public UploadedFile getFile() {
       return file;
   }

   public void setFile(UploadedFile file) {
       this.file = file;
   }
    // this helps uploading the chosen file data.
   public void handleFileUpload(FileUploadEvent event) {
	   file = event.getFile();

       try {
			DataUpload.uploadFromFile(file.getInputstream(), getType());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("error: "+e.getMessage());
			e.printStackTrace();
		}
   }
}