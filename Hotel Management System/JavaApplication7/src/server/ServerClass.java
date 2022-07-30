
package server;
import Classes.AddEmp;
import Classes.AddRoom;
import Classes.Cust;
import Classes.Loger;
import hotel.management.system.DatabaseConnection;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author AA
 */
public class ServerClass {
    ServerSocket ss;
    

    int n = 0;
    
    DatabaseConnection connect = new DatabaseConnection();
    
    
    public ServerClass(){
        try {
            ss = new ServerSocket(5000);
            System.out.println("server is ready");
            
            while(true){
                Socket client = ss.accept();
                System.out.println("client connected succesfully");
                
                Handler handle = new Handler(client);
                handle.start();
                
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    class Handler extends Thread{
        
        ObjectInputStream in = null;
        ObjectOutputStream out = null;
        
        public Handler(Socket request){
            
            try {
                out = new ObjectOutputStream(request.getOutputStream());
                in = new ObjectInputStream(request.getInputStream());
            } catch (IOException ex) {
                Logger.getLogger(ServerClass.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
           
    public void run(){
        
       while(true){
            
            
            try {
                String str = in.readUTF();
                Object object = in.readObject();
                
                
                  if(str.equals("AddEmp")){
                      AddEmp addemp =(AddEmp)object;
                      // database connection object
            DatabaseConnection connect = new DatabaseConnection();
            // database query
            String query = "INSERT INTO employee values( '"+addemp.name+"', '"+addemp.age+"', '"+addemp.gender+"','"+addemp.job+"', '"+addemp.salary+"', '"+addemp.phone+"','"+addemp.aadhar+"', '"+addemp.email+"')";
            try {
                // Execute the query
                connect.statement.executeUpdate(query);
                String message = "New Employee Added !!!";
                out.writeUTF(message);
            }catch (Exception e){
                System.out.println(e);
            }
                  }
                  
                  else if(str.equals("AddRoom")){
                      AddRoom addro = (AddRoom)object;
                      DatabaseConnection connect = new DatabaseConnection();
                      
                       // database query
              String query = "INSERT INTO room values( '"+addro.room_no+"', '"+addro.availability+"', '"+addro.cleaning_status+"','"+addro.bed_type+"', '"+addro.price+"')";
                    try {
                        connect.statement.executeUpdate(query);
                    } catch (SQLException ex) {
                        Logger.getLogger(ServerClass.class.getName()).log(Level.SEVERE, null, ex);
                    }
                      
                  }
                  
                  else if(str.equals("AddCust")){
                      Cust addc = (Cust)object;
                      DatabaseConnection connect = new DatabaseConnection();
                     String query = "INSERT INTO customer values( '" + addc.name + "', '" + addc.phone + "', '" + addc.gender + "','" + addc.id + "', '" + addc.country + "', '" + addc.room_no + "','" + addc.deposit + "','check_in')";
                    try {
                        // Execute the query
                        connect.statement.executeUpdate(query);
                        // update availability status in room table
                         query = "UPDATE room SET availability = 'occupied' WHERE room_no = '" + addc.room_no + "'";
                        connect.statement.executeUpdate(query);
                        
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(ServerClass.class.getName()).log(Level.SEVERE, null, ex);
                    }
                     
                  }
                  else if(str.equals("Check")){
                      AddRoom addro = (AddRoom)object;
                       DatabaseConnection connect = new DatabaseConnection();
                       
                       String query1 = "UPDATE room SET availability = 'available' WHERE room_no = '" + addro.room_no + "'";
                    // Update check status in customer table
                    String query2 = "UPDATE customer SET check_status = 'check_out' WHERE room_no = '" + addro.room_no + "' and check_status = 'check_in'";
                    // Update clean status in room table
                    String query3 = "UPDATE room SET cleaning_status = 'dirty' WHERE room_no = '" + addro.room_no + "'";
                    try {
                        // Execute the query
                        connect.statement.executeUpdate(query1);
                        connect.statement.executeUpdate(query2);
                        connect.statement.executeUpdate(query3);
                        
                       
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                      
                  }
                  
                  else if(str.equals("Update")){
                       AddRoom addro = (AddRoom)object;
                      DatabaseConnection connect = new DatabaseConnection();
                    // database query
                    String query = "UPDATE room SET cleaning_status = '"+addro.cleaning_status+"' WHERE room_no = '"+addro.room_no+"'";
                    try {
                        // Execute the query
                        connect.statement.executeUpdate(query);             
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
                  
                   else if(str.equals("checkCust")){
                      
                      DatabaseConnection connection = new DatabaseConnection();
                    Cust cust=new Cust();
                  
           
                // query when only available is not selected
                String query = "SELECT * FROM customer";
                
                try {
                    ResultSet result = connection.statement.executeQuery(query);
                    cust.name=result.getString(1);
                     cust.phone=result.getString(2);
                      cust.gender=result.getString(3);
                       cust.id=result.getString(4);
                        cust.country=result.getString(5);
                         cust.room_no=result.getString(6);
                          cust.deposit=result.getString(7);
                           cust.check_status=result.getString(8);
                          out.writeObject(cust);
                    //table.setModel(DbUtils.resultSetToTableModel(result));
                } catch (Exception e) {
                    System.out.println(e);
                }
                  
                    
                       
                   }
                    else if(str.equals("checkEmpl")){
                      
                      DatabaseConnection connection = new DatabaseConnection();
                    AddEmp emp=new AddEmp();
                  
           
                // query when only available is not selected
                String query = "SELECT * FROM employee";
                
                try {
                    ResultSet result = connection.statement.executeQuery(query);
                    emp.name=result.getString(1);
                     emp.phone=result.getString(2);
                      emp.gender=result.getString(3);
                       emp.job=result.getString(4);
                        emp.salary=result.getString(5);
                         emp.phone=result.getString(6);
                          emp.aadhar=result.getString(7);
                           emp.email=result.getString(8);
                          out.writeObject(emp);
                  
                } catch (Exception e) {
                    System.out.println(e);
                }
                  
                    
                       
                   }
                  else if(str.equals("roomInfo")){
                      
                      DatabaseConnection connection = new DatabaseConnection();
                    AddRoom room=new AddRoom();
                // query when only available is not selected
                String query = "SELECT * FROM room";
                
                try {
                    ResultSet result = connection.statement.executeQuery(query);
                    room.room_no=result.getString(1);
                     room.availability=result.getString(2);
                      room.cleaning_status=result.getString(3);
                       room.bed_type=result.getString(4);
                        room.price=result.getString(5);
                         
                          out.writeObject(room);
                  
                } catch (Exception e) {
                    System.out.println(e);
                }
        
                   }
                  
                   else if(str.equals("login")){
                     Loger log=(Loger)object;
                      DatabaseConnection connection = new DatabaseConnection();
                // query when only available is not selected
                String query = "SELECT * FROM login WHERE username = '"+log.username+"' AND password = '"+log.Password+"'";
                
                try {
                    ResultSet result = connection.statement.executeQuery(query);
                   
               
                while(result.next()){
                if (result.getString(1).equals("admin") && result.getString(2).equals("admin123")){
                   out.writeUTF("admin");
                }
                else if (result.getString(1).equals("manager") && result.getString(2).equals("manager123")){
                    out.writeUTF("manager");
                } 
                  
             
                       
                   }}
                catch (Exception e){
                System.out.println(e);
            }
                   }
               else if(str.equals("delemp")){
                   String name = in.readUTF();
                   DatabaseConnection connect = new DatabaseConnection();
                       
                   String query = "DELETE FROM employee WHERE name = '"+name+"'";
                    try {
                        connect.statement.executeUpdate(query);
                    } catch (SQLException ex) {
                        Logger.getLogger(ServerClass.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   
               }
              else if(str.equals("delrom")){
                   String room_no = in.readUTF();
                   DatabaseConnection connect = new DatabaseConnection();
                       
                   String query = "DELETE FROM room WHERE room_no = '"+room_no+"'";
                    try {
                        connect.statement.executeUpdate(query);
                    } catch (SQLException ex) {
                        Logger.getLogger(ServerClass.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   
               }
              else if(str.equals("EditEmp")){
                      AddEmp addemp =(AddEmp)object;
                      DatabaseConnection connect = new DatabaseConnection();
               
    String query = "UPDATE employee SET age = '" + addemp.age + "' gender = '"+addemp.gender+"' job = '"+addemp.job+"' salary = '"+addemp.salary+"' phone = '"+addemp.phone+"' aadhar = '"+addemp.aadhar+"' email = '"+addemp.email+"'  WHERE name = '" + addemp.name + "' ";                    
                 
                  try {
                        connect.statement.executeUpdate(query);
                    } catch (SQLException ex) {
                        Logger.getLogger(ServerClass.class.getName()).log(Level.SEVERE, null, ex);
                    }
              }
                  
                   else if(str.equals("EditRom")){
                       AddRoom addro = (AddRoom)object;
                      DatabaseConnection connect = new DatabaseConnection();
        String query = "UPDATE room SET availability = '" + addro.availability + "' cleaning_status = '"+addro.cleaning_status+"' bed_type = '"+addro.bed_type+"' price = '"+addro.price+"' WHERE room_no = '" +addro.room_no+ "' ";                    
                     try {
                        connect.statement.executeUpdate(query);
                    } catch (SQLException ex) {
                        Logger.getLogger(ServerClass.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   }
            }catch (ClassNotFoundException | IOException ex) {
                Logger.getLogger(ServerClass.class.getName()).log(Level.SEVERE, null, ex);
            }
            
   
        }
    }
    
    
    }
    
     public static void main(String[ ] arg)
   {
	ServerClass server1=new ServerClass( );
      }
    
}
