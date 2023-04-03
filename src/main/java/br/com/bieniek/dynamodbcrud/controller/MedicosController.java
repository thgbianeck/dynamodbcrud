package br.com.bieniek.dynamodbcrud.controller;

import br.com.bieniek.dynamodbcrud.model.request.MedicosRequest;
import br.com.bieniek.dynamodbcrud.model.response.MedicosResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/medicos")
public interface MedicosController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<MedicosResponse> save(@Valid @RequestBody MedicosRequest medicosRequest);

    @GetMapping(value = "/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<MedicosResponse> findById(@PathVariable String uuid);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<MedicosResponse>> findAll();

    @DeleteMapping(value = "/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<Void> deleteById(@PathVariable String uuid);

}
