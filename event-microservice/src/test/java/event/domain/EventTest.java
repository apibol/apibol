package event.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;

/**
 * @author Claudio E. de Oliveira on 13/03/16.
 */
public class EventTest {
    
    @Test
    public void testSimpleEvent() throws JsonProcessingException {
        User owner = new User();
        owner.setId("1010");
        owner.setEmail("jao@gmail.com");
        owner.setNickname("Jao");
        Period period = Period.newPeriod(LocalDateTime.now(), LocalDateTime.now());
        Event paulistao = Event.newEvent(UUID.randomUUID().toString(), "Paulistão", period, false, owner);
        String jsonEvent = new ObjectMapper().writeValueAsString(paulistao);
        assertNotNull(jsonEvent);
    }
    
}
