package com.example.guia5.Database;
import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.guia5.DAO.PersonDAO;
import com.example.guia5.Entities.Personas;

    @Database(entities = {Personas.class}, version = 1)
public abstract class PersonasDatabase extends  RoomDatabase{
        private static PersonasDatabase instance;
        public abstract PersonDAO personaDAO();
        public static synchronized PersonasDatabase getInstance(Context context) {
            if (instance == null) {
                instance = Room.databaseBuilder(context.getApplicationContext(), PersonasDatabase.class, "personasbd").fallbackToDestructiveMigration().build();
            }
            return instance;
        }
}
