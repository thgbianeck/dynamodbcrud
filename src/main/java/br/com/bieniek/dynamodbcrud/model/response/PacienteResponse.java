package br.com.bieniek.dynamodbcrud.model.response;

import lombok.Builder;

@Builder
public record PacienteResponse(
        String nome,
        String sobrenome,
        String sexo,
        String telefone,
        String celular,
        String email,
        EnderecoResponse endereco
) {
}
