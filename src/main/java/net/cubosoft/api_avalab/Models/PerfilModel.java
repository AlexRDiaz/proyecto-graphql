package net.cubosoft.api_avalab.Models;

import org.springframework.beans.factory.annotation.Value;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PerfilModel {

       Connection con;
    private String db_port;

    public List<Map> getPerfilList() throws SQLException {
        con=coneccion.getConnection();
        Statement smtnt=con.createStatement();
        ResultSet rs=smtnt.executeQuery("select * from mob_perfiles");
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

    public List<Map> getPerfilListbyMedico(String cod_med) throws SQLException {
        con=coneccion.getConnection();
        PreparedStatement psmtnt=con.prepareStatement("select * from mob_perfiles where cod_med= ? order by id_perfiles desc");
        psmtnt.setString(1,cod_med);
        ResultSet rs=psmtnt.executeQuery();
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

    public Map getPerfilListbyId(String id_perfil) throws SQLException {
        con=coneccion.getConnection();
        PreparedStatement psmtnt=con.prepareStatement("select * from mob_perfiles where id_perfiles= ?");
        psmtnt.setString(1,id_perfil);
        ResultSet rs=psmtnt.executeQuery();
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

    public Map insertPerfil(String data) throws SQLException {
        con=coneccion.getConnection();
        CallableStatement csmtnt=con.prepareCall("call mob_add_perfil(@json_data= ? )");
        csmtnt.setString(1,data);
        ResultSet rs=csmtnt.executeQuery();
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

    public Map updatePerfil(String data) throws SQLException {
        con=coneccion.getConnection();
        CallableStatement csmtnt=con.prepareCall("call mob_upd_perfiles(@json_data= ? )");
        csmtnt.setString(1,data);
        ResultSet rs=csmtnt.executeQuery();
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

    public Map deletePerfil(String data) throws SQLException {
        con=coneccion.getConnection();
        CallableStatement csmtnt=con.prepareCall("call mob_delete_perfil(@json_data= ?) ");
        csmtnt.setString(1,data);
        ResultSet rs=csmtnt.executeQuery();
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
