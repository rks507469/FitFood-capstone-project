package ahmux.nutritionpoint;

public class recipe_card_model {
    private String recipeName;
    private String recipeImg;
    private String calories;
    private String serving;
    private String mealType;
    private String fat;
    private String protein;

    public recipe_card_model(String recipeName, String recipeImg, String calories, String serving, String mealType, String fat, String protein) {
        this.recipeName = recipeName;
        this.recipeImg = recipeImg;
        this.calories = calories;
        this.serving = serving;
        this.mealType = mealType;
        this.fat = fat;
        this.protein = protein;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeImg() {
        return recipeImg;
    }

    public void setRecipeImg(String recipeImg) {
        this.recipeImg = recipeImg;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getServing() {
        return serving;
    }

    public void setServing(String serving) {
        this.serving = serving;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }
}
