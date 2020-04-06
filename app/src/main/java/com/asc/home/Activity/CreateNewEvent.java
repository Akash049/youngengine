package com.asc.home.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.asc.home.Activity.Home.Dashboard;
import com.asc.home.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import id.zelory.compressor.Compressor;

public class CreateNewEvent extends AppCompatActivity implements View.OnClickListener {

    private static final int MAX_LENGTH = 100;
    private Button imageLoad, startDate, endDate, create;
    private ImageView image;
    private LinearLayout getDate;
    private DatePicker datePicker;
    private ProgressBar progressBar;
    private EditText title, desc, tag, tasks, venue, contactName, contactPerson, point;
    private File compressor;

    //var
    private Uri mainImageUri = null;
    private int dateType = 0;
    private LocalDate start = LocalDate.now();
    private LocalDate end = LocalDate.now();;


    //Firebase part
    private StorageReference storageReference;
    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        initialize();
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagePickerActivity();
            }
        });
        imageLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagePickerActivity();
            }
        });
        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int date) {
                if(dateType == 0){
                    start = LocalDate.of(year, month, date);
                    String formattedDate = start.format(DateTimeFormatter.ofPattern("dd-MMM-yy"));
                    startDate.setText(formattedDate);
                }else{
                    end = LocalDate.of(year, month, date);
                    String formattedDate = end.format(DateTimeFormatter.ofPattern("dd-MMM-yy"));
                    endDate.setText(formattedDate);
                }
                getDate.setVisibility(View.GONE);
            }
        });
    }

    public void initialize(){

        //EditTexts
        title = (EditText)findViewById(R.id.title);
        desc = (EditText)findViewById(R.id.description);
        tasks = (EditText)findViewById(R.id.task);
        tag = (EditText)findViewById(R.id.learning);
        point = (EditText)findViewById(R.id.point);
        venue = (EditText)findViewById(R.id.venue);
        contactName = (EditText)findViewById(R.id.contact_name);
        contactPerson = (EditText)findViewById(R.id.contact_mobile);

        //Firebase
        auth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        firebaseFirestore = FirebaseFirestore.getInstance();

        //widget
        datePicker = (DatePicker)findViewById(R.id.datePicker);
        progressBar = (ProgressBar)findViewById(R.id.progress_bar);
        imageLoad = (Button)findViewById(R.id.image_load);
        image = (ImageView)findViewById(R.id.image);
        startDate = (Button)findViewById(R.id.start_date);
        endDate = (Button)findViewById(R.id.end_date);
        create = (Button)findViewById(R.id.create);
        create.setOnClickListener(this);
        endDate.setOnClickListener(this);
        startDate.setOnClickListener(this);
        getDate = (LinearLayout)findViewById(R.id.date);

        //Initialize the date
        LocalDate today = LocalDate.now();
        String formattedDate = today.format(DateTimeFormatter.ofPattern("dd-MMM-yy"));
        startDate.setText(formattedDate);
        endDate.setText(formattedDate);
    }

    public void imagePickerActivity(){
        // start picker to get image for cropping and then use the image in cropping activity
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                //.setMaxCropResultSize(512,512)
                .setAspectRatio(2,1)
                .start(CreateNewEvent.this);
    }

    private static String randomId(){
        Random generator = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        int randomLength =generator.nextInt(MAX_LENGTH);
        char tempChar;
        for(int i = 0 ;i < randomLength; i++){
            tempChar = (char)(generator.nextInt(96)+32);
            stringBuilder.append(tempChar);
        }
        return stringBuilder.toString();
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                mainImageUri = result.getUri();
                imageLoad.setVisibility(View.GONE);
                image.setVisibility(View.VISIBLE);
                image.setImageURI(mainImageUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

    public void createEvent(){
        if(mainImageUri == null){
            Toast.makeText(getApplicationContext(),"Please upload an image",Toast.LENGTH_SHORT).show();
        }else if(!TextUtils.isEmpty(title.getText().toString()) && !TextUtils.isEmpty(desc.getText().toString())
        && !TextUtils.isEmpty(tasks.getText().toString()) && !TextUtils.isEmpty(tag.getText().toString())
        && !TextUtils.isEmpty(point.getText().toString())&& !TextUtils.isEmpty(venue.getText().toString())
        && !TextUtils.isEmpty(contactName.getText().toString())&& !TextUtils.isEmpty(contactPerson.getText().toString())){

            progressBar.setVisibility(View.VISIBLE);
            create.setVisibility(View.GONE);

            final String randomName = FieldValue.serverTimestamp().toString();
            final StorageReference filePath = storageReference.child("event_images").child(randomName+".jpg");
            filePath.putFile(mainImageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if(task.isSuccessful()){
                        filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                final String downloadUri = uri.toString();

                                //Creating and uploading the thumbnail
                                File newImageFile = new File(mainImageUri.getPath());

                                //Compress the file so that a lower version of file is available
                                try {
                                    compressor = new Compressor(CreateNewEvent.this).compressToFile(newImageFile);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                final StorageReference thumbnail = storageReference.child("post_images/thumbs").child(randomName+".jpg");
                                thumbnail.putFile(Uri.fromFile(compressor)).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                        if(task.isSuccessful()){
                                            thumbnail.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                @Override
                                                public void onSuccess(Uri uri) {
                                                    String thumbnailUri = uri.toString();

                                                    Map<String,Object> eventMap = new HashMap<>();
                                                    eventMap.put("image_url", downloadUri);
                                                    eventMap.put("thumb",thumbnailUri);
                                                    eventMap.put("title", title.getText().toString());
                                                    eventMap.put("desc", desc.getText().toString());
                                                    eventMap.put("tags",tag.getText().toString());
                                                    eventMap.put("tasks",tasks.getText().toString());
                                                    eventMap.put("point",point.getText().toString());
                                                    eventMap.put("start",start.toString());
                                                    eventMap.put("end",end.toString());
                                                    eventMap.put("contact_name",contactName.getText().toString());
                                                    eventMap.put("contact_person",contactPerson.getText().toString());
                                                    eventMap.put("timestamp", FieldValue.serverTimestamp());

                                                    firebaseFirestore.collection("Events").add(eventMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<DocumentReference> task) {
                                                            if(task.isSuccessful()){
                                                                progressBar.setVisibility(View.GONE);
                                                                create.setVisibility(View.VISIBLE);
                                                                Toast.makeText(getApplicationContext(),"Your event has been created",Toast.LENGTH_SHORT).show();
                                                                startActivity(new Intent(CreateNewEvent.this, Dashboard.class));
                                                            }else{
                                                                progressBar.setVisibility(View.GONE);
                                                                create.setVisibility(View.VISIBLE);
                                                                Toast.makeText(getApplicationContext(),"Could not upload your post",Toast.LENGTH_SHORT).show();
                                                            }
                                                        }
                                                    });
                                                }
                                            });
                                        }
                                    }
                                });

                            }
                        });
                    }else{
                        progressBar.setVisibility(View.GONE);
                        create.setVisibility(View.VISIBLE);
                        Toast.makeText(getApplicationContext(),"Could not upload. Please try again",Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }else{
            Toast.makeText(getApplicationContext(),"Please provide all details",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.start_date:
                dateType = 0;
                getDate.setVisibility(View.VISIBLE);
                break;
            case R.id.end_date:
                dateType = 1;
                getDate.setVisibility(View.VISIBLE);
                break;
            case R.id.create:
                createEvent();
                break;
        }
    }
}
