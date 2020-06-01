package ee.tp.interview_assignments.weather.controller;

import ee.tp.interview_assignments.weather.configuration.ForecastStreamConfiguration;
import ee.tp.interview_assignments.weather.service.dto.ForecastListDto;
import ee.tp.interview_assignments.weather.service.forecast.ForecastService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(path = "/v1")
@AllArgsConstructor
public class ForecastController {
    private final ForecastService service;
    private final ForecastStreamConfiguration streamConfiguration;

    @CrossOrigin
    @GetMapping(path = "forecasts/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ForecastListDto> getStream() {
        return Flux.interval(streamConfiguration.getRefreshDelay(), streamConfiguration.getRefreshRate())
            .switchMap((sequence) -> service.getCurrent());
    }
}
