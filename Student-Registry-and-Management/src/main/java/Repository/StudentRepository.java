package Repository;

import com.example.Student.Registry.and.Management.Student;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {

    private final Connection conn;

    public StudentRepository(Connection databaseConnection) {
        this.conn = databaseConnection;
    }

    public void addStudent(Student student) throws SQLException {
        String sql = "INSERT INTO Student (full_name, age, email,id) VALUES (?, ?, ?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, student.getFirstName());
            ps.setInt(2, student.getAge());
            ps.setString(3, student.getEmail());
            ps.executeUpdate();
        }
    }

    public List<Student> getStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM Student";
        try (Statement s = conn.createStatement();
             ResultSet rs = s.executeQuery(sql)) {
            while (rs.next()) {
                students.add(new Student(
                        rs.getString("full_name"),
                        rs.getInt("age"),
                        rs.getString("email"),
                        rs.getInt("id")));
            }
        }
        return students;
    }


    public Student getStudentByName(String name) throws SQLException {
        String sql = "SELECT * FROM Student WHERE full_name = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                            rs.getString("full_name"),
                            rs.getInt("age"),
                            rs.getString("email"),
                            rs.getInt("id"));
                }
            }
        }
        return null;
    }

    public boolean removeStudent(int id) throws SQLException {
        String sql = "DELETE FROM Student WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}