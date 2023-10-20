package com.booking.booking.service;
import com.booking.booking.model.Bookings;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
@Service
public class BookingsService {
    private Firestore firestore;

    public BookingsService(){
        this.firestore = FirestoreClient.getFirestore();

    }

    private Bookings documentSnapshotToBookings(DocumentSnapshot document)
    {
        Bookings passenger = null;
        if(document.exists()) {
            passenger = new Bookings(document.getId(),document.getString("userID"), document.getTimestamp("checkInDate"), document.getTimestamp("checkOutDate"), document.getLong("totalPrice"), document.getString("status"),document.getString("paymentStatus"));
        }
        return passenger;
    }
    public ArrayList<Bookings> getAllBookings()throws ExecutionException, InterruptedException {
        CollectionReference bookingsCollection = firestore.collection("Bookings");
        ApiFuture<QuerySnapshot> future = bookingsCollection.get();

        ArrayList<Bookings> bookingsList = new ArrayList<>();

        for(DocumentSnapshot document: future.get().getDocuments()){
            Bookings bookings = documentSnapshotToBookings(document);
            if(bookings != null)
                bookingsList.add(bookings);
        }
        return bookingsList;

    }

    public Bookings getBookingsById(String bookingID) throws ExecutionException, InterruptedException {
        CollectionReference bookingsCollection = firestore.collection("Bookings");
        ApiFuture<DocumentSnapshot> future = bookingsCollection.document(bookingID).get();
        DocumentSnapshot document = future.get();
        return documentSnapshotToBookings(document);
    }

}
