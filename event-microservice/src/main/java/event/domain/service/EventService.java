package event.domain.service;

import com.google.common.collect.Lists;
import domain.SystemUser;
import event.domain.Event;
import event.domain.Game;
import event.domain.User;
import event.domain.exception.NoEventsFound;
import event.domain.factory.UserFactory;
import event.domain.repository.EventRepository;
import event.domain.resource.model.BattleResultDTO;
import event.domain.resource.model.EventDTO;
import event.domain.resource.model.NewGame;
import event.domain.specification.EventIsAllowedToUser;
import event.domain.specification.EventIsOpenByTimeRange;
import event.domain.specification.GameIsOpenForPredictions;
import event.domain.specification.IsPrivateEvent;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.HOURS;

/**
 * The event service
 *
 * @author Claudio E. de Oliveira on 28/02/16.
 */
@Service
@Log4j2
public class EventService {

    private final EventRepository eventRepository;

    private final UserInfoService userInfoService;

    private final SenderBattleResultService senderBattleResultService;

    private final SenderNewEventService senderNewEventService;

    private final SystemUserService systemUserService;

    @Autowired
    public EventService(EventRepository eventRepository, UserInfoService userInfoService, SenderBattleResultService senderBattleResultService,
                        SenderNewEventService senderNewEventService, SystemUserService systemUserService) {
        this.eventRepository = eventRepository;
        this.userInfoService = userInfoService;
        this.senderBattleResultService = senderBattleResultService;
        this.senderNewEventService = senderNewEventService;
        this.systemUserService = systemUserService;
    }

    /**
     * Create an event
     *
     * @param eventDTO
     * @param nickname
     * @return
     */
    public Event create(final EventDTO eventDTO, String nickname) {
        SystemUser systemUser = this.systemUserService.loggerUserInfo(nickname);
        User userInfo = UserFactory.fromSystemUser(systemUser);
        Event newEvent = eventDTO.toDomain(userInfo);
        if (new IsPrivateEvent().isSatisfiedBy(eventDTO)) {
            fillUserInfo(newEvent, eventDTO.getParticipants());
        }
        Event savedEvent = this.eventRepository.save(newEvent);
        senderNewEventService.notifyNewEvent(savedEvent);
        return savedEvent;
    }

    /**
     * Add game in event
     *
     * @param eventId
     * @param newGame
     * @param name
     * @return
     */
    public Event addNewGame(final String eventId, final NewGame newGame, String name) {
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
     * @param name
     * @return
     */
    public Event removeGame(final String eventId, final String gameId, String name) {
        Event event = this.eventRepository.findOne(eventId);
        event = event.removeGame(gameId);
        this.eventRepository.save(event);
        return event;
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
     * @param name
     * @return
     */
    public Game addGameResult(final String eventId, final String gameId, final BattleResultDTO resultDTO, String name) {
        Event event = this.findOne(eventId);
        Game game = event.gameById(gameId);
        game.updateGame(resultDTO);
        this.eventRepository.save(event);
        BattleResult battleResult = BattleResult.createNew(eventId, gameId, resultDTO.getPlayerOneResult(), resultDTO.getPlayerTwoResult());
        this.senderBattleResultService.sendResult(battleResult);
        return game;
    }

    /**
     * Fill users info
     *
     * @param event
     * @param participants
     * @return
     */
    private Event fillUserInfo(final Event event, final Set<String> participants) {
        participants.forEach(participantId -> {
            User participantInfo = this.userInfoService.getUserInfo(participantId);
            event.addParticipant(participantInfo);
        });
        return event;
    }

    /**
     * If game is opened for receive new predictions
     *
     * @param eventId
     * @param gameId
     * @return
     */
    public Boolean gameOpenedForPredictions(String eventId, String gameId) {
        final Event event = this.findOne(eventId);
        return new GameIsOpenForPredictions(event).isSatisfiedBy(gameId);
    }

    /**
     * Return user events
     *
     * @param nickname
     * @return
     */
    public List<Event> myEvents(String nickname){
        List<Event> events = Lists.newArrayList();
        SystemUser systemUser = this.systemUserService.loggerUserInfo(nickname);
        User userInfo = UserFactory.fromSystemUser(systemUser);
        final List<Event> myEvents = events.stream().filter(ev -> EventIsOpenByTimeRange.createWithNow().isSatisfiedBy(ev) &&
                new EventIsAllowedToUser(userInfo.getId()).isSatisfiedBy(ev)).collect(Collectors.toList());
        if(CollectionUtils.isEmpty(myEvents)){
            log.info("[MY-EVENTS] No events for user " + userInfo.getId());
            throw new NoEventsFound(userInfo.getId());
        }
        return myEvents;
    }

}
