package co.edu.unicauca.kafkaproductorpar.services;


import co.edu.unicauca.kafkaproductorpar.models.Estructura;

public interface IKafkaProductor {
    void sendMessage(String topic, Estructura estructura);
}
