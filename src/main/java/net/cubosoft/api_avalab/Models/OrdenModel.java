package net.cubosoft.api_avalab.Models;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrdenModel {
       Connection con;
    private String db_port;

    public List<Map> getOrdenList() throws SQLException {
        con=coneccion.getConnection();
        Statement smtnt=con.createStatement();
        ResultSet rs=smtnt.executeQuery("select Top 300 * from mob_orden order by fec_ord desc");
        ResultSetMetaData md=rs.getMetaData();
        int nroColumnas= md.getColumnCount();
        List listado=new ArrayList<>();
        while (rs.next()){
            Map filaData=new HashMap(nroColumnas);
            for (int i=1;i<=nroColumnas;i++){
                filaData.put(md.getColumnName(i),rs.getObject(i));
            }
            listado.add(filaData);
        }
        con.close();
        return listado;
    }

    public List<Map> getOrdenbyUbicacion(String cod_ori) throws SQLException {
        con=coneccion.getConnection();
        PreparedStatement psmtnt=con.prepareStatement("select Top 300 * from mob_orden where cod_ori= ? order by nro_ord desc");
        psmtnt.setString(1,cod_ori);
        ResultSet rs=psmtnt.executeQuery();
        ResultSetMetaData md=rs.getMetaData();
        int nroColumnas= md.getColumnCount();
        List listado=new ArrayList<>();
        while (rs.next()){
            Map filaData=new HashMap(nroColumnas);
            for (int i=1;i<=nroColumnas;i++){
                filaData.put(md.getColumnName(i),rs.getObject(i));
            }
            listado.add(filaData);
        }
        con.close();
        return listado;
    }

    public List<Map> getOrdenbyNum(int nroD, int nroH) throws SQLException {
        con=coneccion.getConnection();
      //  JSONObject json = new JSONObject(data);

        PreparedStatement psmtnt=con.prepareStatement("SELECT Top 300 * FROM mob_orden where nro_ord>=? and nro_ord<=? order by nro_ord desc");
        psmtnt.setString(1,nroD+"");
        psmtnt.setString(2,nroH+"");
        ResultSet rs=psmtnt.executeQuery();
        ResultSetMetaData md=rs.getMetaData();
        int nroColumnas= md.getColumnCount();
        List listado=new ArrayList<>();
        while (rs.next()){
            Map filaData=new HashMap(nroColumnas);
            for (int i=1;i<=nroColumnas;i++){
                filaData.put(md.getColumnName(i),rs.getObject(i));
            }
            listado.add(filaData);
        }
        con.close();
        return listado;
    }

    public Map getOrdenbyNroOrd(int nroOrd) throws SQLException {
        con=coneccion.getConnection();
    System.out.println("nro "+nroOrd);
        PreparedStatement psmtnt=con.prepareStatement("SELECT * FROM mob_orden where nro_ord=?");

        psmtnt.setInt(1,nroOrd);
        ResultSet rs=psmtnt.executeQuery();
        ResultSetMetaData md=rs.getMetaData();
        int nroColumnas= md.getColumnCount();
        //List listado=new ArrayList<>();
       rs.next();
            Map filaData=new HashMap(nroColumnas);
            for (int i=1;i<=nroColumnas;i++){
                filaData.put(md.getColumnName(i),rs.getObject(i));
            }

        con.close();
        return filaData;
    }

    public List<Map> getOrdenbyFecha(String fechaD,String fechaH) throws SQLException {
        con=coneccion.getConnection();
        //JSONObject json = new JSONObject(data);

        PreparedStatement psmtnt=con.prepareStatement("SELECT Top 300 * FROM mob_orden where fec_ord>=? and fec_ord<=?");
        psmtnt.setString(1,fechaD);
        psmtnt.setString(2,fechaH);
        ResultSet rs=psmtnt.executeQuery();
        ResultSetMetaData md=rs.getMetaData();
        int nroColumnas= md.getColumnCount();
        List listado=new ArrayList<>();
        while (rs.next()){
            Map filaData=new HashMap(nroColumnas);
            for (int i=1;i<=nroColumnas;i++){
                filaData.put(md.getColumnName(i),rs.getObject(i));
            }
            listado.add(filaData);
        }
        con.close();
        return listado;
    }

    public List<Map> getOrdenbyCodMed(String data) throws SQLException {
        con=coneccion.getConnection();
        JSONObject json = new JSONObject(data);
        PreparedStatement psmtnt=con.prepareStatement("SELECT Top 300 * FROM mob_orden where cod_med=?");
        psmtnt.setString(1,json.getString("cod_med"));
        ResultSet rs=psmtnt.executeQuery();
        ResultSetMetaData md=rs.getMetaData();
        int nroColumnas= md.getColumnCount();
        List listado=new ArrayList<>();
        while (rs.next()){
            Map filaData=new HashMap(nroColumnas);
            for (int i=1;i<=nroColumnas;i++){
                filaData.put(md.getColumnName(i),rs.getObject(i));
            }
            listado.add(filaData);
        }
        con.close();
        return listado;
    }

    public List<Map> getOrdenbyCodPac(String data) throws SQLException {
        con=coneccion.getConnection();
        JSONObject json = new JSONObject(data);
        PreparedStatement psmtnt=con.prepareStatement("SELECT Top 300 * FROM mob_orden where cod_pac=?");
        psmtnt.setString(1,json.getString("cod_pac"));
        ResultSet rs=psmtnt.executeQuery();
        ResultSetMetaData md=rs.getMetaData();
        int nroColumnas= md.getColumnCount();
        List listado=new ArrayList<>();
        while (rs.next()){
            Map filaData=new HashMap(nroColumnas);
            for (int i=1;i<=nroColumnas;i++){
                filaData.put(md.getColumnName(i),rs.getObject(i));
            }
            listado.add(filaData);
        }
        con.close();
        return listado;
    }

    public List<Map> getOrdenbyEstatus(String cod_sts) throws SQLException {
        con=coneccion.getConnection();
        //JSONObject json = new JSONObject(data);
        PreparedStatement psmtnt=con.prepareStatement("SELECT Top 300 * FROM mob_orden where sts_ord=?");
        psmtnt.setString(1,cod_sts);
        ResultSet rs=psmtnt.executeQuery();
        ResultSetMetaData md=rs.getMetaData();
        int nroColumnas= md.getColumnCount();
        List listado=new ArrayList<>();
        while (rs.next()){
            Map filaData=new HashMap(nroColumnas);
            for (int i=1;i<=nroColumnas;i++){
                filaData.put(md.getColumnName(i),rs.getObject(i));
            }
            listado.add(filaData);
        }
        con.close();
        return listado;
    }

    public Map addOrden(String data) throws SQLException {
        con=coneccion.getConnection();
        CallableStatement csmtnt=con.prepareCall("call mob_add_orden_json(@json_data=?)");
        csmtnt.setString(1,data);
        ResultSet rs=csmtnt.executeQuery();
        ResultSetMetaData md=rs.getMetaData();
        int nroColumnas= md.getColumnCount();
        rs.next();
            Map filaData=new HashMap(nroColumnas);
            for (int i=1;i<=nroColumnas;i++){
                filaData.put(md.getColumnName(i),rs.getObject(i));
            }

        con.close();
        return filaData;
    }

    public Map addOrdenComplete(String json_orden,String json_muestras,String json_analisis) throws SQLException {
        con=coneccion.getConnection();
        CallableStatement csmtnt=con.prepareCall("call mob_json_orden(@json_orden=?, @json_peticiones=?,@json_muestras=?)");
        csmtnt.setString(1,json_orden);
        csmtnt.setString(2,json_analisis);
        csmtnt.setString(3,json_muestras);
        ResultSet rs=csmtnt.executeQuery();
        ResultSetMetaData md=rs.getMetaData();
        int nroColumnas= md.getColumnCount();
        rs.next();
        Map filaData=new HashMap(nroColumnas);
        for (int i=1;i<=nroColumnas;i++){
            filaData.put(md.getColumnName(i),rs.getObject(i));
        }

        con.close();
        return filaData;
    }

    public Map addPagoxOrden(String json_pago) throws SQLException {
        con=coneccion.getConnection();
        CallableStatement csmtnt=con.prepareCall("call mob_add_pagoxorden(@json_data=?)");
        csmtnt.setString(1,json_pago);

        ResultSet rs=csmtnt.executeQuery();
        ResultSetMetaData md=rs.getMetaData();
        int nroColumnas= md.getColumnCount();
        rs.next();
        Map filaData=new HashMap(nroColumnas);
        for (int i=1;i<=nroColumnas;i++){
            filaData.put(md.getColumnName(i),rs.getObject(i));
        }

        con.close();
        return filaData;
    }

    public List<Map> getPagxOrden(String nro_ord) throws SQLException {
        con=coneccion.getConnection();
        PreparedStatement psmtnt=con.prepareCall("select * from mob_pagxord where nro_ord=?");
        psmtnt.setString(1,nro_ord);

        ResultSet rs=psmtnt.executeQuery();
        ResultSetMetaData md=rs.getMetaData();
        int nroColumnas= md.getColumnCount();

        List listado=new ArrayList<Map>();
        rs.next();
        while (rs.next()){
            Map filaData=new HashMap(nroColumnas);
            for (int i=1;i<=nroColumnas;i++){
                filaData.put(md.getColumnName(i),rs.getObject(i));
            }
            listado.add(filaData);
        }


        con.close();
        return listado;
    }

    public List<Map> getMstxOrden(String data) throws SQLException {
        con=coneccion.getConnection();
        PreparedStatement psmtnt=con.prepareCall("select * from mob_mstxorden where nro_ord=?");
        psmtnt.setString(1,data);

        ResultSet rs=psmtnt.executeQuery();
        ResultSetMetaData md=rs.getMetaData();
        int nroColumnas= md.getColumnCount();

        List listado=new ArrayList<Map>();
        rs.next();
        while (rs.next()){
            Map filaData=new HashMap(nroColumnas);
            for (int i=1;i<=nroColumnas;i++){
                filaData.put(md.getColumnName(i),rs.getObject(i));
            }
            listado.add(filaData);
        }


        con.close();
        return listado;
    }

    public List<Map> getOrdentoResultados(String codigo,String tipo) throws SQLException {
        con=coneccion.getConnection();

        //JSONObject json_data=new JSONObject(data);
        //String tipo="";
        if(tipo=="med"){tipo="cod_med";}
        if(tipo=="pat"){tipo="cod_pac";}
        if(tipo=="ref"){tipo="cod_ref";}
        if(tipo=="ord"){tipo="nro_ord";}

        PreparedStatement psmtnt=con.prepareCall("SELECT * FROM mob_orden_resultados where"+tipo+"=? order by nro_ord desc");
        psmtnt.setString(1,codigo);

        ResultSet rs=psmtnt.executeQuery();
        ResultSetMetaData md=rs.getMetaData();
        int nroColumnas= md.getColumnCount();

        List listado=new ArrayList<Map>();
        rs.next();
        while (rs.next()){
            Map filaData=new HashMap(nroColumnas);
            for (int i=1;i<=nroColumnas;i++){
                filaData.put(md.getColumnName(i),rs.getObject(i));
            }
            listado.add(filaData);
        }


        con.close();
        return listado;
    }

}
