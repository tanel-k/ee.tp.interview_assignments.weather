package ee.tp.interview_assignments.weather.util.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateXmlAdapter extends XmlAdapter<String, LocalDate> {
    private DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;

    @Override
    public LocalDate unmarshal(String stringValue) {
        return stringValue != null ? formatter.parse(stringValue, LocalDate::from) : null;
    }

    @Override
    public String marshal(LocalDate value) {
        return value != null ? formatter.format(value) : null;
    }
}
