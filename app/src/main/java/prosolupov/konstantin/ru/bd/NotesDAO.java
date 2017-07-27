package prosolupov.konstantin.ru.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import prosolupov.konstantin.ru.model.Notes;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static prosolupov.konstantin.ru.bd.ShemaNotesBD.CreateBD.Column.ID_NOTES;
import static prosolupov.konstantin.ru.bd.ShemaNotesBD.CreateBD.NOTES_TABLE;

/**
 * Created by home on 05.07.2017.
 */

public class JobBd {

    private static SQLiteDatabase mDataBase;
    private Context context;

    public JobBd(Context context){
        this.context = context;
        mDataBase = new NotesBaseHelper(context).getWritableDatabase();
    }

/*    private SQLiteDatabase connectBd(){
        mDataBase = new NotesBaseHelper(context).getWritableDatabase();
        return mDataBase;
    }*/

    private List getListNotes(){

        Cursor cursor = getCursor();
        List listNotes = new ArrayList();
        while (cursor.moveToNext()){
            Notes mNotes = new Notes();
            mNotes.setId(cursor.getInt(cursor.getColumnIndex("_id")));
            mNotes.setTitle(cursor.getString(cursor.getColumnIndex(ShemaNotesBD.CreateBD.Column.TITLE)));
            mNotes.setBody(cursor.getString(cursor.getColumnIndex(ShemaNotesBD.CreateBD.Column.BODY)));
            listNotes.add(mNotes);
        }
        cursor.close();
        return listNotes;
    }

    private static ContentValues setContentValues(String title, String body){
        ContentValues values = new ContentValues();
        values.put(ShemaNotesBD.CreateBD.Column.TITLE, title);
        values.put(ShemaNotesBD.CreateBD.Column.BODY, body);
        return values;
    }

    public List updateNotesBd(Context context, int id, String title, String body){
        /*connectBd(context);*/
        mDataBase.update(NOTES_TABLE, setContentValues(title, body), "_id = ?", new String[]{String.valueOf(id)});

        Cursor mCursor = mDataBase.query(NOTES_TABLE, new String[]{"_id", ShemaNotesBD.CreateBD.Column.TITLE, ShemaNotesBD.CreateBD.Column.BODY},
                "_id = " +  id,
                null,
                null,
                null,
                null);

        List editList = new ArrayList();
        while (mCursor.moveToNext()){
            Notes mNotes = new Notes();
            mNotes.setId(mCursor.getInt(mCursor.getColumnIndex("_id")));
            mNotes.setTitle(mCursor.getString(mCursor.getColumnIndex(ShemaNotesBD.CreateBD.Column.TITLE)));
            mNotes.setBody(mCursor.getString(mCursor.getColumnIndex(ShemaNotesBD.CreateBD.Column.BODY)));
            editList.add(mNotes);
        }
        mCursor.close();
        return editList;
    }

    private Cursor getCursor(){

        final Cursor mCursor = mDataBase.query(NOTES_TABLE,
                new String[]{"_id", ShemaNotesBD.CreateBD.Column.TITLE,
                        ShemaNotesBD.CreateBD.Column.BODY},
                null,
                null,
                null,
                null,
                null);

        return mCursor;

    }

    //преобразует Cursor в List<Notes>
    Function<Cursor, List<Notes>> getListNotes = new Function<Cursor, List<Notes>>() {
        @Override
        public List<Notes> apply(Cursor cursor) throws Exception {
            return getListNotes();
        }
    };

    //Создаем Flowable
    public Flowable testRxJava(){

        Flowable<List<Notes>> flowable = Flowable
                .just(getCursor())
                .subscribeOn(Schedulers.io())
                .map(getListNotes)
                .observeOn(AndroidSchedulers.mainThread());

        getListNotes().clear();
        return flowable;
    }

    public void removeNotesBd(Context context, String id){
        /*connectBd(context);*/
        mDataBase.delete(NOTES_TABLE, ID_NOTES + "= ?", new String[]{id});
    }


    /*Тестовые методы*/
    public void addNotesBd(Context context, String title, String body){
        ContentValues values = setContentValues(title, body);
        mDataBase.insert(NOTES_TABLE, null, values);
    }

    public Flowable readNotesTable(Context context){
        Cursor mCursor = mDataBase.query(NOTES_TABLE,
                new String[]{"_id", ShemaNotesBD.CreateBD.Column.TITLE,
                        ShemaNotesBD.CreateBD.Column.BODY},
                null,
                null,
                null,
                null,
                null);

        List list = new ArrayList();
        Flowable<Notes> flowable = null;
        while (mCursor.moveToNext()){
            Notes mNotes = new Notes();
            mNotes.setId(mCursor.getInt(mCursor.getColumnIndex("_id")));
            mNotes.setTitle(mCursor.getString(mCursor.getColumnIndex(ShemaNotesBD.CreateBD.Column.TITLE)));
            mNotes.setBody(mCursor.getString(mCursor.getColumnIndex(ShemaNotesBD.CreateBD.Column.BODY)));
//            list.add(mNotes);
            flowable = Flowable.just(mNotes);

        }
        mCursor.close();
        return flowable;
    }
}
