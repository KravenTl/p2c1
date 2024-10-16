package gt.edu.umg.pc2c1.BaseDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper{

    private static final int DB_VERSION=1;
    private static final String DB_NOMBRE="db_umg.db";
    public static final String TABLE_CONTACTOS= "t_contactos";


    // Constructor de la clase DbHelper
    // El parámetro context se refiere al contexto de la aplicación desde donde se llamará este helper
    public DbHelper(@Nullable Context context){
        super(context, DB_NOMBRE, null, DB_VERSION);
    }

    @Override
    // Método onCreate se ejecuta cuando la base de datos se crea por primera vez
    // Aquí creamos la tabla "t_contactos" con las columnas: id, nombre, telefono, y email
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_CONTACTOS + " (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, telefono TEXT, email TEXT)");
    }

    @Override
    // Método onUpgrade se llama cuando se detecta un cambio en la versión de la base de datos
    // Esto es útil si necesitas modificar la estructura de la base de datos en futuras versiones
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTOS);
        onCreate(db);
    }

    // Método para insertar un nuevo contacto en la tabla "t_contactos"
    public long insertarContacto(String nombre, String telefono, String email) {
        // Obtenemos una referencia a la base de datos en modo escritura
        SQLiteDatabase db = this.getWritableDatabase();
        // Usamos ContentValues para almacenar los valores que se insertarán en la base de datos
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("telefono", telefono);
        values.put("email", email);

        // Insertamos una nueva fila en la tabla "t_contactos"
        long resultado = db.insert(TABLE_CONTACTOS, null, values);
        db.close(); // Cerrar conexión a la base de datos
        // Retornamos el resultado de la inserción (el ID de la nueva fila o -1 si hay un error)
        return resultado;
    }
}
