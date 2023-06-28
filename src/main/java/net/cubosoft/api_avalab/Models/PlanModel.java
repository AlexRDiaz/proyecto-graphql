package net.cubosoft.api_avalab.Models;
import org.springframework.beans.factory.annotation.Value;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlanModel {

       Connection con;
    private String db_port;

    public List<Map> getPlanxsegList() throws SQLException {
        con=coneccion.getConnection();
        Statement smtnt=con.createStatement();
        ResultSet rs=smtnt.executeQuery("select * from mob_planxseg");
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

    public List<Map> getPlanxsegListbyidseg(String data) throws SQLException {
        con=coneccion.getConnection();
        PreparedStatement psmtnt=con.prepareStatement("select * from mob_planxseg where cod_seg= ?");
        psmtnt.setString(1,data);
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
}
