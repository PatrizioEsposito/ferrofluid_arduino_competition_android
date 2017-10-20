package it.na.margheritadisavoia.ferrofluid.Classes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import it.na.margheritadisavoia.ferrofluid.R;

/**
 * Created by pe-la on 18/10/2017.
 */


public class homeRecyclerAdapter extends RecyclerView.Adapter<homeRecyclerAdapter.ViewHolder>{


    private List<Planets> planetsData;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView planetName;
        public ImageView planetImage;
        public ViewHolder(View v){
            super(v);
            planetImage = (ImageView)v.findViewById(R.id.planetImage);
            planetName = (TextView)v.findViewById(R.id.planetName);

        }

    }

    public homeRecyclerAdapter(List<Planets> planetsData){

        this.planetsData = planetsData;

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int pos){

        Planets planets = planetsData.get(pos);
        viewHolder.planetName.setText(planets.getPlanetName());
        viewHolder.planetImage.setImageResource(planets.getPlanetImage());


    }

    @Override
    public int getItemCount(){

        return planetsData.size();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardplanet, parent, false);
        return new ViewHolder(v);
    }
}
