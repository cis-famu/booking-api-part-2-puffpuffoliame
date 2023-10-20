package com.booking.booking.model;

import com.google.cloud.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;


@Data
@NoArgsConstructor

public class Bookings {String	bookingID;
    String userID;
    Timestamp checkInDate ;
    Timestamp checkOutDate;
    long totalPrice;
    String status;
    String paymentStatus;

    public Bookings(String bd, String ud, Timestamp ci, Timestamp co,long tp, String s, String ps){
        bookingID = bd;
        userID = ud;
        checkInDate = ci;
        checkOutDate= co;
        totalPrice = tp ;
        status = s;
        paymentStatus = ps;
    }

}