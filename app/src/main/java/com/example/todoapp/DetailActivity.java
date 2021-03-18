package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.todoapp.TodoActivity.TODO_INDEX;

public class DetailActivity extends AppCompatActivity {
    public static final String TAG= DetailActivity.class.getSimpleName();
    public static final String TODO_INDEX_EXTRA = " com.example.todoIndex";
    private String[] todoDetail;
    private TextView detailTextView;
    private int todoIndex;

    private static final String IS_TODO_COMPLETE = "com.example.isTodoComplete";
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(TODO_INDEX, todoIndex);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (savedInstanceState != null){
            todoIndex = savedInstanceState.getInt(TODO_INDEX, 0);
        }
        detailTextView = findViewById(R.id.detail_tv);

        Resources res = getResources();
        todoDetail = res.getStringArray(R.array.todos_detail);

        Intent intent = getIntent();
        todoIndex = intent.getIntExtra(TODO_INDEX_EXTRA, 0);

        detailTextView.setText(todoDetail[todoIndex]);

        CheckBox checkboxIsComplete = (CheckBox)findViewById(R.id.checkBox);
        checkboxIsComplete.setOnClickListener(mTodoListener);

    }

    private void updateTextViewTodoDetail(int todoIndex) {

        final TextView textViewTodoDetail;
        textViewTodoDetail = (TextView) findViewById(R.id.detail_tv);

        /* display the first task from mTodo array in the TodoTextView */
        textViewTodoDetail.setText(todoDetail[todoIndex]);

    }

    public static Intent makeIntent(Context context, int todoIndex){
        Intent intent = new Intent(context,DetailActivity.class);
        intent.putExtra(TODO_INDEX_EXTRA,todoIndex);
        return intent;
    }

    private View.OnClickListener mTodoListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.checkBox:
                CheckBox checkBoxIsComplete = (CheckBox)findViewById(R.id.checkBox);
                setIsComplete(checkBoxIsComplete.isChecked());
                finish();
                break;
                default:
                    break;
            }
        }
    };
private void setIsComplete(boolean isChecked) {

    /* celebrate with a static Toast! */
    if(isChecked){
        Toast.makeText(DetailActivity.this,
                "Hurray, it's done!", Toast.LENGTH_LONG).show();
    } else {
        Toast.makeText(DetailActivity.this,
                "There is always tomorrow!", Toast.LENGTH_LONG).show();
    }

    Intent intent = new Intent();
//    intent.putExtra(IS_TODO_COMPLETE, isChecked);
    setResult(RESULT_OK, intent);
}

}