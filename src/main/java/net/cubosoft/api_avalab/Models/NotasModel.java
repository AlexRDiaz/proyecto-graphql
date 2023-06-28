package net.cubosoft.api_avalab.Models;

import org.springframework.beans.factory.annotation.Value;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class NotasModel {
       Connection con;
    private String db_port;

    public Map AddNotaxOrden(String json_data)throws SQLException {
        con=coneccion.getConnection();
        CallableStatement csmtnt=con.prepareCall("call mob_add_notaxorden(@json_data=?)");
        csmtnt.setString(1,json_data);
        ResultSet rs=csmtnt.executeQuery();
        ResultSetMetaData md= rs.getMetaData();
        int nroColumnas= md.getColumnCount();
        Map filadata=new HashMap(nroColumnas);

        for (int i=1;i<=nroColumnas;i++){
            filadata.put(md.getColumnName(i),rs.getObject(i));
        }
        con.close();
        return filadata;


    }
}
