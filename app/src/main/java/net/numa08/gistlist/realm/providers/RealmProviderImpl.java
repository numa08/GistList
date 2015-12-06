package net.numa08.gistlist.realm.providers;

import org.androidannotations.annotations.EBean;

import io.realm.Realm;
import io.realm.RealmConfiguration;

@EBean(scope = EBean.Scope.Singleton)
public class RealmProviderImpl implements RealmProvider {

    private RealmConfiguration realmConfiguration;

    @Override
    public Realm getRealm() {
        return Realm.getInstance(realmConfiguration);
    }

    public RealmConfiguration getRealmConfiguration() {
        return realmConfiguration;
    }

    public void setRealmConfiguration(RealmConfiguration realmConfiguration) {
        this.realmConfiguration = realmConfiguration;
    }
}
