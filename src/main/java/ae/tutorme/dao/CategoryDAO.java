package ae.tutorme.dao;

import ae.tutorme.model.Category;

/**
 * Created by almehairbi on 2/25/17.
 */
public interface CategoryDAO  {


    void saveCategory(Category category);

    Category getCategoryById(int id);


}
