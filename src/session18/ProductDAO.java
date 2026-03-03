package session18;

import java.sql.*;
import java.util.Scanner;

public class ProductDAO {
    private static Scanner sc = new Scanner(System.in);

    // 1. Lấy danh sách sản phẩm
    public static void getAllProducts() {
        try (Connection conn = ConnectionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM SanPham")) {
            while (rs.next()) {
                System.out.println(rs.getInt("ma_san_pham") + " - " +
                        rs.getString("ten_san_pham") + " - " +
                        rs.getFloat("gia_san_pham"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 2. Thêm sản phẩm mới
    public static void addProduct() {
        try (Connection conn = ConnectionDB.getConnection()) {
            System.out.print("Tên sản phẩm: ");
            String ten = sc.nextLine();
            System.out.print("Giá sản phẩm: ");
            float gia = sc.nextFloat(); sc.nextLine();
            System.out.print("Tiêu đề: ");
            String tieuDe = sc.nextLine();
            System.out.print("Danh mục: ");
            String danhMuc = sc.nextLine();

            String sql = "INSERT INTO SanPham(ten_san_pham, gia_san_pham, tieu_de_san_pham, ngay_tao, danh_muc) VALUES (?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ten);
            ps.setFloat(2, gia);
            ps.setString(3, tieuDe);
            ps.setDate(4, new Date(System.currentTimeMillis()));
            ps.setString(5, danhMuc);
            ps.executeUpdate();
            System.out.println("Thêm sản phẩm thành công!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 3. Cập nhật sản phẩm
    public static void updateProduct() {
        try (Connection conn = ConnectionDB.getConnection()) {
            System.out.print("Nhập mã sản phẩm cần sửa: ");
            int id = sc.nextInt(); sc.nextLine();
            System.out.print("Tên mới: ");
            String ten = sc.nextLine();
            System.out.print("Giá mới: ");
            float gia = sc.nextFloat(); sc.nextLine();

            String sql = "UPDATE SanPham SET ten_san_pham=?, gia_san_pham=? WHERE ma_san_pham=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ten);
            ps.setFloat(2, gia);
            ps.setInt(3, id);
            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("Cập nhật thành công!");
            else System.out.println("Không tìm thấy sản phẩm!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 4. Xóa sản phẩm
    public static void deleteProduct() {
        try (Connection conn = ConnectionDB.getConnection()) {
            System.out.print("Nhập mã sản phẩm cần xóa: ");
            int id = sc.nextInt(); sc.nextLine();
            String sql = "DELETE FROM SanPham WHERE ma_san_pham=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("Xóa thành công!");
            else System.out.println("Không tìm thấy sản phẩm!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 5. Tìm kiếm theo tên
    public static void searchByName() {
        try (Connection conn = ConnectionDB.getConnection()) {
            System.out.print("Nhập tên cần tìm: ");
            String keyword = sc.nextLine();
            String sql = "SELECT * FROM SanPham WHERE ten_san_pham ILIKE ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("ma_san_pham") + " - " + rs.getString("ten_san_pham"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 6. Sắp xếp theo giá tăng dần
    public static void sortByPrice() {
        try (Connection conn = ConnectionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM SanPham ORDER BY gia_san_pham ASC")) {
            while (rs.next()) {
                System.out.println(rs.getString("ten_san_pham") + " - " + rs.getFloat("gia_san_pham"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 7. Thống kê số lượng theo danh mục
    public static void countByCategory() {
        try (Connection conn = ConnectionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT danh_muc, COUNT(*) AS so_luong FROM SanPham GROUP BY danh_muc")) {
            while (rs.next()) {
                System.out.println(rs.getString("danh_muc") + " - " + rs.getInt("so_luong"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

