package br.com.bieniek.dynamodbcrud.model.request;

import br.com.bieniek.dynamodbcrud.entity.Paciente;

public record ConsultaRequest(
        Long consultaID,
        String data,
        String horario,
        PacienteRequest paciente
) {
}
