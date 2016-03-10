
import Beans.*;
import DBconnectivity.ManipulateDB;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lenovo
 */
public class Test {

    public static void main(String[] args) {
        
        try { 
            ManipulateDB mdb = new ManipulateDB();
            Category category = new Category(2, "science fiction");
//            mdb.insertCategory(category);
            Book book = new Book();
            book.setBookId(2);
            book.setBookName("book2");
            book.setPrice(200);
            book.setCategory(category);
            mdb.insertBook(book);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    } 
}
