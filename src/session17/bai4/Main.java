package session17.bai4;

import java.sql.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        OrderManager om = new OrderManager();
        om.initDatabase();

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== SHOP MENU =====");
            System.out.println("1. Thêm sản phẩm");
            System.out.println("2. Cập nhật khách hàng");
            System.out.println("3. Tạo đơn hàng");
            System.out.println("4. Hiển thị tất cả đơn hàng");
            System.out.println("5. Tìm đơn theo khách hàng");
            System.out.println("0. Thoát");
            System.out.print(" Chọn: ");

            try {
                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1 -> {
                        System.out.print("Tên SP: ");
                        String name = sc.nextLine();
                        System.out.print("Giá: ");
                        double price = Double.parseDouble(sc.nextLine());
                        om.addProduct(new Product(name, price));
                    }

                    case 2 -> {
                        System.out.print("Customer ID: ");
                        int id = Integer.parseInt(sc.nextLine());
                        System.out.print("Tên: ");
                        String name = sc.nextLine();
                        System.out.print("Email: ");
                        String email = sc.nextLine();
                        om.updateCustomer(id, new Customer(name, email));
                    }

                    case 3 -> {
                        System.out.print("Customer ID: ");
                        int cid = Integer.parseInt(sc.nextLine());
                        System.out.print("Tổng tiền: ");
                        double total = Double.parseDouble(sc.nextLine());

                        om.createOrder(
                                new Order(cid, new Date(System.currentTimeMillis()), total)
                        );
                    }

                    case 4 -> om.listAllOrders();
                    case 5 -> {
                        System.out.print("Customer ID: ");
                        om.getOrdersByCustomer(Integer.parseInt(sc.nextLine()));
                    }
                    case 0 -> System.out.println(" Thoát chương trình");
                    default -> System.out.println(" Lựa chọn không hợp lệ");
                }

            } catch (NumberFormatException e) {
                System.out.println(" Nhập sai kiểu dữ liệu");
                choice = -1;
            }

        } while (choice != 0);

        sc.close();
    }
}
