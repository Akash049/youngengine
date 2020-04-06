package com.asc.home.Activity.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.asc.home.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mission extends AppCompatActivity {
    private LinearLayout join;
    private ProgressBar progressBar;
    private String id, title, image_url, thumb, desc, tags, tasks , point, start, end, contact_name, contact_person;
    private ImageView image;
    private TextView titleView, descView, dateView,tagView, taskView, pointView, contactView;
    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;
    private String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission);
        try{
            Intent intent = getIntent();
            id = intent.getStringExtra("ID");
            title = intent.getStringExtra("TITLE");
            image_url = intent.getStringExtra("IMG");
            thumb = intent.getStringExtra("THUMB");
            desc = intent.getStringExtra("DESC");
            tags = intent.getStringExtra("TAG");
            tasks = intent.getStringExtra("TASK");
            point = intent.getStringExtra("POINT");
            start = intent.getStringExtra("START");
            end = intent.getStringExtra("END");
            contact_name = intent.getStringExtra("NAME");
            contact_person = intent.getStringExtra("MOBILE");
        }catch (Exception e){

        }
        initiate();

        userID = auth.getCurrentUser().getUid().toString();

        //Apply the data
        try{
            new DownloadImageTask(image)
                    .execute(image_url);
        }catch (Exception e){

        }
        //Apply the data
        try{
            new DownloadImageTask(image)
                    .execute(thumb);
        }catch (Exception e){

        }
        titleView.setText(title);
        dateView.setText(start);
        descView.setText(desc);

        List<String> tagList = Arrays.asList(tags.split(";"));
        String tagsSum = String.join(" | ", tagList);
        tagView.setText(tagsSum);

        List<String> taskList = Arrays.asList(tasks.split(";"));
        String taskSum = String.join("\n", taskList);
        taskView.setText(taskSum);


        pointView.setText(point + " points");
        contactView.setText("Name: "+contact_name +", Mobile: " + contact_person);

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                join.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);

                Map<String,Object> joinList = new HashMap<>();
                joinList.put("image_url", image_url);
                joinList.put("id", id);
                joinList.put("tasks",tasks);
                joinList.put("point",point);
                joinList.put("thumb",thumb);
                joinList.put("title", title);
                joinList.put("status", "0");
                joinList.put("timestamp", FieldValue.serverTimestamp());

                firebaseFirestore.collection(userID).add(joinList).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if(task.isSuccessful()){
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(),"You have been registered to the event",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Mission.this, Dashboard.class));
                        }else{
                            progressBar.setVisibility(View.GONE);
                            join.setVisibility(View.VISIBLE);
                            Toast.makeText(getApplicationContext(),"Could join you to this list",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }

    public void initiate(){
        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        image = (ImageView)findViewById(R.id.image);
        titleView = (TextView)findViewById(R.id.title);
        descView = (TextView)findViewById(R.id.desc);
        dateView = (TextView)findViewById(R.id.date);
        tagView = (TextView)findViewById(R.id.tags);
        taskView = (TextView)findViewById(R.id.task);
        pointView = (TextView)findViewById(R.id.points);
        contactView = (TextView)findViewById(R.id.contact);
        join = (LinearLayout)findViewById(R.id.join);
        progressBar = (ProgressBar)findViewById(R.id.progress_bar);
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

}
