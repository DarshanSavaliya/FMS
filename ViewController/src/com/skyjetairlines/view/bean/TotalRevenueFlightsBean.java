package com.skyjetairlines.view.bean;

import java.util.Date;

public class TotalRevenueFlightsBean {
    private Date departureDate;
    private String flightNo;

    public TotalRevenueFlightsBean() {
        super();
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public String getFlightNo() {
        return flightNo;
    }
}
