package com.cs.acompanhamentotwo.components;

import com.cs.acompanhamentotwo.model.entities.Usuario;
import com.cs.acompanhamentotwo.repositories.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private UsuarioRepository usuarioRepository;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        Usuario usuario = usuarioRepository.findByEmail(oAuth2Authentication.getName());

        Map<String, Object> map = new HashMap<>();
        map.put("primeiroNome", usuario.getNome());
        map.put("idUsuario", usuario.getId());
        map.put("permissao", usuario.getPerfis());


        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) oAuth2AccessToken;
        token.setAdditionalInformation(map);

        return oAuth2AccessToken;
    }
}
