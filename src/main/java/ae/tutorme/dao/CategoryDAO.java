package ae.tutorme.dao;

import ae.tutorme.dto.CategoryDTO;
import ae.tutorme.model.Category;
import ae.tutorme.model.Topic;

import java.util.List;

/**
 * Created by almehairbi on 2/25/17.
 */
public interface CategoryDAO  {

    Category saveCategory(Category category);

    Category getCategoryById(int id);

    List<Category> getCategories();
    
    CategoryDTO getCategoryDTOById(int id);

    CategoryDTO updateCategory(int id, CategoryDTO topic);
    
    void deleteCategory(int id);
    
    void updateCategory(Category cat);
}
