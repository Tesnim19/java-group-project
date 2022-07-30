package hotel.management.system;
import Classes.AddRoom;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CheckOut extends JFrame implements ActionListener {

    // declared objects globally which are needed outside of constructor
    JComboBox rooms_combobox;
    JButton submit, back;
    Socket socket = null;
    ObjectInputStream in = null;
    ObjectOutputStream out = null;

    CheckOut(){
        Color blue = new Color(158,177,255);
        // size and location of bounding box
        setBounds(520,250,500,300);
        setLocationRelativeTo(null);
        // heading label
        JLabel heading = new JLabel("CHECK OUT");
        heading.setForeground(new Color(204,246,221));
        heading.setFont(new Font("monospaced",Font.BOLD, 40));
        heading.setBounds(125,5,500,50);
        add(heading);

        
        // allocated room no. label
        JLabel room_label = new JLabel("Occupied Room No. : ");
        room_label.setFont(new Font("Tahoma",Font.PLAIN, 15));
        room_label.setForeground(Color.WHITE);
        room_label.setBounds(20,75,150,20);
        add(room_label);

        // created array list of string data type
        ArrayList<String> rooms_list = new ArrayList<>();

        try {
            // query for available rooms
            DatabaseConnection connect = new DatabaseConnection();
            String query = "SELECT * FROM room WHERE availability = 'occupied'";
            ResultSet result = connect.statement.executeQuery(query);
            while (result.next()){
                // added each available room in array list
                rooms_list.add(result.getString("room_no"));
            }
        }catch (Exception e){
            System.out.println(e);
        }

        // converting available rooms arraylist to array
        String[] available_rooms = rooms_list.toArray(new String[rooms_list.size()]);
        rooms_combobox = new JComboBox(available_rooms);
        String room_no = (String)rooms_combobox.getSelectedItem();
        rooms_combobox.setBackground(Color.WHITE);
        rooms_combobox.setBounds(170,75,100,20);
        add(rooms_combobox);

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

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit){
            try {
                socket = new Socket("192.168.43.228", 5000);
                out = new ObjectOutputStream(socket.getOutputStream());
                in = new ObjectInputStream(socket.getInputStream());
                
                AddRoom add = new AddRoom();
                
                add.room_no = (String)rooms_combobox.getSelectedItem();
                if (add.room_no == null){
                    String message = "No Room Selected !!!";
                    JOptionPane.showMessageDialog(null, message, "WARNING", JOptionPane.WARNING_MESSAGE);
                }
                else{
                    out.writeUTF("Check");
                    out.writeObject(add);
                    
                     // popup message
                        JOptionPane.showMessageDialog(null, "Checked Out!");
                        this.setVisible(false);
                }  
            
   
            } catch (IOException ex) {
                Logger.getLogger(CheckOut.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if (ae.getSource() == back){
            this.setVisible(false);
            new Reception().setVisible(true);
        }
    }

    public static void main(String[] args) {
        new CheckOut().setVisible(true);
    }
}