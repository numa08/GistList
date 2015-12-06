package net.numa08.gistlist.main;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import net.numa08.gistlist.realm.models.Gist;
import net.numa08.gistlist.widget.GistView;
import net.numa08.gistlist.widget.GistView_;

import io.realm.RealmBaseAdapter;
import io.realm.RealmResults;

public class GistListAdapter extends RealmBaseAdapter<Gist>{

    private final Picasso picasso;
    public GistListAdapter(Context context, RealmResults<Gist> realmResults, Picasso picasso) {
        super(context, realmResults, true);
        this.picasso = picasso;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final GistView gistView;
        if (convertView != null) {
            gistView = (GistView) convertView;
        } else {
            gistView = GistView_.build(parent.getContext());
            gistView.setPicasso(picasso);
        }
        gistView.setGist(getItem(position));
        return gistView;
    }
}
