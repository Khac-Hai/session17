package bai1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        MovieManagement manager = new MovieManagement();
        manager.initDatabase();   // tạo bảng + procedure

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== QUẢN LÝ PHIM =====");
            System.out.println("1. Thêm phim");
            System.out.println("2. Liệt kê phim");
            System.out.println("3. Sửa phim");
            System.out.println("4. Xóa phim");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");

            try {
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1 -> {
                        System.out.print("Tiêu đề: ");
                        String title = sc.nextLine();
                        System.out.print("Đạo diễn: ");
                        String director = sc.nextLine();
                        System.out.print("Năm phát hành: ");
                        int year = Integer.parseInt(sc.nextLine());

                        manager.addMovie(title, director, year);
                    }
                    case 2 -> manager.listMovies();
                    case 3 -> {
                        System.out.print("ID phim: ");
                        int id = Integer.parseInt(sc.nextLine());
                        System.out.print("Tiêu đề mới: ");
                        String title = sc.nextLine();
                        System.out.print("Đạo diễn mới: ");
                        String director = sc.nextLine();
                        System.out.print("Năm mới: ");
                        int year = Integer.parseInt(sc.nextLine());

                        manager.updateMovie(id, title, director, year);
                    }
                    case 4 -> {
                        System.out.print("ID cần xóa: ");
                        int id = Integer.parseInt(sc.nextLine());
                        manager.deleteMovie(id);
                    }
                    case 0 -> {
                        System.out.println("👋 Kết thúc chương trình");
                        return;
                    }
                    default -> System.out.println("❌ Lựa chọn không hợp lệ");
                }

            } catch (NumberFormatException e) {
                System.out.println("⚠️ Dữ liệu nhập không hợp lệ!");
            }
        }
    }
}