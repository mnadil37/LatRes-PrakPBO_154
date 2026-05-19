package com.pbo.latres.repository;

import com.pbo.latres.database.DatabaseConnection;
import com.pbo.latres.dto.InsertTodoDTO;
import com.pbo.latres.model.TodoRepository;
import com.pbo.latres.model.TodoTask;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlTodoRepository
    implements TodoRepository {

    @Override
    public List<TodoTask> getAll() {

        List<TodoTask> tasks =
            new ArrayList<>();

        String query =
            "SELECT * FROM todos";

        try (
            Connection conn =
                DatabaseConnection.getConnection();

            Statement stmt =
                conn.createStatement();

            ResultSet rs =
                stmt.executeQuery(query)
        ) {

            while (rs.next()) {

                TodoTask task = new TodoTask(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("status")
                );

                tasks.add(task);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tasks;
    }
    
    @Override
    public TodoTask getById(int id) {

        String query =
            "SELECT * FROM todos WHERE id = ?";

        try (
            Connection conn =
                DatabaseConnection.getConnection();

            PreparedStatement stmt =
                conn.prepareStatement(query)
        ) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                return new TodoTask(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("status")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    @Override
    public Boolean update(TodoTask todoTask) {

        String query =
            "UPDATE todos SET title=?, status=? WHERE id=?";

        try (
            Connection conn =
                DatabaseConnection.getConnection();

            PreparedStatement stmt =
                conn.prepareStatement(query)
        ) {

            stmt.setString(1, todoTask.getTitle());
            stmt.setString(2, todoTask.getStatus());
            stmt.setInt(3, todoTask.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Boolean deleteById(int id) {

        String query =
            "DELETE FROM todos WHERE id=?";

        try (
            Connection conn =
                DatabaseConnection.getConnection();

            PreparedStatement stmt =
                conn.prepareStatement(query)
        ) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Boolean insert(InsertTodoDTO insertTodoDTO) {

        String query =
            "INSERT INTO todos(title, status) VALUES (?, ?)";

        try (
            Connection conn =
                DatabaseConnection.getConnection();

            PreparedStatement stmt =
                conn.prepareStatement(query)
        ) {

            stmt.setString(
                1,
                insertTodoDTO.getTitle()
            );

            stmt.setString(
                2,
                insertTodoDTO.getStatus()
            );

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }
}