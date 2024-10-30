package com.example.guia5;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import com.example.guia5.Entities.Personas;
import com.example.guia5.ViewModel.PersonasViewModel;

public class ShowList extends AppCompatActivity {

    private PersonasViewModel personaViewModel;
    private PersonAdapter personaAdapter;

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
        personaViewModel.getListaDePersonas().observe(this, personas -> {
            personaAdapter.setPersonasList(personas);
        });

        personaAdapter.setOnItemClickListener(position -> {
            // Lógica para editar
            Intent intent = new Intent(ShowList.this, EditPerson.class);
            intent.putExtra("editMode", true);
            intent.putExtra("personId", personaAdapter.getPersonaAt(position).getIdPersona());
            startActivity(intent);
        });

        personaAdapter.setOnItemLongClickListener(position -> {
            // Mostrar alerta para confirmar eliminación
            new AlertDialog.Builder(ShowList.this)
                    .setTitle("Eliminar")
                    .setMessage("¿Realmente desea eliminar el registro?")
                    .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Lógica para eliminar el registro
                            personaViewModel.eliminarPersona(personaAdapter.getPersonaAt(position));
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();

        }
}