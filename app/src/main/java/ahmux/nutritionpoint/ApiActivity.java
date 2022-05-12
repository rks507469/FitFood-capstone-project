package ahmux.nutritionpoint;

import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiActivity extends AppCompatActivity {

    String food;
    EditText et;
    TextView tv1, tv2,tv3, tv4, tv5;
    View v1, v2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);

        et = (EditText)findViewById(R.id.editText);
        tv1 = (TextView)findViewById(R.id.textView13);
        tv2 = (TextView)findViewById(R.id.textView16);
        tv3 = (TextView)findViewById(R.id.textView17);
        tv4 = (TextView)findViewById(R.id.textView18);
        tv5 = (TextView)findViewById(R.id.textView19);

    }


    public void calculateClk(View view) {
        food = et.getText().toString();
        Toast.makeText(this, "Searching...", Toast.LENGTH_SHORT).show();
        new MyAsyncTask().execute();
    }


    /* #####AsyncTask Subclass################################################################### */
    private class MyAsyncTask extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... strings) {

            String allStrings;
            try{
                URL myUrl = new URL("https://api.edamam.com/api/recipes/v2?type=public&app_id=bee89654&app_key=5ab4040cc985ff59387ab89f5d297b8e&q="+food);
                HttpURLConnection connection =(HttpURLConnection) myUrl.openConnection();
                connection.setConnectTimeout(700);
                connection.connect();

                //Create a new InputStreamReader
                InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
                //Create a new buffered reader and String Builder
                BufferedReader reader = new BufferedReader(streamReader);

                String inputLine;
                StringBuilder stringBuilder = new StringBuilder();
                //Check if the line we are reading is not null
                while((inputLine = reader.readLine()) != null){
                    stringBuilder.append(inputLine);
                }
                reader.close();
                streamReader.close();
                allStrings = stringBuilder.toString();
                publishProgress(allStrings);

            }catch(Exception e){}
            return "";
        }

        @Override
        protected void onProgressUpdate(String... values) {
            try {

                JSONObject j            = new JSONObject(values[0]);
                JSONArray hits          = (JSONArray) j.get("hits");
                JSONObject firstObj     = hits.getJSONObject(0);
                JSONObject recipe       = firstObj.getJSONObject("recipe");
                String dishName         = recipe.getString("label");
                Double calories         = recipe.getDouble("calories");
                String recipeExtUrl     = recipe.getString("url");
                JSONArray mealType      = (JSONArray) recipe.get("mealType");


//                JSONObject j = new  JSONObject(values[0]);
//                JSONArray h= (JSONArray) j.get("hits");
//                JSONObject rec = h.getJSONObject(0);
//                JSONObject fields = rec.getJSONObject("fields");
//                String calories = fields.getString("nf_calories");
//                String fat = fields.getString("nf_total_fat");
//                String name = fields.getString("item_name");


                tv2.setText("Nutrition Facts");
                tv3.setText("Name: " + dishName);
                tv4.setText("Calories: " + Math.ceil(calories));
                tv5.setText("Meal Type: " + mealType.getString(0));
                v1 = findViewById(R.id.view);
                v1.setVisibility(View.VISIBLE);
                v2 = findViewById(R.id.view);
                v2.setVisibility(View.VISIBLE);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
