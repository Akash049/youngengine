package com.asc.home.Activity.Home.DashboardFragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.asc.home.Activity.Home.Dashboard;
import com.asc.home.Activity.Home.Mission;
import com.asc.home.Activity.ViewModels.HomeViewModel;
import com.asc.home.Adapter.EventListAdapter;
import com.asc.home.Adapter.SliderImageAdapter;
import com.asc.home.Model.Event;
import com.asc.home.Model.NewModel;
import com.asc.home.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Home extends Fragment implements EventListAdapter.EventCardClickListener {

    // Container Vars
    private HomeViewModel homeViewModel;
    private EventListAdapter eventListAdapter;
    private SliderImageAdapter sliderImageAdapter;
    private Event event;
    private ArrayList<Event> completeEventList = new ArrayList<>();
    private ArrayList<Event> eventArrayList = new ArrayList<>();


    // Wigets
    private RecyclerView mrecyclerView;
    private LinearLayout sort,sortbyred,categoriesred,newest_first,oldest_first,featured,ongoing,only_near_me;
    private ViewPager imageSlider;
    private ImageView backy,cancel,ham;
    private EditText search;
    private LinearLayout sortView, bottomPullUpSortView,progressBar;
    private Button closeView;
    private boolean firstBottomOpenView = false, isBottomSortViewOpen = false;
    private TextView sortby,categories,result;

    //Firebase
    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel=ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        initialize(root);
        loadData();
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }

    private void initialize(final View root){
        newest_first=root.findViewById(R.id.newest_first);
        oldest_first= root.findViewById(R.id.oldest_first);
        featured=root.findViewById(R.id.featured);
        ongoing=root.findViewById(R.id.ongoingitems);
        only_near_me=root.findViewById(R.id.only_near_me);
        sortbyred=root.findViewById(R.id.sortbyred);
        categoriesred=root.findViewById(R.id.categoriesred);
        sortby=root.findViewById(R.id.sortby);
        categories=root.findViewById(R.id.categories);
        sortby.setTextColor(getResources().getColor(R.color.black));
        categories.setTextColor(getResources().getColor(R.color.unselected_grey));
        categoriesred.setVisibility(View.GONE);
        sortbyred.setVisibility(View.VISIBLE);
        sort=root.findViewById(R.id.sort);
        backy=root.findViewById(R.id.backy);
        cancel=root.findViewById(R.id.cancel);
        search=root.findViewById(R.id.search);
        ham = root.findViewById(R.id.ham);
        progressBar = root.findViewById(R.id.progress_bar);
        firebaseFirestore = FirebaseFirestore.getInstance();

        ham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Dashboard)getActivity()).open_side_drawer();
            }
        });
        newest_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // sortArrayList();
                newest_first.setBackgroundColor(getResources().getColor(R.color.lay_red));
                oldest_first.setBackgroundColor(getResources().getColor(R.color.white));
                ongoing.setBackgroundColor(getResources().getColor(R.color.white));
                featured.setBackgroundColor(getResources().getColor(R.color.white));
                only_near_me.setBackgroundColor(getResources().getColor(R.color.white));
                slideDownBottomView();

            }
        });
        oldest_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // sortArrayList();
                oldest_first.setBackgroundColor(getResources().getColor(R.color.lay_red));
                newest_first.setBackgroundColor(getResources().getColor(R.color.white));
                ongoing.setBackgroundColor(getResources().getColor(R.color.white));
                featured.setBackgroundColor(getResources().getColor(R.color.white));
                only_near_me.setBackgroundColor(getResources().getColor(R.color.white));
                slideDownBottomView();
            }
        });
        featured.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // sortArrayList();
                featured.setBackgroundColor(getResources().getColor(R.color.lay_red));
                oldest_first.setBackgroundColor(getResources().getColor(R.color.white));
                ongoing.setBackgroundColor(getResources().getColor(R.color.white));
                newest_first.setBackgroundColor(getResources().getColor(R.color.white));
                only_near_me.setBackgroundColor(getResources().getColor(R.color.white));
                slideDownBottomView();
            }
        });
        ongoing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // sortArrayList();
                ongoing.setBackgroundColor(getResources().getColor(R.color.lay_red));
                oldest_first.setBackgroundColor(getResources().getColor(R.color.white));
                newest_first.setBackgroundColor(getResources().getColor(R.color.white));
                featured.setBackgroundColor(getResources().getColor(R.color.white));
                only_near_me.setBackgroundColor(getResources().getColor(R.color.white));
                slideDownBottomView();
            }
        });
        only_near_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // sortArrayList();
                only_near_me.setBackgroundColor(getResources().getColor(R.color.lay_red));
                oldest_first.setBackgroundColor(getResources().getColor(R.color.white));
                ongoing.setBackgroundColor(getResources().getColor(R.color.white));
                featured.setBackgroundColor(getResources().getColor(R.color.white));
                newest_first.setBackgroundColor(getResources().getColor(R.color.white));
                slideDownBottomView();
            }
        });

        sortby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortby.setTextColor(getResources().getColor(R.color.black));
                categories.setTextColor(getResources().getColor(R.color.unselected_grey));
                categoriesred.setVisibility(View.GONE);
                sortbyred.setVisibility(View.VISIBLE);

            }
        });
        categories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categories.setTextColor(getResources().getColor(R.color.black));
                sortby.setTextColor(getResources().getColor(R.color.unselected_grey));
                sortbyred.setVisibility(View.GONE);
                categoriesred.setVisibility(View.VISIBLE);

            }
        });


        sort=root.findViewById(R.id.sort);
        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ExampleBottomSheetDialog bottomSheet = new ExampleBottomSheetDialog();
//                bottomSheet.show(getFragmentManager(),"exampleBottomSheet");
                slideUpBottomView();
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.setText("");
            }
        });
        backy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.setText("");
                hideKeyboardFrom(getContext(),search);
            }
        });
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ArrayList<Event> newArr = new ArrayList<>();
                for(Event event: completeEventList){
                    if(event.getTitle().toLowerCase().contains( s.toString().toLowerCase() )){
                        newArr.add(event);
                    }
                }
                updateFilterList(newArr);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        sortView = (LinearLayout)root.findViewById(R.id.sort_view);
        bottomPullUpSortView = (LinearLayout)root.findViewById(R.id.sort_option_view);
        closeView = (Button) root.findViewById(R.id.close_view);
        closeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slideDownBottomView();
            }
        });

        mrecyclerView= (RecyclerView)root.findViewById(R.id.events);
        eventListAdapter = new EventListAdapter(getContext(),eventArrayList,this);
        mrecyclerView.setAdapter(eventListAdapter);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }


    public void updateFilterList(ArrayList<Event> arrayList){
        eventListAdapter = new EventListAdapter(getContext(),arrayList,this);
        mrecyclerView.setAdapter(eventListAdapter);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }


    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void slideUpBottomView(){
        if(!isBottomSortViewOpen){
            isBottomSortViewOpen =true;
            sortView.setBackgroundColor(getResources().getColor(R.color.semi));
            TranslateAnimation animate = new TranslateAnimation(
                    0,                 // fromXDelta
                    0,                 // toXDelta
                    bottomPullUpSortView.getHeight(),  // fromYDelta
                    0);                // toYDelta
            animate.setDuration(500);
            animate.setFillAfter(true);
            bottomPullUpSortView.startAnimation(animate);
        }
    }
    public void slideDownBottomView() {
        if (isBottomSortViewOpen) {
            isBottomSortViewOpen = false;
            sortView.setBackgroundColor(getResources().getColor(R.color.trans));
            TranslateAnimation animate = new TranslateAnimation(
                    0,                 // fromXDelta
                    0,                 // toXDelta
                    0,                 // fromYDelta
                    bottomPullUpSortView.getHeight()); // toYDelta
            animate.setDuration(500);
            animate.setFillAfter(true);
            bottomPullUpSortView.startAnimation(animate);
            bottomPullUpSortView.setVisibility(View.GONE);
        }
    }

    private void loadData(){
        //Add the data from the blog post
        firebaseFirestore.collection("Events").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {

                try{
                    progressBar.setVisibility(View.GONE);
                    mrecyclerView.setVisibility(View.VISIBLE);
                    for(DocumentChange doc: queryDocumentSnapshots.getDocumentChanges()){
                        if(doc.getType() ==  DocumentChange.Type.ADDED){
                            event = new Event();
                            event = doc.getDocument().toObject(Event.class);
                            event.setId(doc.getDocument().getId());
                            eventArrayList.add(event);
                            completeEventList.add(event);
                        }
                    }
                    Collections.reverse(eventArrayList);
                    Collections.reverse(completeEventList);
                    eventListAdapter.notifyDataSetChanged();
                }catch (Exception exception){
                    Toast.makeText(getContext(),"Could not download",Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    mrecyclerView.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(),exception.getMessage().toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void filter(String text){
//        ArrayList<NewModel> filteredlist=new ArrayList<>();
//        for (NewModel newModel : getMyList())
//        {
//            if (newModel.getNew_title().toLowerCase().contains(text.toLowerCase()))
//            {
//                filteredlist.add(newModel);
//            }
//        }
//        eventListAdapter.filterlist(filteredlist);
    }
    private void sortArrayList(){

    }


    @Override
    public void onParameterSelected(int position) {
        Event event = eventArrayList.get(position);
        startActivity(
                new Intent(getActivity(), Mission.class)
                        .putExtra("ID",event.getId())
                        .putExtra("TITLE",event.getTitle())
                        .putExtra("DESC",event.getDesc())
                        .putExtra("IMG",event.getImage_url())
                        .putExtra("THUMB",event.getThumb())
                        .putExtra("TAG",event.getTags())
                        .putExtra("TASK",event.getTasks())
                        .putExtra("POINT",event.getPoint())
                        .putExtra("START",event.getStart())
                        .putExtra("END",event.getEnd())
                        .putExtra("NAME",event.getContact_name())
                        .putExtra("MOBILE",event.getContact_person())

        );
    }
}
