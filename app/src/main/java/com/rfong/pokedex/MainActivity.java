package com.rfong.pokedex;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.rfong.pokedex.models.NamePair;
import com.rfong.pokedex.models.PokemonList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends Activity {

    private static final String TAG = "TAG-MainActivity"; // debug tag

    private Context mContext;
    private PokemonService mApi;

    private ListView mPokemonList;
    private PokemonListAdapter mPokemonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // declare activity as context
        mContext = this;

        // Build retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(C.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Instantiate our service
        mApi = retrofit.create(PokemonService.class);

        // Create the listview and bind it to an adapter
        mPokemonList = (ListView) findViewById(R.id.lv_pokemon);
        mPokemonAdapter = new PokemonListAdapter(mContext);
        mPokemonList.setAdapter(mPokemonAdapter);

        // Handle item click events
        mPokemonList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the pokemon clicked (the name and url)
                NamePair pair = (NamePair) adapterView.getItemAtPosition(i);

                // Begin an intent to start new activity
                Intent intent = new Intent(mContext, PokemonActivity.class);
                Log.d(TAG, "Putting " + pair.getUrl());
                intent.putExtra(C.KEY_POKEMON_URL, pair.getUrl());
                intent.putExtra(C.KEY_POKEMON_NAME, pair.getName());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Everytime activity resumes, get pokemon and update the list
        if (mPokemonAdapter.isEmpty()) {
            // Make retrofit call
            Call<PokemonList> pokemonList = mApi.getPokemon();

            // Define callbacks for success or failure
            pokemonList.enqueue(new Callback<PokemonList>() {
                @Override
                public void onResponse(Call<PokemonList> call, Response<PokemonList> response) {
                    // Obtain the pokemonlist object and set the names
                    PokemonList pokemonList = response.body();
                    mPokemonAdapter.addPokemonNamePairs(pokemonList.getNamePairs());
                }

                @Override
                public void onFailure(Call<PokemonList> call, Throwable t) {
                    // Handle error
                    Toast.makeText(mContext, "Could not retrieve list of Pokemon", Toast.LENGTH_LONG);
                    Log.e(TAG, "Could not retrive list of Pokemon");
                    t.printStackTrace();
                }
            });
        }
    }
}
