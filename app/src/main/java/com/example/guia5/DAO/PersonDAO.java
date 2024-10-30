package com.example.guia5.DAO;
import androidx.lifecycle.LiveData; //agregar
import androidx.room.Dao; //agregar
import androidx.room.Delete;//agregar
import androidx.room.Insert;//agregar
import androidx.room.Query;//agregar
import androidx.room.Update;//agregar
import com.example.guia5.Entities.Personas;
import java.util.List;
@Dao
public interface PersonDAO {

    @Insert
    void Insert(Personas personaEntity);

    @Query("SELECT * FROM PERSONAS")
    LiveData<List<Personas>> obtenerTodasLasPersonas();

    @Delete
    void Delete(Personas personaEntity);

    @Update
    void Update(Personas personaEntity);
}
