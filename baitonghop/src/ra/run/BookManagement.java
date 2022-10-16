package ra.run;

import com.sun.scenario.effect.impl.sw.java.JSWColorAdjustPeer;
import ra.entity.Author;
import ra.entity.Book;

import java.util.*;

public class  BookManagement {
    static List <Author> listAuthor = new ArrayList<>();
    static List <Book> listBook = new ArrayList<>();
    public static void main(String[] args) {
        Author author = new Author();
        author.getData(listAuthor,listBook,"D:\\MD 2\\bai tong hop 1-16\\baitonghop\\Authors.txt");
        Book book = new Book();
        book.getData(listAuthor,listBook,"D:\\MD 2\\bai tong hop 1-16\\baitonghop\\Books.txt");
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("********************QUẢN LÝ CỬA HÀNG SÁCH***************");
            System.out.println("1. Quản lý tác giả");
            System.out.println("2. Quản lý sách");
            System.out.println("3. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            System.out.print("\n");
            String choice = scanner.nextLine();
            switch (choice){
                case "1":
                    authorMenu(scanner);
                    break;
                case "2":
                    bookMenu(scanner);
                    break;
                case "3":
                    scanner.close();
                    System.exit(0);
                default:
                    System.err.println("Vui lòng nhập từ 1 -3 ");
            }
        }while (true);
    }
    public static void authorMenu (Scanner scanner){
        boolean exitMenu = true;
        Author author = new Author();
        Book book = new Book();
        do {
            System.out.println("********************QUẢN LÝ TÁC GIẢ***********************");
            System.out.println("1. Danh sách tác giả");
            System.out.println("2. Thêm các tác giả");
            System.out.println("3. Cập nhật thông tin tác giả");
            System.out.println("4. Cập nhật trạng thái tác giả");
            System.out.println("5. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            System.out.print("\n");
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException ex1){
                System.err.println("Vui nhập vào số từ 1 - 5");
            }
            switch (choice){
                case 1:
                    displayAuthor();
                    break;
                case 2:
                    inputAuthors(scanner);
                    author.insertData(listAuthor,listBook,"D:\\MD 2\\bai tong hop 1-16\\baitonghop\\Authors.txt");
                    break;
                case 3:
                    updateAuthorInformationById(scanner);
                    author.insertData(listAuthor,listBook,"D:\\MD 2\\bai tong hop 1-16\\baitonghop\\Authors.txt");
                    break;
                case 4:
                    updateAuthorStatusByAuthorName(scanner);
                    author.insertData(listAuthor,listBook,"D:\\MD 2\\bai tong hop 1-16\\baitonghop\\Authors.txt");
                    break;
                case 5:
                    exitMenu = false;
                    break;
                default:
                    System.err.println("Vui lòng nhập từ 1 - 5 !!!");
            }
        }while (exitMenu);
    }
    public static void bookMenu (Scanner scanner) {
        boolean exitMenu = true;
        Author author = new Author();
        Book book = new Book();
        do {
            System.out.println("********************QUẢN LÝ SÁCH*************************");
            System.out.println("1. Danh sách sách");
            System.out.println("2. Thêm các sách");
            System.out.println("3. Cập nhật thông tin sách");
            System.out.println("4. Cập nhật trạng thái sách");
            System.out.println("5. Tính lợi nhuận sách");
            System.out.println("6. Sắp xếp sách theo giá bán tăng dần");
            System.out.println("7. Tìm kiếm sách theo tên sách, tên tác giả");
            System.out.println("8. Bán sách");
            System.out.println("9. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            System.out.print("\n");
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException ex1){
                System.err.println("Vui nhập vào số từ 1 - 9");
            }
            switch (choice){
                case 1:
                    displayBooks();
                    break;
                case 2:
                    inputBooks(scanner);
                    book.insertData(listAuthor,listBook,"BookChange");
                    author.insertData(listAuthor,listBook,"D:\\MD 2\\bai tong hop 1-16\\baitonghop\\Authors.txt");
                    break;
                case 3:
                    updateBookInformationById(scanner);
                    book.insertData(listAuthor,listBook,"D:\\MD 2\\bai tong hop 1-16\\baitonghop\\Books.txt");
                    break;
                case 4:
                    updateBookStatusByBookName(scanner);
                    book.insertData(listAuthor,listBook,"D:\\MD 2\\bai tong hop 1-16\\baitonghop\\Books.txt");
                    break;
                case 5:
                    calProfit();
                    book.insertData(listAuthor,listBook,"D:\\MD 2\\bai tong hop 1-16\\baitonghop\\Books.txt");
                    break;
                case 6:
                    softByExportPrice();
                    break;
                case 7:
                    searchBookByNameOrAuthorName(scanner);
                    break;
                case 8:
                    saleBook(scanner);
                    book.insertData(listAuthor,listBook,"D:\\MD 2\\bai tong hop 1-16\\baitonghop\\Books.txt");
                    break;
                case 9:
                    exitMenu = false;
                    break;
                default:
                    System.err.println("Vui lòng nhập từ 1 - 9 !!!");
            }
        }while (exitMenu);
    }
    public static void displayAuthor () {
        System.out.printf("%-20s%-30s%-20s\n","Mã tác giả","Tên tác giả","Trạng thái");
        for (Author author :listAuthor) {
            author.displayData();
        }
    }
    public static void inputAuthors (Scanner scanner){
        int number = 0;
        do {
            System.out.println("Nhập vào số lượng tác giả bạn muốn thêm lần này: ");
            try {
                number  = Integer.parseInt(scanner.nextLine());
                break;
            }catch (NumberFormatException ex1){
                System.err.println("Vui lòng nhập vào số !!!");
            }
        }while (true);

        for (int i = 0; i < number; i++) {
            System.out.println("Nhập vào tác giả thứ " + (i + 1));
            Author author = new Author();
            author.inputData(scanner,listAuthor,listBook);
            listAuthor.add(author);
        }
    }
    public static void updateAuthorInformationById(Scanner scanner){
        int number = -1;
        do {
            try {
                System.out.println("Nhập vào Mã tác giả  cần cập nhật lại thông tin !!! ");
                number = Integer.parseInt(scanner.nextLine());
                if (number>listAuthor.size()){
                    System.err.println("Vui lòng nhập mã nhỏ hơn " + listAuthor.size());
                }else if (number> 0){
                    break;
                }else {
                    System.err.println("Vui lòng nhập số lớn hơn 0");
                }
            }catch (NumberFormatException e){
                System.err.println("Vui lòng nhập vào số !!!");
            }
        }while (true);

        System.out.println("Bạn có muốn sửa tên tác giả này không? Tên cũ : " + listAuthor.get(number-1).getAuthorName());
        System.out.println("1. Có");
        System.out.println("2(hoặc bấm phím bất kỳ). Không");
        String choice = scanner.nextLine();
        if (choice.equals("1")){
            listAuthor.get(number-1).setAuthorName(listAuthor.get(number-1).inputAuthorName(scanner,listAuthor));
        }
        System.out.println("Bạn có muốn sửa trạng thái tác giả này không? Tên cũ : " + listAuthor.get(number-1).displayStatus());
        System.out.println("1. Có");
        System.out.println("2(hoặc bấm phím bất kỳ). Không");
        choice = scanner.nextLine();
        if (choice.equals("1")){
            listAuthor.get(number-1).setAuthorStatus(listAuthor.get(number-1).inputAuthorStatus(scanner));
        }
    }
    public static void updateAuthorStatusByAuthorName(Scanner scanner){
        do {
            System.out.println("Nhập vào tên tác giả cần thay đổi trạng thái: ");
            String searchName =  scanner.nextLine();
            if (searchName.trim().length()!=0){
                boolean checkName = false;
                for (Author author :listAuthor) {
                    if (author.getAuthorName().toLowerCase().equals(searchName.toLowerCase())){
                        author.setAuthorStatus(author.inputAuthorStatus(scanner));
                        checkName = true;
                        break;
                    }
                }
                if (!checkName){
                    System.err.println("Không có tên tác giả này trong danh sách ");
                }else{
                    break;
                }
            }else {
                System.err.println("Không được để trống!!! Nhập lại !!! ");
            }

        }while (true);
    }
    public static void displayBooks (){
        for (Book book :listBook) {
            book.displayData();
        }
    }
    public static void inputBooks (Scanner scanner){
        int number = 0;
        do {
            try {
                System.out.println("Nhập vào số lượng sách bạn muốn thêm lần này: ");
                number = Integer.parseInt(scanner.nextLine());
                break;
            }catch (NumberFormatException e){
                System.err.println("Vui lòng nhập vào số !!!");
            }
        }while (true);
        for (int i = 0; i < number; i++) {
            System.out.println("Nhập vào thông tin sách thứ: " + (i+1));
            Book book = new Book();
            book.inputData(scanner,listAuthor,listBook);
            listBook.add(book);
        }
    }
    public static void updateBookInformationById(Scanner scanner){
        String searchId;
        int indexSearch = -1;
        do {
            System.out.println("Nhập vào mã sách cần sửa: ");
            searchId = scanner.nextLine();
            if (searchId.trim().length()==5){
                if (searchId.charAt(0)=='B'){
                    boolean checkId = false;
                    for (int i = 0; i < listBook.size(); i++) {
                        if (listBook.get(i).equals(indexSearch)){
                            indexSearch = i;
                            checkId = true;
                            break;
                        }
                    }
                    if (checkId){
                        break;
                    }else {
                        System.err.println("Không có mã sách này trong danh sách !!!");
                        System.out.println("Bạn có muốn nhập lại không?");
                        System.out.println("1. Có");
                        System.out.println("2. Bấm phím bất kỳ để thoát !");
                        String choice = scanner.nextLine();
                        if (!choice.equals("1")){
                            break;
                        }
                    }
                }else {
                    System.err.println("Mã sách bắt đầu bằng ký tự 'B' (Viết hoa)");
                }

            }else {
                System.err.println("Mã Sách gồm 5 ký tự !!!");
            }
        }while (true);
        if (indexSearch!=0){
            System.out.println("Bạn có muốn thay đổi tên sách không? Tên cũ:  " + listBook.get(indexSearch).getBookName());
            System.out.println("1. Có");
            System.out.println("2. Bấm phím bất kỳ để tiếp tục !");
            String choice = scanner.nextLine();
            if (choice.equals("1")){
                listBook.get(indexSearch).setBookName(listBook.get(indexSearch).inputBookName(scanner));
            }
            System.out.println("Bạn có muốn sửa lại giá nhập vào không ? Giá cũ: " + listBook.get(indexSearch).getImportPrice());
            System.out.println("1. Có");
            System.out.println("2. Bấm phím bất kỳ để tiếp tục !");
            choice = scanner.nextLine();
            float price;
            if (choice.equals("1")){
                while (true){
                    try {
                        price = Float.parseFloat(scanner.nextLine());
                        break;
                    }catch (NumberFormatException e){
                        System.err.println("Vui lòng nhập vào số !!!");
                    }
                }
                listBook.get(indexSearch).setImportPrice(price);
            }
            System.out.println("Bạn có muốn sửa lại giá bán ra không ? Giá cũ: " + listBook.get(indexSearch).getExportPrice());
            System.out.println("1. Có");
            System.out.println("2. Bấm phím bất kỳ để tiếp tục !");
            choice = scanner.nextLine();
            if (choice.equals("1")){
                listBook.get(indexSearch).setExportPrice(listBook.get(indexSearch).inputExportPrice(scanner));
            }
            System.out.println("Bạn có muốn sửa lại số lượng sách còn lại không? số lượng cũ: " + listBook.get(indexSearch).getQuantity());
            System.out.println("1. Có");
            System.out.println("2. Bấm phím bất kỳ để tiếp tục !");
            choice = scanner.nextLine();

            if (choice.equals("1")){
                while (true){
                    try {
                        int quantity = Integer.parseInt(scanner.nextLine());
                        listBook.get(indexSearch).setQuantity(quantity);
                        break;
                    }catch (NumberFormatException e){
                        System.err.println("Vui lòng nhập vào số !!!");
                    }
                }
            }
            System.out.println("Bạn có muốn sửa lại giá bán ra không? \n Giá cũ: " + listBook.get(indexSearch).getTitle());
            System.out.println("1. Có");
            System.out.println("2. Bấm phím bất kỳ để tiếp tục !");
            choice = scanner.nextLine();
            if (choice.equals("1")){
                while (true){
                    String title = scanner.nextLine();
                    if (title.trim().length()!=0){
                        if (title.trim().length()>=30 && title.trim().length()<=100){
                            listBook.get(indexSearch).setTitle(title);
                        }else {
                            System.err.println("Vui lòng nhập tiêu đề từ 30-100 ký tự !!!");
                        }

                    }else {
                        System.err.println("Không được để trống !!!");
                    }
                }
            }
            System.out.println("Bạn có muốn sửa lại trạng thái sách này không? \n Giá cũ: " + listBook.get(indexSearch).getBookStatus());
            System.out.println("1. Có");
            System.out.println("2. Bấm phím bất kỳ để tiếp tục !");
            choice = scanner.nextLine();
            if (choice.equals("1")){
                listBook.get(indexSearch).setBookStatus(listBook.get(indexSearch).inputBookStatus(scanner));
            }
        }
    }
    public static void updateBookStatusByBookName (Scanner scanner){
        do {
            System.out.println("Nhập vào tên sách bạn muốn thay đổi trạng thái:");
            String bookName = scanner.nextLine();
            if (bookName.trim().length()>=6 && bookName.trim().length()<=100){
                boolean checkSearchName  = false;
                for (Book book :listBook) {
                    if (book.getBookName().toLowerCase().equals(bookName.toLowerCase())){
                        book.setBookStatus(book.inputBookStatus(scanner));
                        checkSearchName=true;
                        break;
                    }
                }
                if (checkSearchName){
                    break;
                }else {
                    System.err.println("Không tìm thấy tên Sách này trong danh sách được quản lý !!!");
                    System.out.println("Bạn muốn thử tìm lại với tên khác không ?");
                    System.out.println("1. Có");
                    System.out.println("2. Bấm phím bất kỳ để thoát !");
                    String choice = scanner.nextLine();
                    if (!choice.equals("1")){
                        break;
                    }
                }
            }else {
                System.err.println("Vui lòng nhập tên từ 6-100 ký tự !!!");
            }
        }while (true);
    }
    public static void calProfit (){
        for (Book book :listBook) {
            book.calProfit();
        }
        System.out.println("Đã tính xong lợi nhuận !!! ");
    }
    public static void softByExportPrice (){
        Collections.sort(listBook, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return (int) (o1.getExportPrice()-o2.getExportPrice());
            }
        });
        System.out.println("Đã sắp xếp xong theo giá bán tăng dần ! ");
    }
    public static void searchBookByNameOrAuthorName (Scanner scanner){
        do {
            System.out.println("Nhập vào tên sách hoặc tên tác giả:  ");
            String seachKey = scanner.nextLine();
            if (seachKey.trim().length()!=0){
                boolean checkExitSearch = false;
                for (Book book :listBook) {
                    for (Author author :book.getListAuthor()) {
                        if (book.getBookName().trim().toLowerCase().contains(seachKey.toLowerCase()) ||
                            author.getAuthorName().trim().toLowerCase().contains(seachKey.toLowerCase())){
                            book.displayData();
                            checkExitSearch = true;
                        }
                    }
                }
                if (checkExitSearch){
                    break;
                }else {
                    System.err.println("Không tìm thấy tên tác giả hay tên sách này !!! ");
                }
            }else {
                System.err.println("Không được để trống !!!");
            }
        }while (true);
    }
    public static void saleBook (Scanner scanner){
        for (Book book :listBook) {
            book.saleBook(scanner);
        }
    }

}
