package com.cs.acompanhamentotwo.components;

import com.cs.acompanhamentotwo.model.dto.RegistroAcessoResponseDTO;
import com.cs.acompanhamentotwo.model.entities.Usuario;
import com.cs.acompanhamentotwo.services.RegistroAcessoService;
import com.cs.acompanhamentotwo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenEnhancer implements TokenEnhancer {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RegistroAcessoService registroAcessoService;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        Usuario usuario = usuarioService.findByEmail(oAuth2Authentication.getName());

        RegistroAcessoResponseDTO registroAcessoResponseDTO = registroAcessoService.gravarAcesso(usuario.getId());

        Map<String, Object> map = new HashMap<>();
        map.put("primeiroNome", usuario.getNome());
        map.put("idUsuario", usuario.getId());
        map.put("ultimoAcesso", registroAcessoResponseDTO.getDataAcesso());
        map.put("permissao", usuario.getPerfis());


        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) oAuth2AccessToken;
        token.setAdditionalInformation(map);

        return oAuth2AccessToken;
    }
}
