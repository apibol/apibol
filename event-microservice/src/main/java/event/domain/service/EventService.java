package event.domain.service;

import event.domain.Event;
import event.domain.User;
import event.domain.repository.EventRepository;
import event.domain.resource.model.EventDTO;
import event.domain.resource.model.NewGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Claudio E. de Oliveira on 28/02/16.
 */
@Service
public class EventService {

    private final EventRepository eventRepository;

    private final UserInfoService userInfoService;

    @Autowired
    public EventService(EventRepository eventRepository, UserInfoService userInfoService) {
        this.eventRepository = eventRepository;
        this.userInfoService = userInfoService;
    }

    public Event create(EventDTO championship) {
        User userInfo = this.userInfoService.getUserInfo(championship.getOwnerId());
        Event savedEvent = this.eventRepository.save(championship.toDomain(userInfo));
        return savedEvent;
    }

    public Event addNewGame(String championshipId, NewGame newGame) {
        Event event = this.eventRepository.findOne(championshipId);
        event.addGame(newGame.toDomain());
        this.eventRepository.save(event);
        return event;
    }

    public List<Event> all() {
        return this.eventRepository.findAll();
    }

}
