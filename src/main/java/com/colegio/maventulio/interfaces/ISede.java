package com.colegio.maventulio.interfaces;

import com.colegio.maventulio.modelo.Sede;
import java.util.List;

public interface ISede {
    
    public List<Sede> ListarSede();
    
    public List<Sede> ListarSedeID(int id_sede);
    
    public int InsertarSede(Sede sede);
    
    public int ActualizarSede(Sede sede);
    
    public int EliminarSede(Sede sede);
    
}
