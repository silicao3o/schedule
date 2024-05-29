package com.sparta.schedule.entity;

import com.sparta.schedule.dto.ScheduleRequestDto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "schedule")
@NoArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "to_do_title", nullable = false)
    private String toDoTitle;
    @Column(name = "to_do_list", nullable = false, length = 500)
    private String toDoList;
    @Column(name = "director", nullable = false)
    private String director;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "date", nullable = false)
    private int date;

    @OneToMany
    @JoinColumn(name = "comments_id")
    private List<Comment> commentList = new ArrayList<>();


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

    public void verifyPassword(ScheduleRequestDto requestDto) {
        if (!this.password.equals(requestDto.getPassword())) {
            throw new SecurityException("Password does not match");
        }
    }
}