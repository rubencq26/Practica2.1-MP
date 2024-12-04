package libClases;

import java.lang.Object;
import libClases.*;

public class Cliente implements  Cloneable, Proceso{
    private final String nif;
    private final int codCliente;
    private String nombre;
    private final Fecha fechaNac;
    private final Fecha fechaAlta;
    private static int contador = 0;
    private static Fecha fechaPorDefecto = new Fecha(1,1,2018);

    //constructores
    public Cliente(String dni, String nombre, Fecha fNac, Fecha fAlta) {
        contador++;
        codCliente = contador;
        nif = dni;
        this.nombre = nombre;

        this.fechaNac = new Fecha(fNac);
        this.fechaAlta = new Fecha(fAlta);
    }
    public Cliente(String dni, String nombre, Fecha fNac) {
        contador++;
        codCliente = contador;
        nif = dni;
        this.nombre = nombre;
        this.fechaNac = new Fecha(fNac);
        this.fechaAlta = new Fecha(fechaPorDefecto);
    }

    public Cliente(Cliente c){
        contador++;
        codCliente = contador;
        nif = c.nif;
        nombre = c.nombre;
        fechaNac = new Fecha(c.fechaNac);
        fechaAlta = new Fecha(c.fechaAlta);
    }

    //Getters
    public String getNif() {
        return nif;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public Fecha getFechaNac() {
        Fecha f = new Fecha(fechaNac);
        return f;
    }

    public Fecha getFechaAlta() {
        Fecha f = new Fecha(fechaAlta);
        return f;
    }

    public static Fecha getFechaPorDefecto() {
        Fecha f = new Fecha(fechaPorDefecto);
        return f;
    }


    //Setters
    public static void setFechaPorDefecto(Fecha fPorDefecto) {
        fechaPorDefecto.setFecha(fPorDefecto.getDia(), fPorDefecto.getMes(), fPorDefecto.getAnio());
    }

    public void setFechaPorDefecto(int d, int m, int a){
        fechaPorDefecto.setFecha(d, m, a);
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechaAlta(int dia, int mes, int anio) {
        fechaAlta.setFecha(dia, mes, anio);
    }

    public void setFechaAlta(Fecha fAlta) {
        fechaAlta.setFecha(fAlta);
    }

    public String toString() {
        String c = nif + " " + fechaNac + ": " + nombre + " (" + codCliente + " - " + fechaAlta + ")";
        return c;
    }

    public void ver(){
        System.out.println(toString());
    }

    public void setFechaNac(int dia, int mes, int anio) {
        fechaNac.setFecha(dia, mes, anio);
    }




    @Override
    public Cliente clone() {
        try {
            // Clonación superficial
            Cliente copia = (Cliente) super.clone();

            // Realizamos una copia profunda de las fechas para evitar referencias compartidas
            // Crear nuevas instancias de Fecha para fechaNac y fechaAlta
            Fecha fechaNacCopia = new Fecha(this.fechaNac.getDia(), this.fechaNac.getMes(), this.fechaNac.getAnio());
            Fecha fechaAltaCopia = new Fecha(this.fechaAlta.getDia(), this.fechaAlta.getMes(), this.fechaAlta.getAnio());

            // Debido a que las variables son final, no podemos reasignarlas,
            // pero ya hemos creado copias independientes de los objetos Fecha.
            return new Cliente(this.nif, this.nombre, fechaNacCopia, fechaAltaCopia);

        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Clonación no soportada"); // Nunca debería ocurrir
        }
    }

    public boolean equals(Object obj){
        if(this == obj)return true;
        if(obj == null)return false;
        if(getClass() != obj.getClass()){
            return false;
        }
        Cliente c = (Cliente)obj;
        return (nif.equals(c.nif));
    }

}
