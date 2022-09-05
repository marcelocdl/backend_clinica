package br.ufsm.csi.clinica.controller;

import br.ufsm.csi.clinica.model.Usuario;
import br.ufsm.csi.clinica.repository.UsuarioRepository;
import br.ufsm.csi.clinica.security.JWTUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    private UsuarioRepository usuarioRepository;

    public LoginController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> autenticar(@RequestBody Usuario usuario){
        try {

            //String senha = (new BCryptPasswordEncoder().encode(usuario.getPassword()));
            //System.out.println("SENHA-> "+senha);

            //usuario.setPassword(senha);

            final Authentication authentication = this.authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(usuario.getUsername(), usuario.getPassword()));

            if(authentication.isAuthenticated()){
                SecurityContextHolder.getContext().setAuthentication(authentication);

                //usuario = usuarioRepository.findByUsername(usuario.getUsername());

                usuario.setToken(new JWTUtil().gerarToken(usuario));
                usuario.setPassword("");

                return new ResponseEntity<>(usuario, HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<>("Usuário ou senha estão incorretos", HttpStatus.BAD_REQUEST);
    }
}
