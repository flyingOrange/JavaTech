package designPattern.iterator;

import java.util.Iterator;
import java.util.List;

public class MainClass {
    public static void main(String[] args) {
        BookList bookList = new BookList();
        BookListIterator bli = new BookListIterator();
     
        Book book1 = new Book("xxxxx","java编程思想",49.9);
        Book book2 = new Book("yyyyy","设计模式",35.9);
        Book book3 = new Book("zzzzz","redis实战",32);
        Book book4 = new Book("ccccc","docker实战",40.0);
        bookList.addBook(book1);
        bookList.addBook(book2);
        bookList.addBook(book3);
        bookList.addBook(book4);
        bli.addBook(book1);
        bli.addBook(book2);
        bli.addBook(book3);
        bli.addBook(book4);
        
        //遍历方法一
        while(bookList.hasNext()) {
            Book book = bookList.getNext();
            System.out.println(book);
        }
        System.out.println("-------------------------------------");
        //遍历方法二
        //暴漏了底层数据
        List<Book> list = bookList.getBookList();
        for(Book book : list) {
            System.out.println(book);
        }
        System.out.println("-------------------------------------");
        Iterator iterator = bli.iterator();
        while(iterator.hasNext()) {
            Book book = (Book)iterator.next();
            System.out.println(book);
        }
        
    }

}
