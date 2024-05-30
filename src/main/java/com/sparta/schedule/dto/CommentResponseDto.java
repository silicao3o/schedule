package com.sparta.schedule.dto;

import com.sparta.schedule.entity.Comment;
import lombok.Getter;

import java.sql.Timestamp;
@Getter
public class CommentResponseDto {
    private Long id;
    private String comment;
    private String userId;
    private Long scheduleId;
    private Timestamp timestamp;

    public CommentResponseDto(Long id, String comment, String userId, Long dateId, Timestamp timestamp) {
        this.id = id;
        this.comment = comment;
        this.userId = userId;
        this.scheduleId = scheduleId;
        this.timestamp = timestamp;
    }
    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.userId = comment.getUserId();
        this.scheduleId = comment.getScheduleId();
        this.timestamp = comment.getTimestamp();
    }
}
