package co.edu.unicauca.kafkaproductorpar.services;

import co.edu.unicauca.kafkaproductorpar.models.Servicio;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ServicioImpl implements IServicioService{
    public Servicio getServicio(int idServicio){
        String url= "http://localhost:8086/api/servicios/"+idServicio;
        System.out.println("Url: "+url);
        RestTemplate restTemplate= new RestTemplate();
        Servicio  servicio= restTemplate.getForObject(url, Servicio.class);
        return servicio;
    }
}
