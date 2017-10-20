package it.na.margheritadisavoia.ferrofluid;

import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import it.na.margheritadisavoia.ferrofluid.Classes.Planets;
import it.na.margheritadisavoia.ferrofluid.Classes.homeRecyclerAdapter;

public class MainActivity extends AppCompatActivity {


    public List<Planets> planetsList = new ArrayList<>(); //Upcastato se no non funziona
    private RecyclerView recyclerView;
    private homeRecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instantiateAll();
        extrapulateJson();
        createList();


        //

        if(planetsList.size() >= 3){

            recyclerView.setAdapter(recyclerAdapter);


        }

        //

    }

    private void instantiateAll(){

        recyclerView = (RecyclerView)findViewById(R.id.rv);


    }

    private void createList(){

        recyclerAdapter = new homeRecyclerAdapter(planetsList);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);

        recyclerView.setLayoutManager(gridLayoutManager);


    }

    private void extrapulateJson(){

        String jsonString = getString(R.string.planets_json);

        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("planets");
            for(int i = 0; i <= jsonArray.length();i++){

                String planetName = jsonArray.get(i).toString();

                switch(planetName){

                    case("Saturno"):
                        Planets planet = new Planets(planetName, R.drawable.saturno);
                        planetsList.add(planet);
                        break;

                    case("Marte"):
                        Planets planet1 = new Planets(planetName, R.drawable.marte);
                        planetsList.add(planet1);
                        break;

                    case("Giove"):
                        Planets planet2 = new Planets(planetName, R.drawable.giove);
                        planetsList.add(planet2);
                        break;


                }

                Log.d("Debug", String.valueOf(planetsList.size()));

            }

        }catch(JSONException e){
            Log.d("Exception", e.getMessage());
        }


    }



}
