package com.sparta.schedule.dto;

import lombok.Getter;

@Getter
public class ScheduleRequestDto {
    private String toDoTitle;
    private String toDoList;
    private String director;
    private String password;
    private int date;

    public ScheduleRequestDto(String toDoTitle, String toDoList, String director, String password, int date) {
        this.toDoTitle = toDoTitle;
        this.toDoList = toDoList;
        this.director = director;
        this.password = password;
        this.date = date;
    }
}