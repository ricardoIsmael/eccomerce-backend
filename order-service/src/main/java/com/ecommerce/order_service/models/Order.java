package com.ecommerce.order_service.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "t_orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderNumber;
    /*
     * RELACIÓN UNIDIRECCIONAL (Orden -> Ítems)
     * * 1. cascade = ALL: El ciclo de vida está atado. Si guardas o eliminas
     * esta Orden, automáticamente se guardan o eliminan sus ítems.
     * 2. orphanRemoval = true: Si remueves un ítem de esta lista en Java,
     * Hibernate hace un DELETE en la base de datos para no dejar basura.
     * 3. @JoinColumn: Le indica a la BD que cree la llave foránea 'order_id'
     * directamente en la tabla de los ítems.
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) // orphanRemoval es clave para Updates
    @JoinColumn(name = "order_id") // Crea la FK en la tabla de items
    private List<OrderLineItems> orderLineItemsList;
}
