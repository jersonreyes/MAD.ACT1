package com.example.activity;

import java.util.ArrayList;
import java.util.Map;

public class EntryCards {

    private int ID;
    private String Name;
    private String Description;
    private int Picture;
    private String Birthdate;
    private String Gender;
    private String Street;
    private String HouseNumber;
    private String Barangay;
    private String Municipality;
    private String Province;
    private String Phone;

    private boolean c1;
    private boolean c2;
    private boolean c3;
    private boolean c4;
    private boolean c5;
    private boolean c6;
    private boolean c7;
    private boolean c8;


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getPicture() {
        return Picture;
    }

    public void setPicture(int picture) {
        Picture = picture;
    }

    public String getBirthdate() {
        return Birthdate;
    }

    public void setBirthdate(String birthdate) {
        Birthdate = birthdate;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getHouseNumber() {
        return HouseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        HouseNumber = houseNumber;
    }

    public String getBarangay() {
        return Barangay;
    }

    public void setBarangay(String barangay) {
        Barangay = barangay;
    }

    public String getMunicipality() {
        return Municipality;
    }

    public void setMunicipality(String municipality) {
        Municipality = municipality;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public boolean isC1() {
        return c1;
    }

    public void setC1(boolean c1) {
        this.c1 = c1;
    }

    public boolean isC2() {
        return c2;
    }

    public void setC2(boolean c2) {
        this.c2 = c2;
    }

    public boolean isC3() {
        return c3;
    }

    public void setC3(boolean c3) {
        this.c3 = c3;
    }

    public boolean isC4() {
        return c4;
    }

    public void setC4(boolean c4) {
        this.c4 = c4;
    }

    public boolean isC5() {
        return c5;
    }

    public void setC5(boolean c5) {
        this.c5 = c5;
    }

    public boolean isC6() {
        return c6;
    }

    public void setC6(boolean c6) {
        this.c6 = c6;
    }

    public boolean isC7() {
        return c7;
    }

    public void setC7(boolean c7) {
        this.c7 = c7;
    }

    public boolean isC8() {
        return c8;
    }

    public void setC8(boolean c8) {
        this.c8 = c8;
    }

    public static int getLastId() {
        return lastId;
    }

    public static void setLastId(int lastId) {
        EntryCards.lastId = lastId;
    }



    public EntryCards(int ID, String Name, String Description, int Picture, String Birthdate, String Gender, String Street,
                      String HouseNumber,String Barangay, String Municipality, String Province, String Phone, boolean c1, boolean c2, boolean c3,
                      boolean c4, boolean c5, boolean c6, boolean c7, boolean c8){
        this.ID=ID;
        this.Name=Name;
        this.Description=Description;
        this.Picture=Picture;
        this.Birthdate=Birthdate;
        this.Gender=Gender;
        this.Street=Street;
        this.HouseNumber=HouseNumber;
        this.Barangay=Barangay;
        this.Municipality=Municipality;
        this.Province=Province;
        this.Phone=Phone;
        this.c1=c1;
        this.c2=c2;
        this.c3=c3;
        this.c4=c4;
        this.c5=c5;
        this.c6=c6;
        this.c7=c7;
        this.c8=c8;
    }



    private static int lastId = 0;

}
