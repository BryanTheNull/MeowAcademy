package com.example.appmeowacademy;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class ProfileActivity extends AppCompatActivity {

    // Declaro una variable para la barra de navegacion
    private BottomNavigationView barraNavegacion;

    // Declaro los datos del formulario
    EditText editTextID;
    EditText editTextNombre;
    EditText editTextDescripcion;
    Spinner spinnerCategoria;
    Spinner spinnerDuracion;
    Spinner spinnerDificultad;
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Enlazar los componetes de la interfaz con viriables
        editTextID = findViewById(R.id.editTextID);
        editTextNombre = findViewById(R.id.editTextNombre);
        editTextDescripcion = findViewById(R.id.editTextDescripcion);
        spinnerCategoria = findViewById(R.id.spinnerCategory);
        spinnerDuracion = findViewById(R.id.spinnerDuration);
        spinnerDificultad= findViewById(R.id.spinnerDifficulty);
        lista = findViewById(R.id.listLista);


        // Enlazo la barra de navegacion a la variable creada
        barraNavegacion = findViewById(R.id.barraNavegacion);

        // Marcar la opción "Profile" como seleccionada
        barraNavegacion.setSelectedItemId(R.id.profile);

        // Configurar el listener para la barra de navegación
        barraNavegacion.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.profile) {
                    // Ya estamos en Profile, no hacemos nada
                    return true;
                } else if (id == R.id.home) {
                    startActivity(new Intent(ProfileActivity.this, HomeActivity.class));
                    finish(); // Cerrar la actividad actual
                    return true;
                } else if (id == R.id.course) {
                    startActivity(new Intent(ProfileActivity.this, CourseActivity.class));
                    finish(); // Cerrar la actividad actual
                    return true;
                } else if (id == R.id.settings) {
                    startActivity(new Intent(ProfileActivity.this, SettingsActivity.class));
                    finish(); // Cerrar la actividad actual
                    return true;
                }
                return false;
            }
        });

        cargarLista();

    }

    public void onClickMap(View view){
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
        finish();
    }

    // Metodo para cargar la lista
    public void cargarLista(){
        DataHelper dh = new DataHelper(this);
        SQLiteDatabase db = dh.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, nombre, descripcion, categoria, duracion, dificultad FROM Cursos", null);
        String[] arr = new String[cursor.getCount()];
        if(cursor.moveToFirst()){
            int i = 0;
            do {
                String linea = "||" + cursor.getInt(0) + "||" + cursor.getString(1) + "||"  + cursor.getString(2) + "||"  + cursor.getString(3) +
                                "||"  + cursor.getString(4) + "||"  + cursor.getString(5) + "||";
                arr[i] = linea;
                i++;
            } while (cursor.moveToNext() == true);
            // Crear el ArrayAdapter y asignarlo al ListView
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, arr);
            lista.setAdapter(adapter);
        }
        // Asegurarse de cerrar el cursor y la base de datos
        cursor.close();
        db.close();
    }

    public void onClickAgregarCurso(View view){
        DataHelper dh = new DataHelper(this);
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues reg = new ContentValues();
        reg.put("nombre", editTextNombre.getText().toString());
        reg.put("descripcion", editTextDescripcion.getText().toString());
        reg.put("categoria", spinnerCategoria.getSelectedItem().toString());
        reg.put("duracion", spinnerDuracion.getSelectedItem().toString());
        reg.put("dificultad", spinnerDificultad.getSelectedItem().toString());

        if (editTextNombre.getText().toString().isEmpty() ||
                editTextDescripcion.getText().toString().isEmpty() ||
                spinnerCategoria.getSelectedItem() == null ||
                spinnerDuracion.getSelectedItem() == null ||
                spinnerDificultad.getSelectedItem() == null) {
            Toast.makeText(this, "Todos los campos deben ser llenados", Toast.LENGTH_LONG).show();
            return;  // Salir del método si falta algún campo
        }

        long resp = db.insert("Cursos", null, reg);
        db.close();
        if(resp == -1){
            Toast.makeText(this, "No se puede agregar el curso", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this, "Curso agregado exitosamente", Toast.LENGTH_LONG).show();
        }
        cargarLista();
        limpiarFormulario();
    }

    public void limpiarFormulario(){
        editTextID.setText("");
        editTextNombre.setText("");
        editTextDescripcion.setText("");
        spinnerCategoria.setSelection(0);
        spinnerDuracion.setSelection(0);
        spinnerDificultad.setSelection(0);
    }

    public void onClickEliminarCurso(View view){
        DataHelper dh = new DataHelper(this);
        SQLiteDatabase db = dh.getWritableDatabase();

        // Indico el dato a eliminar
        String idEliminar = editTextID.getText().toString();

        if (idEliminar.isEmpty()) {
            Toast.makeText(this, "El ID no puede estar vacío para eliminar un registro", Toast.LENGTH_LONG).show();
            return;
        }

        //Ejecuto la querry para elimianr el registro
        long resp = db.delete( "Cursos", "id=" + idEliminar, null);
        db.close();

        // Verifico si la consulta se ejecuto
        if(resp == -1){
            Toast.makeText(this, "No se puede eliminar el curso", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this, "Curso eliminado exitosamente", Toast.LENGTH_LONG).show();
        }
        cargarLista();
        limpiarFormulario();
    }

    public void onClickModificarCurso(View view){
        DataHelper dh = new DataHelper(this);
        SQLiteDatabase db = dh.getWritableDatabase();

        ContentValues reg = new ContentValues();

        reg.put("nombre", editTextNombre.getText().toString());
        reg.put("descripcion", editTextDescripcion.getText().toString());
        reg.put("categoria", spinnerCategoria.getSelectedItem().toString());
        reg.put("duracion", spinnerDuracion.getSelectedItem().toString());
        reg.put("dificultad", spinnerDificultad.getSelectedItem().toString());

        // Dato que va a modificar
        String idModificar = editTextID.getText().toString();

        if (idModificar.isEmpty()) {
            Toast.makeText(this, "El ID no puede estar vacío", Toast.LENGTH_LONG).show();
            return;
        }

        // Verificar si el resto de los campos están completos
        if (editTextNombre.getText().toString().isEmpty() || editTextDescripcion.getText().toString().isEmpty()) {
            Toast.makeText(this, "Todos los campos deben estar completos", Toast.LENGTH_LONG).show();
            return;
        }

        // Usamos un parámetro de selección seguro
        String whereClause = "id = ?";
        String[] whereArgs = new String[]{idModificar};

        //Ejecuto la querry para modificar el registro
        long resp = db.update( "Cursos", reg, whereClause, whereArgs);
        db.close();

        // Verifico si la consulta se ejecuto
        if(resp == -1){
            Toast.makeText(this, "No se puede modificar el curso", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this, "Curso modificado exitosamente", Toast.LENGTH_LONG).show();
        }
        cargarLista();
        limpiarFormulario();

    }



}
