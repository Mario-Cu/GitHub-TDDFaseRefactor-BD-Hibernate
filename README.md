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
- [x] BilleteIdTest.java
- [x] InfoRecorridoTest.java

## Code to test Ratio por Clase
**(Como no estaban en la entrega anterior, añadimos la informacion de todas las clases)** 


**Clase Billete**
* Lineas de codigo no comentadas: **73**
* Lineas de test no comentadas: **116**
* Code to test ratio -> **1:1.56 (1 linea de codigo equivale a 1.56 lineas de tests)**

**Clase BilleteId**
* Lineas de codigo no comentadas: **38**
* Lineas de test no comentadas: **23**
* Code to test ratio -> **1:0.6 (1 linea de codigo equivale a 0.6 lineas de tests)**

**Clase DataBaseManager**
* Lineas de codigo no comentadas: **290**
* Lineas de test no comentadas: **303**
* Code to test ratio -> **1:1.04 (1 linea de codigo equivale a 1.04 lineas de tests)**

**Clase Recorrido**
* Lineas de codigo no comentadas: **136**
* Lineas de test no comentadas: **218**
* Code to test ratio -> **1:1.6 (1 linea de codigo equivale a 1.6 lineas de tests)**

**Clase InfoRecorrido**
* Lineas de codigo no comentadas: **89**
* Lineas de test no comentadas: **77**
* Code to test ratio -> **1:0.86 (1 linea de codigo equivale a 0.86 lineas de tests)**

**Clase Sistema**
* Lineas de codigo no comentadas: **259**
* Lineas de test no comentadas: **463**
* Code to test ratio -> **1:1.77 (1 linea de codigo equivale a 1.77 lineas de tests)**

**Clase Usuario**
* Lineas de codigo no comentadas: **86**
* Lineas de test no comentadas: **111**
* Code to test ratio -> **1:1.3 (1 linea de codigo equivale a 1.3 lineas de tests)**

**Clase SistemaPersistencia**
* Lineas de codigo no comentadas: **94**
* Lineas de test no comentadas: **471**
* Code to test ratio -> **1:5 (1 linea de codigo equivale a 5 lineas de tests)**

**Clase SistemaPersistenciaSinAislamiento**
* Lineas de codigo no comentadas: **108**
* Lineas de test no comentadas: **344**
* Code to test ratio -> **1:3 (1 linea de codigo equivale a 3 lineas de tests)**


## Justificacion de refactorizaciones del catalogo Fowler aplicadas:

* Introduce Parameter Object -> En la clase Recorrido hemos tenido que introducir los ultimos parametros del constructor en un objeto aparte
* Extract Method -> En el constructor de Recorrido hemos extraido parte del fragmento de codigo y lo hemos incluido en un metodo nuevo separado para reducir la complejidad cognitiva
* Replace Magic Number with Symbolic Constant -> En la clase Sistema hemos sustituido el valor del descuento de billete de tren por una constante TRAIN_DISCOUNT_CONSTANT

## Autores
* **Mario Cobreros del Caz -- Horas Dedicadas: 12h** - [marcobr](https://gitlab.inf.uva.es/marcobr)

* **Mario Danov Ivanov -- Horas Dedicadas: 12h** - [mardano](https://gitlab.inf.uva.es/mardano)
