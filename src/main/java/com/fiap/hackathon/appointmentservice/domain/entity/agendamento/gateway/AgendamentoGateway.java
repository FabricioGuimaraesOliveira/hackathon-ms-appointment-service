package com.fiap.hackathon.appointmentservice.domain.entity.agendamento.gateway;

import com.fiap.hackathon.appointmentservice.domain.entity.agendamento.enums.StatusAgendamento;
import com.fiap.hackathon.appointmentservice.domain.entity.agendamento.model.Agendamento;
import com.fiap.hackathon.appointmentservice.infrastructure.persistence.agendamento.AgendamentoEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AgendamentoGateway {

    List<Agendamento> buscarAgendamentosPorCpf(String cpf);

    List<Agendamento> buscarAgendamentosPorMedicoId(UUID medicoId);
    Boolean atualizarStatusAgendamento(UUID idAgendamento, StatusAgendamento status);
    Optional<Agendamento> buscarAgendamentoPorId(UUID agendamentoId);
    Agendamento save(AgendamentoEntity agendamentoEntity);
}
