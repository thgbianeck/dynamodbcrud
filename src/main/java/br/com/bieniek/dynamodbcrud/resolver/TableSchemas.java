package br.com.bieniek.dynamodbcrud.resolver;

import br.com.bieniek.dynamodbcrud.entity.Consulta;
import br.com.bieniek.dynamodbcrud.entity.Endereco;
import br.com.bieniek.dynamodbcrud.entity.Medicos;
import br.com.bieniek.dynamodbcrud.entity.Paciente;
import software.amazon.awssdk.enhanced.dynamodb.EnhancedType;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.UUID;

import static software.amazon.awssdk.enhanced.dynamodb.mapper.StaticAttributeTags.primaryPartitionKey;

public class TableSchemas {

    public static final TableSchema<Endereco> ENDERECOS_TABLE_SCHEMA = TableSchema.builder(Endereco.class)
            .newItemSupplier(Endereco::new)
            .addAttribute(String.class, a -> a.name("logradouro")
                    .getter(Endereco::getLogradouro)
                    .setter(Endereco::setLogradouro))
            .addAttribute(String.class, a -> a.name("numero")
                    .getter(Endereco::getNumero)
                    .setter(Endereco::setNumero))
            .addAttribute(String.class, a -> a.name("complemento")
                    .getter(Endereco::getComplemento)
                    .setter(Endereco::setComplemento))
            .addAttribute(String.class, a -> a.name("bairro")
                    .getter(Endereco::getBairro)
                    .setter(Endereco::setBairro))
            .addAttribute(String.class, a -> a.name("cidade")
                    .getter(Endereco::getCidade)
                    .setter(Endereco::setCidade))
            .addAttribute(String.class, a -> a.name("estado")
                    .getter(Endereco::getEstado)
                    .setter(Endereco::setEstado))
            .addAttribute(String.class, a -> a.name("cep")
                    .getter(Endereco::getCep)
                    .setter(Endereco::setCep))
            .build();

    public static final TableSchema<Paciente> PACIENTE_TABLE_SCHEMA = TableSchema.builder(Paciente.class)
            .newItemSupplier(Paciente::new)
            .addAttribute(String.class, a -> a.name("nome")
                    .getter(Paciente::getNome)
                    .setter(Paciente::setNome))
            .addAttribute(String.class, a -> a.name("sobrenome")
                    .getter(Paciente::getSobrenome)
                    .setter(Paciente::setSobrenome))
            .addAttribute(String.class, a -> a.name("sexo")
                    .getter(Paciente::getSexo)
                    .setter(Paciente::setSexo))
            .addAttribute(String.class, a -> a.name("telefone")
                    .getter(Paciente::getTelefone)
                    .setter(Paciente::setTelefone))
            .addAttribute(String.class, a -> a.name("celular")
                    .getter(Paciente::getCelular)
                    .setter(Paciente::setCelular))
            .addAttribute(String.class, a -> a.name("email")
                    .getter(Paciente::getEmail)
                    .setter(Paciente::setEmail))
            .addAttribute(EnhancedType.documentOf(Endereco.class, ENDERECOS_TABLE_SCHEMA), a -> a.name("endereco")
                    .getter(Paciente::getEndereco)
                    .setter(Paciente::setEndereco))
            .build();

    public static final TableSchema<Consulta> CONSULTA_TABLE_SCHEMA = TableSchema.builder(Consulta.class)
            .newItemSupplier(Consulta::new)
            .addAttribute(Long.class, a -> a.name("consultaID")
                    .getter(Consulta::getConsultaID)
                    .setter(Consulta::setConsultaID)
                    .tags(primaryPartitionKey()))
            .addAttribute(String.class, a -> a.name("data")
                    .getter(Consulta::getData)
                    .setter(Consulta::setData))
            .addAttribute(String.class, a -> a.name("horario")
                    .getter(Consulta::getHorario)
                    .setter(Consulta::setHorario))
            .addAttribute(EnhancedType.documentOf(Paciente.class, PACIENTE_TABLE_SCHEMA), a -> a.name("paciente")
                    .getter(Consulta::getPaciente)
                    .setter(Consulta::setPaciente))
            .build();


    public static final TableSchema<Medicos> MEDICOS_TABLE_SCHEMA = TableSchema.builder(Medicos.class)
            .newItemSupplier(Medicos::new)
            .addAttribute(UUID.class, u -> u.name("medicoID")
                    .getter(Medicos::getMedicoID)
                    .setter(Medicos::setMedicoID)
                    .tags(primaryPartitionKey()))
            .addAttribute(String.class, a -> a.name("nome")
                    .getter(Medicos::getNome)
                    .setter(Medicos::setNome))
            .addAttribute(String.class, a -> a.name("sobrenome")
                    .getter(Medicos::getSobrenome)
                    .setter(Medicos::setSobrenome))
            .addAttribute(String.class, a -> a.name("sexo")
                    .getter(Medicos::getSexo)
                    .setter(Medicos::setSexo))
            .addAttribute(String.class, a -> a.name("especializacao")
                    .getter(Medicos::getEspecializacao)
                    .setter(Medicos::setEspecializacao))
            .addAttribute(String.class, a -> a.name("telefone")
                    .getter(Medicos::getTelefone)
                    .setter(Medicos::setTelefone))
            .addAttribute(String.class, a -> a.name("celular")
                    .getter(Medicos::getCelular)
                    .setter(Medicos::setCelular))
            .addAttribute(String.class, a -> a.name("email")
                    .getter(Medicos::getEmail)
                    .setter(Medicos::setEmail))
            .addAttribute(
                    EnhancedType.documentOf(Endereco.class, ENDERECOS_TABLE_SCHEMA),
                    a -> a.name("endereco")
                            .getter(Medicos::getEndereco)
                            .setter(Medicos::setEndereco))
            .addAttribute(
                    EnhancedType.listOf(EnhancedType.documentOf(Consulta.class, CONSULTA_TABLE_SCHEMA)),
                    a -> a.name("consultas")
                            .getter(Medicos::getConsultas)
                            .setter(Medicos::setConsultas))
            .build();


}
