package com.electronicarmory.firebase

import com.google.firebase.firestore.FirebaseFirestore
import android.util.Log
import com.google.firebase.firestore.QuerySnapshot
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.Query
import org.greenrobot.eventbus.EventBus
import java.util.*
import kotlin.collections.ArrayList


object EventsController {

    val firestoreDB = FirebaseFirestore.getInstance()

    var eventsArray = ArrayList<BSUEvent>()

    fun refreshEvents(){

        firestoreDB.collection("events")
            .orderBy("date", Query.Direction.ASCENDING)
            .whereGreaterThanOrEqualTo("date", Date())
            .get()
            .addOnCompleteListener(OnCompleteListener<QuerySnapshot> { task ->
                if (task.isSuccessful) {
                    eventsArray = ArrayList<BSUEvent>()

                    for (bsuEvent in task.result!!.documents){

                        var newBSUEvent = BSUEvent(bsuEvent.get("title") as String,
                            bsuEvent.get("description") as String
                        )

                        eventsArray.add(newBSUEvent)
                    }
                    EventBus.getDefault().post(BSUEventMessage())

                } else {
                    Log.w("BSU", "Error getting documents.", task.exception)
                }
            })
    }
}