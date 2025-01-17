package br.com.augustodev.taskmanager.dto.Task;

import java.util.Date;

import br.com.augustodev.taskmanager.data.Task.TaskEditData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskEditDataDto {

    private Long id;

    private String name;

    private String description;

    private String status;

    private Integer priority;

    private Date startDate;

    private Date endDate;

    public TaskEditDataDto(TaskEditData task) {
        this.id = task.id();
        this.name = task.name();
        this.description = task.description();
        this.status = task.status();
        this.priority = task.priority();
        this.startDate = task.startDate();
        this.endDate = task.endDate();
    }

}
