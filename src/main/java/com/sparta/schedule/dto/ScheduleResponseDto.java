package com.sparta.schedule.dto;

import com.sparta.schedule.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private String toDoTitle;
    private String toDoList;
    private String director;
    private int date;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.toDoTitle = schedule.getToDoTitle();
        this.toDoList = schedule.getToDoList();
        this.director = schedule.getDirector();
        this.date = schedule.getDate();
    }
}