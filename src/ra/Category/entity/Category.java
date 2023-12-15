package ra.Category.entity;

import ra.Interface.IEntity;
import ra.Category.presentation.categoryPresentation;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

public class Category implements IEntity, Serializable {
    private int id;
    private String name;
    private Boolean status;

    public Category() {
    }

    public Category(int id, String name, Boolean status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public int inputCatalogId(List<Category> catalogList) {
        if (catalogList.isEmpty()) {
            return 1;
        } else {
            int max = catalogList.get(0).getId();
            for (int i = 1; i < catalogList.size(); i++) {
                if (max < catalogList.get(i).getId()) {
                    max = catalogList.get(i).getId();
                }
            }
            return max + 1;
        }
    }

    public String inputCatalogName(Scanner sc, List<Category> catalogList) {
        do {
            System.out.println("Nhập tên thể loại:");
            this.name = sc.nextLine();
            if (this.name.length() >= 6 && this.name.length() <= 30) {
                boolean isDuplication = false;
                for (int i = 0; i < catalogList.size(); i++) {
                    if (this.name.equals(catalogList.get(i).getName())) {
                        isDuplication = true;
                        break;
                    }
                }
                if (isDuplication) {
                    System.err.println("Tên thể loại bị trùng vui lòng nhập lại");
                } else {
                    return this.name;
                }
            } else {
                System.err.println("Tên thể loại phải có từ 6 đến 30 ký tự, vui lòng nhập lại!");
            }
        } while (true);
    }

    public boolean inputStatus(Scanner sc) {
        System.out.println("Nhập vào trạng thái thể loại:");
        do {
            String status = sc.nextLine();
            if (status.equals("true") || status.equals("false")) {
                return Boolean.parseBoolean(status);
            } else {
                System.err.println("Trạng thái thể loại chỉ nhận true hoặc false, vui lòng nhập lại");
            }
        } while (true);
    }

    @Override
    public void input() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập số the loai cần tao:");
        try {
            int n = Integer.parseInt(sc.nextLine());

            for (int i = 0; i < n; i++) {
                System.out.printf("The loai %d: \n", i + 1);
                this.id = inputCatalogId(categoryPresentation.catalogList);
                this.name = inputCatalogName(sc, categoryPresentation.catalogList);
                this.status = inputStatus(sc);
                Category category = new Category(this.id,this.name,this.status);
                categoryPresentation.catalogList.add(category);
            }


        } catch (NumberFormatException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public String toString() {
        String s;
        if (this.status) {
            s = "Hoạt động";
        } else {
            s = "Không hoạt động";
        }
        return  "---------------------------------------------------------------------"+
                "Mã thể loại: " + id + '\n' +
                "Tên thể loại: " + name + '\n' +
                "Trạng thái: " + s + '\n' +
                "---------------------------------------------------------------------";
    }

    @Override
    public void output() {
        categoryPresentation.catalogList.forEach(System.out::println);
    }
}
