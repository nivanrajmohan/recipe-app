package com.rajapp.camaross1220.recipeapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rajapp.camaross1220.recipeapp.R;
import com.rajapp.camaross1220.recipeapp.ViewRecipeActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerRecipeAdapter extends RecyclerView.Adapter<RecyclerRecipeAdapter.RecipeHolder>{

    private ArrayList<Recipe> recipes = new ArrayList<>();
    private Context ctx;

    public RecyclerRecipeAdapter(ArrayList<Recipe> recipes, Context ctx) {
        this.recipes = recipes;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public RecipeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.recipe_layout,parent,false);
        RecipeHolder rh = new RecipeHolder(v);
        return rh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeHolder holder, int position) {
        final Recipe r = recipes.get(position);
        holder.txtfoodname.setText(r.getName());
        holder.txtfooddesc.setText(r.getDescription());
        Uri image = Uri.parse(r.getImage_url());
        Glide.with(ctx).load(image).into(holder.civfood);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ctx, ViewRecipeActivity.class);
                i.putExtra("Name",r.getName());
                i.putExtra("ID",r.getId());
                i.putExtra("Type",r.getType());
                ctx.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public class RecipeHolder extends RecyclerView.ViewHolder{

        private TextView txtfoodname,txtfooddesc;
        private CircleImageView civfood;

        public RecipeHolder(@NonNull View itemView) {
            super(itemView);
            txtfoodname = itemView.findViewById(R.id.foodname);
            txtfooddesc = itemView.findViewById(R.id.fooddesc);
            civfood = itemView.findViewById(R.id.foodimage);
        }
    }

}
