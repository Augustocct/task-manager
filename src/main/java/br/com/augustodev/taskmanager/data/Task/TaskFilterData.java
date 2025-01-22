package br.com.augustodev.taskmanager.data.Task;

import java.util.Date;

public record TaskFilterData(
        Long id,
        String name,
        String status,
        Integer priority,
        Date startDate,
        Date endDate) {

}
