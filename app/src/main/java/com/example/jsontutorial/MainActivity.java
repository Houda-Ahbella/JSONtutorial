package com.example.jsontutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.jsontutorial.JSON.JSONwrite;
import com.example.jsontutorial.beans.Company;
import com.example.jsontutorial.JSON.ReadJSON;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    private EditText outputText;
    private Button button;
    private Button button1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        this.button = (Button) this.findViewById(R.id.button);
        this.button1 = (Button) this.findViewById(R.id.button1);


        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runExample(view);
            }
        });

        this.outputText = (EditText) this.findViewById(R.id.editText);
        this.button = (Button) this.findViewById(R.id.button);
        this.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runExample1(view);
            }
        });
    }

    public void runExample1(View view)  {
        try {
            StringWriter output = new StringWriter();

            Company company = JSONwrite.createCompany();


            JSONwrite.writeJsonStream(output, company);

            String jsonText = output.toString();

            outputText.setText(jsonText);
        }
        catch(Exception e)  {
            outputText.setText(e.getMessage());
            e.printStackTrace();
        }
    }

    public void runExample(View view)
    {
        try {
            Vector<Company> compans = ReadJSON.readCompanyJSONFile(this);
            ArrayAdapter<Company> adapter =new ArrayAdapter<Company>
                    (this,android.R.layout.simple_list_item_1, compans);
            ListView listView = (ListView) findViewById(R.id.listView1);
            listView.setAdapter(adapter);


        } catch(Exception e)  {
            outputText.setText(e.getMessage());

            e.printStackTrace();
        }
    }

}
