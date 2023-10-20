package com.booking.booking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Users {
    private String userID;
    private String name;
    private String email;
    private String phone;
    private String address;
    private paymentInformation pay;

}
