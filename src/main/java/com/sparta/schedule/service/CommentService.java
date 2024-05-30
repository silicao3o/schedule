package com.sparta.schedule.service;

import com.sparta.schedule.dto.CommentRequestDto;
import com.sparta.schedule.dto.CommentResponseDto;
import com.sparta.schedule.entity.Comment;
import com.sparta.schedule.repository.CommentRepository;
import com.sparta.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;


@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    public CommentService(CommentRepository commentRepository, ScheduleRepository scheduleRepository) {
        this.commentRepository = commentRepository;
        this.scheduleRepository = scheduleRepository;
    }

    public CommentResponseDto createComment(CommentRequestDto commentRequestDto) {
        // ScheduleId를 입력받았는지 확인
        commentRequestDto.notFoundScheduleId(commentRequestDto);
        commentRequestDto.emptyComment(commentRequestDto);
        // RequestDto -> Entity
        Comment comment = new Comment(commentRequestDto);
        scheduleRepository.findById(commentRequestDto.getScheduleId());
        // DB 저장
        Comment saveComment = commentRepository.save(comment);
        CommentResponseDto commentResponseDto = new CommentResponseDto(saveComment);

        return commentResponseDto;
    }
    // 입력받을 때 스케쥴 id를 넣지 않았을 때
    // 스케쥴 id 찾기
    // 댓글 작성
    // 댓글을 작성하지 않았을 시 예외처리
    // 댓글 저장

//    public Comment findComment(Long id) {
//        return comentRepository.findById(id).orElseThrow(() ->
//                new IllegalArgumentException("Comment not found"));
//    }
}
