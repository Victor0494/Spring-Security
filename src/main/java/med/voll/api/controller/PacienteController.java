package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import med.voll.api.dto.AtualizaDadosPacienteDTO;
import med.voll.api.dto.DadosCadastroPacienteDTO;
import med.voll.api.dto.DadosDetalhamentoPacienteDTO;
import med.voll.api.dto.ListaPacienteDTO;
import med.voll.api.repository.PacienteRepository;
import med.voll.api.service.PacienteServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/paciente")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearer-key")
public class PacienteController {


    private final PacienteRepository pacienteRepository;

    private final PacienteServiceImpl pacienteService;

    @PostMapping
    @Transactional
    public ResponseEntity createPaciente(@RequestBody DadosCadastroPacienteDTO pacienteDTO, UriComponentsBuilder builder) {
        var response = pacienteService.createPaciente(pacienteDTO);
        var uri = builder.path("/paciente/{id}").buildAndExpand(response.id()).toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<ListaPacienteDTO>> listPacientes(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        var response = pacienteService.getListPacientes(pageable);

        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoPacienteDTO> updatePaciente(@RequestBody AtualizaDadosPacienteDTO pacienteDTO) {
        var paciente = pacienteService.updatePaciente(pacienteDTO);

        return ResponseEntity.ok(paciente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePaciente(@PathVariable("id") Long id) {
        pacienteService.deletePaciente(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoPacienteDTO> getDetailsPaciente(@PathVariable("id") Long id) {
        var response = pacienteService.getPacienteById(id);

        return ResponseEntity.ok(response);
    }
}
