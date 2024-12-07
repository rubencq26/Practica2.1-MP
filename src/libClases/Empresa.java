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

    public int getNClientes() {
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
        if (pos != -1) {
            System.out.println("Cliente no encontrado: ");
            return;
        }
        clientes[pos].ver();
        System.out.println("¿Seguro que desea eliminarlo (s/n): ");
        s = sc.next().charAt(0);

        if (s=='s' || s=='S') {
            for(int i=pos; i<=nClientes-1; i--) {
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
        }
    }

    public void baja(){
        String nif;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca el nif de cliente para dar de baja: ");
        nif = sc.nextLine();
        baja(nif);
    }

    public void alta(){
        String nif, nombre, nac;
        float minutos, preciominuto, precio, tarifa;
        Fecha fAl=null;
        Fecha fNac=null;
        Fecha fPer=null;
        Cliente c;
        int tipo;
        Scanner sc = new Scanner(System.in);
        System.out.println("DNI: ");
        nif = sc.nextLine();
        if (buscar(nif)!=-1){
            System.out.println("Ya existe un cliente con este DNI");
            int i= buscar(nif);
            c=getCliente(i+1);
            c.ver();
            sc.close();
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
        System.out.println("Nombre: ");
        nombre = sc.nextLine();
        System.out.println("Fecha Nacimiento: ");
        fNac.pedirFecha();
        System.out.println("Fecha Alta: ");
        fAl.pedirFecha();
        System.out.println("Minutos que habla al mes: ");
        minutos = sc.nextFloat();
        System.out.println("Indique tipo de cliente (1-Movil, 2-Tarifa Plana): ");
        tipo = sc.nextInt();
        System.out.println("Precio por minuto: ");
        preciominuto = sc.nextFloat();
        if (tipo==1){
            System.out.println("Fecha fin permanencia: ");
            fPer.pedirFecha();
            clientes[nClientes]=new ClienteMovil(nif, nombre, fNac, fAl, fPer, minutos, preciominuto);
        }else{
            System.out.println("Nacionalidad: ");
            nac = sc.nextLine();
        }
        nClientes++;
        sc.close();
    }

    public void ver(){
        System.out.println(toString());
    }

    public String toString() {
        String c =
        c = ;
        return c;
    }
}
