package com.padima.MicroServicio.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private String rut;
    private String nombre;
    private String mail;
    private Integer idcurso;
}
