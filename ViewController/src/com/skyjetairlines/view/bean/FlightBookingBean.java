package com.skyjetairlines.view.bean;

import com.skyjetairlines.view.common.CommonViewUtil;

public class FlightBookingBean  {
    private int numberOfSeats;
    
    public FlightBookingBean() {
    }

    public void createBooking() {
        //System.out.println("Number of Seats: " + numberOfSeats);
        CommonViewUtil.executeOperation("CreateBooking");
        //System.out.println(CommonViewUtil.getAttributeFromIterator("BookingsInstanceForCurrentCustomerIterator", "BookingId"));
        for(int i=0; i<numberOfSeats; i++) {
            CommonViewUtil.executeOperation("CreateTicket");
            //System.out.println(CommonViewUtil.getAttributeFromIterator("BookingsInstanceForCurrentCustomerIterator", "BookingId"));
            //System.out.println(CommonViewUtil.getAttributeFromIterator("BookingsInstanceForCurrentCustomerIterator", "BookingId"));
        }
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }
}
