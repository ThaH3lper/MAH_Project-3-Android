package se.mah.homebois.ethaplanner.models.Weather;

/**
 * Created by Patrik on 2016-10-23.
 */

public class Results {
    private Channel channel;

    public Channel getChannel ()
    {
        return channel;
    }

    public void setChannel (Channel channel)
    {
        this.channel = channel;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [channel = "+channel+"]";
    }
}
