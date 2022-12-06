package pwp.software.kafkaproductorpar.controllers;

import pwp.software.kafkaproductorpar.models.Estructura;
import pwp.software.kafkaproductorpar.models.Servicio;
import pwp.software.kafkaproductorpar.services.IKafkaProductor;
import pwp.software.kafkaproductorpar.services.IServicioService;
import pwp.software.kafkaproductorpar.services.IUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@CrossOrigin(origins = "http://localhost:4200",  maxAge = 3600)
@RestController
@RequestMapping("/api")
public class KafkaProductorRestController {
    @Autowired
    private IKafkaProductor producer;
    @Autowired
    private IServicioService servicio;
    @Autowired
    private IUsuario externo;

    @PostMapping(value = "kafkaproductor/{idServicio}")
    public ResponseEntity<?> servicioProductor(@PathVariable("idServicio") Integer idServicio, @RequestBody Estructura estructura){

        Map<String,Object> response = new HashMap<>();
        Servicio servicioTemp = null;
        String broker=null;

        System.out.println("Id del servicio: "+idServicio);
        servicioTemp= servicio.getServicio(idServicio);

        System.out.println("Nombre Topic:"+servicioTemp.getSer_topic());
        System.out.println("Broker:      "+servicioTemp.getSer_broker());

        if(externo.validar(estructura.getNit())){
            producer.sendMessage(servicioTemp.getSer_topic(), estructura);
        }
        else{
            response.put("mensaje", "No se encuentra registrado" );
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.FOUND);
        }
        response.put("mensaje","Publicación ha sido realizada con exito en topic: "+servicioTemp.getSer_topic());
        response.put("estructura",estructura);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
}