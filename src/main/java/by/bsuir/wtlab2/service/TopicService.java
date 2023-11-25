package by.bsuir.wtlab2.service;

import by.bsuir.wtlab2.entity.Topic;
import by.bsuir.wtlab2.utils.Page;

import java.util.List;
import java.util.Optional;

public interface TopicService {
    Optional<Topic> addTopic(String title, long authorId);

    boolean deleteTopic(long id);

    Optional<Topic> updateTopic(long id, String title);

    Optional<Topic> getTopic(long id);

    Page<Topic> getAllTopics(int page, int pageSize);

    List<Topic> getAllTopics();
}
