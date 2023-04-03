package br.com.bieniek.dynamodbcrud.model.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record MedicosResponse(
        UUID medicoId,
        String nome,
        String sobrenome,
        String sexo,
        String especializacao,
        String telefone,
        String celular,
        EnderecoResponse endereco,
        List<ConsultaResponse> consultas,
        String email) {
}
