package com.rfong.pokedex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rfong.pokedex.models.NamePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rfong on 4/28/17.
 */

public class PokemonListAdapter extends BaseAdapter {

    private Context mContext;
    private List<NamePair> mPokemonNamePairs; // List of pokemon names and urls
    private LayoutInflater mInflater;

    public PokemonListAdapter(Context c) {
        mContext = c;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mPokemonNamePairs = new ArrayList<>();
    }

    public void addPokemonNamePairs(List<NamePair> pokemon) {
        mPokemonNamePairs.addAll(pokemon);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mPokemonNamePairs.size();
    }

    @Override
    public Object getItem(int i) {
        return mPokemonNamePairs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0; // ignore this method
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if( view == null ){
            view = mInflater.inflate(R.layout.view_pokemon_list_item, viewGroup, false);
        }
        TextView name = (TextView) view.findViewById(R.id.tv_lv_name);
        name.setText(Utils.toUpper(mPokemonNamePairs.get(i).getName()));
        return view;
    }
}
