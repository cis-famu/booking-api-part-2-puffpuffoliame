package com.booking.booking.service;
import com.booking.booking.model.Reviews;
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
public class ReviewsService {
    private Firestore firestore;

    public ReviewsService(){
        this.firestore = FirestoreClient.getFirestore();

    }

    private Reviews documentSnapshotToReview(DocumentSnapshot document)
    {
        Reviews review = null;
        if(document.exists())
            review = new Reviews(document.getId(), document.getString("hotelID"), document.getString("userID"), document.getLong("rating"), document.getString("comment"), document.getTimestamp("date"));
        return review;
    }
    public ArrayList<Reviews> getAllReviews()throws ExecutionException, InterruptedException {
        CollectionReference reviewsCollection = firestore.collection("Reviews");
        ApiFuture<QuerySnapshot> future = reviewsCollection.get();

        ArrayList<Reviews> bookingsList = new ArrayList<>();

        for(DocumentSnapshot document: future.get().getDocuments()){
            Reviews reviews = documentSnapshotToReview(document);
            if(reviews != null)
                bookingsList.add(reviews);
        }
        return bookingsList;

    }

    public Reviews getReviewsById(String reviewID) throws ExecutionException, InterruptedException {
        CollectionReference bookingsCollection = firestore.collection("Reviews");
        ApiFuture<DocumentSnapshot> future = bookingsCollection.document(reviewID).get();
        DocumentSnapshot document = future.get();
        return documentSnapshotToReview(document);
    }

}
