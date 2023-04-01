package br.com.bieniek.dynamodbcrud.repository.impl;

import br.com.bieniek.dynamodbcrud.entity.Medicos;
import br.com.bieniek.dynamodbcrud.repository.MedicosRepositoryAsync;
import io.awspring.cloud.dynamodb.DynamoDbTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import software.amazon.awssdk.enhanced.dynamodb.Key;

import java.util.UUID;


@Repository
@Slf4j
@RequiredArgsConstructor
public class MedicosRepositoryAsyncImpl implements MedicosRepositoryAsync {

    private final DynamoDbTemplate dynamoDbTemplate;


    @Override
    public Mono<Medicos> save(Medicos medico) {
        log.info("Saving medico: {}", medico);
        return Mono.fromCallable(() -> dynamoDbTemplate.save(medico))
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Medicos> getMedicoById(UUID medicoId) {
        log.info("Getting medico by id: {}", medicoId);
        Key key = Key.builder().partitionValue(medicoId.toString()).build();
        return Mono.defer(() -> {
                    var medico = dynamoDbTemplate.load(key, Medicos.class);
                    return Mono.justOrEmpty(medico);
                })
                .subscribeOn(Schedulers.boundedElastic());
    }
}
