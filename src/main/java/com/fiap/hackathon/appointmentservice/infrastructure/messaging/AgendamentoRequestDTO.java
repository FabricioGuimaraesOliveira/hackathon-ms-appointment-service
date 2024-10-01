package com.fiap.hackathon.appointmentservice.infrastructure.messaging;//package com.fiap.greentracefood.infrastructure.messaging;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgendamentoRequestDTO {

    @Schema(description = "ID do médico", example = "123e4567-e89b-12d3-a456-426614174000")
    private String medicoId;

    @Schema(description = "CPF do paciente", example = "123.456.789-10")
    private String cpfPaciente;

    @Schema(description = "Horário do agendamento", example = "2024-09-30T10:00")
    private String horario;
}