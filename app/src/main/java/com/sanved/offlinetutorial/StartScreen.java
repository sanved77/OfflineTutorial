package com.sanved.offlinetutorial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

public class StartScreen extends AppCompatActivity implements View.OnClickListener{

    CardView html, sql, javascript;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen);

        html = findViewById(R.id.cvHtml);
        sql = findViewById(R.id.cvSql);
        javascript = findViewById(R.id.cvJs);

        html.setOnClickListener(this);
        sql.setOnClickListener(this);
        javascript.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(StartScreen.this, ListLoader.class);

        switch(view.getId()){
            case R.id.cvHtml:
                intent.putExtra("type", "html.json");
                break;

            case R.id.cvSql:
                intent.putExtra("type", "sql.json");
                break;

            case R.id.cvJs:
                intent.putExtra("type", "js.json");
                break;
        }

        startActivity(intent);

    }
}
