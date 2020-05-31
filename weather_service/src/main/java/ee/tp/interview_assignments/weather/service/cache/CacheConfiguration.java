package ee.tp.interview_assignments.weather.service.cache;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Duration;

@Getter
@AllArgsConstructor(staticName = "of")
public class CacheConfiguration {
    private Duration timeToLive;
}
