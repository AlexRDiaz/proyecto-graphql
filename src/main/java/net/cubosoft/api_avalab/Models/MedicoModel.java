package net.cubosoft.api_avalab.Models;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MedicoModel {
       Connection con;
    private String db_port;


    public List<Map> getMedicosList() throws SQLException {
        con=coneccion.getConnection();
        Statement stmt=con.createStatement();

        List<Map> list = new ArrayList<Map>();
        String sql="select * from ava_lismed";
        ResultSet rs = stmt.executeQuery(sql);
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

    public Map insertMedico(String data) throws SQLException {
        con=coneccion.getConnection();
        CallableStatement cstmt; // para ejecutar el procedimiento
        cstmt=con.prepareCall("call mob_insert_medico(@json_data=?)");
        cstmt.setString(1,data);
        ResultSet rs = cstmt.executeQuery();
        ResultSetMetaData md = rs.getMetaData(); //Get the structure information of the result set (RS), such as the number of fields, field name, etc.
        int columnCount = md.getColumnCount(); //Returns the number of columns in this ResultSet object
        Map rowData = new HashMap();
        rowData = new HashMap(columnCount);
        //Quitamos while porque solo retornara una fila
        rs.next();
            for (int i = 1; i <= columnCount; i++) {
                rowData.put(md.getColumnName(i).toLowerCase(), rs.getObject(i));
            }
            con.close();
        return rowData;
    }

    public Map insertMedicoLite(String data) throws SQLException {
        con=coneccion.getConnection();
        CallableStatement cstmt; // para ejecutar el procedimiento
        cstmt=con.prepareCall("call mob_add_medico_lite(@json_data=?)");
        cstmt.setString(1,data);
        ResultSet rs = cstmt.executeQuery();
        ResultSetMetaData md = rs.getMetaData(); //Get the structure information of the result set (RS), such as the number of fields, field name, etc.
        int columnCount = md.getColumnCount(); //Returns the number of columns in this ResultSet object
        Map rowData = new HashMap();
        rowData = new HashMap(columnCount);
        //Quitamos while porque solo retornara una fila
        rs.next();
        for (int i = 1; i <= columnCount; i++) {
            rowData.put(md.getColumnName(i).toLowerCase(), rs.getObject(i));
        }
        con.close();
        return rowData;
    }

    public Map getMedicobyId(String data) throws SQLException{
        con=coneccion.getConnection();        PreparedStatement  pstmt=con.prepareStatement("SELECT * FROM mob_medicos where cod_med=?");
        pstmt.setString(1,data);

        ResultSet rs = pstmt.executeQuery();
        ResultSetMetaData md = rs.getMetaData(); //Get the structure information of the result set (RS), such as the number of fields, field name, etc.
        int columnCount = md.getColumnCount(); //Returns the number of columns in this ResultSet object
        Map rowData = new HashMap();
        rowData = new HashMap(columnCount);
        //Quitamos while porque solo retornara una fila
        rs.next();
        for (int i = 1; i <= columnCount; i++) {
            rowData.put(md.getColumnName(i).toLowerCase(), rs.getObject(i));
        }
        con.close();
        return rowData;
    }

    public Map getMedicobyCed(String data) throws SQLException{
        con=coneccion.getConnection();        PreparedStatement  pstmt=con.prepareStatement("SELECT * FROM mob_medicos where id_med=?");
        pstmt.setString(1,data);

        ResultSet rs = pstmt.executeQuery();
        ResultSetMetaData md = rs.getMetaData(); //Get the structure information of the result set (RS), such as the number of fields, field name, etc.
        int columnCount = md.getColumnCount(); //Returns the number of columns in this ResultSet object
        Map rowData = new HashMap();
        rowData = new HashMap(columnCount);
        //Quitamos while porque solo retornara una fila
        rs.next();
        for (int i = 1; i <= columnCount; i++) {
            rowData.put(md.getColumnName(i).toLowerCase(), rs.getObject(i));
        }
        con.close();
        return rowData;
    }

    public List<Map> getMedicoSearch(String nom_med,String id_med) throws SQLException{
        con=coneccion.getConnection();
        PreparedStatement pstmt=null;
       // JSONObject json = new JSONObject(data); //JSONObject is lybrary

         nom_med="%"+nom_med+"%";

        if(nom_med!="%%" && id_med!="" ){
            pstmt= con.prepareStatement("select top 20 * from mob_medicos where nom_med like ? or id_med = ? ORDER BY cod_med asc");
            pstmt.setString(1,nom_med);
            pstmt.setString(2,id_med);
        }

        if(nom_med!="%%" && id_med=="" ){
            pstmt= con.prepareStatement("select top 20 * from mob_medicos where nom_med like ? ORDER BY cod_med asc");
            pstmt.setString(1,nom_med);

        }
        if(nom_med=="%%" && id_med!="" ){
            pstmt= con.prepareStatement("select top 20 * from mob_medicos where id_med = ? ORDER BY cod_med asc");
            pstmt.setString(1,id_med);

        }
        List<Map> list = new ArrayList<Map>();
        ResultSet rs = pstmt.executeQuery();
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

    public List<Map> SearchMedico(String text)throws SQLException{
        con=coneccion.getConnection();
        PreparedStatement pstmt=null;
       // JSONObject json = new JSONObject(data); //JSONObject is lybrary

        text="%"+text+"%";
        List<Map> list = new ArrayList<Map>();
        if(text=="%%"){
          list=null;
        }
        else{
            pstmt=con.prepareStatement("select top 10 * from mob_medicos where nom_med like ?  ORDER BY cod_med asc");
            pstmt.setString(1,text);

            ResultSet rs = pstmt.executeQuery();
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
            }
        con.close();
        return list;
    }

    public Map updateMedico(String data)throws SQLException{
        con=coneccion.getConnection();
        CallableStatement cstmt; // para ejecutar el procedimiento
        cstmt=con.prepareCall("call mob_upd_med(@json_data=?)");
        cstmt.setString(1,data);
        ResultSet rs = cstmt.executeQuery();
        ResultSetMetaData md = rs.getMetaData(); //Get the structure information of the result set (RS), such as the number of fields, field name, etc.
        int columnCount = md.getColumnCount(); //Returns the number of columns in this ResultSet object
        Map rowData = new HashMap();
        rowData = new HashMap(columnCount);
        //Quitamos while porque solo retornara una fila
        rs.next();
        for (int i = 1; i <= columnCount; i++) {
            rowData.put(md.getColumnName(i).toLowerCase(), rs.getObject(i));
        }
        con.close();
        return rowData;

    }
}
