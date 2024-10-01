package com.fiap.hackathon.appointmentservice.infrastructure.medico.client;

import com.fiap.hackathon.appointmentservice.infrastructure.medico.response.DoctorScheduleRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "medico-service", url = "${medico-service.url}")
public interface MedicoClient {

    @PatchMapping("/doctor-calendars/doctor/schedule")
    void scheduleAppointment(@RequestBody DoctorScheduleRequestDTO requestDTO);
}
