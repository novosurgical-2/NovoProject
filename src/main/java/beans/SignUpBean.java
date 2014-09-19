package beans;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import dao.Database;
import model.User;

@ManagedBean(name = "signUpBean")
public class SignUpBean {
	
		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}
		User user = new User();

    	private String emailReceiver="iman.sadooghi@gmail.com";

    	public void addUser(ActionEvent actionEvent) {
    		Connection con = null;
            PreparedStatement ps = null;
            int res;
       	 try {
       		 con = Database.getConnection();
    	            ps = con.prepareStatement("insert into user (email ,password ,LastName ,FirstName"
    	            		+ ",Address,City,Institution,Title ,OrgType ,GPO,Department ,Phone, Newsletter, priceTier)"
    	            		+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
    	            
    	            
    	            ps.setString(1, user.getEmail() );//email
    	            ps.setString(2,user.getPassword() );//
    	            ps.setString(3,user.getLastName() );//
    	            ps.setString(4,user.getFirstName() );//
    	            
    	            if (user.getAddress().equals("")) {		            
    		            ps.setString(5, null);
    	            }else
    					ps.setString(5, user.getAddress());
    	            if (user.getCity().equals("")) {		            
    		            ps.setString(6, null);
    	            }else
    					ps.setString(6, user.getCity());
    	            if (user.getInstitution().equals("")) {		            
    		            ps.setString(7, null);
    	            }else
    					ps.setString(7, user.getInstitution()); 
    	            if (user.getTitle().equals("")) {		            
    		            ps.setString(8, null);
    	            }else
    					ps.setString(8, user.getTitle());
    	            if (user.getOrgType().equals("")) {		            
    		            ps.setString(9, null);
    	            }else
    					ps.setString(9, user.getOrgType());
    	            if (user.getGpo().equals("")) {		            
    		            ps.setString(10, null);
    	            }else
    					ps.setString(10, user.getGpo());
    	            if (user.getDepartment().equals("")) {	
    	            	ps.setString(11, null);
    	            }else
    		            ps.setString(11, user.getDepartment());
    	            
    	            ps.setLong(12, user.getPhone());
    	            
    	            if (!user.isSubscriber() ) {
    		            ps.setInt(13, 0);
    	            }else{
    	            	ps.setInt(13, 1);	            
    	            }
    					ps.setInt(14, user.getPriceTier());
    					
    	            ps.executeUpdate();
      	          	FacesMessage message = new FacesMessage("success");
      	          	FacesContext.getCurrentInstance().addMessage(null, message);
//    	           if (ps.executeUpdate()!= 1) {
//    				System.out.println("error in record with pCode: "+record[0]);
//    			}

    	            
       	 } catch (SQLException ex) {
    	          System.out.println("Error:"+ ex.getMessage());
    		       FacesMessage message = new FacesMessage("Error:"+ ex.getMessage());
    		       FacesContext.getCurrentInstance().addMessage(null, message);
    	    } finally {
    	          Database.close(con);
    	    }
    	}
        public void saveName(ActionEvent actionEvent) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Welcome " + user.getFirstName()+" !"));
                try {
                	System.out.println("here!!!!! "+user.getFirstName());
                	String test =  user.getEmail()+"\n";
        			String content = user.getEmail()+", "+user.getLastName()+", "+user.getFirstName()+
        					", "+user.getAddress()+", "+user.getInstitution()+", "+user.getDepartment()+"\n";
         
        			File file = new File("/home/iman/workspace/signup-forms/"+user.getEmail());
         
        			// if file doesnt exists, then create it
        			if (!file.exists()) {
        				file.createNewFile();
        			}
         
        			FileWriter fw = new FileWriter(file.getAbsoluteFile());
        			BufferedWriter bw = new BufferedWriter(fw);
        			bw.write(content);
        			bw.close();
        			
        			SendEmailUsingGMailSMTP.sendEmail(emailReceiver,
        					"new account sign up for "+user.getFirstName()+" "+user.getLastName(),content);
         
        			System.out.println("saved "+user.getFirstName()+"'s request.");
         
        		} catch (IOException e) {
        			e.printStackTrace();
        		}
        }
        
        
     
}
