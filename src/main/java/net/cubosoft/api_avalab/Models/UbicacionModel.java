package net.cubosoft.api_avalab.Models;
import org.springframework.beans.factory.annotation.Value;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class UbicacionModel {

       Connection con;
    private String db_port;

    public List<Map> getUbicacionList() throws SQLException {
        con=coneccion.getConnection();
        Statement smtnt=con.createStatement();
        ResultSet rs=smtnt.executeQuery("select * from mob_ubicacion");
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

    public List<Map> getUbicacionListbyCod(String cod_ori) throws SQLException {
        con=coneccion.getConnection();
        PreparedStatement smtnt=con.prepareStatement("select * from mob_ubicacion where cod_ori= ?");
        smtnt.setString(1,cod_ori);
        ResultSet rs=smtnt.executeQuery();
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
}
