package com.booking.booking.model;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Users {
    @DocumentId
    private @Nullable String userID;
    private String name;
    private String email;
    private String phone;
    private String address;
    private paymentInformation pay;
    private @Nullable Timestamp createtdAt;

}
