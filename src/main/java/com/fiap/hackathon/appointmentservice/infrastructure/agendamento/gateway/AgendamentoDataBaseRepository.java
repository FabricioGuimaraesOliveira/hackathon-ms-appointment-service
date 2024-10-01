package com.fiap.hackathon.appointmentservice.infrastructure.agendamento.gateway;

import com.fiap.hackathon.appointmentservice.domain.entity.agendamento.enums.StatusAgendamento;
import com.fiap.hackathon.appointmentservice.domain.entity.agendamento.gateway.AgendamentoGateway;
import com.fiap.hackathon.appointmentservice.domain.entity.agendamento.model.Agendamento;
import com.fiap.hackathon.appointmentservice.infrastructure.persistence.agendamento.AgendamentoEntity;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class AgendamentoDataBaseRepository implements AgendamentoGateway {

    private final DynamoDbEnhancedClient enhancedClient;
    private final DynamoDbTable<AgendamentoEntity> table;

    public AgendamentoDataBaseRepository(DynamoDbEnhancedClient enhancedClient, String tableName) {
        this.enhancedClient = enhancedClient;
        this.table = enhancedClient.table(tableName, TableSchema.fromBean(AgendamentoEntity.class));
    }
    @Override
    public Agendamento save(AgendamentoEntity agendamentoEntity) {
        table.putItem(agendamentoEntity);
        return mapToDomain(agendamentoEntity);
    }
    @Override
    public List<Agendamento> buscarAgendamentosPorCpf(String cpf) {
        var query = table.scan();
        List<AgendamentoEntity> agendamentosEntity = query.items().stream()
                .filter(agendamento -> agendamento.getCpfPaciente().equals(cpf))
                .collect(Collectors.toList());

        return agendamentosEntity.stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Agendamento> buscarAgendamentosPorMedicoId(UUID medicoId) {
        var query = table.scan();
        List<AgendamentoEntity> agendamentosEntity = query.items().stream()
                .filter(agendamento -> agendamento.getIdMedico().equals(medicoId.toString()))
                .collect(Collectors.toList());

        return agendamentosEntity.stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    public Optional<Agendamento> buscarAgendamentoPorId(UUID agendamentoId) {
        Key key = Key.builder()
                .partitionValue(agendamentoId.toString())
                .build();

        AgendamentoEntity agendamentoEntity = table.getItem(r -> r.key(key));

        if (agendamentoEntity != null) {
            Agendamento agendamento = mapToDomain(agendamentoEntity);
            return Optional.of(agendamento);
        }

        return Optional.empty();
    }

    @Override
    public Boolean atualizarStatusAgendamento(UUID idAgendamento, StatusAgendamento status) {
        // Busca o agendamento pelo idAgendamento
        Key key = Key.builder()
                .partitionValue(String.valueOf(idAgendamento))
                .build();

        AgendamentoEntity agendamentoEntity = table.getItem(r -> r.key(key));

        if (agendamentoEntity != null) {
            // Atualiza o status do agendamento
            agendamentoEntity.setStatus(status);

            // Salva a atualização no DynamoDB
            table.updateItem(agendamentoEntity);  // Chamada para salvar o item atualizado

            return true;
        }

        return false;
    }
    private Agendamento mapToDomain(AgendamentoEntity entity) {
        return new Agendamento(
                entity.getProtocolo(),
                entity.getCpfPaciente(),
                entity.getIdMedico(),
                entity.getHorarioAgendamento(),
                entity.getStatus()
        );
    }


}
