package com.porto.amazonas.versionamento.security;

import com.porto.amazonas.versionamento.exceptions.VersionamentoException;
import com.porto.amazonas.versionamento.model.Usuario;
import com.porto.amazonas.versionamento.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Classe utilizada para validar o usuario que esta logando no sistema.
 *
 * @author BRUNO VIANA
 */
@Component
@Qualifier("validadorUsuario")
public class ValidadorUsuario implements UserDetailsService {

    @Autowired
    @Qualifier("usuarioService")
    private UsuarioService usuarioService;

    /**
     * Método utilizado para carregarUsuario pelo username (matricula).
     */
    @Override
    public UserDetails loadUserByUsername(String matricula) throws UsernameNotFoundException {
        return getUsuarioPorMatricula(matricula);
    } // fim do método loadUserByUsername

    /*
     * Método utilizado para realizar a chamado ao componente de consulta.
     */
    private Usuario getUsuarioPorMatricula(String matricula) {
        Usuario usuario;
        try {
            usuario = usuarioService.buscarUsuarioPorMatricula(matricula);
            if (usuario == null) {
                throw new UsernameNotFoundException("Usuario não encontrado.");
            } // fim do bloco if
        } catch (VersionamentoException e) {
            throw new UsernameNotFoundException(e.getMessage());
        } // fim do bloco try/catch
        return usuario;
    } // fim do método getUsuarioPorMatricula

} // fim da classe ValidadorUsuario