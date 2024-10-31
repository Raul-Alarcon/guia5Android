package com.example.guia5.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.guia5.DAO.PersonDAO;
import com.example.guia5.Database.PersonasDatabase;
import com.example.guia5.Entities.Personas;

import java.util.List;

public class PersonasViewModel extends AndroidViewModel {
    private PersonDAO personaDAO;
    private LiveData<List<Personas>> listaDePersonas;
    public PersonasViewModel(@NonNull Application application) {
        super(application);

        PersonasDatabase database = PersonasDatabase.getInstance(application);


        personaDAO = database.personaDAO();

        listaDePersonas = personaDAO.obtenerTodasLasPersonas();
    }

    public LiveData<List<Personas>> getListaDePersonas() {
        return listaDePersonas;
    }

    public void insertPersona(Personas persona) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                personaDAO.Insert(persona);
            }
        }).start();
    }
    public void update(Personas persona) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                personaDAO.Update(persona);
            }
        }).start();
    }
    public void delete(Personas persona) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                personaDAO.Delete(persona);
            }
        }).start();
    }
}
