import {Phenomenon} from "./phenomenon";

type ForecastDto = {
    phenomenon?: Phenomenon;
    minTemperatureCelsius: number;
    maxTemperatureCelsius: number;
};

export default ForecastDto;
