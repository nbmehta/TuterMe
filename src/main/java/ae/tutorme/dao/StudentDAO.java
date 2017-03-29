package ae.tutorme.dao;

import ae.tutorme.model.Student;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by almehairbi on 2/28/17.
 */
public interface StudentDAO {

    void saveStudent(Student student);

    Student getStudentById(int id);

    void updateStudentProfile(Student student);



}
