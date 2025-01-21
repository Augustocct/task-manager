package br.com.augustodev.taskmanager.repository.Task;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import br.com.augustodev.taskmanager.data.Task.TaskFilterData;
import br.com.augustodev.taskmanager.dto.Task.TaskDto;

@Repository
public class TaskCustomRepository {

    private JdbcTemplate jdbcTemplate;

    public TaskCustomRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @SuppressWarnings("null")
    public Page<TaskDto> findByFilter(TaskFilterData filter, Pageable pag) {

        StringBuilder query = new StringBuilder(
                "SELECT t.ID, t.NAME, t.DESCRIPTION, t.STATUS, t.PRIORITY, t.START_DATE, t.END_DATE, t.CREATE_DATE"
                        + " FROM TASK t WHERE 1 = 1 ");

        if (filter.taskId() != null) {
            query.append(" AND t.ID = ? ");
        }
        if (filter.name() != null && !filter.name().isEmpty()) {
            query.append(" AND t.NAME = ?");
        }
        if (filter.status() != null && !filter.status().isEmpty()) {
            query.append(" AND t.STATUS = ?");
        }
        if (filter.priority() != null && !filter.priority().toString().isEmpty()) {
            query.append(" AND t.PRIORITY = ?");
        }
        if (filter.startDate() != null && !filter.startDate().toString().isEmpty()) {
            query.append(" AND t.START_DATE = ?");
        }
        if (filter.endDate() != null && !filter.endDate().toString().isEmpty()) {
            query.append(" AND t.END_DATE = ?");
        }
        if (pag.getSort().isSorted()) {
            pag.getSort().forEach(order -> {
                query.append(" ORDER BY " + order.getProperty() + " " + order.getDirection());
            });
        } else {
            query.append(" ORDER BY ID ");
        }

        query.append(" LIMIT ?, ? ");

        List<TaskDto> listTask = jdbcTemplate.query(query.toString(), new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                int param1 = 1;

                if (filter.name() != null) {
                    ps.setString(param1, filter.name());
                    param1 += 1;
                }
                if (filter.status() != null) {
                    ps.setString(param1, filter.status());
                    param1 += 1;
                }
                if (filter.priority() != null) {
                    ps.setInt(param1, filter.priority());
                    param1 += 1;
                }
                if (filter.startDate() != null) {
                    ps.setDate(param1, new java.sql.Date(filter.startDate().getTime()));
                    param1 += 1;
                }
                if (filter.endDate() != null) {
                    ps.setDate(param1, new java.sql.Date(filter.endDate().getTime()));
                    param1 += 1;
                }

                ps.setLong(param1, pag.getOffset());
                param1 += 1;
                ps.setInt(param1, pag.getPageSize());
            }
        }, new TaskDtoResultDataRowMapper());

        long totalCount = getTotalCountByFilter(filter);
        Pageable pageable = Pageable.ofSize(pag.getPageSize()).withPage(pag.getPageNumber());
        Page<TaskDto> page = new PageImpl<>(listTask, pageable, totalCount);
        return page;
    }

    @SuppressWarnings("null")
    private long getTotalCountByFilter(TaskFilterData filter) {

        StringBuilder query = new StringBuilder(
                "SELECT count(*) cnt "
                        + " FROM TASK t WHERE 1 = 1 ");

        if (filter.name() != null && !filter.name().isEmpty()) {
            query.append(" AND t.NAME = ?");
        }
        if (filter.status() != null && !filter.status().isEmpty()) {
            query.append(" AND t.STATUS = ?");
        }
        if (filter.priority() != null && !filter.priority().toString().isEmpty()) {
            query.append(" AND t.PRIORITY = ?");
        }
        if (filter.startDate() != null && !filter.startDate().toString().isEmpty()) {
            query.append(" AND t.START_DATE = ?");
        }
        if (filter.endDate() != null && !filter.endDate().toString().isEmpty()) {
            query.append(" AND t.END_DATE = ?");
        }

        List<Long> count = jdbcTemplate.query(query.toString(), new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                int param1 = 1;

                if (filter.name() != null && !filter.name().isEmpty()) {
                    ps.setString(param1, filter.name());
                    param1 += 1;
                }
                if (filter.status() != null && !filter.status().isEmpty()) {
                    ps.setString(param1, filter.status());
                    param1 += 1;
                }
                if (filter.priority() != null && !filter.priority().toString().isEmpty()) {
                    ps.setInt(param1, filter.priority());
                    param1 += 1;
                }
                if (filter.startDate() != null && !filter.startDate().toString().isEmpty()) {
                    ps.setDate(param1, new java.sql.Date(filter.startDate().getTime()));
                    param1 += 1;
                }
                if (filter.endDate() != null && !filter.endDate().toString().isEmpty()) {
                    ps.setDate(param1, new java.sql.Date(filter.endDate().getTime()));
                    param1 += 1;
                }
            }
        }, new CountRowMapper());

        return count.get(0);
    }

    @SuppressWarnings("null")
    private class TaskDtoResultDataRowMapper implements RowMapper<TaskDto> {
        @Override
        public TaskDto mapRow(ResultSet rs, int rowNum) throws SQLException {

            TaskDto objeto = new TaskDto(
                    Long.valueOf(rs.getInt("ID")),
                    rs.getString("NAME"),
                    rs.getString("DESCRIPTION"),
                    rs.getString("STATUS"),
                    Integer.valueOf(rs.getInt("PRIORITY")),
                    rs.getDate("START_DATE"),
                    rs.getDate("END_DATE"),
                    rs.getTimestamp("CREATE_DATE"));

            return objeto;
        }
    }

    @SuppressWarnings("null")
    private class CountRowMapper implements RowMapper<Long> {
        @Override
        public Long mapRow(ResultSet rs, int rowNum) throws SQLException {

            Long objeto = Long.valueOf(rs.getLong("CNT"));

            return objeto;
        }
    }
}
