package net.cubosoft.api_avalab.Models;

import org.springframework.beans.factory.annotation.Value;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EstatusModel {
       Connection con;
    private String db_port;

    public List<Map> getEstatusList() {
        con=coneccion.getConnection();

        Statement stmt= null;
        List listado=new ArrayList<>();

        try {
            stmt = con.createStatement();

            ResultSet rs= stmt.executeQuery("select * from mob_status");
            ResultSetMetaData md=rs.getMetaData();
            int nroColumna= md.getColumnCount();

            while (rs.next()){
                Map filaData=new HashMap(nroColumna);
                for (int i=1;i<=nroColumna;i++){
                    filaData.put(md.getColumnName(i),rs.getObject(i));
                }

                listado.add(filaData);
            }

            con.close();

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return listado;
    }
}
