package com.example.uolcoursecatalogue;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CourseAdapter.OnItemClickListener {

    private RecyclerView rvCourses;
    private ArrayList<Course> courses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvCourses = findViewById(R.id.rvCourses);
        courses = new ArrayList<>();

        loadCourses();

        // ✅ THIS IS THE RESTORED LINE
        rvCourses.setLayoutManager(new LinearLayoutManager(this));

        rvCourses.setAdapter(new CourseAdapter(courses, this));
    }

    private void loadCourses() {
        courses.add(new Course("CS02310", "Mobile Application Development", "Sir Muhammad Arslan", "arslan@example.com", "Introduces Android app development.", 3));
        courses.add(new Course("CS02210", "Software Construction and Development", "Dr. Ali", "ali@example.com", "Focuses on software construction.", 3));
        courses.add(new Course("CS03120", "E-commerce", "Ms. Sara", "sara@example.com", "Covers online business models.", 3));
        courses.add(new Course("CS02140", "Database Systems", "Mr. Usman", "usman@example.com", "Relational databases and SQL.", 3));
        courses.add(new Course("CS02230", "Software Requirements Engineering", "Dr. Khan", "khan@example.com", "Elicitation and analysis of requirements.", 3));
        courses.add(new Course("CS02320", "Software Design and Architecture", "Ms. Fatima", "fatima@example.com", "Design patterns and system architecture.", 3));
        courses.add(new Course("CS02160", "Computer Networks", "Mr. Ahmed", "ahmed@example.com", "Networking protocols and OSI model.", 3));
        courses.add(new Course("CS03240", "Software Quality Assurance", "Dr. Zain", "zain@example.com", "Testing and quality control processes.", 3));
    }

    @Override
    public void onItemClick(int position) {
        Course course = courses.get(position);
        Intent intent = new Intent(MainActivity.this, CourseDetailActivity.class);
        intent.putExtra("EXTRA_CODE", course.getCode());
        intent.putExtra("EXTRA_TITLE", course.getTitle());
        intent.putExtra("EXTRA_INSTRUCTOR", course.getInstructor());
        intent.putExtra("EXTRA_EMAIL", course.getEmail());
        intent.putExtra("EXTRA_DESCRIPTION", course.getDescription());
        intent.putExtra("EXTRA_CREDITS", course.getCreditHours());
        startActivity(intent);
    }
}