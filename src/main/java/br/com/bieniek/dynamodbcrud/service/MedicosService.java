package br.com.bieniek.dynamodbcrud.service;

import br.com.bieniek.dynamodbcrud.entity.Medicos;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface MedicosService {

    Medicos save(Medicos medicos);
    Medicos getMedicosById(UUID medicosId);
}
