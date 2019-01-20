package by.darya.zdzitavetskaya.notice.utils.realm;

import android.support.annotation.NonNull;

import by.darya.zdzitavetskaya.notice.common.constants.Constants;
import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

public class MigrationManager implements RealmMigration {
    @Override
    public void migrate(@NonNull DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();

        RealmObjectSchema message = schema.get(Constants.MESSAGE);

        switch ((int) oldVersion) {
            case CustomRealmConfiguration.VERSION_1:
                realm.where("NoteModel").findAll().deleteAllFromRealm();
                message = upgradeMessageInfo(schema, message);
        }
    }

    private RealmObjectSchema upgradeMessageInfo(RealmSchema schema, RealmObjectSchema message)
    {
        if (message == null) {
            message = schema.create(Constants.MESSAGE)
                    .addField("text", String.class);
        } else {
            if (!message.hasField("text")) {
                message = message.addField("text", String.class);
            }
        }
        return message;
    }
}