package by.bsuir.wtlab2.service;

import by.bsuir.wtlab2.entity.Topic;

import java.util.List;
import java.util.Optional;

public interface TopicService {
    void addTopic(String title, long authorId);

    void deleteTopic(long id);

    void updateTopic(long id, String title);

    Optional<Topic> getTopic(long id);

    List<Topic> getAllTopics(int page, int pageSize);
}
