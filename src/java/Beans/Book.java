package Beans;

import java.io.Serializable;
import javax.swing.ImageIcon;

/**
 *
 * @author Ahmed Ashraf
 */
public class Book implements Serializable{
    private int id;
    private String name;
    private String img;

    public Book(int id, String name, String img) {
        this.id = id;
        this.name = name;
        this.img = img;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }
    
    




    
}
