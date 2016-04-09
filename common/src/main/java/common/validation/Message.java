package common.validation;

import lombok.Getter;

/**
 * Model message for REST APIs
 *
 * @author Claudio E. de Oliveira on 09/04/16.
 */
public class Message {

    @Getter
    private final MessageType messageType;

    @Getter
    private final String message;

    public Message(MessageType messageType, String message) {
        this.messageType = messageType;
        this.message = message;
    }

}
