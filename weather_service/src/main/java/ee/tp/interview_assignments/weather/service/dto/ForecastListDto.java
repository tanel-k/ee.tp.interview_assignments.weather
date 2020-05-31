package ee.tp.interview_assignments.weather.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ForecastListDto {
    private final List<DateForecastDto> forecasts;
}
