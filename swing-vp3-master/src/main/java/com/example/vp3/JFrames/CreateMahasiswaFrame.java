package com.example.vp3.JFrames;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.Connection;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;

public class CreateMahasiswaFrame {
    public CreateMahasiswaFrame() {
        JFrame newFrame = new JFrame("Create Mahasiswa");

        JLabel labelNama = new JLabel("Nama");
        JTextField textFieldNama = new JTextField(25);

        JLabel labelNIM = new JLabel("NIM");
        JTextField textFieldNIM = new JTextField(25);
        
        JButton buttonSubmit = new JButton("Create");

        buttonSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = textFieldNama.getText();
                String nim = textFieldNIM.getText();
                Connection conn = null;

                try {
                    conn = DriverManager.getConnection("jdbc:mysql://localhost/mahasiswa?" +
                                                       "user=root&password= ");
                    String query = "INSERT INTO mahasiswaku (nama, nim) VALUES (?, ?)";
                    PreparedStatement preparedStatement = conn.prepareStatement(query);
                    preparedStatement.setString(1, nama);
                    preparedStatement.setString(2, nim);
                    preparedStatement.executeUpdate();

                    newFrame.dispose(); 
                } catch (SQLException ex) {
                    System.out.println("SQLException: " + ex.getMessage());
                    System.out.println("SQLState: " + ex.getSQLState());
                    System.out.println("VendorError: " + ex.getErrorCode());
                }
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(true);
        panel.add(labelNama);
        panel.add(textFieldNama);
        panel.add(labelNIM);
        panel.add(textFieldNIM);
        panel.add(buttonSubmit);

        newFrame.getContentPane().add(BorderLayout.CENTER, panel);
        newFrame.pack();
        newFrame.setVisible(true);
        newFrame.setBounds(3, 4, 300, 400);
    }
}
