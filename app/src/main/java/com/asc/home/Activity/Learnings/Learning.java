package com.asc.home.Activity.Learnings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.asc.home.Adapter.LearningsListAdapter;
import com.asc.home.Model.LearningsModel;
import com.asc.home.R;

import java.util.ArrayList;

public class Learning extends AppCompatActivity {
RecyclerView recyclerView;
public LearningsListAdapter learningsListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);
        initialize();


    }
    public void initialize(){
        recyclerView=findViewById(R.id.learnings_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        learningsListAdapter= new LearningsListAdapter(this,getMyList());
        recyclerView.setAdapter(learningsListAdapter);
    }
    private ArrayList<LearningsModel> getMyList() {
        ArrayList<LearningsModel> learningsModels =new ArrayList<>();
        LearningsModel l=new LearningsModel();
        l.setLearnings_image(R.drawable.people);
        l.setLearnings_title("MUN In IITD");
        l.setSkill1("Public Speaking");
        l.setSkill2("General Awareness");
        l.setSkill3("Team Work");
        learningsModels.add(l);

        l=new LearningsModel();
        l.setLearnings_image(R.drawable.people2);
        l.setLearnings_title("Fest In Gargi");
        l.setSkill1("Management Skills");
        l.setSkill2("Public Relations");
        l.setSkill3("Team Work");
        learningsModels.add(l);

        l=new LearningsModel();
        l.setLearnings_image(R.drawable.people3);
        l.setLearnings_title("Musical Night");
        l.setSkill1("Management Skills");
        l.setSkill2("Crowd Management");
        l.setSkill3("Team Work");
        learningsModels.add(l);

        return learningsModels;
    }
}
