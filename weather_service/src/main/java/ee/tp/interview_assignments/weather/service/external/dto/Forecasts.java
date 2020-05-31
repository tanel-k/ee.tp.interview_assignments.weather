package ee.tp.interview_assignments.weather.service.external.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Forecasts {
    @XmlElement(name = "forecast")
    private List<DateForecast> forecasts;
}
