package by.darya.zdzitavetskaya.notice.utils.realm;

import io.realm.RealmConfiguration;

public class CustomRealmConfiguration {
    static final int VERSION_1 = 0;

    public static RealmConfiguration getRealmConfiguration() {
        return new RealmConfiguration.Builder()
                .schemaVersion(VERSION_1)
                .migration(new MigrationManager())
                .build();
    }
}
