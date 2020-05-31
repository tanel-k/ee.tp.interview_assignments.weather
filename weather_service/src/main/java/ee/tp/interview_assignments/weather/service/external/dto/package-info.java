@XmlJavaTypeAdapters({
    @XmlJavaTypeAdapter(value = LocalDateXmlAdapter.class, type = LocalDate.class)
})
package ee.tp.interview_assignments.weather.service.external.dto;

import ee.tp.interview_assignments.weather.util.jaxb.LocalDateXmlAdapter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;
import java.time.LocalDate;
