package com.sparta.schedule.dto;

import com.sparta.schedule.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private String toDoTitle;
    private String toDoList;
    private String director;
    private String password;
    private int date;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.toDoTitle = schedule.getToDoTitle();
        this.toDoList = schedule.getToDoList();
        this.director = schedule.getDirector();
        this.password = schedule.getPassword();
        this.date = schedule.getDate();
    }

    public ScheduleResponseDto(Long id, String toDoTitle, String toDoList, String director, String password, int date) {
        this.id = id;
        this.toDoTitle = toDoTitle;
        this.toDoList = toDoList;
        this.director = director;
        this.password = password;
        this.date = date;
    }
}