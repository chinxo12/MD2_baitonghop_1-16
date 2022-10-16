package ra.interface1;

import ra.entity.Author;
import ra.entity.Book;

import java.util.List;
import java.util.Scanner;

public interface IBook {

    void inputData(Scanner scanner, List<Author> list, List<Book> listBook);

    void displayData();

     void getData(List<Author> list,List<Book> listBook,String path);

    void insertData(List<Author> listAuthor,List<Book> listBook, String path);
}
