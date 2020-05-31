package ee.tp.interview_assignments.weather.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ForecastDto {
    private final String text;
    private final String phenomenon;
    private final Integer minTemperature;
    private final Integer maxTemperature;
}
