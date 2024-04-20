package io.github.osucoding.wikiapi.component;

import io.github.osucoding.wikiapi.factory.UptimeFactory;
import io.github.osucoding.wikiapi.model.uptime.UptimeResponse;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Component
public class UptimeComponent {

    private final Instant startTime = Instant.now();

    public UptimeResponse getUptime() {
        var currentTime = Instant.now();
        var duration = Duration.between(startTime, currentTime);
        return UptimeFactory.build(currentTime, duration);
    }
}
