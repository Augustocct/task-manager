package br.com.augustodev.taskmanager.data.Task;

import java.util.Date;

import jakarta.validation.constraints.Max;

public record TaskEditData(
        Long id,
        String name,
        String description,
        String status,
        @Max(1) Integer priority,
        Date startDate,
        Date endDate) {

}
