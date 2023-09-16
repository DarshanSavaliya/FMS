package com.skyjetairlines.view.bean;

import com.skyjetairlines.view.common.CommonViewUtil;

import java.math.BigDecimal;

import javax.faces.event.ActionEvent;

import oracle.jbo.Row;
import oracle.jbo.domain.Number;

import org.apache.myfaces.trinidad.event.ReturnEvent;

public class FlightBookingBean {
    private int numberOfSeats;

    public FlightBookingBean() {
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void createBooking() {
        CommonViewUtil.executeOperation("CreateBooking");

        Row flightSelected = CommonViewUtil.getIterator("FlightsInstanceIterator").getCurrentRow();

        double price = ((Number) flightSelected.getAttribute("Price")).doubleValue();
        CommonViewUtil.setAttributeInIterator("BookingsInstanceForCurrentCustomerIterator", "TotalAmount",
                                              price * numberOfSeats);

        for (int i = 0; i < numberOfSeats; i++) {
            CommonViewUtil.executeOperation("CreateTicket");
            CommonViewUtil.executeOperation("CreateFlightAssociation");

            Row ticketFlightAssoc =
                CommonViewUtil.getIterator("TicketFlightsInstanceForTicketsForBookingForCurrentCustomerIterator")
                .getCurrentRow();

            ticketFlightAssoc.setAttribute("FlightId", flightSelected.getAttribute("FlightId"));
            ticketFlightAssoc.setAttribute("Amount", flightSelected.getAttribute("Price"));
        }
    }

    public void PaymentCreatedListener(ReturnEvent returnEvent) {
        CommonViewUtil.getIterator("PaymentMethodsInstanceForCurrentCustomerIterator").executeQuery();
    }

    // TODO
    public void PassengerCreatedListener(ReturnEvent returnEvent) {
        CommonViewUtil.getIterator("PassengersInstanceForCurrentCustomerIterator").executeQuery();
        CommonViewUtil.getIterator("TicketsInstanceForCurrentCustomerIterator").executeQuery();
    }

    public void confirmBooking() {
        Row flightSelected = CommonViewUtil.getIterator("FlightsInstanceIterator").getCurrentRow();
        flightSelected.setAttribute("AvailableSeats",
                                    ((BigDecimal) flightSelected.getAttribute("AvailableSeats")).intValue() -
                                    numberOfSeats);
    }
}
