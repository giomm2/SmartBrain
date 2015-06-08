package com.abcsoft.smarttbrain.services;

import java.io.Serializable;

/**
 * Created by Mayron  Corrales on 07/06/2015.
 * Esta clase lo unico que hace es ser parte del patron DAO, contiene los atributos para formar el objeto user
 * que sera usado para ingresar en la DB
 */
public class User implements Serializable {

    private int id;
    private String name;
    private double points;
    private int idMail;

    public User(String name, double points, int idMail){
        this.name= name;
        this.points= points;
        this.idMail= idMail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public int getIdMail() {
        return idMail;
    }

    public void setIdMail(int idMail) {
        this.idMail = idMail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
