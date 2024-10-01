package com.fiap.hackathon.appointmentservice.infrastructure.configuration;


import com.fiap.hackathon.appointmentservice.domain.entity.agendamento.gateway.AgendamentoGateway;
import com.fiap.hackathon.appointmentservice.infrastructure.agendamento.gateway.AgendamentoDataBaseRepository;
import com.fiap.hackathon.appointmentservice.usecases.agendamento.AgendamentoUseCase;
import com.fiap.hackathon.appointmentservice.usecases.agendamento.AuthorizationUseCase;
import com.fiap.hackathon.appointmentservice.usecases.medico.MedicoUseCase;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;

@Configuration
public class BeanConfiguration {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Bean
	AgendamentoGateway createAgendamentoGateway(DynamoDbEnhancedClient enhancedClient, @Value("${dynamodb.tablename}") String tableName) {
		return new AgendamentoDataBaseRepository(enhancedClient,tableName);
	}


	@Bean
	AgendamentoUseCase createAgendamentoUseCase(AgendamentoGateway agendamentoGateway, MedicoUseCase medicoUseCase) {
		return new AgendamentoUseCase(agendamentoGateway,medicoUseCase);
	}
	@Bean
	AuthorizationUseCase createAuthorizationUseCase() {
		return new AuthorizationUseCase();
	}

}
