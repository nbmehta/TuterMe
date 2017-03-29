package ae.tutorme.controller;

import ae.tutorme.dao.CategoryDAO;
import ae.tutorme.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by almehairbi on 2/28/17.
 */

@Controller
@RequestMapping(value = "/category")
public class CategoryMVC {

    @Autowired
    private CategoryDAO categoryDAO;


    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public String getCategories(Model model) {

        List<Category> categories = categoryDAO.getCategories();
        model.addAttribute("categories", categories);

        return "";
    }


    @RequestMapping(method = RequestMethod.GET, value = "/{categoryId}")
    public String getCategory(@PathVariable int categoryId, Model model) {

        Category category = categoryDAO.getCategoryById(categoryId);
        model.addAttribute("category", category);
        return "";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveCategory(@ModelAttribute("category") Category category) {

        categoryDAO.saveCategory(category);

        return "";
    }
}

