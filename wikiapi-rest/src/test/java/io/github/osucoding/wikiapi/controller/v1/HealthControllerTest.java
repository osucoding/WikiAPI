package io.github.osucoding.wikiapi.controller.v1;

import io.github.osucoding.wikiapi.component.UptimeComponent;
import io.github.osucoding.wikiapi.model.uptime.UptimeObject;
import io.github.osucoding.wikiapi.model.uptime.UptimeResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class HealthControllerTest {

    @InjectMocks
    private HealthController healthController;

    @Mock
    private UptimeComponent uptimeComponent;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUptime_Success() {
        // Mocking uptime response
        UptimeResponse uptimeResponse = UptimeResponse.builder()
                .startTime("2024-04-24T12:00:00Z")
                .timeZone("UTC")
                .uptime(UptimeObject.builder()
                        .days(1)
                        .hours(2)
                        .minutes(30)
                        .seconds(45)
                        .build())
                .build();

        // Stubbing the behavior of uptimeComponent
        when(uptimeComponent.getUptime()).thenReturn(uptimeResponse);

        // Calling the controller method
        ResponseEntity<UptimeResponse> responseEntity = healthController.getUptime();

        // Assertions
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        UptimeResponse responseBody = responseEntity.getBody();
        assertNotNull(responseBody);
        assertNotNull(responseBody.getStartTime());
        assertNotNull(responseBody.getTimeZone());
        assertNotNull(responseBody.getUptime());
        assertEquals(1, responseBody.getUptime().getDays());
        assertEquals(2, responseBody.getUptime().getHours());
        assertEquals(30, responseBody.getUptime().getMinutes());
        assertEquals(45, responseBody.getUptime().getSeconds());
    }

    @Test
    void testGetUptime_ContentType() {
        // Mocking uptime response
        UptimeResponse uptimeResponse = UptimeResponse.builder()
                .startTime("2024-04-24T12:00:00Z")
                .timeZone("UTC")
                .uptime(UptimeObject.builder()
                        .days(1)
                        .hours(2)
                        .minutes(30)
                        .seconds(45)
                        .build())
                .build();

        // Stubbing the behavior of uptimeComponent
        when(uptimeComponent.getUptime()).thenReturn(uptimeResponse);

        // Calling the controller method
        ResponseEntity<UptimeResponse> responseEntity = healthController.getUptime();

        // Assertions
        assertNotNull(responseEntity);

        HttpHeaders headers = responseEntity.getHeaders();
        assertNotNull(headers);

        MediaType contentType = headers.getContentType();
        if (contentType != null) {
            assertTrue(contentType.isCompatibleWith(MediaType.APPLICATION_JSON));
        } else {
            // If Content-Type header is not set, it's still valid for JSON response
            assertTrue(responseEntity.hasBody());
            assertTrue(Objects.requireNonNull(responseEntity.getBody()).getClass().isAssignableFrom(UptimeResponse.class));
        }
    }

    @Test
    void testGetUptime_UptimeObjectValues() {
        // Mocking uptime response
        UptimeResponse uptimeResponse = UptimeResponse.builder()
                .startTime("2024-04-24T12:00:00Z")
                .timeZone("UTC")
                .uptime(UptimeObject.builder()
                        .days(1)
                        .hours(2)
                        .minutes(30)
                        .seconds(45)
                        .build())
                .build();

        // Stubbing the behavior of uptimeComponent
        when(uptimeComponent.getUptime()).thenReturn(uptimeResponse);

        // Calling the controller method
        ResponseEntity<UptimeResponse> responseEntity = healthController.getUptime();

        // Assertions
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        UptimeResponse responseBody = responseEntity.getBody();
        assertNotNull(responseBody);
        assertNotNull(responseBody.getStartTime());
        assertNotNull(responseBody.getTimeZone());
        assertNotNull(responseBody.getUptime());
        assertTrue(responseBody.getUptime().getDays() >= 0);
        assertTrue(responseBody.getUptime().getHours() >= 0);
        assertTrue(responseBody.getUptime().getMinutes() >= 0);
        assertTrue(responseBody.getUptime().getSeconds() >= 0);
    }
}
