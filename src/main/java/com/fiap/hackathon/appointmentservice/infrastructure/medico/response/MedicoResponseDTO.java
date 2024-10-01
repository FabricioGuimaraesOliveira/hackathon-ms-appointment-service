package com.fiap.hackathon.appointmentservice.infrastructure.medico.response;

import java.util.List;
import java.util.UUID;

public class MedicoResponseDTO {

    private UUID id;
    private String nome;
    private String especialidade;
    private List<String> horariosDisponiveis;

    // Getters e Setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public List<String> getHorariosDisponiveis() {
        return horariosDisponiveis;
    }

    public void setHorariosDisponiveis(List<String> horariosDisponiveis) {
        this.horariosDisponiveis = horariosDisponiveis;
    }
}
