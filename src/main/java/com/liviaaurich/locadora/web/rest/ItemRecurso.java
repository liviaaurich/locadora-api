package com.liviaaurich.locadora.web.rest;

import com.liviaaurich.locadora.service.ItemServico;
import com.liviaaurich.locadora.service.dto.ItemDTO;
import com.liviaaurich.locadora.service.filtros.ItemFiltro;
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

@RestController
@RequestMapping("/api/item")
@RequiredArgsConstructor
public class ItemRecurso {

    private static final String API_ITEM = "/item";

    private static final String ENTITY_NAME = "item";

    private final ItemServico itemServico;

    @PostMapping
    @Timed
    public ResponseEntity<ItemDTO> salvar(@Valid @RequestBody ItemDTO itemDTO) throws URISyntaxException {
        ItemDTO result = itemServico.salvar(itemDTO);

        return ResponseEntity.created(new URI(API_ITEM + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(null, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @DeleteMapping("/{id}")
    @Timed
    public ResponseEntity excluir(@PathVariable Long id) {
        itemServico.excluir(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(null, false, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping
    @Timed
    public ResponseEntity<Page<ItemDTO>> obterTodos(@ModelAttribute ItemFiltro filtro, Pageable pageable) {
        Page<ItemDTO> page = this.itemServico.obterTodos(filtro, pageable);

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(UriComponentsBuilder.newInstance(), page);
        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }
}
