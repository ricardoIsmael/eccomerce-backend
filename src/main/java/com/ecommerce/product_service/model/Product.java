package com.ecommerce.product_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(value = "product") //document le dice a spring que cuando guardemos esta clase en spring deira que la meta dentro de una collecion llamada product y la crea
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private BigDecimal price; //double puded tener algun error en redondeo
}
/*Referencia de Anotaciones Lombok
@Data: Es una anotación "combo". Su función principal es eliminar el código repetitivo (Boilerplate).

Getters y Setters: Genera el acceso a todos los atributos automáticamente.

Comparación de Objetos: Sobrescribe los métodos equals() y hashCode(). Esto permite que,
si tienes dos objetos Product con el mismo ID y nombre, Java entienda que son el mismo producto al compararlos.

Representación:
Genera un método toString() que muestra los valores reales de los campos en lugar de la dirección de memoria.

@Builder: Implementa el patrón de diseño "Constructor".
Su función es la flexibilidad y legibilidad.

Creación por pasos: Permite asignar solo los campos que
 necesitas en ese momento sin depender de un constructor gigante.

Inmutabilidad: Ayuda a mantener los objetos más seguros, ya que puedes ver
claramente qué valor asignas a qué atributo sin confundirte con el orden de los parámetros.*/