package com.padima.MicroServicio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.padima.MicroServicio.DTO.UsuarioDTO;
import com.padima.MicroServicio.DTO.ventaUsuarioDTO;
import com.padima.MicroServicio.model.venta;
import com.padima.MicroServicio.service.ventaService;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/v1/ventas")
public class ventaController {

    @Autowired
    private ventaService ventaservice;

    @GetMapping
    public ResponseEntity<?> ListarVentas(){
        List<venta> ventas = ventaservice.BuscarTodaVenta();
        if (ventas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no se encuentran datos");
        } else {
            return ResponseEntity.ok(ventas);
        }
    }

    @GetMapping("/{idventa}")
    public ResponseEntity<?> BuscarVenta(@PathVariable Long idventa){
        try {
            venta ventabuscada = ventaservice.BuscarUnaVenta(idventa);
            return ResponseEntity.ok(ventabuscada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no se encuentran ventas");
        }
    }

    @GetMapping("/VentaUsuario/{idventa}")
    public ResponseEntity<?> DatosVentaUsuario(@PathVariable Long idventa){
        try {
            venta ventabuscada = ventaservice.BuscarUnaVenta(idventa);
            UsuarioDTO usuarioVenta = ventaservice.BuscarUsuario(ventabuscada.getRutusuario());
            ventaUsuarioDTO ventausuario = new ventaUsuarioDTO();
            ventausuario.setFechaventa(ventabuscada.getFechaventa());
            ventausuario.setIdventa(ventabuscada.getIdventa());
            ventausuario.setRutusuario(ventabuscada.getRutusuario());
            ventausuario.setNombre(usuarioVenta.getNombre());
            ventausuario.setMail(ventausuario.getMail());
            return ResponseEntity.ok(ventausuario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no se encuentran ventas o el usuario no esta registrado");

        }
    }


    @PostMapping
    public ResponseEntity<?> GuardarVenta(@RequestBody venta ventaguardar){
        try{
            venta ventaregistrar = ventaservice.GuardarVenta(ventaguardar);
            return ResponseEntity.ok(ventaregistrar);
        
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No se puede registrar la venta");

        }
    }

    @DeleteMapping ("/{idventa}")
    public ResponseEntity<String> EliminarVenta(@PathVariable Long idventa){
        try{
            venta ventabuscada = ventaservice.BuscarUnaVenta(idventa);
            ventaservice.EliminarVenta(idventa);
            return ResponseEntity.status(HttpStatus.OK).body("Se elimino la venta");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Venta no esta registrada");

        }
    }


    @PutMapping("/{idventa}")
    public ResponseEntity<?> ActualizarVenta(@PathVariable Long idventa, @RequestBody venta ventaactualizar){
        try{
            venta ventaactualizada = ventaservice.BuscarUnaVenta(idventa);
            ventaactualizada.setRutusuario(ventaactualizar.getRutusuario());
            ventaactualizada.setFechaventa(ventaactualizar.getFechaventa());
            ventaservice.GuardarVenta(ventaactualizada);
            return ResponseEntity.ok(ventaactualizada);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Venta no esta registrada");

        }

        }
}