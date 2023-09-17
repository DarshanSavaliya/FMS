package com.skyjetairlines.view.bean;

import java.util.Date;

public class FlightSearchQueryBean {
    private int numberOfSeats;
    private Date bookingDate;
    private final Date minDate;

    public FlightSearchQueryBean() {
        this.numberOfSeats = 1;
        this.minDate = new Date();
        this.bookingDate = this.minDate;
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

    public Date getMinDate() {
        return minDate;
    }
}
