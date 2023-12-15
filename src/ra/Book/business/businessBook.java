package ra.Book.business;

import ra.Book.entity.Book;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static ra.Book.presentation.bookPresentation.bookList;
import static ra.Category.presentation.categoryPresentation.catalogList;

public class businessBook {
    Book book = new Book();

    public void input() {
        book.input();
    }

    public void update(Scanner sc) {
        try {
            System.out.println("Mời bạn nhập mã sách cần cập nhật: ");
            String updateBookId = sc.nextLine();
            for (int i = 0; i < bookList.size(); i++) {
                if (Objects.equals(bookList.get(i).getId(), updateBookId)) {
                    boolean isExit = false;
                    do {
                        System.out.println("1. Cập nhât tiêu đề sách");
                        System.out.println("2. Cập nhật tên tác giả");
                        System.out.println("3. Cập nhật tên nhà xuất bản");
                        System.out.println("4. Cập nhật năm xuất bản");
                        System.out.println("5. Cập nhật mô ta sách");
                        System.out.println("6. Cập nhật mã thể loại sách");
                        System.out.println("7. Thoát");
                        int choice = Integer.parseInt(sc.nextLine());
                        switch (choice) {
                            case 1:
                                bookList.get(i).setTitle(book.inputTitle(sc, bookList));
                                break;
                            case 2:
                                bookList.get(i).setAuthor(book.inputAuthor(sc));
                                break;
                            case 3:
                                bookList.get(i).setPublisher(book.inputPublisher(sc));
                                break;
                            case 4:
                                bookList.get(i).setYear(book.inputYear(sc));
                                break;
                            case 5:
                                bookList.get(i).setDescription(book.inputDescription(sc));
                                break;
                            case 6:
                                bookList.get(i).setCategoryId(book.inputCategory(sc));
                                break;
                            case 7:
                                isExit = true;
                                System.out.println("Thông tin thể loại đã được cập nhật");
                                break;
                            default:
                                System.err.println("Vui lòng nhập 1-6");
                        }
                    } while (!isExit);

                    break;
                } else {
                    System.err.println("Không tìm thấy mã sach của bạn, vui lòng nhập lại");
                }
            }
        } catch (NumberFormatException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public int checkId(List<Book> bookList, Scanner sc) {
        boolean isExit = false;
        do {
            System.out.print("Nhập mã sách: ");
            String id = sc.nextLine();
            for (int i = 0; i < bookList.size(); i++) {
                if (id.equals(bookList.get(i).getId())) {
                    return i;
                }
            }
            if (!isExit) {
                System.out.println("Mã sách không tồn tại! Mời nhập lại!");

            }
        } while (true);
    }

    public void delete(Scanner sc) {
        boolean isExit = false;

            int deleteIndex = checkId(bookList, sc);
        do {
            System.out.printf("Bạn có muốn xóa sách không?\n 1.Yes \n 2.No \n");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    if (deleteIndex >= 0) {
                        bookList.remove(deleteIndex);
                        System.out.println("Xóa thành công!");
                    }
                    break;
                case 2:
                    isExit = true;
                    break;
                default:
                    System.out.println("Vui lòng nhập 1-2");
            }
        } while (!isExit);


    }

    public void find(Scanner sc) {
        boolean ishave =true;
        do {
            System.out.println("Nhập tên sách cần tim:");
            String findBookName = sc.nextLine();
            for (int i = 0; i < bookList.size(); i++) {
                if (bookList.get(i).getTitle().contains(findBookName)) {
                    ishave=true;
                    System.out.println(bookList.get(i).toString());
                }
            }
            if (!ishave){
                System.out.println("Không có ten sach khop");
            }
        }while (!ishave);

    }

    public void show() {
        System.out.println("Sách đang có ở trong cửa hàng:");
        for (int j = 0; j < catalogList.size(); j++) {
            System.out.printf("Thể loại %s: \n",catalogList.get(j).getName());
            for (int i = 0; i < bookList.size(); i++) {
                if (bookList.get(i).getCategoryId() == catalogList.get(j).getId()) {
                    System.out.printf("Sách %s \n",bookList.get(i).getTitle());
                }
            }
        }
    }
}
