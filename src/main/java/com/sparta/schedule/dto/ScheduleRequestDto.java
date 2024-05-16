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

}