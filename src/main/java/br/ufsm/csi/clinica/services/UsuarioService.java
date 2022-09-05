package br.ufsm.csi.clinica.services;

import br.ufsm.csi.clinica.model.Usuario;
import br.ufsm.csi.clinica.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private final UsuarioRepository usuarioRepository;
    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario cadastrarUsuario(Usuario u){

        u.setPassword(new BCryptPasswordEncoder().encode(u.getPassword()));

        if(u.getPermission().isEmpty()){
            u.setPermission("user");
        }

        Usuario usuario = this.usuarioRepository.save(u);
        return usuario;
    }
}
