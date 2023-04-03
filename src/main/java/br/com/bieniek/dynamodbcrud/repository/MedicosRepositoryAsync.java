package br.com.bieniek.dynamodbcrud.repository;

import br.com.bieniek.dynamodbcrud.entity.Medicos;
import br.com.bieniek.dynamodbcrud.model.response.MedicosResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface MedicosRepositoryAsync {

    Mono<MedicosResponse> save(Medicos medico);
    Mono<MedicosResponse> getMedicoById(UUID medicoId);

    Flux<MedicosResponse> findAllMedicos();

    void deleteById(UUID fromString);

}
