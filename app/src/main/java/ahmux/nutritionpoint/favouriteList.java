package ahmux.nutritionpoint;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class favouriteList extends AppCompatActivity {
    private DatabaseReference ref;
    private ArrayList<recipe_card_model> a;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favlist_main);
        RecyclerView rv= findViewById(R.id.favrecy);
        a= new ArrayList<>();
        ref= FirebaseDatabase.getInstance().getReference().child("Fav").child("user");
        ref.child("recipe").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren())
                {
//(String recipeName, String recipeImg, String calories, String serving, String mealType, String fat, String protein,String recipeurl) {
                    recipe_card_model r= ds.getValue(recipe_card_model.class);
                    //Toast.makeText(favouriteList.this, r., Toast.LENGTH_SHORT).show();
                    a.add(r);
                   // a.add(new recipe_card_model(r.getRecipeName(),r.getRecipeImg(),r.getCalories(),r.getServing(),r.getMealType(),r.getFat(),r.getProtein(),r.getRecipeurl()));
                }
                FavListAdapter fa= new FavListAdapter(getApplicationContext(),a);
                rv.setAdapter(fa);
                rv.setLayoutManager(new LinearLayoutManager(favouriteList.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
