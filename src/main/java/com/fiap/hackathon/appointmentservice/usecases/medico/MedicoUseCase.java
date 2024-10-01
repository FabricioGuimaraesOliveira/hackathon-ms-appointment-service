package com.fiap.hackathon.appointmentservice.usecases.medico;


import com.fiap.hackathon.appointmentservice.domain.entity.agendamento.enums.StatusAgendamento;
import com.fiap.hackathon.appointmentservice.infrastructure.medico.client.MedicoClient;
import com.fiap.hackathon.appointmentservice.infrastructure.medico.response.DoctorScheduleRequestDTO;
import com.fiap.hackathon.appointmentservice.infrastructure.medico.response.MedicoResponseDTO;
import feign.FeignException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MedicoUseCase {

    private final MedicoClient medicoClient;

    public MedicoUseCase(MedicoClient medicoClient) {
        this.medicoClient = medicoClient;
    }

    public StatusAgendamento scheduleDoctorAppointment(UUID doctorId, String dataAgenda, String horaAgenda) {

        DoctorScheduleRequestDTO requestDTO = new DoctorScheduleRequestDTO();
        requestDTO.setDoctorId(doctorId);
        requestDTO.setDataAgenda(dataAgenda);
        requestDTO.setHoraAgenda(horaAgenda);

        try {
            medicoClient.scheduleAppointment(requestDTO);

            return StatusAgendamento.AGENDADO;
        } catch (FeignException e) {

            System.err.println("Erro ao tentar agendar o horário: " + e.getMessage());

            return  StatusAgendamento.ERROR;
        }
    }
}
