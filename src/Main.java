//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import libClases.*;

public class Main {
    public static void main(String[] args) {

        Fecha f3 = new Fecha(3,3,2003);
        Cliente c1=new Cliente("793X","Ana Pi",new Fecha(2,2,1972),f3), c2=new Cliente(c1);
        c1.ver();
        c2.ver();
        System.out.println(c1.equals(c2));

    }
}