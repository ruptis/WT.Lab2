package by.bsuir.wtlab2.service;

import by.bsuir.wtlab2.entity.Topic;
import by.bsuir.wtlab2.exception.ServiceException;
import by.bsuir.wtlab2.utils.Page;

import java.util.List;
import java.util.Optional;

public interface TopicService {
    boolean addTopic(String title, long authorId) throws ServiceException;

    boolean deleteTopic(long id) throws ServiceException;

    Optional<Topic> updateTopic(long id, String title) throws ServiceException;

    Optional<Topic> getTopic(long id) throws ServiceException;

    Page<Topic> getAllTopics(int page, int pageSize) throws ServiceException;

    List<Topic> getAllTopics() throws ServiceException;
}
