package edu.nob.liceo.triviado_proyectofin_nicolasotero.service;

import edu.nob.liceo.triviado_proyectofin_nicolasotero.dao.UsuarioDAO;
import edu.nob.liceo.triviado_proyectofin_nicolasotero.model.Usuario;

import java.util.Optional;

public class UsuarioService {
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    public Optional<Usuario> login(String nickname, String password) {
        Optional<Usuario> usuarioOpt = usuarioDAO.findByNickname(nickname);

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            if (usuario.getPassword().equals(password)) {
                return Optional.of(usuario);
            }
        }
        return Optional.empty();
    }

    public void registrarUsuario(String nickname, String password, boolean esAdmin) throws Exception {
        if (usuarioDAO.findByNickname(nickname).isPresent()) {
            throw new Exception("El nickname ya está en uso. Elige otro.");
        }

        Usuario nuevoUsuario = new Usuario(nickname, password);
        nuevoUsuario.setEsAdmin(esAdmin);
        usuarioDAO.save(nuevoUsuario);
    }
}
