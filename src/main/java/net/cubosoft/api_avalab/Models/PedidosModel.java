package net.cubosoft.api_avalab.Models;

import org.springframework.beans.factory.annotation.Value;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PedidosModel {
       Connection con;
    private String db_port;

    public List<Map> getPedidosList() throws SQLException {
        con=coneccion.getConnection();
        Statement smtnt=con.createStatement();
        ResultSet rs=smtnt.executeQuery("select * from mob_pedidos_completa");
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
        rs.close();
        con.close();
        return listado;
    }

    public List <Map>  getPedidosbyCodMed(String cod_med) throws SQLException{
        con=coneccion.getConnection();
        PreparedStatement pstmnt=con.prepareStatement("select * from mob_pedidos_completa where cod_med = ? order by anular_pedido ,id_pedidos desc");
        pstmnt.setString(1,cod_med);
        ResultSet rs=pstmnt.executeQuery();
        ResultSetMetaData md=rs.getMetaData();
        int nroColumnas=md.getColumnCount();
        List listado=new ArrayList();
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

    public List <Map>  getPedidosbyCodMedPag(String fechai,String fechaf,String texto,String cod_med){
        List listado=new ArrayList();
        PreparedStatement pstmnt= null;
            con=coneccion.getConnection();

        try {
            pstmnt = con.prepareStatement("SELECT top 100 * FROM mob_pedidos_completa where cod_med=? and fec_ord between ? and ?  and (id_pac like ? or upper(nombre_paciente) like upper(?)) order by anular_pedido ,id_pedidos desc");

            pstmnt.setString(1,cod_med);
            pstmnt.setString(2,fechai);
            pstmnt.setString(3,fechaf+" 23:59");
            pstmnt.setString(4,"%"+texto+"%");
            pstmnt.setString(5,"%"+texto+"%");

            ResultSet rs=pstmnt.executeQuery();
            ResultSetMetaData md=rs.getMetaData();
            int nroColumnas=md.getColumnCount();

            while (rs.next()){
                Map filaData=new HashMap(nroColumnas);
                for (int i=1;i<=nroColumnas;i++){
                    filaData.put(md.getColumnName(i),rs.getObject(i));
                }
                listado.add(filaData);
            }

            pstmnt.close();
            rs.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }




        return listado;

    }

    public Map  getPedidosbyUud(String uuid) throws SQLException{
        con=coneccion.getConnection();
        PreparedStatement pstmnt=con.prepareStatement("select * from mob_pedidos_completa where uuid_pedido = ? ");
        pstmnt.setString(1,uuid);
        ResultSet rs=pstmnt.executeQuery();
        ResultSetMetaData md=rs.getMetaData();
        int nroColumnas=md.getColumnCount();
        rs.next();
        Map filaData=new HashMap(nroColumnas);
        for (int i=1 ;i<=nroColumnas;i++){
            filaData.put(md.getColumnName(i),rs.getObject(i));
        }

        con.close();
        return filaData;
    }

    public List<Map>  getAnalisisbyIdPedidos(String id_pedido) throws SQLException{
        con=coneccion.getConnection();        PreparedStatement pstmnt=con.prepareStatement("select * from mob_analisisxpedidos where id_pedidos = ? order by id_pedidos desc");
        pstmnt.setString(1,id_pedido);
        ResultSet rs=pstmnt.executeQuery();
        ResultSetMetaData md=rs.getMetaData();
        int nroColumnas=md.getColumnCount();
        List listado=new ArrayList();
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

    public Map  addPedidoc(String json_pedido,String json_analisis) throws SQLException{
        con=coneccion.getConnection();        CallableStatement cstmnt=con.prepareCall("call mob_add_pedido1(@json_data_pedido=?,@json_data_analisis=?)");
        cstmnt.setString(1,json_pedido);
        cstmnt.setString(2,json_analisis);
        ResultSet rs=cstmnt.executeQuery();
        ResultSetMetaData md=rs.getMetaData();
        int nroColumnas=md.getColumnCount();
        rs.next();
        Map filaData=new HashMap(nroColumnas);
        for (int i=1 ;i<=nroColumnas;i++){
            filaData.put(md.getColumnName(i),rs.getObject(i));
        }
        con.close();
        return filaData;
    }


    public Map  AnularPedido(String data) throws SQLException{
        con=coneccion.getConnection();        CallableStatement cstmnt=con.prepareCall("call mob_anular_pedido(@json_data=?)");
        cstmnt.setString(1,data);
        ResultSet rs=cstmnt.executeQuery();
        ResultSetMetaData md=rs.getMetaData();
        int nroColumnas=md.getColumnCount();
        rs.next();
        Map filaData=new HashMap(nroColumnas);
        for (int i=1 ;i<=nroColumnas;i++){
            filaData.put(md.getColumnName(i),rs.getObject(i));
        }
        con.close();
        return filaData;
    }

    public Map  getPedidosbyOrden(String uuid) throws SQLException{
        con=coneccion.getConnection();
        PreparedStatement pstmnt=con.prepareStatement("select * from mob_pedidos_completa where id_pedidos = ? order by anular_pedido ,id_pedidos desc");
        pstmnt.setString(1,uuid);
        ResultSet rs=pstmnt.executeQuery();
        ResultSetMetaData md=rs.getMetaData();
        int nroColumnas=md.getColumnCount();
        rs.next();
        Map filaData=new HashMap(nroColumnas);
        for (int i=1 ;i<=nroColumnas;i++){
            filaData.put(md.getColumnName(i),rs.getObject(i));
        }

        con.close();
        return filaData;
    }
}
