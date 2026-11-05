package br.edu.ifpr.todo.repository;

import br.edu.ifpr.todo.model.Todo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByFinishedAtIsNotNull();

    List<Todo> findByFinishedAtIsNull();
}
