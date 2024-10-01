package com.fiap.hackathon.patientservice.domain.entity.paciente.gateway;



import com.fiap.hackathon.patientservice.domain.entity.paciente.model.Paciente;

import java.util.Optional;

public interface PacienteGateway{

    Paciente cadastrarPaciente(String name, String cpf, String email, String password);

    Optional<Paciente> buscarPacientePorCpf(String cpf);
}
