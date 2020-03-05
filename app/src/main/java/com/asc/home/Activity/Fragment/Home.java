package com.asc.home.Activity.Fragment;

import android.app.Activity;
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.asc.home.Activity.ViewModels.HomeViewModel;
import com.asc.home.Adapter.NewListAdapter;
import com.asc.home.Adapter.SliderImageAdapter;
import com.asc.home.ExampleBottomSheetDialog;
import com.asc.home.Model.EventModel;
import com.asc.home.Adapter.EventListAdapter;
import com.asc.home.Model.NewModel;
import com.asc.home.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import static java.security.AccessController.getContext;

public class Home extends Fragment {

    // Container Vars
    private HomeViewModel homeViewModel;
    public NewListAdapter newListAdapter;
    private SliderImageAdapter sliderImageAdapter;
    LinearLayout sort,sortbyred,categoriesred,newest_first,oldest_first,featured,ongoing,only_near_me;
    // Wigets
    private RecyclerView mrecyclerView;
    private ViewPager imageSlider;
    ImageView backy,cancel;
    EditText search;
    TextView result;
    private LinearLayout sortView, bottomPullUpSortView;
    private Button closeView;
    private boolean firstBottomOpenView = false, isBottomSortViewOpen = false;
    TextView sortby,categories;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel=ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        initialize(root);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;


    }

    private void initialize(final View root){
      //  imageSlider = (ViewPager) root.findViewById(R.id.image_slider);
     //   sliderImageAdapter = new SliderImageAdapter(getContext());
     //   imageSlider.setAdapter(sliderImageAdapter);
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
       // result=root.findViewById(R.id.result);
        newest_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // sortArrayList();
                newest_first.setBackgroundColor(getResources().getColor(R.color.lay_red));
                oldest_first.setBackgroundColor(getResources().getColor(R.color.white));
                ongoing.setBackgroundColor(getResources().getColor(R.color.white));
                featured.setBackgroundColor(getResources().getColor(R.color.white));
                only_near_me.setBackgroundColor(getResources().getColor(R.color.white));
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

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());

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
        mrecyclerView=root.findViewById(R.id.new_view);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        newListAdapter = new NewListAdapter(getContext(),getMyList());
        mrecyclerView.setAdapter(newListAdapter);
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
//      sortFrame.setVisibility(View.GONE);
            bottomPullUpSortView.setVisibility(View.GONE);
        }
    }
    // slide the view from its current position to below itself



    private ArrayList<NewModel> getMyList(){
        ArrayList<NewModel> newModels =new ArrayList<>();
        NewModel m=new NewModel();
        m.setNew_title("MUN In IITD");
        m.setNew_date("21st Feb, 2020");
        m.setTaskcoins("133");
        m.setTasks("5");
        m.setNew_image(R.drawable.people);
        newModels.add(m);

        m=new NewModel();
        m.setNew_title("College Fest In Gargi");
        m.setNew_date("22nd Feb, 2020");
        m.setTaskcoins("133");
        m.setTasks("5");
        m.setNew_image(R.drawable.people2);
        newModels.add(m);

        m=new NewModel();
        m.setNew_title("Cultural Fest In S");
        m.setNew_date("23rd Feb, 2020");
        m.setTaskcoins("133");
        m.setTasks("5");
        m.setNew_image(R.drawable.people3);
        newModels.add(m);


        return newModels;
    }
    private void filter(String text){
        ArrayList<NewModel> filteredlist=new ArrayList<>();
        for (NewModel newModel : getMyList())
        {
            if (newModel.getNew_title().toLowerCase().contains(text.toLowerCase()))
            {
                filteredlist.add(newModel);
            }
        }
//        result.setText(filteredlist.size());
        newListAdapter.filterlist(filteredlist);

//        result.setText(newListAdapter.getItemCount());

    }
    private void sortArrayList(){
        Collections.sort(getMyList(), new Comparator<NewModel>() {
            @Override
            public int compare(NewModel o1, NewModel o2) {
                return o1.getNew_title().compareTo(o2.getNew_title());
            }
        });
            newListAdapter.notifyDataSetChanged();
    }




}
