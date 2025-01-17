package br.com.augustodev.taskmanager.dto.Task;

import java.util.Date;

import br.com.augustodev.taskmanager.data.Task.TaskNewData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskNewDataDto {
    private String name;
    private String description;
    private String status;
    private Integer priority;
    private Date startDate;
    private Date endDate;

    public TaskNewDataDto(TaskNewData data) {
        this.name = data.name();
        this.description = data.description();
        this.status = data.status();
        this.priority = data.priority();
        this.startDate = data.startDate();
        this.endDate = data.endDate();
    }
}
