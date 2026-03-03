package session18;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n******** QUẢN LÝ SẢN PHẨM ********");
            System.out.println("1. Danh sách sản phẩm");
            System.out.println("2. Thêm sản phẩm mới");
            System.out.println("3. Cập nhật sản phẩm");
            System.out.println("4. Xóa sản phẩm");
            System.out.println("5. Tìm kiếm sản phẩm theo tên");
            System.out.println("6. Sắp xếp sản phẩm theo giá tăng dần");
            System.out.println("7. Thống kê số lượng sản phẩm theo danh mục");
            System.out.println("8. Thoát");
            System.out.print("Chọn: ");

            // kiểm tra input hợp lệ
            while (!sc.hasNextInt()) {
                System.out.println("Vui lòng nhập số từ 1 đến 8!");
                sc.next();
            }
            choice = sc.nextInt();
            sc.nextLine(); // bỏ qua ký tự xuống dòng

            switch (choice) {
                case 1:
                    ProductDAO.getAllProducts();
                    break;
                case 2:
                    ProductDAO.addProduct();
                    break;
                case 3:
                    ProductDAO.updateProduct();
                    break;
                case 4:
                    ProductDAO.deleteProduct();
                    break;
                case 5:
                    ProductDAO.searchByName();
                    break;
                case 6:
                    ProductDAO.sortByPrice();
                    break;
                case 7:
                    ProductDAO.countByCategory();
                    break;
                case 8:
                    System.out.println("Thoát chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng nhập từ 1 đến 8!");
            }
        } while (choice != 8);

        sc.close();
    }
}
