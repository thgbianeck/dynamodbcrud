package br.com.bieniek.dynamodbcrud.service.impl;

import br.com.bieniek.dynamodbcrud.entity.Medicos;
import br.com.bieniek.dynamodbcrud.repository.MedicosRepository;
import br.com.bieniek.dynamodbcrud.repository.MedicosRepositoryAsync;
import br.com.bieniek.dynamodbcrud.service.MedicosService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.scheduler.Schedulers;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class MedicosServiceImpl implements MedicosService {

    private final MedicosRepository medicosRepository;
    @Override
    public Medicos save(Medicos medicos) {
        UUID medicoID = UUID.randomUUID();
        medicos.setMedicoID(medicoID);
        log.info("Saving medicos: {}", medicos);
        return medicosRepository.save(medicos)
//                .subscribeOn(Schedulers.boundedElastic()).block()
                ;
    }

    @Override
    public Medicos getMedicosById(UUID medicosId) {
        log.info("Getting medicos by id: {}", medicosId);
        return medicosRepository.getMedicoById(medicosId)
//                .subscribeOn(Schedulers.boundedElastic()).block()
                ;
    }

}
