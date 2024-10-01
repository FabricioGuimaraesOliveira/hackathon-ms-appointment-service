//package com.fiap.hackathon.patientservice.infrastructure.messaging;//package com.fiap.greentracefood.infrastructure.messaging;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fiap.hackathon.patientservice.domain.entity.agendamento.model.Agendamento;
//import io.awspring.cloud.sqs.operations.SqsTemplate;
//import lombok.RequiredArgsConstructor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//
//@Service
//@RequiredArgsConstructor
//public class AgendamentoSender {
//
//    @Value("${aws.sqs.queue.agendamento}")
//    private String PROCESSED_QUEUE_NAME;
//
//    private static final Logger logger = LoggerFactory.getLogger(AgendamentoSender.class);
//    private final ObjectMapper objectMapper ;
//
//    private final SqsTemplate sqsTemplate;
//
//
//    public void sendAgendamentoMessage(Agendamento agendamento) {
//        try {
//            objectMapper.registerModule(new JavaTimeModule());
//            String jsonMessage = objectMapper.writeValueAsString(agendamento);
//            sqsTemplate.send(PROCESSED_QUEUE_NAME, jsonMessage);
//            logger.info("Message sent to {} successfully", PROCESSED_QUEUE_NAME);
//            logger.info("Agendamento Send Status: {}", jsonMessage);
//
//        } catch (Exception e) {
//            logger.error("Error sending message to {}", PROCESSED_QUEUE_NAME, e);
//        }
//    }
//}
