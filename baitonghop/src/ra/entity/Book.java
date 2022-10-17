package ra.entity;

import com.sun.scenario.effect.impl.sw.java.JSWColorAdjustPeer;
import ra.interface1.IBook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Book implements IBook, Serializable {
    private String bookId;
    private String bookName;
    private float importPrice;
    private float exportPrice;
    private float profit;
    private int quantity;
    private ArrayList<Author> listAuthor = new ArrayList<>();
    private String title;
    private String content;
    private String publishing;
    private String bookStatus;

    public Book() {
    }

    public Book(String bookId, String bookName, float importPrice, float exportPrice,
                float profit, int quantity, ArrayList<Author> listAuthor, String title,
                String content, String publishing, String bookStatus) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.profit = profit;
        this.quantity = quantity;
        this.listAuthor = listAuthor;
        this.title = title;
        this.content = content;
        this.publishing = publishing;
        this.bookStatus = bookStatus;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public float getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(float importPrice) {
        this.importPrice = importPrice;
    }

    public float getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(float exportPrice) {
        this.exportPrice = exportPrice;
    }

    public float getProfit() {
        return profit;
    }

    public void setProfit(float profit) {
        this.profit = profit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ArrayList<Author> getListAuthor() {
        return listAuthor;
    }

    public void setListAuthor(ArrayList<Author> listAuthor) {
        this.listAuthor = listAuthor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublishing() {
        return publishing;
    }

    public void setPublishing(String publishing) {
        this.publishing = publishing;
    }

    public String getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(String bookStatus) {
        this.bookStatus = bookStatus;
    }

    @Override
    public void inputData(Scanner scanner, List<Author> authorList,List<Book> listBook) {
        inputBookId(scanner,listBook);
        inputBookName(scanner);
        while (true){
            try {
                System.out.println("Nhập vào giá nhập của Sách");
                this.importPrice = Float.parseFloat(scanner.nextLine());
                if (this.importPrice>0){
                    break;
                }else {
                    System.err.println("Trên đời chả có gì là miễn phí cả hãy nhập số tiền nhập vào lớn hơn 0 !!!");
                }

            }catch (NumberFormatException e){
                System.err.println("Vui lòng nhập vào số!");
            }
        }
        inputExportPrice(scanner);
        do {
            try {
                System.out.println("Nhập vào số lượng còn lại của sản phẩm !!!");
                this.quantity = Integer.parseInt(scanner.nextLine());
                if (this.quantity>=0){
                    break;
                }else {
                    System.err.println("Vui lòng nhập số lượng hơn hoặc bằng 0");
                }

            }catch (NumberFormatException e){
                System.err.println("Vui lòng nhập vào số !!!");
            }
        }while (true);
        inputAthorList(scanner,authorList,listBook);
        do {
            System.out.println("Nhập vào tiêu đề của sách !");
            this.title = scanner.nextLine();
            if (this.title.trim().length()>=30 && this.title.trim().length()<=100){
                break;
            }else {
                System.err.println("Vui lòng nhập tiêu đề từ 30-100 ký tự !!!");
            }
        }while (true);
        System.out.println("Nhập vào nội dung của sách: ");
        this.content = scanner.nextLine();
        System.out.println("Nhập vào tên nhà xuất bản: ");
        this.publishing = scanner.nextLine();
        inputBookStatus(scanner);
    }
    public String inputBookStatus (Scanner scanner){
        do {
            System.out.println("Nhập vào trạng thái của Sách (Vui lòng nhập: Còn hàng/Sắp hết hàng/Hết hàng)");
            String status = scanner.nextLine();
            if (status.trim().length()!=0){
                if (status.equals("Còn hàng")){
                    this.bookStatus = "Còn hàng" ;
                    break;
                }else if (status.equals("Sắp hết hàng")){
                    this.bookStatus = "Sắp hết hàng";
                    break;
                }else if (status.equals("Hết hàng")){
                    this.bookStatus = "Hết hàng ";
                    break;
                }else {
                    System.err.println("Vui lòng nhập: Còn hàng/Sắp hết hàng/Hết hàng");
                }
            }else {
                System.err.println("Không được để trống !!!");

            }
        }while (true);
        return this.bookStatus;

    }
    public void inputAthorList (Scanner scanner, List<Author> authorList, List<Book> listBook  ){
        int number = -1;
        int count =0;
        do {
            try {
                System.out.println("Nhập vào số lượng tác giả ");
                number =  Integer.parseInt(scanner.nextLine());
                for (int i = 0; i < number; i++) {
                    while (true){
                        System.out.println("Nhập vào tác giả thứ " + (i+1));
                        String inputAuthorName = scanner.nextLine();
                        boolean checkValidateAuthorName = false;
                        for (Author author : authorList) {
                            if (author.getAuthorName().toLowerCase().equals(inputAuthorName.toLowerCase())){
                                listAuthor.add(author);
                                count++;
                                checkValidateAuthorName = true;
                                break;
                            }
                        }
                        if (checkValidateAuthorName){
                            break;
                        }else {
                            System.err.println("Không tìm thấy tên tác giả này !!!");
                            System.out.println("Bạn có muốn muốn thêm tác giả này vào danh sách tác giả không ?");
                            System.out.println("1. Có");
                            System.out.println("2(Phím bất kì) . Không (Nhập lại tên khác) ");
                            String choice = scanner.nextLine();
                            if (choice.equals("1")){
                                Author author = new Author();
                                author.inputData(scanner,authorList,listBook);
                                authorList.add(author);
                                listAuthor.add(author);
                                count++;
                                break;
                            }
                        }
                    }
                }
                if (number == count){
                    break;
                }
            }catch (NumberFormatException e){
                System.err.println("Vui lòng nhập vào số !!! ");
            }catch (NullPointerException ex2){
                ex2.printStackTrace();
            }
        }while (true);
    }
    public String inputBookId (Scanner scanner, List<Book> listbook){
        String bookId;
        do {
            System.out.println("Nhập vào mã Sách");
            bookId = scanner.nextLine();
            if (bookId.trim().length()==5){
                if (bookId.charAt(0) == 'B'){
                    boolean checkId = false;
                    for (Book book :listbook) {
                        if (book.getBookId().equals(bookId)){
                            checkId = true;
                            break;
                        }
                    }
                    if (!checkId){
                        this.bookId = bookId;
                        break;
                    }else {
                        System.err.println("Đã có mã này trong hệ thống vui lòng nhập mã khác !!!");
                    }
                }else {
                    System.err.println("Mã Sách phải bắt đầu bằng ký tự 'B' ! ");
                }
            }else {
                System.err.println("Mã sách phải gồm 5 Ký tự!");
            }
        }while (true);
        return this.bookId ;
    }
    public String inputBookName (Scanner scanner){
        do {
            System.out.println("Nhập vào tên của Sách ");
            String bookName = scanner.nextLine();
            if (bookName.trim().length()>=6 && bookName.trim().length()<=100){
                this.bookName = bookName;
                break;
            }else {
                System.err.println("Tên phải từ 6 - 100 ký tự");
            }
        }while (true);
        return this.bookName;
    }
    public float inputExportPrice (Scanner scanner){
        float export = 0;
        do {
            try {
                System.out.println("Nhập vào giá bán Sách");
                export = Float.parseFloat(scanner.nextLine());
            }catch (NumberFormatException e){
                System.err.println("Vui lòng nhập vào số !!!");
            }
            if (export>= this.importPrice*1.2f){
                this.exportPrice = export;
                break;
            }else {
                System.err.println("Giá bán phải cao hơn giá nhập ít nhất 20% !!! ");
            }
        }while (true);
        return this.exportPrice;
    }

    @Override
    public void displayData() {
        String profit = "";
        if (this.profit==0){
            profit = "Chưa tính";
        }
        else {
            profit = this.profit +"";
        }
        System.out.println("*--------------------------------------------------------------------------------------------------------------------------*");
        System.out.printf("|   Tên Sách: %55s\n", this.bookName);
        System.out.printf("|   Mã ID: %-10s Giá nhập: %-15f Giá Bán: %-15f Lợi Nhuận: %-15s Sổ lượng còn lại: %-5d\n",this.bookId,this.importPrice,this.exportPrice,profit,this.quantity);
        System.out.print("|   Danh sách tác giả: ");
        try {
            for (Author author :listAuthor) {
                System.out.printf(author.getAuthorName() + ",\t");
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        System.out.print("\n");
        System.out.println("|   Tiêu đề sách: " + this.title);
        System.out.printf("|   Nhà xuất bản: %-20s Trạng thái sách: %-30s\n", this.publishing,this.bookStatus);
        System.out.println("*--------------------------------------------------------------------------------------------------------------------------*");
    }

    @Override
    public void getData(List<Author> list, List<Book> listBook,String path) {
        File file = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            file = new File(path);
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            List<Book> bookList = (List<Book>) ois.readObject();
            listBook.addAll(bookList);
//            listBook = (List<Book>) ois.readObject();
        } catch (IOException | ClassNotFoundException ex1) {
            ex1.printStackTrace();
        } finally {
            try {
                if (ois!=null){
                    ois.close();
                }
                if (fis!=null){
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
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
            oos.writeObject(listBook);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
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
    public void calProfit(){
        this.profit = this.exportPrice - this.importPrice;
    }
    public void saleBook(Scanner scanner){
        do {
            try {
                System.out.println("Nhập vào số lượng sách bán đi: ");
                int number = Integer.parseInt(scanner.nextLine());
                if (number>this.quantity){
                    System.err.println("Số sách trong cửa hàng còn lại không đủ số lượng !!! ");
                    System.out.println("Số lượng còn lại: " +this.quantity);

                }else if (number>0){
                    this.quantity-=number;
                    break;
                }else {
                    System.err.println("Vui lòng nhập vào số lượng lớn hơn 0 !!!");;
                }
            }catch (NumberFormatException e){
                System.err.println("Vui lòng nhập vào số !!!");
            }
        }while (true);
    }
    public void displayDataForUser (){
        System.out.println("*--------------------------------------------------------------------------------------------------------------------------*");
        System.out.printf("|   Tên Sách: %55s\n", this.bookName);
        System.out.printf("|   Mã ID: %-10s  Giá Bán: %-15f  Sổ lượng còn lại: %-5d\n",this.bookId,this.exportPrice,this.quantity);
        System.out.print("|   Danh sách tác giả: ");
        try {
            for (Author author :listAuthor) {
                System.out.printf(author.getAuthorName() + ",\t");
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        System.out.print("\n");
        System.out.println("|   Tiêu đề sách: " + this.title);
        System.out.printf("|   Nhà xuất bản: %-20s Trạng thái sách: %-30s\n", this.publishing,this.bookStatus);
        System.out.println("*--------------------------------------------------------------------------------------------------------------------------*");
    }
}
