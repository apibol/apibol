package event.domain.service;

import event.domain.Event;
import event.domain.Game;
import event.domain.User;
import event.domain.repository.EventRepository;
import event.domain.resource.model.BattleResultDTO;
import event.domain.resource.model.EventDTO;
import event.domain.resource.model.NewGame;
import event.domain.specification.IsPrivateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author Claudio E. de Oliveira on 28/02/16.
 */
@Service
public class EventService {

    private final EventRepository eventRepository;

    private final UserInfoService userInfoService;

    private final SenderService senderService;

    @Autowired
    public EventService(EventRepository eventRepository, UserInfoService userInfoService, SenderService senderService) {
        this.eventRepository = eventRepository;
        this.userInfoService = userInfoService;
        this.senderService = senderService;
    }

    /**
     * Create an event
     *
     * @param eventDTO
     * @return
     */
    public Event create(final EventDTO eventDTO) {
        User userInfo = this.userInfoService.getUserInfo(eventDTO.getOwnerId());
        Event newEvent = eventDTO.toDomain(userInfo);
        if(new IsPrivateEvent().isSatisfiedBy(eventDTO)){
            fillUserInfo(newEvent,eventDTO.getParticipants());
        }
        Event savedEvent = this.eventRepository.save(newEvent);
        return savedEvent;
    }

    /**
     * Add game in event
     *
     * @param eventId
     * @param newGame
     * @return
     */
    public Event addNewGame(final String eventId, final NewGame newGame) {
        Event event = this.eventRepository.findOne(eventId);
        event.addGame(newGame.toDomain());
        this.eventRepository.save(event);
        return event;
    }

    /**
     * Remove game from event
     *
     * @param eventId
     * @param gameId
     * @return
     */
    public Event removeGame(final String eventId, final String gameId) {
        Event event = this.eventRepository.findOne(eventId);
        event = event.removeGame(gameId);
        this.eventRepository.save(event);
        return event;
    }

    /**
     * List all events
     *
     * @return
     */
    public List<Event> all() {
        return this.eventRepository.findAll();
    }

    /**
     * Find event by Id
     *
     * @param id
     * @return
     */
    public Event findOne(final String id) {
        return this.eventRepository.findOne(id);
    }

    /**
     * Find game in event
     *
     * @param eventId
     * @param gameId
     * @return
     */
    public Game findGameById(final String eventId, final String gameId) {
        Event event = this.findOne(eventId);
        return event.gameById(gameId);
    }

    /**
     * Update Game result
     *
     * @param eventId
     * @param gameId
     * @param resultDTO
     * @return
     */
    public Game addGameResult(final String eventId, final String gameId, final BattleResultDTO resultDTO) {
        Event event = this.findOne(eventId);
        Game game = event.gameById(gameId);
        game.updateGame(resultDTO);
        this.eventRepository.save(event);
        BattleResult battleResult =  BattleResult.createNew(eventId,gameId,resultDTO.getPlayerOneResult(),resultDTO.getPlayerTwoResult());
        this.senderService.sendResult(battleResult);
        return game;
    }

    /**
     * Fill users info
     *
     * @param event
     * @param participants
     * @return
     */
    private Event fillUserInfo(final Event event,final Set<String> participants){
        participants.forEach(participantId -> {
            User participantInfo = this.userInfoService.getUserInfo(participantId);
            event.addParticipant(participantInfo);
        });
        return event;
    }

}
