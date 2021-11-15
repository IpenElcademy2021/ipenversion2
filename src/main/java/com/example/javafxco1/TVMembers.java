package com.example.javafxco1;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TVMembers {

    private SimpleStringProperty caisse;
    private SimpleStringProperty num;
    private SimpleStringProperty nom;
    private SimpleStringProperty prenom;
    private SimpleStringProperty dob;
    private SimpleStringProperty sex;

    public TVMembers(String caisse, String num, String nom, String prenom, String dob, String sex) {
        this.caisse = new SimpleStringProperty(caisse);
        this.num = new SimpleStringProperty(num);
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.dob = new SimpleStringProperty(dob);
        this.sex = new SimpleStringProperty(sex);
    }

    public String getCaisse() {
        return caisse.get();
    }

    public void setCaisse(String caisse) {
        this.caisse.set(caisse);
    }

    public String getNum() {
        return num.get();
    }

    public void setNum(String num) {
        this.num.set(num);
    }

    public String getNom() {
        return nom.get();
    }


    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public String getPrenom() {
        return prenom.get();
    }


    public void setPrenom(String prenom) {
        this.prenom.set(prenom);
    }

    public String getDob() {
        return dob.get();
    }


    public void setDob(String dob) {
        this.dob.set(dob);
    }

    public String getSex() {
        return sex.get();
    }

    public void setSex(String sex) {
        this.sex.set(sex);
    }

}
