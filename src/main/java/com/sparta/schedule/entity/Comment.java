package com.sparta.schedule.entity;

import com.sparta.schedule.dto.CommentRequestDto;
import com.sparta.schedule.dto.CommentResponseDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Entity
@Getter
@Table(name = "comments")
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "comment", nullable = false, length = 500)
    private String comment;
    @Column(name = "userId", nullable = false)
    private String userId;
    @Column(name = "scheduleId", nullable = false)
    private Long scheduleId;
    @Column(name = "timestamp", nullable = false)
    private Timestamp timestamp;

    public Comment(CommentRequestDto commentRequestDto) {
        this.comment = commentRequestDto.getComment();
        this.userId = commentRequestDto.getUserId();
        this.scheduleId = commentRequestDto.getScheduleId();
        this.timestamp =  new Timestamp(System.currentTimeMillis());
    }

    public void update(CommentResponseDto commentResponseDto) {
        this.comment = commentResponseDto.getComment();
        this.userId = commentResponseDto.getUserId();
        this.scheduleId = commentResponseDto.getScheduleId();
        this.timestamp = commentResponseDto.getTimestamp();
    }
}
