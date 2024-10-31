package com.example.guia5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.guia5.Entities.Personas;
import com.example.guia5.ViewModel.PersonasViewModel;

public class EditPerson extends AppCompatActivity {

    private EditText etNombre;
    private EditText etApellido;
    private EditText etEdad;
    private Button btnGuardar;
    private PersonasViewModel personaViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_person);

        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etEdad = findViewById(R.id.etEdad);
        btnGuardar = findViewById(R.id.btnGuardar);

        personaViewModel = new ViewModelProvider(this).get(PersonasViewModel.class);
        Intent intent = getIntent();
        int idPersona = intent.getIntExtra("idPersona", -1);
        String nombre = intent.getStringExtra("nombre");
        String apellido = intent.getStringExtra("apellido");
        int edad = intent.getIntExtra("edad", -1);
        etNombre.setText(nombre);
        etApellido.setText(apellido);
        etEdad.setText(String.valueOf(edad));

        btnGuardar.setOnClickListener(view -> {
            // Validar que todos los campos estén llenos
            if (etNombre.getText().toString().isEmpty() || etApellido.getText().toString().isEmpty() || etEdad.getText().toString().isEmpty()) {
                // Mostrar mensaje de advertencia si falta algún campo
                Toast.makeText(view.getContext(), "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show();
                return; // Salir del método si hay campos vacíos
            }
            Personas persona = new Personas(idPersona, etNombre.getText().toString(), etApellido.getText().toString(), Integer.parseInt(etEdad.getText().toString()));

            personaViewModel.update(persona);

            Toast.makeText(this, "Persona actualizada correctamente", Toast.LENGTH_SHORT).show();
            Intent resultIntent = new Intent();
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}