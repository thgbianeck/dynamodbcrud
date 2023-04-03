package br.com.bieniek.dynamodbcrud.repository.impl;

import br.com.bieniek.dynamodbcrud.entity.Medicos;
import br.com.bieniek.dynamodbcrud.mapper.MedicosMapper;
import br.com.bieniek.dynamodbcrud.model.response.MedicosResponse;
import br.com.bieniek.dynamodbcrud.repository.MedicosRepositoryAsync;
import io.awspring.cloud.dynamodb.DynamoDbTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import software.amazon.awssdk.enhanced.dynamodb.Key;

import java.util.List;
import java.util.UUID;


@Repository
@Slf4j
@RequiredArgsConstructor
public class MedicosRepositoryAsyncImpl implements MedicosRepositoryAsync {

    private final DynamoDbTemplate dynamoDbTemplate;


    @Override
    public Mono<MedicosResponse> save(Medicos medico) {
        log.info("Saving medico: {}", medico);
        return Mono.fromCallable(() -> dynamoDbTemplate.save(medico))
                .map(MedicosMapper::toMedicosResponseFrom)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<MedicosResponse> getMedicoById(UUID medicoId) {
        log.info("Getting medico by id: {}", medicoId);
        Key key = Key.builder().partitionValue(medicoId.toString()).build();
        return Mono.defer(() -> {
                    var medico = dynamoDbTemplate.load(key, Medicos.class);
                    log.info("Medico: {}", medico);
                    var medicosResponse = MedicosMapper.toMedicosResponseFrom(medico);
                    log.info("MedicosResponse: {}", medicosResponse);
                    return Mono.justOrEmpty(medicosResponse);
                })
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Flux<MedicosResponse> findAllMedicos() {
        List<MedicosResponse> medicosResponses = dynamoDbTemplate
                .scanAll(Medicos.class).items()
                .stream().map(MedicosMapper::toMedicosResponseFrom)
                .toList();
        return Flux.fromIterable(medicosResponses)
                .subscribeOn(Schedulers.boundedElastic());

    }

    @Override
    public void deleteById(UUID fromString) {
        Key key = Key.builder().partitionValue(fromString.toString()).build();
        dynamoDbTemplate.delete(key, Medicos.class);
    }
}
