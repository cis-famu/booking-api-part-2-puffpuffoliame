package com.booking.booking.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.booking.booking.model.paymentInformation;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class paymentInformationService {
    private Firestore firestore;

    public paymentInformationService(){
        this.firestore = FirestoreClient.getFirestore();
    }

    public paymentInformation getPaymentInformation(DocumentReference docRef) throws ExecutionException, InterruptedException {
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        return document.toObject(paymentInformation.class);
    }
}
