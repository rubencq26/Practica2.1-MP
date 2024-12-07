package libClases;
import libClases.*;

import java.util.Scanner;

public class Empresa implements Cloneable, Proceso{
    private Cliente[] clientes;
    private int nClientes;
    private int nmax;

    private int buscar(Cliente c){
        for (int i = 0; i < nClientes; i++) {
            if(c.equals(clientes[i])){
                return i;
            }
        }
        return -1;
    }
    private int buscar(String dni){
        for (int i = 0; i < nClientes; i++) {
            if(clientes[i].getNif().equals(dni)){
                return i;
            }
        }
        return -1;
    }

    public Empresa(){
        nClientes = 0;
        nmax = 5;
        clientes = new Cliente[5];
    }

    public int getN() {
        return nClientes;
    }

    public int getNmax() {
        return nmax;
    }

    public Cliente getCliente(int i) {
        return clientes[i-1];
    }

    public void alta(Cliente c){
        if (buscar(c) != -1) {
            return;
        }
        if(nmax==nClientes){
            Cliente[] aux=clientes;
            nmax+=5;
            aux=new Cliente[nmax];
            for(int i=0; i<nClientes; i++) {
                aux[i] =clientes[i];
            }
            clientes=aux;
        }
        clientes[nClientes]=c;
        nClientes++;
    }

    public void baja(String dni){
        char s;
        int pos=buscar(dni);
        Scanner sc = new Scanner(System.in);
        if (pos == -1) {
            System.out.println("Cliente no encontrado: ");
            return;
        }

        for(int i=pos; i< nClientes-1; i++) {
            clientes[i] = clientes[i+1];
        }
        if (nmax-5 == nClientes && nmax!=5){
            Cliente[] aux=clientes;
            for(int i=0; i < nClientes; i++) {
                aux[i] = clientes[i];
            }
            clientes = aux;
        }
            clientes[nClientes - 1] = null;
            nClientes--;
    }

    public void baja(){
        String nif,nombre;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca el nif de cliente para dar de baja: ");
        nif = sc.nextLine();
        int pos=buscar(nif);
        if (pos == -1) {
            System.out.println("Cliente no encontrado: ");
            return;
        }
        clientes[pos].ver();
        System.out.println("¿Seguro que desea eliminarlo (s/n): ");
        char s = sc.next().charAt(0);
        if (s=='s' || s=='S') {
            nombre=clientes[pos].getNombre();
            for(int i=pos; i < nClientes-1; i++) {
                clientes[i]  =clientes[i+1];
            }
            if (nmax-5==nClientes && nmax!=5){
                Cliente[] aux=clientes;
                for(int i=0; i<nClientes; i++) {
                    aux[i] =clientes[i];
                }
                clientes=aux;
            }
            clientes[nClientes-1]=null;
            nClientes--;
            System.out.println("El cliente "+ nombre +" con nif "+ nif +" ha sido eliminado. \n");
        }else{
            System.out.println("El cliente con nif "+ clientes[pos].getNif() +" no se elimina. \n");
        }
    }

    public void alta(){
        String nif, nombre, nac;
        float minutos, preciominuto, precio, tarifa;
        Cliente c;
       Fecha fNac, fPer=null, fAl;
        int tipo;
        Scanner sc = new Scanner(System.in);
        System.out.println("DNI: ");
        nif = sc.nextLine();
        if (buscar(nif)!=-1){
            System.out.println("Ya existe un cliente con este DNI");
            int i= buscar(nif);
            c=getCliente(i+1);
            c.ver();

        }else{
            System.out.println("Nombre: ");
            nombre = sc.nextLine();
            System.out.println("Fecha Nacimiento: ");
            fNac= Fecha.pedirFecha();
            System.out.println("Fecha Alta: ");
            fAl =Fecha.pedirFecha();
            System.out.println("Minutos que habla al mes: ");
            minutos = sc.nextFloat();
            System.out.println("Indique tipo de cliente (1-Movil, 2-Tarifa Plana): ");
            tipo = sc.nextInt();

            if (tipo==1){
                System.out.println("Precio por minuto: ");
                preciominuto = sc.nextFloat();
                System.out.println("Fecha fin permanencia: ");
                fPer=Fecha.pedirFecha();
                c =new ClienteMovil(nif, nombre, fNac, fAl, fPer, minutos, preciominuto);
            }else{
                System.out.println("Nacionalidad: ");
                sc.nextLine();
                nac = sc.nextLine();
                c = new ClienteTarifaPlana(nif,nombre,fNac, fAl, minutos, nac);
            }
            alta(c);


        }

    }

    public void ver(){
        System.out.println(toString());
    }

    public String toString() {
        String c = "";
        for (int i = 0; i < nClientes; i++) {
            c += clientes[i].toString()+ "\n";
        }
        return c;
    }

    public float factura(){
        float fact=0;
        ClienteMovil cm;
        ClienteTarifaPlana ctp;
        for (int i = 0; i < nClientes; i++) {
            if(clientes[i].getClass()==ClienteMovil.class){
                cm = (ClienteMovil)clientes[i];
                fact +=cm.factura();
            }else{
                ctp = (ClienteTarifaPlana)clientes[i];
                fact +=ctp.factura();
            }
        }
        return fact;
    }

    public Object clone() {
        Empresa emp= new Empresa();
        ClienteMovil cm;
        ClienteTarifaPlana ct;
        for (int i = 0; i < nClientes; i++) {
            if(clientes[i].getClass()==ClienteMovil.class){
                cm = new ClienteMovil((ClienteMovil) clientes[i]);
                emp.alta(cm);
            }else{
                ct = new ClienteTarifaPlana((ClienteTarifaPlana) clientes[i]);
                emp.alta(ct);
            }

        }
        return emp;
    }

    public int nClienteMovil() {
        int nCm= 0;
        for (int i = 0; i < nClientes; i++) {
            if(clientes[i].getClass()==ClienteMovil.class){
                nCm++;
            }
        }
        return nCm;
    }

    public void descuento(int desc) {
        ClienteMovil cm;
        for (int i = 0; i < nClientes; i++) {
            if (clientes[i] instanceof ClienteMovil) {  // Usar instanceof para comprobar el tipo
                cm = (ClienteMovil) clientes[i];
                // Aplicar el descuento correctamente en porcentaje
                float precioConDescuento = cm.getPrecioMinuto() * (1 - desc / 100.0f);
                cm.setPrecioMinuto(precioConDescuento);
            }
        }
    }
}
