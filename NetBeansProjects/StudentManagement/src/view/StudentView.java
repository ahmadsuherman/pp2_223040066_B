/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author Ahmad Suherman
 */

import dao.StudentDao;
import model.Gender;
import model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class StudentView extends JFrame {
    private JTable table;
    private JTextField txtId, txtName, txtAge, txtPhone, txtAddress;
    private JComboBox<Gender> comboGender;
    private DefaultTableModel tableModel;
    private StudentDao studentDao = new StudentDao();
    private JButton btnAdd, btnUpdate, btnDelete, btnClear;

    public StudentView() {
        setTitle("Student CRUD");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(null);

        // Form Fields
        JLabel lblId = new JLabel("ID:");
        JLabel lblName = new JLabel("Name:");
        JLabel lblAge = new JLabel("Age:");
        JLabel lblPhone = new JLabel("Phone:");
        JLabel lblAddress = new JLabel("Address:");
        JLabel lblGender = new JLabel("Gender:");

        txtId = new JTextField();
        txtId.setEditable(false); // ID tidak dapat diubah
        txtName = new JTextField();
        txtAge = new JTextField();
        txtPhone = new JTextField();
        txtAddress = new JTextField();
        comboGender = new JComboBox<>(new Gender[]{new Gender(1, "Laki Laki"), new Gender(2, "Perempuan")});

        lblId.setBounds(20, 20, 100, 25);
        lblName.setBounds(20, 60, 100, 25);
        lblAge.setBounds(20, 100, 100, 25);
        lblPhone.setBounds(20, 140, 100, 25);
        lblAddress.setBounds(20, 180, 100, 25);
        lblGender.setBounds(20, 220, 100, 25);

        txtId.setBounds(120, 20, 150, 25);
        txtName.setBounds(120, 60, 150, 25);
        txtAge.setBounds(120, 100, 150, 25);
        txtPhone.setBounds(120, 140, 150, 25);
        txtAddress.setBounds(120, 180, 150, 25);
        comboGender.setBounds(120, 220, 150, 25);

        add(lblId);
        add(lblName);
        add(lblAge);
        add(lblPhone);
        add(lblAddress);
        add(lblGender);

        add(txtId);
        add(txtName);
        add(txtAge);
        add(txtPhone);
        add(txtAddress);
        add(comboGender);

        btnAdd = new JButton("Add");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnClear = new JButton("Clear");

       int buttonWidth = 150; // Lebar tombol
        int buttonHeight = 30; // Tinggi tombol
        int buttonY = 270; // Jarak vertikal (y-axis) tombol
        int gap = 20; // Jarak antar tombol

        int centerX = (800 - (4 * buttonWidth + 3 * gap)) / 2;
        
        btnAdd.setBounds(centerX, buttonY, buttonWidth, buttonHeight);
        btnUpdate.setBounds(centerX + buttonWidth + gap, buttonY, buttonWidth, buttonHeight);
        btnDelete.setBounds(centerX + 2 * (buttonWidth + gap), buttonY, buttonWidth, buttonHeight);
        btnClear.setBounds(centerX + 3 * (buttonWidth + gap), buttonY, buttonWidth, buttonHeight);

        add(btnAdd);
        add(btnUpdate);
        add(btnDelete);
        add(btnClear);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Name", "Age", "Phone", "Address", "Gender"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 320, 740, 230);
        add(scrollPane);

        loadData();

        btnAdd.addActionListener(e -> addStudent());
        btnUpdate.addActionListener(e -> updateStudent());
        btnDelete.addActionListener(e -> deleteStudent());
        btnClear.addActionListener(e -> clearForm());

        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    populateFormFromTable(selectedRow);
                }
            }
        });
    }

    private void loadData() {
        tableModel.setRowCount(0);
        List<Student> students = studentDao.getAllStudents();
        for (Student student : students) {
            tableModel.addRow(new Object[]{
                    student.getId(),
                    student.getName(),
                    student.getAge(),
                    student.getPhoneNumber(),
                    student.getAddress(),
                    student.getGender().getGenderName()
            });
        }
    }

    private void addStudent() {
        String name = txtName.getText();
        int age = Integer.parseInt(txtAge.getText());
        String phone = txtPhone.getText();
        String address = txtAddress.getText();
        Gender gender = (Gender) comboGender.getSelectedItem();

        Student student = new Student(0, name, age, phone, address, gender);
        studentDao.addStudent(student);
        loadData();
        clearForm();
        JOptionPane.showMessageDialog(this, "Student added successfully!");
    }

    private void updateStudent() {
        int id = Integer.parseInt(txtId.getText());
        String name = txtName.getText();
        int age = Integer.parseInt(txtAge.getText());
        String phone = txtPhone.getText();
        String address = txtAddress.getText();
        Gender gender = (Gender) comboGender.getSelectedItem();

        Student student = new Student(id, name, age, phone, address, gender);
        studentDao.updateStudent(student);
        loadData();
        clearForm();
        JOptionPane.showMessageDialog(this, "Student updated successfully!");
    }

    private void deleteStudent() {
        int id = Integer.parseInt(txtId.getText());
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this student?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            studentDao.deleteStudent(id);
            loadData();
            clearForm();
            JOptionPane.showMessageDialog(this, "Student deleted successfully!");
        }
    }

    private void clearForm() {
        txtId.setText("");
        txtName.setText("");
        txtAge.setText("");
        txtPhone.setText("");
        txtAddress.setText("");
        comboGender.setSelectedIndex(0);
    }

    private void populateFormFromTable(int row) {
        txtId.setText(tableModel.getValueAt(row, 0).toString());
        txtName.setText(tableModel.getValueAt(row, 1).toString());
        txtAge.setText(tableModel.getValueAt(row, 2).toString());
        txtPhone.setText(tableModel.getValueAt(row, 3).toString());
        txtAddress.setText(tableModel.getValueAt(row, 4).toString());

        String genderName = tableModel.getValueAt(row, 5).toString();
        if (genderName.equals("Laki Laki")) {
            comboGender.setSelectedIndex(0);
        } else {
            comboGender.setSelectedIndex(1);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentView view = new StudentView();
            view.setVisible(true);
        });
    }
}