package com.skyjetairlines.view.bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

import org.apache.http.client.utils.DateUtils;

public class FlightSearchQueryBean {
    private int numberOfSeats;
    private Date bookingDate;
    private Date minDate;

    public FlightSearchQueryBean() {
        this.numberOfSeats = 1;
        this.minDate = new Date();
        
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String currentDate = formatter.format(minDate);
            minDate = formatter.parse(currentDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        this.bookingDate = minDate;
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
