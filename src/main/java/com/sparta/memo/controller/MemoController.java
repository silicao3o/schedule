package com.sparta.memo.controller;

import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MeomoResponseDto;
import com.sparta.memo.entity.Memo;
import org.springframework.web.bind.annotation.*;


import java.util.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MemoController {

    private final Map<Long, Memo> memoList = new HashMap<>();

    @PostMapping("/memos")
    public MeomoResponseDto createMemo(@RequestBody MemoRequestDto requstDto){
        //RequestDto -> Entity
        Memo memo = new Memo(requstDto);

        //Memo Max ID Check
        Long maxId = memoList.size() > 0 ? Collections.max(memoList.keySet()) + 1 : 1;
        memo.setId(maxId);

        // DB 저장
        memoList.put(memo.getId(), memo);

        // Entity -> ResponseDto
        MeomoResponseDto meomoResponseDto = new MeomoResponseDto(memo);

        return meomoResponseDto;
    }

    @GetMapping("/memos")
    public List<MeomoResponseDto> getMemos(){
        // Map To List
        List<MeomoResponseDto> responseDtoList = memoList.values().stream()
                .map(MeomoResponseDto::new).toList();

        return responseDtoList;
    }

    @PutMapping("/memos/{id}")
    public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto){
        // 해당 메모가 DB에 존재하는지 확인
        if(memoList.containsKey(id)){
            //해당 메모 가져오기
            Memo memo = memoList.get(id);

            // memo 수정
            memo.update(requestDto);
            return memo.getId();
        } else {
            throw new IllegalArgumentException("선택한 메모가 존재하지 않습니다.");
        }
    }

    @DeleteMapping("/memos/{id}")
    public Long deleteMemo(@PathVariable Long id){
        //해당 메모가 DB에 존재하는지 확인
        if(memoList.containsKey(id)){
            memoList.remove(id);
            return id;
        }
        else {
            throw new IllegalArgumentException("선택한 메모가 존재하지 않습니다.");
        }
    }


}
