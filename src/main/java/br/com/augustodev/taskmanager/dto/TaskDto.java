package br.com.augustodev.taskmanager.dto;

import java.sql.Date;

import br.com.augustodev.taskmanager.entities.Task;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class TaskDto {

    private Integer id;

    private String name;

    private String description;

    private String status;

    private Integer priority;

    private Date startDate;

    private Date endDate;

    private Date createDate;

    public TaskDto(Task task) {
        this.id = task.getId();
        this.name = task.getName();
        this.description = task.getDescription();
        this.status = task.getStatus();
        this.priority = task.getPriority();
        this.startDate = task.getStartDate();
        this.endDate = task.getEndDate();
        this.createDate = task.getCreateDate();
    }

}
