package net.cubosoft.api_avalab.Models;

import org.springframework.beans.factory.annotation.Value;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SGCalidadModel {
       Connection con;
    private String db_port;

    public List<Map> getsgCalidad() throws SQLException {
        con=coneccion.getConnection();
        Statement smtnt=con.createStatement();
        ResultSet rs=smtnt.executeQuery("select * from mob_sgcalidad where lock_eve=0");
        ResultSetMetaData md=rs.getMetaData();
        int nroColumna=md.getColumnCount();
        List listado=new ArrayList();
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

    public Map addSgcxOrden(String json_sgc) throws SQLException {
        con=coneccion.getConnection();
        CallableStatement smtnt=con.prepareCall("call mob_add_sgcxorden(@json_data=?)");
        smtnt.setString(1,json_sgc);
        ResultSet rs=smtnt.executeQuery();
        ResultSetMetaData md=rs.getMetaData();
        int nroColumna=md.getColumnCount();
        rs.next();
        Map filaData=new HashMap(nroColumna);
        for (int i=1;i<=nroColumna;i++){
            filaData.put(md.getColumnName(i),rs.getObject(i));
        }

        con.close();
        return filaData;
    }
}
