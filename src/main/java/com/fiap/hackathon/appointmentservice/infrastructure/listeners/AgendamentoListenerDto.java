package com.fiap.hackathon.appointmentservice.infrastructure.listeners;

import io.swagger.v3.oas.annotations.media.Schema;

public class AgendamentoListenerDto {

    @Schema(description = "ID do médico", example = "123e4567-e89b-12d3-a456-426614174000")
    private String medicoId;

    @Schema(description = "CPF do paciente", example = "123.456.789-10")
    private String cpfPaciente;

    // Campo para a data do agendamento
    @Schema(description = "Data do agendamento", example = "2024-09-30")
    private String dataAgenda;

    // Campo para a hora do agendamento
    @Schema(description = "Hora do agendamento", example = "09:45")
    private String horaAgenda;

    @Schema(description = "Status do agendamento", example = "AGENDADO")
    private String status;

    // Getters e Setters
    public String getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(String medicoId) {
        this.medicoId = medicoId;
    }

    public String getCpfPaciente() {
        return cpfPaciente;
    }

    public void setCpfPaciente(String cpfPaciente) {
        this.cpfPaciente = cpfPaciente;
    }

    public String getDataAgenda() {
        return dataAgenda;
    }

    public void setDataAgenda(String dataAgenda) {
        this.dataAgenda = dataAgenda;
    }

    public String getHoraAgenda() {
        return horaAgenda;
    }

    public void setHoraAgenda(String horaAgenda) {
        this.horaAgenda = horaAgenda;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
