package com.fiap.hackathon.patientservice.domain.entity.agendamento.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Medico {

    private UUID id;
    private String nome;
    private String crm;
    private String especialidade;  // Adicionado o campo especialidade
    private List<LocalDateTime> horariosDisponiveis;

    public Medico(UUID id, String nome, String crm, String especialidade, List<LocalDateTime> horariosDisponiveis) {
        this.id = id;
        this.nome = nome;
        this.crm = crm;
        this.especialidade = especialidade;  // Inicializa o campo especialidade
        this.horariosDisponiveis = horariosDisponiveis;
    }

    // Verifica se o horário está disponível para o médico
    public boolean isHorarioDisponivel(LocalDateTime horario) {
        return horariosDisponiveis.contains(horario);
    }

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

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public List<LocalDateTime> getHorariosDisponiveis() {
        return horariosDisponiveis;
    }

    public void setHorariosDisponiveis(List<LocalDateTime> horariosDisponiveis) {
        this.horariosDisponiveis = horariosDisponiveis;
    }
}