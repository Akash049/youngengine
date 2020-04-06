package com.asc.home.Activity.Home.DashboardFragments;
import androidx.fragment.app.Fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.asc.home.Activity.ViewModels.DashboardViewModel;
import com.asc.home.Adapter.EventListAdapter;
import com.asc.home.Model.Event;
import com.asc.home.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Active extends Fragment implements EventListAdapter.EventCardClickListener, View.OnClickListener {

    //widgets
    private RecyclerView ongoingRecyclerView, completedView;
    private EventListAdapter eventListAdapter, eventListAdapterCompleted;
    private TextView ongoingTextView, completedTextView;
    private LinearLayout ongoingRed, completedRed;
    private ProgressBar progressBar;

    //vars
    private String userId;
    private ArrayList<Event> eventDataList = new ArrayList<>();
    private ArrayList<Event> ongoingList = new ArrayList<>();
    private ArrayList<Event> completedList = new ArrayList<>();
    private Event event;
    private DashboardViewModel dashboardViewModel;

    //Firebase
    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_active, container, false);
        initialize(root);
        loadData();
        dashboardViewModel.getText().observe(this, new Observer<String>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onChanged(@Nullable String s) {

            }
        });

        ongoingTextView.setOnClickListener(this);

        completedTextView.setOnClickListener(this);

        return root;
    }

    public void initialize(View root){
        //Initialization at one place
        ongoingTextView = root.findViewById(R.id.textView3);
        completedTextView = root.findViewById(R.id.textView7);
        ongoingRed =root.findViewById(R.id.ongoingred);
        completedRed =root.findViewById(R.id.completedred);
        progressBar = root.findViewById(R.id.progress_bar);

        // On the start of app, by default ongoing has to be selected
        ongoingTextView.setTextColor(getResources().getColor(R.color.red));
        completedTextView.setTextColor(getResources().getColor(R.color.unselected_grey));
        ongoingRed.setVisibility(View.VISIBLE);

        ongoingRecyclerView = root.findViewById(R.id.recyclerview5);
        eventListAdapter = new EventListAdapter(getContext(),ongoingList,this);
        ongoingRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ongoingRecyclerView.setAdapter(eventListAdapter);

        completedView = root.findViewById(R.id.completed);
        eventListAdapterCompleted = new EventListAdapter(getContext(),completedList,this);
        completedView.setLayoutManager(new LinearLayoutManager(getContext()));
        completedView.setAdapter(eventListAdapterCompleted);

        auth = FirebaseAuth.getInstance();
        userId = auth.getCurrentUser().getUid().toString();
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    public void loadData(){
        //Add the data from the blog post
        firebaseFirestore.collection(userId).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {

                try{
                    progressBar.setVisibility(View.GONE);
                    ongoingRecyclerView.setVisibility(View.VISIBLE);
                    for(DocumentChange doc: queryDocumentSnapshots.getDocumentChanges()){
                        if(doc.getType() ==  DocumentChange.Type.ADDED){
                            event = new Event();
                            event = doc.getDocument().toObject(Event.class);
                            if( event.getStatus() == "1" ){
                                completedList.add(event);
                            }else{
                                ongoingList.add(event);
                            }
                        }
                    }
                    eventListAdapterCompleted.notifyDataSetChanged();
                    eventListAdapter.notifyDataSetChanged();
                }catch (Exception exception){
                    Toast.makeText(getContext(),"Could not download",Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    ongoingRecyclerView.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(),exception.getMessage().toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onParameterSelected(int position) {

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.textView3:
                completedView.setVisibility(View.GONE);
                ongoingRecyclerView.setVisibility(View.VISIBLE);

                ongoingTextView.setTextColor(getResources().getColor(R.color.red));
                completedTextView.setTextColor(getResources().getColor(R.color.unselected_grey));
                completedRed.setVisibility(View.GONE);
                ongoingRed.setVisibility(View.VISIBLE);
                break;
            case R.id.textView7:
                completedView.setVisibility(View.VISIBLE);
                ongoingRecyclerView.setVisibility(View.GONE);

                completedTextView.setTextColor(getResources().getColor(R.color.red));
                ongoingTextView.setTextColor(getResources().getColor(R.color.unselected_grey));
                completedRed.setVisibility(View.VISIBLE);
                ongoingRed.setVisibility(View.GONE);
                break;
        }
    }
}