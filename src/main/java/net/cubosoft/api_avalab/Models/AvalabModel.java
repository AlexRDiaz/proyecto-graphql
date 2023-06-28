package net.cubosoft.api_avalab.Models;

import org.springframework.beans.factory.annotation.Value;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class AvalabModel {
        Connection con;
    private String db_port;

    public Map MenufavxUser(String usuario) throws SQLException {
        con=coneccion.getConnection();
        PreparedStatement smtnt=con.prepareStatement("select * from mob_menufav   where usuario= ?");
        //JSONObject json_data=new JSONObject(data);
        smtnt.setString(1,usuario);
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
