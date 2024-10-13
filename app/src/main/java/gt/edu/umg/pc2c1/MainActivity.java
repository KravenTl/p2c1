package gt.edu.umg.pc2c1;

import android.annotation.SuppressLint;
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

    Button bntSaludo,btnCrearDB;
    TextView tvSaludo;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //coddigo nuevo
        bntSaludo= findViewById(R.id.btnSaludo);
        tvSaludo= findViewById(R.id.tvSaludo);
        btnCrearDB=findViewById(R.id.btnCrarDB);

        bntSaludo.setOnClickListener(v->{
            Toast.makeText(this,"Aviso Kraven", Toast.LENGTH_SHORT).show();
            tvSaludo.setText("Hola Kraven");
        });

        //crear db
        btnCrearDB.setOnClickListener(v->{

            //crear base de datos
            DbHelper dbHelper= new DbHelper(this);
            dbHelper.getWritableDatabase();
            SQLiteDatabase db= dbHelper.getWritableDatabase();
            if (db!=null){
                Toast.makeText(this,"Base de datos creada",Toast.LENGTH_SHORT).show();
                tvSaludo.setText("Base de datos creada");

            }else{
                Toast.makeText(this,"Error al crear base de datos",Toast.LENGTH_SHORT).show();
                tvSaludo.setText("Error al crear base de datos");
            }

        });

        //crear registro llama a la activity NuevoActivity
        btnCrearRegistro.setOnClickListener(v -> {
            Toast.makeText(this, "Creando Registro", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, NuevoActivity.class);
            startActivity(intent);

        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}