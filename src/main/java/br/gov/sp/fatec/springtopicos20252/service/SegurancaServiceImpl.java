package br.gov.sp.fatec.springtopicos20252.service;

import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.gov.sp.fatec.springtopicos20252.entity.Autorizacao;
import br.gov.sp.fatec.springtopicos20252.entity.Usuario;
import br.gov.sp.fatec.springtopicos20252.repository.UsuarioRepository;

@Service
public class SegurancaServiceImpl implements UserDetailsService {

    private UsuarioRepository usuarioRepo;

    public SegurancaServiceImpl(UsuarioRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Usuario usuario = usuarioRepo.findByNome(username).orElseThrow(() -> {
            throw new UsernameNotFoundException("Usuário não encontrado!");
        });
        return User.builder().username(username).password(usuario.getSenha())
                .authorities(usuario.getAutorizacoes().stream()
                        .map(Autorizacao::getNome).collect(Collectors.toList())
                        .toArray(new String[usuario.getAutorizacoes().size()]))
                .build();
    }

}
