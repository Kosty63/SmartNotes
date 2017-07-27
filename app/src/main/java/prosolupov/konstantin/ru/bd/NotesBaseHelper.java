package prosolupov.konstantin.ru.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static prosolupov.konstantin.ru.bd.ShemaNotesBD.CreateBD.NOTES_TABLE;

/**
 * Created by home on 26.06.2017.
 */

public class NotesBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATA_BASE = "NotesBase.db";

    public NotesBaseHelper(Context context) {
        super(context, DATA_BASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + NOTES_TABLE + "(" +
        "_id integer primary key autoincrement, " +
/*                ShemaNotesBD.CreateBD.Column.ID_NOTES + ", " +*/
                ShemaNotesBD.CreateBD.Column.TITLE + ", " +
                ShemaNotesBD.CreateBD.Column.BODY + ")" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
