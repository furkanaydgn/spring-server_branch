package com.server.springserver.model;

import com.server.springserver.enumaration.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Server {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  long id;

    @Column(unique = true)
    @NotEmpty(message = "Ip adress cannot be empty")
    private String isAddress;
    private String name ;
    private String memory;
    private String type;
    private  String imageUrl;
    private Status status;

}
