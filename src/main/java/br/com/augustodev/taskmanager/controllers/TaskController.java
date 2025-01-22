package br.com.augustodev.taskmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.augustodev.taskmanager.data.Task.TaskEditData;
import br.com.augustodev.taskmanager.data.Task.TaskFilterData;
import br.com.augustodev.taskmanager.data.Task.TaskNewData;
import br.com.augustodev.taskmanager.dto.Task.TaskDto;
import br.com.augustodev.taskmanager.dto.Task.TaskEditDataDto;
import br.com.augustodev.taskmanager.dto.Task.TaskNewDataDto;
import br.com.augustodev.taskmanager.responses.Response;
import br.com.augustodev.taskmanager.service.TaskService;

@RestController
@RequestMapping("/api/v1/task")
@CrossOrigin(origins = "http://localhost:5173")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/new")
    public ResponseEntity<Response<TaskDto>> save(@RequestBody TaskNewData data) {

        Response<TaskDto> response = new Response<TaskDto>();
        TaskDto task = taskService.create(new TaskNewDataDto(data));
        response.setData(task);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Response<TaskDto>> edit(@RequestBody TaskEditData data) {

        Response<TaskDto> response = new Response<TaskDto>();
        TaskDto task = taskService.edit(new TaskEditDataDto(data));
        response.setData(task);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/list")
    public ResponseEntity<Response<Page<TaskDto>>> list(@RequestBody TaskFilterData data, Pageable pageable) {

        Response<Page<TaskDto>> response = new Response<Page<TaskDto>>();
        Page<TaskDto> tasks = taskService.findByFilter(data, pageable);
        response.setData(tasks);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<TaskDto>> getById(@PathVariable Integer id) {

        Response<TaskDto> response = new Response<TaskDto>();
        TaskDto task = taskService.findById(id);
        response.setData(task);

        return ResponseEntity.ok(response);
    }
}
