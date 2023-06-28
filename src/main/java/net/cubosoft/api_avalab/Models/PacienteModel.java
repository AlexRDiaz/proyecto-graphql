package net.cubosoft.api_avalab.Models;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PacienteModel {
       Connection con;
    private String db_port;

    public List<Map> getPacientesList() throws SQLException{
        con=coneccion.getConnection();
        Statement smtnt=con.createStatement();
        ResultSet rs=smtnt.executeQuery("select * from mob_pacientes order by cod_pac desc");
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
    public Map  getPacientebyCodPac(String cod_pac) throws SQLException{
        con=coneccion.getConnection();
        PreparedStatement pstmnt=con.prepareStatement("select * from mob_pacientes where cod_pac= ?");
        pstmnt.setString(1,cod_pac+"" );
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

    public Map  getPacienteByCedula(String cedula) throws SQLException{
        con=coneccion.getConnection();
        CallableStatement cstmtn=con.prepareCall("call ava_check_cedula(?)");
        cstmtn.setString(1,cedula);
        //cstmtn.executeQuery();
        System.out.println("cstmtn.execute();"+cstmtn.execute());
        //rscall.next();
        //revisaar si se puede controlar que se ejcuto el procedimiento para continuar
        PreparedStatement pstmnt=con.prepareStatement("select * from mob_pacientes where id_pac= ?");
        pstmnt.setString(1,cedula);
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

    public List<Map>  getPacienteSearch(String nom_pac,String ape_pac) throws SQLException{
        con=coneccion.getConnection();     //   JSONObject json_data=new JSONObject(data);

        PreparedStatement pstmnt=null;

        if(nom_pac != "%%" && ape_pac != "%%") {
            pstmnt=con.prepareStatement("select top 20 * from mob_pacientes where nom_pac like ? or ape_pac like ? ORDER BY cod_pac asc");
            pstmnt.setString(1,nom_pac);
            pstmnt.setString(2,ape_pac);
        }

        if (nom_pac != "%%" && ape_pac == "%%") {
            pstmnt=con.prepareStatement("select top 20 * from mob_pacientes where nom_pac like ? ORDER BY cod_pac asc");
            pstmnt.setString(1,nom_pac);
        }
        if (nom_pac == "%%" && ape_pac != "%%") {
            pstmnt=con.prepareStatement("select top 20 * from mob_pacientes where ape_pac like ? ORDER BY cod_pac asc");
            pstmnt.setString(2,ape_pac);
        }

        ResultSet rs=pstmnt.executeQuery();
        ResultSetMetaData md=rs.getMetaData();
        int nroColumnas=md.getColumnCount();
        List listado=new ArrayList();

        while (rs.next()){
            Map filaData=new HashMap(nroColumnas);
            for (int i=1 ;i<=nroColumnas;i++){
                filaData.put(md.getColumnName(i),rs.getObject(i));
            }
            listado.add(filaData);
        }

        con.close();
        return listado;
    }

    public Map insertPaciente(String data) throws SQLException{

        con=coneccion.getConnection();        CallableStatement cstmtn=con.prepareCall("call mob_insert_paciente(?)");
        cstmtn.setString(1,data);

        ResultSet rs=cstmtn.executeQuery();
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

    public Map insertPacientelite(String json_data) throws SQLException{

        con=coneccion.getConnection();        CallableStatement cstmtn=con.prepareCall("call mob_add_paciente_lite(@json_data=?)");
        cstmtn.setString(1,json_data);

        ResultSet rs=cstmtn.executeQuery();
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

    public Map updatePaciente(String json_data) throws SQLException{

        con=coneccion.getConnection();        CallableStatement cstmtn=con.prepareCall("call mob_update_pac(@json_data=?)");
        cstmtn.setString(1,json_data);

        ResultSet rs=cstmtn.executeQuery();
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

    public List<Map>  SearchPaciente(String text,String tipo) throws SQLException{
        con=coneccion.getConnection();        //JSONObject json_data=new JSONObject(data);

        PreparedStatement pstmnt=null;
        List listado=new ArrayList();

        if(text=="%%"){
            return  listado;
        }
        else{
            if(tipo=="normal"){
                pstmnt=con.prepareStatement("select top 10 * from mob_pacientes where nom_pac like ? or ape_pac like ? ORDER BY cod_pac asc");
                pstmnt.setString(1,text);
                pstmnt.setString(2,text);

            }
            if(tipo=="complete"){
                pstmnt=con.prepareStatement("select * from mob_pacientes where nom_pac like ? or ape_pac like ? ORDER BY cod_pac asc");
                pstmnt.setString(1,text);
                pstmnt.setString(2,text);
            }
            // $listado = $result->fetchAll();
            ResultSet rs= pstmnt.executeQuery();
            ResultSetMetaData md=rs.getMetaData();
            int nroColumnas=md.getColumnCount();

            while (rs.next()){
                Map filaData=new HashMap(nroColumnas);
                for (int i=1 ;i<=nroColumnas;i++){
                    filaData.put(md.getColumnName(i),rs.getObject(i));
                }
                listado.add(filaData);
            }

            con.close();
            return listado;
        }


    }

}
