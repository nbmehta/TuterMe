package ae.tutorme.dao.imp;

import ae.tutorme.dao.LessonDAO;
import ae.tutorme.model.Lesson;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by almehairbi on 2/25/17.
 */
@Repository
@Transactional
public class LessonDAOImp implements LessonDAO {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void saveLesson(Lesson lesson) {
        Session session = sessionFactory.getCurrentSession();
        session.save(lesson);
        session.flush();
    }

    @Override
    public void updateLesson(Lesson lesson) {

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(lesson);
        session.flush();
    }
}
