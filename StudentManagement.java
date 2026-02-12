package studentSwingProgram;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class StudentManagement {
		private static int count = 0;
	public static void main(String[] args) {
		JFrame frame = new JFrame("Student Management");
		frame.setLayout(null);
		frame.setSize(500,400);
		Font font = new Font("Times New Roman",Font.PLAIN,14);
		
		JLabel headLabel = new JLabel("👨‍ STUDENT MANAGEMENT SYSTEM");
		headLabel.setBounds(80,0,300,30);
		headLabel.setFont(new Font(null, Font.BOLD,16));
		
		JLabel rollLabel = new JLabel("Roll No.: ");
		rollLabel.setBounds(40, 50, 100, 30);
		rollLabel.setFont(font);
		
		JLabel nameLabel = new JLabel("Name: ");
		nameLabel.setBounds(40, 90, 100, 30);
		nameLabel.setFont(font);
		
		JLabel ageLabel = new JLabel("Age: ");
		ageLabel.setBounds(40, 130, 100, 30);
		ageLabel.setFont(font);
		
		JLabel classLabel = new JLabel("Class: ");
		classLabel.setBounds(40, 170, 100, 30);
		classLabel.setFont(font);
		
		JLabel genderLabel = new JLabel("Gender: ");
		genderLabel.setBounds(40, 210, 100, 30);
		genderLabel.setFont(font);
		
		JLabel phoneLabel = new JLabel("Phone No.: ");
		phoneLabel.setBounds(40,250,100,30);
		phoneLabel.setFont(font);
		
		final JLabel countLabel = new JLabel("Student Count: 0");
		countLabel.setFont(new Font("Arial Black",Font.BOLD,12));
		countLabel.setBounds(300,250,150,30);
		
		JTextField rollField = new JTextField(15);
		rollField.setBounds(125, 50, 120, 26);
		
		
		JTextField nameField = new JTextField(15);
		nameField.setBounds(125, 90, 120, 26);
		
		JTextField ageField = new JTextField(15);
		ageField.setBounds(125, 130, 120, 26);
		
		JTextField classField = new JTextField(15);
		classField.setBounds(125, 170, 120, 26);
		
		JTextField genderField = new JTextField(10);
		genderField.setBounds(125, 210, 120, 26);
		
		JTextField phoneField = new JTextField();
		phoneField.setBounds(125, 250, 120, 26);
		
		JButton addButton = new JButton("Add Student");
		addButton.setBounds(70, 300, 100, 30);
		addButton.setFont(new Font("Arial",Font.PLAIN,11));
		
		JButton updateButton = new JButton("Update Student Details");
		updateButton.setBounds(280,50,150,26);
		updateButton.setFont(new Font("Arial",Font.PLAIN,11));
		
		JButton deleteButton = new JButton("Delete Student");
		deleteButton.setBounds(280,130,150,26);
		deleteButton.setFont(new Font("Arial",Font.PLAIN,11));
		
		JButton displayButton = new JButton("Display Student Data");
		displayButton.setBounds(280,210,150,26);
		displayButton.setFont(new Font("Arial",Font.PLAIN,11));
		
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rollno = Integer.parseInt(rollField.getText());
				String name = nameField.getText();
				int age = Integer.parseInt(ageField.getText());
				String standard = classField.getText();
				String gender = genderField.getText();
				long phone = Long.parseLong(phoneField.getText());
				count++;
				countLabel.setText("Total Students: "+count);
				
				try {
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdata","root","Sikkandar@26.");
					System.out.println("Connected to MySQL Successfully!");
					String sql = "INSERT INTO students (roll,name,age,standard,gender,phone) VALUES (?,?,?,?,?,?)";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, rollno);
					pstmt.setString(2, name);
					pstmt.setInt(3, age);
					pstmt.setString(4, standard);
					pstmt.setString(5, gender);
					pstmt.setLong(6, phone);
					pstmt.executeUpdate();
					conn.close();
					JOptionPane.showMessageDialog(frame,"Student Added Successfully!");
				
				}	catch(Exception Ex) {
					System.out.println(Ex);	
					Ex.printStackTrace();
				}
			}
		});
		
		updateButton.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				JFrame updateFrame = new JFrame("Students Details Updating...");
				updateFrame.setSize(400, 360);
				updateFrame.setLayout(null);
				
				JLabel updateRollLabel = new JLabel("Enter the Roll to Update Data");
				updateRollLabel.setBounds(100, 20, 200, 30);
				updateRollLabel.setFont(font);
				
				JTextField updateRollField = new JTextField();
				updateRollField.setBounds(110,60,160,30);
				
				JLabel updateNameLabel = new JLabel("Enter New Name: ");
				updateNameLabel.setBounds(30, 110, 130, 30);
				updateNameLabel.setFont(font);
				
				JTextField updateNameField = new JTextField();
				updateNameField.setBounds(160,110,160,26);
				
				JLabel updateAgeLabel = new JLabel("Enter New Age: ");
				updateAgeLabel.setBounds(30, 150, 130, 30);
				updateAgeLabel.setFont(font);
				
				JTextField updateAgeField = new JTextField();
				updateAgeField.setBounds(160,150,160,26);
				
				JLabel updateClassLabel = new JLabel("Enter New Class: ");
				updateClassLabel.setBounds(30, 190, 200, 30);
				updateClassLabel.setFont(font);
				
				JTextField updateClassField = new JTextField();
				updateClassField.setBounds(160,190,160,26);
				
				JLabel updatePhoneLabel = new JLabel("Enter New Phone No.: ");
				updatePhoneLabel.setBounds(30, 230, 200, 30);
				updatePhoneLabel.setFont(font);
				
				JTextField updatePhoneField = new JTextField();
				updatePhoneField.setBounds(160,230,160,26);
				
				JButton submitButton = new JButton("Submit");
				submitButton.setBounds(130,270,100,30);
				submitButton.setFont(new Font("Arial",Font.PLAIN,11));
				
				
				submitButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int updateRoll = Integer.parseInt(updateRollField.getText());
						String updateName = updateNameField.getText();
						int updateAge = Integer.parseInt(updateAgeField.getText());
						String updateClass = updateClassField.getText();
						long updatePhone = Long.parseLong(updatePhoneField.getText());
				
						try {
							Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdata","root","Sikkandar@26.");
							String sql = "UPDATE students SET name=?,age=?,standard=?,phone=? WHERE roll=?";
							PreparedStatement stmt = con.prepareStatement(sql);
							stmt.setString(1, updateName);
							stmt.setInt(2, updateAge);
							stmt.setString(3, updateClass);
							stmt.setLong(4, updatePhone);
							stmt.setInt(5, updateRoll);
							int rows = stmt.executeUpdate();
							if(rows > 0) {
								System.out.println("Data updated successfully");
							} else {
								System.out.println("No record found this roll: "+updateRoll);
							}
							con.close();
							JOptionPane.showMessageDialog(frame, "Data Updated Successfully");
							updateFrame.dispose();
						} catch(Exception ex) {
							ex.printStackTrace();
						}	
					}
				});
				updateFrame.add(updateRollLabel);	updateFrame.add(updateRollField);
				updateFrame.add(updateNameLabel);	updateFrame.add(updateNameField);
				updateFrame.add(updateAgeLabel);	updateFrame.add(updateAgeField);
				updateFrame.add(updateClassLabel);	updateFrame.add(updateClassField);
				updateFrame.add(updatePhoneLabel);	updateFrame.add(updatePhoneField);
				updateFrame.add(submitButton);
				updateFrame.setVisible(true);
			}
		});
		
		deleteButton.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				JFrame deleteFrame = new JFrame("Student Data deleting...");
				deleteFrame.setSize(400,200);
				deleteFrame.setLayout(null);
				
				JLabel deleteRollLabel = new JLabel("Enter the Roll to Delete Data");
				deleteRollLabel.setBounds(100, 20, 200, 30);
				deleteRollLabel.setFont(font);
				
				JTextField deleteRollField = new JTextField();
				deleteRollField.setBounds(110,60,160,30);
				
				JButton deleteDataButton = new JButton("DELETE");
				deleteDataButton.setBounds(140,110,100,25);
				deleteDataButton.setFont(new Font("Arial",Font.PLAIN,11));
				
				deleteDataButton.addActionListener(new ActionListener () {
					public void actionPerformed(ActionEvent e) {
						String deleteRoll = deleteRollField.getText(); 
						try {
							Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdata","root","Sikkandar@26.");
							String sql = "DELETE FROM students WHERE roll = ?";
							PreparedStatement stmt = con.prepareStatement(sql);
							stmt.setString(1, deleteRoll);
							int deleteRow = stmt.executeUpdate();
							JOptionPane.showMessageDialog(frame,"Data deleted Successfully!");
							if(deleteRow > 0) {
								System.out.println("Data updated successfully");
							} else {
								System.out.println("No record found this name: "+deleteRoll);
							}
							con.close();
							deleteFrame.dispose();
							count--;
							countLabel.setText("Total Students: "+count);
						} catch(Exception ex) {
							ex.printStackTrace();
						}
					}
				});
				deleteFrame.add(deleteRollLabel);	deleteFrame.add(deleteRollField);
				deleteFrame.add(deleteDataButton);
				deleteFrame.setVisible(true);
			}
		});
		
		displayButton.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				JFrame searchFrame = new JFrame("Student Data");
				searchFrame.setSize(500,300);
				 
				 String[] columnNames = {"Roll","Name","Age","Class","Gender","Phone"};
				 DefaultTableModel model = new DefaultTableModel(columnNames,0);
				 JTable table = new JTable(model);
				 
				 try {
					 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdata","root","Sikkandar@26.");
					 String sql = "SELECT * FROM students";
					 Statement stmt = conn.createStatement();
					 ResultSet rs = stmt.executeQuery(sql);
					 while(rs.next()) {
						 Object[] row = {rs.getInt("roll"),rs.getString("name"),rs.getInt("age"),rs.getString("standard"),rs.getString("Gender"),rs.getLong("phone")};
						 model.addRow(row);
					 }
					 rs.close();
					 conn.close();
				 } catch(Exception Ex) {
					 System.out.println("Error Occurred: "+Ex);
				 }
				 searchFrame.add(new JScrollPane(table),BorderLayout.CENTER);
				 searchFrame.setVisible(true);
			}	
	});
		
		frame.add(headLabel);	frame.add(countLabel);
		frame.add(rollLabel);	frame.add(rollField);
		frame.add(nameLabel);	frame.add(nameField);
		frame.add(ageLabel);	frame.add(ageField);
		frame.add(classLabel);	frame.add(classField);
		frame.add(phoneLabel);	frame.add(phoneField);
		frame.add(genderLabel); frame.add(genderField);
		frame.add(addButton); frame.add(updateButton); frame.add(deleteButton); frame.add(displayButton);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
