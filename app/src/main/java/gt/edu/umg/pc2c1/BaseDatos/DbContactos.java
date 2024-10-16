package gt.edu.umg.pc2c1.BaseDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class DbContactos extends DbHelper {

    Context context;

    // Constructor de DbContactos, que llama al constructor de la clase base (DbHelper)
    public DbContactos(@Nullable Context context) {
        super(context);
        this.context = context; // Guardamos el context en una variable de instancia
    }

    //Metodo para insertar contactos en la tabla t_contactos
    public long insertaContacto(String nombre, String telefono, String email) {
        try{
            if (nombre.isEmpty() || telefono.isEmpty() || email.isEmpty()) {
                return -1;
            }
            // Instancia de DbHelper para acceder a la base de datos
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();// Obtenemos una referencia en modo escritura
            // ContentValues para almacenar los valores que se insertarán
            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("telefono", telefono);
            values.put("email", email);
            // Insertar una nueva fila en la tabla "t_contactos" y obtener el ID de la nueva fila
            long id = db.insert(DbHelper.TABLE_CONTACTOS, null, values);
            return id;
        } catch (Exception e) {
            return -1;
        }

    }
}


