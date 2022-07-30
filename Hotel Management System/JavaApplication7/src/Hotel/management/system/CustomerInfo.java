package hotel.management.system;

import Classes.Cust;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import static jdk.nashorn.internal.ir.debug.ObjectSizeCalculator.getObjectSize;

public class CustomerInfo extends JFrame implements ActionListener{

    // declared objects globally which are needed outside of constructor
    JButton check_button, back_button;
    JTable table;
    JCheckBox foreigner;
     JTextArea result;
      Socket socket = null;
    ObjectInputStream in = null;
    ObjectOutputStream out = null;

    CustomerInfo(String who){

        // size and location of bounding box
        setBounds(160,85,1250,700);
        setLocationRelativeTo(null);
        
         Color blue = new Color(158,177,255);
        // heading and its properties
        JLabel heading = new JLabel("CUSTOMER INFO");
        heading.setForeground(new Color(204,246,221));
        heading.setFont(new Font("monospaced",Font.BOLD, 40));
        heading.setBounds(450,15,350,35);
        add(heading);


        // all labels declared which are used below
        JLabel name, id, gender, phone, country, room, deposit, check_status;

        // name
        name = new JLabel("NAME");
        name.setFont(new Font("Tahoma",Font.BOLD, 15));
        name.setForeground(Color.WHITE);
        name.setBounds(50,115,70,30);
        add(name);

        // phone no
        phone = new JLabel("PHONE NO.");
        phone.setFont(new Font("Tahoma",Font.BOLD, 15));
        phone.setForeground(Color.WHITE);
        phone.setBounds(180,115,100,30);
        add(phone);

        // gender
        gender = new JLabel("GENDER");
        gender.setFont(new Font("Tahoma",Font.BOLD, 15));
        gender.setForeground(Color.WHITE);
        gender.setBounds(360,115,70,30);
        add(gender);

        // id
        id = new JLabel("ID");
        id.setFont(new Font("Tahoma",Font.BOLD, 15));
        id.setForeground(Color.WHITE);
        id.setBounds(525,115,70,30);
        add(id);

        // country
        country = new JLabel("COUNTRY");
        country.setFont(new Font("Tahoma",Font.BOLD, 15));
        country.setForeground(Color.WHITE);
        country.setBounds(650,115,100,30);
        add(country);

        // room no
        room = new JLabel("ROOM NO.");
        room.setFont(new Font("Tahoma",Font.BOLD, 15));
        room.setForeground(Color.WHITE);
        room.setBounds(815,115,100,30);
        add(room);

        // deposit
        deposit = new JLabel("DEPOSIT");
        deposit.setFont(new Font("Tahoma",Font.BOLD, 15));
        deposit.setForeground(Color.WHITE);
        deposit.setBounds(975,115,70,30);
        add(deposit);

        // check status
        check_status = new JLabel("CHECK STATUS");
        check_status.setFont(new Font("Tahoma",Font.BOLD, 15));
        check_status.setForeground(Color.WHITE);
        check_status.setBounds(1100,115,125,30);
        add(check_status);

        // table and its properties
        table = new JTable();
        table.setBackground(new Color(32,32,32));
        table.setForeground(Color.WHITE);
        table.setFont(new Font("arial", Font.PLAIN, 15));
        table.setRowHeight(20);
        table.setBounds(0,160,1250,400);
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
            back_button = new JButton("BACK.");
        }
        else if(who.equals("Admin")){
            back_button = new JButton("BACK");
        }
        back_button.setForeground(Color.WHITE);
        back_button.setBackground(blue);
        back_button.setFont(new Font("times new roman", Font.PLAIN, 20));
        back_button.addActionListener(this);
        back_button.setBounds(625,600,100,30);
        add(back_button);

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
               out.writeUTF("checkCust");
              Object object=in.readObject();
             
              Cust cust=(Cust)object;
              //table.setModel(DbUtils.ObjectToTableModel(cust));
              result.setText("");
              int i=0;
              while(i<getObjectSize(cust)){
                  String s=cust.name;
                  result.append(s+"\t\t");
                  s=cust.phone;
                  result.append(s+"\t\t");
                   s=cust.gender;
                  result.append(s+"\t\t");
                  s=cust.id;
                  result.append(s+"\t\t");
                   s=cust.country;
                  result.append(s+"\t\t");
                   s=cust.room_no;
                  result.append(s+"\t\t");
                  s=cust.deposit;
                  result.append(s+"\t\t");
                  s=cust.check_status;
                  result.append(s+"\n");
                  i++;
              }
              
            } catch (IOException ex) {
                Logger.getLogger(CustomerInfo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CustomerInfo.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            
        } else if (ae.getSource() == back_button) {
            if(ae.getActionCommand().equals("BACK.")){
                this.setVisible(false);
            new Reception().setVisible(true);
            }
            else{
                this.setVisible(false);
            new Dashboard().setVisible(true);
            }
        }
    }

//    public static void main(String[] args) {
//        new CustomerInfo().setVisible(true);
//    }
}
