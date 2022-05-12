package ahmux.nutritionpoint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class recipeAdapter extends RecyclerView.Adapter<recipeAdapter.Viewholder> {

    private Context context;
    private ArrayList<recipe_card_model> cardModelArrayList;

    // Constructor
    public recipeAdapter(Context context,ArrayList<recipe_card_model> cardModelArrayList) {
        this.context = context;
        this.cardModelArrayList = cardModelArrayList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_card_layout, parent, false);
        return new Viewholder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        recipe_card_model model= cardModelArrayList.get(position);
        holder.recipeName.setText(model.getRecipeName());
        holder.calories.setText(model.getCalories());
        holder.mealtype.setText(model.getMealType());
        holder.servings.setText(model.getServing());
        holder.fats.setText(model.getFat());
        holder.protein.setText(model.getProtein());
        // holder.recipeImage.setImageURI((URI)model.getRecipeImg());
        Picasso.get().load(("https://community.tm/attachments/thumb-060-batman-arkham-knight-1-1-jpg.15883/")).into(holder.recipeImage);
        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Uri uri= Uri.parse(model.getRecipeImg());
                Intent intent= new Intent(Intent.ACTION_VIEW,uri);
                view.getContext().startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        // this method is used for showing number
        // of card items in recycler view.
        return cardModelArrayList.size();
    }

    // View holder class for initializing of
    // your views such as TextView and Imageview.
    public class Viewholder extends RecyclerView.ViewHolder {
        private ImageView recipeImage;
        private TextView recipeName, calories,servings,fats,protein,mealtype;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            recipeName = itemView.findViewById(R.id.RecipeName);
            recipeImage = itemView.findViewById(R.id.RecipeImage);
            calories = itemView.findViewById(R.id.calorie);
            servings = itemView.findViewById(R.id.servings);
            fats = itemView.findViewById(R.id.fats);
            protein = itemView.findViewById(R.id.protein);
            mealtype = itemView.findViewById(R.id.mealtype);
        }
    }
}