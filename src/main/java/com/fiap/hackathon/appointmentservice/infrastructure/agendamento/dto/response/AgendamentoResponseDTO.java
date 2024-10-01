package com.fiap.hackathon.appointmentservice.infrastructure.agendamento.dto.response;

import com.fiap.hackathon.appointmentservice.domain.entity.agendamento.enums.StatusAgendamento;
import io.swagger.v3.oas.annotations.media.Schema;

public class AgendamentoResponseDTO {

    @Schema(description = "ID do agendamento", example = "123e4567-e89b-12d3-a456-426614174000")
    private String idAgendamento;

    @Schema(description = "CPF do paciente", example = "123.456.789-00")
    private String cpfPaciente;

    @Schema(description = "ID do médico", example = "d290f1ee-6c54-4b01-90e6-d701748f0851")
    private String medicoId;

    @Schema(description = "Horário do agendamento", example = "2024-09-30T10:00:00")
    private String horarioAgendamento;  // Mudança para String

    @Schema(description = "Status do agendamento", example = "AGENDADO")
    private StatusAgendamento status;

    // Construtor
    public AgendamentoResponseDTO(String idAgendamento, String cpfPaciente, String medicoId, String horarioAgendamento, StatusAgendamento status) {
        this.idAgendamento = idAgendamento;
        this.cpfPaciente = cpfPaciente;
        this.medicoId = medicoId;
        this.horarioAgendamento = horarioAgendamento;  // Ajustado para String
        this.status = status;
    }

    // Getters e Setters
    public String getIdAgendamento() {
        return idAgendamento;
    }

    public void setIdAgendamento(String idAgendamento) {
        this.idAgendamento = idAgendamento;
    }

    public String getCpfPaciente() {
        return cpfPaciente;
    }

    public void setCpfPaciente(String cpfPaciente) {
        this.cpfPaciente = cpfPaciente;
    }

    public String getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(String medicoId) {
        this.medicoId = medicoId;
    }

    public String getHorarioAgendamento() {
        return horarioAgendamento;
    }

    public void setHorarioAgendamento(String horarioAgendamento) {
        this.horarioAgendamento = horarioAgendamento;
    }

    public StatusAgendamento getStatus() {
        return status;
    }

    public void setStatus(StatusAgendamento status) {
        this.status = status;
    }
}
