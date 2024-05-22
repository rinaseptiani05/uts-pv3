package com.example.vp3.JFrames;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MahasiswaFrame {
    public MahasiswaFrame() {
        Connection conn = null;

        JFrame jFrame = new JFrame("Aplikasi Hello World");
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Nama");
        tableModel.addColumn("NIM");

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/mahasiswa?" +
                                               "user=root&password= ");
            String query = "SELECT * FROM mahasiswaku";
            PreparedStatement preparedStatement = conn.prepareStatement(query);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1);
                String nama = rs.getString(2);
                String nim = rs.getString(3);

                tableModel.addRow(new Object[]{id, nama, nim});
            }

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
        JTable table = new JTable(tableModel);
        JScrollPane pane = new JScrollPane(table);

        JButton button = new JButton("Create Mahasiswa");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateMahasiswaFrame();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(true);
        panel.add(button);
        panel.add(pane);

        jFrame.getContentPane().add(BorderLayout.CENTER, panel);
        jFrame.pack();
        jFrame.setDefaultCloseOperation(3);
        jFrame.setVisible(true);
        jFrame.setBounds(3, 4, 300, 400);
    }
}
