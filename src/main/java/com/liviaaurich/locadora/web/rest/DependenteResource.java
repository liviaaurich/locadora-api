package com.liviaaurich.locadora.web.rest;

import com.liviaaurich.locadora.service.BaseService;
import com.liviaaurich.locadora.service.dto.AtorDTO;
import com.liviaaurich.locadora.service.dto.ClasseDTO;
import com.liviaaurich.locadora.service.dto.ClienteDTO;
import com.liviaaurich.locadora.service.dto.DependenteDTO;
import com.liviaaurich.locadora.service.dto.dropdown.DropdownDTO;
import com.liviaaurich.locadora.service.impl.DependenteService;
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
@RequestMapping("/api/v1/dependentes")
@RequiredArgsConstructor
public class DependenteResource {

    private static final String API_TITULO = "/dependentes";

    private static final String APP_NAME = "LocadoraPassaTempo";

    private static final String ENTITY_NAME = "dependente";

    private final BaseService<DependenteDTO> baseService;

    private final DependenteService dependenteService;

    @PostMapping
    @Timed
    public ResponseEntity<DependenteDTO> salvar(@Valid @RequestBody DependenteDTO dependenteDTO) throws URISyntaxException {
        DependenteDTO result = baseService.salvar(dependenteDTO);

        return ResponseEntity.created(new URI(API_TITULO + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(null, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping
    @Timed
    public ResponseEntity<DependenteDTO> atualizar(@Valid @RequestBody DependenteDTO dependenteDTO) throws URISyntaxException {
        DependenteDTO result = baseService.salvar(dependenteDTO);

        return ResponseEntity.created(new URI(API_TITULO + result.getId()))
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
    public ResponseEntity<DependenteDTO> obterPorId(@PathVariable Long id) {
        Optional<DependenteDTO> result = dependenteService.obterPorId(id);
        return ResponseUtil.wrapOrNotFound(result);
    }

    @GetMapping("/socio/{id}")
    @Timed
    public ResponseEntity<Page<DependenteDTO>> obterTodosPorSocio(@ModelAttribute DependenteDTO filtro, Pageable pageable, @PathVariable Long id) {
        Page<DependenteDTO> page = this.dependenteService.obterTodosBySocio(filtro, pageable, id);

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(UriComponentsBuilder.newInstance(), page);
        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }

    @GetMapping("/clientes/")
    @Timed
    public ResponseEntity<List<ClienteDTO>> obterTodosClientes() {
        List<ClienteDTO> result = this.dependenteService.obterTodosClientes();

        return new ResponseEntity<>(result, null, HttpStatus.OK);
    }

    @GetMapping("/dropdown/")
    @Timed
    public ResponseEntity<List<DropdownDTO>> obterTitulosDropdown() {
        List<DropdownDTO> result = baseService.obterDropdown();
        return new ResponseEntity<>(result, null, HttpStatus.OK);
    }
}
