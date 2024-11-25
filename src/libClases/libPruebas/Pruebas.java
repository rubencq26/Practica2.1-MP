package libClases.libPruebas;
import libClases.*;

public class Pruebas {
    public static void main(String[] args) {
        Fecha f1 = new Fecha(29,2,2001), f2 = new Fecha(f1);
        Fecha f3 = new Fecha(29, 2,2004);
        final Fecha f4 = new Fecha(05,12,2003);
        System.out.println("Fechas: " + f1.toString() + ", " + f4);
        f1=new Fecha(31,12,2016);
        f4.setFecha(28, 2, 2008);
        System.out.println(f1 +" "+ f2.toString() +" " + f3 + " "+ f4 + " "+ f1);
        f1=new Fecha(f4.getDia()-10, f4.getMes(), f4.getAnio()-7);
        f3=Fecha.pedirFecha();
        System.out.println(f3.toString());
        if (f3.bisiesto() && Fecha.mayor(f2,f1)){
            System.out.println("El " + f3.getAnio() + " fue bisiesto, " + f1 + ", " + f3);
        }
        System.out.print("Fin\n");
    }
}
