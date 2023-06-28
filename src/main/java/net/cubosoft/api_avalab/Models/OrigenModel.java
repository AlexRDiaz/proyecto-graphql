package net.cubosoft.api_avalab.Models;

import org.springframework.beans.factory.annotation.Value;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrigenModel {

       Connection con;
    private String db_port;

    public List<Map> getOrigenList() throws SQLException{
        con=coneccion.getConnection();
        Statement stmnt=con.createStatement();
        ResultSet rs=stmnt.executeQuery("select * from mob_origen");
        ResultSetMetaData md=rs.getMetaData();
        int nroColumna=md.getColumnCount();
        List<Map> listado = new ArrayList();
        while (rs.next()){
            Map filaData=new HashMap(nroColumna);
            for (int i=1;i<=nroColumna;i++){
                filaData.put(md.getColumnName(i),rs.getObject(i));
            }
            listado.add(filaData);
        }
        con.close();
        return listado;
    }
}
