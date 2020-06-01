package ee.tp.interview_assignments.weather.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
public class DateForecastDto {
    private final Date date;
    private final ForecastDto day;
    private final ForecastDto night;
}
