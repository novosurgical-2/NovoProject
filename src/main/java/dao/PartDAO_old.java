package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Part;

  
public class PartDAO_old {    
//		public static Part[] search(String input){
//			Part[] parts = new Part[10];
//			Part part ;
//			for (int i = 0; i < parts.length; i++) {
//				part = new Part();
//				part.setpCode("1"+i);
//				part.setName("part"+i);
//				part.setDescription("this is a test desc for item number"+i);
//				parts[i] = part;
//			}
//			if (input.isEmpty())
//				return null;
//			
//			return parts;
//			
//		}
     public static ArrayList<Part> search(String query) {
    	 ArrayList<Part> resultParts = new ArrayList<Part>();
     	 String[] splitQuery = query.replaceAll(" . | .$|^. ", " ").trim().split(" +");
    	 Connection con = null;
         PreparedStatement ps = null;
         Part thisPart; 
         
    	 if (splitQuery.length == 1) {
 
    		
    		
    		
    		if (query.length()<3) {
    			 
				System.out.println("empty query");
				return null;
			}
    		 if (query.contains(" ")) {
				query = splitQuery[0];
			}
    	   		// run query on comPart.pcode  
     		try {
 	            con = Database.getConnection();
 	            ps = con.prepareStatement("select localPart.* from localPart, comPart where " +
 	            		"comPart.novoPCode=localPart.pCode " +
 	            		"AND (comPart.pCode=? OR comPart.stPCode=?)");
	            ps.setString(1, query);
	            ps.setString(2, query);
	            ResultSet rs = ps.executeQuery();
	            if (rs.next()) { // compart has some value. return part in an array which has only one part
	            	thisPart = new Part();
	                //System.out.println(rs.getString("pCode"));
	                thisPart.setpCode(rs.getString("pCode"));
	                thisPart.setStPCode(rs.getString("stPCode"));
	                thisPart.setDescription(rs.getString("description"));
	                thisPart.setPartName(rs.getString("name"));
	                thisPart.setPrice(Double.valueOf(rs.getString("price")));
	                thisPart.setGroupId(rs.getString("groupId"));
	                thisPart.setConfigurable(Boolean.valueOf(rs.getString("isConfigurable")));
	                thisPart.setImagePath("9.jpg");
	                System.out.println("9 called one part only");
	                resultParts.add(thisPart);
	                return resultParts; // this array should only have 1 part object in it
				} else { // compart didnt return anything. therefore look into localPart
					ps = con.prepareStatement(
    	                    "select * from localPart where pCode like ? union " +
    	                    "select * from localPart where stPCode like ? union " +
    	                    "select * from localPart where name like ? union " +
    	                    "select * from localPart where description like ?");
    	            ps.setString(1, "%"+query+"%");
    	            ps.setString(2, "%"+query+"%");
    	            ps.setString(3, "%"+query+"%");
    	            ps.setString(4, "%"+query+"%");

    	            rs = ps.executeQuery();
    	            if (!rs.isBeforeFirst()){
    	            	System.out.println("empty resultSet");
    	            	return null;
    	            }
	    	            while (rs.next()) {
	    	            	thisPart = new Part();
	    	                //System.out.println(rs.getString("pCode"));
	    	                thisPart.setpCode(rs.getString("pCode"));
	    	                thisPart.setStPCode(rs.getString("stPCode"));
	    	                thisPart.setDescription(rs.getString("description"));
	    	                thisPart.setPartName(rs.getString("name"));
	    	                thisPart.setPrice(Double.valueOf(rs.getString("price")));
	    	                thisPart.setGroupId(rs.getString("groupId"));
	    	                thisPart.setConfigurable(Boolean.valueOf(rs.getString("isConfigurable")));
	    	                thisPart.setImagePath("1.png");
	    	                resultParts.add(thisPart);
	    	                }
	    	            return resultParts;
				}
     		} catch (Exception ex) {
 	            System.out.println("Error in search() -->" + ex.getMessage());
 	            return null;
 	        } finally {
 	            Database.close(con);
 	        } 
     		
		} else if (splitQuery.length >1) {
			try {
			      String selector = null;
				String tempSelector = "select * from localPart where pCode like ? union " +
		                  "select * from localPart where stPCode like ? union " +
		                  "select * from localPart where name like ? union " +
		                  "select * from localPart where description like ?";
		    	  for (int i = 0; i < splitQuery.length-1; i++) {
		    		  if (i == 0)
		    			  selector = tempSelector;
		    		  	  selector = selector +" union "+ tempSelector;
				}
		    	  	    	  
		          con = Database.getConnection();
		          ps = con.prepareStatement(selector);
		          int j = 0;
		          for (int i = 0; i < (splitQuery.length); i++) {
		              ps.setString((i*4)+1, "%"+splitQuery[j]+"%");
		              ps.setString((i*4)+2, "%"+splitQuery[j]+"%");
		              ps.setString((i*4)+3, "%"+splitQuery[j]+"%");
		              ps.setString((i*4)+4, "%"+splitQuery[j]+"%");
		              j++;
				} 


		          ResultSet rs = ps.executeQuery();
  	            if (!rs.isBeforeFirst()){
  	            	System.out.println("empty resultSet");
  	            	return null;
  	            }
	    	            while (rs.next()) {
	    	            	thisPart = new Part();
	    	                System.out.println(rs.getString("pCode"));
	    	                thisPart.setpCode(rs.getString("pCode"));
	    	                thisPart.setStPCode(rs.getString("stPCode"));
	    	                thisPart.setDescription(rs.getString("description"));
	    	                thisPart.setPartName(rs.getString("name"));
	    	                thisPart.setPrice(Double.valueOf(rs.getString("price")));
	    	                thisPart.setGroupId(rs.getString("groupId"));
	    	                thisPart.setConfigurable(Boolean.valueOf(rs.getString("isConfigurable")));
	    	                thisPart.setImagePath("3.jpg");
	    	                resultParts.add(thisPart);
	    	                }
	    	            return resultParts;

			} catch (SQLException ex) {
		          System.out.println("Error in search() -->" + ex.getMessage());
		    } finally {
		          Database.close(con);
		    }
			return null;
		}
    	 /* first try to split. if 1 word then:
    	  * select * where string = localpart.pcode or localpart.stripped or 
    	  * comptetitorpart.pcode or comptetitorpart.stripped or 
    	  * (%string% like localpart.description or localpart.itemname 
    	  * 
    	  * if more than 1 word, then it s keyword search:
    	  * %str1% like localpart.description or localpart.itemname OR 
    	  * %str2% like localpart.description or localpart.itemname
    	  * %str3% ...
    	  * 
    	  */
        
    	 return null;
    }
     
     public static ArrayList<Part> searchExact(String pCode, String groupId) {
    	 // if exact item, add to index 0 , else just add anywhere
    	 ArrayList<Part> parts = new ArrayList<>();
    	 Connection con = null;
         PreparedStatement ps = null;
         Part thisPart;
    	 try {
    		 con = Database.getConnection();
	            ps = con.prepareStatement("select * from localPart where pCode=? OR groupId=?");
	            ps.setString(1, pCode);
	            ps.setString(2, groupId);
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	            	if (rs.getString("pCode").equals(pCode)) {// if exact item
		            	thisPart = new Part();
		                thisPart.setpCode(rs.getString("pCode"));
		                thisPart.setStPCode(rs.getString("stPCode"));
		                thisPart.setDescription(rs.getString("description"));
		                thisPart.setPartName(rs.getString("name"));
		                thisPart.setPrice(Double.valueOf(rs.getString("price")));
		                thisPart.setGroupId(rs.getString("groupId"));
		                thisPart.setConfigurable(Boolean.valueOf(rs.getString("isConfigurable")));
		                parts.add(0, thisPart);
					} else {									// else if related item
		            	thisPart = new Part();
		                thisPart.setpCode(rs.getString("pCode"));
		                thisPart.setStPCode(rs.getString("stPCode"));
		                thisPart.setDescription(rs.getString("description"));
		                thisPart.setPartName(rs.getString("name"));
		                thisPart.setPrice(Double.valueOf(rs.getString("price")));
		                thisPart.setGroupId(rs.getString("groupId"));
		                thisPart.setConfigurable(Boolean.valueOf(rs.getString("isConfigurable")));
		                parts.add(thisPart);
					}   
	            }
	            return parts;
	            
    	 } catch (SQLException ex) {
	          System.out.println("Error in search() -->" + ex.getMessage());
	    } finally {
	          Database.close(con);
	    }
    	return null;
     }
}