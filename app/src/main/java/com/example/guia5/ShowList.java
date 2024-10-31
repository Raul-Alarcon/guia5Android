package com.example.guia5;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import com.example.guia5.Entities.Personas;
import com.example.guia5.ViewModel.PersonasViewModel;

public class ShowList extends AppCompatActivity {

    private PersonasViewModel personaViewModel;
    private PersonAdapter personaAdapter;
    private static final int REQUEST_CODE_EDITAR = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_list);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        personaAdapter = new PersonAdapter();
        recyclerView.setAdapter(personaAdapter);

        personaViewModel = new ViewModelProvider(this).get(PersonasViewModel.class);
        personaViewModel.getListaDePersonas().observe(this, new Observer<List<Personas>>() {
            @Override
            public void onChanged(List<Personas> personas) {
                personaAdapter.setPersonasList(personas);
            }
        });

        personaAdapter.setOnItemClickListener(position -> {
            Personas personaSeleccionada = personaAdapter.getPersonaAt(position);
            mostrarDialogoConfirmacionEdicion(personaSeleccionada);
        });

        personaAdapter.setOnItemLongClickListener(position -> {
            Personas personaSeleccionada = personaAdapter.getPersonaAt(position);
            mostrarDialogoConfirmacionEliminacion(personaSeleccionada);
        });
    }

    private void mostrarDialogoConfirmacionEdicion(Personas persona) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Quieres modificar esta persona?")
                .setTitle("Confirmación")
                .setPositiveButton("Sí", (dialog, id) -> {
                    // Abrir la actividad EditarPersonaActivity con los datos de la persona seleccionada
                    Intent intent = new Intent(ShowList.this, EditPerson.class);
                    intent.putExtra("idPersona", persona.idPersona);
                    intent.putExtra("nombre", persona.nombrePersona);
                    intent.putExtra("apellido", persona.apellidoPersona);
                    intent.putExtra("edad", persona.edadPersona);
                    startActivityForResult(intent, REQUEST_CODE_EDITAR); // Usa onActivityResult para manejar el resultado
                })
                .setNegativeButton("No", (dialog, id) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void mostrarDialogoConfirmacionEliminacion(Personas persona) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Quieres eliminar esta persona?")
                .setTitle("Confirmación")
                .setPositiveButton("Sí", (dialog, id) -> {
                    personaViewModel.delete(persona);
                    Toast.makeText(this, "Persona eliminada correctamente", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("No", (dialog, id) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_EDITAR && resultCode == RESULT_OK) {
            int id = data.getIntExtra("idPersona", -1);
            String nombre = data.getStringExtra("nombre");
            String apellido = data.getStringExtra("apellido");
            int edad = data.getIntExtra("edad", -1);

            if (id != -1) {
                Personas personaActualizada = new Personas(id, nombre, apellido, edad);
                personaViewModel.update(personaActualizada); // Usa el método en tu ViewModel
            }
        }
    }
}