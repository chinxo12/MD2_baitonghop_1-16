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
                System.out.println("Nh???p v??o gi?? nh???p c???a S??ch");
                this.importPrice = Float.parseFloat(scanner.nextLine());
                if (this.importPrice>0){
                    break;
                }else {
                    System.err.println("Tr??n ?????i ch??? c?? g?? l?? mi???n ph?? c??? h??y nh???p s??? ti???n nh???p v??o l???n h??n 0 !!!");
                }

            }catch (NumberFormatException e){
                System.err.println("Vui l??ng nh???p v??o s???!");
            }
        }
        inputExportPrice(scanner);
        do {
            try {
                System.out.println("Nh???p v??o s??? l?????ng c??n l???i c???a s???n ph???m !!!");
                this.quantity = Integer.parseInt(scanner.nextLine());
                if (this.quantity>=0){
                    break;
                }else {
                    System.err.println("Vui l??ng nh???p s??? l?????ng h??n ho???c b???ng 0");
                }

            }catch (NumberFormatException e){
                System.err.println("Vui l??ng nh???p v??o s??? !!!");
            }
        }while (true);
        inputAthorList(scanner,authorList,listBook);
        do {
            System.out.println("Nh???p v??o ti??u ????? c???a s??ch !");
            this.title = scanner.nextLine();
            if (this.title.trim().length()>=30 && this.title.trim().length()<=100){
                break;
            }else {
                System.err.println("Vui l??ng nh???p ti??u ????? t??? 30-100 k?? t??? !!!");
            }
        }while (true);
        System.out.println("Nh???p v??o n???i dung c???a s??ch: ");
        this.content = scanner.nextLine();
        System.out.println("Nh???p v??o t??n nh?? xu???t b???n: ");
        this.publishing = scanner.nextLine();
        inputBookStatus(scanner);
    }
    public String inputBookStatus (Scanner scanner){
        do {
            System.out.println("Nh???p v??o tr???ng th??i c???a S??ch (Vui l??ng nh???p: C??n h??ng/S???p h???t h??ng/H???t h??ng)");
            String status = scanner.nextLine();
            if (status.trim().length()!=0){
                if (status.equals("C??n h??ng")){
                    this.bookStatus = "C??n h??ng" ;
                    break;
                }else if (status.equals("S???p h???t h??ng")){
                    this.bookStatus = "S???p h???t h??ng";
                    break;
                }else if (status.equals("H???t h??ng")){
                    this.bookStatus = "H???t h??ng ";
                    break;
                }else {
                    System.err.println("Vui l??ng nh???p: C??n h??ng/S???p h???t h??ng/H???t h??ng");
                }
            }else {
                System.err.println("Kh??ng ???????c ????? tr???ng !!!");

            }
        }while (true);
        return this.bookStatus;

    }
    public void inputAthorList (Scanner scanner, List<Author> authorList, List<Book> listBook  ){
        int number = -1;
        int count =0;
        do {
            try {
                System.out.println("Nh???p v??o s??? l?????ng t??c gi??? ");
                number =  Integer.parseInt(scanner.nextLine());
                for (int i = 0; i < number; i++) {
                    while (true){
                        System.out.println("Nh???p v??o t??c gi??? th??? " + (i+1));
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
                            System.err.println("Kh??ng t??m th???y t??n t??c gi??? n??y !!!");
                            System.out.println("B???n c?? mu???n mu???n th??m t??c gi??? n??y v??o danh s??ch t??c gi??? kh??ng ?");
                            System.out.println("1. C??");
                            System.out.println("2(Ph??m b???t k??) . Kh??ng (Nh???p l???i t??n kh??c) ");
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
                System.err.println("Vui l??ng nh???p v??o s??? !!! ");
            }catch (NullPointerException ex2){
                ex2.printStackTrace();
            }
        }while (true);
    }
    public String inputBookId (Scanner scanner, List<Book> listbook){
        String bookId;
        do {
            System.out.println("Nh???p v??o m?? S??ch");
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
                        System.err.println("???? c?? m?? n??y trong h??? th???ng vui l??ng nh???p m?? kh??c !!!");
                    }
                }else {
                    System.err.println("M?? S??ch ph???i b???t ?????u b???ng k?? t??? 'B' ! ");
                }
            }else {
                System.err.println("M?? s??ch ph???i g???m 5 K?? t???!");
            }
        }while (true);
        return this.bookId ;
    }
    public String inputBookName (Scanner scanner){
        do {
            System.out.println("Nh???p v??o t??n c???a S??ch ");
            String bookName = scanner.nextLine();
            if (bookName.trim().length()>=6 && bookName.trim().length()<=100){
                this.bookName = bookName;
                break;
            }else {
                System.err.println("T??n ph???i t??? 6 - 100 k?? t???");
            }
        }while (true);
        return this.bookName;
    }
    public float inputExportPrice (Scanner scanner){
        float export = 0;
        do {
            try {
                System.out.println("Nh???p v??o gi?? b??n S??ch");
                export = Float.parseFloat(scanner.nextLine());
            }catch (NumberFormatException e){
                System.err.println("Vui l??ng nh???p v??o s??? !!!");
            }
            if (export>= this.importPrice*1.2f){
                this.exportPrice = export;
                break;
            }else {
                System.err.println("Gi?? b??n ph???i cao h??n gi?? nh???p ??t nh???t 20% !!! ");
            }
        }while (true);
        return this.exportPrice;
    }

    @Override
    public void displayData() {
        String profit = "";
        if (this.profit==0){
            profit = "Ch??a t??nh";
        }
        else {
            profit = this.profit +"";
        }
        System.out.println("*--------------------------------------------------------------------------------------------------------------------------*");
        System.out.printf("|   T??n S??ch: %55s\n", this.bookName);
        System.out.printf("|   M?? ID: %-10s Gi?? nh???p: %-15f Gi?? B??n: %-15f L???i Nhu???n: %-15s S??? l?????ng c??n l???i: %-5d\n",this.bookId,this.importPrice,this.exportPrice,profit,this.quantity);
        System.out.print("|   Danh s??ch t??c gi???: ");
        try {
            for (Author author :listAuthor) {
                System.out.printf(author.getAuthorName() + ",\t");
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        System.out.print("\n");
        System.out.println("|   Ti??u ????? s??ch: " + this.title);
        System.out.printf("|   Nh?? xu???t b???n: %-20s Tr???ng th??i s??ch: %-30s\n", this.publishing,this.bookStatus);
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
                System.out.println("Nh???p v??o s??? l?????ng s??ch b??n ??i: ");
                int number = Integer.parseInt(scanner.nextLine());
                if (number>this.quantity){
                    System.err.println("S??? s??ch trong c???a h??ng c??n l???i kh??ng ????? s??? l?????ng !!! ");
                    System.out.println("S??? l?????ng c??n l???i: " +this.quantity);

                }else if (number>0){
                    this.quantity-=number;
                    break;
                }else {
                    System.err.println("Vui l??ng nh???p v??o s??? l?????ng l???n h??n 0 !!!");;
                }
            }catch (NumberFormatException e){
                System.err.println("Vui l??ng nh???p v??o s??? !!!");
            }
        }while (true);
    }
    public void displayDataForUser (){
        System.out.println("*--------------------------------------------------------------------------------------------------------------------------*");
        System.out.printf("|   T??n S??ch: %55s\n", this.bookName);
        System.out.printf("|   M?? ID: %-10s  Gi?? B??n: %-15f  S??? l?????ng c??n l???i: %-5d\n",this.bookId,this.exportPrice,this.quantity);
        System.out.print("|   Danh s??ch t??c gi???: ");
        try {
            for (Author author :listAuthor) {
                System.out.printf(author.getAuthorName() + ",\t");
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        System.out.print("\n");
        System.out.println("|   Ti??u ????? s??ch: " + this.title);
        System.out.printf("|   Nh?? xu???t b???n: %-20s Tr???ng th??i s??ch: %-30s\n", this.publishing,this.bookStatus);
        System.out.println("*--------------------------------------------------------------------------------------------------------------------------*");
    }
}
