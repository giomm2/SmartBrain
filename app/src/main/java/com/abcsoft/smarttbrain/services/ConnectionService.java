package com.abcsoft.smarttbrain.services;

import android.os.Message;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Mayron  Corrales on 07/06/2015.
 * Servicio que me permite establecer la conexion y cerrarla.
 */
public class ConnectionService {

    static Connection con = null;
    private final static String user = "root";
    private final static String password="LPBpyc67673";
    private final static String url="jdbc:mysql://162.219.247.33:3306/AbcSoftDB";
    private static ConnectionService instance= null;

    // singleton para llamar la instancia de la conexion
    // delacro y creo la instancia de la clase
    private synchronized static void createInstance(){
        if(instance == null)
            instance = new ConnectionService(){};
    }

    // obtengo la conexion
    public static ConnectionService getInstance(){
        if(instance == null)
            createInstance();
        return instance;
    }

    // abro la conexion y la obtengo

    public Connection getConnection() throws ClassNotFoundException, SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Conecte con Exito");
        }catch (Exception exception){
            exception.printStackTrace();
            System.out.println("No conecte");
        }

        return con;

    }

    // cierro la conexion
    public void closeConnection() throws SQLException {
        try {
            if(con != null){
                con.close();
                System.out.println("Cerre la DB");
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

}
