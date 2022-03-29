import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {


        ArrayList<String> pathList = new ArrayList<>();
        pathList.add("C:\\Алексей\\Программирование\\JAVA Нетология\\Java Core\\HW Save\\Games\\savegames\\save1.dat");
        pathList.add("C:\\Алексей\\Программирование\\JAVA Нетология\\Java Core\\HW Save\\Games\\savegames\\save2.dat");
        pathList.add("C:\\Алексей\\Программирование\\JAVA Нетология\\Java Core\\HW Save\\Games\\savegames\\save3.dat");

        GameProgress GameProgress1 = new GameProgress(100, 2, 1, 10.00);
        GameProgress GameProgress2 = new GameProgress(60, 3, 5, 70.00);
        GameProgress GameProgress3 = new GameProgress(40, 1, 50, 100.00);

        saveGame("C:\\Алексей\\Программирование\\JAVA Нетология\\Java Core\\HW Save\\Games\\savegames\\save1.dat", GameProgress1);
        saveGame("C:\\Алексей\\Программирование\\JAVA Нетология\\Java Core\\HW Save\\Games\\savegames\\save2.dat", GameProgress2);
        saveGame("C:\\Алексей\\Программирование\\JAVA Нетология\\Java Core\\HW Save\\Games\\savegames\\save3.dat", GameProgress3);

        zipFiles("C:\\Алексей\\Программирование\\JAVA Нетология\\Java Core\\HW Save\\Games\\savegames\\zip.zip", pathList);
    }


    static void saveGame(String path, GameProgress p) {


        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
// запишем экземпляр класса в файл
            oos.writeObject(p);
        } catch (Exception ex) {
            System.out.println(ex.getMessage(
            ));
        }
    }


    static void zipFiles(String pathZip, ArrayList<String> pathDat) {


        try (ZipOutputStream zout = new ZipOutputStream(new
                FileOutputStream(pathZip))) {
            int i = 1;
            for (String pathDatZip : pathDat) {
                try (FileInputStream fis = new FileInputStream(pathDatZip)) {
                    ZipEntry entry = new ZipEntry("save " + i);
                    zout.putNextEntry(entry);
// считываем содержимое файла в массив byte
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
// добавляем содержимое к архиву
                    zout.write(buffer);
// закрываем текущую запись для новой записи
                    zout.closeEntry();
                    i++;


                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }

                File file = new File(pathDatZip);
                file.delete();
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}

