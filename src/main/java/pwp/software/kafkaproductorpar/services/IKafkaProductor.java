package pwp.software.kafkaproductorpar.services;


import pwp.software.kafkaproductorpar.models.Estructura;

public interface IKafkaProductor {
    void sendMessage(String topic, Estructura estructura);
}
