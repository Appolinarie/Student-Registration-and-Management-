package com.example.Student.Registry.and.Management;

import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

 import java.sql.*;
 import java.util.ArrayList;
 import java.util.List;

@Repository
public class StudentRepository {

    private final Connection conn;

    public StudentRepository(Connection databaseConnection) {
        this.conn = databaseConnection;
    }

    public String addStudent(Student student) throws SQLException {

        String insertSql = "INSERT INTO Student (Name, email, Age, id) VALUES (?, ?, ?,?, ?)";

        PreparedStatement preparedStatement = conn.prepareStatement(insertSql);

        preparedStatement.setString(1, student.getLastName());
        preparedStatement.setString(2, student.getFirstName());
        preparedStatement.setInt(3, student.getAge());
        preparedStatement.setString(3, student.getEmail());;
        preparedStatement.setInt(5,student.getId());

        preparedStatement.executeUpdate();

        preparedStatement.close();

        return "Student added successfully";
    }

    public void removeStudent(Student student) {

    }

    public List<Student> getStudent() throws SQLException {
        List<Student> students = new ArrayList<>();
        String select = "select * from Student";

        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(select);

        while (rs.next()) {
            String name = rs.getString("name");
            int age = rs.getInt("age");
            String email = rs.getString("email");
            int id =rs.getInt("id");


            students.add(new Student(name, age, email, id));

        }
        return students;

    }

    public Student getStudent(String studentNmae) throws SQLException {
        String selectSql = "select * from Student where name=?";

        PreparedStatement ps = conn.prepareStatement(selectSql);
        ps.setString(1, studentNmae);
        ResultSet rs = ps.executeQuery();

        ResultSet resultSet = ps.executeQuery();
        if (resultSet.next()) {
            String name = resultSet.getString("name");
            int age = resultSet.getInt("age");
            String gender = resultSet.getString("gender");
            return new Student(name, age, gender);
        }
        return getStudent(studentNmae);
    }
}



