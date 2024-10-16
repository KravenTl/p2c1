package gt.edu.umg.pc2c1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import gt.edu.umg.pc2c1.BaseDatos.DbHelper;

public class NuevoActivity extends AppCompatActivity {


    // Declaración de los elementos de la interfaz
    Button btnGuardar;
    EditText txtNombre, txtTelefono, txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Habilita un diseño que aprovecha todo el borde de la pantalla
        EdgeToEdge.enable(this);
        // Establece el archivo de diseño de la actividad
        setContentView(R.layout.activity_nuevo);

        // Vincular los elementos de la interfaz con las variables
        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtEmail = findViewById(R.id.txtEmail);
        btnGuardar = findViewById(R.id.btnGuardar);

        // Instancia de DbHelper para gestionar la base de datos
        DbHelper dbHelper = new DbHelper(this);

        // Acción para guardar un contacto cuando se presiona el botón
        btnGuardar.setOnClickListener(v -> {
            // Obtener los datos ingresados por el usuario
            String nombre = txtNombre.getText().toString();
            String telefono = txtTelefono.getText().toString();
            String email = txtEmail.getText().toString();

            // Verificar que todos los campos estén completos
            if (nombre.isEmpty() || telefono.isEmpty() || email.isEmpty()) {
                // Si alguno de los campos está vacío, muestra un mensaje
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
            } else {
                // Si los campos están completos, inserta el nuevo contacto en la base de datos
                long resultado = dbHelper.insertarContacto(nombre, telefono, email);

                // Verifica si la inserción fue exitosa
                if (resultado != -1) {
                    // Muestra un mensaje de éxito y limpia los campos del formulario
                    Toast.makeText(this, "Contacto guardado con éxito", Toast.LENGTH_SHORT).show();
                    txtNombre.setText("");
                    txtTelefono.setText("");
                    txtEmail.setText("");
                } else {
                    // Si hay un error al guardar, muestra un mensaje de error
                    Toast.makeText(this, "Error al guardar el contacto", Toast.LENGTH_SHORT).show();
                }
            }
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