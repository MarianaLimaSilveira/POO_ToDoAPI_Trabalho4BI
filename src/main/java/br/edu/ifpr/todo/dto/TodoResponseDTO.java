package br.edu.ifpr.todo.dto;

import java.time.LocalDateTime;

public record TodoResponseDTO(Long id, String title, LocalDateTime createdAt, LocalDateTime deadLine) {
}
