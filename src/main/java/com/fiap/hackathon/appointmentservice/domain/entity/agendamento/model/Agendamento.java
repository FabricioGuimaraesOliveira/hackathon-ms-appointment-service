package com.fiap.hackathon.appointmentservice.domain.entity.agendamento.model;

import com.fiap.hackathon.appointmentservice.domain.entity.agendamento.enums.StatusAgendamento;

import java.time.LocalDateTime;
import java.util.UUID;

public class Agendamento {

    private String protocolo;
    private String cpfPaciente;
    private String medicoId;
    private LocalDateTime horarioAgendamento;
    private StatusAgendamento status; // Adiciona o status do agendamento

    // Construtor com geração de UUID para o idAgendamento
    public Agendamento(String cpfPaciente, String medicoId, LocalDateTime horarioAgendamento, StatusAgendamento status) {
        this.protocolo = UUID.randomUUID().toString(); // Gera um UUID automaticamente
        this.cpfPaciente = cpfPaciente;
        this.medicoId = medicoId;
        this.horarioAgendamento = horarioAgendamento;
        this.status = status; // Inicializa o status
    }

    // Construtor com idAgendamento explícito (usado quando se busca um agendamento existente)
    public Agendamento(String protocolo, String cpfPaciente, String medicoId, LocalDateTime horarioAgendamento, StatusAgendamento status) {
        this.protocolo = protocolo;
        this.cpfPaciente = cpfPaciente;
        this.medicoId = medicoId;
        this.horarioAgendamento = horarioAgendamento;
        this.status = status;
    }

    // Getters e Setters

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
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

    public LocalDateTime getHorarioAgendamento() {
        return horarioAgendamento;
    }

    public void setHorarioAgendamento(LocalDateTime horarioAgendamento) {
        this.horarioAgendamento = horarioAgendamento;
    }

    public StatusAgendamento getStatus() {
        return status;
    }

    public void setStatus(StatusAgendamento status) {
        this.status = status;
    }

    // Método de validação
    public void validar() throws IllegalArgumentException {
        if (cpfPaciente == null || cpfPaciente.trim().isEmpty()) {
            throw new IllegalArgumentException("CPF do paciente não pode ser nulo ou vazio.");
        }

        if (medicoId == null) {
            throw new IllegalArgumentException("ID do médico não pode ser nulo.");
        }

        if (horarioAgendamento == null) {
            throw new IllegalArgumentException("Horário de agendamento não pode ser nulo.");
        }
        if (horarioAgendamento.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("O horário de agendamento não pode estar no passado.");
        }

        if (status == null) {
            throw new IllegalArgumentException("O status do agendamento não pode ser nulo.");
        }
    }
}
