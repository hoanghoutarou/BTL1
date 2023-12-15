package ra.Book.entity;

import ra.Book.presentation.bookPresentation;
import ra.Interface.IEntity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import static ra.Category.presentation.categoryPresentation.catalogList;
public class Book implements IEntity, Serializable {
    private String id, title, author, publisher, description;
    private int year, categoryId;

    public Book() {
    }

    public Book(String id, String title, String author, String publisher, String description, int year, int categoryId) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.description = description;
        this.year = year;
        this.categoryId = categoryId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String inputBookId(Scanner sc, List<Book> bookList) {
        do {
            System.out.println("Nhâp mã sách:");
            this.id = sc.nextLine();
            if (this.id.length() == 4) {
                if (this.id.charAt(0) == 'B') {
                    boolean isDuplication = false;
                    for (int i = 0; i < bookList.size(); i++) {
                        if (this.id.equals(bookList.get(i).getId())) {
                            isDuplication = true;
                            break;
                        }
                    }
                    if (isDuplication) {
                        System.err.println("Mã sách bị trùng vui lòng nhập lại");
                    } else {
                        return this.id;
                    }
                } else {
                    System.err.println("Mã sách phải bắt đầu bằng ký tự 'B' ");
                }
            } else {
                System.err.println("Mã sách có độ dài kí tự là 4");
            }
        } while (true);
    }
    public String inputTitle(Scanner sc, List<Book> bookList){
        do {
            System.out.println("Nhập tiêu đề sách:");
            this.title=sc.nextLine();
            if (this.title.length()>=6||this.title.length()<=50){
                boolean isDuplication = false;
                for (int i = 0; i < bookList.size(); i++) {
                    if (this.title.equals(bookList.get(i).getTitle())) {
                        isDuplication = true;
                        break;
                    }
                }
                if (isDuplication) {
                    System.err.println("Tên tiêu đề sách bị trùng vui lòng nhập lại");
                } else {
                    return this.title;
                }
            }else {
                System.err.println("Tiêu đề sách phải có từ 6 - 50 ký tự");
            }
        }while (true);
    }
    public String inputAuthor(Scanner sc){
        do {
            System.out.println("Nhập tên tác giả:");
            this.author = sc.nextLine();
            if (this.author.isEmpty()==false){
                return this.author;
            }else {
                System.err.println("Tên tác giả không được bỏ trống");
            }
        }while (true);
    }
    public int inputYear(Scanner sc){
        do {
            LocalDate localDate =LocalDate.now();
            System.out.println("Nhập năm xuất bản:");
            this.year=Integer.parseInt(sc.nextLine());
            if (this.year>=1970&&this.year<= localDate.getYear()){
                return this.year;
            }else {
                System.err.println("Năm xuất bản tối thiểu từ năm 1970 và không lớn hơn năm hiện tại");
            }
        }while (true);
    }
    public String inputPublisher(Scanner sc){
        do {
            System.out.println("Nhập tên nhà xuất bản:");
            this.publisher = sc.nextLine();
            if (this.publisher.isEmpty()==false){
                return this.publisher;
            }else {
                System.err.println("Tên  nhà xuất bản không được bỏ trống");
            }
        }while (true);
    }
    public String inputDescription(Scanner sc){
        do {
            System.out.println("Nhập mô tả sách:");
            this.description = sc.nextLine();
            if (this.description.isEmpty()==false){
                return this.description;
            }else {
                System.err.println("Mô tả sách không được bỏ trống");
            }
        }while (true);
    }
    public int inputCategory(Scanner sc){
        System.out.println("Chọn thể loại:");
        do {
            for (int i=0;i<catalogList.size();i++ ){
                System.out.printf("%d. %s \n",i+1,catalogList.get(i).getName());
            }
            System.out.println("Lựa chọn của bạn");
            this.categoryId = Integer.parseInt(sc.nextLine());
            boolean isExit = false;
            for (int i = 0; i < catalogList.size(); i++) {
                if (this.categoryId == catalogList.get(i).getId()) {
                    isExit = true;
                    break;
                }
            }
            if (isExit) {
                return this.categoryId;
            } else {
                System.out.println("Mã danh mục không tồn tại! Mời nhập lại!");
            }
        }while (true);

    }
    @Override
    public void input() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập số sach cần tao:");
        try {
            int n = Integer.parseInt(sc.nextLine());
            for (int i = 0; i < n; i++) {
                this.id=inputBookId(sc, bookPresentation.bookList);
                this.title=inputTitle(sc, bookPresentation.bookList);
                this.author=inputAuthor(sc);
                this.publisher=inputPublisher(sc);
                this.year=inputYear(sc);
                this.description=inputDescription(sc);
                this.categoryId=inputCategory(sc);
                Book book = new Book(this.id,this.title,this.author,this.publisher,this.description,this.year,this.categoryId);
                bookPresentation.bookList.add(book);
            }
        }catch (NumberFormatException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public String toString() {
        return  "Mã sách: " + id + '\n' +
                "Tiêu đề sách: " + title + '\n' +
                "Tác giả: " + author + '\n' +
                "Nhà xuat bản: " + publisher + '\n' +
                "Mô tả sách: " + description + '\n' +
                "Nam xuất bản" + year + '\n' +
                "Mã thể loại: " + categoryId + '\n' +
                "---------------------------------------------------------------------";

    }

    @Override
    public void output() {
        bookPresentation.bookList.forEach(System.out::println);
    }
}
