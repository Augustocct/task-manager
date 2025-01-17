package br.com.augustodev.taskmanager.repository.Task;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.augustodev.taskmanager.entities.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    Task findById(Long id);

}
