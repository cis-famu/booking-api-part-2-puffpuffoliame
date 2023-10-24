package com.booking.booking.model;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Rooms {
    @DocumentId
    private	@Nullable String roomID;
    private	String hotelID;
    private String roomType;
    private long price;
    private long capacity;
    private String	description;
    private String	availability;
    private String[] images;
    Timestamp createdAt;

}
