package br.com.bieniek.dynamodbcrud.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDbBean
public class Paciente {
    private Long pacienteID;
    private String nome;
    private String sobrenome;
    private String sexo;
    private String telefone;
    private String celular;
    private String email;
    private Endereco endereco;
}
