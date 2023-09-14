package com.skyjetairlines.view.bean;

import com.skyjetairlines.view.common.CommonViewUtil;

import java.util.Date;

public class FlightSearchQueryBean {
    private int numberOfSeats;
    private Date bookingDate;

    public FlightSearchQueryBean() {
        this.numberOfSeats = 1;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Date getBookingDate() {
        return bookingDate;
    }
}
