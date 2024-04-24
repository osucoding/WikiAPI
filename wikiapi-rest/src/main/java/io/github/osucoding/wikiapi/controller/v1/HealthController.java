package io.github.osucoding.wikiapi.controller.v1;

import io.github.osucoding.wikiapi.component.UptimeComponent;
import io.github.osucoding.wikiapi.model.uptime.UptimeResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/health")
@Slf4j
@RequiredArgsConstructor
public class HealthController {

    private final UptimeComponent uptimeComponent;

    @GetMapping(path = "/uptime", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UptimeResponse> getUptime() {
        return ResponseEntity.ok(uptimeComponent.getUptime());
    }
}
