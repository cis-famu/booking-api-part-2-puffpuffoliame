package com.booking.booking.model;

import com.google.cloud.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Reviews {
    private @Nullable String reviewID;
    private String hotelID;
    private String userID;
    private long rating;
    private String	comment;
    private Timestamp date;

}
