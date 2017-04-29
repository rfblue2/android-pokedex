package com.rfong.pokedex;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rfong.pokedex.models.NamePair;
import com.rfong.pokedex.models.Pokemon;

import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonActivity extends Activity {

    private static final String TAG = "TAG-PokemonActivity";

    private Context mContext;
    private PokemonService mApi;

    private Pokemon mPokemon;
    private NamePair mPokemonName;

    private TextView mNameView;
    private ImageView mPokemonView;
    private Button mBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);

        // Declare this activity as context
        mContext = this;

        // Build retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(C.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Instantiate our service
        mApi = retrofit.create(PokemonService.class);

        // Get parameters passed in
        mPokemonName = new NamePair(getIntent().getStringExtra(C.KEY_POKEMON_NAME),
                                    getIntent().getStringExtra(C.KEY_POKEMON_URL));

        // Set the Name (so at least user sees name while rest loads)
        mNameView = (TextView) findViewById(R.id.tv_name);

        mNameView.setText(Utils.toUpper(mPokemonName.getName())); // have name ready (while rest of activity loads)

        // Grab all other UI elements
        mPokemonView = (ImageView) findViewById(R.id.im_picture);

        mBack = (Button) findViewById(R.id.btn_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // exit this activity and return to previously viewed activity
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mPokemon == null) {
            String url = mPokemonName.getUrl();

            // Get ID from URL, which has form BASE_URL + /pokemon/:id
            final int id = Integer.parseInt(url.substring((C.BASE_URL + "pokemon").length() + 1, url.length() - 1));

            Call<Pokemon> pokemonCall = mApi.getPokemon(id);
            pokemonCall.enqueue(new Callback<Pokemon>() {
                @Override
                public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                    Pokemon p = response.body();

                    // Set the picture
                    new ImageDownloadTask(mPokemonView).execute(p.getSpriteUrl());

                    // TODO Set other info....
                }

                @Override
                public void onFailure(Call<Pokemon> call, Throwable t) {
                    // Handle error
                    Toast.makeText(mContext, "Could not retrieve Pokemon " + id, Toast.LENGTH_LONG);
                    Log.e(TAG, "Could not retrive Pokemon " + id);
                    t.printStackTrace();
                }
            });
        }

    }

    private class ImageDownloadTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public ImageDownloadTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
