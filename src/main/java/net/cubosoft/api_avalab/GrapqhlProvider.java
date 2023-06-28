package net.cubosoft.api_avalab;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import graphql.schema.visibility.NoIntrospectionGraphqlFieldVisibility;
import net.cubosoft.api_avalab.graphqlControllers.GraphQLDataFetchersQuerys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;



@Component
public class GrapqhlProvider {
    private GraphQL graphQL;
    @Value("${graphql.enable_schema}")
    private boolean enable_schema;

    @Bean
    public GraphQL graphQL() {
        return graphQL;
    }

    @PostConstruct
    public void init() throws IOException {

        URL url = Resources.getResource("schema.graphqls");
        String sdl = Resources.toString(url, Charsets.UTF_8);
        GraphQLSchema graphQLSchema = buildSchema(sdl);

        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();

/*
        URL url = Resources.getResource("schema.graphqls");
        String sdl = Resources.toString(url, Charsets.UTF_8);
        GraphQLSchema graphQLSchema = buildSchema(sdl);


       GraphQLSchema.Builder builder = GraphQLSchema.newSchema()                ;
        builder.fieldVisibility(NoIntrospectionGraphqlFieldVisibility.NO_INTROSPECTION_FIELD_VISIBILITY);
        builder.build();
*/




    }


    @Autowired
    GraphQLDataFetchersQuerys graphQLDataFetchersQuerys;

    private GraphQLSchema buildSchema(String sdl) {
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
        RuntimeWiring runtimeWiring = buildWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }


    private RuntimeWiring buildWiring() {
        RuntimeWiring.Builder rm= RuntimeWiring.newRuntimeWiring();
    if(!enable_schema){
        rm.fieldVisibility(NoIntrospectionGraphqlFieldVisibility.NO_INTROSPECTION_FIELD_VISIBILITY);
    }
        return  rm
               // .fieldVisibility()//Oculta la vista de los querys y mutation
                .type(newTypeWiring("Query").dataFetcher("test", graphQLDataFetchersQuerys.test()))
                .type(newTypeWiring("Query").dataFetcher("ListEstatus", graphQLDataFetchersQuerys.ListEstatus()))
                .type(newTypeWiring("Query").dataFetcher("ListOrigen", graphQLDataFetchersQuerys.ListOrigen()))
                .type(newTypeWiring("Query").dataFetcher("ListAnalisis", graphQLDataFetchersQuerys.ListAnalisis()))
                .type(newTypeWiring("Query").dataFetcher("ListSeguro", graphQLDataFetchersQuerys.ListSeguro()))
                .type(newTypeWiring("Query").dataFetcher("ListUbicaciones", graphQLDataFetchersQuerys.ListUbicaciones()))
                .type(newTypeWiring("Query").dataFetcher("ListUnidad", graphQLDataFetchersQuerys.ListUnidad()))
                .type(newTypeWiring("Query").dataFetcher("ListReferencia", graphQLDataFetchersQuerys.ListReferencia()))
                .type(newTypeWiring("Query").dataFetcher("ListOrdenCompleto", graphQLDataFetchersQuerys.ListOrdenCompleto()))
                .type(newTypeWiring("Query").dataFetcher("ListMedicos", graphQLDataFetchersQuerys.ListMedicos()))
                .type(newTypeWiring("Query").dataFetcher("searchMedico", graphQLDataFetchersQuerys.searchMedico()))
                .type(newTypeWiring("Query").dataFetcher("searchMedico2", graphQLDataFetchersQuerys.searchMedico2()))
                .type(newTypeWiring("Query").dataFetcher("getMedicobyCod",graphQLDataFetchersQuerys.getMedicobyCod()))
                .type(newTypeWiring("Query").dataFetcher("getMedicobyCed",graphQLDataFetchersQuerys.getMedicobyCed()))
                .type(newTypeWiring("Query").dataFetcher("getPaciente",graphQLDataFetchersQuerys.getPaciente()))
                .type(newTypeWiring("Query").dataFetcher("getPacientebyCod",graphQLDataFetchersQuerys.getPacientebyCod()))
                .type(newTypeWiring("Query").dataFetcher("getUnidadbyCod",graphQLDataFetchersQuerys.getUnidadbyCod()))
                .type(newTypeWiring("Query").dataFetcher("searchPaciente",graphQLDataFetchersQuerys.searchPaciente()))
                .type(newTypeWiring("Query").dataFetcher("searchPaciente2",graphQLDataFetchersQuerys.searchPaciente2()))
                .type(newTypeWiring("Query").dataFetcher("searchPacienteComplete",graphQLDataFetchersQuerys.searchPacienteComplete()))
                .type(newTypeWiring("Query").dataFetcher("ListPacientes",graphQLDataFetchersQuerys.ListPacientes()))
                .type(newTypeWiring("Query").dataFetcher("AnalisisbyCod",graphQLDataFetchersQuerys.AnalisisbyCod()))
                .type(newTypeWiring("Query").dataFetcher("AnalisisMstrsbyCod",graphQLDataFetchersQuerys.AnalisisMstrsbyCod()))
                .type(newTypeWiring("Query").dataFetcher("searchAnalisis",graphQLDataFetchersQuerys.searchAnalisis()))
                .type(newTypeWiring("Query").dataFetcher("searchAnalisisxOrden",graphQLDataFetchersQuerys.searchAnalisisxOrden()))
                .type(newTypeWiring("Query").dataFetcher("searchAnalisisMstrs",graphQLDataFetchersQuerys.searchAnalisisMstrs()))
                .type(newTypeWiring("Query").dataFetcher("searchAnalisisMstrs2",graphQLDataFetchersQuerys.searchAnalisisMstrs2()))
                .type(newTypeWiring("Query").dataFetcher("PagoxOrden",graphQLDataFetchersQuerys.PagoxOrden()))
                .type(newTypeWiring("Query").dataFetcher("MuestraxOrden",graphQLDataFetchersQuerys.MuestraxOrden()))
                .type(newTypeWiring("Query").dataFetcher("OrdenByNum",graphQLDataFetchersQuerys.OrdenByNum()))
                .type(newTypeWiring("Query").dataFetcher("OrdenByOrd",graphQLDataFetchersQuerys.OrdenByOrd()))
                .type(newTypeWiring("Query").dataFetcher("OrdenByFecha",graphQLDataFetchersQuerys.OrdenByFecha()))
                .type(newTypeWiring("Query").dataFetcher("OrdenByMed",graphQLDataFetchersQuerys.OrdenByMed()))
                .type(newTypeWiring("Query").dataFetcher("OrdenByPac",graphQLDataFetchersQuerys.OrdenByPac()))
                .type(newTypeWiring("Query").dataFetcher("OrdenByEstatus",graphQLDataFetchersQuerys.OrdenByEstatus()))
                .type(newTypeWiring("Query").dataFetcher("ListTipoPago",graphQLDataFetchersQuerys.ListTipoPago()))
                .type(newTypeWiring("Query").dataFetcher("ListCalidad",graphQLDataFetchersQuerys.ListCalidad()))
                .type(newTypeWiring("Query").dataFetcher("ListIessTipo",graphQLDataFetchersQuerys.ListIessTipo()))
                .type(newTypeWiring("Query").dataFetcher("ListIessCIE10",graphQLDataFetchersQuerys.ListIessCIE10()))
                .type(newTypeWiring("Query").dataFetcher("ListMuestras",graphQLDataFetchersQuerys.ListMuestras()))
                .type(newTypeWiring("Query").dataFetcher("MuestrabyCod",graphQLDataFetchersQuerys.MuestrabyCod()))
                .type(newTypeWiring("Query").dataFetcher("PreciosbySeguro",graphQLDataFetchersQuerys.PreciosbySeguro()))
                .type(newTypeWiring("Query").dataFetcher("MenuFavbyUsuario",graphQLDataFetchersQuerys.MenuFavbyUsuario()))
                .type(newTypeWiring("Query").dataFetcher("ListPerfiles",graphQLDataFetchersQuerys.ListPerfiles()))
                .type(newTypeWiring("Query").dataFetcher("PerfilesbyCodMed",graphQLDataFetchersQuerys.PerfilesbyCodMed()))
                .type(newTypeWiring("Query").dataFetcher("PerfilesbyId",graphQLDataFetchersQuerys.PerfilesbyId()))
                .type(newTypeWiring("Query").dataFetcher("ListDiagnostico",graphQLDataFetchersQuerys.ListDiagnostico()))
                .type(newTypeWiring("Query").dataFetcher("DiagnosticoId",graphQLDataFetchersQuerys.DiagnosticoId()))
                .type(newTypeWiring("Query").dataFetcher("searchDiagnostico",graphQLDataFetchersQuerys.searchDiagnostico()))
                .type(newTypeWiring("Query").dataFetcher("ListPedidos",graphQLDataFetchersQuerys.ListPedidos()))
                .type(newTypeWiring("Query").dataFetcher("ListPedidosbyMed",graphQLDataFetchersQuerys.ListPedidosbyMed()))
                .type(newTypeWiring("Query").dataFetcher("ListPedidosbyMedpag",graphQLDataFetchersQuerys.ListPedidosbyMedpag()))
                .type(newTypeWiring("Query").dataFetcher("PedidoUuid",graphQLDataFetchersQuerys.PedidoUuid()))
                .type(newTypeWiring("Query").dataFetcher("PedidobyId",graphQLDataFetchersQuerys.PedidobyId()))
                .type(newTypeWiring("Query").dataFetcher("searchUnidad",graphQLDataFetchersQuerys.searchUnidad()))
                .type(newTypeWiring("Query").dataFetcher("ListPerfilesAvalab",graphQLDataFetchersQuerys.ListPerfilesAvalab()))
                .type(newTypeWiring("Query").dataFetcher("OrdenResultadosbyparam",graphQLDataFetchersQuerys.OrdenResultadosbyparam()))


                //types que tienen otro types PedidosCompleto
                .type(newTypeWiring("OrdenComplete").dataFetcher("paciente",graphQLDataFetchersQuerys.getPacientebyCod()))
                .type(newTypeWiring("OrigenComplete").dataFetcher("ubicaciones",graphQLDataFetchersQuerys.getUbicacionCod()))
                .type(newTypeWiring("Seguro").dataFetcher("Plan",graphQLDataFetchersQuerys.getPlanxseguro()))
                .type(newTypeWiring("PedidosCompleto").dataFetcher("Analisis",graphQLDataFetchersQuerys.getAnalisisxPedidobyIdPedido()))
                .type(newTypeWiring("PedidosCompleto").dataFetcher("Paciente",graphQLDataFetchersQuerys.getPacientebyCod()))
                .type(newTypeWiring("PedidosCompleto").dataFetcher("Diagnostico",graphQLDataFetchersQuerys.Diagnosticopedido()))
                .type(newTypeWiring("PedidosCompleto").dataFetcher("Diagnosticoextra",graphQLDataFetchersQuerys.Diagnosticoextrapedido()))
                .type(newTypeWiring("PedidosCompleto").dataFetcher("Medico",graphQLDataFetchersQuerys.getMedicobyCod()))
                .type(newTypeWiring("PedidosCompleto").dataFetcher("Unidad",graphQLDataFetchersQuerys.getUnidadbyCodpedido()))
                .type(newTypeWiring("PerfilesAvaComplete").dataFetcher("Analisis",graphQLDataFetchersQuerys.getAnaxperfilAva()))

                //---------------------------Mutations----------------------------------------------------------------------------
                .type(newTypeWiring("Mutation").dataFetcher("insertMedico",graphQLDataFetchersQuerys.insertMedico()))
                .type(newTypeWiring("Mutation").dataFetcher("insertMedicoLite",graphQLDataFetchersQuerys.insertMedicoLite()))
                .type(newTypeWiring("Mutation").dataFetcher("insertPerfil",graphQLDataFetchersQuerys.insertPerfil()))
                .type(newTypeWiring("Mutation").dataFetcher("UpdatePerfiles",graphQLDataFetchersQuerys.UpdatePerfiles()))
                .type(newTypeWiring("Mutation").dataFetcher("DeletePerfiles",graphQLDataFetchersQuerys.DeletePerfiles()))
                .type(newTypeWiring("Mutation").dataFetcher("insertPedido",graphQLDataFetchersQuerys.insertPedido()))
                .type(newTypeWiring("Mutation").dataFetcher("PedidoAnular",graphQLDataFetchersQuerys.PedidoAnular()))
                .type(newTypeWiring("Mutation").dataFetcher("insertPacientelite",graphQLDataFetchersQuerys.insertPacientelite()))

                .build();



    }

}
