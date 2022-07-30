package hotel.management.system;
import Classes.Loger;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Login extends JFrame implements ActionListener{

    // all objects declared globally in class to use in methods
    JButton login_button, cancel_button;
    JTextField un_field;
    JPasswordField psd_field;
     Socket socket = null;
    ObjectInputStream in = null;
    ObjectOutputStream out = null;
    public Login(){

        // size and location of bounding box
       // setExtendedState(JFrame.MAXIMIZED_BOTH);
setBounds(375,140,800,550);
setLocationRelativeTo(null);
        // Inserted image and changed its dimension and gave location in dialog box
        ImageIcon login_image = new ImageIcon(ClassLoader.getSystemResource("images/login.jpeg"));
        Image img = login_image.getImage().getScaledInstance(200,200, Image.SCALE_DEFAULT);
        ImageIcon new_login_image = new ImageIcon(img);
        JLabel login_image_label = new JLabel(new_login_image);
        login_image_label.setBounds(250,80,200,200);
        add(login_image_label);

        // Username label and its size and location
        JLabel un_label = new JLabel("Username : ");
        un_label.setForeground(Color.WHITE);
        un_label.setBounds(200,330,100,30);
        un_label.setFont(new Font("serif", Font.PLAIN,15));
        add(un_label);

        // Password label and its size and location
        JLabel psd_label = new JLabel("Password : ");
        psd_label.setForeground(Color.WHITE);
        psd_label.setFont(new Font("serif", Font.PLAIN,15));
        psd_label.setBounds(200,380,100,30);
        add(psd_label);

        // Username field and its size and location
        un_field = new JTextField();
        un_field.setBounds(265,330,150,30);
        add(un_field);

        // Password field and its size and location
        psd_field = new JPasswordField();
        psd_field.setBounds(265,380,150,30);
        add(psd_field);

        // Login button and its properties, size and location
        login_button = new JButton("Login");
        login_button.setBounds(265,440,100,30);
        login_button.setBackground(Color.GREEN);
        login_button.setForeground(Color.BLACK);
        login_button.setFont(new Font("serif", Font.BOLD,20));
        login_button.addActionListener(this);
        add(login_button);

        // Cancel button and its properties, size and location
        cancel_button = new JButton("Cancel");
        cancel_button.setBounds(380,440,100,30);
        cancel_button.setBackground(Color.RED);
        cancel_button.setForeground(Color.WHITE);
        cancel_button.setFont(new Font("serif", Font.BOLD,20));
        cancel_button.addActionListener(this);
        add(cancel_button);

        // Change dialog box background color
        getContentPane().setBackground(new Color(32,32,32));
setResizable(true);
        setLayout(null);
        setVisible(true);
    }

    // action function for login and cancel buttons
    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == login_button){
       
            DatabaseConnection connect = new DatabaseConnection();
           
            try {
                 socket = new Socket("192.168.43.228", 5000);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            
                // Execute the query
                Loger log=new Loger();
             log.username = un_field.getText();
             log.Password = psd_field.getText();
              out.writeUTF("login");
            out.writeObject(log);
            String st=in.readUTF();
            
                if (st.equals("admin")){
                    
            new Dashboard().setVisible(true);
            setVisible(false); 
        
                }
            
                else if (st.equals("manager") ){
                   
                  new Reception ().setVisible(true);
                    setVisible(false);
                   
                }
                else {
                    // pop up message
                    JOptionPane.showMessageDialog(null, "Invalid username and password !!!", "WARNING", JOptionPane.WARNING_MESSAGE);
                }
              
                
            }catch (Exception e){
                System.out.println(e);
            }
        }
        // exit the application
        else if (ae.getSource() == cancel_button){
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Login();
    }

}
