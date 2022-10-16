package ra.entity;

import ra.interface1.IBook;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Author implements IBook, Serializable{
    private int authorId;
    private String authorName;
    private boolean authorStatus;

    public Author() {
    }

    public Author(int authorId, String authorName, boolean authorStatus) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.authorStatus = authorStatus;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public boolean isAuthorStatus() {
        return authorStatus;
    }

    public void setAuthorStatus(boolean authorStatus) {
        this.authorStatus = authorStatus;
    }

    @Override
    public void inputData(Scanner scanner, List<Author> listAuthor, List<Book> listBook) {
        this.authorId = listAuthor.size()+1;
       inputAuthorName(scanner,listAuthor);
       inputAuthorStatus(scanner);
    }
    public boolean inputAuthorStatus (Scanner scanner){
        do {
            System.out.println("Vui lòng chọn trạng thái của tác giả!");
            System.out.println("1. Còn bán!");
            System.out.println("2. Không còn bán!");
            System.out.print("Lựa chọn của bạn là:");
            String choice = scanner.nextLine();
            if (choice.trim().length()!=0){
                if (choice.equals("1")){
                    this.authorStatus = true;
                    break;
                }else if (choice.equals("2")){
                    this.authorStatus = false;
                    break;
                }else {
                    System.err.println("Vui lòng nhập 1 hoặc 2 !!!");
                }
            }else {
                System.err.println("Không được để trống!");
            }
        }while (true);
      return this.authorStatus;
    }
    public String inputAuthorName (Scanner scanner, List<Author> authorList){
        String name;

        do {
            System.out.println("Nhập vào tên tác giả: ");
            name = scanner.nextLine();
            if (name.trim().length()>=6 && name.trim().length()<=50 ){
                boolean checkAuthorName = false;
                for (Author author : authorList) {
                    if ( author.getAuthorName().toLowerCase().equals(name.toLowerCase())){
                        checkAuthorName = true;
                        break;
                    }
                }
                if (!checkAuthorName){
                    this.authorName = name;
                    break;
                }else {
                    System.err.println("Đã có tên tác giả này trong danh sách !!!");
                    System.out.println("Bạn hãy nhập lại tên tác giả mới hoặc thêm ký tự để phân biệt với tác giả cũ !!!");
                }
            }else {
                System.err.println("Vui lòng nhập từ 6-50 ký tự");
            }
        }while (true);
        return  this.authorName;
    }


    @Override
    public void displayData() {
        String status = displayStatus();
        System.out.printf("%-20d%-30s%-20s\n",this.authorId,this.authorName,status);
    }

    @Override
    public void getData(List<Author> list, List<Book> listBook,String path) {
        File file = null;
        FileInputStream  fileInputStream = null;
        ObjectInputStream ois = null;
        try {
            file = new File(path);
            fileInputStream = new FileInputStream(file);
           ois = new ObjectInputStream(fileInputStream);
           List<Author> authorList = (List<Author>) ois.readObject();
            list.addAll(authorList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois!=null){
                    ois.close();
                }
                if (fileInputStream!=null){
                    fileInputStream.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void insertData(List<Author> listAuthor, List<Book> listBook,String path) {
        File file = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            file = new File(path);
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(listAuthor);
        }catch (Exception ex1){
            ex1.printStackTrace();
        }
        finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (oos != null) {
                    oos.close();
                }
            } catch (Exception ex2) {
                ex2.printStackTrace();
            }
        }
    }

    public String displayStatus (){
        String status = "";
        if (this.authorStatus){
            status = "Còn bán";
        }else {
            status = "Không còn bán";
        }
        return status;
    }
}
