package br.com.bieniek.dynamodbcrud.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.time.LocalDate;
//import java.util.List;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDbBean
public class Medicos {

    private UUID medicoID;
    private String nome;
    private String sobrenome;
    private String sexo;
    private String especializacao;
    private String telefone;
    private String celular;
    private String email;
    private Endereco endereco;
    private List<Consulta> consultas;

    @DynamoDbPartitionKey // This is the primary key
    public UUID getMedicoID() {
        return medicoID;
    }
}
