package ee.tp.interview_assignments.weather.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ForecastDto {
    private final String text;
    private final Phenomenon phenomenon;
    private final Integer minTemperatureCelsius;
    private final Integer maxTemperatureCelsius;
}
