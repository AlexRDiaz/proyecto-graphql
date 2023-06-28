package net.cubosoft.api_avalab.Models;

import org.springframework.beans.factory.annotation.Value;

import java.sql.*;
import java.util.*;

public class AnalisisModel {

        Connection con;

    public List<Map> getAnalisisList()throws SQLException {
       con= con=coneccion.getConnection();

        Statement stmt=con.createStatement();

        List<Map> list = new ArrayList<Map>();

        ResultSet rs = stmt.executeQuery("select top 20 * from mob_analisis order by cod_ana asc");
        ResultSetMetaData md = rs.getMetaData(); //Get the structure information of the result set (RS), such as the number of fields, field name, etc.
        int columnCount = md.getColumnCount(); //Returns the number of columns in this ResultSet object

        Map rowData = new HashMap();

        while (rs.next()) {
            rowData = new HashMap(columnCount);
            for (int i = 1; i <= columnCount; i++) {
                rowData.put(md.getColumnName(i).toLowerCase(), rs.getObject(i));
            }
            list.add(rowData);
            System.out.println("list:" + list.toString());
        }
        con.close();
        return list;

    }

    public List<Map> searchAnalisis(String des_ana)throws SQLException{
        con=coneccion.getConnection();
        PreparedStatement pstmt=con.prepareStatement("select top 20 * from mob_analisis where des_ana like ? order by cod_ana asc");
        pstmt.setString(1,des_ana);
        List<Map> list = new ArrayList<Map>();
        ResultSet rs =pstmt.executeQuery();
        ResultSetMetaData md = rs.getMetaData();
        int columnCount = md.getColumnCount(); //Returns the number of columns in this ResultSet object
        Map rowData;
        while (rs.next()){
            rowData=new HashMap(columnCount);
            for (int i=1;i<=columnCount;i++){
                rowData.put(md.getColumnName(i).toLowerCase(),rs.getObject(i));
            }
            list.add(rowData);
        }

        con.close();
        return list;
    }

    public List<Map> searchAnalisisMuestras(String des_ana)throws  SQLException{
        con=coneccion.getConnection();
        PreparedStatement pstmt =con.prepareStatement("select top 20 * from mob_analisis_mstrs where des_ana like ? order by des_ana asc");
        pstmt.setString(1,des_ana);

        List<Map> listado = new ArrayList<Map>();
        ResultSet rs=pstmt.executeQuery();
        ResultSetMetaData md=rs.getMetaData();
        int columnCount =md.getColumnCount();
        Map rowData;
        while(rs.next()){
            rowData=new HashMap(columnCount);
            for (int i=1;i<=columnCount;i++){
                rowData.put(md.getColumnName(i).toLowerCase(),rs.getObject(i));
            }
            listado.add(rowData);
        }
        con.close();
        return listado;
    }

    public List<Map> getAnalisisByCod(String cod_ana)throws SQLException{
        con=coneccion.getConnection();
        PreparedStatement pstmt=con.prepareStatement("select * from mob_analisis where cod_ana=?");
        pstmt.setString(1,cod_ana);

        ResultSet rs=pstmt.executeQuery();
        ResultSetMetaData md=rs.getMetaData();
        int nroColumnas=md.getColumnCount();

        List listado=new ArrayList<Map>();

        while (rs.next()){
            Map  dataFila=new HashMap(nroColumnas);
            for (int i=1;i<=nroColumnas;i++){
                dataFila.put(md.getColumnName(i).toLowerCase(),rs.getObject(i));
            }
            listado.add(dataFila);
        }
        con.close();
        return  listado;
    }

    public List<Map> getAnalisisMuestrasbyCod(String cod_ana)throws SQLException{
        con=coneccion.getConnection();
        PreparedStatement pstmt=con.prepareStatement("select * from mob_analisis_mstrs where cod_ana=?");
        pstmt.setString(1,cod_ana);

        ResultSet rs=pstmt.executeQuery();
        ResultSetMetaData md=rs.getMetaData();
        int nroColumnas=md.getColumnCount();

        List listado=new ArrayList<Map>();

        while (rs.next()){
            Map  dataFila=new HashMap(nroColumnas);
            for (int i=1;i<=nroColumnas;i++){
                dataFila.put(md.getColumnName(i).toLowerCase(),rs.getObject(i));
            }
            listado.add(dataFila);
        }
        rs.close();
        pstmt.close();
        con.close();
        return  listado;
    }

    public List<Map> getAnalisisxOrd(String nro_ord)throws SQLException{
        con=coneccion.getConnection();
        PreparedStatement pstmt=con.prepareStatement("select * from mob_petxord where nro_ord=?");
        pstmt.setString(1,nro_ord);
        ResultSet rs=pstmt.executeQuery();
        ResultSetMetaData md=rs.getMetaData();
        int nroColumnas=md.getColumnCount();
        List listado=new ArrayList<Map>();
        while (rs.next()){
            Map  dataFila=new HashMap(nroColumnas);
            for (int i=1;i<=nroColumnas;i++){
                dataFila.put(md.getColumnName(i).toLowerCase(),rs.getObject(i));
            }
            listado.add(dataFila);
        }
        con.close();
        return  listado;
    }

    public Map AddPetxOrden(String data)throws SQLException{
        con=coneccion.getConnection();
        CallableStatement cstmt=con.prepareCall("call mob_add_peticiones_json(@json_data=?");
        cstmt.setString(1,data);

        ResultSet rs =cstmt.executeQuery();
        ResultSetMetaData md=rs.getMetaData();
        int nroColumnas =md.getColumnCount();
        rs.next();
        Map filaData=new HashMap(nroColumnas);
        for (int i=1;i<=nroColumnas;i++){
            filaData.put(md.getColumnName(i),rs.getObject(i));
        }
        con.close();
        return filaData;
    }

    public List<Map> searchAnalisisByNom(String des_ana)throws SQLException{
        con=coneccion.getConnection();
        PreparedStatement pstmt=con.prepareStatement("select top 10 * from mob_analisis_mstrs where des_ana like ? order by des_ana asc");
        pstmt.setString(1,des_ana);
        ResultSet rs =pstmt.executeQuery();
        ResultSetMetaData md=rs.getMetaData();
        int nroColumnas =md.getColumnCount();
        List listado=new ArrayList<Map>();
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

    public List<Map> getAnalisisxPedidobyIdPedido(String id_pedido) throws  SQLException{
        con=coneccion.getConnection();
        PreparedStatement pstmt=con.prepareStatement("select  * from mob_analisisxpedido_completo where id_pedidos=? order by des_ana asc");
        pstmt.setString(1,id_pedido);
        ResultSet rs =pstmt.executeQuery();
        ResultSetMetaData md=rs.getMetaData();
        int nroColumnas =md.getColumnCount();
        List listado=new ArrayList<Map>();
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
