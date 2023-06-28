package net.cubosoft.api_avalab.Models;

import org.springframework.beans.factory.annotation.Value;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IessanexoModel {
       Connection con;
    private String db_port;

    public List<Map> getListbyTipo(String tipo) throws SQLException{
        con=coneccion.getConnection();
        PreparedStatement pstmt=con.prepareStatement("SELECT * from mob_iessanexo where tipo= ? and lock_reg=0");
        pstmt.setString(1,tipo);
        ResultSet rs= pstmt.executeQuery();
        ResultSetMetaData md=rs.getMetaData();
        int nroColumnas=md.getColumnCount();
        List listado=new ArrayList<>();

        while (rs.next()){
        Map filaData=new HashMap<>();
        for(int i=1;i<=nroColumnas;i++){
            filaData.put(md.getColumnName(i),rs.getObject(i));
        }
        listado.add(filaData);
        }
        con.close();
        return listado;
    }

    public Map getCIE10(String codigo) throws SQLException{
        con=coneccion.getConnection();
        PreparedStatement pstmt=con.prepareStatement("SELECT * FROM mob_iessanexo where tipo='CIE10' and codigo=? and lock_reg=0");
        pstmt.setString(1,codigo);
        ResultSet rs= pstmt.executeQuery();
        ResultSetMetaData md=rs.getMetaData();
        int nroColumnas=md.getColumnCount();
        rs.next();
        Map filaData=new HashMap(nroColumnas);
        for(int i=1;i<=nroColumnas;i++){
            filaData.put(md.getColumnName(i),rs.getObject(i));
        }
       con.close();
        return filaData;
    }
}
