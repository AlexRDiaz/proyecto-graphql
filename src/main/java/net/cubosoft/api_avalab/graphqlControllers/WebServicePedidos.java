//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.cubosoft.api_avalab.graphqlControllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import net.cubosoft.api_avalab.Models.PacienteModel;
import net.cubosoft.api_avalab.Models.PedidosModel;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping({"/ws"})
public class WebServicePedidos {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Value("${app.web_service_render}")
    private String uriRender = "";
    RestTemplate restTemplate = new RestTemplate();
    private static final Logger logger = LoggerFactory.getLogger(WebServicePedidos.class);

    public WebServicePedidos() {
    }

    @GetMapping({"/get-pdf-pedido"})
    public Object getPDFRender(@RequestParam String uid) throws JSONException, SQLException, JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        new JSONObject();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            PedidosModel pedidosModel = new PedidosModel();
            PacienteModel pacientesModel = new PacienteModel();
            Map pedido = pedidosModel.getPedidosbyUud(uid);
            List analisis = pedidosModel.getAnalisisbyIdPedidos(pedido.get("id_pedidos").toString());
            Map paciente = pacientesModel.getPacientebyCodPac(pedido.get("cod_pac").toString());
            logger.info("fec_nac  " + paciente.get("fec_nac").toString());
            logger.info("fec_examen " + pedido.get("fec_examen").toString());
            pedido.replace("fec_examen", pedido.get("fec_examen").toString());
            paciente.replace("fec_nac", paciente.get("fec_nac").toString());
            pedido.put("Analisis", analisis);
            pedido.put("Paciente", paciente);
            String json = this.objectMapper.writeValueAsString(pedido);
            json = "{\"orden\":" + json + "}";
            logger.info("StrJson " + json);
            logger.info("datos " + pedido);
            logger.info("analisis " + analisis);
            HttpEntity<String> request = new HttpEntity(json, headers);
            ResponseEntity<String> responseEntityRender = this.restTemplate.postForEntity(this.uriRender + "/pdfPedido", request, String.class, new Object[0]);
            JsonNode root = this.objectMapper.readTree((String)responseEntityRender.getBody());
            byte[] base64 = Base64.getDecoder().decode(root.get("data").asText());
            headers.setContentType(MediaType.parseMediaType("application/pdf"));
            HttpHeaders headersSet = new HttpHeaders();
            headersSet.setContentType(MediaType.APPLICATION_PDF);
            String filename = uid + ".pdf";
            headersSet.add("Content-Disposition", "inline; filename=" + filename);
            headersSet.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            ResponseEntity<byte[]> response = new ResponseEntity(base64, headersSet, HttpStatus.OK);
            logger.debug("RESPUESTA SERVIDOR " + response.toString());
            return response;
        } catch (HttpStatusCodeException var17) {
            ((BodyBuilder)ResponseEntity.status(var17.getRawStatusCode()).headers(var17.getResponseHeaders())).body(var17.getResponseBodyAsString());
            var17.printStackTrace();
            return ((BodyBuilder)ResponseEntity.status(var17.getRawStatusCode()).headers(var17.getResponseHeaders())).body(var17.getResponseBodyAsString());
        }
    }
}
