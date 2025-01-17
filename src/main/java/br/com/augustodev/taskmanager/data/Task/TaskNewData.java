package br.com.augustodev.taskmanager.data.Task;

import java.sql.Date;

import jakarta.validation.constraints.Max;

public record TaskNewData(
        String name,
        String description,
        String status,
        @Max(1) Integer priority,
        Date startDate,
        Date endDate) {

}
