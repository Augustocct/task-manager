package br.com.augustodev.taskmanager.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.augustodev.taskmanager.dto.TaskDto;

@Controller
@RequestMapping("/api/v1")
public class TaskController {

    @PostMapping("/save")
    public ResponseEntity<TaskDto> save(@RequestBody TaskDto taskDto) {
        return ResponseEntity.ok(taskDto);
    }
}
