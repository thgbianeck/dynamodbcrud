package br.com.bieniek.dynamodbcrud.repository.impl;

import br.com.bieniek.dynamodbcrud.entity.Medicos;
import br.com.bieniek.dynamodbcrud.repository.MedicosRepository;
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
public class MedicosRepositoryImpl implements MedicosRepository {

    private final DynamoDbTemplate dynamoDbTemplate;


    @Override
    public Medicos save(Medicos medico) {
        log.info("Saving medico: {}", medico);
        return dynamoDbTemplate.save(medico);
    }

    @Override
    public Medicos getMedicoById(UUID medicoId) {
        log.info("Getting medico by id: {}", medicoId);
        Key key = Key.builder().partitionValue(medicoId.toString()).build();
        return dynamoDbTemplate.load(key, Medicos.class);
    }
}
