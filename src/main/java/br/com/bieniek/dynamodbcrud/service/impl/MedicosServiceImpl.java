package br.com.bieniek.dynamodbcrud.service.impl;

import br.com.bieniek.dynamodbcrud.entity.Medicos;
import br.com.bieniek.dynamodbcrud.mapper.MedicosMapper;
import br.com.bieniek.dynamodbcrud.model.request.MedicosRequest;
import br.com.bieniek.dynamodbcrud.model.response.MedicosResponse;
import br.com.bieniek.dynamodbcrud.repository.MedicosRepositoryAsync;
import br.com.bieniek.dynamodbcrud.service.MedicosService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class MedicosServiceImpl implements MedicosService {

    private final MedicosRepositoryAsync medicosRepository;
    @Override
    @Transactional
    public MedicosResponse save(MedicosRequest request) {
        UUID medicoID = UUID.randomUUID();
        Medicos medicos = MedicosMapper.toMedicosFrom(medicoID, request);
        log.info("Saving medicos: {}", medicos);
        return medicosRepository.save(medicos)
                .subscribeOn(Schedulers.boundedElastic()).block()
                ;
    }

    @Override
    public MedicosResponse getMedicosById(String medicosId) {
        log.info("Getting medicos by id: {}", medicosId);
        return medicosRepository.getMedicoById(UUID.fromString(medicosId))
                .subscribeOn(Schedulers.boundedElastic()).block()
                ;
    }

    @Override
    public List<MedicosResponse> findAllMedicos() {
        return medicosRepository.findAllMedicos()
                .subscribeOn(Schedulers.boundedElastic()).collectList().block()
                ;
    }

    @Override
    public void deleteById(String uuid) {
        medicosRepository.deleteById(UUID.fromString(uuid));
    }
}
