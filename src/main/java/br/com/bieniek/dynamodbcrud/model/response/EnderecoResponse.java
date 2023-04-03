package br.com.bieniek.dynamodbcrud.model.response;

import lombok.Builder;

@Builder
public record EnderecoResponse(
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
