/// <reference types="react" />
/// <reference types="react-dom" />

declare module 'weather-icons-react' {
    type IconProps = {
        className?: string;
        size?: string | number;
        color?: string;
        style?: any;
    };

    class WiDayHail extends React.Component<IconProps, any>{}
    class WiCelsius extends React.Component<IconProps, any>{}
    class WiDaySunny extends React.Component<IconProps, any>{}
    class WiNightClear extends React.Component<IconProps, any>{}
    class WiDayCloudy extends React.Component<IconProps, any>{}
    class WiNightCloudy extends React.Component<IconProps, any>{}
    class WiDaySunnyOvercast extends React.Component<IconProps, any>{}
    class WiNightPartlyCloudy extends React.Component<IconProps, any>{}
    class WiFog extends React.Component<IconProps, any>{}
    class WiNightHail extends React.Component<IconProps, any>{}
    class WiDayRain extends React.Component<IconProps, any>{}
    class WiNightRain extends React.Component<IconProps, any>{}
    class WiDayShowers extends React.Component<IconProps, any>{}
    class WiNightShowers extends React.Component<IconProps, any>{}
    class WiDaySnow extends React.Component<IconProps, any>{}
    class WiNightSnow extends React.Component<IconProps, any>{}
    class WiNightSleet extends React.Component<IconProps, any>{}
    class WiSnowflakeCold extends React.Component<IconProps, any>{}
    class WiSnowWind extends React.Component<IconProps, any>{}
    class WiLightning extends React.Component<IconProps, any>{}
    class WiDayThunderstorm extends React.Component<IconProps, any>{}
    class WiNightThunderstorm extends React.Component<IconProps, any>{}
    class WiDaySleet extends React.Component<IconProps, any>{}
    class WiNightSleet extends React.Component<IconProps, any>{}
}
