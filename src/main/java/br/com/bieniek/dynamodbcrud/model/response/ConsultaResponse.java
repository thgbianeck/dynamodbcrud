package br.com.bieniek.dynamodbcrud.model.response;

import lombok.Builder;

@Builder
public record ConsultaResponse(
        Long consultaID,
        String data,
        String horario,
        PacienteResponse paciente
) {
}
