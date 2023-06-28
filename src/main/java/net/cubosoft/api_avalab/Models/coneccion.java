package net.cubosoft.api_avalab.Models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class coneccion {

    private static Connection con = null;



    private static String db_user_STATIC;
    private static String db_pass_STATIC;
    private static String db_name_STATIC;
    private static String db_url_STATIC;
    private static String db_port_STATIC;


    public  static Connection getConnection()
    {
         String connection_string="jdbc:sybase:Tds:"+db_url_STATIC+":"+db_port_STATIC+db_name_STATIC;
        System.out.println(connection_string);
       // System.out.println(db_user_STATIC);
        //System.out.println(db_pass_STATIC);
        try{
            // importar drivers    Class.forName("com.mysql.jdbc.Driver"); Drivers importados como dependencias Maven
         //   Class.forName("com.sybaseconn.jconn4");

            con = DriverManager.getConnection(connection_string, db_user_STATIC, db_pass_STATIC);//crear coneccion con bd
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }

    @Value("${graphql.db_user}")
    public void set_db_user_STATIC(String db_user){
        coneccion.db_user_STATIC = db_user;
    }

    @Value("${graphql.db_pass}")
    public void set_db_pass_STATIC(String db_pass){
        coneccion.db_pass_STATIC = db_pass;
    }

    @Value("${graphql.db_name}")
    public void set_db_name_STATIC(String db_name){
        coneccion.db_name_STATIC = db_name;
    }

    @Value("${graphql.db_url}")
    public void set_db_url_STATIC(String db_url){
        coneccion.db_url_STATIC = db_url;
    }

    @Value("${graphql.db_port}")
    public void set_db_port_STATIC(String db_port){
        coneccion.db_port_STATIC = db_port;
    }

}
