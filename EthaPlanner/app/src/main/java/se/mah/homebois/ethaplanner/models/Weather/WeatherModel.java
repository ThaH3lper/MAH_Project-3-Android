package se.mah.homebois.ethaplanner.models.Weather;

/**
 * Created by Simon on 10/23/2016.
 */

public class WeatherModel {
    private Query query;

    public Query getQuery ()
    {
        return query;
    }

    public void setQuery (Query query)
    {
        this.query = query;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [query = "+query+"]";
    }
}