package ra.Category.business;

import ra.Category.entity.Category;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static ra.Category.presentation.categoryPresentation.catalogList;
import static ra.Book.presentation.bookPresentation.bookList;

public class businessCategory {
    Category catalog = new Category();

    public void input() {
        catalog.input();
    }

    public void sortName() {
        System.out.println("▃▅▆█ THỂ LOẠI SÁCH █▆▅▃");
        catalogList.stream().sorted(Comparator.comparing(Category::getName)).forEach(System.out::println);

    }

    public void statistical() {

        System.out.println("Thống kê thể loại và số sách trong mỗi thể loại");
        try {
            for (Category category:catalogList){
                int id = category.getId();
                String name = category.getName();
                long bookCount = bookList.stream().filter(book -> book.getCategoryId() == id).count();
                System.out.printf("Mã thể loại: %d  Tên danh mục: %s  Số lượng sách: %d \n",id,name,bookCount);
            }

        }catch (Exception ex){
            System.err.println(ex);
        }
    }

    public void update(Scanner sc) {
        try {
            System.out.println("Mời bạn nhập mã thể loại cần cập nhật: ");
            int updateCategoryId = Integer.parseInt(sc.nextLine());
            for (int i = 0; i < catalogList.size(); i++) {
                if (catalogList.get(i).getId() == updateCategoryId) {
                    boolean isExit = false;
                    do {
                        System.out.println("1. Cập nhât tên thể loại");
                        System.out.println("2. Cập nhật trạng thái thể loại");
                        System.out.println("3. Thoát");
                        int choice = Integer.parseInt(sc.nextLine());
                        switch (choice) {
                            case 1:
                                catalogList.get(i).setName(catalog.inputCatalogName(sc, catalogList));
                                break;
                            case 2:
                                catalogList.get(i).setStatus(catalog.inputStatus(sc));
                                break;
                            case 3:
                                isExit = true;
                                System.out.println("Thông tin thể loại đã được cập nhật");
                                break;

                            default:
                                System.err.println("Vui lòng nhập 1-3");
                        }
                    } while (!isExit);

                    break;
                } else {
                    System.err.println("Không tìm thấy mã thể loại của bạn, vui lòng nhập lại");
                }
            }
        } catch (NumberFormatException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public int checkId(List<Category> catalogList, Scanner sc) {
        boolean isExit = false;
        do {
            System.out.print("Nhập mã thể loại: ");
            int id = Integer.parseInt(sc.nextLine());
            for (int i = 0; i < catalogList.size(); i++) {
                if (id == catalogList.get(i).getId()) {
                    return i;
                }
            }
            if (!isExit) {
                System.out.println("Mã thể loại không tồn tại! Mời nhập lại!");

            }
        } while (true);
    }
    public void delete(Scanner sc) {
        int deleteIndex = checkId(catalogList, sc);
        if (deleteIndex >= 0) {
            boolean isCheck = false;
            for (int i = 0; i < bookList.size(); i++) {
                if (bookList.get(i).getCategoryId() == catalogList.get(deleteIndex).getId()) {
                    isCheck = true;
                    System.out.println("Danh mục đang chứa sản phẩm nên không xóa được !");
                }
            }
            if (!isCheck) {
                catalogList.remove(deleteIndex);
                System.out.println("Xóa thành công!");
            }
        }
    }
}
