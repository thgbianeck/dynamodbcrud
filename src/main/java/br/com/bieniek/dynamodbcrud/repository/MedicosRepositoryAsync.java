package br.com.bieniek.dynamodbcrud.repository;

import br.com.bieniek.dynamodbcrud.entity.Medicos;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface MedicosRepositoryAsync {

    Mono<Medicos> save(Medicos medico);
    Mono<Medicos> getMedicoById(UUID medicoId);

}
