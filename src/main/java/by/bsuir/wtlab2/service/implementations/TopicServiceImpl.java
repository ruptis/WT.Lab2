package by.bsuir.wtlab2.service.implementations;

import by.bsuir.wtlab2.annotations.Singleton;
import by.bsuir.wtlab2.dao.TopicDAO;
import by.bsuir.wtlab2.entity.Topic;
import by.bsuir.wtlab2.entity.User;
import by.bsuir.wtlab2.exception.DAOException;
import by.bsuir.wtlab2.exception.ServiceException;
import by.bsuir.wtlab2.service.TopicService;
import by.bsuir.wtlab2.utils.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Singleton
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {

    private final TopicDAO topicDAO;

    @Override
    public boolean addTopic(String title, long authorId) throws ServiceException {
        Topic topic = Topic.builder()
                .name(title)
                .name(title)
                .author(User.builder().id(authorId).build())
                .creationTime(LocalDateTime.now())
                .questionsCount(0)
                .build();
        try {
            topicDAO.save(topic);
        } catch (DAOException e) {
            log.error("Failed to add topic", e);
            throw new ServiceException("Failed to add topic", e);
        }
        return true;
    }

    @Override
    public boolean deleteTopic(long id) throws ServiceException {
        boolean deleted;
        try {
            deleted = topicDAO.delete(id);
        } catch (DAOException e) {
            log.error("Failed to delete topic", e);
            throw new ServiceException("Failed to delete topic", e);
        }
        return deleted;
    }

    @Override
    public Optional<Topic> updateTopic(long id, String title) throws ServiceException {
        Optional<Topic> topic = getTopic(id);
        topic.ifPresent(t -> t.setName(title));
        try {
            topicDAO.update(topic.orElseThrow());
            return topic;
        } catch (DAOException e) {
            log.error("Failed to update topic", e);
            throw new ServiceException("Failed to update topic", e);
        }
    }

    @Override
    public Optional<Topic> getTopic(long id) throws ServiceException {
        try {
            return topicDAO.getById(id);
        } catch (DAOException e) {
            log.error("Failed to get topic by id", e);
            throw new ServiceException("Failed to get topic by id", e);
        }
    }

    @Override
    public Page<Topic> getAllTopics(int page, int pageSize) throws ServiceException {
        try {
            return topicDAO.getAll(page, pageSize);
        } catch (DAOException e) {
            log.error("Failed to get all topics", e);
            throw new ServiceException("Failed to get all topics", e);
        }
    }

    @Override
    public List<Topic> getAllTopics() throws ServiceException {
        try {
            return topicDAO.getAll();
        } catch (DAOException e) {
            log.error("Failed to get all topics", e);
            throw new ServiceException("Failed to get all topics", e);
        }
    }
}
