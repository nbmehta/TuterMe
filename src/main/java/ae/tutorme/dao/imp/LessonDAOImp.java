package ae.tutorme.dao.imp;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ae.tutorme.dao.LessonDAO;
import ae.tutorme.dto.LessonDTO;
import ae.tutorme.model.Lesson;

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

	@Override
	public LessonDTO getLessonDTOById(int id) {
		Lesson lesson = getById(id);
		return lesson == null ? null : new LessonDTO(getById(id));
	}

	@Override
	public Lesson getById(int id) {
		return (Lesson) sessionFactory.getCurrentSession().get(Lesson.class, id);
	}

	@Override
	public LessonDTO updateLesson(int id, LessonDTO lesson) {
		Lesson lessonFull = getById(lesson.getId());
		
		if(lessonFull != null) {
			lessonFull.setLessonName(lesson.getLessonName());
			lessonFull.setLessonNumber(lesson.getLessonNumber());
			lessonFull.setMaterialPath(lesson.getMaterialPath());
			
			updateLesson(lessonFull);
			return new LessonDTO(lessonFull);
		} else {
			return null;
		}
	}

	@Override
	public void deleteLesson(int id) {
		Session session = sessionFactory.getCurrentSession();
        String querry = "delete from ae.tutorme.model.Lesson l where l.id = :id";
        Query query = session.createQuery(querry);
        query.setParameter("id", id);
        query.executeUpdate();
	}
}
