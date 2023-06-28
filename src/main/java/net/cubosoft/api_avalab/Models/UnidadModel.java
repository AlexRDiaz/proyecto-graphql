package net.cubosoft.api_avalab.Models;
import org.springframework.beans.factory.annotation.Value;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class UnidadModel {

       Connection con;
    private String db_port;

    public List<Map> getUnidadList() throws SQLException {
        con=coneccion.getConnection();
        Statement smtnt=con.createStatement();
        ResultSet rs=smtnt.executeQuery("select * from mob_unidad order by des_uni asc");
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

    public Map getUnidadbyCod(String cod_uni) throws SQLException {
        con=coneccion.getConnection();
        PreparedStatement smtnt=con.prepareStatement("select * from mob_unidad where cod_uni = ?");
        smtnt.setString(1,cod_uni);
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

    public List<Map> searchUnidad(String des_uni) throws SQLException {
        con=coneccion.getConnection();
        PreparedStatement smtnt=con.prepareStatement("select top 15 * from mob_unidad where des_uni like ? order by des_uni asc");
        smtnt.setString(1,des_uni);
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
