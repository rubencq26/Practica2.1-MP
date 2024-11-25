package libClases;
import java.util.Scanner;

public class Fecha implements Cloneable, Proceso{
    private int dia;
    private int mes;
    private int anio;

    public void setFecha(int dia, int mes, int anio) {
        int dMes[] = {31,28,31,30,31,30,31,31,30,31,30,31};
        this.anio = anio;
        if(bisiesto()){
            dMes[1] = 29;
        }
        if(mes<1){
            mes = 1;
        }else if(mes>12){
            mes = 12;
        }
        this.mes = mes;

        if(dia>dMes[mes-1]){
            dia = dMes[mes-1];
        } else if (dia < 1) {
            dia = 1;
        }
        this.dia = dia;

    }

    public Fecha(int dia, int mes, int anio) {
        setFecha(dia, mes, anio);
    }

    public Fecha(Fecha f) {
        dia = f.dia;
        mes = f.mes;
        anio = f.anio;
    }

    public int getDia() {return dia;}
    public int getMes() {return mes;}
    public int getAnio() {return anio;}

    public void setDia(int dia) {this.dia = dia;}
    public void setMes(int mes) {this.mes = mes;}
    public void setAnio(int anio) {this.anio = anio;}

    public String toString() {
        String s = "";
        if (dia < 10) s=s+0;
        s=s+dia+"/";
        if(mes < 10) s=s+0;
        s = s+mes+"/"+anio;
        return s;
    }

    public boolean bisiesto(){
        boolean b = false;
        if(anio % 4 == 0){
            b = true;
            if(anio %100 == 0 && anio%400 != 0) {
                b = false;
            }
        }
    return b;
    }

    public void ver(){
        System.out.println(this.toString());
    }

    public static Fecha pedirFecha(){
        Fecha fecha = null;
        boolean valida = false;
        Scanner scanner = new Scanner(System.in);
        int dia, mes, anio;
        do{
            System.out.print("Introduce la Fecha (dd/mm/yyyy): ");
            String cadena = scanner.next();
            String[] tokens = cadena.split("/");
            try{
                if(tokens.length != 3){
                 throw new NumberFormatException();
                }
                dia = Integer.parseInt(tokens[0]);
                mes = Integer.parseInt(tokens[1]);
                anio = Integer.parseInt(tokens[2]);

                fecha = new Fecha(dia, mes, anio);

                if(fecha.getDia() != dia || fecha.getMes() != mes){
                    throw new NumberFormatException();
                }
                valida = true;
            }catch(NumberFormatException e){
                System.out.println("Fecha no valida");
            }
        }while(!valida);
        scanner.close();
        return fecha;
    }
    static public boolean mayor(Fecha f1, Fecha f2){
        if(f1.anio*10000 + f1.mes*100 + f1.dia > f2.anio*10000 + f2.mes*100 + f2.dia){
            return true;
        }else{
            return false;
        }
    }

    public Object clone(){
        Object obj = null;
        try{
            obj = super.clone();
        }catch(CloneNotSupportedException e){
            System.out.println("no se puede duplicar");
        }
        return obj;
    }

    public boolean equals(Object obj){
        if(this == obj)return true;
        if(obj == null)return false;
        if(getClass() != obj.getClass()){
            return false;
        }
        Fecha c = (Fecha)obj;
        return (dia ==c.dia && mes ==c.mes && anio == c.anio);
    }

}




