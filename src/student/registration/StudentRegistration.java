package student.registration;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class StudentRegistration extends JFrame implements ActionListener {
    
    JLabel lbl1, name, mobile, course;
    JTextField txt2, txt3, txt4;
    JButton add, delete, edit, cancel;

    StudentRegistration() {
        
        super("Student Registration");
        setSize(1000, 500);
        setLayout(null);
        setVisible(true);
        
        lbl1 = new JLabel("STUDENT REGISTRATION");
        lbl1.setBounds(130, 150, 300, 30);
        lbl1.setFont(new Font("Serif",Font.BOLD, 20));
        
        /****************Name***********************/
        name = new JLabel("Student Name");
        name.setBounds(100, 200, 80, 30);
        
        txt2 = new JTextField();
        txt2.setBounds(200, 200, 200, 30);
        
        /****************Mobile***********************/
        mobile = new JLabel("Mobile");
        mobile.setBounds(100, 300, 80, 30);
        
        txt3 = new JTextField();
        txt3.setBounds(200, 300, 200, 30);
        
        /****************Course***********************/
        course = new JLabel("Course");
        course.setBounds(100, 400, 80, 30);
        
        txt4 = new JTextField();
        txt4.setBounds(200, 400, 200, 30);
        
        /****************Add***********************/
        add = new JButton("Add");
        add.setBounds(100, 500, 80, 30);
        add.addActionListener(this);
        
        /****************Edit***********************/
        edit = new JButton("Edit");
        edit.setBounds(200, 500, 80, 30);
        edit.addActionListener(this);
        
        /****************Delete***********************/
        delete = new JButton("Delete");
        delete.setBounds(300, 500, 80, 30);
        delete.addActionListener(this);
        
        /****************Cancel***********************/
        cancel = new JButton("Cancel");
        cancel.setBounds(200, 550, 80, 30);
        cancel.addActionListener(this);
        
        add(lbl1);
        add(name);
        add(mobile);
        add(course);
        add(add);
        add(delete);
        add(edit);
        add(cancel);  
        add(txt2);
        add(txt3);
        add(txt4);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        
        if(e.getSource() == cancel){
            this.dispose();
        }
        
        if(e.getSource() == add){
            
            String n = txt2.getText();
            String c = txt3.getText();
            String mo = txt4.getText();

            try{
                // Step 1: Load the JDBC driver
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Step 2: Establish the connection
                String url = "jdbc:mysql://localhost:3306/student";
                String uname = "root";
                String pass = "root";
                Connection conn = DriverManager.getConnection(url, uname, pass);

                // Step 3: Create a statement
                // Step 4: Execute the query
                PreparedStatement st = conn.prepareStatement("INSERT INTO Record (name, course, mobile) VALUES (?, ?, ?)");
                st.setString(1, n);     // name
                st.setString(2, c);     // course
                st.setString(3, mo);    // mobile
                
                st.executeUpdate();

                JOptionPane.showMessageDialog(this, "Record Added");

                System.out.println("MYSQL connected");

                //  Step 6: Close the resources (same as before)
                st.close();
                conn.close();
            }
            catch(Exception ex){
                System.out.println("Exception : " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        
        JButton submit, exitBtn;
        JTextField txt1;
        JLabel l1;

        if (e.getSource() == delete) {

            JFrame j = new JFrame("Delete Record");
            j.setVisible(true);
            j.setLayout(null);
            j.setSize(300, 300);

            l1 = new JLabel("Enter ID");
            l1.setBounds(100, 50, 80, 30);

            txt1 = new JTextField();
            txt1.setBounds(100, 100, 80, 30);

            submit = new JButton("Submit");
            submit.setBounds(50, 150, 80, 30);
            
            exitBtn = new JButton("Cancel");
            exitBtn.setBounds(150, 150, 80, 30);
            
            submit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    
                    String deleteRecID = txt1.getText();
                    int ids = 0;

                    try {
                        ids = Integer.parseInt(deleteRecID);

                        /******************MYSQL Connection and Deletion******************/
                        try {
                            // Step 1: Load the JDBC driver
                            Class.forName("com.mysql.cj.jdbc.Driver");

                            // Step 2: Establish the connection
                            String url = "jdbc:mysql://localhost:3306/student";
                            String uname = "root";
                            String pass = "root";
                            Connection conn = DriverManager.getConnection(url, uname, pass);

                            // Step 3: Create a statement
                            // Step 4: Execute the query
                            PreparedStatement st = conn.prepareStatement("DELETE FROM Record WHERE id = ?");
                            st.setInt(1, ids);
                            st.executeUpdate();

                            System.out.println("Record deleted successfully");

                            // Step 6: Close the resources (same as before)
                            st.close();
                            conn.close();
                        } catch (Exception ex) {
                            System.out.println("Exception: " + ex.getMessage());
                            ex.printStackTrace();
                        }
                    } catch (NumberFormatException ex) {
                        System.out.println("Invalid ID format. Please enter a valid integer.");
                        ex.printStackTrace();
                    }
                }
            });

            exitBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent action){
                    j.dispose();
                }
            });
            
            j.add(l1);
            j.add(txt1);
            j.add(submit);
            j.add(exitBtn);
        }
        
        JTextField txtName, txtMob, txtID, txtCourse;

        if(e.getSource() == edit) {
            
            JFrame j = new JFrame("Edit your Row");
            j.setVisible(true);
            j.setLayout(null);
            j.setSize(500, 500);
            
            /**************ID****************/
            JLabel idLabel = new JLabel("ID");
            idLabel.setBounds(100, 100, 80, 30);
            
            txtID = new JTextField();
            txtID.setBounds(200, 100, 80, 30);
            
            /**************Name****************/
            JLabel nameLabel = new JLabel("Name");
            nameLabel.setBounds(100, 150, 80, 30);
            
            txtName = new JTextField();
            txtName.setBounds(200, 150, 80, 30);
            
            /**************Mobile****************/
            JLabel mobLabel = new JLabel("Mobile");
            mobLabel.setBounds(100, 200, 80, 30);
            
            txtMob = new JTextField();
            txtMob.setBounds(200, 200, 80, 30);
            
            /**************Course****************/
            JLabel courseLabel = new JLabel("Course");
            courseLabel.setBounds(100, 250, 80, 30);
            
            txtCourse = new JTextField();
            txtCourse.setBounds(200, 250, 80, 30);

            /******************Submit******************/
            
            JButton submitBtn = new JButton("Submit");
            submitBtn.setBounds(100, 300, 80, 30);
            
            /*****************Cancel******************/
            
            JButton exitButton = new JButton("Cancel");
            exitButton.setBounds(200, 300, 80, 30);
            
            j.add(idLabel);
            j.add(nameLabel);
            j.add(mobLabel);
            j.add(courseLabel);
            j.add(txtName);
            j.add(txtMob);
            j.add(txtID);
            j.add(txtCourse);
            j.add(submitBtn);
            j.add(exitButton);
            
            submitBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent action) {
                    
                    String id = txtID.getText();
                    String name = txtName.getText();
                    String course = txtCourse.getText();
                    String mob = txtMob.getText();
                    int ids = 0;
                    
                    try {
                            ids = Integer.parseInt(id);
                            
                            // Step 1: Load the JDBC driver
                            Class.forName("com.mysql.cj.jdbc.Driver");

                            // Step 2: Establish the connection
                            String url = "jdbc:mysql://localhost:3306/student";
                            String uname = "root";
                            String pass = "root";
                            Connection conn = DriverManager.getConnection(url, uname, pass);

                            // Step 3: Create a statement
                            // Step 4: Execute the query
                            PreparedStatement st = conn.prepareStatement("Update Record SET name = ?, course = ?, mobile = ? where id = ?");
                            st.setString(1, name);
                            st.setString(2, course);
                            st.setString(3, mob);
                            st.setInt(4, ids);
                            
                            st.executeUpdate();

                            System.out.println("Record UPDATED successfully");

                            // Step 6: Close the resources (same as before)
                            st.close();
                            conn.close();
                        } catch (Exception ex) {
                            System.out.println("Exception: " + ex.getMessage());
                            ex.printStackTrace();
                        }
                }
        });  
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent action) {
                j.dispose();
            }
        });
        }
    }

    public static void main(String[] args) {
        StudentRegistration s1 = new StudentRegistration();
    }   
}