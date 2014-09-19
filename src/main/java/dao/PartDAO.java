package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import model.Part;

  
public class PartDAO {    
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
         System.out.println("query: "+query);
    	 if (splitQuery.length == 1) {	// searching in compart table 
    		 if (query.length()<2) 
 				return null;
     		 if (query.contains(" ")) 
 				query = splitQuery[0];
 			
     	   		// run query on comPart.pcode  
      		try {

  	            con = Database.getConnection();
  	            ps = con.prepareStatement("select localPart.* from localPart, comPart where " +
  	            		"comPart.novoPCode=localPart.pCode " +
  	            		"AND (comPart.pCode=? OR comPart.stPCode=?)");
 	            ps.setString(1, query);
 	            ps.setString(2, query);
 	            ResultSet rs = ps.executeQuery();
 	            if (rs.next()) { // compart has some value. return part in a array which has only one part
 	            	thisPart = new Part();
 	                thisPart.setpCode(rs.getString("pCode"));
 	                thisPart.setStPCode(rs.getString("stPCode"));
 	                thisPart.setDescription(rs.getString("description"));
 	                thisPart.setPartName(rs.getString("name"));
 	                thisPart.setRetailPrice(Double.valueOf(rs.getString("retailPrice")));
 	                thisPart.setGroupId(rs.getString("groupId"));
 	                thisPart.setConfigurable(Boolean.valueOf(rs.getString("isConfigurable")));
 	                thisPart.setImagePath(rs.getString("imagePath"));
 	                thisPart.setT1Price(Double.valueOf(rs.getString("t1Price")));
 	                thisPart.setT2Price(Double.valueOf(rs.getString("t2Price")));
 	                thisPart.setT3Price(Double.valueOf(rs.getString("t3Price")));
 	                thisPart.setT4Price(Double.valueOf(rs.getString("t4Price")));
 	                thisPart.setT5Price(Double.valueOf(rs.getString("t5Price")));
 	                thisPart.setT6Price(Double.valueOf(rs.getString("t6Price")));
 	                thisPart.setT7Price(Double.valueOf(rs.getString("t7Price")));
 	                thisPart.setT8Price(Double.valueOf(rs.getString("t8Price")));
 	                thisPart.setT9Price(Double.valueOf(rs.getString("t9Price")));
 	                thisPart.setT10Price(Double.valueOf(rs.getString("t10Price")));
 	                thisPart.setLongDesc(rs.getString("longDesc"));
 	                thisPart.setUsage(rs.getString("partUsage"));
 	                thisPart.setFinish(rs.getString("finish"));
 	                thisPart.setGrade(rs.getString("grade"));
 	                thisPart.setUom(rs.getString("uom"));
 	                thisPart.setSterile(rs.getString("sterile"));
 	                thisPart.setSpecialty(rs.getString("specialty"));
 	                thisPart.setLeadTime((rs.getString("leadTime")));
 	                thisPart.setOrigin(rs.getString("origin"));
 	                thisPart.setManufacturer(rs.getString("manufacturer"));
 	                thisPart.setParent(rs.getString("isParent"));
 	                resultParts.add(thisPart);
 	                System.out.println("added from db comPart Table");
 	                return resultParts; // this array should only have 1 part object in it
 				} else { //compart didnt return anything. look in localPart pcodes
 					ps = con.prepareStatement("select localPart.* from localPart where " +
		 	            		"pCode=? OR stPCode=?");
			            ps.setString(1, query);
			            ps.setString(2, query);
			            rs = ps.executeQuery();
			            if (rs.next()) { // compart has some value. return part in a array which has only one part
			            	thisPart = new Part();
			                thisPart.setpCode(rs.getString("pCode"));
			                thisPart.setStPCode(rs.getString("stPCode"));
			                thisPart.setDescription(rs.getString("description"));
			                thisPart.setPartName(rs.getString("name"));
			                thisPart.setRetailPrice(Double.valueOf(rs.getString("retailPrice")));
			                thisPart.setGroupId(rs.getString("groupId"));
			                thisPart.setConfigurable(Boolean.valueOf(rs.getString("isConfigurable")));
			                thisPart.setImagePath(rs.getString("imagePath"));
			                thisPart.setT1Price(Double.valueOf(rs.getString("t1Price")));
			                thisPart.setT2Price(Double.valueOf(rs.getString("t2Price")));
			                thisPart.setT3Price(Double.valueOf(rs.getString("t3Price")));
			                thisPart.setT4Price(Double.valueOf(rs.getString("t4Price")));
			                thisPart.setT5Price(Double.valueOf(rs.getString("t5Price")));
			                thisPart.setT6Price(Double.valueOf(rs.getString("t6Price")));
			                thisPart.setT7Price(Double.valueOf(rs.getString("t7Price")));
			                thisPart.setT8Price(Double.valueOf(rs.getString("t8Price")));
			                thisPart.setT9Price(Double.valueOf(rs.getString("t9Price")));
			                thisPart.setT10Price(Double.valueOf(rs.getString("t10Price")));
			                thisPart.setLongDesc(rs.getString("longDesc"));
			                thisPart.setUsage(rs.getString("partUsage"));
			                thisPart.setFinish(rs.getString("finish"));
			                thisPart.setGrade(rs.getString("grade"));
			                thisPart.setUom(rs.getString("uom"));
			                thisPart.setSterile(rs.getString("sterile"));
			                thisPart.setSpecialty(rs.getString("specialty"));
			                thisPart.setLeadTime((rs.getString("leadTime")));
			                thisPart.setOrigin(rs.getString("origin"));
			                thisPart.setManufacturer(rs.getString("manufacturer"));
			                thisPart.setParent(rs.getString("isParent"));
			                resultParts.add(thisPart);
			                System.out.println("added from db localPart Table");
			                return resultParts; // this array should only have 1 part object in it
			            } else {      
					// compart didnt return anything. therefore call Solr
					System.out.println("searching in solr");
					return searchSolr(query);
			            }
 				}
 		 	            
      		} catch (Exception ex) {
  	            System.out.println("Error in search() -->" + ex.getMessage());
  	            return null;
  	        } finally {
  	            Database.close(con);
  	        } 
      		
		} else if (splitQuery.length >1) { // call solr
			return searchSolr(query);
		}
        
    	 return null;
    }
     private static ArrayList<Part> searchSolr(String userQuery){
         Part thisPart; 
         ArrayList<Part> resultParts = new ArrayList();
    	 String url = "http://localhost:8983/solr/";
		  HttpSolrServer server = new HttpSolrServer( url );
		  server.setMaxRetries(1); // defaults to 0.  > 1 not recommended.
		  server.setConnectionTimeout(5000); // 5 seconds to establish TCP
		  
		  server.setParser(new XMLResponseParser()); // binary parser is used by default		  
		  server.setSoTimeout(1000);  // socket read timeout
		  server.setDefaultMaxConnectionsPerHost(100);
		  server.setMaxTotalConnections(100);
		  server.setFollowRedirects(false);  // defaults to false  
		  server.setAllowCompression(true);
	  
		  SolrQuery query = new SolrQuery();
		  // work on the query to: look for keyword, words wrong typed,.. sort based on closeness to results
		  
		  query.setQuery(userQuery);
		  query.setStart(0);    
		  query.setRows(300);  
		  QueryResponse response;
		  SolrDocument doc;
		  try {
			  response = server.query(query);
			  SolrDocumentList results = response.getResults();
			  for (int i = 0; i < results.size(); ++i) {
				  thisPart = new Part();
				  doc = results.get(i);
				  System.out.println("object in solr: "+results.get(i));
					thisPart.setpCode(String.valueOf(doc.getFieldValue("id")));
					thisPart.setStPCode(String.valueOf(doc.getFieldValue("stPCode")));					
					thisPart.setPartName(String.valueOf(doc.getFieldValue("partName")));
					thisPart.setDescription(String.valueOf(doc.getFieldValue("description")));
					thisPart.setGroupId(String.valueOf(doc.getFieldValue("groupId")));
					thisPart.setImagePath(String.valueOf(doc.getFieldValue("imagePath")));
					thisPart.setRetailPrice((double) doc.getFieldValue("retailPrice"));
					thisPart.setConfigurable((boolean) doc.getFieldValue("isConfigurable"));
					thisPart.setParent((boolean) doc.getFieldValue("isParent"));
					thisPart.setT1Price((double) doc.getFieldValue("t1Price"));
					// these are null sometimes and cause error
					thisPart.setT2Price((double) doc.getFieldValue("t2Price"));
					thisPart.setT3Price((double) doc.getFieldValue("t3Price"));
					thisPart.setT4Price((double) doc.getFieldValue("t4Price"));
					thisPart.setT5Price((double) doc.getFieldValue("t5Price"));
					thisPart.setT6Price((double) doc.getFieldValue("t6Price"));
					thisPart.setT7Price((double) doc.getFieldValue("t7Price"));
					thisPart.setT8Price((double) doc.getFieldValue("t8Price"));
					thisPart.setT9Price((double) doc.getFieldValue("t9Price"));
					thisPart.setT10Price((double) doc.getFieldValue("t10Price"));
					thisPart.setLongDesc(String.valueOf(doc.getFieldValue("longDesc")));
					thisPart.setFinish(String.valueOf(doc.getFieldValue("finish")));
					thisPart.setGrade(String.valueOf(doc.getFieldValue("grade")));
					thisPart.setUom(String.valueOf(doc.getFieldValue("uom")));
					thisPart.setSterile(String.valueOf(doc.getFieldValue("sterile")));
					thisPart.setSpecialty(String.valueOf(doc.getFieldValue("specialty")));
					thisPart.setLeadTime(String.valueOf((doc.getFieldValue("leadTime"))));
					thisPart.setUsage(String.valueOf((doc.getFieldValue("partUsage"))));
					thisPart.setOrigin(String.valueOf(doc.getFieldValue("origin")));
					thisPart.setManufacturer(String.valueOf(doc.getFieldValue("manufacturer")));
					resultParts.add(thisPart);
			  }
		  } catch (SolrServerException e) {
				// TODO Auto-generated catch block
			  e.printStackTrace();
			  return resultParts;
		  }
		  return resultParts;
     }
     
     public static ArrayList<Part> selectPart(String pCode, String groupId) { // some fields maybe missing
    	 // if exact item, add to index 0 , else just add anywhere
    	 ArrayList<Part> parts = new ArrayList<>();
    	 Connection con = null;
         PreparedStatement ps = null;
         Part thisPart;
    	 try {
    		 con = Database.getConnection();
	            ps = con.prepareStatement("select * from localPart where groupId=?");
	            ps.setString(1, groupId);
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	            	if (!rs.getString("pCode").equals(pCode)&&(Integer.valueOf(rs.getString("isParent"))!= 1)) {
		            	thisPart = new Part();
		            	thisPart.setpCode(rs.getString("pCode"));
		                thisPart.setStPCode(rs.getString("stPCode"));
		                thisPart.setDescription(rs.getString("description"));
		                thisPart.setPartName(rs.getString("name"));
		                thisPart.setRetailPrice(Double.valueOf(rs.getString("retailPrice")));
		                thisPart.setGroupId(rs.getString("groupId"));
		                thisPart.setConfigurable(Boolean.valueOf(rs.getString("isConfigurable")));
		                thisPart.setImagePath(rs.getString("imagePath"));
		                thisPart.setT1Price(Double.valueOf(rs.getString("t1Price")));
		                thisPart.setT2Price(Double.valueOf(rs.getString("t2Price")));
		                thisPart.setT3Price(Double.valueOf(rs.getString("t3Price")));
		                thisPart.setT4Price(Double.valueOf(rs.getString("t4Price")));
		                thisPart.setT5Price(Double.valueOf(rs.getString("t5Price")));
		                thisPart.setT6Price(Double.valueOf(rs.getString("t6Price")));
		                thisPart.setT7Price(Double.valueOf(rs.getString("t7Price")));
		                thisPart.setT8Price(Double.valueOf(rs.getString("t8Price")));
		                thisPart.setT9Price(Double.valueOf(rs.getString("t9Price")));
		                thisPart.setT10Price(Double.valueOf(rs.getString("t10Price")));
		                thisPart.setLongDesc(rs.getString("longDesc"));
		                thisPart.setUsage(rs.getString("partUsage"));
		                thisPart.setFinish(rs.getString("finish"));
		                thisPart.setGrade(rs.getString("grade"));
		                thisPart.setUom(rs.getString("uom"));
		                thisPart.setSterile(rs.getString("sterile"));
		                thisPart.setSpecialty(rs.getString("specialty"));
		                thisPart.setLeadTime((rs.getString("leadTime")));
		                thisPart.setOrigin(rs.getString("origin"));
		                thisPart.setManufacturer(rs.getString("manufacturer"));
		                thisPart.setParent(rs.getString("isParent"));
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