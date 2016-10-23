package se.mah.homebois.ethaplanner.models.Weather;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Patrik on 2016-10-23.
 */

public class Item {
    private Guid guid;

    private String pubDate;

    private String title;

    private Forecast[] forecast;

    private Condition condition;

    private String description;

    private String link;

    @SerializedName("long")
    private String longitude;

    private String lat;

    public Guid getGuid ()
    {
        return guid;
    }

    public void setGuid (Guid guid)
    {
        this.guid = guid;
    }

    public String getPubDate ()
    {
        return pubDate;
    }

    public void setPubDate (String pubDate)
    {
        this.pubDate = pubDate;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public Forecast[] getForecast ()
    {
        return forecast;
    }

    public void setForecast (Forecast[] forecast)
    {
        this.forecast = forecast;
    }

    public Condition getCondition ()
    {
        return condition;
    }

    public void setCondition (Condition condition)
    {
        this.condition = condition;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getLink ()
    {
        return link;
    }

    public void setLink (String link)
    {
        this.link = link;
    }

    public String getLong ()
    {
        return longitude;
    }

    public void setLong (String longitude)
    {
        this.longitude = longitude;
    }

    public String getLat ()
    {
        return lat;
    }

    public void setLat (String lat)
    {
        this.lat = lat;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [guid = "+guid+", pubDate = "+pubDate+", title = "+title+", forecast = "+forecast+", condition = "+condition+", description = "+description+", link = "+link+", long = "+ longitude +", lat = "+lat+"]";
    }
}
