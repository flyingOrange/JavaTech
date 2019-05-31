package designPattern.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookListIterator {
    private List<Book> bookList;
    private int index;
    
    public BookListIterator() {
        bookList = new ArrayList<Book>();
    }
    

    public List<Book> getBookList() {
        return bookList;
    }

    // 添加book
    public void addBook(Book book) {
        bookList.add(book);
    }

    public Iterator iterator() {
        return new Iter();
    }
    
    private class Iter implements Iterator{

        @Override
        public boolean hasNext() {
            if (index >= bookList.size()) {
                return false;
            }
            return true;
        }

        @Override
        public Object next() {
            return bookList.get(index++);
        }
        
    }

}
