package ahmux.nutritionpoint;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class discoverRecipe extends AppCompatActivity {
    private static ArrayList<recipe_card_model> RecipeModelArrayList;
    private static RecyclerView courseRV;
    private static EditText et;
    Handler mainHandler= new Handler();

    String food,meal="";
    String calorie="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.discover_recipe_activity);
        courseRV = findViewById(R.id.idRVCourse);
        et=findViewById(R.id.search);

        Button button = findViewById(R.id.getdata);
        RecipeModelArrayList = new ArrayList<>();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),"Heyyyy",Toast.LENGTH_LONG).show();
                food=et.getText().toString();
                new Fetch().start();
                et.getText().clear();
                recipeAdapter ra = new recipeAdapter(getApplicationContext(),RecipeModelArrayList);
                courseRV.setAdapter(ra);
                courseRV.setLayoutManager(new LinearLayoutManager(discoverRecipe.this));

            }
        });


    }
    class Fetch extends Thread{
        @Override
        public void run() {

            String allStrings="";

            try{
                URL myUrl = new URL("https://api.edamam.com/api/recipes/v2?type=public&app_id=bee89654&app_key=5ab4040cc985ff59387ab89f5d297b8e&q="+food);
                HttpURLConnection connection =(HttpURLConnection) myUrl.openConnection();
                InputStream streamReader = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(streamReader));
                String inputLine;
                //StringBuilder stringBuilder = new StringBuilder();
                //Check if the line we are reading is not null

                while((inputLine = reader.readLine()) != null){
                    allStrings=allStrings+inputLine;
                }
                if(!allStrings.isEmpty()) {

                    JSONObject jsonObject = new JSONObject(allStrings);
                    JSONArray h = (JSONArray) jsonObject.get("hits");
                    for(int i=0;i<h.length();i++) {
                        JSONObject rec=h.getJSONObject(i);
                        JSONObject recipe = rec.getJSONObject("recipe");
                        String calories=Double.toString(recipe.getDouble("calories"));
                        JSONArray mealType= (JSONArray) recipe.get("mealType");
                        String meal=mealType.getString(0);
                        String recipeurl =recipe.getString("url");
                        JSONObject totalNutrients=recipe.getJSONObject("totalNutrients");
                        JSONObject fat=totalNutrients.getJSONObject("FAT");
                        String fat_q= Double.toString(fat.getDouble("quantity"));
                        JSONObject protein=totalNutrients.getJSONObject("PROCNT");
                        String protein_q= Double.toString(protein.getDouble("quantity"));
                        String portion=Integer.toString(recipe.getInt("yield"));
                        String recipeName=recipe.getString("label");
                        JSONObject imgs=recipe.getJSONObject("images");
                        JSONObject thumbnail=imgs.getJSONObject("THUMBNAIL");
                        String imu=thumbnail.getString("url");
                        RecipeModelArrayList.add(new recipe_card_model(recipeName, imu,calories,portion,meal,fat_q,protein_q,recipeurl));
                        //(String recipeName, String recipeImg, String calories, String serving, String mealType, String fat, String protein)
                    }
//                    System.out.println("calories = "+calories);
//                    System.out.println("Meal Type = "+meal);
//                    System.out.println("Recipe Name = "+recipe.getString("label"));
//                    System.out.println("Servings = "+portion);
//                    System.out.println("Total Fats = "+fat_q+" grams");
//                    System.out.println("Total Protein = "+protein_q+" grams");
//                    System.out.println("recipeUrl = "+recipeimg);
//                    JSONObject rec = h.getJSONObject(0);
//                    JSONObject recipe = rec.getJSONObject("recipe");
//                    JSONArray mealType = (JSONArray) recipe.get("mealType");
//                    meal = mealType.getString(0);
//                    calorie=String.valueOf(recipe.getDouble("calories"));
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(getApplicationContext(),"Meal type "+meal+" Calories "+calorie,Toast.LENGTH_LONG).show();
//                        }
//                    });

                }

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }
    }
}