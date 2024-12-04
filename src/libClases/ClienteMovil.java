package libClases;
import libClases.Cliente;

public class ClienteMovil extends Cliente {
    private Fecha permanecia;
    private float minutos;
    private float precioMinuto;

    public ClienteMovil(String dni, String nombre, Fecha fNac, Fecha fAlta, Fecha permanecia, float minutos, float precioMinuto) {
        super(dni, nombre, fNac, fAlta);
        this.permanecia = permanecia;
        this.minutos = minutos;
        this.precioMinuto = precioMinuto;
    }

    public ClienteMovil(String dni, String nombre, Fecha fNac, float minutos, float precioMinuto) {
        super(dni, nombre, fNac);
        this.minutos = minutos;
        this.precioMinuto = precioMinuto;
        this.permanecia = new Fecha(getFechaAlta().getDia(), getFechaAlta().getMes(), getFechaAlta().getAnio() + 1 );
    }

    public ClienteMovil(ClienteMovil c){
        super(c);
        this.minutos = c.minutos;
        this.precioMinuto = c.precioMinuto;
        this.permanecia = new Fecha(c.permanecia);
    }

    public Fecha getFPermanencia() {
        Fecha f = new Fecha(permanecia);
        return f;
    }
    public float getMinutos() {
        return minutos;
    }
    public float getPrecioMinuto() {
        return precioMinuto;
    }

    public void setFPermanencia(Fecha permanecia) {
        Fecha f = new Fecha(permanecia);
        this.permanecia = f;
    }

    public void setMinutos(float minutos) {
        this.minutos = minutos;
    }

    public void setPrecioMinuto(float precioMinuto) {
        this.precioMinuto = precioMinuto;
    }


    @Override
    public void ver(){
        System.out.println(toString());
    }

    public String toString() {
        String c = super.toString();
        c = c + " " + permanecia + " " + minutos + " x " + precioMinuto + " --> " + factura();
        return c;
    }

    public float factura(){
        return minutos * precioMinuto;
    }

    public ClienteMovil clone() {
        // Realizamos la clonación superficial utilizando super.clone(), lo que devolverá un Cliente
        // La clase ClienteMovil se asegura de que la clonación devuelva un ClienteMovil
        return new ClienteMovil(this);
    }


}


