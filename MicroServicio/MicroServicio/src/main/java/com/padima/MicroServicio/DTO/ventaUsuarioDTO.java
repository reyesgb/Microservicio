package com.padima.MicroServicio.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ventaUsuarioDTO {
    
    private Long idventa;
    private String rutusuario;
    private LocalDate fechaventa;
    private String mail;
    private String nombre;

}
