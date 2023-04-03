package br.com.bieniek.dynamodbcrud.model.request;

import lombok.Builder;

import java.util.List;

@Builder
public record MedicosRequest(
        String nome,
        String sobrenome,
        String sexo,
        String especializacao,
        String telefone,
        String celular,
        EnderecoRequest endereco,
        List<ConsultaRequest> consultas,
        String email) {
}
