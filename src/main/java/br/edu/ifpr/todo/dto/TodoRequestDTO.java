package br.edu.ifpr.todo.dto;

import java.time.LocalDateTime;

public record TodoRequestDTO(String title, LocalDateTime deadLine) {
}
