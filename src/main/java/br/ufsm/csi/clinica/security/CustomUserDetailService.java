package br.ufsm.csi.clinica.security;

import br.ufsm.csi.clinica.model.Usuario;
import br.ufsm.csi.clinica.repository.UsuarioRepository;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private UsuarioRepository usuarioRepository;

    public CustomUserDetailService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username);

        if(usuario == null){
            throw new UsernameNotFoundException("Credenciais inválidas");
        }else{
            UserDetails user = User.withUsername(usuario.getUsername())
                    .password(usuario.getPassword())
                    .authorities(usuario.getPermission()).build();

            return user;
        }
    }
}
