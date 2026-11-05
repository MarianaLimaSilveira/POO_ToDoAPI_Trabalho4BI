# API de Tarefas (Spring Boot)

Projeto Bimestral – POO – IFPR Cascavel.

## Como rodar
Java 17+ e Maven: `mvn spring-boot:run` (ou rode `TodoApplication` na IDE). A API sobe em `http://localhost:8080`.

## Arquitetura
Requisição -> **Controller** (HTTP/status) -> **Service** (regras) -> **Repository** (banco) -> H2.
Os dados entram e saem da API por **DTOs**; a entidade `Todo` nunca é exposta.

## Solução adotada para os DTOs (Desafio)
| DTO | Uso | Campos |
|---|---|---|
| `TodoRequestDTO` | entrada do POST | title, deadLine |
| `TodoConclusaoRequestDTO` | entrada do PATCH | finishedAt |
| `TodoResponseDTO` | saída do POST (o DTO atual, sem finishedAt) | id, title, createdAt, deadLine |
| `TodoConcluidaResponseDTO` | saída do PATCH e das listagens | id, title, createdAt, deadLine, finishedAt |

- **Receber finishedAt:** DTO de entrada próprio só com esse campo; assim o PATCH altera apenas ele.
- **Conferir finishedAt:** DTO de saída novo, com finishedAt, sem alterar o DTO atual.

## Exemplos de requisições
**1. Inserir** `POST /api/todos` -> 201
```json
{ "title": "Estudar Spring Boot", "deadLine": "2026-10-30T18:00:00" }
```
**2. Excluir** `DELETE /api/todos/1` -> 204 (sem corpo)

**3. Concluir** `PATCH /api/todos/1/conclusao` -> 200
```json
{ "finishedAt": "2026-10-22T10:30:00" }
```
**4. Listar** `GET /api/todos/concluidas` e `GET /api/todos/pendentes` -> 200 (lista, ou `[]`)
