package com.JDBC.demo.Repo;

import com.JDBC.demo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepo {
    private JdbcTemplate jdbc;
    @Autowired
    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    public JdbcTemplate getJdbc() {
        return jdbc;
    }

    public int saveStudent(Student st) {
        String sql = "INSERT INTO student (id,name,tech) VALUES (?, ?,?)";
        return jdbc.update(sql,st.getId() ,st.getName(), st.getTech());
    }

    public List<Student> getAllStudents() {
        String sql = "SELECT * FROM student";
        RowMapper<Student> map=new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
                Student st=new Student();
                st.setId(rs.getInt(1));
                st.setName(rs.getString(2));
                st.setTech(rs.getString(3));
                return st;
            }
        };
        List<Student> st=jdbc.query(sql,map);
        return st;

    }



}
