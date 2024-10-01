package com.fiap.hackathon.appointmentservice.usecases.agendamento;


import com.fiap.hackathon.appointmentservice.domain.entity.agendamento.enums.StatusAgendamento;
import com.fiap.hackathon.appointmentservice.domain.entity.agendamento.gateway.AgendamentoGateway;
import com.fiap.hackathon.appointmentservice.domain.entity.agendamento.model.Agendamento;
import com.fiap.hackathon.appointmentservice.infrastructure.listeners.AgendamentoListenerDto;
import com.fiap.hackathon.appointmentservice.infrastructure.medico.response.DoctorScheduleRequestDTO;
import com.fiap.hackathon.appointmentservice.infrastructure.persistence.agendamento.AgendamentoEntity;
import com.fiap.hackathon.appointmentservice.usecases.medico.MedicoUseCase;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

public class AgendamentoUseCase {

    private final AgendamentoGateway agendamentoGateway;
    private final MedicoUseCase medicoUseCase;

    public AgendamentoUseCase(AgendamentoGateway agendamentoGateway, MedicoUseCase medicoUseCase) {
        this.agendamentoGateway = agendamentoGateway;
        this.medicoUseCase = medicoUseCase;
    }

    public List<Agendamento> consultarAgendamentosPorCpf(String cpf) {
        return agendamentoGateway.buscarAgendamentosPorCpf(cpf);
    }

    public List<Agendamento> consultarAgendamentosPorMedicoId(UUID medicoId) {
        return agendamentoGateway.buscarAgendamentosPorMedicoId(medicoId);
    }

    public boolean atualizarStatusAgendamento(UUID idAgendamento, StatusAgendamento status) {
        return agendamentoGateway.atualizarStatusAgendamento(idAgendamento, status);
    }
    public void realizarAgendamento(AgendamentoListenerDto agendamentoListenerDto) {
        LocalDate dataAgenda = LocalDate.parse(agendamentoListenerDto.getDataAgenda(), DateTimeFormatter.ISO_LOCAL_DATE);
        LocalTime horaAgenda = LocalTime.parse(agendamentoListenerDto.getHoraAgenda(), DateTimeFormatter.ofPattern("HH:mm"));
        LocalDateTime dataHoraAgendamento = LocalDateTime.of(dataAgenda, horaAgenda);

        // Chamar o use case para realizar o agendamento via Feign Client
        StatusAgendamento status = medicoUseCase.scheduleDoctorAppointment(
                UUID.fromString(agendamentoListenerDto.getMedicoId()),
                agendamentoListenerDto.getDataAgenda(),
                agendamentoListenerDto.getHoraAgenda()
        );

        // Salvar o agendamento no banco de dados
        AgendamentoEntity agendamentoEntity = new AgendamentoEntity(
                agendamentoListenerDto.getCpfPaciente(),          // CPF do paciente
                agendamentoListenerDto.getMedicoId(),             // ID do médico
                dataHoraAgendamento,                              // Data e hora do agendamento
                status                       // Status AGENDADO
        );

        // Persistir a entidade no gateway de agendamento
        agendamentoGateway.save(agendamentoEntity);
    }

}


