package com.fiap.hackathon.appointmentservice.infrastructure.medico.response;

import java.util.UUID;

public class DoctorScheduleRequestDTO {
    private UUID doctorId;
    private String dataAgenda;
    private String horaAgenda;

    // Getters e Setters

    public UUID getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(UUID doctorId) {
        this.doctorId = doctorId;
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
}
