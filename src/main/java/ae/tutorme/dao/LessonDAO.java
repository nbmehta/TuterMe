package ae.tutorme.dao;

import ae.tutorme.dto.LessonDTO;
import ae.tutorme.model.Lesson;

/**
 * Created by almehairbi on 2/25/17.
 */
public interface LessonDAO  {

    void saveLesson(Lesson lesson);

    void updateLesson(Lesson lesson);
    
    Lesson getById(int id);
    
    LessonDTO getLessonDTOById(int id);

    LessonDTO updateLesson(int id, LessonDTO lesson);
    
    void deleteLesson(int id);
}
