```markdown
# Sales Project

Este proyecto es una aplicación en Java que procesa archivos de ventas de varios vendedores y productos, generando reportes de ventas por vendedor y por producto.

## Estructura del Proyecto

```
SalesProject/
├── src/
│   └── com/
│       └── project/
│           └── sales/
│               ├── GenerateInfoFiles.java
│               └── Main.java
├── data/
│   ├── salesman_info.txt
│   ├── products.txt
│   ├── sales_12345678.txt
│   ├── sales_87654321.txt
│   ├── sales_11223344.txt
│   ├── sales_44332211.txt
│   └── sales_99887766.txt
├── data/salesman_report.csv
├── data/product_report.csv
└── README.md
```

## Descripción

El proyecto tiene dos clases principales:

1. **GenerateInfoFiles**: Esta clase genera archivos de prueba con datos pseudoaleatorios de vendedores, productos y ventas.
2. **Main**: Esta clase procesa los archivos de ventas, productos y vendedores, y genera dos reportes:
   - `salesman_report.csv`: Reporte de los vendedores con el total de dinero recaudado, ordenado de mayor a menor.
   - `product_report.csv`: Reporte de los productos vendidos por cantidad, ordenado de mayor a menor.

### Archivos de entrada:

1. **salesman_info.txt**: Contiene la información de los vendedores.

2. **products.txt**: Contiene la información de los productos disponibles.

3. **Archivos de ventas**: Cada archivo contiene las ventas de un vendedor. Ejemplo de `sales_12345678.

1. **salesman_report.csv**: Reporte con el total de ventas por cada vendedor, ordenado de mayor a menor.
 
2. **product_report.csv**: Reporte con el nombre de los productos, la cantidad total vendida y el 

## Requisitos del Sistema

- Java 8 o superior.

## Instrucciones de Ejecución

### 1. Generar Archivos de Prueba

Para generar los archivos de prueba, ejecuta la clase `GenerateInfoFiles`. Esto creará archivos de prueba para vendedores, productos y ventas.

```bash
javac src/com/project/sales/GenerateInfoFiles.java
java com.project.sales.GenerateInfoFiles
```

### 2. Procesar Archivos de Ventas y Generar Reportes

Ejecuta la clase `Main` para procesar los archivos de ventas, productos y vendedores. Los reportes generados estarán en la carpeta `data/`.

```bash
javac src/com/project/sales/Main.java
java com.project.sales.Main
```

### Archivos generados:

- `data/salesman_report.csv`
- `data/product_report.csv`

## Funciones Adicionales

1. **Procesar múltiples archivos de ventas**: El programa puede procesar varios archivos de ventas por vendedor.
2. **Validación de datos**: El programa verifica la existencia de productos y maneja errores como cantidades negativas o IDs inválidos.
3. **Serialización de archivos**: Se puede modificar fácilmente para manejar archivos serializados.

