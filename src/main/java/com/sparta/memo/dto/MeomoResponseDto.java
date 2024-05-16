package com.sparta.memo.dto;

import com.sparta.memo.entity.Memo;
import lombok.Getter;

@Getter
public class MeomoResponseDto {
    private Long id;
    private String username;
    private String contents;

    public MeomoResponseDto(Memo memo) {
        this.id = memo.getId();
        this.username = memo.getUsername();
        this.contents = memo.getContents();
    }
}