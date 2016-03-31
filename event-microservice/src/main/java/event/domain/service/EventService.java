package event.domain.service;

import event.domain.BattleResult;
import event.domain.Event;
import event.domain.Game;
import event.domain.User;
import event.domain.repository.EventRepository;
import event.domain.resource.model.BattleResultDTO;
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
     * @param eventoDTO
     * @return
     */
    public Event create(EventDTO eventoDTO) {
        User userInfo = this.userInfoService.getUserInfo(eventoDTO.getOwnerId());
        Event savedEvent = this.eventRepository.save(eventoDTO.toDomain(userInfo));
        return savedEvent;
    }

    /**
     * Add game in event
     *
     * @param eventId
     * @param newGame
     * @return
     */
    public Event addNewGame(String eventId, NewGame newGame) {
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
    public Event removeGame(String eventId, String gameId) {
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
    public Event findOne(String id) {
        return this.eventRepository.findOne(id);
    }

    /**
     * Find game in event
     *
     * @param eventId
     * @param gameId
     * @return
     */
    public Game findGameById(String eventId, String gameId) {
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
    public Game addGameResult(String eventId, String gameId, BattleResultDTO resultDTO) {
        Event event = this.findOne(eventId);
        Game game = event.gameById(gameId);
        game.updateGame(resultDTO);
        this.eventRepository.save(event);
        BattleResult battleResult = new BattleResult().eventId(eventId).gameId(gameId).playerOneResult(resultDTO.getPlayerOneResult()).playerTwoResult(resultDTO.getPlayerTwoResult());
        this.senderService.sendResult(battleResult);
        return game;
    }

}
