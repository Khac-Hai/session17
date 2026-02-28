package bai3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BookManager bm = new BookManager();
        bm.initDatabase(); // 👈 DÒNG QUAN TRỌNG
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== LIBRARY MENU =====");
            System.out.println("1. Thêm sách");
            System.out.println("2. Cập nhật sách");
            System.out.println("3. Xóa sách");
            System.out.println("4. Tìm theo tác giả");
            System.out.println("5. Hiển thị tất cả sách");
            System.out.println("0. Thoát");
            System.out.print(" Chọn: ");

            try {
                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1 -> {
                        System.out.print("Title: ");
                        String title = sc.nextLine();
                        System.out.print("Author: ");
                        String author = sc.nextLine();
                        System.out.print("Year: ");
                        int year = Integer.parseInt(sc.nextLine());
                        System.out.print("Price: ");
                        double price = Double.parseDouble(sc.nextLine());

                        bm.addBook(new Book(title, author, year, price));
                    }

                    case 2 -> {
                        System.out.print("ID: ");
                        int id = Integer.parseInt(sc.nextLine());
                        System.out.print("Title: ");
                        String title = sc.nextLine();
                        System.out.print("Author: ");
                        String author = sc.nextLine();
                        System.out.print("Year: ");
                        int year = Integer.parseInt(sc.nextLine());
                        System.out.print("Price: ");
                        double price = Double.parseDouble(sc.nextLine());

                        bm.updateBook(id, new Book(title, author, year, price));
                    }

                    case 3 -> {
                        System.out.print("ID: ");
                        bm.deleteBook(Integer.parseInt(sc.nextLine()));
                    }

                    case 4 -> {
                        System.out.print("Author: ");
                        bm.findBooksByAuthor(sc.nextLine());
                    }

                    case 5 -> bm.listAllBooks();

                    case 0 -> System.out.println(" Thoát chương trình");

                    default -> System.out.println(" Lựa chọn không hợp lệ");
                }

            } catch (NumberFormatException e) {
                System.out.println(" Nhập sai kiểu dữ liệu!");
                choice = -1;
            }

        } while (choice != 0);

        sc.close();
    }
}