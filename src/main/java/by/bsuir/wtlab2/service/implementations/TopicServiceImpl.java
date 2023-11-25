package by.bsuir.wtlab2.service.implementations;

import by.bsuir.wtlab2.annotations.Singleton;
import by.bsuir.wtlab2.entity.Topic;
import by.bsuir.wtlab2.service.TopicService;
import by.bsuir.wtlab2.service.UserService;
import by.bsuir.wtlab2.utils.Page;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Singleton
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {

    private final UserService userService;

    private final List<Topic> topics = new ArrayList<>();

    @Override
    public Optional<Topic> addTopic(String title, long authorId) {
        Topic topic = Topic.builder()
                .id(topics.size() + 1)
                .name(title)
                .author(userService.getUserById(authorId).orElseThrow())
                .creationTime(LocalDateTime.now())
                .build();
        topics.add(topic);
        return Optional.of(topic);

    }

    @Override
    public boolean deleteTopic(long id) {
        return topics.removeIf(topic -> topic.getId() == id);
    }

    @Override
    public Optional<Topic> updateTopic(long id, String title) {
        Topic topic = topics.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElseThrow();
        topic.setName(title);
        return Optional.of(topic);
    }

    @Override
    public Optional<Topic> getTopic(long id) {
        return topics.stream()
                .filter(topic -> topic.getId() == id)
                .findFirst();
    }

    @Override
    public Page<Topic> getAllTopics(int page, int pageSize) {
        int fromIndex = (page - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, topics.size());
        return Page.<Topic>builder()
                .content(topics.subList(fromIndex, toIndex))
                .pageNumber(page)
                .pageSize(pageSize)
                .totalPages((topics.size() + pageSize - 1) / pageSize)
                .totalElements(topics.size())
                .build();
    }

    @Override
    public List<Topic> getAllTopics() {
        return topics;
    }
}
