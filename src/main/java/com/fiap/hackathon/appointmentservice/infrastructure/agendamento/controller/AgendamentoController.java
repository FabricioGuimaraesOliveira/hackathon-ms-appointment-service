package com.fiap.hackathon.appointmentservice.infrastructure.agendamento.controller;

import com.fiap.hackathon.appointmentservice.domain.entity.agendamento.enums.StatusAgendamento;
import com.fiap.hackathon.appointmentservice.domain.entity.agendamento.model.Agendamento;
import com.fiap.hackathon.appointmentservice.infrastructure.agendamento.dto.response.AgendamentoResponseDTO;
import com.fiap.hackathon.appointmentservice.usecases.agendamento.AgendamentoUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/agendamentos")
@Tag(name = "agendamento", description = "API responsável pelo gerenciamento de agendamentos.")
public class AgendamentoController {

    private final AgendamentoUseCase agendamentoUseCase;

    public AgendamentoController(AgendamentoUseCase agendamentoUseCase) {
        this.agendamentoUseCase = agendamentoUseCase;
    }

    @Operation(summary = "Consultar agendamentos por CPF do paciente ou ID do médico",
            description = "Este endpoint permite que um usuário consulte os agendamentos pelo CPF do paciente ou pelo ID do médico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agendamentos encontrados com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Agendamento.class)) }),
            @ApiResponse(responseCode = "404", description = "Nenhum agendamento encontrado para os parâmetros fornecidos",
                    content = @Content)
    })
    @GetMapping("/consultar")
    public ResponseEntity<List<AgendamentoResponseDTO>> consultarAgendamentos(
            @RequestParam(required = false) String cpf,
            @RequestParam(required = false) UUID medicoId) {

        if (cpf == null && medicoId == null) {
            return ResponseEntity.badRequest().body(null);
        }

        List<Agendamento> agendamentos;

        if (cpf != null) {
            agendamentos = agendamentoUseCase.consultarAgendamentosPorCpf(cpf);
        }
        else {
            agendamentos = agendamentoUseCase.consultarAgendamentosPorMedicoId(medicoId);
        }

        if (agendamentos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<AgendamentoResponseDTO> responseDTOs = agendamentos.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseDTOs);
    }

    @Operation(summary = "Atualizar o status de um agendamento",
            description = "Este endpoint permite ao usuário atualizar o status de um agendamento para CANCELADO ou REALIZADO.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status do agendamento atualizado com sucesso",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado",
                    content = @Content)
    })
    @PutMapping("/atualizar-status/{idAgendamento}")
    public ResponseEntity<String> atualizarStatusAgendamento(
            @PathVariable UUID idAgendamento,
            @RequestParam StatusAgendamento status) {

        if (status != StatusAgendamento.CANCELADO && status != StatusAgendamento.REALIZADO) {
            return ResponseEntity.badRequest().body("Status inválido. Somente 'CANCELADO' ou 'REALIZADO' são permitidos.");
        }

        boolean atualizado = agendamentoUseCase.atualizarStatusAgendamento(idAgendamento, status);

        if (!atualizado) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Status do agendamento atualizado com sucesso.");
    }

    public AgendamentoResponseDTO toResponseDTO(Agendamento agendamento) {
        // Formatar LocalDateTime para String
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        String horarioAgendamento = agendamento.getHorarioAgendamento().format(formatter);

        return new AgendamentoResponseDTO(
                agendamento.getProtocolo(),
                agendamento.getCpfPaciente(),
                agendamento.getMedicoId(),
                horarioAgendamento,
                agendamento.getStatus()
        );
    }
}
