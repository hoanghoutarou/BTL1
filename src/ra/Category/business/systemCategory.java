package ra.Category.business;

import ra.Category.entity.Category;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class systemCategory {
    public static void writeDataToFile(List<Category> catalogList) {
    File file = new File("categories.txt");
    FileOutputStream fos = null;
    ObjectOutputStream oos = null;
    try {
        fos = new FileOutputStream(file);
        oos = new ObjectOutputStream(fos);
        oos.writeObject(catalogList);
        //dẩy từ stream xuống file
        oos.flush();

    } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
    } catch (IOException e) {
        throw new RuntimeException(e);
    } catch (Exception ex) {
        ex.printStackTrace();
    } finally {
        if (fos != null) {
            try {
                fos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (oos != null) {
            try {
                oos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}

    public static List<Category> readDataFromFile() {
        List<Category> catalogListRead = null;
        File file = new File("categories.txt");
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            catalogListRead = (List<Category>) ois.readObject();
            return catalogListRead;
        } catch (FileNotFoundException e) {
            catalogListRead = new ArrayList<>();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception ex) {
            //doc ten file sai
            ex.printStackTrace();
            catalogListRead = new ArrayList<>();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return catalogListRead;
    }

}
