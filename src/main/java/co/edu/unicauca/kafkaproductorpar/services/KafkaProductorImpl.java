package co.edu.unicauca.kafkaproductorpar.services;

import co.edu.unicauca.kafkaproductorpar.models.Estructura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class KafkaProductorImpl implements  IKafkaProductor{

    @Autowired
    private KafkaTemplate<String, Estructura> KafkaTemplate;
    @Override
    public void sendMessage(String topic, Estructura estructura) {
        ListenableFuture<SendResult<String,Estructura>> future= KafkaTemplate.send(topic,estructura);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Estructura>>() {
            @Override
            public void onFailure(Throwable ex) {
                ex.printStackTrace();
            }
            @Override
            public void onSuccess(SendResult<String,Estructura> result) {
                System.out.println("Enviado "+ result.getProducerRecord().value().getNit()+" al topic " +result.getRecordMetadata().topic());
            }
        });
    }
}