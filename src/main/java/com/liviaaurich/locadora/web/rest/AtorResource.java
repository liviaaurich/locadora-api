package com.liviaaurich.locadora.web.rest;

import com.liviaaurich.locadora.service.BaseService;
import com.liviaaurich.locadora.service.dto.AtorDTO;
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
@RequestMapping("/api/v1/atores")
@RequiredArgsConstructor
public class AtorResource {

    private static final String API_ATOR = "/atores";

    private static final String ENTITY_NAME = "ator";

    private static final String APP_NAME = "LocadoraPassaTempo";

    private final BaseService<AtorDTO> atorService;

    @PostMapping
    @Timed
    public ResponseEntity<AtorDTO> salvar(@Valid @RequestBody AtorDTO atorDTO) throws URISyntaxException {
        AtorDTO result = atorService.salvar(atorDTO);

        return ResponseEntity.created(new URI(API_ATOR + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(APP_NAME, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping
    @Timed
    public ResponseEntity<AtorDTO> atualizar(@Valid @RequestBody AtorDTO atorDTO) throws URISyntaxException {
        AtorDTO result = atorService.salvar(atorDTO);

        return ResponseEntity.created(new URI(API_ATOR + result.getId()))
            .headers(HeaderUtil.createEntityUpdateAlert(APP_NAME, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @DeleteMapping("/{id}")
    @Timed
    public ResponseEntity excluir(@PathVariable Long id) {
        atorService.excluir(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(APP_NAME, false, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtorDTO> obterPorId(@PathVariable Long id) {
        Optional<AtorDTO> ator = atorService.obterPorId(id);
        return ResponseUtil.wrapOrNotFound(ator);
    }

    @GetMapping
    @Timed
    public ResponseEntity<Page<AtorDTO>> obterTodos(@ModelAttribute AtorDTO filtro, Pageable pageable) {
        Page<AtorDTO> page = this.atorService.obterTodos(filtro, pageable);

        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("/dropdown/")
    @Timed
    public ResponseEntity<List<DropdownDTO>> obterAtoresDropdown() {
        List<DropdownDTO> result = atorService.obterDropdown();
        return new ResponseEntity<>(result, null, HttpStatus.OK);
    }

}
