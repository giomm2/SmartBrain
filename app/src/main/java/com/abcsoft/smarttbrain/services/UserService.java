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

    public boolean deleteUser(User user) throws ClassNotFoundException, SQLException{
        boolean flag= false;
        String delete="delete from AbcSoftDB.user where (name= ? and points =?); ";
        getConnection();
        try {
            PreparedStatement stm = con.prepareStatement(delete);
            stm.setString(1, user.getName());
            stm.setDouble(2, user.getPoints());
            stm.executeUpdate();
            stm.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            closeConnection();
        }

        return flag;
    }

    public boolean updateUsername(User user) throws ClassNotFoundException, SQLException{
        boolean flag = false;
        String update= "update AbcSoftDB.user set name = ? where mail_iduser = ?;  ";
        getConnection();
        try {
            PreparedStatement stm = con.prepareStatement(update);
            stm.setString(1, user.getName());
            stm.setInt(2,user.getIdMail());
            stm.executeUpdate();
            stm.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            closeConnection();
        }
        return flag;
    }
    public boolean updateUserPoints(User user) throws ClassNotFoundException, SQLException{
        boolean flag = false;
        String update= "update AbcSoftDB.user set points = points + ? where (name = ? and mail_iduser = ?);  ";
        getConnection();
        try {
            PreparedStatement stm = con.prepareStatement(update);
            stm.setDouble(1, user.getPoints());
            stm.setString(2, user.getName());
            stm.setInt(3, user.getIdMail());
            stm.executeUpdate();
            stm.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            closeConnection();
        }
        return flag;
    }
}
