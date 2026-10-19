package com.example.uolcoursecatalogue;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CourseDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        // 1. Get the data from the Intent
        Intent intent = getIntent();
        String code = intent.getStringExtra("EXTRA_CODE");
        String title = intent.getStringExtra("EXTRA_TITLE");
        String instructor = intent.getStringExtra("EXTRA_INSTRUCTOR");
        String description = intent.getStringExtra("EXTRA_DESCRIPTION");
        int credits = intent.getIntExtra("EXTRA_CREDITS", 0);
        String email = intent.getStringExtra("EXTRA_EMAIL");

        // 2. Set the Title bar to the course code (e.g., CS02310)
        setTitle(code);

        // Adds the back arrow in the top bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // 3. Find the views
        TextView tvDetailCode = findViewById(R.id.tvDetailCode);
        TextView tvDetailTitle = findViewById(R.id.tvDetailTitle);
        TextView tvDetailInstructor = findViewById(R.id.tvDetailInstructor);
        TextView tvDetailCredits = findViewById(R.id.tvDetailCredits);
        TextView tvDetailDescription = findViewById(R.id.tvDetailDescription);
        Button btnEmail = findViewById(R.id.btnEmail);

        // 4. Set the text for the views (UPDATED FOR PREMIUM UI)
        tvDetailCode.setText(code);
        tvDetailTitle.setText(title);
        tvDetailInstructor.setText(instructor);
        tvDetailCredits.setText(String.valueOf(credits));
        tvDetailDescription.setText(description);

        // 5. The Email Button Logic (Implicit Intent)
        btnEmail.setOnClickListener(v -> {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);

            // This ensures ONLY email apps can open it
            emailIntent.setData(Uri.parse("mailto:" + email));

            // Optional: Pre-fill the subject line
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Query about " + code);

            try {
                startActivity(emailIntent);
            } catch (ActivityNotFoundException e) {
                // This Toast shows if the emulator has no email app installed
                Toast.makeText(CourseDetailActivity.this,
                        "No email app found", Toast.LENGTH_SHORT).show();
            }
        });
    }
}