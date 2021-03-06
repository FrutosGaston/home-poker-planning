package org.unq.pokerplanning.adapter.controller.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Value;
import lombok.val;
import org.unq.pokerplanning.domain.Task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Value
@Builder
public class TaskRest {
    Integer id;
    Integer roomId;
    String title;
    EstimationRest estimation;
    List<EstimationRest> estimations;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    LocalDateTime createdAt;

    public static TaskRest from(Task task) {
        val estimation = Optional.ofNullable(task.getEstimation()).map(EstimationRest::from).orElse(null);
        return TaskRest.builder()
                .id(task.getId())
                .roomId(task.getRoomId())
                .title(task.getTitle())
                .estimation(estimation)
                .createdAt(task.getCreatedAt())
                .estimations(task.getEstimations().stream()
                        .map(EstimationRest::from)
                        .collect(Collectors.toList()))
                .build();
    }

    public Task toDomain() {
        val estimation = Optional.ofNullable(this.estimation).map(EstimationRest::toDomain).orElse(null);
        return Task.builder()
                .roomId(this.roomId)
                .title(this.title)
                .estimation(estimation)
                .build();
    }

}
