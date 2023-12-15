package ra;
import ra.Category.business.systemCategory;


import ra.Book.presentation.bookPresentation;
import ra.Category.presentation.categoryPresentation;

import java.util.ArrayList;
import java.util.Scanner;

public class libraryPresentation {

    public static void main(String[] args) {
        try{
            categoryPresentation.catalogList = systemCategory.readDataFromFile();
        }catch (Exception ex){
            System.err.println("File not found!");
            categoryPresentation.catalogList= new ArrayList<>();
        }
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("●▬▬▬▬๑۩ QUẢN LÝ THƯ VIỆN ۩๑▬▬▬▬▬●\n" +
                    "1. Quản lý Thể loại\n" +
                    "2. Quản lý Sách\n" +
                    "3. Thoát");
            System.out.println("Nhap lua chon của ban:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    categoryPresentation categoryPresentation = new categoryPresentation();
                    categoryPresentation.displayMenu(scanner);
                    break;
                case 2:
                    bookPresentation bookPresentation = new bookPresentation();
                    bookPresentation.displayMenu(scanner);
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng nhập 1-3");
            }
        } while (true);
    }
}
