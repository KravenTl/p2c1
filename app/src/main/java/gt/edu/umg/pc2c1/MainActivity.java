package gt.edu.umg.pc2c1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import gt.edu.umg.pc2c1.BaseDatos.DbHelper;

public class MainActivity extends AppCompatActivity {


    // Declaración de los botones y el TextView
    Button btnSaludo, btnCrearDB, btnCrearRegistro;
    TextView tvSaludo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Habilita un diseño que aprovecha todo el borde de la pantalla
        EdgeToEdge.enable(this);
        // Establece el archivo de diseño de la actividad
        setContentView(R.layout.activity_main);

        // Enlazar componentes del layout con las variables en el código
        btnSaludo = findViewById(R.id.btnSaludo);
        tvSaludo = findViewById(R.id.tvSaludo);
        btnCrearDB = findViewById(R.id.btnCrearDb);
        btnCrearRegistro = findViewById(R.id.btnCrearRegistro);

        // Acción para crear la base de datos cuando se presiona el botón
        btnCrearDB.setOnClickListener(v -> {
            // Crear una instancia de DbHelper que gestiona la creación y actualización de la base de datos
            DbHelper dbHelper = new DbHelper(this);
            // Obtiene una referencia a la base de datos en modo escritura
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            // Verifica si la base de datos se creó exitosamente
            if (db != null) {
                // Muestra un mensaje de éxito al usuario y cambia el texto en tvSaludo
                Toast.makeText(this, "Creando base de datos", Toast.LENGTH_SHORT).show();
                tvSaludo.setText("Creando base de datos");
            } else {
                // Si hay un error al crear la base de datos, se informa al usuario
                Toast.makeText(this, "Error al crear base de datos", Toast.LENGTH_SHORT).show();
                tvSaludo.setText("Error al crear base de datos");
            }
        });

        // Acción para abrir una nueva actividad cuando se presiona el botón para crear un registro
        btnCrearRegistro.setOnClickListener(v -> {
            // Muestra un mensaje al usuario indicando que se creará un registro
            Toast.makeText(this, "Creando Registro", Toast.LENGTH_SHORT).show();
            // Crea un Intent para abrir la actividad NuevoActivity
            Intent intent = new Intent(this, NuevoActivity.class);
            // Inicia la nueva actividad
            startActivity(intent);
        });

        // Acción cuando se presiona el botón de saludo
        btnSaludo.setOnClickListener(v -> {
            // Muestra un mensaje "Aviso Kraven" como notificación emergente
            Toast.makeText(this, "Aviso Kraven", Toast.LENGTH_SHORT).show();
            // Cambia el texto del TextView a "Hola Kraven"
            tvSaludo.setText("Hola Kraven");
        });

        // Ajusta los márgenes para evitar que la interfaz gráfica quede debajo de las barras del sistema (como la barra de estado)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            // Obtiene los márgenes del sistema (barras de estado, navegación, etc.)
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            // Establece los márgenes en la vista principal para evitar superposición
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}