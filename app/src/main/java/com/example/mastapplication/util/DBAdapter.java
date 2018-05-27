package com.example.mastapplication.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mastapplication.model.LoginOutput;

/**
 * Created by vandanahegde on 27/05/18.
 */

public class DBAdapter {
    private DatabasHelper dbHelper;
    private SQLiteDatabase db;
    private Context context;


    public DBAdapter(Context context) {
        this.context = context;
        dbHelper = new DatabasHelper(context);
    }

    public class DatabasHelper extends SQLiteOpenHelper {


        public DatabasHelper(Context context) {
            super(context, "DB_MAST", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(DATABASE_CREATE_TABLE_USER);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }

    public synchronized DBAdapter open() {
        db = dbHelper.getWritableDatabase();
        return this;
    }


    public synchronized void close() {
        dbHelper.close();
    }

    private static final String DATABASE_TABLE_USER = "user";
    private static final String DATABASE_KEY_ID = "id";
    private static final String DATABASE_KEY_LOGIN = "login";
    private static final String DATABASE_KEY_AVATAR_URL = "avatar_url";
    private static final String DATABASE_KEY_GRAVATAR_ID = "gravatar_id";
    private static final String DATABASE_KEY_URL = "url";
    private static final String DATABASE_KEY_HTML_URL = "html_url";
    private static final String DATABASE_KEY_FOLLOWERS_URL = "followers_url";
    private static final String DATABASE_KEY_FOLLOWING_URL = "following_url";
    private static final String DATABASE_KEY_GISTS_URL = "gists_url";
    private static final String DATABASE_KEY_STARRED_URL = "starred_url";
    private static final String DATABASE_KEY_SUBSCRIPTIOS_URL = "subscriptions_url";
    private static final String DATABASE_KEY_ORGANIZATUONS_URL = "organizations_url";
    private static final String DATABASE_KEY_REPOS_URL = "repos_url";
    private static final String DATABASE_KEY_EVENTS_URL = "events_url";
    private static final String DATABASE_KEY_RECEIVED_EVENTS_URL = "received_events_url";
    private static final String DATABASE_KEY_TYPE = "type";
    private static final String DATABASE_KEY_SITE_ADMIN = "site_admin";
    private static final String DATABASE_KEY_NAME = "name";
    private static final String DATABASE_KEY_COMPANY = "company";
    private static final String DATABASE_KEY_BLOG = "blog";
    private static final String DATABASE_KEY_LOCATION = "location";
    private static final String DATABASE_KEY_EMAIL = "email";
    private static final String DATABASE_KEY_HIREABLE = "hireable";
    private static final String DATABASE_KEY_BIO = "bio";
    private static final String DATABASE_KEY_PUBLIC_REPOS = "public_repos";
    private static final String DATABASE_KEY_PUBLIC_GISTS = "public_gists";
    private static final String DATABASE_KEY_FOLLOWERS = "followers";
    private static final String DATABASE_KEY_FOLLOWING = "following";
    private static final String DATABASE_KEY_CREATED_AT = "created_at";
    private static final String DATABASE_KEY_UPDATED_AT = "updated_at";
    private static final String DATABASE_KEY_MESSAGE = "message";
    private static final String DATABASE_KEY_DOCUMENTATION_URL = "documentation_url";


    private static final String TYPE_VARCHAR = " varchar(250)";
    private static final String TYPE_INT = " integer";
    private static final String PRIMARY_KEY = " PRIMARY KEY";
    private static final String COMMA = ", ";

    private static final String DATABASE_CREATE_TABLE_USER = "create table " + DATABASE_TABLE_USER + "(" + DATABASE_KEY_ID + TYPE_INT + PRIMARY_KEY + COMMA +
            DATABASE_KEY_LOGIN + TYPE_VARCHAR + COMMA +
            DATABASE_KEY_AVATAR_URL + TYPE_VARCHAR + COMMA +
            DATABASE_KEY_GRAVATAR_ID + TYPE_VARCHAR + COMMA +
            DATABASE_KEY_URL + TYPE_VARCHAR + COMMA +
            DATABASE_KEY_HTML_URL + TYPE_VARCHAR + COMMA +
            DATABASE_KEY_FOLLOWERS_URL + TYPE_VARCHAR + COMMA +
            DATABASE_KEY_FOLLOWING_URL + TYPE_VARCHAR + COMMA +
            DATABASE_KEY_GISTS_URL + TYPE_VARCHAR + COMMA +
            DATABASE_KEY_STARRED_URL + TYPE_VARCHAR + COMMA +
            DATABASE_KEY_SUBSCRIPTIOS_URL + TYPE_VARCHAR + COMMA +
            DATABASE_KEY_ORGANIZATUONS_URL + TYPE_VARCHAR + COMMA +
            DATABASE_KEY_REPOS_URL + TYPE_VARCHAR + COMMA +
            DATABASE_KEY_EVENTS_URL + TYPE_VARCHAR + COMMA +
            DATABASE_KEY_RECEIVED_EVENTS_URL + TYPE_VARCHAR + COMMA +
            DATABASE_KEY_TYPE + TYPE_VARCHAR + COMMA +
            DATABASE_KEY_SITE_ADMIN + TYPE_VARCHAR + COMMA +
            DATABASE_KEY_NAME + TYPE_VARCHAR + COMMA +
            DATABASE_KEY_COMPANY + TYPE_VARCHAR + COMMA +
            DATABASE_KEY_BLOG + TYPE_VARCHAR + COMMA +
            DATABASE_KEY_LOCATION + TYPE_VARCHAR + COMMA +
            DATABASE_KEY_EMAIL + TYPE_VARCHAR + COMMA +
            DATABASE_KEY_HIREABLE + TYPE_VARCHAR + COMMA +
            DATABASE_KEY_BIO + TYPE_VARCHAR + COMMA +
            DATABASE_KEY_PUBLIC_REPOS + TYPE_INT + COMMA +
            DATABASE_KEY_PUBLIC_GISTS + TYPE_INT + COMMA +
            DATABASE_KEY_FOLLOWERS + TYPE_INT + COMMA +
            DATABASE_KEY_FOLLOWING + TYPE_INT + COMMA +
            DATABASE_KEY_CREATED_AT + TYPE_VARCHAR + COMMA +
            DATABASE_KEY_UPDATED_AT + TYPE_VARCHAR + COMMA +
            DATABASE_KEY_MESSAGE + TYPE_VARCHAR + COMMA +
            DATABASE_KEY_DOCUMENTATION_URL + TYPE_VARCHAR +");";

    public void insertUserData(LoginOutput loginOutput) {
        try {
            long l;
            ContentValues contentValues = new ContentValues();
            contentValues.put(DATABASE_KEY_ID, loginOutput.getId());
            contentValues.put(DATABASE_KEY_LOGIN, loginOutput.getLogin());
            contentValues.put(DATABASE_KEY_AVATAR_URL, loginOutput.getAvatar_url());
            contentValues.put(DATABASE_KEY_GRAVATAR_ID, loginOutput.getGravatar_id());
            contentValues.put(DATABASE_KEY_URL, loginOutput.getUrl());
            contentValues.put(DATABASE_KEY_HTML_URL, loginOutput.getHtml_url());
            contentValues.put(DATABASE_KEY_FOLLOWERS_URL, loginOutput.getFollowers_url());
            contentValues.put(DATABASE_KEY_FOLLOWING_URL, loginOutput.getFollowing_url());
            contentValues.put(DATABASE_KEY_GISTS_URL, loginOutput.getGists_url());
            contentValues.put(DATABASE_KEY_STARRED_URL, loginOutput.getStarred_url());
            contentValues.put(DATABASE_KEY_SUBSCRIPTIOS_URL, loginOutput.getSubscriptions_url());
            contentValues.put(DATABASE_KEY_ORGANIZATUONS_URL, loginOutput.getOrganizations_url());
            contentValues.put(DATABASE_KEY_REPOS_URL, loginOutput.getRepos_url());
            contentValues.put(DATABASE_KEY_EVENTS_URL, loginOutput.getEvents_url());
            contentValues.put(DATABASE_KEY_RECEIVED_EVENTS_URL, loginOutput.getReceived_events_url());
            contentValues.put(DATABASE_KEY_TYPE, loginOutput.getType());
            contentValues.put(DATABASE_KEY_SITE_ADMIN, loginOutput.getSite_admin());
            contentValues.put(DATABASE_KEY_NAME, loginOutput.getName());
            contentValues.put(DATABASE_KEY_COMPANY, loginOutput.getCompany());
            contentValues.put(DATABASE_KEY_BLOG, loginOutput.getBlog());
            contentValues.put(DATABASE_KEY_LOCATION, loginOutput.getLocation());
            contentValues.put(DATABASE_KEY_EMAIL, loginOutput.getEmail());
            contentValues.put(DATABASE_KEY_HIREABLE, loginOutput.getHireable());
            contentValues.put(DATABASE_KEY_BIO, loginOutput.getBio());
            contentValues.put(DATABASE_KEY_PUBLIC_REPOS, loginOutput.getPublic_repos());
            contentValues.put(DATABASE_KEY_PUBLIC_GISTS, loginOutput.getPublic_gists());
            contentValues.put(DATABASE_KEY_FOLLOWERS, loginOutput.getFollowers());
            contentValues.put(DATABASE_KEY_FOLLOWING, loginOutput.getFollowing());
            contentValues.put(DATABASE_KEY_CREATED_AT, loginOutput.getCreated_at());
            contentValues.put(DATABASE_KEY_UPDATED_AT, loginOutput.getUpdated_at());
            contentValues.put(DATABASE_KEY_MESSAGE, loginOutput.getMessage());
            contentValues.put(DATABASE_KEY_DOCUMENTATION_URL, loginOutput.getDocumentation_url());
            l = db.insertWithOnConflict(DATABASE_TABLE_USER, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
            if(l == -1) {
                boolean b = db.update(DATABASE_TABLE_USER, contentValues, DATABASE_KEY_ID + "=" + loginOutput.getId(), null) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public LoginOutput fetchUserData() {
        LoginOutput output = new LoginOutput();
        try {
            Cursor cursor = db.query(DATABASE_TABLE_USER, null, null, null, null, null, null);
            if(cursor.moveToFirst()) {
                do {
                    output.setId(cursor.getInt(0));
                    output.setLogin(cursor.getString(1));
                    output.setAvatar_url(cursor.getString(2));
                    output.setGravatar_id(cursor.getString(3));
                    output.setUrl(cursor.getString(4));
                    output.setHtml_url(cursor.getString(5));
                    output.setFollowers_url(cursor.getString(6));
                    output.setFollowing_url(cursor.getString(7));
                    output.setGists_url(cursor.getString(8));
                    output.setStarred_url(cursor.getString(9));
                    output.setSubscriptions_url(cursor.getString(10));
                    output.setOrganizations_url(cursor.getString(11));
                    output.setRepos_url(cursor.getString(12));
                    output.setEvents_url(cursor.getString(13));
                    output.setReceived_events_url(cursor.getString(14));
                    output.setType(cursor.getString(15));
                    output.setSite_admin(cursor.getString(16));
                    output.setName(cursor.getString(17));
                    output.setCompany(cursor.getString(18));
                    output.setBlog(cursor.getString(19));
                    output.setLocation(cursor.getString(20));
                    output.setEmail(cursor.getString(21));
                    output.setHireable(cursor.getString(22));
                    output.setBio(cursor.getString(23));
                    output.setPublic_repos(cursor.getInt(24));
                    output.setPublic_gists(cursor.getInt(25));
                    output.setFollowers(cursor.getInt(26));
                    output.setFollowing(cursor.getInt(27));
                    output.setCreated_at(cursor.getString(28));
                    output.setUpdated_at(cursor.getString(29));
                    output.setMessage(cursor.getString(30));
                    output.setDocumentation_url(cursor.getString(31));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

    public void deleteUserData(int id) {
       db.delete(DATABASE_TABLE_USER, DATABASE_KEY_ID + "=" + id, null);
    }


}
