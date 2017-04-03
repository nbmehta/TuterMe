package ae.tutorme.dto.converter;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ae.tutorme.dao.ActivationDAO;
import ae.tutorme.dao.AuthorizationDAO;
import ae.tutorme.dao.CategoryDAO;
import ae.tutorme.dao.CourseDAO;
import ae.tutorme.dao.InstructorDAO;
import ae.tutorme.dao.MessageDAO;
import ae.tutorme.dao.ModeratorDAO;
import ae.tutorme.dao.ResponseDAO;
import ae.tutorme.dao.StudentDAO;
import ae.tutorme.dao.TopicDAO;
import ae.tutorme.dao.UserDAO;
import ae.tutorme.dto.ActivationDTO;
import ae.tutorme.dto.AdminDTO;
import ae.tutorme.dto.AuthorizationDTO;
import ae.tutorme.dto.CategoryDTO;
import ae.tutorme.dto.CourseDTO;
import ae.tutorme.dto.EnrollmentDTO;
import ae.tutorme.dto.InstructorDTO;
import ae.tutorme.dto.LessonDTO;
import ae.tutorme.dto.MessageDTO;
import ae.tutorme.dto.ModeratorDTO;
import ae.tutorme.dto.RateDTO;
import ae.tutorme.dto.ResponseDTO;
import ae.tutorme.dto.StudentDTO;
import ae.tutorme.dto.TopicDTO;
import ae.tutorme.model.Activation;
import ae.tutorme.model.Admin;
import ae.tutorme.model.Authorization;
import ae.tutorme.model.Category;
import ae.tutorme.model.Course;
import ae.tutorme.model.Enrollment;
import ae.tutorme.model.Instructor;
import ae.tutorme.model.Lesson;
import ae.tutorme.model.Message;
import ae.tutorme.model.Moderator;
import ae.tutorme.model.Rate;
import ae.tutorme.model.Response;
import ae.tutorme.model.Student;
import ae.tutorme.model.Topic;
import ae.tutorme.model.User;

@Component
public class Converter {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private ActivationDAO activationDAO;
	
	@Autowired
	private AuthorizationDAO authorizationDAO;
	
	@Autowired
	private InstructorDAO instructorDAO;
	
	@Autowired
	private ModeratorDAO moderatorDAO;
	
	@Autowired
	private StudentDAO studentDAO;
	
	@Autowired
	private CourseDAO courseDAO;
	
	@Autowired
	private TopicDAO topicDAO;
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private MessageDAO messageDAO;
	
	@Autowired
	private ResponseDAO responseDAO;
	
	public Activation toActivation(ActivationDTO dto) {
		User user = userDAO.getUserBuUserName(dto.getUserName());
		return new Activation(dto.getId(), dto.getActivationCode(), dto.getExpiryDate(), user);
	}
	
	public Authorization toAuthorization(AuthorizationDTO auth) {
		User user = userDAO.getUserBuUserName(auth.getUserName());
		return new Authorization(auth.getId(), user, auth.getRole());
	}
	
	public Admin toAdmin(AdminDTO admin) {
		Activation activation = activationDAO.getById(admin.getActivation().getId());
		if(activation == null) {
			activation = toActivation(admin.getActivation());
		}
		
		Authorization auth = authorizationDAO.getById(admin.getAuthorization().getId());
		if(auth == null) {
			auth = toAuthorization(admin.getAuthorization());
		}
		
		Admin ret = new Admin(admin.getUserId(), admin.getUserName(), admin.getPassword(), admin.isEnabled(), admin.getName(), activation, auth, admin.getMessages());
		if(ret.getActivation().getUser() == null)
			ret.getActivation().setUser(ret);
		if(ret.getAuthorization().getUser() == null)
			ret.getAuthorization().setUser(ret);
		
		return ret;
	}
	
	public Student toStudent(StudentDTO studentDTO) {
		Activation activation = activationDAO.getById(studentDTO.getActivation().getId());
		if(activation == null) {
			activation = toActivation(studentDTO.getActivation());
		}
		
		Authorization auth = authorizationDAO.getById(studentDTO.getAuthorization().getId());
		if(auth == null) {
			auth = toAuthorization(studentDTO.getAuthorization());
		}
		Student ret =new Student(studentDTO.getUserId(), studentDTO.getUserName(), studentDTO.getPassword(), studentDTO.isEnabled(), studentDTO.getName(), activation, auth, studentDTO.getMessages(), studentDTO.getEnrollments(), studentDTO.getRates()); 
		
		if(ret.getActivation().getUser() == null)
			ret.getActivation().setUser(ret);
		if(ret.getAuthorization().getUser() == null)
			ret.getAuthorization().setUser(ret);
		
		return ret;
	}
	
	public Instructor toInstructor(InstructorDTO instructorDTO) {
		Activation activation = activationDAO.getById(instructorDTO.getActivation().getId());
		if(activation == null) {
			activation = toActivation(instructorDTO.getActivation());
		}
		
		Authorization auth = authorizationDAO.getById(instructorDTO.getAuthorization().getId());
		if(auth == null) {
			auth = toAuthorization(instructorDTO.getAuthorization());
		}
		
		Set<Course> courses = new HashSet<>(instructorDTO.getCourses().size());
		for(CourseDTO c : instructorDTO.getCourses()) {
			courses.add(toCouse(c));
		}
		
		Instructor ret = new Instructor(instructorDTO.getUserId(), instructorDTO.getUserName(), instructorDTO.getPassword(), instructorDTO.isEnabled(), instructorDTO.getName(), activation, auth, instructorDTO.getMessages(), courses);
		
		if(ret.getActivation().getUser() == null)
			ret.getActivation().setUser(ret);
		if(ret.getAuthorization().getUser() == null)
			ret.getAuthorization().setUser(ret);
		
		return ret;
	}
	
	public Moderator toModerator(ModeratorDTO moderatorDTO) {
		Activation activation = activationDAO.getById(moderatorDTO.getActivation().getId());
		if(activation == null) {
			activation = toActivation(moderatorDTO.getActivation());
		}
		
		Authorization auth = authorizationDAO.getById(moderatorDTO.getAuthorization().getId());
		if(auth == null) {
			auth = toAuthorization(moderatorDTO.getAuthorization());
		}
		
		Set<Course> courses = new HashSet<>(moderatorDTO.getCourses().size());
		for(CourseDTO c : moderatorDTO.getCourses()) {
			courses.add(toCouse(c));
		}
		
		Moderator ret = new Moderator(moderatorDTO.getUserId(), moderatorDTO.getUserName(), moderatorDTO.getPassword(), moderatorDTO.isEnabled(), moderatorDTO.getName(), activation, auth, moderatorDTO.getMessages(), courses);
		
		if(ret.getActivation().getUser() == null)
			ret.getActivation().setUser(ret);
		if(ret.getAuthorization().getUser() == null)
			ret.getAuthorization().setUser(ret);
		
		return ret;
	}
	
	public Lesson toLesson(LessonDTO dto) {
		Topic topic = topicDAO.getById(dto.getTopicId());
		return new Lesson(dto.getId(), topic, dto.getLessonName(), dto.getLessonNumber(), dto.getMaterialPath());
	}
	
	public Topic toTopic(TopicDTO dto) {
		Course course = courseDAO.getCourseById(dto.getCourseId());
		Set<Lesson> lessons = new HashSet<>(dto.getLessons().size());
		Topic topic = new Topic(dto.getId(), course, dto.getTopicName(), dto.getTopicNumber(), lessons);
		for(LessonDTO l : dto.getLessons()) {
			Lesson full = toLesson(l);
			if(full.getTopic() == null)
				full.setTopic(topic);
			lessons.add(full);
		}
		return 	topic;
	}
	
	public Enrollment toEnrollment(EnrollmentDTO enrollment) {
		Student student = studentDAO.getStudentById(enrollment.getStudentId());
		Course course = courseDAO.getCourseById(enrollment.getCourseId());
		return new Enrollment(enrollment.getId(), student, course, enrollment.getEnrolledDate(), enrollment.getAmountPaid());
	}
	
	public Rate toRate(RateDTO dto) {
		Student student = studentDAO.getStudentById(dto.getStudentId());
		Course course = courseDAO.getCourseById(dto.getCourseId());
		return new Rate(dto.getId(), student, course, dto.getRating());
	}
	
	public Course toCouse(CourseDTO course) {
		Instructor instructor = instructorDAO.getUserById(course.getInstructorId());
		Moderator moderator = moderatorDAO.getModeratorById(course.getModeratorId());
		Category category = categoryDAO.getCategoryById(course.getCategoryId());
		
		Set<Enrollment> enrollments = new HashSet<>(course.getEnrollments().size());
		for(EnrollmentDTO e : course.getEnrollments()) {
			enrollments.add(toEnrollment(e));
		}
		
		Set<Topic> topics = new HashSet<>(course.getTopics().size());
		for(TopicDTO t : course.getTopics()) {
			topics.add(toTopic(t));
		}
		
		Set<Rate> rates = new HashSet<>(course.getRates().size());
		for(RateDTO r : course.getRates()) {
			rates.add(toRate(r));
		}
		
		return new Course(
				course.getCategoryId(),
				instructor,
				moderator,
				course.getDescription(),
				course.getPrice(),
				course.getName(),
				course.isEnabled(),
				course.getRating(), 
				enrollments, 
				topics, 
				rates, 
				category);
	}
	
	public Category toCategory(CategoryDTO cat) {
		Set<Course> courses = new HashSet<>(cat.getCourses().size());
		for(CourseDTO c : cat.getCourses()) {
			courses.add(toCouse(c));
		}
		return new Category(cat.getCategoryId(), cat.getName(), courses);
	}
	
	public Message toMessage(MessageDTO dto) {
		User user = userDAO.getUserById(dto.getUserId());
		Message msg = messageDAO.getMessagesById(dto.getMessageId());
		Set<Message> msgs = new HashSet<>(dto.getMessages().size());
		Message ret = new Message(dto.getId(), user, msgs, msg, dto.getReciverId(), dto.getSubject(), dto.getBody());
		
		for(MessageDTO m : dto.getMessages()) {
			Message collectionElement = toMessage(m);
			collectionElement.setMessage(ret);
			msgs.add(collectionElement);
		}
		
		return ret;
	}
	
	public Response toResponse(ResponseDTO dto) {
		Course course = courseDAO.getCourseById(dto.getCourseId());
		User user = userDAO.getUserById(dto.getUserId());
		Response response = responseDAO.getById(dto.getResponseId());
		
		Set<Response> res = new HashSet<>(dto.getResponses().size());
		for(ResponseDTO r : dto.getResponses()) {
			res.add(toResponse(r));
		}
		
		return new Response(dto.getId(), course, user, res, response, dto.getText());
	}
}
