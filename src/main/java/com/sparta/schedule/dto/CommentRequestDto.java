package com.sparta.schedule.dto;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class CommentRequestDto {
    private String comment;
    private String userId;
    private Long scheduleId;

    public CommentRequestDto(String comment, String userId, Long scheduleId) {
        this.comment = comment;
        this.userId = userId;
        this.scheduleId = scheduleId;
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
}
