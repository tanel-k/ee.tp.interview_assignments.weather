package ee.tp.interview_assignments.weather.service.forecast;

import ee.tp.interview_assignments.weather.configuration.ForecastProcessingConfiguration;
import ee.tp.interview_assignments.weather.service.cache.CacheConfiguration;
import ee.tp.interview_assignments.weather.service.cache.CacheService;
import ee.tp.interview_assignments.weather.service.dto.ForecastListDto;
import ee.tp.interview_assignments.weather.service.external.ForecastSourceApiClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ForecastService {
    private static final String KEY_FORECAST_LIST = String.format("%s:%s", ForecastService.class.getName(), "forecastList");

    private final CacheService cacheService;
    private final ForecastSourceApiClient apiClient;
    private final ForecastProcessingConfiguration configuration;

    public Mono<ForecastListDto> getCurrent() {
        return cacheService.get(
            KEY_FORECAST_LIST,
            Mono.defer(apiClient::getForecasts),
            () -> CacheConfiguration.of(configuration.getCachedResponseValidFor())
        );
    }
}
