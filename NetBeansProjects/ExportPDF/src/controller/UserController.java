/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author Ahmad Suherman
 */

import model.*;
import view.UserPdf;
import view.UserView;
import org.apache.ibatis.session.SqlSession; 
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserController {
    private UserView view;
    private UserMapper mapper;
    private SqlSession session; 
    private UserPdf pdf;
    
    public UserController(UserView view, UserMapper mapper, UserPdf pdf) {
        this.view = view;
        this.mapper = mapper;
        this.session = MyBatisUtil.getSqlSession(); 
        this.pdf = pdf;
        
        this.view.addAddUserListener(new AddUserListener());
        this.view.addRefreshListener(new RefreshListener());
        this.view.addExportListener(new ExportListener());
    }
    
    class AddUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = view.getNameInput();
            String email = view.getEmailInput();
            if (!name.isEmpty() && !email.isEmpty()) {
                User user = new User();
                user.setName(name);
                user.setEmail(email);
                try {
                    mapper.insertUser(user); 
                    session.commit(); 
                    JOptionPane.showMessageDialog(view, "User added successfully!");
                    refreshUserList(); 
                } catch (Exception ex) {
                    session.rollback(); 
                    JOptionPane.showMessageDialog(view, "Error adding user: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(view, "Please fill in all fields.");
            }
        }
    }
    
    class RefreshListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            refreshUserList(); 
        }
    }
    
    private void refreshUserList() {
        List<User> users = mapper.getAllUsers();
        String[] userArray = users.stream()
            .map(u -> u.getName() + " (" + u.getEmail() + ")")
            .toArray(String[]::new);
        view.setUserList(userArray); 
    }
    
    class ExportListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<User> users = mapper.getAllUsers();
            pdf.exportPdf(users);
            JOptionPane.showMessageDialog(view, "User data exported to PDF.");
        }
    }
}

