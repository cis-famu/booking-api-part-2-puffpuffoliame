package com.booking.booking.service;
import com.booking.booking.model.Users;
import com.booking.booking.model.paymentInformation;
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
public class UsersService {
    private Firestore firestore;

    public UsersService(){
        this.firestore = FirestoreClient.getFirestore();

    }

    private Users documentSnapshotToRoom(DocumentSnapshot document)
    {
        Users user = null;
        if(document.exists())
            user = new Users(document.getId(), document.getString("name"), document.getString("email"), document.getString("phone"), document.getString("address"),(paymentInformation) document.get("pay"));
        return user;
    }
    public ArrayList<Users> getAllUsers()throws ExecutionException, InterruptedException {
        CollectionReference usersCollection = firestore.collection("Users");
        ApiFuture<QuerySnapshot> future = usersCollection.get();

        ArrayList<Users> bookingsList = new ArrayList<>();

        for(DocumentSnapshot document: future.get().getDocuments()){
            Users users = documentSnapshotToRoom(document);
            if(users != null)
                bookingsList.add(users);
        }
        return bookingsList;

    }

    public Users getUsersById(String userID) throws ExecutionException, InterruptedException {
        CollectionReference bookingsCollection = firestore.collection("Users");
        ApiFuture<DocumentSnapshot> future = bookingsCollection.document(userID).get();
        DocumentSnapshot document = future.get();
        return documentSnapshotToRoom(document);
    }
}
