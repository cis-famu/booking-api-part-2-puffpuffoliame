package com.booking.booking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Rooms {
    private	String roomID;
    private	String hotelID;
    private String roomType;
    private long price;
    private long capacity;
    private String	description;
    private String	availability;
    private String[] images;

}
