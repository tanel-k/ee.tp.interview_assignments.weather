package ee.tp.interview_assignments.weather.service.forecast;

import ee.tp.interview_assignments.weather.service.dto.ForecastListDto;
import ee.tp.interview_assignments.weather.service.forecast_source.ForecastSourceClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ForecastService {
    private final ForecastSourceClient client;

    public Mono<ForecastListDto> getCurrent() {
        return client.getForecasts();
    }
}
