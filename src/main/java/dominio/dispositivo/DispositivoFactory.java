package dominio.dispositivo;

public class DispositivoFactory {
    private Dispositivo nuevoEstandar (String nombre, Double consumo, Rango restricciones) {
        return new Dispositivo(new ComportamientoEstandar(consumo,0d),nombre, restricciones);
    }
    
    private Dispositivo nuevoInteligente (String nombre, Double consumo, Rango restricciones) {
        return new Dispositivo(new ComportamientoInteligente(null,consumo),nombre ,restricciones);
    }
    
    public Dispositivo aire3500(){
        return nuevoInteligente("Aire acondicionado",1.613, new Rango(90d,360d));
    }

    public Dispositivo aire2200(){
        return nuevoInteligente("Aire acondicionado",1.013, new Rango(90d,360d));
    }
    
    public Dispositivo tvFluo21(){
        return nuevoEstandar("TV",0.075, new Rango(90d,360d));
    }
    
    public Dispositivo tvFluo29a34(){
        return nuevoEstandar("TV",0.175, new Rango(90d,360d));
    }
    
    public Dispositivo tvLCD40(){
        return nuevoEstandar("TV",0.18, new Rango(90d,360d));
    }
    
    public Dispositivo tvLED24(){
        return nuevoInteligente("TV",0.04, new Rango(90d,360d));
    }
    
    public Dispositivo tvLED32(){
        return nuevoInteligente("TV",0.055, new Rango(90d,360d));
    }
    
    public Dispositivo tvLED40(){
        return nuevoInteligente("TV",0.08, new Rango(90d,360d));
    }
    
    public Dispositivo HeladeraConFreezer(){
        return nuevoInteligente("Heladera",0.09,null);
    }
    
    public Dispositivo HeladeraSinFreezer(){
        return nuevoInteligente("Heladera",0.075,null);
    }
    
    public Dispositivo LavarropaAuto5KGCalentador(){
        return nuevoEstandar("Lavarropa",0.875, new Rango(6d,30d));
    }
    
    public Dispositivo LavarropaAuto5KG(){
        return nuevoInteligente("Lavarropa",0.175, new Rango(6d,30d));
    }
    
    public Dispositivo LavarropaSemi5KG(){
        return nuevoEstandar("Lavarropa",0.1275, new Rango(6d,30d));
    }
    
    public Dispositivo ventiladorPie(){
        return nuevoEstandar("Ventilador",0.09, new Rango(120d,360d));
    }
    
    public Dispositivo ventiladorTecho(){
        return nuevoInteligente("Ventilador",0.06, new Rango(6d,30d));
    }
    
    public Dispositivo LamparaHalogena40W(){
        return nuevoInteligente("Lampara",0.04, new Rango(90d,360d));
    }
    
    public Dispositivo LamparaHalogena60W(){
        return nuevoInteligente("Lampara",0.06, new Rango(90d,360d));
    }
    
    public Dispositivo LamparaHalogena100W(){
        return nuevoInteligente("Lampara",0.015, new Rango(90d,360d));
    }
    
    public Dispositivo Lampara11W(){
        return nuevoInteligente("Lampara",0.011, new Rango(90d,360d));
    }
    
    public Dispositivo Lampara15W(){
        return nuevoInteligente("Lampara",0.015, new Rango(90d,360d));
    }
    
    public Dispositivo Lampara20W(){
        return nuevoInteligente("Lampara",0.02, new Rango(90d,360d));
    }
    
    public Dispositivo pc(){
        return nuevoInteligente("PC",0.4, new Rango(60d,360d));
    }
    
    public Dispositivo microondas(){
        return nuevoEstandar("Microondas",0.64, new Rango(3d,15d));
    }
    
    public Dispositivo plancha(){
        return nuevoEstandar("Plancha",0.75, new Rango(3d,30d));
    }
}