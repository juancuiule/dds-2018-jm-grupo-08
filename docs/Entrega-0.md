# Especificación de responsabilidades

## RepositorioCategoria
Ésta clase se va a instanciar una sola vez, posee una lista  con de cada tipo de categoría. Se encarga de iterar sobre la misma preguntando cuál categoría corresponde. No calcula directamente si el consumo del cliente cae en el rango de cada categoría, ya que de eso se encargan ellas. Debido a ésto decidimos diseñarla como un Singleton, ya que no necesitamos más de una instancia de ésta clase. Todos los clientes recurrirán al mismo repositorio a la hora de recategorizarse.

## Categoría
Ésta clase tiene la responsabilidad de verificar si un consumo dado pertenece a su rango de aceptación. Para ello posee un límite inferior y uno superior.
``` java
def Boolean correspondeCategoria(consumo){
    return(this.limiteInferiorDeConsumo < consumo) && (consumo <=this.limiteSuperiorDeConsumo);
}
```
## Cliente
Decidimos por el momento calcular su consumo como los kwh de todos sus dispositivos encendidos, ignorando cualquier otro factor (como el tiempo que mantiene encendido cada uno de ellos).
``` java
def Double consumo(){
    this.dispositivos.stream()
        .filter((Dispositivo dispositivo) -> dispositivo.estaEncendido())
.mapToDouble((Dispositivo dispositivo) ->dispositivo.getkWh()).sum();
}
```
Ahora hablando sobre el método recategorizar:
``` java
def void recategorizar(){
    RepositorioCategorias repositorio = RepositorioCategorias.getInstance();
    public void recategorizar() {
        RepositorioCategorias repositorio = RepositorioCategorias.getInstance();
        this.categoria = repositorio.categorias().stream()
                .filter(categoria -> categoria.correspondeCategoria(this.consumo())).findFirst();
    }
this.categoria = repositorio.categoriaCorrespondiente(this.consumo());
}
```
Como todas las clases entienden mensajes( como el mensaje “new),le mandamos a la clase el mensaje “getInstance”, que devolverá la única instancia de esa clase, o creará una si todavía no la había creado.
