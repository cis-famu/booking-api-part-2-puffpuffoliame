package com.booking.booking.model;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;
import java.util.ArrayList;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class Hotels {
    @DocumentId
    @Nullable String hotelID;
    String name;
    String description;
    long rating;
    String address;
    String contactInformation;
    ArrayList<String> amenities;
    Timestamp createdAt;


}
