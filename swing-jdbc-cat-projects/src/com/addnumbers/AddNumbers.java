package com.addnumbers;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddNumbers extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNum1;
	private JTextField textNum2;
	private JTextField textNum3;
	private JTextField textNum4;
	private JTextField textRes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddNumbers frame = new AddNumbers();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddNumbers() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setUndecorated(true);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Num1: ");
		lblNewLabel.setBounds(10, 110, 63, 14);
		contentPane.add(lblNewLabel);
		
		textNum1 = new JTextField();
		textNum1.setBounds(61, 107, 86, 20);
		contentPane.add(textNum1);
		textNum1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Num2:");
		lblNewLabel_1.setBounds(204, 110, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		textNum2 = new JTextField();
		textNum2.setBounds(260, 107, 86, 20);
		contentPane.add(textNum2);
		textNum2.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Num3:");
		lblNewLabel_2.setBounds(10, 150, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		textNum3 = new JTextField();
		textNum3.setBounds(61, 147, 86, 20);
		contentPane.add(textNum3);
		textNum3.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Num4:");
		lblNewLabel_3.setBounds(204, 150, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		textNum4 = new JTextField();
		textNum4.setBounds(260, 147, 86, 20);
		contentPane.add(textNum4);
		textNum4.setColumns(10);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					double num1 = Double.parseDouble(textNum1.getText());
					double num2 = Double.parseDouble(textNum2.getText());
					double num3 = Double.parseDouble(textNum3.getText());
					double num4 = Double.parseDouble(textNum4.getText());
					
					double res = NumberAdder.add(num1, num2, num3, num4);
					
		            textRes.setText(String.format("%.2f", res));
				}catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Please enter a valid number");
				}
				
			}
		});
		btnAdd.setBounds(10, 191, 89, 23);
		contentPane.add(btnAdd);
		
		JButton btnReset = new JButton("RESET");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textNum1.setText("");
		        textNum2.setText("");
		        textNum3.setText("");
		        textNum4.setText("");
		        textRes.setText("0");
		        textNum1.requestFocus();
				
			}
		});
		btnReset.setBounds(141, 191, 89, 23);
		contentPane.add(btnReset);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textNum1.setText("");
		        textNum2.setText("");
		        textNum3.setText("");
		        textNum4.setText("");
		        textNum1.requestFocus();
			}
		});
		btnClear.setBounds(272, 191, 89, 23);
		contentPane.add(btnClear);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(383, 11, 57, 23);
		contentPane.add(btnExit);
		
		textRes = new JTextField();
		textRes.setText("0");
		textRes.setEditable(false);
		textRes.setBounds(10, 11, 263, 58);
		contentPane.add(textRes);
		textRes.setColumns(10);
		
		

	}
}
