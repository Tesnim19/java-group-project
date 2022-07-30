package hotel.management.system;

import Classes.AddEmp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AddEmployee extends JFrame implements ActionListener {

    // declared objects globally which are needed outside of constructor
    JTextField name_field, age_field, salary_field, phone_field, aadhar_field, email_field;
    ButtonGroup gender_group;
    JComboBox job_combobox;
     Socket socket = null;
    ObjectInputStream in = null;
    ObjectOutputStream out = null;

    AddEmployee(){
        Color blue = new Color(158,177,255);
        // size and location of bounding box
        setBounds(375,140,800,550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    
        // heading and its properties
        JLabel heading = new JLabel("ADD EMPLOYEE");
        heading.setForeground(new Color(204,246,221));
        heading.setFont(new Font("monospaced",Font.BOLD, 40));
        heading.setBounds(250,10,300,35);
        add(heading);        

        // name label
        JLabel name_label = new JLabel("Name : ");
        name_label.setFont(new Font("Tahoma",Font.PLAIN, 15));
        name_label.setForeground(Color.WHITE);
        name_label.setBounds(220,70,60,20);
        add(name_label);

        // name field
        name_field = new JTextField();
        name_field.setBounds(300,70,200,20);
        add(name_field);
       
        // age label
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
        JButton submit = new JButton("SUBMIT");
        submit.setForeground(Color.BLACK);
        submit.setBackground(blue);
        submit.setFont(new Font("times new roman", Font.PLAIN, 20));
        submit.addActionListener(this);
        submit.setBounds(325,455,115,30);
        add(submit);

        getContentPane().setBackground(new Color(32,32,32));

        setLayout(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            // all info took in separate variable
            socket = new Socket("192.168.43.228", 5000);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            
            AddEmp add = new AddEmp();
            
            System.out.println("connected to server");
            add.name = name_field.getText();
            add.age = age_field.getText();
            add.gender =(String) gender_group.getSelection().getActionCommand();
            add.job = (String)job_combobox.getSelectedItem();
            add.salary = salary_field.getText();
            add.phone = phone_field.getText();
            add.aadhar = aadhar_field.getText();
            add.email = email_field.getText();
           
            
            out.writeUTF("AddEmp");
            out.writeObject(add);
           
                // Execute the query
                
                String message = in.readUTF();
               // if(message!=null){
                    // popup message
                JOptionPane.showMessageDialog(null, "Succesfully Inserted!");
                this.setVisible(false);
                  //}
//                else{
//                   JOptionPane.showMessageDialog(null, "Not inserted");
//                this.setVisible(false); 
//                }
//                
            
        }catch (IOException ex){
            Logger.getLogger(AddEmployee.class.getName()).log(Level.SEVERE, null,ex);
        }
    }

    public static void main(String[] args) {
        new AddEmployee().setVisible(true);
    }
}
