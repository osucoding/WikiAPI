package io.github.osucoding.wikiapi.component;

import io.github.osucoding.wikiapi.model.uptime.UptimeObject;
import io.github.osucoding.wikiapi.model.uptime.UptimeResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UptimeComponentTest {

    @InjectMocks
    private UptimeComponent uptimeComponent;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUptime() {
        var actual = uptimeComponent.getUptime();
        assertNotNull(actual.getUptime());
        assertNotNull(actual.getStartTime());
        assertEquals("America/New_York", actual.getTimeZone());
    }

    private UptimeResponse getDummyUptimeResponse() {
        return UptimeResponse.builder()
                .uptime(UptimeObject.builder()
                        .days(1)
                        .hours(1)
                        .minutes(1)
                        .seconds(1)
                        .build())
                .timeZone("dummy-time-zone")
                .startTime("dummy-start-time")
                .build();
    }
}
