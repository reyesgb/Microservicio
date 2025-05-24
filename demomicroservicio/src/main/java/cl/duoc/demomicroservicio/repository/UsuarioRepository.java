package cl.duoc.demomicroservicio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.duoc.demomicroservicio.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
