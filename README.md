# PARCIAL DESARROLLO DE SOFTWARE
Parcial backend de la materia "Desarrollo de Software"

3er año ingeniería en sistemas, UTN

## Alumna
- Beneto, Maria Victoria
- Legajo: 47727
- Comisión: 3K10
- Año: 2024


## Introducción

Magneto quiere reclutar la mayor cantidad de mutantes para poder luchar contra los X-Mens.

Te ha contratado a ti para que desarrolles un proyecto que detecte si un humano es mutante basándose en su secuencia de ADN.

Para eso te ha pedido crear un programa con un método o función con la siguiente firma:

*isMutant(String[] dna)*


## Funcionamiento

Se recibirá como parámetro un array de Strings que representan cada fila de una tabla de (6x6) con la secuencia del ADN. Las letras de los Strings solo pueden ser: (A,T,C,G), las cuales representa cada base nitrogenada del ADN.

Se sabrá si un humano es mutante, si se encuentra *MAS DE UNA SECUENCIA* de cuatro letras iguales, de forma oblicua, horizontal o vertical.

Las filas de la matriz a verificar se ingresan por teclado.

Ejemplo de input: '*ATCGTA*' (esto equivale a una fila de la matriz)

Una vez cargada correctamente la misma, se aplica una función que verifica si hay presencia en la matriz de mutantes o no y se devuelve el resultado al usuario en base a eso.


# Características

**Nivel 1**
- **Detección de Mutantes**: Verifica si una secuencia de ADN es mutante. 

**Nivel 2**
- **API REST**: Expone endpoints para verificar secuencias de ADN y obtener estadísticas.

**Nivel 3**
- **Base de Datos H2**: Almacena los resultados de las verificaciones de ADN.
- **Tests Unitarios**: Incluye tests unitarios para asegurar la correcta funcionalidad del servicio.
- **Diagrama de secuencia:** Se encuentra en la carpeta PDFS del repositorio.
- **Pruebas de stress:** Se realizaron tests en JMeter con éxito.

Dentro de la carpeta "Nivel 3 - pdfs" se encuentran los diagramas de secuencia, las pruebas de stress y CodeCoverage > 80.

# Ejecución

## Deploy en Render
El proyecto ha sido deployado a Render y puede ser accedido mediante el siguiente link:

https://primer-parcial-mutantes.onrender.com


## Base de datos:
La base de datos se encuentra almacenada en

`http://localhost:8080/h2-console`

Las credenciales están configuradas en el archivo application.properties.

## Endpoints
Las peticiones se pueden realizar a:

**POST -> /api/v1/dna/mutant**

- En Render: https://primer-parcial-mutantes.onrender.com/api/v1/dna/mutant

Recibe un array de Strings de una secuencia de ADN en JSON, devuelve si la secuencia es de un ADN mutante o humano.

**GET -> /api/v1/dna/stats**

- En render: https://primer-parcial-mutantes.onrender.com/api/v1/dna/stats

Devuelve la cantidad de ADN mutante y ADN humano en la base de datos, y el ratio entre ambos (cantidad de mutantes por cada humano).

## Uso

### Verificar Secuencia de ADN
Envía una solicitud POST a /api/v1/dna/mutant/ con un JSON que contenga la secuencia de ADN:

```
{
  "dna": [
    "ATGCGA",
    "CAGTGC",
    "TTATGT",
    "AGAAGG",
    "CCCCTA",
    "TCACTG"]
}
```

- Respuesta 200 OK: Si la secuencia de ADN es mutante.
- Respuesta 403 Forbidden: Si la secuencia de ADN no es mutante.

### Obtener Estadísticas
Envía una solicitud GET a /api/v1/dna/stats/ para obtener las estadísticas de las verificaciones de ADN:

```
{
  "count_mutant_dna": 40,
  "count_human_dna": 100,
  "ratio": 0.4
}
```


# Ejemplos de ADN
Se incluye una lista de ejemplos de ADN mutante/no mutante.
También el proyecto cuenta con pruebas unitarias con cada caso.

- Ejemplo ADN humano (no mutante):

```
{
    "dna": [
        "ATGCGA",
        "CAGTGC",
        "TTATTT",
        "AGACGG",
        "CCTCTA",
        "TCACTG"
    ]
}
```
- Ejemplo ADN mutante (horizontal):
```
{
    "dna": [
        "AAAAAA",
        "CAGTGC",
        "TTATGT",
        "AGAAGG",
        "CCCCTA",
        "TCACTG"
    ]
}
```
- Ejemplo ADN mutante (vertical):
 ```
 {
    "dna": [
        "ATGCGA",
        "AAGTGC",
        "ATATGT",
        "AGAAGG",
        "ACCCCT",
        "ATCACT"
    ]
 }
```
- Ejemplo ADN mutante (diagonal):
```
{
    "dna": [
        "ATGCGA",
        "CAGTGC",
        "TTATGT",
        "AGAAGG",
        "CCCCTA",
        "TCACTG"
    ]
}
``` 
