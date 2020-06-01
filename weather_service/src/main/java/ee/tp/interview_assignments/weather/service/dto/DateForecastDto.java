package ee.tp.interview_assignments.weather.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class DateForecastDto {
    private final LocalDate date;
    private final ForecastDto day;
    private final ForecastDto night;
}
