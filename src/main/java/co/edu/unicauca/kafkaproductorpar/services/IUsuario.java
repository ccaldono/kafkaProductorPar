package co.edu.unicauca.kafkaproductorpar.services;

import co.edu.unicauca.kafkaproductorpar.models.Externo;

import java.util.List;

public interface IUsuario {
    public List<Externo> getUsuarios();
    public Boolean validar(int id);
}
