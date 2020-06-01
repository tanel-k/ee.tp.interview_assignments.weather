package ee.tp.interview_assignments.weather.service.dto;

// From API docs: http://www.ilmateenistus.ee/teenused/ilmainfo/eesti-prognoos-xml/
@SuppressWarnings("unused")
public enum Phenomenon {
    // Not strictly needed, but if migrated to DB table,
    // can be supplemented with additional information that the UI can use to provide enhanced visual input for the user.
    CLEAR,
    FEW_CLOUDS,
    VARIABLE_CLOUDS,
    CLOUDY_WITH_CLEAR_SPELLS,
    CLOUDY,
    LIGHT_SNOW_SHOWER,
    MODERATE_SNOW_SHOWER,
    HEAVY_SNOW_SHOWER,
    LIGHT_SHOWER,
    MODERATE_SHOWER,
    HEAVY_SHOWER,
    LIGHT_RAIN,
    MODERATE_RAIN,
    HEAVY_RAIN,
    RISK_OF_GLAZE,
    LIGHT_SLEET,
    MODERATE_SLEET,
    LIGHT_SNOWFALL,
    MODERATE_SNOWFALL,
    HEAVY_SNOWFALL,
    SNOWSTORM,
    DRIFTING_SNOW,
    HAIL,
    MIST,
    FOG,
    THUNDER,
    THUNDERSTORM
}
