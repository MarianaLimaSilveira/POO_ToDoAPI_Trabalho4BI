package br.edu.ifpr.todo.controller;

import br.edu.ifpr.todo.dto.TodoConclusaoRequestDTO;
import br.edu.ifpr.todo.dto.TodoConcluidaResponseDTO;
import br.edu.ifpr.todo.dto.TodoRequestDTO;
import br.edu.ifpr.todo.dto.TodoResponseDTO;
import br.edu.ifpr.todo.service.TodoService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TodoResponseDTO> criar(@RequestBody TodoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/conclusao")
    public ResponseEntity<TodoConcluidaResponseDTO> concluir(@PathVariable Long id,
                                                             @RequestBody TodoConclusaoRequestDTO dto) {
        return ResponseEntity.ok(service.concluir(id, dto));
    }

    @GetMapping("/concluidas")
    public ResponseEntity<List<TodoConcluidaResponseDTO>> concluidas() {
        return ResponseEntity.ok(service.listarConcluidas());
    }

    @GetMapping("/pendentes")
    public ResponseEntity<List<TodoConcluidaResponseDTO>> pendentes() {
        return ResponseEntity.ok(service.listarPendentes());
    }
}
