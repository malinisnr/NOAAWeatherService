package com.weather.noaaservice;

import java.util.Date;
import java.util.List;

public class Properties{
    public Date updated;
    public String units;
    public String forecastGenerator;
    public Date generatedAt;
    public Date updateTime;
    public String validTimes;
    public Elevation elevation;
    public List<Period> periods;
}