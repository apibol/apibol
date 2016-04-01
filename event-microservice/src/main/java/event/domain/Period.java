package event.domain;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Claudio E. de Oliveira on 28/02/16.
 */
@Data
public class Period {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private String start;

    private String end;

    Period() {
    }

    private Period(String start, String end) {
        this.start = start;
        this.end = end;
    }

    public static Period newPeriod(String start, String end) {
        return new Period(start, end);
    }

    public LocalDateTime start(){
        return LocalDateTime.parse(this.start,FORMATTER);
    }

    public LocalDateTime end(){
        return LocalDateTime.parse(this.end,FORMATTER);
    }


}
