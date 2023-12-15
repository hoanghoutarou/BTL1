package ra.Category.presentation;

import ra.Category.business.businessCategory;
import ra.Category.entity.Category;
import ra.Category.business.systemCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class categoryPresentation {
    public static List<Category> catalogList = new ArrayList<>();
    public void displayMenu(Scanner sc) {
        catalogList = systemCategory.readDataFromFile();
        boolean isExit = false;
        businessCategory business = new businessCategory();
        do {
            System.out.println("▃▅▆█ QUẢN LÝ THỂ LOẠI █▆▅▃\n" +
                    "1. Thêm mới thể loại\n" +
                    "2. Hiển thị danh sách theo tên A – Z\n" +
                    "3. Thống kê thể loại và số sách có trong mỗi thể loại\n" +
                    "4. Cập nhật thể loại\n" +
                    "5. Xóa thể loại\n" +
                    "6. Quay lại");
            System.out.printf("Nhập lựa chọn của bạn: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice){
                case 1:
                    business.input();
                    systemCategory.writeDataToFile(catalogList);
                    break;
                case 2:
                    business.sortName();
                    break;
                case 3:
                    business.statistical();
                    break;
                case 4:
                    business.update(sc);
                    systemCategory.writeDataToFile(catalogList);
                    break;
                case 5:
                    business.delete(sc);
                    systemCategory.writeDataToFile(catalogList);
                    break;
                case 6:
                    isExit = true;
                    break;
                default:
                    System.err.println("Vui lòng nhập 1-6");
            }
        }while (!isExit);
    }
}
