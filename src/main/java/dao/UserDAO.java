package dao;
import java.sql.*;

import model.User;
  
public class UserDAO {    
	/*
	 * login: it gets the userid and the password and checks it with the user table in mysql.
	 */
     public static boolean login(String email, String password) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = Database.getConnection();
            //simple statement with email && password from table user
            ps = con.prepareStatement(
                    "select email, password from user where email= ? and password= ? ");
            ps.setString(1, email);
            ps.setString(2, password);
  
            ResultSet rs = ps.executeQuery();
            if (rs.next()) // found
            {
                System.out.println(rs.getString("email"));
                return true;
            }
            else {
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Error in login() -->" + ex.getMessage());
            return false;
        } finally {
            Database.close(con);
        }
    }
     /*
      * getUser: this is a utility method that gets a certain user object from the

user table. the input is the string of email address and the output is a User object.
      input argument: email of the user
      output: the User object which has all of user's data
      */
     public static User  getUser(String email) {
         Connection con = null;
         PreparedStatement ps = null;
         User user = new User();
         try {
             con = Database.getConnection();
             ps = con.prepareStatement("select * from user where email= ?");
             ps.setString(1, email);
             ResultSet rs = ps.executeQuery();
             if (rs.next()) // found
             {
            	 user.setEmail(rs.getString("email"));
            	 user.setFirstName(rs.getString("FirstName"));
            	 user.setLastName(rs.getString("LastName"));
            	 user.setInstitution(rs.getString("Institution"));
            	 user.setAddress(rs.getString("Address"));
            	 user.setCity(rs.getString("City"));
            	 user.setDepartment(rs.getString("Department"));
            	 user.setGpo(rs.getString("GPO"));
            	 user.setOrgType(rs.getString("OrgType"));
            	 user.setPhone(rs.getInt("Phone"));
            	 user.setPriceTier(rs.getInt("PriceTier"));
            	 user.setTitle(rs.getString("Title"));            	 
             }
         } catch (Exception ex) {
             System.out.println("Error in getting User Object -->" + ex.getMessage());
             return null;
         } finally {
             Database.close(con);
         }
         return user;
     }
}