package com.std.ec.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "clientes")
public class Guest implements Serializable {

    @Id
    @Column(name = "id_guest")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idGuest;
    @Column(name = "name")
    private String name;
    @Column(name = "invited")
    private String invited;

}
