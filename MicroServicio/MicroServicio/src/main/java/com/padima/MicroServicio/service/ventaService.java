package com.padima.MicroServicio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.padima.MicroServicio.DTO.UsuarioDTO;
import com.padima.MicroServicio.model.venta;
import com.padima.MicroServicio.repository.ventaRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ventaService {

    private final WebClient webClient;

    public ventaService(WebClient webClient) {
        this.webClient = webClient;
    }

    @Autowired
    private ventaRepository ventarepository;

    public UsuarioDTO BuscarUsuario(String rut){
        UsuarioDTO usuario = webClient.get()
                                .uri("/{rut}", rut)
                                .retrieve()
                                .bodyToMono(UsuarioDTO.class)
                                .block();
    return usuario;

    }
    


    public List<venta> BuscarTodaVenta(){
        return ventarepository.findAll();
    }

    public venta BuscarUnaVenta(Long idventa){
        return ventarepository.findById(idventa).get();
    }

    public venta GuardarVenta(venta venta){
        return ventarepository.save(venta);
    }

    public void EliminarVenta(Long idventa){
        ventarepository.deleteById(idventa);
    }
}
