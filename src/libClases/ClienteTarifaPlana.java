package libClases;
import libClases.Cliente;

public class ClienteTarifaPlana extends Cliente {
    private String Nacionalidad;
    private float minutos;
    private static float tarifa = 300f;
    private static float precio = 20f;
    private static float precioMinuto = 0.15f;


    public ClienteTarifaPlana(String dni, String nombre, Fecha fNac, Fecha fAlta, float minutos, String Nacionalidad) {
        super(dni, nombre, fNac, fAlta);
        this.minutos = minutos;
        this.Nacionalidad = Nacionalidad;
    }

    public ClienteTarifaPlana(String dni, String nombre, Fecha fNac, float minutos, String Nacionalidad) {
        super(dni, nombre, fNac);
        this.minutos = minutos;
        this.Nacionalidad = Nacionalidad;
    }

    public ClienteTarifaPlana(ClienteTarifaPlana c) {
        super(c);
        this.minutos = c.minutos;
        this.Nacionalidad = c.Nacionalidad;
    }


    public String getNacionalidad() {
        return Nacionalidad;
    }

    public static void setTarifa(float t) {
        tarifa = t;
    }

    public static void setTarifa(float t, float p) {
        tarifa = t;
        precio = p;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void setNacionalidad(String Nacionalidad) {
        this.Nacionalidad = Nacionalidad;
    }

    public String toString() {
        String c = super.toString();
        c = c + " " + Nacionalidad + " [" + tarifa + " por " + precio + "] " + minutos + " --> " + factura();
        return c;
    }

    public float factura() {
        float total = minutos - tarifa;
        if(total < 0){
            total = 0;
        }
        return (precio) + total * precioMinuto;
    }

    public static void setPrecioMinuto(float pMinuto) {
        precioMinuto = pMinuto;
    }

    public void ver() {
        System.out.println(toString());
    }


    public static float getLimite() {
        return tarifa;
    }

    public static float getTarifa() {
        return precio;
    }

    public void setMinutos(float minutos) {
        this.minutos = minutos;
    }

    public float getPrecioMinuto() {
        return precioMinuto;
    }

    public ClienteTarifaPlana clone() {
        return new ClienteTarifaPlana(this);
    }

}


