package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import med.voll.api.dto.AtualizaDadosMedicoDTO;
import med.voll.api.dto.DadosCadastroMedicoDTO;
import med.voll.api.dto.DadosDetalhamentoMedicoDTO;
import med.voll.api.dto.DadosListagemMedicoDTO;
import med.voll.api.service.MedicoServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    private final MedicoServiceImpl medicoService;

    @PostMapping
    @Transactional
    public ResponseEntity createMedico(@RequestBody @Valid DadosCadastroMedicoDTO dados, UriComponentsBuilder uriBuilder) {
        var response = medicoService.createMedico(dados);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(response.id()).toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedicoDTO>> listMedicos(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        var response =  medicoService.medicoList(pageable);

        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateMedico(@RequestBody @Valid AtualizaDadosMedicoDTO dto) {
        var response = medicoService.updateMedico(dto);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteMedico(@PathVariable("id") Long id) {
        medicoService.deleteMedico(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detailMedico(@PathVariable("id") Long id) {
        var response = medicoService.detailMedico(id);

        return ResponseEntity.ok(response);
    }

}
