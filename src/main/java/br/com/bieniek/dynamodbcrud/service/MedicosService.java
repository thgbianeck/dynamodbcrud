package br.com.bieniek.dynamodbcrud.service;

import br.com.bieniek.dynamodbcrud.model.request.MedicosRequest;
import br.com.bieniek.dynamodbcrud.model.response.MedicosResponse;

import java.util.List;

public interface MedicosService {

    MedicosResponse save(MedicosRequest medicos);
    MedicosResponse getMedicosById(String medicosId);

    List<MedicosResponse> findAllMedicos();

    void deleteById(String uuid);

}
