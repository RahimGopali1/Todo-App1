package com.example.todoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TodoActivity extends AppCompatActivity {
    public static final String TAG = TodoActivity.class.getSimpleName();
    public static final String TODO_INDEX = "todoIndex";
    private String[] todos;
    Button nextButton;
    Button prevButton;
    Button detailButton;
    private TextView todotitle;
    int orientation;
    private int todoIndex = 0;
    private static final String IS_TODO_COMPLETE = "com.example.isTodoComplete";

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(TODO_INDEX, todoIndex);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        orientation = getResources().getConfiguration().orientation;
        setContentView(R.layout.activity_todo);
        final TextView todo_title;
        todo_title = (TextView) findViewById(R.id.todo_title);
        if(savedInstanceState !=null){
            todoIndex = savedInstanceState.getInt(TODO_INDEX, 0);
            todo_title.setText(todos[todoIndex]);
        }

        todotitle = findViewById(R.id.todo_title);
        nextButton = findViewById(R.id.next_btn);
        detailButton = findViewById(R.id.detail_btn);
        prevButton = findViewById(R.id.prev_button);

        Resources res = getResources();
        todos = res.getStringArray(R.array.todos);
        Log.d(TAG,"**** Just to say the PC is in onCreate!"+todos.length);

        todo_title.setText(todos[todoIndex]);

        todotitle.setText(todos[0]);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todoIndex = (todoIndex +1) % todos.length;
                todotitle.setText(todos[todoIndex]);
                setTextViewComplete("");
            }
        });

        prevButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                todoIndex = (todoIndex - 1) % todos.length;
                todotitle.setText(todos[todoIndex]);
                setTextViewComplete("");
            }
            });
            detailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = DetailActivity.makeIntent(TodoActivity.this, todoIndex);
                startActivity(intent);
            }

//            protected void  onRestoreInstanceState(@NonNull Bundle saveInstancestate){
//
//            }
        });

    }

    private void setTextViewComplete(String s) {
        final TextView textViewComplete;

        textViewComplete = (TextView) findViewById(R.id.textViewComplete);
        textViewComplete.setText(s);
    }
}