package com.liviaaurich.locadora.web.rest;

import com.liviaaurich.locadora.service.BaseService;
import com.liviaaurich.locadora.service.dto.AtorDTO;
import com.liviaaurich.locadora.service.dto.ItemDTO;
import com.liviaaurich.locadora.service.dto.SocioDTO;
import com.liviaaurich.locadora.service.dto.dropdown.DropdownDTO;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/socios")
@RequiredArgsConstructor
public class SocioResource {

    private static final String API_SOCIO = "/socios";

    private static final String APP_NAME = "LocadoraPassaTempo";

    private static final String ENTITY_NAME = "sócio";

    private final BaseService<SocioDTO> baseService;

    @PostMapping
    @Timed
    public ResponseEntity<SocioDTO> salvar(@Valid @RequestBody SocioDTO socioDTO) throws URISyntaxException {
        SocioDTO result = baseService.salvar(socioDTO);

        return ResponseEntity.created(new URI(API_SOCIO + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(null, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping
    @Timed
    public ResponseEntity<SocioDTO> atualizar(@Valid @RequestBody SocioDTO socioDTO) throws URISyntaxException {
        SocioDTO result = baseService.salvar(socioDTO);

        return ResponseEntity.created(new URI(API_SOCIO + result.getId()))
            .headers(HeaderUtil.createEntityUpdateAlert(APP_NAME, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @DeleteMapping("/{id}")
    @Timed
    public ResponseEntity excluir(@PathVariable Long id) {
        baseService.excluir(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(null, false, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SocioDTO> obterPorId(@PathVariable Long id) {
        Optional<SocioDTO> result = baseService.obterPorId(id);
        return ResponseUtil.wrapOrNotFound(result);
    }

    @GetMapping
    @Timed
    public ResponseEntity<Page<SocioDTO>> obterTodos(@ModelAttribute SocioDTO filtro, Pageable pageable) {
        Page<SocioDTO> page = this.baseService.obterTodos(filtro, pageable);

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(UriComponentsBuilder.newInstance(), page);
        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }

    @GetMapping("/dropdown/")
    @Timed
    public ResponseEntity<List<DropdownDTO>> obterTitulosDropdown() {
        List<DropdownDTO> result = baseService.obterDropdown();
        return new ResponseEntity<>(result, null, HttpStatus.OK);
    }
}
