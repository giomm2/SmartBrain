package com.abcsoft.smarttbrain.services;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Mayron  Corrales on 07/06/2015.
 * servicio que permite hacer insert, update y delete de usuarios.
 */
public class UserService extends ConnectionService {


    public boolean insertUser(User user) throws ClassNotFoundException, SQLException, SQLException {
        boolean flag = false;
        String insert= "insert into AbcSoftDB.user(iduser,name,points,mail_iduser) values(?,?,?,?)";
        getConnection();
        try {
            PreparedStatement stm = con.prepareStatement(insert);
            stm.setInt(1, user.getId());
            stm.setString(2,user.getName());
            stm.setDouble(3, user.getPoints());
            stm.setInt(4, user.getIdMail());
            stm.executeUpdate();
            stm.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally
        {
            closeConnection();
        }
        return flag;
    }
}
