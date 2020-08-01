package com.liviaaurich.locadora.web.rest;

import com.liviaaurich.locadora.service.BaseService;
import com.liviaaurich.locadora.service.dto.AtorDTO;
import com.liviaaurich.locadora.service.dto.DependenteDTO;
import com.liviaaurich.locadora.service.dto.DiretorDTO;
import com.liviaaurich.locadora.service.dto.dropdown.DropdownDTO;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/diretores")
@RequiredArgsConstructor
public class DiretorResource {

    private static final String API_DIRETOR = "/diretores";

    private static final String ENTITY_NAME = "diretor";

    private static final String APP_NAME = "LocadoraPassaTempo";

    private final BaseService<DiretorDTO> diretorServico;

    @PostMapping
    @Timed
    public ResponseEntity<DiretorDTO> salvar(@Valid @RequestBody DiretorDTO diretorDTO) throws URISyntaxException {
        DiretorDTO result = diretorServico.salvar(diretorDTO);

        return ResponseEntity.created(new URI(API_DIRETOR + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(APP_NAME, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping
    @Timed
    public ResponseEntity<DiretorDTO> atualizar(@Valid @RequestBody DiretorDTO diretorDTO) throws URISyntaxException {
        DiretorDTO result = diretorServico.salvar(diretorDTO);

        return ResponseEntity.created(new URI(API_DIRETOR + result.getId()))
            .headers(HeaderUtil.createEntityUpdateAlert(APP_NAME, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @DeleteMapping("/{id}")
    @Timed
    public ResponseEntity excluir(@PathVariable Long id) {
        diretorServico.excluir(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(APP_NAME, false, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiretorDTO> obterPorId(@PathVariable Long id) {
        Optional<DiretorDTO> result = diretorServico.obterPorId(id);
        return ResponseUtil.wrapOrNotFound(result);
    }

    @GetMapping
    @Timed
    public ResponseEntity<Page<DiretorDTO>> obterTodos(@ModelAttribute DiretorDTO filtro, Pageable pageable) {
        Page<DiretorDTO> page = this.diretorServico.obterTodos(filtro, pageable);

        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("/dropdown/")
    @Timed
    public ResponseEntity<List<DropdownDTO>> obterDiretoresDropdown() {
        List<DropdownDTO> result = diretorServico.obterDropdown();
        return new ResponseEntity<>(result, null, HttpStatus.OK);
    }
}
