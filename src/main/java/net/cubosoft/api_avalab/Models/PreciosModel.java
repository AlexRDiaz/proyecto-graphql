package net.cubosoft.api_avalab.Models;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PreciosModel {
       Connection con;
    private String db_port;

    public List<Map> getPreciosList() throws SQLException {
        con=coneccion.getConnection();
        Statement smtnt=con.createStatement();
        ResultSet rs=smtnt.executeQuery("select * from moblisprecios");
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
    //ver si retorna siempre solo un dato
    public Map getPreciosbySeg(String cod_ana,String cod_lpr) throws SQLException {
        con=coneccion.getConnection();
        PreparedStatement smtnt=con.prepareStatement("SELECT * FROM mob_lisprecios where cod_ana=? AND cod_lpr=?");
        //JSONObject json_data=new JSONObject(data);
        smtnt.setString(1,cod_ana);
        smtnt.setString(2,cod_lpr);
        ResultSet rs=smtnt.executeQuery();
        ResultSetMetaData md=rs.getMetaData();
        int nroColumna=md.getColumnCount();
        rs.next();
        Map filaData=new HashMap(nroColumna);
        for (int i=1;i<=nroColumna;i++) {
            filaData.put(md.getColumnName(i), rs.getObject(i));
        }

        con.close();
        return filaData;
    }
}
