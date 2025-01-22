package br.com.augustodev.taskmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;

import br.com.augustodev.taskmanager.data.Task.TaskData;
import br.com.augustodev.taskmanager.data.Task.TaskFilterData;
import br.com.augustodev.taskmanager.dto.Task.TaskDto;
import br.com.augustodev.taskmanager.dto.Task.TaskEditDataDto;
import br.com.augustodev.taskmanager.dto.Task.TaskNewDataDto;
import br.com.augustodev.taskmanager.entities.Task;
import br.com.augustodev.taskmanager.exception.CustomHttpInputMessage;
import br.com.augustodev.taskmanager.repository.Task.TaskCustomRepository;
import br.com.augustodev.taskmanager.repository.Task.TaskRepository;
import jakarta.validation.Valid;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    @Autowired
    private TaskCustomRepository customRepository;

    public TaskDto create(@Valid TaskNewDataDto data) {

        Task task = createTaskFromData(data);
        return new TaskDto(task);
    }

    private Task createTaskFromData(@Valid TaskNewDataDto newData) {
        Task task = new Task();

        TaskData data = dateFromNewData(newData);

        task.setName(data.name());
        task.setDescription(data.description());
        task.setStatus(data.status());
        task.setPriority(data.priority());
        task.setStartDate(data.startDate());
        task.setEndDate(data.endDate());

        repository.save(task);
        repository.flush();

        return task;
    }

    private TaskData dateFromNewData(TaskNewDataDto newData) {
        return new TaskData(
                newData.getName(),
                newData.getDescription(),
                newData.getStatus(),
                newData.getPriority(),
                newData.getStartDate(),
                newData.getEndDate());
    }

    public TaskDto edit(@Valid TaskEditDataDto data) {

        Task task = editTaskFromData(data);
        return new TaskDto(task);
    }

    private Task editTaskFromData(@Valid TaskEditDataDto editData) {
        Task task = repository.findById(editData.getId()).orElseThrow(() -> new RuntimeException("Task not found"));

        TaskData data = dateFromEditData(editData);

        task.setName(data.name());
        task.setDescription(data.description());
        task.setStatus(data.status());
        task.setPriority(data.priority());
        task.setStartDate(data.startDate());
        task.setEndDate(data.endDate());

        repository.save(task);
        repository.flush();

        return task;
    }

    private TaskData dateFromEditData(TaskEditDataDto editData) {
        return new TaskData(
                editData.getName(),
                editData.getDescription(),
                editData.getStatus(),
                editData.getPriority(),
                editData.getStartDate(),
                editData.getEndDate());
    }

    public Page<TaskDto> findByFilter(@Valid TaskFilterData filter,
            Pageable pag) {

        Page<TaskDto> task = customRepository.findByFilter(filter, pag);
        if (task == null) {
            throw new HttpMessageNotReadableException("Não foram encontradas tarefas com os filtros utilizados",
                    new CustomHttpInputMessage(null, null));
        }
        return task;
    }

    public TaskDto findById(Integer id) {
        TaskDto task = new TaskDto(findOneById(id));
        return task;
    }

    public Task findOneById(Integer id) {
        Task task = repository.findOneById(id);
        return task;
    }

}
