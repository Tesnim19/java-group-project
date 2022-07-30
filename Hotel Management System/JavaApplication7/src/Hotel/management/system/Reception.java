package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Reception extends JFrame  implements ActionListener{

    // declared objects globally which are needed outside of constructor
    JButton new_customer, all_employee, customer_info, check_out, update_room_status, pickup_service, search, search_room, pickup_info;

    Reception(){
        // size and location of bounding box
        setBounds(370,175,800,520);

        // colors that used for button background
       
        Color blue = new Color(158,177,255);
     
        // heading and its properties
        JLabel heading = new JLabel("RECEPTION");
        heading.setForeground(new Color(204,246,221));
        heading.setFont(new Font("monospaced",Font.BOLD, 40));
        heading.setBounds(290,15,250,35);
        add(heading);

    
        // new customer button
        new_customer = new JButton("New Customer Form");
        new_customer.setBackground(blue);
        new_customer.setForeground(Color.BLACK);
        new_customer.setFont(new Font("serif", Font.BOLD, 20));
        new_customer.addActionListener(this);
        new_customer.setBounds(280,70,225,30);
        add(new_customer);

        // search room button
        search_room = new JButton("Search Room");
        search_room.setBackground(blue);
        search_room.setForeground(Color.BLACK);
        search_room.setFont(new Font("serif", Font.BOLD, 20));
        search_room.addActionListener(this);
        search_room.setBounds(280,120,225,30);
        add(search_room);

        // all employee info button
        all_employee = new JButton("Employee Info");
        all_employee.setBackground(blue);
        all_employee.setForeground(Color.BLACK);
        all_employee.addActionListener(this);
        all_employee.setFont(new Font("serif", Font.BOLD, 20));
        all_employee.setBounds(280,170,225,30);
        add(all_employee);

        // customer info button
        customer_info = new JButton("Customer Info");
        customer_info.setBackground(blue);
        customer_info.setForeground(Color.BLACK);
        customer_info.addActionListener(this);
        customer_info.setFont(new Font("serif", Font.BOLD, 20));
        customer_info.setBounds(280,220,225,30);
        add(customer_info);

        // check out button
        check_out = new JButton("Check Out");
        check_out.setBackground(blue);
        check_out.setForeground(Color.BLACK);
        check_out.setFont(new Font("serif", Font.BOLD, 20));
        check_out.addActionListener(this);
        check_out.setBounds(280,270,225,30);
        add(check_out);

        // update room status button
        update_room_status = new JButton("Update Room Status");
        update_room_status.setBackground(blue);
        update_room_status.setForeground(Color.BLACK);
        update_room_status.setFont(new Font("serif", Font.BOLD, 20));
        update_room_status.addActionListener(this);
      
        update_room_status.setBounds(280,320,225,30);
        add(update_room_status);

      
        // dialog box background
        getContentPane().setBackground(new Color(32,32,32));

        setLayout(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String rec="Recep";
        if (ae.getSource() == new_customer){
            // add new customer
             NewCustomer custom= new NewCustomer();
           
           Thread t1=new Thread((Runnable) custom);
          // custom.setVisible(true);
            this.setVisible(false);
            t1.start();
//            new NewCustomer().setVisible(true);
//            this.setVisible(false);
        }else if (ae.getSource() == search_room){
            // search room
            
            new SearchRoom(rec).setVisible(true);
            this.setVisible(false);
        }else if (ae.getSource() == all_employee){
            // all employee info dialog box
            new EmployeeInfo(rec).setVisible(true);
            this.setVisible(false);
        }else if (ae.getSource() == customer_info){
            // customer info dialog box
            new CustomerInfo(rec).setVisible(true);
            this.setVisible(false);
        }else if (ae.getSource() == check_out){
            // check out program
            new CheckOut().setVisible(true);
            this.setVisible(false);
        }else if (ae.getSource() == update_room_status){
            // update room status
            new UpdateRoomStatus().setVisible(true);
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Reception().setVisible(true);
    }
}
