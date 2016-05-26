package prediction.infra.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.Instant;

public class InstantToLongConverter implements Converter<Instant, Long> {
    @Override
    public Long convert(Instant instant) {
        return instant.toEpochMilli();
    }
}