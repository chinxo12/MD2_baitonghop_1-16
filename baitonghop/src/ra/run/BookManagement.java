package ra.run;

import ra.entity.Author;
import ra.entity.Book;
import ra.entity.User;

import java.util.*;

public class  BookManagement {
    static List<Author> listAuthor = new ArrayList<>();
    static List<Book> listBook = new ArrayList<>();
    static List<User> userList = new ArrayList<>();
    static String pathAuthor = "D:\\MD 2\\bai tong hop 1-16\\Author.txt";
    static String pahtBooks = "D:\\MD 2\\bai tong hop 1-16\\books.txt";
    static String pathUser = "D:\\MD 2\\bai tong hop 1-16\\User.txt";
    static List<User> userLoginThisTime = new ArrayList<>();
    static List<Book> cartList = new ArrayList<>();

    public static void main(String[] args) {
//        User admin = new User("admin1","admin1",false);
//        userList.add(admin);
//        User user1 = new User("chinxo26", "123456", true);
//        userList.add(user1);
        User user = new User();
        user.getUserData(pathUser, userList);
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("*--------------------------------------------------------------------------------------------------------------------------*");
            System.out.println("|                            *************** TIỆM SÁCH BẤT ỔN **********************                                       |");
            System.out.println("|  1. Đăng Nhập                                                                                                            |");
            System.out.println("|  2. Đăng Ký                                                                                                              |");
            System.out.println("|  3. Thoát                                                                                                                |");
            System.out.println("*--------------------------------------------------------------------------------------------------------------------------*");
            System.out.print("Lựa chọn của bạn: ");
            int choice = 0;
            System.out.print("\n");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ex1) {
                System.err.println("Vui lòng nhập vào số !!! ");
            }
            switch (choice) {
                case 1:
                    boolean checkLogin = loginMenu(scanner);
                    if (checkLogin) {
                        userMenu(scanner);
                    } else {
                        BookManagement.loginAdmin();
                    }
                    break;
                case 2:
                    register(scanner);
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng nhập 1 - 3 !!! ");
            }
        } while (true);
    }

    public static boolean loginMenu(Scanner scanner) {
        String userName = "";
        String passWord = "";
        boolean checkLogin = true;
        do {
            while (true) {
                System.out.println("Nhập vào tên Đăng nhập");
                userName = scanner.nextLine();
                if (userName.trim().length() >= 6) {
                    break;
                } else {
                    System.err.println("Tên đăng nhập lớn hơn 6 ký tự !!! ");
                }
            }
            while (true) {
                System.out.println("Nhập vào Mật khẩu: ");
                passWord = scanner.nextLine();
                if (passWord.trim().length() >= 6) {
                    break;
                } else {
                    System.err.println("Password lớn hơn 6 ký tự !!! ");
                }
            }
            boolean checkExit = false;
            for (User user : userList) {
                if (user.getUserName().equals(userName) && user.getPassword().equals(passWord)) {
                    checkExit = true;
                    if (user.isCheckUser()) {
                        userLoginThisTime.add(user);
                        break;
                    } else {
                        checkLogin = false;
                        break;
                    }
                }
            }
            if (checkExit) {
                break;
            } else {
                System.err.println("Tên tài khoản hoặc mật khẩu không đúng !!!");
            }
        } while (true);
        return checkLogin;
    }

    public static void register(Scanner scanner) {
        User user = new User();
        user.register(scanner, userList);
        userList.add(user);
        user.writeUser(pathUser, userList);
    }

    public static void loginAdmin() {
        Author author = new Author();
        author.getData(listAuthor, listBook, pathAuthor);
        Book book = new Book();
        book.getData(listAuthor, listBook, pahtBooks);
        User user = new User();
        Scanner scanner = new Scanner(System.in);
        boolean checkExit = true;
        do {
            System.out.println("*--------------------------------------------------------------------------------------------------------------------------*");
            System.out.println("|                           ******************** QUẢN LÝ CỬA HÀNG SÁCH ***************                                     |");
            System.out.println("|  1. Quản lý tác giả                                                                                                      |");
            System.out.println("|  2. Quản lý sách                                                                                                         |");
            System.out.println("|  3. Xóa User                                                                                                             |");
            System.out.println("|  4. Tạo thêm admin User                                                                                                  |");
            System.out.println("|  5. Thoát                                                                                                                |");
            System.out.println("*--------------------------------------------------------------------------------------------------------------------------*");
            System.out.print("Lựa chọn của bạn: ");
            System.out.print("\n");
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException ex1){
                System.err.println("Nhập váo số !!!");
            }
            switch (choice) {
                case 1:
                    authorMenu(scanner);
                    break;
                case 2:
                    bookMenu(scanner);
                    break;
                case 3:
                    deleteUser(scanner);
                    user.writeUser(pathUser, userList);
                    break;
                case 4:
                    addAdminUser(scanner);
                    user.writeUser(pathUser, userList);
                    break;
                case 5:
                    checkExit = false;
                    break;
                default:
                    System.err.println("Vui lòng nhập từ 1 -3 ");
            }
        } while (checkExit);
    }

    public static void authorMenu(Scanner scanner) {
        boolean exitMenu = true;
        Author author = new Author();
        Book book = new Book();
        do {
            System.out.println("*--------------------------------------------------------------------------------------------------------------------------*");
            System.out.println("|                    ******************** QUẢN LÝ TÁC GIẢ ***********************                                          |");
            System.out.println("|  1. Danh sách tác giả                                                                                                    |");
            System.out.println("|  2. Thêm các tác giả                                                                                                     |");
            System.out.println("|  3. Cập nhật thông tin tác giả                                                                                           |");
            System.out.println("|  4. Cập nhật trạng thái tác giả                                                                                          |");
            System.out.println("|  5. Thoát                                                                                                                |");
            System.out.println("*--------------------------------------------------------------------------------------------------------------------------*");
            System.out.print("Lựa chọn của bạn: ");
            System.out.print("\n");
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ex1) {
                System.err.println("Vui nhập vào số từ 1 - 5");
            }
            switch (choice) {
                case 1:
                    displayAuthor();
                    break;
                case 2:
                    inputAuthors(scanner);
                    author.insertData(listAuthor, listBook, pathAuthor);
                    break;
                case 3:
                    updateAuthorInformationById(scanner);
                    author.insertData(listAuthor, listBook, pathAuthor);
                    break;
                case 4:
                    updateAuthorStatusByAuthorName(scanner);
                    author.insertData(listAuthor, listBook, pathAuthor);
                    break;
                case 5:
                    exitMenu = false;
                    break;
                default:
                    System.err.println("Vui lòng nhập từ 1 - 5 !!!");
            }
        } while (exitMenu);
    }

    public static void bookMenu(Scanner scanner) {
        boolean exitMenu = true;
        Author author = new Author();
        Book book = new Book();
        do {
            System.out.println("*--------------------------------------------------------------------------------------------------------------------------*");
            System.out.println("|                            ******************** QUẢN LÝ SÁCH *************************                                   |");
            System.out.println("|  1. Danh sách sách                                                                                                       |");
            System.out.println("|  2. Thêm các sách                                                                                                        |");
            System.out.println("|  3. Cập nhật thông tin sách                                                                                              |");
            System.out.println("|  4. Cập nhật trạng thái sách                                                                                             |");
            System.out.println("|  5. Tính lợi nhuận sách                                                                                                  |");
            System.out.println("|  6. Sắp xếp sách theo giá bán tăng dần                                                                                   |");
            System.out.println("|  7. Tìm kiếm sách theo tên sách, tên tác giả                                                                             |");
            System.out.println("|  8. Bán sách                                                                                                             |");
            System.out.println("|  9. Thoát                                                                                                                |");
            System.out.println("*--------------------------------------------------------------------------------------------------------------------------*");
            System.out.print("Lựa chọn của bạn: ");
            System.out.print("\n");
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ex1) {
                System.err.println("Vui nhập vào số từ 1 - 9");
            }
            switch (choice) {
                case 1:
                    displayBooks();
                    break;
                case 2:
                    inputBooks(scanner);
                    book.insertData(listAuthor, listBook, pahtBooks);
                    author.insertData(listAuthor, listBook, pathAuthor);
                    break;
                case 3:
                    updateBookInformationById(scanner);
                    book.insertData(listAuthor, listBook, pahtBooks);
                    break;
                case 4:
                    updateBookStatusByBookName(scanner);
                    book.insertData(listAuthor, listBook, pahtBooks);
                    break;
                case 5:
                    calProfit();
                    book.insertData(listAuthor, listBook, pahtBooks);
                    break;
                case 6:
                    softByExportPrice();
                    break;
                case 7:
                    searchBookByNameOrAuthorName(scanner);
                    break;
                case 8:
                    saleBook(scanner);
                    book.insertData(listAuthor, listBook, pahtBooks);
                    break;
                case 9:
                    exitMenu = false;
                    break;
                default:
                    System.err.println("Vui lòng nhập từ 1 - 9 !!!");
            }
        } while (exitMenu);
    }

    public static void userMenu(Scanner scanner) {
        boolean logIn = true;
        do {
            Author author = new Author();
            author.getData(listAuthor, listBook, pathAuthor);
            Book newBook = new Book();
            newBook.getData(listAuthor, listBook, pahtBooks);
            System.out.println("*--------------------------------------------------------------------------------------------------------------------------*");
            System.out.println("|                           ******************* CỬA HÀNG SÁCH BẤT ỔN *****************                                     |");
            System.out.println("|  1. Danh sách tác giả.                                                                                                   |");
            System.out.println("|  2. Danh sách sách.                                                                                                      |");
            System.out.println("|  3. Sắp xếp sách theo giá bán tăng dần.                                                                                  |");
            System.out.println("|  4. Tìm kiếm sách theo tên sách, tên tác giả.                                                                            |");
            System.out.println("|  5. Đổi mật khẩu.                                                                                                        |");
            System.out.println("|  6. Đăng xuất                                                                                                            |");
            System.out.println("*--------------------------------------------------------------------------------------------------------------------------*");
            System.out.print("lựa chọn của bạn: ");
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ex1) {
                System.err.println("Vui lòng nhập vào số !!!");
            }
            switch (choice) {
                case 1:
                    displayAuthor();
                    break;
                case 2:
                    for (Book book : listBook) {
                        book.displayDataForUser();
                    }
                    break;
                case 3:
                    softByExportPrice();
                    break;
                case 4:
                    searchBookByNameOrAuthorNameForUser(scanner);
                    break;
                case 5:
                    changePassword(scanner);
                    break;
                case 6:
                    userLoginThisTime.clear();
                    logIn = false;
                    break;
                default:
                    System.err.println("vui lòng nhập từ 1-6 !!!");

            }
        } while (logIn);
    }

    public static void displayAuthor() {
        System.out.printf("%-20s%-30s%-20s\n", "Mã tác giả", "Tên tác giả", "Trạng thái");
        for (Author author : listAuthor) {
            author.displayData();
        }
    }

    public static void inputAuthors(Scanner scanner) {
        int number = 0;
        do {
            System.out.println("Nhập vào số lượng tác giả bạn muốn thêm lần này: ");
            try {
                number = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException ex1) {
                System.err.println("Vui lòng nhập vào số !!!");
            }
        } while (true);

        for (int i = 0; i < number; i++) {
            System.out.println("Nhập vào tác giả thứ " + (i + 1));
            Author author = new Author();
            author.inputData(scanner, listAuthor, listBook);
            listAuthor.add(author);
        }
    }

    public static void updateAuthorInformationById(Scanner scanner) {
        int number = -1;
        do {
            try {
                System.out.println("Nhập vào Mã tác giả  cần cập nhật lại thông tin !!! ");
                number = Integer.parseInt(scanner.nextLine());
                if (number > listAuthor.size()) {
                    System.err.println("Vui lòng nhập mã nhỏ hơn " + listAuthor.size());
                } else if (number > 0) {
                    break;
                } else {
                    System.err.println("Vui lòng nhập số lớn hơn 0");
                }
            } catch (NumberFormatException e) {
                System.err.println("Vui lòng nhập vào số !!!");
            }
        } while (true);

        System.out.println("Bạn có muốn sửa tên tác giả này không? Tên cũ : " + listAuthor.get(number - 1).getAuthorName());
        System.out.println("1. Có");
        System.out.println("2(hoặc bấm phím bất kỳ). Không");
        String choice = scanner.nextLine();
        if (choice.equals("1")) {
            listAuthor.get(number - 1).setAuthorName(listAuthor.get(number - 1).inputAuthorName(scanner, listAuthor));
        }
        System.out.println("Bạn có muốn sửa trạng thái tác giả này không? Tên cũ : " + listAuthor.get(number - 1).displayStatus());
        System.out.println("1. Có");
        System.out.println("2(hoặc bấm phím bất kỳ). Không");
        choice = scanner.nextLine();
        if (choice.equals("1")) {
            listAuthor.get(number - 1).setAuthorStatus(listAuthor.get(number - 1).inputAuthorStatus(scanner));
        }
    }

    public static void updateAuthorStatusByAuthorName(Scanner scanner) {
        do {
            System.out.println("Nhập vào tên tác giả cần thay đổi trạng thái: ");
            String searchName = scanner.nextLine();
            if (searchName.trim().length() != 0) {
                boolean checkName = false;
                for (Author author : listAuthor) {
                    if (author.getAuthorName().toLowerCase().equals(searchName.toLowerCase())) {
                        author.setAuthorStatus(author.inputAuthorStatus(scanner));
                        checkName = true;
                        break;
                    }
                }
                if (!checkName) {
                    System.err.println("Không có tên tác giả này trong danh sách ");
                } else {
                    break;
                }
            } else {
                System.err.println("Không được để trống!!! Nhập lại !!! ");
            }

        } while (true);
    }

    public static void displayBooks() {
        for (Book book : listBook) {
            book.displayData();
        }
    }

    public static void inputBooks(Scanner scanner) {
        int number = 0;
        do {
            try {
                System.out.println("Nhập vào số lượng sách bạn muốn thêm lần này: ");
                number = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.err.println("Vui lòng nhập vào số !!!");
            }
        } while (true);
        for (int i = 0; i < number; i++) {
            System.out.println("Nhập vào thông tin sách thứ: " + (i + 1));
            Book book = new Book();
            book.inputData(scanner, listAuthor, listBook);
            listBook.add(book);
        }
    }

    public static void updateBookInformationById(Scanner scanner) {
        String searchId;
        int indexSearch = -1;
        do {
            System.out.println("Nhập vào mã sách cần sửa: ");
            searchId = scanner.nextLine();
            if (searchId.trim().length() == 5) {
                if (searchId.charAt(0) == 'B') {
                    boolean checkId = false;
                    for (int i = 0; i < listBook.size(); i++) {
                        if (listBook.get(i).getBookId().equals(searchId)) {
                            indexSearch = i;
                            checkId = true;
                            break;
                        }
                    }
                    if (checkId) {
                        break;
                    } else {
                        System.err.println("Không có mã sách này trong danh sách !!!");
                        System.out.println("Bạn có muốn nhập lại không?");
                        System.out.println("1. Có");
                        System.out.println("2. Bấm phím bất kỳ để thoát !");
                        String choice = scanner.nextLine();
                        if (!choice.equals("1")) {
                            break;
                        }
                    }
                } else {
                    System.err.println("Mã sách bắt đầu bằng ký tự 'B' (Viết hoa)");
                }

            } else {
                System.err.println("Mã Sách gồm 5 ký tự !!!");
            }
        } while (true);
        if (indexSearch != -1) {
            System.out.println("Bạn có muốn thay đổi tên sách không? Tên cũ:  " + listBook.get(indexSearch).getBookName());
            System.out.println("1. Có");
            System.out.println("2. Bấm phím bất kỳ để tiếp tục !");
            String choice = scanner.nextLine();
            if (choice.equals("1")) {
                listBook.get(indexSearch).setBookName(listBook.get(indexSearch).inputBookName(scanner));
            }
            System.out.println("Bạn có muốn sửa lại giá nhập vào không ? Giá cũ: " + listBook.get(indexSearch).getImportPrice());
            System.out.println("1. Có");
            System.out.println("2. Bấm phím bất kỳ để tiếp tục !");
            choice = scanner.nextLine();
            float price;
            if (choice.equals("1")) {
                while (true) {
                    try {
                        price = Float.parseFloat(scanner.nextLine());
                        break;
                    } catch (NumberFormatException e) {
                        System.err.println("Vui lòng nhập vào số !!!");
                    }
                }
                listBook.get(indexSearch).setImportPrice(price);
            }
            System.out.println("Bạn có muốn sửa lại giá bán ra không ? Giá cũ: " + listBook.get(indexSearch).getExportPrice());
            System.out.println("1. Có");
            System.out.println("2. Bấm phím bất kỳ để tiếp tục !");
            choice = scanner.nextLine();
            if (choice.equals("1")) {
                listBook.get(indexSearch).setExportPrice(listBook.get(indexSearch).inputExportPrice(scanner));
            }
            System.out.println("Bạn có muốn sửa lại số lượng sách còn lại không? số lượng cũ: " + listBook.get(indexSearch).getQuantity());
            System.out.println("1. Có");
            System.out.println("2. Bấm phím bất kỳ để tiếp tục !");
            choice = scanner.nextLine();

            if (choice.equals("1")) {
                while (true) {
                    try {
                        int quantity = Integer.parseInt(scanner.nextLine());
                        listBook.get(indexSearch).setQuantity(quantity);
                        break;
                    } catch (NumberFormatException e) {
                        System.err.println("Vui lòng nhập vào số !!!");
                    }
                }
            }
            System.out.println("Bạn có muốn sửa lại giá bán ra không? \n Giá cũ: " + listBook.get(indexSearch).getTitle());
            System.out.println("1. Có");
            System.out.println("2. Bấm phím bất kỳ để tiếp tục !");
            choice = scanner.nextLine();
            if (choice.equals("1")) {
                while (true) {
                    String title = scanner.nextLine();
                    if (title.trim().length() != 0) {
                        if (title.trim().length() >= 30 && title.trim().length() <= 100) {
                            listBook.get(indexSearch).setTitle(title);
                        } else {
                            System.err.println("Vui lòng nhập tiêu đề từ 30-100 ký tự !!!");
                        }

                    } else {
                        System.err.println("Không được để trống !!!");
                    }
                }
            }
            System.out.println("Bạn có muốn sửa lại trạng thái sách này không? \n Giá cũ: " + listBook.get(indexSearch).getBookStatus());
            System.out.println("1. Có");
            System.out.println("2. Bấm phím bất kỳ để tiếp tục !");
            choice = scanner.nextLine();
            if (choice.equals("1")) {
                listBook.get(indexSearch).setBookStatus(listBook.get(indexSearch).inputBookStatus(scanner));
            }
        }
    }

    public static void updateBookStatusByBookName(Scanner scanner) {
        do {
            System.out.println("Nhập vào tên sách bạn muốn thay đổi trạng thái:");
            String bookName = scanner.nextLine();
            if (bookName.trim().length() >= 6 && bookName.trim().length() <= 100) {
                boolean checkSearchName = false;
                for (Book book : listBook) {
                    if (book.getBookName().toLowerCase().equals(bookName.toLowerCase())) {
                        book.setBookStatus(book.inputBookStatus(scanner));
                        checkSearchName = true;
                        break;
                    }
                }
                if (checkSearchName) {
                    break;
                } else {
                    System.err.println("Không tìm thấy tên Sách này trong danh sách được quản lý !!!");
                    System.out.println("Bạn muốn thử tìm lại với tên khác không ?");
                    System.out.println("1. Có");
                    System.out.println("2. Bấm phím bất kỳ để thoát !");
                    String choice = scanner.nextLine();
                    if (!choice.equals("1")) {
                        break;
                    }
                }
            } else {
                System.err.println("Vui lòng nhập tên từ 6-100 ký tự !!!");
            }
        } while (true);
    }

    public static void calProfit() {
        for (Book book : listBook) {
            book.calProfit();
        }
        System.out.println("Đã tính xong lợi nhuận !!! ");
    }

    public static void softByExportPrice() {
        Collections.sort(listBook, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return (int) (o1.getExportPrice() - o2.getExportPrice());
            }
        });
        System.out.println("Đã sắp xếp xong theo giá bán tăng dần ! ");
    }

    public static void searchBookByNameOrAuthorName(Scanner scanner) {
        do {
            System.out.println("Nhập vào tên sách hoặc tên tác giả:  ");
            String seachKey = scanner.nextLine();
            if (seachKey.trim().length() != 0) {
                boolean checkExitSearch = false;
                for (Book book : listBook) {
                    for (Author author : book.getListAuthor()) {
                        if (book.getBookName().trim().toLowerCase().contains(seachKey.toLowerCase()) ||
                                author.getAuthorName().trim().toLowerCase().contains(seachKey.toLowerCase())) {
                            book.displayData();
                            checkExitSearch = true;
                        }
                    }
                }
                if (checkExitSearch) {
                    break;
                } else {
                    System.err.println("Không tìm thấy tên tác giả hay tên sách này !!! ");
                    System.out.println("Bạn muốn thử tìm lại với tên khác không ?");
                    System.out.println("1. Có");
                    System.out.println("2. Bấm phím bất kỳ để thoát !");
                    String choice = scanner.nextLine();
                    if (!choice.equals("1")) {
                        break;
                    }
                }
            } else {
                System.err.println("Không được để trống !!!");
            }
        } while (true);
    }

    public static void saleBook(Scanner scanner) {
        do {
            System.out.println("Nhập vào tên sách cần bán: ");
            String bookName = scanner.nextLine();
            if (bookName.trim().length() != 0) {
                boolean checkExit = false;
                for (Book book : listBook) {
                    if (book.getBookName().toLowerCase().equals(bookName.toLowerCase())) {
                        book.saleBook(scanner);
                        checkExit = true;
                        break;
                    }
                }
                if (checkExit) {
                    break;
                } else {
                    System.err.println("Không tìm thấy tên sách này trong danh sách !!!");
                    System.out.println("Bạn muốn thử tìm lại với tên khác không ?");
                    System.out.println("1. Có");
                    System.out.println("2. Bấm phím bất kỳ để thoát !");
                    String choice = scanner.nextLine();
                    if (!choice.equals("1")) {
                        break;
                    }
                }
            } else {
                System.err.println("Vui lòng  không để trống !!!");
            }

        } while (true);
    }

    public static void searchBookByNameOrAuthorNameForUser(Scanner scanner) {
        do {
            System.out.println("Nhập vào tên sách hoặc tên tác giả:  ");
            String seachKey = scanner.nextLine();
            if (seachKey.trim().length() != 0) {
                boolean checkExitSearch = false;
                for (Book book : listBook) {
                    for (Author author : book.getListAuthor()) {
                        if (book.getBookName().trim().toLowerCase().contains(seachKey.toLowerCase()) ||
                                author.getAuthorName().trim().toLowerCase().contains(seachKey.toLowerCase())) {
                            book.displayDataForUser();
                            checkExitSearch = true;
                        }
                    }
                }
                if (checkExitSearch) {
                    break;
                } else {
                    System.err.println("Không tìm thấy tên tác giả hay tên sách này !!! ");
                    System.out.println("Bạn muốn thử tìm lại với tên khác không ?");
                    System.out.println("1. Có");
                    System.out.println("2. Bấm phím bất kỳ để thoát !");
                    String choice = scanner.nextLine();
                    if (!choice.equals("1")) {
                        break;
                    }
                }
            } else {
                System.err.println("Không được để trống !!!");
            }
        } while (true);
    }

    public static void changePassword(Scanner scanner) {
        userLoginThisTime.get(0).setPassword(userLoginThisTime.get(0).inputPassword(scanner));
    }
    public static void deleteUser (Scanner scanner){
        System.out.println("Danh sách User trong hệ thống");
        System.out.printf("%-5s%-30s\n","STT","Tên User");
        for (int i = 0;i<userList.size();i++) {
            System.out.printf("%-5d%-30s\n",i+1,userList.get(i).getUserName());
        }
        System.out.println("Chọn User cần xóa");
        System.out.println("Nhập vào 0 để thoát !");
        int choice = -1;
        while (true){
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice>0&& choice<=userList.size()){
                    break;
                }else if (choice == 0){
                    break;
                }
                else {
                    System.err.println("Vui lòng nhập từ 1 đên " + userList.size());
                }
            }catch (NumberFormatException e){
                System.err.println("Vui lòng nhập vào số !!!");
            }
        }
        if (choice>0){
            userList.remove(choice-1);
        }
        System.out.println("Đã xóa thành công !");
    }
    public static void addAdminUser (Scanner scanner){
        User user = new User();
        user.register(scanner, userList);
        user.setCheckUser(false);
        userList.add(user);
    }
    public static void addToCart (Scanner scanner){
        do {
            System.out.println("Nhập vào tên sách bạn  muốn thêm vào giỏ hàng ");
            String name = scanner.nextLine();

            if (name.trim().length()!=0){
                boolean checkSearch = false;
                for (Book book :listBook) {
                    if (book.getBookName().toLowerCase().contains(name.toLowerCase())){
                        book.displayDataForUser();
                    }else {
                        System.err.println("Không tìm thấy sản phẩm này vui lòng nhập tên khác! ");
                    }
                }

            }else {
                System.err.println("Vui lòng  không để trống !!!");
            }
        }while (true);


    }
}