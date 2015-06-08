package com.abcsoft.smarttbrain.services;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Mayron  Corrales on 07/06/2015.
 * Servicio que permite insertar mails en la db para luego hacerle un binding con el usuario
 */
public class MailService extends ConnectionService {

    public boolean insertMail(Mail mail) throws ClassNotFoundException, SQLException {
        boolean flag = false;
        String insert="insert into AbcSoftDB.mail(iduser, mail) values(?,?)";
        getConnection();
        try {
            PreparedStatement smt = con.prepareStatement(insert);
            smt.setInt(1, mail.getId());
            smt.setString(2, mail.getMail());
            smt.executeUpdate();
            smt.close();
            flag = true;

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            this.closeConnection();
        }
        return flag;
    }
}
