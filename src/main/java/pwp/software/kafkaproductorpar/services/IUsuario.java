package pwp.software.kafkaproductorpar.services;

import pwp.software.kafkaproductorpar.models.Externo;

import java.util.List;

public interface IUsuario {
    public List<Externo> getUsuarios();
    public Boolean validar(int id);
}
