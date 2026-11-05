package br.edu.ifpr.todo.dto;

import java.time.LocalDateTime;

public record TodoConclusaoRequestDTO(LocalDateTime finishedAt) {
}
