package med.voll.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import med.voll.api.config.security.TokenServiceConfiguration;
import med.voll.api.dto.DadosAutenticaoDTO;
import med.voll.api.dto.DadosTokenJWT;
import med.voll.api.entities.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AutenticacaoController {

    private final AuthenticationManager authenticationManager;

    private final TokenServiceConfiguration tokenServiceConfiguration;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticaoDTO dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authenticate = authenticationManager.authenticate(authenticationToken);

        var tokenJWT = tokenServiceConfiguration.generateToken((Usuario) authenticate.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

}
