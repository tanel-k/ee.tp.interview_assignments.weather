package ee.tp.interview_assignments.weather.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.time.Duration;

@ConstructorBinding
@ConfigurationProperties(prefix = "forecast.processing")
@Getter
@AllArgsConstructor
public class ForecastProcessingConfiguration {
    private final Duration cachedResponseValidFor;
}
