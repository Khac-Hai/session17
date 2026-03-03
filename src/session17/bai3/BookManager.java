package session17.bai3;

import java.sql.*;

public class BookManager {

    public void initDatabase() {
        try (Connection conn = ConnectionDB.getConnection();
             Statement stm = conn.createStatement()) {

            stm.execute("""
                CREATE TABLE IF NOT EXISTS books (
                    id SERIAL PRIMARY KEY,
                    title VARCHAR(255) NOT NULL,
                    author VARCHAR(255) NOT NULL,
                    published_year INT NOT NULL,
                    price DECIMAL(10,2) NOT NULL,
                    UNIQUE(title, author)
                )
            """);

            System.out.println("Bảng books đã sẵn sàng");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private boolean isDuplicate(String title, String author) throws SQLException {
        String sql = "SELECT COUNT(*) FROM books WHERE title = ? AND author = ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, title);
            ps.setString(2, author);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1) > 0;
        }
    }

    /* ===== THÊM SÁCH ===== */
    public void addBook(Book book) {
        String sql = "INSERT INTO books(title, author, published_year, price) VALUES (?,?,?,?)";

        try {
            if (isDuplicate(book.getTitle(), book.getAuthor())) {
                System.out.println(" Sách đã tồn tại!");
                return;
            }

            try (Connection conn = ConnectionDB.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, book.getTitle());
                ps.setString(2, book.getAuthor());
                ps.setInt(3, book.getPublishedYear());
                ps.setDouble(4, book.getPrice());
                ps.executeUpdate();

                System.out.println(" Thêm sách thành công");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* ===== CẬP NHẬT SÁCH ===== */
    public void updateBook(int id, Book book) {
        String sql = """
            UPDATE books
            SET title=?, author=?, published_year=?, price=?
            WHERE id=?
        """;

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getPublishedYear());
            ps.setDouble(4, book.getPrice());
            ps.setInt(5, id);

            if (ps.executeUpdate() == 0)
                System.out.println(" Không tìm thấy sách");
            else
                System.out.println(" Cập nhật thành công");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* ===== XÓA SÁCH ===== */
    public void deleteBook(int id) {
        String sql = "DELETE FROM books WHERE id=?";

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            if (ps.executeUpdate() == 0)
                System.out.println(" Không tìm thấy sách");
            else
                System.out.println(" Xóa thành công");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* ===== TÌM THEO TÁC GIẢ ===== */
    public void findBooksByAuthor(String author) {
        String sql = "SELECT * FROM books WHERE author ILIKE ?";

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + author + "%");
            ResultSet rs = ps.executeQuery();

            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("title") + " | " +
                                rs.getString("author") + " | " +
                                rs.getInt("published_year") + " | " +
                                rs.getDouble("price")
                );
            }

            if (!found)
                System.out.println(" Không có sách của tác giả này");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* ===== HIỂN THỊ TẤT CẢ ===== */
    public void listAllBooks() {
        String sql = "SELECT * FROM books ORDER BY id";

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("ID | Title | Author | Year | Price");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("title") + " | " +
                                rs.getString("author") + " | " +
                                rs.getInt("published_year") + " | " +
                                rs.getDouble("price")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}