package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Dashboard extends JFrame implements ActionListener{
    JButton item2;//add emp
    JButton item3;//add room
    //delete
    JButton delEmp;
    JButton delRom;
    //edit
    JButton edEmp;
    JButton edRom;
    //show records
    JButton CustInfo;
    JButton EmpInfo;
    JButton RoomInfo;
    
    
    public Dashboard(){
        Color blue = new Color(158,177,255);
        // maximise the dialog box
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        setBounds(370,175,1500,800);
        setLocationRelativeTo(null);
        // menu bar of dialog box
        JMenuBar menu_bar = new JMenuBar();
        menu_bar.setBackground(new Color(23,32,42));
        UIManager.put("Menu.font", new Font("Verdana", Font.BOLD, 15));
        setJMenuBar(menu_bar);

//        // first menu
//        JMenu menu1 = new JMenu("Hotel Management");
//        menu1.setForeground(new Color(171,235,198));
//        menu_bar.add(menu1);
//        // item of first menu
//        JMenuItem item1 = new JMenuItem("Reception");
//        // reception click event
//        item1.addActionListener(this);
//        menu1.add(item1);

        // second menu
//        JMenu menu2 = new JMenu("Admin");
//        menu2.setForeground(new Color(246,221,204));
        
         item2 = new JButton("Add Employee");
        item2.setForeground(Color.WHITE);
        item2.setBackground(Color.BLUE);
        item2.setFont(new Font("times new roman", Font.PLAIN, 20));
        item2.addActionListener(this);
        item2.setBounds(320,250,200,60);
        add(item2);
        
        delEmp = new JButton("Remove Employee");
        delEmp.setForeground(Color.WHITE);
        delEmp.setBackground(Color.BLUE);
        delEmp.setFont(new Font("times new roman", Font.PLAIN, 20));
        delEmp.addActionListener(this);
        delEmp.setBounds(550,250,200,60);
        add(delEmp);
        
        edEmp = new JButton("Edit Employee");
        edEmp.setForeground(Color.WHITE);
        edEmp.setBackground(Color.BLUE);
        edEmp.setFont(new Font("times new roman", Font.PLAIN, 20));
        edEmp.addActionListener(this);
        edEmp.setBounds(780,250,200,60);
        add(edEmp);
        
         item3 = new JButton("Add Room");
        item3.setForeground(Color.WHITE);
        item3.setBackground(Color.BLUE);
        item3.setFont(new Font("times new roman", Font.PLAIN, 20));
        item3.addActionListener(this);
        item3.setBounds(320,350,200,60);
        add(item3);
               
        delRom = new JButton("Remove Room");
        delRom.setForeground(Color.WHITE);
        delRom.setBackground(Color.BLUE);
        delRom.setFont(new Font("times new roman", Font.PLAIN, 20));
        delRom.addActionListener(this);
        delRom.setBounds(550,350,200,60);
        add(delRom);
        
        edRom = new JButton("Edit Room");
        edRom.setForeground(Color.WHITE);
        edRom.setBackground(Color.BLUE);
        edRom.setFont(new Font("times new roman", Font.PLAIN, 20));
        edRom.addActionListener(this);
        edRom.setBounds(780,350,200,60);
        add(edRom);
        
        CustInfo = new JButton("CustomerInfo");
        CustInfo.setForeground(Color.WHITE);
        CustInfo.setBackground(Color.BLUE);
        CustInfo.setFont(new Font("times new roman", Font.PLAIN, 20));
        CustInfo.addActionListener(this);
        CustInfo.setBounds(320,450,200,60);
        add(CustInfo);
        
        EmpInfo = new JButton("EmployeeInfo");
        EmpInfo.setForeground(Color.WHITE);
        EmpInfo.setBackground(Color.BLUE);
        EmpInfo.setFont(new Font("times new roman", Font.PLAIN, 20));
        EmpInfo.addActionListener(this);
        EmpInfo.setBounds(550,450,200,60);
        add(EmpInfo);
        
        RoomInfo = new JButton("Room Info");
        RoomInfo.setForeground(Color.WHITE);
        RoomInfo.setBackground(Color.BLUE);
        RoomInfo.setFont(new Font("times new roman", Font.PLAIN, 20));
        RoomInfo.addActionListener(this);
        RoomInfo.setBounds(780,450,200,60);
        add(RoomInfo);
        
//        menu_bar.add(menu2);
//        // items of second menu
//        JMenuItem item2 = new JMenuItem("Add Employee");
//        menu2.add(item2);
        // add employee click event
//        item2.addActionListener(this);
//        JMenuItem item3 = new JMenuItem("Add Room");
//        // add room click event
//        item3.addActionListener(this);
//        menu2.add(item3);
        // log out button
        JButton logout_button = new JButton("Log Out");
        logout_button.setForeground(Color.BLACK);
        logout_button.setBackground(new Color(255,0,0));
        logout_button.addActionListener(this);
        logout_button.setFont(new Font("verdana", Font.BOLD, 15));
        menu_bar.add(logout_button);

        // background image of dialog box
        ImageIcon dashboard_image = new ImageIcon(ClassLoader.getSystemResource("images/dashboard.jpg"));
        // resize image
        Image img = dashboard_image.getImage().getScaledInstance(1550,820, Image.SCALE_DEFAULT);
        ImageIcon new_dashboard_image = new ImageIcon(img);
        JLabel dashboard_image_lable = new JLabel(new_dashboard_image);
        dashboard_image_lable.setBounds(0,0,1550,820);
        add(dashboard_image_lable);

        // title on image
        JLabel title = new JLabel("Welcome to Admin Dashboard!!");
        title.setBounds(220,50,1500,200);
        title.setForeground(Color.BLACK);
        title.setFont(new Font("times new roman", Font.BOLD, 70));
        dashboard_image_lable.add(title);

        // exit program on closing dialog box
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // dialog box background color
        getContentPane().setBackground(new Color(23,32,42));

        setLayout(null);
        setVisible(true);
        setResizable(true);
    }

    public void actionPerformed(ActionEvent ae){
        String who ="Admin";
        if (ae.getSource()==item2){
            // add employee dialog box
            new AddEmployee().setVisible(true);
             //this.setVisible(false);
        }else if (ae.getSource()==item3) {
            // add room dialog box
            new AddRooms().setVisible(true);
        } else if (ae.getActionCommand().equals("Log Out")){
            // logout dashboard
            new Login().setVisible(true);
            this.setVisible(false);
        }
        else if (ae.getSource()==delEmp) {
            // del emp dialog box 
            new Hotel.management.system.DelEmp().setVisible(true);
            this.setVisible(false);
            
        }
        else if (ae.getSource()==delRom) {
            // del room dialog box
            new Hotel.management.system.DelRom().setVisible(true);
            this.setVisible(false);
        }
        else if (ae.getSource()==edEmp) {
            // edit emp dialog box
            new Hotel.management.system.EditEmp().setVisible(true);
            this.setVisible(false);
        }
        else if (ae.getSource()==edRom) {
            // edit room dialog box
            new Hotel.management.system.EditRom().setVisible(true);
            this.setVisible(false);
        }
        else if (ae.getSource()==CustInfo) {
            // cust info dialog box
            new CustomerInfo(who).setVisible(true);
            this.setVisible(false);
        }
         else if (ae.getSource()==EmpInfo) {
            // emp info dialog box
            new EmployeeInfo(who).setVisible(true);
            this.setVisible(false);
        }
        else if (ae.getSource()==RoomInfo) {
            // room info dialog box
            
            new SearchRoom(who).setVisible(true);
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Dashboard();
    }

}
