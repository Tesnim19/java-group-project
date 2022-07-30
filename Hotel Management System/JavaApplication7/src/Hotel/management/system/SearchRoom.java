package hotel.management.system;
import Classes.AddRoom;
import Classes.Cust;
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

public class SearchRoom extends JFrame implements ActionListener {

    // declared objects globally which are needed outside of constructor
    JButton check, back;
    ButtonGroup bed_type_group;
    JTable rooms_table;
    JCheckBox available;
    JTextArea result;
      Socket socket = null;
    ObjectInputStream in = null;
    ObjectOutputStream out = null;
    SearchRoom(String who){
        
        Color blue = new Color(158,177,255);
        // size and location of bounding box
        setBounds(275,125,1000,600);

        // heading and its properties
        JLabel heading = new JLabel("ROOM Info");
        heading.setForeground(new Color(204,246,221));
        heading.setFont(new Font("monospaced",Font.BOLD, 40));
        heading.setBounds(345,55,300,35);
        add(heading);

        // table headings
        JLabel room_no = new JLabel("ROOM NO.");
        room_no.setFont(new Font("Tahoma",Font.BOLD, 15));
        room_no.setForeground(Color.WHITE);
        room_no.setBounds(65,140,150,20);
        add(room_no);

        JLabel availability_status = new JLabel("AVAILABILITY");
        availability_status.setFont(new Font("Tahoma",Font.BOLD, 15));
        availability_status.setForeground(Color.WHITE);
        availability_status.setBounds(235,140,150,20);
        add(availability_status);

        JLabel cleaning_status = new JLabel("CLEANING");
        cleaning_status.setFont(new Font("Tahoma",Font.BOLD, 15));
        cleaning_status.setForeground(Color.WHITE);
        cleaning_status.setBounds(450,140,150,20);
        add(cleaning_status);

        JLabel bed_type = new JLabel("BED TYPE");
        bed_type.setFont(new Font("Tahoma",Font.BOLD, 15));
        bed_type.setForeground(Color.WHITE);
        bed_type.setBounds(660,140,150,20);
        add(bed_type);

        JLabel price = new JLabel("PRICE");
        price.setFont(new Font("Tahoma",Font.BOLD, 15));
        price.setForeground(Color.WHITE);
        price.setBounds(860,140,150,20);
        add(price);

        // table and its properties
        rooms_table = new JTable();
        rooms_table.setBackground(new Color(32,32,32));
        rooms_table.setForeground(Color.WHITE);
        rooms_table.setFont(new Font("arial", Font.PLAIN, 15));
        rooms_table.setRowHeight(20);
        rooms_table.setBounds(0,175,1000,300);
        add(rooms_table);

        // check button and its properties
        result=new JTextArea(100,100);
        check = new JButton("CHECK");
        check.setForeground(Color.BLACK);
        check.setBackground(blue);
        check.setFont(new Font("times new roman", Font.PLAIN, 20));
        check.addActionListener(this);
        check.setBounds(360,500,115,30);
        add(check);

        // back button and its properties
        if(who.equals("Recep")){
             back = new JButton("BACK.");
        }
        else if(who.equals("Admin")){
            back = new JButton("BACK");
        }
        back.setForeground(Color.BLACK);
        back.setBackground(blue);
        back.setFont(new Font("times new roman", Font.PLAIN, 20));
        back.addActionListener(this);
        back.setBounds(490,500,115,30);
        add(back);

        // dialog box background
        getContentPane().setBackground(new Color(32,32,32));

        setLayout(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == check){
            try {
                // all info took in separate variable
                socket = new Socket("192.168.43.228", 5000);
                 out = new ObjectOutputStream(socket.getOutputStream());
              in = new ObjectInputStream(socket.getInputStream());
               out.writeUTF("roomInfo");
              Object object=in.readObject();
             
              AddRoom room=(AddRoom)object;
              //table.setModel(DbUtils.ObjectToTableModel(cust));
              result.setText("");
              int i=0;
              while(i<getObjectSize(room)){
                  
                  String s=room.room_no;
                  result.append(s+"\t\t");
                   s=room.availability;
                  result.append(s+"\t\t");
                  s=room.cleaning_status;
                  result.append(s+"\t\t");
                   s=room.bed_type;
                  result.append(s+"\t\t");
                   s=room.price;
                  result.append(s+"\n");
                  
                  i++;
              }
            } catch (IOException ex) {
                Logger.getLogger(SearchRoom.class.getName()).log(Level.SEVERE, null, ex);
             
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CustomerInfo.class.getName()).log(Level.SEVERE, null, ex);
            }
           
           
               
            
        }else if (ae.getSource() == back){
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
//        new SearchRoom(rec).setVisible(true);
//    }
}
