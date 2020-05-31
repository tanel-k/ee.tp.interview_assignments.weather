package ee.tp.interview_assignments.weather.service.external;

import ee.tp.interview_assignments.weather.configuration.ForecastSourceApiConfiguration;
import ee.tp.interview_assignments.weather.service.dto.DateForecastDto;
import ee.tp.interview_assignments.weather.service.dto.ForecastDto;
import ee.tp.interview_assignments.weather.service.dto.ForecastListDto;
import ee.tp.interview_assignments.weather.service.external.dto.DateForecast;
import ee.tp.interview_assignments.weather.service.external.dto.Forecast;
import ee.tp.interview_assignments.weather.service.external.dto.Forecasts;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ForecastSourceApiClient {
    private final WebClient webClient;
    private final ForecastSourceApiConfiguration configuration;

    public ForecastSourceApiClient(WebClient.Builder webClientBuilder, ForecastSourceApiConfiguration configuration) {
        this.webClient = webClientBuilder
            .baseUrl(configuration.getHost())
            .build();
        this.configuration = configuration;
    }

    public Mono<ForecastListDto> getForecasts() {
        return webClient.get()
            .uri(configuration.getPath(), (builder) -> {
                configuration.getParameters().forEach(builder::queryParam);
                return builder.build();
            })
            .retrieve()
            .bodyToMono(Forecasts.class)
            .flatMapIterable(Forecasts::getForecasts)
            .map(this::mapDateForecast)
            .collectList()
            .map(ForecastListDto::new);
    }

    private DateForecastDto mapDateForecast(DateForecast dateForecast) {
        return DateForecastDto.builder()
            .date(dateForecast.getDate())
            .day(mapForecast(dateForecast.getDay()))
            .night(mapForecast(dateForecast.getNight()))
            .build();
    }

    private ForecastDto mapForecast(Forecast forecast) {
        return ForecastDto.builder()
            .phenomenon(forecast.getPhenomenon())
            .text(forecast.getText())
            .minTemperature(forecast.getMinTemperature())
            .maxTemperature(forecast.getMaxTemperature())
            .build();
    }
}
