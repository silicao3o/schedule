package com.sparta.schedule.dto;

import lombok.Getter;

@Getter
public class ScheduleRequestDto {
    private Long id;
    private String toDoTitle;
    private String toDoList;
    private String director;
    private String password;
    private int date;

    public ScheduleRequestDto(Long id, String toDoTitle, String toDoList, String director, String password, int date) {
        this.id = id;
        this.toDoTitle = toDoTitle;
        this.toDoList = toDoList;
        this.director = director;
        this.password = password;
        this.date = date;
    }
}