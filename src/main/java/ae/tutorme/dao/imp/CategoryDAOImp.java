package ae.tutorme.dao.imp;

import ae.tutorme.dao.CategoryDAO;
import ae.tutorme.model.Category;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by almehairbi on 2/25/17.
 */

@Repository
@Transactional
public class CategoryDAOImp implements CategoryDAO{

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void saveCategory(Category category) {
        Session session = sessionFactory.getCurrentSession();
        session.save(category);
        session.flush();

    }

    @Override
    public Category getCategoryById(int id) {
        Category category = null;
        Session session = sessionFactory.getCurrentSession();
        category = (Category) session.get(Category.class, id);
        session.flush();
        return category;
    }

    @Override
    public List<Category> getCategories() {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Category.class).list();
    }
}
