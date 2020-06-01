import ForecastDto from "./forecast.dto";

type DateForecastDto = {
    date: string;
    day: ForecastDto;
    night: ForecastDto;
};

export default DateForecastDto;
