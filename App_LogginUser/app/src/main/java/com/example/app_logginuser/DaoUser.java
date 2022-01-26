package com.example.app_logginuser;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class DaoUser {
    Context c;
    User u;
    ArrayList<User> list;
    SQLiteDatabase sql;
    String bd= "dbUsers";
    String table="create table if not exists userDB(id integer primary key autoincrement, nombre text, ap text, usuario text, pass text) ";

    public DaoUser() {

    }

    public DaoUser(@NonNull Context c) {
        this.c = c;
        sql=c.openOrCreateDatabase(bd, c.MODE_PRIVATE, null);
        sql.execSQL(table);
        u = new User();
    }

    public boolean insertUser(@NonNull User u){
        if (find(u.getUsuario()) == 0){
            ContentValues cv = new ContentValues();
            cv.put("nombre", u.getNombre());
            cv.put("ap", u.getApellidos());
            cv.put("usuario", u.getUsuario());
            cv.put("pass", u.getPassword());
            return (sql.insert("userDB",null,cv)>0);
        }
        else{
            return false;
        }
    }
    public int find(String u){
        int x = 0;
        list = selectUsers();
        for (User us : list) {
            if(us.getUsuario().equals(u)){
                x++;
            }
        }
        return x;
    }

    public ArrayList<User> selectUsers(){
        ArrayList<User> list = new ArrayList<User>();
        list.clear();
        Cursor cr = sql.rawQuery("select * from userDB ",null);
        if(cr!=null&&cr.moveToFirst()){
            do{
                User u = new User();
                u.setId(cr.getInt(0));
                u.setNombre(cr.getString(1));
                u.setApellidos(cr.getString(2));
                u.setUsuario(cr.getString(3));
                u.setPassword(cr.getString(4));
                list.add(u);
            }while (cr.moveToNext());
        }
        return list;
    }

    public int login(String user, String pass){
        int a=0;
        Cursor cr = sql.rawQuery("select * from userDB ",null);
        if(cr!=null&&cr.moveToFirst()){
            do{
                if(cr.getString(3).equals(user)&&cr.getString(4).equals(pass)) a++;

            }while (cr.moveToNext());
        }
        return a;
    }

    public User getUser(String user, String pass){
        list = selectUsers();
        for (User u : list ) {
            if (u.getUsuario().equals(user)&&u.getPassword().equals(pass)) return u;
        }
        return  null;
    }
    public User getUserById(int id){
        list = selectUsers();
        for (User u : list ) {
            if (u.getId() == id) return u;
        }
        return  null;
    }
}
