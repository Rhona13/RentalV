
package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;



public class DBConnector {
private Connection connect;
    
      public DBConnector(){
            try{
                connect = DriverManager.getConnection("jdbc:mysql://localhost:3307/renatal", "root", "");
            }catch(SQLException ex){
                    System.out.println("Can't connect to database: "+ex.getMessage());
            }
        }
      
      public Connection getConnection(){
          return connect;
      }
      
      //Function to retrieve data
        public ResultSet getData(String sql) throws SQLException{
            Statement stmt = connect.createStatement();
            ResultSet rst = stmt.executeQuery(sql);
            return rst;
        }
        
        //Function to save data
        public boolean insertData(String sql){
            try{
                PreparedStatement pst = connect.prepareStatement(sql);
                pst.executeUpdate();
                System.out.println("Inserted Successfully!");
                pst.close();
               return true;
            }catch(SQLException ex){
                System.out.println("Connection Error: "+ex);
               return false;
            }
        }
      
       public int updateData(String sql){
           int num = 0;
           try{  
               
              String query = sql;
              PreparedStatement pst = connect.prepareStatement(sql);
              int rowsUpdated = pst.executeUpdate();
              if(rowsUpdated > 0) {
                  System.out.println("Data Updated Successfully!");
                  num = 1;
              }else {
                  System.out.println("Data Updated Failed!");
                  num = 0;
              }
           }catch(SQLException ex) {
               ex.printStackTrace();
           }
             
           return num;
           
       }

}
   
