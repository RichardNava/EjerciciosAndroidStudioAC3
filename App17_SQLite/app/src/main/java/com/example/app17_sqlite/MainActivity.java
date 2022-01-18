package com.example.app17_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etCodigo, etDescripcion, etPrecio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etCodigo = (EditText) findViewById(R.id.etCodigo);
        etDescripcion = (EditText) findViewById(R.id.etDescripcion);
        etPrecio = (EditText) findViewById(R.id.etPrecio);

    }

    // Método para el CRUD
    // Método para Crear
    public void crear(View v){
        SQLiteDatabase dbWrite = connectDB("admin");

        String codigo = etCodigo.getText().toString();
        String descripcion = etDescripcion.getText().toString();
        String precio = etPrecio.getText().toString();

        if (!codigo.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty()){
            ContentValues register = registrarValores(codigo,descripcion,precio);

            dbWrite.insert("articulos", null, register);
            dbWrite.close();

            etCodigo.setText("");
            etDescripcion.setText("");
            etPrecio.setText("");

            Toast.makeText(this, "¡Buen trabajo! Registro realizado con éxito.", Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(this, "Debes rellenar todos los campos para realizar el registro.", Toast.LENGTH_SHORT).show();
            dbWrite.close();
        }

    }
    // Método para Buscar
    public void buscar(View v){
        SQLiteDatabase dbWrite = connectDB("admin");

        String codigo = etCodigo.getText().toString();

        if (!codigo.isEmpty()){
            Cursor query = dbWrite.rawQuery("SELECT descripcion, precio FROM articulos WHERE codigo="+codigo,null);

            if (query.moveToFirst()){ // movetToFirst() revisa si hay valores en la BBDD
                etDescripcion.setText(query.getString(0)); // el orden de los datos dentro de Cursor estaran ordenados igual que lo hemos defenido en la query
                etPrecio.setText(query.getString(1) + "€");
                dbWrite.close();
            }
            else{
                Toast.makeText(this, "Artículo no encontrado.", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "No existe ese artículo.", Toast.LENGTH_SHORT).show();
            dbWrite.close();
        }

    }
    // Método para Modificar
    public void modificar(View v){
        SQLiteDatabase dbWrite = connectDB("admin");
        String codigo = etCodigo.getText().toString();
        String descripcion = etDescripcion.getText().toString();
        String precio = etPrecio.getText().toString();

        if (!codigo.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty()){
            ContentValues register = registrarValores(codigo,descripcion,precio);

            int qty = dbWrite.update("articulos",register,"codigo="+codigo,null);
            dbWrite.close();

            if (qty==1){
                Toast.makeText(this, "El articulo con ID "+codigo+ " ha sido modificado con éxito.", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "El articulo con ID "+codigo+ " no ha podido ser modificado.", Toast.LENGTH_SHORT).show();
            }

        }
        else{
            Toast.makeText(this, "Debe rellenar todos lo campos.", Toast.LENGTH_SHORT).show();
            dbWrite.close();
        }
    }
    // Método para Eliminar
    public void eliminar(View v){
        SQLiteDatabase dbWrite = connectDB("admin");

        String codigo = etCodigo.getText().toString();

        if (!codigo.isEmpty()){

            int qty = dbWrite.delete("articulos","codigo="+codigo,null); // DELETE FROM articulos WHERE codigo = codigo;
            dbWrite.close();

            etCodigo.setText("");
            etDescripcion.setText("");
            etPrecio.setText("");

            if (qty == 1){
                Toast.makeText(this, "Artículo eliminado.", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Artículo no existe.", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "Debe introducir el código del artículo.", Toast.LENGTH_SHORT).show();
            dbWrite.close();
        }

    }
    public SQLiteDatabase connectDB(String nombre){
        AdminSQLiteOpenHelper dbAdmin = new AdminSQLiteOpenHelper(this,nombre, null, 1);
        SQLiteDatabase dbWrite = dbAdmin.getWritableDatabase();
        return dbWrite;
    }

    public ContentValues registrarValores(String codigo, String descripcion, String precio){
        ContentValues register = new ContentValues();
        register.put("codigo", codigo);
        register.put("descripcion", descripcion);
        register.put("precio", precio);
        return register;
    }
}