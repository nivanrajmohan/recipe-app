package com.rajapp.camaross1220.recipeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rajapp.camaross1220.recipeapp.Adapter.DBHelper;
import com.rajapp.camaross1220.recipeapp.Adapter.Recipe;
import com.rajapp.camaross1220.recipeapp.Adapter.RecyclerRecipeAdapter;

import java.util.ArrayList;

public class RecipesActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spintype;
    private FloatingActionButton fabadd;
    private RecyclerView recyclertype;
    private String type;
    private TextView txtrecipealert;
    private ArrayList<Recipe> recipes = new ArrayList<>();
    private RecyclerRecipeAdapter rra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        spintype = findViewById(R.id.typespinner);
        fabadd = findViewById(R.id.fabadd);
        recyclertype = findViewById(R.id.recylertype);
        txtrecipealert = findViewById(R.id.txtrecipes);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.types,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spintype.setAdapter(adapter);
        SetUpRecycler();
        GetRecipeAll();
        spintype.setOnItemSelectedListener(this);
        fabadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RecipesActivity.this,AddRecipeActivity.class));
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (i == 0){
            GetRecipeAll();
        }
        else {
            type = adapterView.getItemAtPosition(i).toString();
            GetRecipe(type);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void GetRecipe(String foodtype){
        recipes.clear();
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM recipe_details where type like '"+foodtype+"'",null);
        if (c.moveToFirst()){
            txtrecipealert.setVisibility(View.GONE);
            do {
                Recipe recipe = new Recipe();
                recipe.setId(c.getString(0));
                recipe.setImage_url(c.getString(1));
                recipe.setName(c.getString(2));
                recipe.setDescription(c.getString(3));
                recipe.setIngredients(c.getString(4));
                recipe.setInstruction(c.getString(5));
                recipe.setType(c.getString(6));
                recipes.add(recipe);
                rra.notifyDataSetChanged();
            }while (c.moveToNext());
        }
        else {
            txtrecipealert.setVisibility(View.VISIBLE);
        }
    }

    private void GetRecipeAll(){
        recipes.clear();
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM recipe_details",null);
        if (c.moveToFirst()){
            txtrecipealert.setVisibility(View.GONE);
            do {
                Recipe recipe = new Recipe();
                recipe.setId(c.getString(0));
                recipe.setImage_url(c.getString(1));
                recipe.setName(c.getString(2));
                recipe.setDescription(c.getString(3));
                recipe.setIngredients(c.getString(4));
                recipe.setInstruction(c.getString(5));
                recipe.setType(c.getString(6));
                recipes.add(recipe);
                rra.notifyDataSetChanged();
            }while (c.moveToNext());
        }
        else {
            txtrecipealert.setVisibility(View.VISIBLE);
        }
    }

    private void SetUpRecycler(){
        rra = new RecyclerRecipeAdapter(recipes,this);
        recyclertype.setAdapter(rra);
        recyclertype.setLayoutManager(new LinearLayoutManager(this));
    }
}
