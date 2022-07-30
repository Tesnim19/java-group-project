/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hotel.management.system;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author malik munier
 */
public class DelEmp extends JFrame implements ActionListener {
    
    JComboBox emp_combobox;
    JButton submit, back;
    Socket socket = null;
    ObjectInputStream in = null;
    ObjectOutputStream out = null;
    

    public DelEmp(){
        Color blue = new Color(158,177,255);
        // size and location of bounding box
        setBounds(520,250,500,300);
        setLocationRelativeTo(null);
        // heading label
        JLabel heading = new JLabel("REMOVE EMPLOYEE");
        heading.setForeground(new Color(204,246,221));
        heading.setFont(new Font("monospaced",Font.BOLD, 25));
        heading.setBounds(105,5,500,50);
        add(heading);

        
        // allocated room no. label
        JLabel emp_label = new JLabel("Employee Name : ");
        emp_label.setFont(new Font("Tahoma",Font.PLAIN, 15));
        emp_label.setForeground(Color.WHITE);
        emp_label.setBounds(20,75,150,20);
        add(emp_label);

        // created array list of string data type
        ArrayList<String> emp_list = new ArrayList<>();

        try {
            // query for available employees
            hotel.management.system.DatabaseConnection connect = new hotel.management.system.DatabaseConnection();
            String query = "SELECT * FROM employee ";
            ResultSet result = connect.statement.executeQuery(query);
            while (result.next()){
                // added each emp name in array list
                emp_list.add(result.getString("name"));
            }
        }catch (Exception e){
            System.out.println(e);
        }

        // converting employee names arraylist to array
        String[] emp = emp_list.toArray(new String[emp_list.size()]);
        emp_combobox = new JComboBox(emp);
        emp_combobox.setBackground(Color.WHITE);
        emp_combobox.setBounds(170,75,100,20);
        add(emp_combobox);

        // submit button
        submit = new JButton("SUBMIT");
        submit.setForeground(Color.BLACK);
        submit.setBackground(blue);
        submit.setFont(new Font("times new roman", Font.PLAIN, 20));
        submit.addActionListener(this);
        submit.setBounds(170,125,115,30);
        add(submit);

        // back  button
        back = new JButton("BACK");
        back.setForeground(Color.BLACK);
        back.setBackground(blue);
        back.setFont(new Font("times new roman", Font.PLAIN, 20));
        back.addActionListener(this);back.setBounds(170,175,115,30);
        add(back);

        // dialog box background color
        getContentPane().setBackground(new Color(32,32,32));

        setLayout(null);
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
       if (ae.getSource() == submit){
            try {
                socket = new Socket("192.168.43.228", 5000);
                out = new ObjectOutputStream(socket.getOutputStream());
                in = new ObjectInputStream(socket.getInputStream());
                
                
                String name = (String)emp_combobox.getSelectedItem();
                if (name == null){
                    String message = "No Employee Selected !!!";
                    JOptionPane.showMessageDialog(null, message, "WARNING", JOptionPane.WARNING_MESSAGE);
                }
                else{
                    out.writeUTF("delemp");
                    out.writeUTF(name);
  
                     // popup message
                        JOptionPane.showMessageDialog(null, "Employee Removed!");
                        this.setVisible(false);
                }  
            
   
            } catch (IOException ex) {
                Logger.getLogger(DelEmp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if (ae.getSource() == back){
            this.setVisible(false);
             new hotel.management.system.Dashboard().setVisible(true);
            
        }
    }
     public static void main(String[] args) {
        new DelEmp().setVisible(true);
    }
    
}
