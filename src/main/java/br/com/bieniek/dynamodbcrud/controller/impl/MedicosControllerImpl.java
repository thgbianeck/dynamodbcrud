package br.com.bieniek.dynamodbcrud.controller.impl;

import br.com.bieniek.dynamodbcrud.controller.MedicosController;
import br.com.bieniek.dynamodbcrud.model.request.MedicosRequest;
import br.com.bieniek.dynamodbcrud.model.response.MedicosResponse;
import br.com.bieniek.dynamodbcrud.service.MedicosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MedicosControllerImpl implements MedicosController {

    private final MedicosService medicosService;

    @Override
    public ResponseEntity<MedicosResponse> save(MedicosRequest medicosRequest) {
        try {
            return ResponseEntity.ok(medicosService.save(medicosRequest));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<MedicosResponse> findById(String uuid) {
        try {
            return ResponseEntity.ok(medicosService.getMedicosById(uuid));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<List<MedicosResponse>> findAll() {
        return ResponseEntity.ok(medicosService.findAllMedicos());
    }

    @Override
    public ResponseEntity<Void> deleteById(String uuid) {
        try {
            medicosService.deleteById(uuid);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
