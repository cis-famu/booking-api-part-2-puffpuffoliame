package com.booking.booking.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.booking.booking.model.Users;
import com.booking.booking.model.paymentInformation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
@Service
public class UsersService {
    private Firestore firestore;

    public UsersService(){
        this.firestore = FirestoreClient.getFirestore();

    }

    private Users documentSnapshotToUsers(DocumentSnapshot document)
    {
        Users user = null;
        if(document.exists())
            user = new Users(document.getId(), document.getString("name"), document.getString("email"), document.getString("phone"), document.getString("address"),(paymentInformation) document.get("pay"));
        return user;
    }
    public ArrayList<Users> getAllUsers()throws ExecutionException, InterruptedException {
        CollectionReference usersCollection = firestore.collection("Users");
        ApiFuture<QuerySnapshot> future = usersCollection.get();

        ArrayList<Users> userssList = new ArrayList<>();

        for(DocumentSnapshot document: future.get().getDocuments()){
            Users users = documentSnapshotToUsers(document);
            if(users != null)
                userssList.add(users);
        }
        return userssList;

    }

    public Users getUsersById(String userID) throws ExecutionException, InterruptedException {
        CollectionReference userssCollection = firestore.collection("Users");
        ApiFuture<DocumentSnapshot> future = userssCollection.document(userID).get();
        DocumentSnapshot document = future.get();
        return documentSnapshotToUsers(document);
    }
    public ArrayList<Users> getUserssBycreatedAtAndSort(String createdAt) throws ExecutionException, InterruptedException {
        ArrayList<Users> userss = null;

        DocumentReference flightRef = firestore.collection("Users").document(createdAt);

        CollectionReference usersCollection = firestore.collection("Users");
        Query query = usersCollection.whereEqualTo("createdAt", flightRef)
                .orderBy("createdAt", Query.Direction.DESCENDING);

        ApiFuture<QuerySnapshot> future = query.get();

        for(QueryDocumentSnapshot document :  future.get().getDocuments())
        {
            Users users = documentSnapshotToUsers(document);
            if(users != null)
                userss.add(users);

        }
        return userss;
    }
}
