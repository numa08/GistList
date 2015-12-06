package net.numa08.gistlist;

import android.app.Application;

import net.numa08.gistlist.realm.providers.RealmProviderImpl_;

import io.realm.RealmConfiguration;

public class GistList extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        final RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this).name("gist-list").build();
        RealmProviderImpl_.getInstance_(this).setRealmConfiguration(realmConfiguration);
    }

}
