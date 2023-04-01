package br.com.bieniek.dynamodbcrud.repository;

import br.com.bieniek.dynamodbcrud.entity.Medicos;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface MedicosRepository {

    Medicos save(Medicos medico);
    Medicos getMedicoById(UUID medicoId);

}
