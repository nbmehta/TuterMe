package ae.tutorme.dao.imp;

import ae.tutorme.dao.CategoryDAO;
import ae.tutorme.dto.CategoryDTO;
import ae.tutorme.model.Category;
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
    public Category saveCategory(Category category) {
        Session session = sessionFactory.getCurrentSession();
        session.save(category);
        session.flush();
        return category;
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

	@Override
	public CategoryDTO getCategoryDTOById(int id) {
		Category category = getCategoryById(id);
		return category == null ? null : new CategoryDTO(getCategoryById(id));
	}

	@Override
	public CategoryDTO updateCategory(int id, CategoryDTO category) {
		Category categoryFull = getCategoryById(id);
		
		if(categoryFull != null) {
			categoryFull.setName(category.getName());
			
			updateCategory(categoryFull);
			return new CategoryDTO(categoryFull);
		} else {
			return null;
		}
	}

	@Override
	public void deleteCategory(int id) {
		Session session = sessionFactory.getCurrentSession();
        String querry = "delete from ae.tutorme.model.Category c where c.id = :id";
        Query query = session.createQuery(querry);
        query.setParameter("id", id);
        query.executeUpdate();
	}

	@Override
	public void updateCategory(Category cat) {
		sessionFactory.getCurrentSession().update(cat);
	}
}
