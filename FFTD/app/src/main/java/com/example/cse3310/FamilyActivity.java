package com.example.cse3310;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class FamilyActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageView menu;
    LinearLayout home, friends, family, event, travel, recent, bucket, info, logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family);

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        TextView profileName = findViewById(R.id.profileName);
        TextView toolbarName = findViewById(R.id.toolbar_title);
        TextView contactInfo = findViewById(R.id.contactInfo);
        ImageView profilePic = findViewById(R.id.profilePic);

        drawerLayout = findViewById(R.id.drawerLayout);
        menu = findViewById(R.id.menu);
        home = findViewById(R.id.home);
        friends = findViewById(R.id.friends);
        family = findViewById(R.id.family);
        event = findViewById(R.id.event);
        travel = findViewById(R.id.travel);
        recent = findViewById(R.id.recent);
        bucket = findViewById(R.id.bucket);
        info = findViewById(R.id.info);
        logout = findViewById(R.id.logout);


        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection("Users").document(uid).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    String name = documentSnapshot.getString("Name");
                    String email = documentSnapshot.getString("Email");
                    String imageUrl = documentSnapshot.getString("URL");
                    profileName.setText(name);
                    toolbarName.setText(name);
                    contactInfo.setText(email);

                    Picasso.get().load(imageUrl).into(profilePic);
                }
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDrawer(drawerLayout);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(FamilyActivity.this, MainActivity.class);
            }
        });
        friends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(FamilyActivity.this, FriendsActivity.class);
            }
        });
        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recreate();
            }
        });
        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(FamilyActivity.this, EventActivity.class);
            }
        });
        travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(FamilyActivity.this, TravelActivity.class);
            }
        });
        recent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(FamilyActivity.this, RecentActivity.class);
            }
        });
        bucket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(FamilyActivity.this, BucketActivity.class);
            }
        });
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(FamilyActivity.this, InfoActivity.class);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(FamilyActivity.this, "Logout", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(FamilyActivity.this, SignUpActivity.class));
                finish();
            }
        });

    }
    public static void openDrawer(DrawerLayout drawerLayout){
        drawerLayout.openDrawer(GravityCompat.START);
    }
    public static void closeDrawer(DrawerLayout drawerLayout){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public static void redirectActivity(Activity activity, Class secondActivity){
        Intent intent = new Intent(activity, secondActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }
}