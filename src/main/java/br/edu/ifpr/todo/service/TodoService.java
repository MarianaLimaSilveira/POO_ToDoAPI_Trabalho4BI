package br.edu.ifpr.todo.service;

import br.edu.ifpr.todo.dto.TodoConclusaoRequestDTO;
import br.edu.ifpr.todo.dto.TodoConcluidaResponseDTO;
import br.edu.ifpr.todo.dto.TodoRequestDTO;
import br.edu.ifpr.todo.dto.TodoResponseDTO;
import br.edu.ifpr.todo.model.Todo;
import br.edu.ifpr.todo.repository.TodoRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
    private final TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public TodoResponseDTO criar(TodoRequestDTO dto) {
        Todo todo = new Todo();
        todo.setTitle(dto.title());
        todo.setDeadLine(dto.deadLine());
        todo.setCreatedAt(LocalDateTime.now());
        todo.setFinishedAt(null);

        Todo salvo = repository.save(todo);
        return new TodoResponseDTO(salvo.getId(), salvo.getTitle(),
                salvo.getCreatedAt(), salvo.getDeadLine());
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

    public TodoConcluidaResponseDTO concluir(Long id, TodoConclusaoRequestDTO dto) {
        Todo todo = repository.findById(id).orElseThrow();
        todo.setFinishedAt(dto.finishedAt());
        Todo salvo = repository.save(todo);
        return paraDTO(salvo);
    }

    public List<TodoConcluidaResponseDTO> listarConcluidas() {
        return repository.findByFinishedAtIsNotNull().stream().map(this::paraDTO).toList();
    }

    public List<TodoConcluidaResponseDTO> listarPendentes() {
        return repository.findByFinishedAtIsNull().stream().map(this::paraDTO).toList();
    }

    private TodoConcluidaResponseDTO paraDTO(Todo t) {
        return new TodoConcluidaResponseDTO(t.getId(), t.getTitle(),
                t.getCreatedAt(), t.getDeadLine(), t.getFinishedAt());
    }
}
