package com.fiap.hackathon.appointmentservice.infrastructure.persistence.agendamento;

import com.fiap.hackathon.appointmentservice.domain.entity.agendamento.enums.StatusAgendamento;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@DynamoDbBean
public class AgendamentoEntity {

    private String protocolo;
    private String cpfPaciente;
    private String idMedico;
    private LocalDateTime horarioAgendamento;
    private StatusAgendamento status;

    public AgendamentoEntity() {
        this.protocolo = UUID.randomUUID().toString();
    }

    public AgendamentoEntity(String cpfPaciente, String idMedico, LocalDateTime horarioAgendamento, StatusAgendamento status) {
        this();
        this.cpfPaciente = cpfPaciente;
        this.idMedico = idMedico;
        this.horarioAgendamento = horarioAgendamento;
        this.status = status;
    }

    @DynamoDbPartitionKey
    @DynamoDbAttribute("protocolo")
    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    @DynamoDbAttribute("cpfPaciente")
    public String getCpfPaciente() {
        return cpfPaciente;
    }

    public void setCpfPaciente(String cpfPaciente) {
        this.cpfPaciente = cpfPaciente;
    }

    @DynamoDbAttribute("idMedico")
    public String getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(String idMedico) {
        this.idMedico = idMedico;
    }

    @DynamoDbAttribute("horarioAgendamento")
    public LocalDateTime getHorarioAgendamento() {
        return horarioAgendamento;
    }

    public void setHorarioAgendamento(LocalDateTime horarioAgendamento) {
        this.horarioAgendamento = horarioAgendamento;
    }

    @DynamoDbAttribute("status")
    public StatusAgendamento getStatus() {
        return status;
    }

    public void setStatus(StatusAgendamento status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AgendamentoEntity that)) return false;
        return Objects.equals(getProtocolo(), that.getProtocolo()) &&
                Objects.equals(getCpfPaciente(), that.getCpfPaciente()) &&
                Objects.equals(getIdMedico(), that.getIdMedico()) &&
                Objects.equals(getHorarioAgendamento(), that.getHorarioAgendamento()) &&
                getStatus() == that.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProtocolo(), getCpfPaciente(), getIdMedico(), getHorarioAgendamento(), getStatus());
    }

    @Override
    public String toString() {
        return "AgendamentoEntity{" +
                "protocolo='" + protocolo + '\'' +
                ", cpfPaciente='" + cpfPaciente + '\'' +
                ", idMedico='" + idMedico + '\'' +
                ", horarioAgendamento=" + horarioAgendamento +
                ", status=" + status +
                '}';
    }
}
