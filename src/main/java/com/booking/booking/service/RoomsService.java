package com.booking.booking.service;
import com.booking.booking.model.Rooms;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
@Service
public class RoomsService {
    private Firestore firestore;

    public RoomsService(){
        this.firestore = FirestoreClient.getFirestore();

    }

    private Rooms documentSnapshotToRoom(DocumentSnapshot document)
    {
        Rooms room = null;
        if(document.exists())
            room = new Rooms(document.getId(), document.getString("hotelID"), document.getString("roomType"), document.getLong("price"),document.getLong("capacity"), document.getString("description"), document.getString("availability"),(String[])document.get("images"),document.getTimestamp("createdAt"));
        return room;
    }
    public ArrayList<Rooms> getAllRooms()throws ExecutionException, InterruptedException {
        CollectionReference roomsCollection = firestore.collection("Rooms");
        ApiFuture<QuerySnapshot> future = roomsCollection.get();

        ArrayList<Rooms> bookingsList = new ArrayList<>();

        for(DocumentSnapshot document: future.get().getDocuments()){
            Rooms rooms = documentSnapshotToRoom(document);
            if(rooms != null)
                bookingsList.add(rooms);
        }
        return bookingsList;

    }

    public Rooms getRoomsById(String roomID) throws ExecutionException, InterruptedException {
        CollectionReference bookingsCollection = firestore.collection("Rooms");
        ApiFuture<DocumentSnapshot> future = bookingsCollection.document(roomID).get();
        DocumentSnapshot document = future.get();
        return documentSnapshotToRoom(document);
    }

}
