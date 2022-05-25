package ahmux.nutritionpoint;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.like.LikeButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FavListAdapter extends RecyclerView.Adapter<FavListAdapter.Viewholder> {
    private Context context;

    private ArrayList<recipe_card_model> cardModelArrayList;

    public FavListAdapter(Context context, ArrayList<recipe_card_model> cardModelArrayList) {
        this.context = context;
        this.cardModelArrayList = cardModelArrayList;
    }

    @NonNull
    @Override
    public FavListAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
        Picasso.get().load((model.getRecipeImg())).into(holder.recipeImage);
    }

    @Override
    public int getItemCount() {
        return cardModelArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private ImageView recipeImage;
        private TextView recipeName, calories,servings,fats,protein,mealtype;
        private LikeButton like;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            recipeName = itemView.findViewById(R.id.RecipeName);
            recipeImage = itemView.findViewById(R.id.RecipeImage);
            calories = itemView.findViewById(R.id.calorie);
            servings = itemView.findViewById(R.id.servings);
            fats = itemView.findViewById(R.id.fats);
            protein = itemView.findViewById(R.id.protein);
            mealtype = itemView.findViewById(R.id.mealtype);
            like=itemView.findViewById(R.id.star_button);
        }
    }
}
