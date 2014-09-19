package misc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import model.Part;
import dao.Database;

@ManagedBean
public class DataUpload {
	public static void main(String[] args){
		uploadFromFile("dataSource/LocalPart-new", "localPart");
	}
	
	public static void addToLocalPart(String line){
		String[] record = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
		Connection con = null;
        PreparedStatement ps = null;
        String temp = new String();
   	 try {
   		 con = Database.getConnection();
	            ps = con.prepareStatement("insert into localPart (pCode, stPCode, name, description, longDesc,"
	            		+ " retailPrice, t1Price, t2Price, t3Price, t4Price, t5Price, t6Price, t7Price, t8Price,"
	            		+ " t9Price, t10Price, leadTime, specialty, finish, origin, grade, uom, manufacturer, sterile,"
	            		+ " partUsage, groupID, isConfigurable, imagePath, isParent) values"
	            		+ " ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, '9.jpg', ?)");
	            temp = record[0] ;
	            ps.setString(1,record[0] );//pCode
	            ps.setString(2,record[1] );//stPCode
	            ps.setString(3,record[2] );//name
	            ps.setString(4,record[3] );//desc
	            ps.setString(5,record[4] );//longdesc
	            ps.setString(6,record[5] );//retailPrice
	            ps.setString(7,record[6] );
	            ps.setString(8,record[7] );
	            ps.setString(9,record[8] );
	            ps.setString(10,record[9] );
	            ps.setString(11,record[10] );
	            ps.setString(12,record[11] );
	            ps.setString(13,record[12] );
	            ps.setString(14,record[13] );
	            ps.setString(15,record[14] );
	            ps.setString(16,record[15] );
	            ps.setString(17,record[16] );//leadtime
	            ps.setString(18,record[17] );
	            ps.setString(19,record[18] );
	            ps.setString(20,record[19] );
	            ps.setString(21,record[20] );
	            ps.setString(22,record[21] );
	            ps.setString(23,record[22] );
	            ps.setString(24,record[23] );
	            ps.setString(25,record[24] );
	            ps.setString(26,record[25] );
	            ps.setString(27,record[26] );
	            ps.setString(28, record[27]);
	            
	            ps.executeUpdate();
//			       FacesMessage message = new FacesMessage("success");
//			       FacesContext.getCurrentInstance().addMessage(null, message);

	            
   	 } catch (SQLException ex) {
         System.out.println("Error in search() --> record with pCode: "+temp+" " + ex.getMessage());
	       FacesMessage message = new FacesMessage( "Error for pCode: "+temp);
//	       FacesContext.getCurrentInstance().addMessage(null, message);
	    } finally {
	          Database.close(con);
	    }
		
	}
	public static void uploadFromFile(String path,String type){
		List<String> lines= null; 
		try {
			lines = Files.readAllLines(new File(path).toPath(),Charset.forName("UTF-8"));
			if (type.equalsIgnoreCase("localPart")) {
				for (int i = 0; i < lines.size(); i++) {
					addToLocalPart(lines.get(i));
				}
				//lines.forEach((String line) -> addToLocalPart(line) );
			} else if (type.equalsIgnoreCase("comPart")) {
				for (int i = 0; i < lines.size(); i++) {
					addToLocalPart(lines.get(i));
				}
				//lines.forEach((String line) -> addtoComPart(line) );
			} else if (type.equalsIgnoreCase("user")) {
				for (int i = 0; i < lines.size(); i++) {
					addToUser(lines.get(i));
				}
				//lines.forEach((String line) -> addtoUser(line) );
			}
			
		} catch (IOException e) {
	          System.out.println("Error"+ e.getMessage());
		       FacesMessage message = new FacesMessage( "error: "+e.getMessage());
		       FacesContext.getCurrentInstance().addMessage(null, message);
		}
		
	}
	public static File convertToFile(InputStream is){
		 try
		  {
			  File f=new File("outFile.java");
			  OutputStream out=new FileOutputStream(f);
			  byte buf[]=new byte[1024];
			  int len;
			  while((len=is.read(buf))>0)
				  out.write(buf,0,len);
			  out.close();
			  is.close();
			  return f;
		  }
		  catch (IOException e){
			  System.out.println(e.getMessage());
			  return null;
		  }
	}
	public static void uploadFromFile(InputStream inputSteam,String type){
		List<String> lines= null; 
		File file = convertToFile(inputSteam);
		try {
			lines = Files.readAllLines(file.toPath(),Charset.forName("UTF-8"));
			if (type.equalsIgnoreCase("localPart")) {
				for (int i = 0; i < lines.size(); i++) {
					addToLocalPart(lines.get(i));
				}
				//lines.forEach((String line) -> addToLocalPart(line) );
			} else if (type.equalsIgnoreCase("comPart")) {
				for (int i = 0; i < lines.size(); i++) {
					addToLocalPart(lines.get(i));
				}
				//lines.forEach((String line) -> addtoComPart(line) );
			} else if (type.equalsIgnoreCase("user")) {
				for (int i = 0; i < lines.size(); i++) {
					addToUser(lines.get(i));
				}
				//lines.forEach((String line) -> addtoUser(line) );
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void addToComPart(String line){
		String[] record = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
		Connection con = null;
        PreparedStatement ps = null;
        int res;
        String temp = new String();
   	 try {
   		 con = Database.getConnection();
	            ps = con.prepareStatement("insert into comPart (pCode,stPCode,NovoPCode,description,price,manufacturer) values"
	            		+ " ( ?, ?, ?, ?, ?, ?)");
	            temp = record[0] ;
	            
	            ps.setString(1,record[0] );//pCode
	            ps.setString(2,record[1] );//stPCode
	            ps.setString(3,record[2] );//NovoPCode
	            if (record[3].equals("")) {		            
		            ps.setString(4, null);
	            }else
					ps.setString(4, record[3]);
	            if (record[4].equals("")) {
	            	ps.setNull(5, java.sql.Types.DOUBLE);//price
				}else
					ps.setString(5, record[4]);
	            if (record[5].equals("")) {
	            	ps.setString(6, null);//desc
				}else
					ps.setString(6, record[5]);
	            
	            
           
	            ps.executeUpdate();
			       FacesMessage message = new FacesMessage("success");
			       FacesContext.getCurrentInstance().addMessage(null, message);

	            
   	 } catch (SQLException ex) {
	          System.out.println("Error in search() --> record with pCode: "+temp+" " + ex.getMessage());
		       FacesMessage message = new FacesMessage( "Error for pCode: "+temp);
		       FacesContext.getCurrentInstance().addMessage(null, message);
	    } finally {
	          Database.close(con);
	    }
	}
	public static void addToUser(String line){
		String[] record = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
		Connection con = null;
        PreparedStatement ps = null;
        int res;
        String temp = new String();
   	 try {
   		 con = Database.getConnection();
	            ps = con.prepareStatement("insert into user (email ,password ,LastName ,FirstName"
	            		+ ",Address,City,Institution,Title ,OrgType ,GPO,Department ,Phone, Newsletter, priceTier)"
	            		+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
	            temp = record[0] ;
	            
	            ps.setString(1,record[0] );//email
	            ps.setString(2,record[1] );//
	            ps.setString(3,record[2] );//
	            ps.setString(4,record[3] );//
	            
	            if (record[4].equals("")) {		            
		            ps.setString(5, null);
	            }else
					ps.setString(5, record[4]);
	            if (record[5].equals("")) {		            
		            ps.setString(6, null);
	            }else
					ps.setString(6, record[5]);
	            if (record[6].equals("")) {		            
		            ps.setString(7, null);
	            }else
					ps.setString(7, record[6]); 
	            if (record[7].equals("")) {		            
		            ps.setString(8, null);
	            }else
					ps.setString(8, record[7]);
	            if (record[8].equals("")) {		            
		            ps.setString(9, null);
	            }else
					ps.setString(9, record[8]);
	            if (record[9].equals("")) {		            
		            ps.setString(10, null);
	            }else
					ps.setString(10, record[9]);
	            if (record[10].equals("")) {		            
		            ps.setString(11, null);
	            }else
					ps.setString(11, record[10]);
	            if (record[11].equals("")) {	
	            	ps.setNull(12, java.sql.Types.INTEGER);
	            }else
		            ps.setString(12, record[11]);
	            if (record[12].equals("") || record[12].equalsIgnoreCase("FALSE")) {
		            ps.setInt(13, 0);
	            }else if (record[12].equalsIgnoreCase("TRUE")){
	            	ps.setInt(13, 1);	            }
	            if (record[13].equals("")) {		            
		            ps.setNull(14, java.sql.Types.INTEGER);
	            }else
					ps.setString(14, record[13]);
	      
	            
           
	            ps.executeUpdate();
			       FacesMessage message = new FacesMessage("success");
			       FacesContext.getCurrentInstance().addMessage(null, message);

	            
   	 } catch (SQLException ex) {
	          System.out.println("Error in search() --> record with userName: "+temp+" " + ex.getMessage());
		       FacesMessage message = new FacesMessage(
		    		   "Error for userName: "+temp);
		       FacesContext.getCurrentInstance().addMessage(null, message);
	    } finally {
	          Database.close(con);
	    }
	}

	
}
