package session17.bai1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MovieManagement {
    private Connection conn;

    public MovieManagement() {
        conn = ConnectionDB.getConnection();
    }

    public void initDatabase() {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS movies (
                    id SERIAL PRIMARY KEY,
                    title VARCHAR(255),
                    director VARCHAR(255),
                    year INT
                )
            """);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addMovie(String title, String director, int year) {
        String sql = "INSERT INTO movies (title, director, year) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, title);
            ps.setString(2, director);
            ps.setInt(3, year);
            ps.executeUpdate();
            System.out.println("Thêm phim thành công!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listMovies() {
        String sql = "SELECT * FROM movies";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.printf("%d | %s | %s | %d%n",
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("director"),
                        rs.getInt("year"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateMovie(int id, String title, String director, int year) {
        String sql = "UPDATE movies SET title=?, director=?, year=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, title);
            ps.setString(2, director);
            ps.setInt(3, year);
            ps.setInt(4, id);
            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("Cập nhật thành công!");
            else System.out.println("Không tìm thấy phim với ID này.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteMovie(int id) {
        String sql = "DELETE FROM movies WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println(" Xóa thành công!");
            else System.out.println(" Không tìm thấy phim với ID này.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
