package ae.tutorme.dto;


import ae.tutorme.model.Lesson;
import ae.tutorme.model.Topic;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by almehairbi on 2/23/17.
 */


public class TopicDTO implements Serializable {

    private int id;
    private int courseId;
    private String topicName;
    private int topicNumber;
    private Set<LessonDTO> lessons;
    
    public TopicDTO() {
		// TODO Auto-generated constructor stub
	}


    public TopicDTO(Topic topic) {
        this.id = topic.getId();
        this.courseId = topic.getCourse().getCourseId();
        this.topicName = topic.getTopicName();
        this.topicNumber = topic.getTopicNumber();
        this.lessons = converter(topic.getLessons());
    }

    public Set<LessonDTO> converter(Set<Lesson> lessons) {
        Set<LessonDTO> lessonDTOs = new HashSet<>();
        for (Lesson l : lessons) {
            LessonDTO lessonDTO = new LessonDTO(l);
            lessonDTOs.add(lessonDTO);
        }
        return lessonDTOs;
    }
    public int getId() {
        return id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public int getTopicNumber() {
        return topicNumber;
    }

    public void setTopicNumber(int topicNumber) {
        this.topicNumber = topicNumber;
    }

    public Set<LessonDTO> getLessons() {
        return lessons;
    }

    public void setLessons(Set<LessonDTO> lessons) {
        this.lessons = lessons;
    }
}
