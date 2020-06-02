/// <reference types="../weather-icons-react" />

import React from "react";

import {Grid, Typography} from "@material-ui/core";
import {Skeleton} from '@material-ui/lab';
import {makeStyles} from "@material-ui/core/styles";

import {
    WiCelsius,
    WiDayCloudy,
    WiDayHail,
    WiDayRain,
    WiDayShowers,
    WiDaySleet,
    WiDaySnow,
    WiDaySunny,
    WiDaySunnyOvercast,
    WiDayThunderstorm,
    WiFog,
    WiLightning,
    WiNightClear,
    WiNightCloudy,
    WiNightHail,
    WiNightPartlyCloudy,
    WiNightRain,
    WiNightShowers,
    WiNightSleet,
    WiNightSnow,
    WiNightThunderstorm,
    WiSnowflakeCold,
    WiSnowWind
} from "weather-icons-react";
import {Phenomenon} from "../domain/phenomenon";
import ForecastDto from "../domain/forecast.dto";

const useStyles = makeStyles(() => ({
    root: {
        minWidth: 235,
        maxWidth: 325
    }
}));

type ForecastProps = {
    period: 'DAY' | 'NIGHT';
    forecast?: ForecastDto;
};

const Forecast = ({
    period,
    forecast
}: ForecastProps) => {
    const periodName = period === 'DAY' ? 'Day' : 'Night';

    let Icon;
    let phenomenonName = '';
    if (forecast) {
        const {phenomenon} = forecast;
        Icon = getIcon(period, phenomenon);
        phenomenonName = getDisplayName(phenomenon);
    }

    const classes = useStyles();
    return (
        <Grid container justify="space-around" spacing={1} className={classes.root}>
            <Grid item xs={12}>
                <Typography variant="subtitle1">
                    {periodName}
                </Typography>
            </Grid>
            <Grid item container xs={6} spacing={1}>
                <Grid item xs={12}>
                    {Icon ? <Icon size={75} /> : <Skeleton variant="rect" width={75} height={75} />}
                </Grid>
            </Grid>
            <Grid item container xs={6} spacing={1}>
                <Grid item xs={12}>
                    <Typography align="center">
                        {!forecast ? (
                            <Skeleton width={130} />
                        ) : (
                            <React.Fragment>
                                {forecast.minTemperatureCelsius} &#8451; &hellip; {forecast.maxTemperatureCelsius} &#8451;
                            </React.Fragment>
                        )}
                    </Typography>
                </Grid>
                <Grid item xs={12}>
                    <Typography variant="subtitle2" align="center">
                        {forecast ? phenomenonName : <Skeleton />}
                    </Typography>
                </Grid>
            </Grid>
        </Grid>
    );
};

const getDisplayName = (phenomenon?: Phenomenon) => {
    if (!phenomenon) {
        return '';
    }

    const name = phenomenon as string;
    return name.charAt(0) + name.slice(1).replace(/_/g, " ").toLowerCase();
};

const getIcon = (period: 'DAY' | 'NIGHT', phenomenon?: Phenomenon) => {
    if (!phenomenon) {
        return WiCelsius;
    }

    const isDay = period === 'DAY';
    switch (phenomenon) {
        case Phenomenon.CLEAR:
            return isDay ? WiDaySunny : WiNightClear;
        case Phenomenon.CLOUDY:
            return isDay ? WiDayCloudy : WiNightCloudy;
        case Phenomenon.VARIABLE_CLOUDS:
        case Phenomenon.FEW_CLOUDS:
        case Phenomenon.CLOUDY_WITH_CLEAR_SPELLS:
            return isDay ? WiDaySunnyOvercast : WiNightPartlyCloudy;
        case Phenomenon.MIST:
        case Phenomenon.FOG:
            return WiFog;
        case Phenomenon.HAIL:
            return isDay ? WiDayHail : WiNightHail;
        case Phenomenon.HEAVY_RAIN:
        case Phenomenon.MODERATE_RAIN:
        case Phenomenon.LIGHT_RAIN:
            return isDay ? WiDayRain : WiNightRain;
        case Phenomenon.HEAVY_SHOWER:
        case Phenomenon.MODERATE_SHOWER:
        case Phenomenon.LIGHT_SHOWER:
            return isDay ? WiDayShowers : WiNightShowers;
        case Phenomenon.HEAVY_SNOWFALL:
        case Phenomenon.MODERATE_SNOWFALL:
        case Phenomenon.LIGHT_SNOWFALL:
        case Phenomenon.DRIFTING_SNOW:
        case Phenomenon.HEAVY_SNOW_SHOWER:
        case Phenomenon.MODERATE_SNOW_SHOWER:
        case Phenomenon.LIGHT_SNOW_SHOWER:
            return isDay ? WiDaySnow : WiNightSnow;
        case Phenomenon.MODERATE_SLEET:
            return isDay ? WiDaySleet : WiNightSleet;
        case Phenomenon.RISK_OF_GLAZE:
            return WiSnowflakeCold;
        case Phenomenon.SNOWSTORM:
            return WiSnowWind;
        case Phenomenon.THUNDER:
            return WiLightning;
        case Phenomenon.THUNDERSTORM:
            return isDay ? WiDayThunderstorm : WiNightThunderstorm;
        case Phenomenon.LIGHT_SLEET:
            return isDay ? WiDaySleet : WiNightSleet;
        default:
            return WiCelsius;
    }
};

export default Forecast;
