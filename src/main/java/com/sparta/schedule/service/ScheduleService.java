package com.sparta.schedule.service;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        // RequestDto -> Entity
        Schedule schedule = new Schedule(requestDto);
        // DB 저장
        Schedule saveSchedule = scheduleRepository.save(schedule);
        // Entity -> ResponseDto
        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(saveSchedule);
        return scheduleResponseDto;
    }

    public List<ScheduleResponseDto> getSchedules() {
        /*
         * select * from schedule
         */

        /*
         * select * from schedule order by date desc;
         */
        return scheduleRepository.findAllOrderByDateDesc().stream().map(ScheduleResponseDto::new).toList();
    }

    /*
     * 1. 입력값에 대한 데이터 가져오기.
     * 2. 가져온 데이터의 암호 확인.
     * 3. 확인되면, DB 업데이트 -> schedule.update()
     * 4. 업데이트 된 데이터를 이용해서 ScheduleResponseDto를 생성하고 리턴.
     */

    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto requestDto) {
        // 해당 스케쥴이 DB에 존재하는지 확인
        Schedule schedule = findSchedule(id);
        // 비밀번호 비교
        schedule.verifyPassword(requestDto);
        // 수정된 스케쥴을 DB에 업데이트
        schedule.update(requestDto);
        // 수정된 스케쥴을 반환
        return new ScheduleResponseDto(schedule);
    }

    public Long deleteSchedule(Long id, ScheduleRequestDto requestDto) {
        // 해당 스케쥴이 DB에 존재하는지 확인
        Schedule schedule = findSchedule(id);
        // 비밀번호 비교
        schedule.verifyPassword(requestDto);
        //schedule 삭제
        scheduleRepository.delete(schedule);
        return id;
    }

    private Schedule findSchedule(Long id) {
        return scheduleRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Schedule not found"));
    };
}
