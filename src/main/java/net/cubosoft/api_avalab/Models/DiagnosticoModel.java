package net.cubosoft.api_avalab.Models;

import org.springframework.beans.factory.annotation.Value;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiagnosticoModel {

       Connection con;
    private String db_port;

    public List<Map> getDiagnosticosList() throws SQLException{
        con=coneccion.getConnection();
        Statement stmt=con.createStatement();
        ResultSet rs= stmt.executeQuery("SELECT * FROM cs_iessanexo where tipo = 'CIE10' order by codigo asc");
        ResultSetMetaData md=rs.getMetaData();
        int nroColumnas=md.getColumnCount();
        List listado=new ArrayList();
        while (rs.next()){
            Map datafila= new HashMap<>(nroColumnas);
            for(int i=1;i<=nroColumnas;i++){
                datafila.put(md.getColumnName(i),rs.getObject(i));
            }
            listado.add(datafila);
        }
        con.close();
        return listado;

    }

    public List<Map> searchDiagnostico(String des_diagnostico) throws SQLException{
        con=coneccion.getConnection();
        PreparedStatement pstmt=con.prepareStatement("select top 15 * from cs_iessanexo where tipo = 'CIE10' and descripcion like ? order by codigo asc");
        pstmt.setString(1,des_diagnostico);
        ResultSet rs=pstmt.executeQuery();
        ResultSetMetaData md=rs.getMetaData();
        int nrocolumnas= md.getColumnCount();
        List listado= new ArrayList();

        while (rs.next()){
            Map datafila=new HashMap(nrocolumnas);
            for (int i=1;i<=nrocolumnas;i++){
                datafila.put(md.getColumnName(i),rs.getObject(i));
            }
            listado.add(datafila);
        }
        con.close();
        return listado;
    }

    public List<Map> searchDiagnosticoDesCod(String des_diagnostico,String des_diagnostico_cod) throws SQLException{
        con=coneccion.getConnection();
        PreparedStatement pstmt=con.prepareStatement("select top 15 * from cs_iessanexo where tipo = 'CIE10' and descripcion like ? or codigo= ? order by codigo asc");
        pstmt.setString(1,des_diagnostico);
        pstmt.setString(2,des_diagnostico_cod);
        ResultSet rs=pstmt.executeQuery();
        ResultSetMetaData md=rs.getMetaData();
        int nrocolumnas= md.getColumnCount();
        List listado= new ArrayList();

        while (rs.next()){
            Map datafila=new HashMap(nrocolumnas);
            for (int i=1;i<=nrocolumnas;i++){
                datafila.put(md.getColumnName(i),rs.getObject(i));
            }
            listado.add(datafila);
        }
        con.close();
        return listado;
    }

    public Map getDiagnosticobyCodigo(String id_diagnostico) throws SQLException{
        Map listado= new HashMap();
        if(id_diagnostico=="" || id_diagnostico==null){
            return  listado;
        }
        con=coneccion.getConnection();
        PreparedStatement pstmt= con.prepareStatement("select * from cs_iessanexo where codigo= ?");
        pstmt.setString(1,id_diagnostico);
        ResultSet rs= pstmt.executeQuery();
        ResultSetMetaData md=rs.getMetaData();
        int nroColumnas= md.getColumnCount();
        Map filaresulatdo=new HashMap(nroColumnas);
        rs.next();
        for (int i=1;i<=nroColumnas;i++){
            filaresulatdo.put(md.getColumnName(i),rs.getObject(i));
        }
        con.close();
        return filaresulatdo;
    }
}
