import React from 'react';
import {Grid, Typography} from "@material-ui/core";

import env from "./env";
import DateForecast from "./components/DateForecast";
import DateForecastDto from "./domain/date-forecast.dto";

const App = () => {
    const [forecasts, setForecasts] = React.useState<DateForecastDto[]>([]);

    React.useEffect(() => {
        let eventSource = new EventSource(`${env.API_ROOT_URL}/v1/forecasts/stream`);
        eventSource.onmessage = (evt) => {
            const forecasts = JSON.parse(evt.data).forecasts as DateForecastDto[];
            setForecasts([...forecasts]);
        };
    }, []);

    return (
        <Grid container direction="column" spacing={5} justify="center">
            <Grid item container direction="row">
                <Grid item xs={12}>
                    <Typography variant="h4" align='center'>
                        Weather Forecast
                    </Typography>
                </Grid>
            </Grid>
            <Grid item container
                  direction="row" justify="center"
                  spacing={5}>
                {forecasts.length ? (
                    forecasts.map((forecast) => (
                        <DateForecast forecast={forecast} key={forecast.date} />
                    ))
                ) : (
                    Array.from(Array(4).keys()).map((key) => (
                        <DateForecast key={key} />
                    ))
                )}
            </Grid>
        </Grid>
    );
};

export default App;
