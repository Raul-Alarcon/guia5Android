package com.example.guia5;

import android.os.Bundle;
import android.view.View;
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

public class AddPerson extends AppCompatActivity {

    private EditText etNombre, etApellido, etEdad;
    private PersonasViewModel personaViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_person);

        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etEdad = findViewById(R.id.etEdad);
        Button btnGuardar = findViewById(R.id.btnGuardar);

        personaViewModel = new ViewModelProvider(this).get(PersonasViewModel.class);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = etNombre.getText().toString();
                String apellido = etApellido.getText().toString();
                int edad = Integer.parseInt(etEdad.getText().toString());
            if (nombre.isEmpty() || apellido.isEmpty() || edad < 0) {
                Toast.makeText(v.getContext(), "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show();
                return;
            }
                Personas persona = new Personas();

                persona.nombrePersona = nombre;
                persona.apellidoPersona = apellido;
                persona.edadPersona = edad;
                personaViewModel.insertPersona(persona);
                Toast.makeText(v.getContext(), "Persona agregada correctamente", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}