package ee.tp.interview_assignments.weather.service.forecast_source.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Forecast {
    @XmlElement(name = "phenomenon", nillable = true)
    private String phenomenon;
    @XmlElement(name = "text", nillable = true)
    private String text;
    @XmlElement(name = "tempmin")
    private Integer minTemperature;
    @XmlElement(name = "tempmax")
    private Integer maxTemperature;
}
