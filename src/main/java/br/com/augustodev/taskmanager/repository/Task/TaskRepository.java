package br.com.augustodev.taskmanager.repository.Task;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.augustodev.taskmanager.entities.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    Optional<Task> findById(Integer id);

    Task findOneById(Integer id);

}
