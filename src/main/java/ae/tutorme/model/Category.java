package ae.tutorme.model;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Created by almehairbi on 2/23/17.
 */


public enum  Category {

    IT("Information Technology");


    private String category;

    Category(String category) {
        this.category = category;
    }

    @Enumerated(EnumType.STRING)
    public String getCategory() {
        return category;
    }


}

