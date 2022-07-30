package hotel.management.system;
import Classes.AddEmp;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import static jdk.nashorn.internal.ir.debug.ObjectSizeCalculator.getObjectSize;

public class EmployeeInfo extends JFrame implements ActionListener {

    // declared objects globally which are needed outside of constructor
    JButton back_button;
    JButton back;
     JButton check_button;
     JTextArea result;
      Socket socket = null;
    ObjectInputStream in = null;
    ObjectOutputStream out = null;
    EmployeeInfo(String who){
        Color blue = new Color(158,177,255);
        // size and location of bounding box
        setBounds(150,100,1250,700);
setLocationRelativeTo(null);
        // heading and its properties
        JLabel heading = new JLabel("EMPLOYEE INFO");
        heading.setForeground(new Color(204,246,221));
        heading.setFont(new Font("monospaced",Font.BOLD, 40));
        heading.setBounds(450,15,350,35);
        add(heading);

        // all labels declared which are used below
        JLabel name, age, gender, job, salary, phone, aadhar, email;

        // name
        name = new JLabel("NAME");
        name.setFont(new Font("Tahoma",Font.BOLD, 15));
        name.setForeground(Color.WHITE);
        name.setBounds(50,85,70,30);
        add(name);

        // age
        age = new JLabel("AGE");
        age.setFont(new Font("Tahoma",Font.BOLD, 15));
        age.setForeground(Color.WHITE);
        age.setBounds(220,85,70,30);
        add(age);

        // gender
        gender = new JLabel("GENDER");
        gender.setFont(new Font("Tahoma",Font.BOLD, 15));
        gender.setForeground(Color.WHITE);
        gender.setBounds(360,85,70,30);
        add(gender);

        // job
        job = new JLabel("JOB");
        job.setFont(new Font("Tahoma",Font.BOLD, 15));
        job.setForeground(Color.WHITE);
        job.setBounds(525,85,70,30);
        add(job);

        // salary
        salary = new JLabel("SALARY ");
        salary.setFont(new Font("Tahoma",Font.BOLD, 15));
        salary.setForeground(Color.WHITE);
        salary.setBounds(675,85,70,30);
        add(salary);

        // phone no.
        phone = new JLabel("PHONE NO.");
        phone.setFont(new Font("Tahoma",Font.BOLD, 15));
        phone.setForeground(Color.WHITE);
        phone.setBounds(815,85,100,30);
        add(phone);

        // aadhar
        aadhar = new JLabel("Address");
        aadhar.setFont(new Font("Tahoma",Font.BOLD, 15));
        aadhar.setForeground(Color.WHITE);
        aadhar.setBounds(975,85,70,30);
        add(aadhar);

        // email id
        email = new JLabel("EMAIL ID");
        email.setFont(new Font("Tahoma",Font.BOLD, 15));
        email.setForeground(Color.WHITE);
        email.setBounds(1125,85,100,30);
        add(email);

        // table and its properties
        JTable table = new JTable();
        table.setBackground(new Color(32,32,32));
        table.setForeground(Color.WHITE);
        table.setFont(new Font("arial", Font.PLAIN, 15));
        table.setRowHeight(20);
        table.setBounds(0,130,1250,400);
        add(table);
        result=new JTextArea(100,100);

        // check button
         check_button = new JButton("CHECK");
        check_button.setForeground(Color.WHITE);
        check_button.setBackground(blue);
        check_button.setFont(new Font("times new roman", Font.PLAIN, 20));
        check_button.addActionListener(this);
        check_button.setBounds(475,600,115,30);
        add(check_button);
        
        // back button
        if(who.equals("Recep")){
            back_button = new JButton("BACK");
        back_button.setForeground(Color.BLACK);
        back_button.setBackground(blue);
        back_button.setFont(new Font("times new roman", Font.PLAIN, 20));
        back_button.addActionListener(this);
        back_button.setBounds(750,600,100,30);
        add(back_button);
        }
        else{
        back = new JButton("BACK");
        back.setForeground(Color.BLACK);
        back.setBackground(blue);
        back.setFont(new Font("times new roman", Font.PLAIN, 20));
        back.addActionListener(this);
        back.setBounds(750,600,100,30);
        add(back);
        }
        
        

//        // fetching employee information from database
//        DatabaseConnection connect = new DatabaseConnection();
//        // query for all employee info
//        String query = "SELECT * FROM employee";
//        try {
//            ResultSet result = connect.statement.executeQuery(query);
//            // convert result to table
//         table.setModel(DbUtils.resultSetToTableModel(result));
//        } catch (Exception e) {
//            System.out.println(e);
//        }

        // dialog box background color
        getContentPane().setBackground(new Color(32,32,32));

        setLayout(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == check_button) {
            try {
                // all info took in separate variable
                socket = new Socket("192.168.43.228", 5000);
                 out = new ObjectOutputStream(socket.getOutputStream());
              in = new ObjectInputStream(socket.getInputStream());
              out.writeUTF("checkEmpl");
              Object object=in.readObject();
              AddEmp emp=(AddEmp)object;
              result.setText("");
              int i=0;
              while(i<getObjectSize(emp)){
                  String s=emp.name;
                  result.append(s+"\t\t");
                  s=emp.age;
                  result.append(s+"\t\t");
                   s=emp.gender;
                  result.append(s+"\t\t");
                  s=emp.job;
                  result.append(s+"\t\t");
                   s=emp.salary;
                  result.append(s+"\t\t");
                   s=emp.phone;
                  result.append(s+"\t\t");
                  s=emp.aadhar;
                  result.append(s+"\t\t");
                  s=emp.email;
                  result.append(s+"\n");
                  i++;
              }
              
            } catch (IOException ex) {
                Logger.getLogger(CustomerInfo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CustomerInfo.class.getName()).log(Level.SEVERE, null, ex);
            }
      
            
        } else if (ae.getSource() == back_button) {
            // get back to reception on clicking back button
            new Reception().setVisible(true);
            this.setVisible(false);
        }
        else if (ae.getSource() == back) {
            // get back to reception on clicking back button
            new Dashboard().setVisible(true);
            this.setVisible(false);
        }
    }
//
//    public static void main(String[] args) {
//        new EmployeeInfo().setVisible(true);
//    }
}
