package prediction.infra.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.Instant;

public class LongToInstantConverter implements Converter<Long, Instant> {
    @Override
    public Instant convert(Long source) {
        return Instant.ofEpochMilli(source);
    }
}