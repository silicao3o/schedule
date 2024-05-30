package com.sparta.schedule.dto;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class CommentRequestDto {
    private Long id;
    private String comment;
    private String userId;
    private Long scheduleId;
    private Timestamp timestamp;

    public CommentRequestDto(String comment, String userId, Long scheduleId, Timestamp timestamp) {
        this.comment = comment;
        this.userId = userId;
        this.scheduleId = scheduleId;
        this.timestamp = timestamp;
    }

    public void notFoundScheduleId(CommentRequestDto commentRequestDto) {
        if (!this.scheduleId.equals(commentRequestDto.getScheduleId())) {
            throw new SecurityException("Not found ScheduleId");
        }
    }

    public void emptyComment(CommentRequestDto commentRequestDto) {
        if(!this.comment.equals(commentRequestDto.getComment())) {
            throw new SecurityException("No Comment");
        }
    }

    public void notFoundCommentId(CommentRequestDto commentRequestDto) {
        if (!this.comment.equals(commentRequestDto.getId())) {
            throw new SecurityException("Not found Comment");
        }
    }
}
