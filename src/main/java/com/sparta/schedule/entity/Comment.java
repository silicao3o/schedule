package com.sparta.schedule.entity;

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
    @Column(name = "dateId", nullable = false)
    private Long dateId;
    @Column(name = "timestamp", nullable = false)
    private Timestamp timestamp;

//    public Comment(CommentResponseDto commentResponseDto) {
//        this.coment = commentResponseDto.getComent();
//        this.userId = commentResponseDto.getUserId();
//        this.dateId = commentResponseDto.getDateId();
//        this.timestamp = commentResponseDto.getTimestamp();
//    }
//
//    public void update(CommentResponseDto commentResponseDto) {
//        this.coment = commentResponseDto.getComent();
//        this.userId = commentResponseDto.getUserId();
//        this.dateId = commentResponseDto.getDateId();
//        this.timestamp = commentResponseDto.getTimestamp();
//    }
}
