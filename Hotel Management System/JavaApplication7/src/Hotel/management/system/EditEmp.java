/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hotel.management.system;

import Classes.AddEmp;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author malik munier
 */
public class EditEmp  extends JFrame implements ActionListener {
     JComboBox emp_combobox;
    JButton submit, back;
    Socket socket = null;
    ObjectInputStream in = null;
    ObjectOutputStream out = null;
    JTextField name_field, age_field, salary_field, phone_field, aadhar_field, email_field;
    ButtonGroup gender_group;
    JComboBox job_combobox;
    

    public EditEmp(){
        Color blue = new Color(158,177,255);
        // size and location of bounding box
        setBounds(375,140,800,550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // heading label
        JLabel heading = new JLabel("EDIT EMPLOYEE");
        heading.setForeground(new Color(204,246,221));
        heading.setFont(new Font("monospaced",Font.BOLD, 40));
        heading.setBounds(250,10,600,35);
        add(heading); 

        
        // allocated room no. label
        JLabel emp_label = new JLabel("Employee Name : ");
        emp_label.setFont(new Font("Tahoma",Font.PLAIN, 15));
        emp_label.setForeground(Color.WHITE);
        emp_label.setBounds(170,70,150,20);
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
        emp_combobox.setBounds(300,70,200,20);
        add(emp_combobox);
        
        JLabel age_label = new JLabel("Age : ");
        age_label.setFont(new Font("Tahoma",Font.PLAIN, 15));
        age_label.setForeground(Color.WHITE);
        age_label.setBounds(220,110,60,20);
        add(age_label);

        // age field
        age_field = new JTextField();
        age_field.setBounds(300,110,200,20);
        add(age_field);

        // gender label
        JLabel gender_label = new JLabel("Gender : ");
        gender_label.setFont(new Font("Tahoma",Font.PLAIN, 15));
        gender_label.setForeground(Color.WHITE);
        gender_label.setBounds(220,150,80,20);
        add(gender_label);

        // gender radio buttons
        JRadioButton male = new JRadioButton("Male");
        male.setActionCommand("male");
        male.setFont(new Font("Tahoma",Font.PLAIN,14));
        male.setBackground(new Color(32,32,32));
        male.setForeground(Color.WHITE);
        male.setBounds(300,150,70,20);
        add(male);

        JRadioButton female = new JRadioButton("Female");
        female.setActionCommand("female");
        female.setFont(new Font("Tahoma",Font.PLAIN,14));
        female.setBackground(new Color(32,32,32));
        female.setForeground(Color.WHITE);
        female.setBounds(300,180,80,20);
        add(female);

        // gender radio buttons grouped
        gender_group = new ButtonGroup();
        gender_group.add(male);
        gender_group.add(female);


        // job label
        JLabel job_label = new JLabel("Job : ");
        job_label.setFont(new Font("Tahoma",Font.PLAIN, 15));
        job_label.setForeground(Color.WHITE);
        job_label.setBounds(220,250,70,20);
        add(job_label);

        // job list and combobox
        String jobs[] = {"Restaurant Manager", "Kitchen staff", "Head Chef", "Room Service", "Hotel Porter", "Front Desk Employee", "Waiter/Waitress", "Driver", "Housekeeping"};
        job_combobox = new JComboBox(jobs);
        String job = (String)job_combobox.getSelectedItem();
        job_combobox.setBackground(Color.WHITE);
        job_combobox.setBounds(300,250,200,20);
        add(job_combobox);

        // salary label
        JLabel salary_label = new JLabel("Salary : ");
        salary_label.setFont(new Font("Tahoma",Font.PLAIN, 15));
        salary_label.setForeground(Color.WHITE);
        salary_label.setBounds(220,290,70,20);
        add(salary_label);

        // salary field
        salary_field = new JTextField();
        salary_field.setBounds(300,290,200,20);
        add(salary_field);

        // phone label
        JLabel phone_label = new JLabel("Phone : ");
        phone_label.setFont(new Font("Tahoma",Font.PLAIN, 15));
        phone_label.setForeground(Color.WHITE);
        phone_label.setBounds(220,330,70,20);
        add(phone_label);

        // phone field
        phone_field = new JTextField();
        phone_field.setBounds(300,330,200,20);
        add(phone_field);
 
        // address label
        JLabel aadhar_label = new JLabel("Address : ");
        aadhar_label.setFont(new Font("Tahoma",Font.PLAIN, 15));
        aadhar_label.setForeground(Color.WHITE);
        aadhar_label.setBounds(220,370,80,20);
        add(aadhar_label);

        // address field
        aadhar_field = new JTextField();
        aadhar_field.setBounds(300,370,200,20);
        add(aadhar_field);
      
        // email label
        JLabel email_label = new JLabel("Email : ");
        email_label.setFont(new Font("Tahoma",Font.PLAIN, 15));
        email_label.setForeground(Color.WHITE);
        email_label.setBounds(220,410,70,20);
        add(email_label);

        // email field
        email_field = new JTextField();
        email_field.setBounds(300,410,200,20);
        add(email_field);
        
        // submit button
        submit = new JButton("SUBMIT");
        submit.setForeground(Color.BLACK);
        submit.setBackground(blue);
        submit.setFont(new Font("times new roman", Font.PLAIN, 20));
        submit.addActionListener(this);
        submit.setBounds(225,455,115,30);
        add(submit);

        // back  button
        back = new JButton("BACK");
        back.setForeground(Color.BLACK);
        back.setBackground(blue);
        back.setFont(new Font("times new roman", Font.PLAIN, 20));
        back.addActionListener(this);
        back.setBounds(425,455,115,30);
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
                     AddEmp edit = new AddEmp();
            
            System.out.println("connected to server");
            edit.name = name;
            edit.age = age_field.getText();
            edit.gender =(String) gender_group.getSelection().getActionCommand();
            edit.job = (String)job_combobox.getSelectedItem();
            edit.salary = salary_field.getText();
            edit.phone = phone_field.getText();
            edit.aadhar = aadhar_field.getText();
            edit.email = email_field.getText();
           
            
            out.writeUTF("EditEmp");
            out.writeObject(edit);
                    
  
                     // popup message
                        JOptionPane.showMessageDialog(null, "Employee Editted!");
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
        new EditEmp().setVisible(true);
    }
    
}
