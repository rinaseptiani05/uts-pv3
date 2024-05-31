package com.example.vp3.JFrames;

import java.awt.BorderLayout;
import java.awt.Component;
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
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.AbstractCellEditor;

public class MahasiswaFrame {
    public MahasiswaFrame() {
        Connection conn = null;

        JFrame jFrame = new JFrame("Aplikasi Mahasiswa");
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Nama");
        tableModel.addColumn("NIM");
        tableModel.addColumn("Update");
        tableModel.addColumn("Delete");

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

                tableModel.addRow(new Object[]{id, nama, nim, "Update", "Delete"});
            }

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
        JTable table = new JTable(tableModel);
        table.getColumn("Update").setCellRenderer(new ButtonRenderer());
        table.getColumn("Update").setCellEditor(new ButtonEditor(new JButton("Update"), this));
        table.getColumn("Delete").setCellRenderer(new ButtonRenderer());
        table.getColumn("Delete").setCellEditor(new ButtonEditor(new JButton("Delete"), this));

        JScrollPane pane = new JScrollPane(table);

        JButton createButton = new JButton("Create Mahasiswa");

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateMahasiswaFrame();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(true);
        panel.add(createButton);
        panel.add(pane);

        jFrame.getContentPane().add(BorderLayout.CENTER, panel);
        jFrame.pack();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        jFrame.setBounds(3, 4, 600, 400);
    }
}

class ButtonRenderer extends JButton implements TableCellRenderer {

    public ButtonRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText((value == null) ? "" : value.toString());
        return this;
    }
}

class ButtonEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
    private JButton button;
    private String label;
    private boolean isPushed;
    private JTable table;
    private MahasiswaFrame mahasiswaFrame;

    public ButtonEditor(JButton button, MahasiswaFrame mahasiswaFrame) {
        this.button = button;
        this.button.addActionListener(this);
        this.mahasiswaFrame = mahasiswaFrame;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.table = table;
        this.label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
            int row = table.getSelectedRow();
            int id = (int) table.getValueAt(row, 0);
            String nama = (String) table.getValueAt(row, 1);
            String nim = (String) table.getValueAt(row, 2);
            if (label.equals("Update")) {
                new UpdateMahasiswaFrame(id, nama, nim);
            } else if (label.equals("Delete")) {
                deleteMahasiswa(id);
            }
        }
        isPushed = false;
        return label;
    }

    private void deleteMahasiswa(int id) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/mahasiswa?" +
                                               "user=root&password= ");
            String query = "DELETE FROM mahasiswaku WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            DefaultTableModel model = (DefaultTableModel) table.getModel();
            for (int i = 0; i < model.getRowCount(); i++) {
                if ((int) model.getValueAt(i, 0) == id) {
                    model.removeRow(i);
                    break;
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        fireEditingStopped();
    }
}
