package com.liviaaurich.locadora.web.rest;

import com.liviaaurich.locadora.service.BaseService;
import com.liviaaurich.locadora.service.TituloServico;
import com.liviaaurich.locadora.service.dto.CategoriaDTO;
import com.liviaaurich.locadora.service.dto.TituloDTO;
import com.liviaaurich.locadora.service.dto.dropdown.DropdownDTO;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/titulos")
@RequiredArgsConstructor
public class TituloRecurso {

    private static final String API_TITULO = "/titulos";

    private static final String ENTITY_NAME = "titulo";

    private final BaseService<TituloDTO> baseService;

    private final TituloServico tituloServico;

    @PostMapping
    @Timed
    public ResponseEntity<TituloDTO> salvar(@Valid @RequestBody TituloDTO tituloDTO) throws URISyntaxException {
        TituloDTO result = baseService.salvar(tituloDTO);

        return ResponseEntity.created(new URI(API_TITULO + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(null, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @DeleteMapping("/{id}")
    @Timed
    public ResponseEntity excluir(@PathVariable Long id) {
        baseService.excluir(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(null, false, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping
    @Timed
    public ResponseEntity<Page<TituloDTO>> obterTodos(@ModelAttribute TituloDTO filtro, Pageable pageable) {
        Page<TituloDTO> page = this.baseService.obterTodos(filtro, pageable);

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(UriComponentsBuilder.newInstance(), page);
        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }

    @GetMapping("/categorias/")
    @Timed
    public ResponseEntity<List<CategoriaDTO>> obterCategorias() {
        List<CategoriaDTO> result = tituloServico.obterCategorias();
        return new ResponseEntity<>(result, null, HttpStatus.OK);
    }

    @GetMapping("/dropdown/")
    @Timed
    public ResponseEntity<List<DropdownDTO>> obterTitulosDropdown() {
        List<DropdownDTO> result = baseService.obterDropdown();
        return new ResponseEntity<>(result, null, HttpStatus.OK);
    }
}
