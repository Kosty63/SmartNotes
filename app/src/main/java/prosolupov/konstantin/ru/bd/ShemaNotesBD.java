package prosolupov.konstantin.ru.bd;

/**
 * Created by home on 26.06.2017.
 */

public class ShemaNotesBD {

    public static final class CreateBD{

        public static final String NOTES_TABLE = "notes";

        public static final class Column{
            public static final String ID_NOTES = "id_notes";
            public static final String TITLE = "title";
            public static final String BODY = "body";
        }
    }

}
