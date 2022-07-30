package hotel.management.system;

import java.sql.*;

public class DatabaseConnection {

    // all objects declared globally in class to use in methods
   public Connection connection;
  public  Statement statement;

    public DatabaseConnection(){
        try {


             Class.forName("com.mysql.cj.jdbc.Driver");
            connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/HotelManagement","root","0612");
            statement = connection.createStatement();

        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new DatabaseConnection();
    }

}
