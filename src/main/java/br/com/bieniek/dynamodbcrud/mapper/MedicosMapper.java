package br.com.bieniek.dynamodbcrud.mapper;

import br.com.bieniek.dynamodbcrud.entity.Consulta;
import br.com.bieniek.dynamodbcrud.entity.Endereco;
import br.com.bieniek.dynamodbcrud.entity.Medicos;
import br.com.bieniek.dynamodbcrud.entity.Paciente;
import br.com.bieniek.dynamodbcrud.model.request.ConsultaRequest;
import br.com.bieniek.dynamodbcrud.model.request.EnderecoRequest;
import br.com.bieniek.dynamodbcrud.model.request.MedicosRequest;
import br.com.bieniek.dynamodbcrud.model.request.PacienteRequest;
import br.com.bieniek.dynamodbcrud.model.response.ConsultaResponse;
import br.com.bieniek.dynamodbcrud.model.response.EnderecoResponse;
import br.com.bieniek.dynamodbcrud.model.response.MedicosResponse;
import br.com.bieniek.dynamodbcrud.model.response.PacienteResponse;
import org.bouncycastle.asn1.cmc.CMCStatusInfoBuilder;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class MedicosMapper {

    public static Medicos toMedicosFrom(UUID medicoId, MedicosRequest medicosRequest) {
        return Medicos.builder()
                .medicoID(medicoId)
                .nome(medicosRequest.nome())
                .sobrenome(medicosRequest.sobrenome())
                .sexo(medicosRequest.sexo())
                .especializacao(medicosRequest.especializacao())
                .telefone(medicosRequest.telefone())
                .celular(medicosRequest.celular())
                .email(medicosRequest.email())
                .endereco(toEnderecoFrom(medicosRequest))
                .consultas(toConsultasFrom(medicosRequest))
                .build();
    }

    public static MedicosResponse toMedicosResponseFrom(Medicos medicos) {
        return MedicosResponse.builder()
                .medicoId(medicos.getMedicoID())
                .nome(medicos.getNome())
                .sobrenome(medicos.getSobrenome())
                .sexo(medicos.getSexo())
                .especializacao(medicos.getEspecializacao())
                .telefone(medicos.getTelefone())
                .celular(medicos.getCelular())
                .email(medicos.getEmail())
                .endereco(toEnderecoResponseFrom(medicos))
                .consultas(toConsultasResponseFrom(medicos))
                .build();
    }

    private static List<Consulta> toConsultasFrom(MedicosRequest medicosRequest) {
        List<ConsultaRequest> consultas = medicosRequest.consultas();
        return (Objects.nonNull(consultas)) ? consultas.stream()
                .map(request -> Consulta.builder()
                        .data(request.data())
                        .horario(request.horario())
                        .paciente(toPacientefrom(request))
                        .build())
                .toList() : null;
    }

    private static List<ConsultaResponse> toConsultasResponseFrom(Medicos medicos) {
        List<Consulta> consultas = medicos.getConsultas();
        return (Objects.nonNull(consultas)) ? consultas.stream()
                .map(consulta -> ConsultaResponse.builder()
                        .data(consulta.getData())
                        .horario(consulta.getHorario())
                        .paciente(toPacienteResponsefrom(consulta))
                        .build())
                .toList() : null;
    }

    private static Paciente toPacientefrom(ConsultaRequest request) {
        PacienteRequest paciente = request.paciente();
        return (Objects.nonNull(paciente)) ? Paciente.builder()
                .nome(paciente.nome())
                .sobrenome(paciente.sobrenome())
                .sexo(paciente.sexo())
                .telefone(paciente.telefone())
                .celular(paciente.celular())
                .email(paciente.email())
                .endereco(toEnderecoFrom(paciente))
                .build() : null;
    }

    private static PacienteResponse toPacienteResponsefrom(Consulta consulta) {
        Paciente paciente = consulta.getPaciente();
        return (Objects.nonNull(paciente)) ? PacienteResponse.builder()
                .nome(paciente.getNome())
                .sobrenome(paciente.getSobrenome())
                .sexo(paciente.getSexo())
                .telefone(paciente.getTelefone())
                .celular(paciente.getCelular())
                .email(paciente.getEmail())
                .endereco(toEnderecoResponseFrom(paciente))
                .build() : null;
    }

    private static Endereco toEnderecoFrom(PacienteRequest paciente) {
        return toEnderecoFrom(paciente.endereco());
    }

    private static EnderecoResponse toEnderecoResponseFrom(Paciente paciente) {
        return toEnderecoResponseFrom(paciente.getEndereco());
    }

    private static EnderecoResponse toEnderecoResponseFrom(Medicos medico) {
        return toEnderecoResponseFrom(medico.getEndereco());
    }

    private static EnderecoResponse toEnderecoResponseFrom(Endereco endereco) {
        return (Objects.nonNull(endereco)) ? EnderecoResponse.builder()
                .logradouro(endereco.getLogradouro())
                .numero(endereco.getNumero())
                .complemento(endereco.getComplemento())
                .bairro(endereco.getBairro())
                .cidade(endereco.getCidade())
                .estado(endereco.getEstado())
                .pais(endereco.getPais())
                .cep(endereco.getCep())
                .build() : null;
    }

    private static Endereco toEnderecoFrom(MedicosRequest medicosRequest) {
        return toEnderecoFrom(medicosRequest.endereco());
    }

    private static Endereco toEnderecoFrom(EnderecoRequest request) {
        return (Objects.nonNull(request)) ? Endereco.builder()
                .logradouro(request.logradouro())
                .numero(request.numero())
                .complemento(request.complemento())
                .bairro(request.bairro())
                .cidade(request.cidade())
                .estado(request.estado())
                .pais(request.pais())
                .cep(request.cep())
                .build() : null;
    }


}
