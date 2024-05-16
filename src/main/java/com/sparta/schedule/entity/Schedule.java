package com.sparta.schedule.entity;

import com.sparta.schedule.dto.ScheduleRequestDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Schedule {
    private Long id;
    private String toDoTitle;
    private String toDoList;
    private String director;
    private String password;
    private int date;


    public Schedule(ScheduleRequestDto requestDto) {
        this.toDoTitle = requestDto.getToDoTitle();
        this.toDoList = requestDto.getToDoList();
        this.director = requestDto.getDirector();
        this.password = requestDto.getPassword();
        this.date = requestDto.getDate();
    }

    public void update(ScheduleRequestDto requestDto) {
        this.toDoTitle = requestDto.getToDoTitle();
        this.toDoList = requestDto.getToDoList();
        this.director = requestDto.getDirector();
        this.password = requestDto.getPassword();
        this.date = requestDto.getDate();
    }
}