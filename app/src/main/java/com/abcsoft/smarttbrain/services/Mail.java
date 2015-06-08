package com.abcsoft.smarttbrain.services;

import java.io.Serializable;

/**
 * Created by Mayron  Corrales on 07/06/2015.
 * Esta clase lo unico que hace es ser parte del patron DAO, contiene los atributos para formar el objeto mail
 * que sera usado para ingresar en la DB
 */
public class Mail implements Serializable {


    private int id;
    private String mail;

    public Mail(String mail){

        this.mail= mail;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

}
