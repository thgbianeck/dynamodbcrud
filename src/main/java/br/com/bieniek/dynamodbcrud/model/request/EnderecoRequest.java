package br.com.bieniek.dynamodbcrud.model.request;

public record EnderecoRequest(
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String estado,
        String pais,
        String cep
) {
}
