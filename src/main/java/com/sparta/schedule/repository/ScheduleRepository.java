package com.sparta.schedule.repository;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

public class ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Schedule save(Schedule schedule) {
        //DB 저장
        KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키를 반환받기 위한 객체

        String sql = "INSERT INTO schedule (toDoTitle, toDoList, director, password, date) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update( con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setString(1, schedule.getToDoTitle());
                    preparedStatement.setString(2, schedule.getToDoList());
                    preparedStatement.setString(3, schedule.getDirector());
                    preparedStatement.setString(4, schedule.getPassword());
                    preparedStatement.setInt(5, schedule.getDate());
                    return preparedStatement;
                },
                keyHolder);
        // DB Insert 후 받아온 기본키 확인
        Long id = keyHolder.getKey().longValue();
        schedule.setId(id);
        return schedule;
    }

    public void update(Long id, ScheduleRequestDto requestDto) {
        String sql = "UPDATE schedule SET toDoTitle = ?, toDoList = ? , director = ?, password = ?, date = ? WHERE id = ?";
        jdbcTemplate.update(sql, requestDto.getToDoTitle(), requestDto.getToDoList(), requestDto.getDirector(), requestDto.getPassword(), requestDto.getDate(), id);
    }


    public void delete(Long id) {
        String sql = "DELETE FROM schedule WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<ScheduleResponseDto> findAll() {
        // DB 조회
        String sql = "SELECT * FROM schedule";

        return jdbcTemplate.query(sql, new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                // SQL 의 결과로 받아온 Schedule 데이터들을 ScheduleResponseDto 타입으로 변환해줄 메서드
                Long id = rs.getLong("id");
                String toDoTitle = rs.getString("username");
                String toDoList = rs.getString("contents");
                String director = rs.getString("director");
                String password = rs.getString("password");
                Integer date = rs.getInt("date");
                return new ScheduleResponseDto(id, toDoTitle,toDoList,director,password,date);
            }
        });
    }

    public Schedule findById(Long id) {
        // DB 조회
        String sql = "SELECT * FROM schedule WHERE id = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if(resultSet.next()) {
                Schedule schedule = new Schedule();
                schedule.setToDoTitle(resultSet.getString("username"));
                schedule.setToDoList(resultSet.getString("contents"));
                schedule.setDirector(resultSet.getString("director"));
                schedule.setPassword(resultSet.getString("password"));
                schedule.setDate(resultSet.getInt("date"));
                return schedule;
            } else {
                return null;
            }
        }, id);
    }
}
