package com.sparta.schedule.service;

import com.sparta.schedule.dto.CommentRequestDto;
import com.sparta.schedule.dto.CommentResponseDto;
import com.sparta.schedule.entity.Comment;
import com.sparta.schedule.repository.CommentRepository;
import com.sparta.schedule.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
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
        // Comment가 null인지 확인
        commentRequestDto.emptyComment(commentRequestDto);
        // RequestDto -> Entity
        Comment comment = new Comment(commentRequestDto);
        // Schedule ID 찾기
        scheduleRepository.findById(commentRequestDto.getScheduleId());
        // DB 저장
        Comment saveComment = commentRepository.save(comment);
        CommentResponseDto commentResponseDto = new CommentResponseDto(saveComment);

        return commentResponseDto;
    }

    @Transactional
    public CommentResponseDto updateComments(Long id, CommentRequestDto commentRequestDto) {
        // ScheduleId 혹은 CommentId를 받았는지 확인
        commentRequestDto.notFoundScheduleId(commentRequestDto);
        commentRequestDto.notFoundCommentId(commentRequestDto);
        //해당 댓글이 DB에 존재하는지 확인
        Comment comment = findComment(id);
        // 댓글의 사용자가 현재 사용자와 일치하는지 확인
        comment.verifyUserId(id);
        comment.update(commentRequestDto);
        return new CommentResponseDto(comment);
    }

    public Comment findComment(Long id) {
        return commentRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Comment not found"));
    }
}
