/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Ahmad Suherman
 */

public class Gender {
    private int id;
    private String genderName;

    public Gender(int id, String genderName) {
        this.id = id;
        this.genderName = genderName;
    }

    public int getId() {
        return id;
    }

    public String getGenderName() {
        return genderName;
    }

    @Override
    public String toString() {
        return genderName;
    }
}