package ee.tp.interview_assignments.weather.service.forecast_source;

import ee.tp.interview_assignments.weather.configuration.ForecastSourceConfiguration;
import ee.tp.interview_assignments.weather.service.dto.DateForecastDto;
import ee.tp.interview_assignments.weather.service.dto.ForecastDto;
import ee.tp.interview_assignments.weather.service.dto.ForecastListDto;
import ee.tp.interview_assignments.weather.service.forecast_source.dto.DateForecast;
import ee.tp.interview_assignments.weather.service.forecast_source.dto.Forecast;
import ee.tp.interview_assignments.weather.service.forecast_source.dto.Forecasts;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ForecastSourceClient {
    private final WebClient webClient;
    private final ForecastSourceConfiguration configuration;

    public ForecastSourceClient(WebClient.Builder webClientBuilder, ForecastSourceConfiguration configuration) {
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
