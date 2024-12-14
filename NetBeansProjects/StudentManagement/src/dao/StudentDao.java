/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Ahmad Suherman
 */

import db.DatabaseConnection;
import model.Gender;
import model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT students.*, genders.gender_name FROM students " +
                         "JOIN genders ON students.gender_id = genders.id";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("phone_number"),
                        rs.getString("address"),
                        new Gender(rs.getInt("gender_id"), rs.getString("gender_name"))
                );
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public void addStudent(Student student) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO students (name, age, phone_number, address, gender_id) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, student.getName());
            pstmt.setInt(2, student.getAge());
            pstmt.setString(3, student.getPhoneNumber());
            pstmt.setString(4, student.getAddress());
            pstmt.setInt(5, student.getGender().getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(Student student) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "UPDATE students SET name = ?, age = ?, phone_number = ?, address = ?, gender_id = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, student.getName());
            pstmt.setInt(2, student.getAge());
            pstmt.setString(3, student.getPhoneNumber());
            pstmt.setString(4, student.getAddress());
            pstmt.setInt(5, student.getGender().getId());
            pstmt.setInt(6, student.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(int id) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM students WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}