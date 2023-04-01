package br.com.bieniek.dynamodbcrud.runner;

import br.com.bieniek.dynamodbcrud.entity.Consulta;
import br.com.bieniek.dynamodbcrud.entity.Endereco;
import br.com.bieniek.dynamodbcrud.entity.Medicos;
import br.com.bieniek.dynamodbcrud.entity.Paciente;
import br.com.bieniek.dynamodbcrud.service.MedicosService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class CommandLineRunnerTest implements CommandLineRunner {

    private final MedicosService medicosService;

    @Override
    public void run(String... args) throws Exception {
        log.info("CommandLineRunnerTest");
//        saveMedicos();
        buscarMedicos("164ff902-2df0-4113-b5b9-3fb13d408733");
    }

    private void buscarMedicos(String s) {
        log.info("Buscando medicos com id: {}", s);
        Medicos medicosById = medicosService.getMedicosById(UUID.fromString(s));
        try {
            log.info("\nMedico: {}\n", new ObjectMapper().writeValueAsString(medicosById));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveMedicos() {
        Endereco endereco = Endereco.builder()
                .logradouro("Rua dos Bobos")
                .numero("0")
                .complemento("Casa")
                .bairro("Centro")
                .cidade("São Paulo")
                .estado("SP")
                .pais("Brasil")
                .build();

        Paciente paciente1 = Paciente.builder()
                .nome("Joao")
                .sobrenome("Silva")
                .sexo("M").build();

        Paciente paciente2 = Paciente.builder()
                .nome("Maria")
                .sobrenome("Silva")
                .sexo("F").build();

        Paciente paciente3 = Paciente.builder()
                .nome("Jose")
                .sobrenome("Silva")
                .sexo("M").build();

        List<Consulta> consultas = List.of(Consulta.builder()
                        .consultaID(1L)
                        .data(LocalDateTime.now().toString())
                        .horario("10:00")
                        .paciente(paciente1)
                        .build(),
                Consulta.builder()
                        .consultaID(2L)
                        .data(LocalDateTime.now().toString())
                        .horario("11:00")
                        .paciente(paciente2)
                        .build(),

                Consulta.builder()
                        .consultaID(3L)
                        .data(LocalDateTime.now().toString())
                        .horario("12:00")
                        .paciente(paciente3)
                        .build()
        );

        Medicos medicos = Medicos.builder()
                .nome("Joao")
                .sobrenome("Silva")
                .sexo("M")
                .email("drjoao@mail.com")
                .especializacao("Cardiologista")
                .celular("11999999999")
                .telefone("1133333333")
                .endereco(endereco)
                .consultas(consultas)
                .build();
        log.info("saveMedicos: {}", medicos);
        try {
            Medicos save = medicosService.save(medicos);
            log.info("save: {}", new ObjectMapper().writeValueAsString(save));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
