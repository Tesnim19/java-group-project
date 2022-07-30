package hotel.management.system;
import Classes.AddRoom;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddRooms extends JFrame implements ActionListener {

    // declared objects globally which are needed outside of constructor
    JTextField room_no_field, price_field;
    ButtonGroup availability_group, cleaning_status_group, bed_type_group;
      Socket socket = null;
    ObjectInputStream in = null;
    ObjectOutputStream out = null;
    AddRooms(){
        
        Color blue = new Color(158,177,255);
        // size and location of bounding box
        setBounds(375,240,800,375);
        setLocationRelativeTo(null);
        // heading and its properties
        JLabel heading = new JLabel("ADD ROOMS");
        heading.setForeground(new Color(204,246,221));
        heading.setFont(new Font("monospaced",Font.BOLD, 40));
        heading.setBounds(270,7,250,35);
        add(heading);

        // room number label
        JLabel room_no_label = new JLabel("Room No. : ");
        room_no_label.setFont(new Font("Tahoma",Font.PLAIN, 15));
        room_no_label.setForeground(Color.WHITE);
        room_no_label.setBounds(220,70,100,20);
        add(room_no_label);

        // room number field
        room_no_field = new JTextField();
        room_no_field.setBounds(330,70,100,20);
        add(room_no_field);

        // availability label
        JLabel availability_label = new JLabel("Availability : ");
        availability_label.setFont(new Font("Tahoma",Font.PLAIN, 15));
        availability_label.setForeground(Color.WHITE);
        availability_label.setBounds(220,110,120,20);
        add(availability_label);

        // available button
        JRadioButton available = new JRadioButton("Available");
        available.setActionCommand("available");
        available.setFont(new Font("Tahoma",Font.PLAIN,14));
        available.setBackground(new Color(32,32,32));
        available.setForeground(Color.WHITE);
        available.setBounds(330,110,80,20);
        add(available);

        // occupied button
        JRadioButton occupied = new JRadioButton("Occupied");
        occupied.setActionCommand("occupied");
        occupied.setFont(new Font("Tahoma",Font.PLAIN,14));
        occupied.setBackground(new Color(32,32,32));
        occupied.setForeground(Color.WHITE);
        occupied.setBounds(430,110,120,20);
        add(occupied);

        // availability button group
        availability_group = new ButtonGroup();
        availability_group.add(available);
        availability_group.add(occupied);

        // cleaning status label
        JLabel cleaning_status_label = new JLabel("Cleaning Status : ");
        cleaning_status_label.setFont(new Font("Tahoma",Font.PLAIN, 15));
        cleaning_status_label.setForeground(Color.WHITE);
        cleaning_status_label.setBounds(220,150,200,20);
        add(cleaning_status_label);

        // cleaned button
        JRadioButton cleaned = new JRadioButton("Cleaned");
        cleaned.setActionCommand("cleaned");
        cleaned.setFont(new Font("Tahoma",Font.PLAIN,14));
        cleaned.setBackground(new Color(32,32,32));
        cleaned.setForeground(Color.WHITE);
        cleaned.setBounds(330,150,80,20);
        add(cleaned);

        // dirty button
        JRadioButton dirty = new JRadioButton("Dirty");
        dirty.setActionCommand("dirty");
        dirty.setFont(new Font("Tahoma",Font.PLAIN,14));
        dirty.setBackground(new Color(32,32,32));
        dirty.setForeground(Color.WHITE);
        dirty.setBounds(430,150,80,20);
        add(dirty);

        // availability button group
        cleaning_status_group = new ButtonGroup();
        cleaning_status_group.add(cleaned);
        cleaning_status_group.add(dirty);

        // bed type label
        JLabel bed_type_label = new JLabel("Bed Type : ");
        bed_type_label.setFont(new Font("Tahoma",Font.PLAIN, 15));
        bed_type_label.setForeground(Color.WHITE);
        bed_type_label.setBounds(220,190,80,20);
        add(bed_type_label);

        // single bed button
        JRadioButton single_bed = new JRadioButton("Single Bed");
        single_bed.setActionCommand("single");
        single_bed.setFont(new Font("Tahoma",Font.PLAIN,14));
        single_bed.setBackground(new Color(32,32,32));
        single_bed.setForeground(Color.WHITE);
        single_bed.setBounds(330,190,100,20);
        add(single_bed);

        // double bed button
        JRadioButton double_bed = new JRadioButton("Double Bed");
        double_bed.setActionCommand("double");
        double_bed.setFont(new Font("Tahoma",Font.PLAIN,14));
        double_bed.setBackground(new Color(32,32,32));
        double_bed.setForeground(Color.WHITE);
        double_bed.setBounds(430,190,120,20);
        add(double_bed);

        // availability button group
        bed_type_group = new ButtonGroup();
        bed_type_group.add(single_bed);
        bed_type_group.add(double_bed);

        // price label
        JLabel price_label = new JLabel("Price : ");
        price_label.setFont(new Font("Tahoma",Font.PLAIN, 15));
        price_label.setForeground(Color.WHITE);
        price_label.setBounds(220,230,100,20);
        add(price_label);

        // price field
        price_field = new JTextField();
        price_field.setBounds(330,230,50,20);
        add(price_field);

        // submit button
        JButton submit = new JButton("SUBMIT");
        submit.setForeground(Color.BLACK);
        submit.setBackground(blue);
        submit.setFont(new Font("times new roman", Font.PLAIN, 20));
        submit.addActionListener(this);
        submit.setBounds(315,280,115,30);
        add(submit);
        getContentPane().setBackground(new Color(32,32,32));
        setLayout(null);
        setVisible(true);
        setResizable(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            socket = new Socket("192.168.43.228", 5000);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());           
            AddRoom add = new AddRoom();
            System.out.println("connected to server");
            
            // all info took in separate variable
            add.room_no = room_no_field.getText();
            add.availability = availability_group.getSelection().getActionCommand();
            add.cleaning_status = cleaning_status_group.getSelection().getActionCommand();
            add.bed_type = bed_type_group.getSelection().getActionCommand();
            add.price = price_field.getText();
            
            out.writeUTF("AddRoom");
            out.writeObject(add);
                
                JOptionPane.showMessageDialog(null, "Succesfully Inserted!");
        }catch (IOException ex){
            Logger.getLogger(AddRooms.class.getName()).log(Level.SEVERE, null,ex);
        }
    }

    public static void main(String[] args) {
        new AddRooms().setVisible(true);
    }
}
