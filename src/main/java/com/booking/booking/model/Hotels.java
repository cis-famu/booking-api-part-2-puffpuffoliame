package com.booking.booking.model;
import com.google.cloud.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class Hotels {
    String hotelID;
    String name;
    String description;
    long rating;
    String address;
    String contactInformation;
    String[] amenities;
}
