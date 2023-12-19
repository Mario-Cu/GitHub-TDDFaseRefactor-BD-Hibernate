# Tecnologias para el Desarrollo Software

Practica: Fase REFACTOR con metodologia TDD + Implementacion BD y framework Hibernate

## Documento Web

* [Doc](https://campusvirtual.uva.es/pluginfile.php/5433793/mod_resource/content/1/tds_23_24_practica3.pdf) - Documento guion de la practica

 
## Clases que forman parte de la solucion 
**(Acompañadas del enum EstadoBillete.java, la clase HibernateUtil y la interfaz IDatabaseManager.java)**
- [x] Billete.java
- [x] BilleteId.java
- [x] DataBaseManager.java
- [x] Recorrido.java
- [x] InfoRecorrido.java
- [x] Sistema.java
- [x] Usuario.java
- [x] SistemaPersistencia.java
- [x] SistemaPersistenciaSinAislamiento.java

## Clases de tests nuevas 

- [x] DataBaseManagerTest.java
- [x] SistemaPersistenciaSinAislamientoTest.java

## Code to test Ratio por Clase
**(Como no estaban en la entrega anterior, añadimos la informacion de todas las clases)** 


**Clase Billete**
* Lineas de codigo no comentadas: **73**
* Lineas de test no comentadas: **114**
* Code to test ratio -> **1:1.56 (1 linea de codigo equivale a 1.56 lineas de tests)**

**Clase BilleteId**
* Lineas de codigo no comentadas: **40**
* Lineas de test no comentadas: ****
* Code to test ratio -> **: ( linea de codigo equivale a  lineas de tests)**

**Clase DataBaseManager**
* Lineas de codigo no comentadas: **327**
* Lineas de test no comentadas: **275**
* Code to test ratio -> **1:0.84 (1 linea de codigo equivale a 0.84 lineas de tests)**

**Clase Recorrido**
* Lineas de codigo no comentadas: **138**
* Lineas de test no comentadas: **281**
* Code to test ratio -> **1:2.04 (1 linea de codigo equivale a 2.04 lineas de tests)**

**Clase InfoRecorrido**
* Lineas de codigo no comentadas: **87**
* Lineas de test no comentadas: ****
* Code to test ratio -> **: ( linea de codigo equivale a  lineas de tests)**

**Clase Sistema**
* Lineas de codigo no comentadas: **252**
* Lineas de test no comentadas: **445**
* Code to test ratio -> **1:1.77 (1 linea de codigo equivale a 1.77 lineas de tests)**

**Clase Usuario**
* Lineas de codigo no comentadas: **86**
* Lineas de test no comentadas: **106**
* Code to test ratio -> **1:1.23 (1 linea de codigo equivale a 1.23 lineas de tests)**

**Clase SistemaPersistencia**
* Lineas de codigo no comentadas: **94**
* Lineas de test no comentadas: **471**
* Code to test ratio -> **1:5 (1 linea de codigo equivale a 5 lineas de tests)**

**Clase SistemaPersistenciaSinAislamiento**
* Lineas de codigo no comentadas: **110**
* Lineas de test no comentadas: **344**
* Code to test ratio -> **1:3 (1 linea de codigo equivale a 3 lineas de tests)**


## Justificacion de refactorizaciones del catalogo Fowler aplicadas:

* Introduce Parameter Object -> En la clase Recorrido hemos tenido que introducir los ultimos parametros del constructor en un objeto aparte
* Extract Method -> En el constructor de Recorrido hemos extraido parte del fragmento de codigo y lo hemos incluido en un metodo nuevo separado para reducir la complejidad cognitiva
* Replace Magic Number with Symbolic Constant -> En la clase Sistema hemos sustituido el valor del descuento de billete de tren por una constante TRAIN_DISCOUNT_CONSTANT

## Autores
* **Mario Cobreros del Caz -- Horas Dedicadas: 12h** - [marcobr](https://gitlab.inf.uva.es/marcobr)

* **Mario Danov Ivanov -- Horas Dedicadas: 12h** - [mardano](https://gitlab.inf.uva.es/mardano)
