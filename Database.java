public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "text.db";
    private static final String TABLE_NAME = "STUDENTS";
    private static final String COLUMN_NO_1 = "FIRSTNAME";      // (NAME, DATA TYPE, CONSTRAINT)
    private static final String COLUMN_NO_2 = "LASTNAME";      // (NAME, DATA TYPE)
   // private static final String TABLE_CREATE = "CREATE TABLE"+TABLE_NAME+"" +
    //                         "( ID INTEGER PRIMARY KEY AUTOINCREMENT,"+COLUMN_NO_1+","+COLUMN_NO_2+");";
    //"CREATE TABLE STUDENT (ID INTEGER PRIMARY KEY AUTOINCREMENT, FIRSTNAME TEXT UNIQUE, LASTNAME TEXT)"   look like this

    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {       //table will create over here
        sqLiteDatabase.execSQL("CREATE TABLE" +TABLE_NAME+"( ID INTEGER PRIMARY KEY AUTOINCREMENT," +COLUMN_NO_1 +"TEXT UNIQUE," +COLUMN_NO_2 +" TEXT);");
	}

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS"+TABLE_NAME+";");
        onCreate(sqLiteDatabase);

    }
    public void insert_Student (String firstName, String lastName)
    {
        ContentValues values = new ContentValues();      //insert the value which column u acces
        values.put("FIRSTNAME", firstName);
        values.put("LASTNAME", lastName);
        this.getWritableDatabase().insertOrThrow(TABLE_NAME,null,values);        //write this values into the table
    }

    public void delete_student(String firstName)        //table were delete only provide first name
    {

        this.getWritableDatabase().delete(TABLE_NAME,"FIRSTNAME='"+firstName+"'", null );
    }

    public void update_student(String old_Firstname, String new_Firstname)
    {
            this.getWritableDatabase().execSQL("UPDATE STUDENTS SET FIRSTNAME='"+new_Firstname+"' WHERE FIRSTNAME='"+old_Firstname+"'");
        //new name k leye dialog ai ga.
    }

    public void list_all_student(TextView textView)
    {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM "+TABLE_NAME, null);    //read records from table use cusur

        textView.setText("");
        while (cursor.moveToNext()) {

            textView.append(cursor.getString(1) + " "+cursor.getString(2)+"\n");    //0 id , 1 first name , 2 last name
        }
        }


    }
