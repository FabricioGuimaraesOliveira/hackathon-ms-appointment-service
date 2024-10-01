package com.fiap.hackathon.appointmentservice.infrastructure.listeners;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fiap.hackathon.appointmentservice.usecases.agendamento.AgendamentoUseCase;
import io.awspring.cloud.sqs.annotation.SqsListener;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AgendamentoListener {

    private static final Logger logger = LoggerFactory.getLogger(AgendamentoListener.class);
    private final AgendamentoUseCase agendamentoUseCase;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final SqsTemplate sqsTemplate;

    @SqsListener("${aws.sqs.queue.appointment}")
    public void receiveMessage(String message) {
        try {
            objectMapper.registerModule(new JavaTimeModule());
            AgendamentoListenerDto agendamentoListenerDto = objectMapper.readValue(message, AgendamentoListenerDto.class);
            logger.info("Received appointment: {}", agendamentoListenerDto);
            agendamentoUseCase.realizarAgendamento(agendamentoListenerDto);
        } catch (Exception e) {
            logger.error("Error processing message", e);
        }

    }
}
