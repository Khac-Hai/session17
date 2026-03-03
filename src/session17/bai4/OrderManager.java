package session17.bai4;

import java.sql.*;

public class OrderManager {

    public void initDatabase() {
        try (Connection conn = ConnectionDB.getConnection();
             Statement stm = conn.createStatement()) {

            stm.execute("""
                CREATE TABLE IF NOT EXISTS products (
                    id SERIAL PRIMARY KEY,
                    name VARCHAR(255) NOT NULL UNIQUE,
                    price DECIMAL(10,2) NOT NULL
                )
            """);

            stm.execute("""
                CREATE TABLE IF NOT EXISTS customers (
                    id SERIAL PRIMARY KEY,
                    name VARCHAR(255) NOT NULL,
                    email VARCHAR(255) UNIQUE
                )
            """);

            stm.execute("""
                CREATE TABLE IF NOT EXISTS orders (
                    id SERIAL PRIMARY KEY,
                    customer_id INT REFERENCES customers(id),
                    order_date DATE NOT NULL,
                    total_amount DECIMAL(10,2) NOT NULL
                )
            """);

            System.out.println(" Khởi tạo CSDL shop_db thành công");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addProduct(Product p) {
        String sql = "INSERT INTO products(name, price) VALUES (?,?)";

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getName());
            ps.setDouble(2, p.getPrice());
            ps.executeUpdate();

            System.out.println(" Thêm sản phẩm thành công");

        } catch (SQLException e) {
            System.out.println(" Tên sản phẩm đã tồn tại");
        }
    }

    public void updateCustomer(int id, Customer c) {
        String sql = "UPDATE customers SET name=?, email=? WHERE id=?";

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getName());
            ps.setString(2, c.getEmail());
            ps.setInt(3, id);

            if (ps.executeUpdate() == 0)
                System.out.println(" Không tìm thấy khách hàng");
            else
                System.out.println(" Cập nhật khách hàng thành công");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createOrder(Order o) {
        String sql = """
            INSERT INTO orders(customer_id, order_date, total_amount)
            VALUES (?,?,?)
        """;

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, o.getCustomerId());
            ps.setDate(2, o.getOrderDate());
            ps.setDouble(3, o.getTotalAmount());
            ps.executeUpdate();

            System.out.println(" Tạo đơn hàng thành công");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listAllOrders() {
        String sql = """
            SELECT o.id, c.name, o.order_date, o.total_amount
            FROM orders o
            JOIN customers c ON o.customer_id = c.id
        """;

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("name") + " | " +
                                rs.getDate("order_date") + " | " +
                                rs.getDouble("total_amount")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getOrdersByCustomer(int customerId) {
        String sql = "SELECT * FROM orders WHERE customer_id=?";

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();

            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getDate("order_date") + " | " +
                                rs.getDouble("total_amount")
                );
            }

            if (!found)
                System.out.println(" Khách hàng chưa có đơn hàng");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
