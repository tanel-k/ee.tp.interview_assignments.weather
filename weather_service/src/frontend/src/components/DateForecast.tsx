import React from "react";

import {format, isToday, isTomorrow} from "date-fns";
import {Card, CardContent, CardHeader, Divider, Grid} from "@material-ui/core";
import Forecast from "./Forecast";
import DateForecastDto from "../domain/date-forecast.dto";
import {makeStyles} from "@material-ui/core/styles";
import {Skeleton} from "@material-ui/lab";

type DateForecastProps = {
    forecast?: DateForecastDto;
};

const useStyles = makeStyles((theme) => ({
    divider: {
        marginTop: theme.spacing(2),
        marginBottom: theme.spacing(2)
    }
}));

const DateForecast = ({forecast}: DateForecastProps) => {
    let dateTitle = '';
    let dateSubtitle = '';
    if (forecast) {
        const forecastDate = new Date(forecast.date);
        const isForecastForToday = isToday(forecastDate);
        const isForecastForTomorrow = !isForecastForToday && isTomorrow(forecastDate);

        if (isForecastForToday) {
            dateTitle = "Today";
        } else if (isForecastForTomorrow) {
            dateTitle = "Tomorrow";
        } else {
            dateTitle = format(forecastDate, "cccc");
        }
        dateSubtitle = format(forecastDate, "MMM d");
    }

    const classes = useStyles();
    return (
        <Grid item>
            <Card>
                <CardHeader
                    title={dateTitle ? dateTitle : <Skeleton />}
                    subheader={dateSubtitle ? dateSubtitle : <Skeleton />}
                />
                <CardContent>
                    <Forecast period="DAY" forecast={forecast?.day} />
                    <Divider className={classes.divider} />
                    <Forecast period="NIGHT" forecast={forecast?.night} />
                </CardContent>
            </Card>
        </Grid>
    );
};

export default DateForecast;
