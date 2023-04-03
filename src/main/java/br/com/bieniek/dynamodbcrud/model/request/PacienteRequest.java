package br.com.bieniek.dynamodbcrud.model.request;

public record PacienteRequest(
        String nome,
        String sobrenome,
        String sexo,
        String telefone,
        String celular,
        String email,
        EnderecoRequest endereco
) {
}
