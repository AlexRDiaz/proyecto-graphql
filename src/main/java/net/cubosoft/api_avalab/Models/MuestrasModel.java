package net.cubosoft.api_avalab.Models;

import org.springframework.beans.factory.annotation.Value;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MuestrasModel {

       Connection con;
    private String db_port;

    public List<Map> getMuestrasList() throws SQLException{
        con=coneccion.getConnection();
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("select from mob_getmsts");
        ResultSetMetaData md=rs.getMetaData();
        int nroColumna=md.getColumnCount();
        List listado=new ArrayList<>();
        while (rs.next()){
            Map filaData = new HashMap(nroColumna);
            for (int i=1;i<=nroColumna;i++){
                filaData.put(md.getColumnName(i),rs.getObject(i));
            }
            listado.add(filaData);
        }
        con.close();
        return listado;
    }

    public Map getMuestrasbyCod(String cod_mst) throws SQLException{
        con=coneccion.getConnection();
        PreparedStatement pstmt=con.prepareStatement("SELECT * FROM mob_getmsts where cod_mst=?");
        pstmt.setString(1,cod_mst);
        ResultSet rs=pstmt.executeQuery();
        ResultSetMetaData md=rs.getMetaData();
        int nroColumnas=md.getColumnCount();
        Map filaData=new HashMap(nroColumnas);
        rs.next();
        for (int i=1;i<=nroColumnas;i++){
            filaData.put(md.getColumnName(i),rs.getObject(i));
        }
        con.close();
        return filaData;
    }

    public Map AddMstxOrden(String jsonData) throws SQLException{
        con=coneccion.getConnection();
        CallableStatement cstmt=con.prepareCall("call mob_add_mstxord_json(@json_data=?");
        cstmt.setString(1,jsonData);
        ResultSet rs=cstmt.executeQuery();
        ResultSetMetaData md=rs.getMetaData();
        int nroColumnas=md.getColumnCount();
        Map filaData=new HashMap(nroColumnas);
        rs.next();
        for (int i=1;i<=nroColumnas;i++){
            filaData.put(md.getColumnName(i),rs.getObject(i));
        }
        con.close();
        return filaData;
    }
}
