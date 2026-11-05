package br.edu.ifpr.todo.dto;

import java.time.LocalDateTime;

public record TodoConcluidaResponseDTO(Long id, String title, LocalDateTime createdAt,
                                       LocalDateTime deadLine, LocalDateTime finishedAt) {
}
