package com.parkingmanagement.parkingmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import java.time.LocalTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Occupation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @CPF
    private String clientCpf;
    @OneToOne
    private Car car;
    private LocalTime hourEntry;
    private LocalTime hourExit;
    private float price;

}
