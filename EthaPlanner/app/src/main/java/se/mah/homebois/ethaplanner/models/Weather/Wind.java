package se.mah.homebois.ethaplanner.models.Weather;

/**
 * Created by Patrik on 2016-10-23.
 */

public class Wind {
    private String speed;

    private String direction;

    private String chill;

    public String getSpeed ()
    {
        return speed;
    }

    public void setSpeed (String speed)
    {
        this.speed = speed;
    }

    public String getDirection ()
    {
        return direction;
    }

    public void setDirection (String direction)
    {
        this.direction = direction;
    }

    public String getChill ()
    {
        return chill;
    }

    public void setChill (String chill)
    {
        this.chill = chill;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [speed = "+speed+", direction = "+direction+", chill = "+chill+"]";
    }
}
