# hackathon-ms-appointment-service
Hackathon - MVP Agendamentos de Consultas Médicas

### Grupo 2 - Membros:
* RM352611 - José Ronaldo Leles Júnior - email: juniorleles80@gmail.co
* RM352829 - Saulo Carvalho Gomes - email: sgomesnet@gmail.co
* RM353122 - Fabrício Guimarães de Oliveira - email: fguimaraesoliveira@gmail.co


## Documentação

# Repositórios relacionados
* [Infraestrutura Terraform](https://github.com/FabricioGuimaraesOliveira/hackathon-terraform-fiap)
* [Serviço de Doctor Microservice - Médico](https://github.com/FabricioGuimaraesOliveira/hackathon-ms-doctor-service)
* [Serviço de Patient Microservice - Paciente](https://github.com/FabricioGuimaraesOliveira/hackathon-ms-patient-service)
* [Serviço de Appointment Microservice - Agendamento](https://github.com/FabricioGuimaraesOliveira/hackathon-ms-appointment-service )

## Dependências
* [IntelliJ IDEA (Opcional)](https://www.jetbrains.com/idea/download/#section=windows)
* [Java JDK 21](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
* [Spring Boot 3.1.0](https://spring.io/projects/spring-boot)
* [PostgreSql](https://www.postgresql.org/download/)
* [Maven](https://maven.apache.org/)
* [Docker](https://www.docker.com/)
* [AWS](https://aws.amazon.com/pt/)


## Executando aplicação completa via docker

Execute o comando abaixo para iniciar os containers com a base de dados e executar a aplicação localmente.

```bash
docker-compose up -d
```

##Entregáveis
* [Cadastro do Usuário Médico] (http://localhost:8080/doctors)
* [Autenticação do Usuário Médico] (http://localhost:8080/doctors/auth)
* [Cadastro e Edição de horários disponíveis Médico] (http://localhost:8080/doctors-calendars/)
* [Cadastro do Usuário Paciente] (http://localhost:8080/doctors)
* [Autenticação do Usuário Paciente] (http://localhost:8080/pacientes/auth)
* [Busca por médicos pelo paciente] (http://localhost:8080/doctor-calendars/doctor/available-times)
* [Agendamento de Consultas Paciente] (http://localhost:8080/doctors-calendars/doctor/schedule)
* [Validar se a consulta está disponível] (http://localhost:8080/doctors-calendars/doctor/avaliable-times)
* [Confirmar o agendamento médico pelo paciente] (http://localhost:8080/agendamentos/atualizar-status/{idAgendamento}) 

## Endpoints
Para visualizar os endpoints disponíveis de cada aplicação basta acessar o swagger em [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

