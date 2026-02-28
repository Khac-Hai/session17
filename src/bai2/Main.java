package bai2;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        TaskManagement taskManagement = new TaskManagement();
        taskManagement.initDatabase();

        Scanner sc = new Scanner(System.in);
        int choice = -1;

        do {
            System.out.println("\n========= TO-DO LIST MENU =========");
            System.out.println("1. Thêm công việc");
            System.out.println("2. Liệt kê công việc");
            System.out.println("3. Cập nhật trạng thái công việc");
            System.out.println("4. Xóa công việc");
            System.out.println("5. Tìm kiếm công việc theo tên");
            System.out.println("6. Thống kê công việc");
            System.out.println("0. Thoát");
            System.out.print(" Chọn chức năng: ");

            try {
                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1 -> {
                        System.out.print("Nhập tên công việc: ");
                        String name = sc.nextLine();

                        System.out.print("Nhập trạng thái (Hoàn thành / Chưa hoàn thành): ");
                        String status = sc.nextLine();

                        taskManagement.addTask(name, status);
                    }

                    case 2 -> taskManagement.listTasks();

                    case 3 -> {
                        System.out.print("Nhập ID công việc: ");
                        int id = Integer.parseInt(sc.nextLine());

                        System.out.print("Nhập trạng thái mới: ");
                        String status = sc.nextLine();

                        taskManagement.updateTaskStatus(id, status);
                    }

                    case 4 -> {
                        System.out.print("Nhập ID công việc cần xóa: ");
                        int id = Integer.parseInt(sc.nextLine());

                        taskManagement.deleteTask(id);
                    }

                    case 5 -> {
                        System.out.print("Nhập tên công việc cần tìm: ");
                        String keyword = sc.nextLine();

                        taskManagement.searchTaskByName(keyword);
                    }

                    case 6 -> taskManagement.taskStatistics();

                    case 0 -> System.out.println(" Thoát chương trình. Tạm biệt!");

                    default -> System.out.println("Lựa chọn không hợp lệ!");
                }

            } catch (NumberFormatException e) {
                System.out.println(" Lỗi: Vui lòng nhập số!");
            }

        } while (choice != 0);

        sc.close();
    }
}