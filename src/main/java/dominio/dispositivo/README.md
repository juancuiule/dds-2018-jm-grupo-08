# Consideraciones de diseño
Se decide modelar los diferentes tipos de dispositivos utilizando composicion, debido a la necesidad de poder cambiar su comportamiento y/o estructura; Para ello creamos la interfaz ```Comportamiento```

## Comportamientos
### Estandar
El comportamiento estandar recibe los datos necesariso para calcular su consumo en el constructor. Debido a que la interfaz declara mas mensajes de los que por dominio puede entender este comportamiento, algunos metodos se dejaron en blanco, prefiriendo esto por sobre una excepcion que interrumpiese lo que consideramos flujo normal de la aplicacion.

### Inteligente
El dispositivo recibe en su construccion, una interfaz con su fabricante, objeto que sera encargado de servir de adaptador para las clases concretas de cada fabricante  
