package se.mah.homebois.ethaplanner.models.Weather;

/**
 * Created by Patrik on 2016-10-23.
 */

public class Astronomy {
    private String sunset;

    private String sunrise;

    public String getSunset ()
    {
        return sunset;
    }

    public void setSunset (String sunset)
    {
        this.sunset = sunset;
    }

    public String getSunrise ()
    {
        return sunrise;
    }

    public void setSunrise (String sunrise)
    {
        this.sunrise = sunrise;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [sunset = "+sunset+", sunrise = "+sunrise+"]";
    }
}
