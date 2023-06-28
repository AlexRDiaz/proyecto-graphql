package net.cubosoft.api_avalab.graphqlControllers;

import graphql.schema.DataFetcher;
import net.cubosoft.api_avalab.Models.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Component
public class GraphQLDataFetchersQuerys {

    public DataFetcher test(){
        return dataFetchingEnvironment -> {
        //    EstatusModel estatusModel=new EstatusModel();
            Map testdata=new HashMap();
            testdata.put("mensaje","Exitosa");
            return testdata;
        } ;
    }

    public DataFetcher ListEstatus(){
        return dataFetchingEnvironment -> {
            EstatusModel estatusModel=new EstatusModel();
            return estatusModel.getEstatusList();
        } ;
    }

    public DataFetcher ListOrigen(){
        return dataFetchingEnvironment -> {
            OrigenModel origenModel=new OrigenModel();
            return origenModel.getOrigenList();
        } ;
    }

    public DataFetcher ListAnalisis(){
        return dataFetchingEnvironment -> {
            AnalisisModel analisisModel=new AnalisisModel();
            return analisisModel.getAnalisisList();
        } ;
    }

    public DataFetcher ListSeguro(){
        return dataFetchingEnvironment -> {
            SeguroModel seguroModel=new SeguroModel();
            return seguroModel.getSeguroList();
        } ;
    }

    public DataFetcher ListUbicaciones(){
        return dataFetchingEnvironment -> {
            UbicacionModel ubicacionModel=new UbicacionModel();
            return ubicacionModel.getUbicacionList();
        } ;
    }

    public DataFetcher ListUnidad(){
        return dataFetchingEnvironment -> {
            UnidadModel unidadModel=new UnidadModel();
            return unidadModel.getUnidadList();
        } ;
    }

    public DataFetcher ListReferencia(){
        return dataFetchingEnvironment -> {
            ReferenciaModel referenciaModel=new ReferenciaModel();
            return referenciaModel.getReferenciaList();
        } ;
    }

    public DataFetcher ListOrdenCompleto(){
        return dataFetchingEnvironment -> {
            OrdenModel ordenModel=new OrdenModel();
            String cod_ori = dataFetchingEnvironment.getArgument("cod_ori");
            return ordenModel.getOrdenbyUbicacion(cod_ori);
        } ;
    }

   public DataFetcher ListMedicos(){
        return dataFetchingEnvironment -> {
            MedicoModel medicoModel=new MedicoModel();
            return medicoModel.getMedicosList();
        } ;
    }

    public DataFetcher searchMedico(){
        return  dataFetchingEnvironment ->{
            MedicoModel medicoModel=new MedicoModel();
            String nom_med = dataFetchingEnvironment.getArgument("nom_med");
            String id_med = dataFetchingEnvironment.getArgument("nom_med");
            return medicoModel.getMedicoSearch(nom_med,id_med);
        };
    }

    public DataFetcher searchMedico2(){
        return  dataFetchingEnvironment ->{
            MedicoModel medicoModel=new MedicoModel();
            String text = dataFetchingEnvironment.getArgument("text");
            return medicoModel.SearchMedico(text);
        };
    }

   public DataFetcher insertMedico(){
        return  dataFetchingEnvironment ->{
            MedicoModel medicoModel=new MedicoModel();
            String data = dataFetchingEnvironment.getArgument("jsonMedico");
            return medicoModel.insertMedico(data);
        };
    }

    public DataFetcher getMedicobyCod(){
        return dataFetchingEnvironment -> {
            String cod_med="";

            Map<Map,Map> rootData= dataFetchingEnvironment.getSource();
            if(rootData!=null){
               // System.out.println("rootData.get(cod_med) "+rootData.get(cod_med));
                cod_med=rootData.get("cod_med")+"";
            }
            else {
                cod_med = dataFetchingEnvironment.getArgument("cod_med");
            }
           MedicoModel medicoModel=new MedicoModel();
            return medicoModel.getMedicobyId(cod_med);
        } ;
    }

    public DataFetcher getMedicobyCed(){
        return dataFetchingEnvironment -> {
            String data = dataFetchingEnvironment.getArgument("id_med");
            MedicoModel medicoModel=new MedicoModel();
            return medicoModel.getMedicosList();
        } ;
    }

    public DataFetcher getPaciente(){
        return dataFetchingEnvironment -> {
            String id_pac = dataFetchingEnvironment.getArgument("id_pac");
            PacienteModel pacienteModel=new PacienteModel();
            return pacienteModel.getPacienteByCedula(id_pac);
        } ;
    }
//----------------Añadido--------------
    public DataFetcher getUbicacionCod(){
        return dataFetchingEnvironment -> {
            String cod_ori="";
            Map<String,BigDecimal> root = dataFetchingEnvironment.getSource();
            if(root !=null) {
                cod_ori = root.get("cod_ori") + "";
            }

            UbicacionModel ubicacionModel=new UbicacionModel();
                return ubicacionModel.getUbicacionListbyCod(cod_ori);

        } ;
    }

    public DataFetcher getPlanxseguro(){
        return dataFetchingEnvironment -> {
            String cod_seg="";
            Map<String,BigDecimal> root = dataFetchingEnvironment.getSource();
            if(root !=null) {
                cod_seg = root.get("cod_seg") + "";
            }

            PlanModel planModel=new PlanModel();
            return planModel.getPlanxsegListbyidseg(cod_seg);

        } ;
    }
//---------------------------------------------------------------//
    public DataFetcher getPacientebyCod(){
        return dataFetchingEnvironment -> {
            String cod_pac="";
            PacienteModel pacienteModel = new PacienteModel();
            //vemos si viene de alguna consulta anterior
            Map<Map,Map> root = dataFetchingEnvironment.getSource();
            if(root !=null){
                cod_pac=root.get("cod_pac")+"";
            }else {
                cod_pac = dataFetchingEnvironment.getArgument("cod_pac");
            }
            return pacienteModel.getPacientebyCodPac(cod_pac);
        } ;
    }

    public DataFetcher getUnidadbyCod(){
        return dataFetchingEnvironment -> {
            String cod_uni = dataFetchingEnvironment.getArgument("cod_uni")+"";
            UnidadModel unidadModel=new UnidadModel();
            return unidadModel.getUnidadbyCod(cod_uni);
        } ;
    }
    public DataFetcher getUnidadbyCodpedido(){
        return dataFetchingEnvironment -> {
            String cod_uni ="";
            Map<Map,Map> rootData=dataFetchingEnvironment.getSource();
            if (rootData!=null){
                cod_uni=rootData.get("cod_unidad")+"";
            }
            UnidadModel unidadModel=new UnidadModel();
            return unidadModel.getUnidadbyCod(cod_uni);
        } ;
    }
    public DataFetcher searchPaciente(){
        return dataFetchingEnvironment -> {
            String nom_pac = dataFetchingEnvironment.getArgument("nom_pac");
            String ape_pac = dataFetchingEnvironment.getArgument("ape_pac");
            nom_pac="%"+nom_pac+"%";
            ape_pac="%"+ape_pac+"%";
            PacienteModel pacienteModel=new PacienteModel();
            return pacienteModel.getPacienteSearch(nom_pac,ape_pac);
        } ;
    }

    public DataFetcher searchPaciente2(){
        return dataFetchingEnvironment -> {
            String text = dataFetchingEnvironment.getArgument("text");
            text="%"+text+"%";
            PacienteModel pacienteModel=new PacienteModel();
            return pacienteModel.SearchPaciente(text,"normal");
        } ;
    }

    public DataFetcher searchPacienteComplete(){
        return dataFetchingEnvironment -> {
            String text = dataFetchingEnvironment.getArgument("text");
            text="%"+text+"%";
            PacienteModel pacienteModel=new PacienteModel();
            return pacienteModel.SearchPaciente(text,"complete");
        } ;
    }

    public DataFetcher ListPacientes(){
        return dataFetchingEnvironment -> {
            PacienteModel pacienteModel=new PacienteModel();
            return pacienteModel.getPacientesList();
        } ;
    }

    public DataFetcher AnalisisbyCod(){
        return dataFetchingEnvironment -> {
            AnalisisModel analisisModel=new AnalisisModel();
            String cod_ana = dataFetchingEnvironment.getArgument("cod_ana");
            return analisisModel.getAnalisisByCod(cod_ana);
        } ;
    }

    //----------------------Añadido---------------------------------------

    public DataFetcher getAnalisisxPedidobyIdPedido(){
        return dataFetchingEnvironment -> {
            String id_pedidos="";
            Map<Map,Map> rootdata= dataFetchingEnvironment.getSource();
           if(rootdata!=null){
               id_pedidos = rootdata.get("id_pedidos")+"";
           }
            AnalisisModel analisisModel=new AnalisisModel();

            return analisisModel.getAnalisisxPedidobyIdPedido(id_pedidos);
        } ;
    }

    public DataFetcher getAnaxperfilAva(){
        return dataFetchingEnvironment -> {
            String cod_per="";
            Map<Map,Map> rootdata= dataFetchingEnvironment.getSource();
            if(rootdata!=null){
                cod_per = rootdata.get("cod_per")+"";
            }
            else {
                return null;
            }
            PerfilesAvaModel perfilesAvaModel=new PerfilesAvaModel();

            return perfilesAvaModel.getAnaxperfilAva(cod_per);
        } ;
    }

    //----------------------------------------------------------------

    public DataFetcher AnalisisMstrsbyCod(){
        return dataFetchingEnvironment -> {
            AnalisisModel analisisModel=new AnalisisModel();
            String cod_ana = dataFetchingEnvironment.getArgument("cod_ana");
            return analisisModel.getAnalisisMuestrasbyCod(cod_ana);
        } ;
    }

    public DataFetcher searchAnalisis(){
        return dataFetchingEnvironment -> {
            AnalisisModel analisisModel=new AnalisisModel();
            String des_ana = dataFetchingEnvironment.getArgument("des_ana");
            des_ana="%"+des_ana+"%";
            return analisisModel.searchAnalisis(des_ana);
        } ;
    }

    public DataFetcher searchAnalisisxOrden(){
        return dataFetchingEnvironment -> {
            AnalisisModel analisisModel=new AnalisisModel();
            String nro_ord = dataFetchingEnvironment.getArgument("nro_ord");
            return analisisModel.getAnalisisxOrd(nro_ord);
        } ;
    }

    public DataFetcher searchAnalisisMstrs(){
        return dataFetchingEnvironment -> {
            AnalisisModel analisisModel=new AnalisisModel();
            String des_ana = dataFetchingEnvironment.getArgument("des_ana");
            des_ana="%"+des_ana+"%";
            return analisisModel.searchAnalisisMuestras(des_ana);
        } ;
    }

    public DataFetcher searchAnalisisMstrs2(){
        return dataFetchingEnvironment -> {
            AnalisisModel analisisModel=new AnalisisModel();
            String des_ana = dataFetchingEnvironment.getArgument("des_ana");
            des_ana="%"+des_ana+"%";
            return analisisModel.searchAnalisisByNom(des_ana);
        } ;
    }

    public DataFetcher PagoxOrden(){
        return dataFetchingEnvironment -> {
            OrdenModel ordenModel=new OrdenModel();
            String nro_ord = dataFetchingEnvironment.getArgument("nro_ord");
            return ordenModel.getPagxOrden(nro_ord);
        } ;
    }

    public DataFetcher MuestraxOrden(){
        return dataFetchingEnvironment -> {
            OrdenModel ordenModel=new OrdenModel();
            String nro_ord = dataFetchingEnvironment.getArgument("nro_ord");
            return ordenModel.getMstxOrden(nro_ord);
        } ;
    }

    public DataFetcher OrdenByNum(){
        return dataFetchingEnvironment -> {
            OrdenModel ordenModel=new OrdenModel();
            int nroD = dataFetchingEnvironment.getArgument("nroD");
            int nroH = dataFetchingEnvironment.getArgument("nroH");
            return ordenModel.getOrdenbyNum(nroD,nroH);
        } ;
    }

    public DataFetcher OrdenByOrd(){
        return dataFetchingEnvironment -> {
            OrdenModel ordenModel=new OrdenModel();
            int nroOrd = dataFetchingEnvironment.getArgument("nroOrd");
            return ordenModel.getOrdenbyNroOrd(nroOrd);
        } ;
    }

    public DataFetcher OrdenByFecha(){
        return dataFetchingEnvironment -> {
            OrdenModel ordenModel=new OrdenModel();
            String fechaD = dataFetchingEnvironment.getArgument("fechaD");
            String fechaH = dataFetchingEnvironment.getArgument("fechaH");
            return ordenModel.getOrdenbyFecha(fechaD,fechaH);
        } ;
    }

    public DataFetcher OrdenByMed(){//revisar logica
        return dataFetchingEnvironment -> {
            MedicoModel medicoModel=new MedicoModel();
            String cod_med = dataFetchingEnvironment.getArgument("cod_med");
            String nom_med = dataFetchingEnvironment.getArgument("nom_med");

          return null;
        } ;
    }

    public DataFetcher OrdenByPac(){//Revisar Logica
        return dataFetchingEnvironment -> {
            OrdenModel ordenModel=new OrdenModel();
            String fechaD = dataFetchingEnvironment.getArgument("fechaD");
            String fechaH = dataFetchingEnvironment.getArgument("fechaH");
            return null;
        } ;
    }

    public DataFetcher OrdenByEstatus(){
        return dataFetchingEnvironment -> {
            OrdenModel ordenModel=new OrdenModel();
            String cod_sts = dataFetchingEnvironment.getArgument("cod_sts");
            return ordenModel.getOrdenbyEstatus(cod_sts);
        } ;
    }

    public DataFetcher ListTipoPago(){
        return dataFetchingEnvironment -> {
            TipoPagoModel tipoPagoModel=new TipoPagoModel();
            return tipoPagoModel.getTipoPagoList();
        } ;
    }

    public DataFetcher ListCalidad(){
        return dataFetchingEnvironment -> {
            SGCalidadModel sGCalidadModel=new SGCalidadModel();
            return sGCalidadModel.getsgCalidad();
        } ;
    }

    public DataFetcher ListIessTipo(){
        return dataFetchingEnvironment -> {
            IessanexoModel iessanexoModel=new IessanexoModel();
            String tipo=dataFetchingEnvironment.getArgument("tipo");
            return iessanexoModel.getListbyTipo(tipo);
        } ;
    }

    public DataFetcher ListIessCIE10(){
        return dataFetchingEnvironment -> {
            IessanexoModel iessanexoModel=new IessanexoModel();
            String codigo=dataFetchingEnvironment.getArgument("codigo");
            return iessanexoModel.getCIE10(codigo);
        } ;
    }

    public DataFetcher ListMuestras(){
        return dataFetchingEnvironment -> {
            MuestrasModel muestrasModel=new MuestrasModel();
            return muestrasModel.getMuestrasList();
        } ;
    }

    public DataFetcher MuestrabyCod(){
        return dataFetchingEnvironment -> {
            String cod_mst= dataFetchingEnvironment.getArgument("cod_mst");
            MuestrasModel muestrasModel=new MuestrasModel();
            return muestrasModel.getMuestrasbyCod(cod_mst);
        } ;
    }

    public DataFetcher PreciosbySeguro(){
        return dataFetchingEnvironment -> {
            String cod_ana= dataFetchingEnvironment.getArgument("cod_ana");
            String cod_lpr= dataFetchingEnvironment.getArgument("cod_lpr");
            PreciosModel preciosModel=new PreciosModel();
            return preciosModel.getPreciosbySeg(cod_ana,cod_lpr);
        } ;
    }

    public DataFetcher MenuFavbyUsuario(){
        return dataFetchingEnvironment -> {
            String usuario= dataFetchingEnvironment.getArgument("usuario");
            AvalabModel avalabModel=new AvalabModel();
            return avalabModel.MenufavxUser(usuario);
        } ;
    }

    public DataFetcher ListPerfiles(){
        return dataFetchingEnvironment -> {
            PerfilModel perfilModel=new PerfilModel();
            return perfilModel.getPerfilList();
        } ;
    }

    public DataFetcher PerfilesbyCodMed(){
        return dataFetchingEnvironment -> {
            String cod_med=dataFetchingEnvironment.getArgument("cod_med")+"";
            PerfilModel perfilModel=new PerfilModel();
            return perfilModel.getPerfilListbyMedico(cod_med);
        } ;
    }

    public DataFetcher PerfilesbyId(){
        return dataFetchingEnvironment -> {
            String id_perfil=dataFetchingEnvironment.getArgument("id_perfil")+"";
            PerfilModel perfilModel=new PerfilModel();
            return perfilModel.getPerfilListbyId(id_perfil);
        } ;
    }

    public DataFetcher ListDiagnostico(){
        return dataFetchingEnvironment -> {
            DiagnosticoModel diagnosticoModel=new DiagnosticoModel();
            return diagnosticoModel.getDiagnosticosList();
        } ;
    }

    public DataFetcher Diagnosticopedido(){
        return dataFetchingEnvironment -> {
            String codigo_diagnostico="";
            Map<String,String> root = dataFetchingEnvironment.getSource();
            if(root !=null) {
                            codigo_diagnostico = root.get("codigo_diagnostico") + "";

            }
            else{
                codigo_diagnostico=dataFetchingEnvironment.getArgument("codigo_diagnostico");
            }

            DiagnosticoModel diagnosticoModel=new DiagnosticoModel();
            return diagnosticoModel.getDiagnosticobyCodigo(codigo_diagnostico);
        } ;
    }

    public DataFetcher Diagnosticoextrapedido(){
        return dataFetchingEnvironment -> {
            String codigo_diagnostico="";
            Map<String,String> root = dataFetchingEnvironment.getSource();
            if(root !=null) {
                            codigo_diagnostico = root.get("codigo_diagnostico2") + "";

            }
            else{
                codigo_diagnostico=dataFetchingEnvironment.getArgument("codigo_diagnostico");
            }

            DiagnosticoModel diagnosticoModel=new DiagnosticoModel();
            return diagnosticoModel.getDiagnosticobyCodigo(codigo_diagnostico);
        } ;
    }

    public DataFetcher DiagnosticoId(){
        return dataFetchingEnvironment -> {
            String codigo_diagnostico="";
            Map<String,String> root = dataFetchingEnvironment.getSource();
            if(root !=null) {
    //            codigo_diagnostico = root.get("codigo_diagnostico") + "";

            }
            else{
                 codigo_diagnostico=dataFetchingEnvironment.getArgument("codigo_diagnostico");
            }

            DiagnosticoModel diagnosticoModel=new DiagnosticoModel();
            return diagnosticoModel.getDiagnosticobyCodigo(codigo_diagnostico);
        } ;
    }

    public DataFetcher searchDiagnostico(){
        return dataFetchingEnvironment -> {
            String des_diagnostico=dataFetchingEnvironment.getArgument("des_diagnostico");
            des_diagnostico="%"+des_diagnostico+"%";
            String des_diagnostico_cod=dataFetchingEnvironment.getArgument("des_diagnostico");
            DiagnosticoModel diagnosticoModel=new DiagnosticoModel();
            return diagnosticoModel.searchDiagnosticoDesCod(des_diagnostico,des_diagnostico_cod);
        } ;
    }

    public DataFetcher ListPedidos(){
        return dataFetchingEnvironment -> {
            PedidosModel pedidosModel=new PedidosModel();
            return pedidosModel.getPedidosList();
        } ;
    }

    public DataFetcher ListPedidosbyMed(){
        return dataFetchingEnvironment -> {
            String cod_med=dataFetchingEnvironment.getArgument("cod_med")+"";
            PedidosModel pedidosModel=new PedidosModel();
            return pedidosModel.getPedidosbyCodMed(cod_med);
        } ;
    }

    public DataFetcher ListPedidosbyMedpag(){
        return dataFetchingEnvironment -> {
            String fechai=dataFetchingEnvironment.getArgument("fechai")+"";
            String fechaf=dataFetchingEnvironment.getArgument("fechaf")+"";
            String texto=dataFetchingEnvironment.getArgument("texto")+"";
            texto=texto+"%";
            String cod_med=dataFetchingEnvironment.getArgument("cod_med")+"";
            PedidosModel pedidosModel=new PedidosModel();
            return pedidosModel.getPedidosbyCodMedPag(fechai,fechaf,texto,cod_med);
        } ;
    }


    public DataFetcher PedidoUuid(){
        return dataFetchingEnvironment -> {
            String uuid=dataFetchingEnvironment.getArgument("uuid");
            PedidosModel pedidosModel=new PedidosModel();
            return pedidosModel.getPedidosbyUud(uuid);
        } ;
    }

    public DataFetcher PedidobyId(){
        return dataFetchingEnvironment -> {
            String id=dataFetchingEnvironment.getArgument("id");
            PedidosModel pedidosModel=new PedidosModel();
            return pedidosModel.getPedidosbyOrden(id);
        } ;
    }

    public DataFetcher searchUnidad(){
        return dataFetchingEnvironment -> {
            String text=dataFetchingEnvironment.getArgument("text");
            text="%"+text+"%";
            UnidadModel unidadModel=new UnidadModel();
            return unidadModel.searchUnidad(text);
        } ;
    }

    public DataFetcher ListPerfilesAvalab(){
        return dataFetchingEnvironment -> {
            PerfilesAvaModel unidadModel=new PerfilesAvaModel();
            return unidadModel.listPerfilesAva();
        } ;
    }

    public DataFetcher OrdenResultadosbyparam(){
        return dataFetchingEnvironment -> {
            String codigo=dataFetchingEnvironment.getArgument("codigo");
            String tipo=dataFetchingEnvironment.getArgument("tipo");

            OrdenModel ordenModel=new OrdenModel();
            return ordenModel.getOrdentoResultados(codigo,tipo);
        } ;
    }

//-------------------------------------Mutations..------------------------------------------------------------------------------------
    public DataFetcher checkLogin(){
        //Revisar
    return  dataFetchingEnvironment ->{
        MedicoModel medicoModel=new MedicoModel();
        String usuario = dataFetchingEnvironment.getArgument("usuario");
        String tipo_usuario = dataFetchingEnvironment.getArgument("clave");
        String data = dataFetchingEnvironment.getArgument("tipo_usuario");
        return medicoModel.insertMedicoLite(data);
    };
}

    public DataFetcher insertPaciente(){
        //Revisar
        return  dataFetchingEnvironment ->{
            MedicoModel medicoModel=new MedicoModel();
            String cod_ori = dataFetchingEnvironment.getArgument("cod_ori");
            String nom_pac = dataFetchingEnvironment.getArgument("nom_pac");
            String ape_pac = dataFetchingEnvironment.getArgument("usuario");
            String fec_nac = dataFetchingEnvironment.getArgument("usuario");
            String edad_pac = dataFetchingEnvironment.getArgument("usuario");
            String id_pac = dataFetchingEnvironment.getArgument("usuario");
            String mail_pac = dataFetchingEnvironment.getArgument("usuario");
            String cel_pac = dataFetchingEnvironment.getArgument("usuario");
            String sex_pac = dataFetchingEnvironment.getArgument("usuario");
            String dir_pac = dataFetchingEnvironment.getArgument("usuario");
            String telf_pac = dataFetchingEnvironment.getArgument("usuario");
            String pais_nace = dataFetchingEnvironment.getArgument("usuario");
            String estado_civil = dataFetchingEnvironment.getArgument("usuario");
            String instruccion = dataFetchingEnvironment.getArgument("usuario");
            String ocu_pac = dataFetchingEnvironment.getArgument("usuario");
            String pat_pac = dataFetchingEnvironment.getArgument("usuario");
            String san_pac = dataFetchingEnvironment.getArgument("usuario");
            String etnia = dataFetchingEnvironment.getArgument("usuario");
            String usuario = dataFetchingEnvironment.getArgument("usuario");
            String tipo_usuario = dataFetchingEnvironment.getArgument("clave");
            String data = dataFetchingEnvironment.getArgument("tipo_usuario");
            return medicoModel.insertMedicoLite(data);
        };
    }

    public DataFetcher insertPacientelite(){
        return  dataFetchingEnvironment ->{
            PacienteModel pacienteModel=new PacienteModel();
            String data = dataFetchingEnvironment.getArgument("json_pac");
            return pacienteModel.insertPacientelite(data);
        };
    }

    public DataFetcher insertMedicoLite(){
        return  dataFetchingEnvironment ->{
            MedicoModel medicoModel=new MedicoModel();
            String data = dataFetchingEnvironment.getArgument("jsonMedico");
            return medicoModel.insertMedicoLite(data);
        };
    }

    public DataFetcher insertPerfil(){
        return  dataFetchingEnvironment ->{
            PerfilModel perfilModel=new PerfilModel();
            String data = dataFetchingEnvironment.getArgument("jsonPerfil");
            return perfilModel.insertPerfil(data);
        };
    }

    public DataFetcher UpdatePerfiles(){
        return  dataFetchingEnvironment ->{
            PerfilModel perfilModel=new PerfilModel();
            String data = dataFetchingEnvironment.getArgument("jsonPerfilesUpd");
            return perfilModel.updatePerfil(data);
        };
    }

    public DataFetcher DeletePerfiles(){
        return  dataFetchingEnvironment ->{
            PerfilModel perfilModel=new PerfilModel();
            String data = dataFetchingEnvironment.getArgument("jsonPerfilesDelete");
            return perfilModel.deletePerfil(data);
        };
    }

    public DataFetcher insertPedido(){
        return  dataFetchingEnvironment ->{
            PedidosModel pedidosModel=new PedidosModel();
            String jsonPedido = dataFetchingEnvironment.getArgument("jsonPedido");
            String jsonAnalisis = dataFetchingEnvironment.getArgument("jsonAnalisis");
            return pedidosModel.addPedidoc(jsonPedido,jsonAnalisis);
        };
    }

    public DataFetcher PedidoAnular(){
        return  dataFetchingEnvironment ->{
            PedidosModel pedidosModel=new PedidosModel();
            String jsonPedido = dataFetchingEnvironment.getArgument("jsonPedido");
            return pedidosModel.AnularPedido(jsonPedido);
        };
    }







}
