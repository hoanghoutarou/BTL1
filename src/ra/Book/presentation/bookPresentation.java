package ra.Book.presentation;

import ra.Book.entity.Book;
import ra.Book.business.systemBook;
import ra.Book.business.businessBook;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class bookPresentation {
    public static List<Book> bookList = new ArrayList<>();

    public void displayMenu(Scanner sc) {
        bookList = systemBook.readDataFromFile();
        boolean isExit = false;
        businessBook business = new businessBook();
        do {
            System.out.println(" ▃▅▆█ QUẢN LÝ SÁCH █▆▅▃\n" +
                    "1. Thêm mới sách\n" +
                    "2. Cập nhật thông tin sách\n" +
                    "3. Xóa sách\n" +
                    "4. Tìm kiếm sách\n" +
                    "5. Hiển thị danh sách sách theo nhóm thể loại\n" +
                    "6. Quay lại\n");
            System.out.printf("Nhập lựa chọn của bạn: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    business.input();
                    systemBook.writeDataToFile(bookList);
                    break;
                case 2:
                    business.update(sc);
                    systemBook.writeDataToFile(bookList);
                    break;
                case 3:
                    business.delete(sc);
                    break;
                case 4:
                    business.find(sc);
                    break;
                case 5:
                    business.show();
                    break;
                case 6:
                    isExit = true;
                    break;
                default:
                    System.err.println("Vui lòng nhập 1-6");
            }
        } while (!isExit);

    }
}
