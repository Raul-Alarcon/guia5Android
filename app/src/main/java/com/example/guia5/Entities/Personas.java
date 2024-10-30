package com.example.guia5.Entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.io.Serializable;

@Entity
public class Personas implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int idPersona;
    public String nombrePersona;
    public String apellidoPersona;
    public int edadPersona;

    public Personas(){

    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getApellidoPersona() {
        return apellidoPersona;
    }

    public void setApellidoPersona(String apellidoPersona) {
        this.apellidoPersona = apellidoPersona;
    }

    public int getEdadPersona() {
        return edadPersona;
    }

    public void setEdadPersona(int edadPersona) {
        this.edadPersona = edadPersona;
    }

    @NonNull
    @Override
    public String toString() {
        return nombrePersona + " " + apellidoPersona + " " + edadPersona;
    }
}
