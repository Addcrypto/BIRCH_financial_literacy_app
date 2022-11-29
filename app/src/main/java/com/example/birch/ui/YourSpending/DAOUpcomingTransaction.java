package com.example.birch.ui.YourSpending;

import com.example.birch.models.UpcomingTransactionModel;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;
import java.util.Map;

public class DAOUpcomingTransaction {
    private DatabaseReference databaseReference;

    public DAOUpcomingTransaction() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        // databaseReference = db.getReference(UpcomingTransactionModel.class.getSimpleName());
        databaseReference = db.getReference("UpcomingTransaction");
    }

    public Task<Void> add(UpcomingTransactionModel tr) {
        // TODO: throw exception if tr is null
        return databaseReference.push().setValue(tr);
    }

    public Task<Void> update(String key, HashMap<String ,Object> hashMap)
    {
        return databaseReference.child(key).updateChildren(hashMap);
    }

    public Task<Void> remove(String key)
    {
        return databaseReference.child(key).removeValue();
    }

    public Query get(String key) { return databaseReference.child(key); }
    public Query get() {
        return databaseReference;
    }

}
