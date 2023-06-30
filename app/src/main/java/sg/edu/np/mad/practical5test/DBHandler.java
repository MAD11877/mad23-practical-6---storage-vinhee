package sg.edu.np.mad.practical5test;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "UserDatabase";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_USER = "User";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_FOLLOWED = "Followed";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory,int version){
        super(context,DATABASE_NAME,factory,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE User" + "("
                + "id" + " INTEGER PRIMARY KEY" + ","
                + "name" + " TEXT" + ","
                + "description" + " TEXT" + ","
                + "Followed" + " BOOLEAN" + ")";
        db.execSQL(CREATE_USER_TABLE);

        for (int i = 0; i < 20; i++) {
            String name = "Name" + generateName();
            String description = "Description " + generateDesc();
            boolean followed = generateFollow();

            User user1 = new User();
            user1.name = name;
            user1.description = description;
            user1.followed = followed;
            user1.id = i;

            ContentValues values = new ContentValues();
            values.put("id", user1.getId());
            values.put("name", user1.getName());
            values.put("description", user1.getDescription());
            values.put("Followed", user1.isFollowed());

            db.insert(TABLE_USER, null, values);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    private boolean generateFollow(){
        Random r = new Random();
        boolean follow = r.nextBoolean();
        return follow;
    }

    private String generateName(){
        Random r = new Random();
        int number = r.nextInt(999999999);
        return String.valueOf(number);
    }

    private String generateDesc(){
        Random r = new Random();
        int number = r.nextInt(999999999);
        return String.valueOf(number);
    }

    public ArrayList<User> getUsers(){
        String query = "SELECT * FROM " + "User";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<User> userList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(cursor.getInt(0));
                user.setName(cursor.getString(1));
                user.setDescription(cursor.getString(2));
                user.setFollowed(Boolean.parseBoolean(cursor.getString(3)));

                userList.add(user);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return userList;
    }

    public void updateUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        if(user.isFollowed() == true){
            values.put("Followed", true);
            db.update("User", values, COLUMN_ID + " = ?", new String[]{String.valueOf(user.getId())});
            Log.d(TAG, "User ID: " + user.getId() + ", Followed: " + user.isFollowed());
        }
        else{
            values.put("Followed", false);
            db.update("User", values, COLUMN_ID + " = ?", new String[]{String.valueOf(user.getId())});
            Log.d(TAG, "User ID: " + user.getId() + ", Followed: " + user.isFollowed());
        }

    }

}
