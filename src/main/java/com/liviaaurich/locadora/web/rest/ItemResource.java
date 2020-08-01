package com.liviaaurich.locadora.web.rest;

import com.liviaaurich.locadora.service.BaseService;
import com.liviaaurich.locadora.service.dto.AtorDTO;
import com.liviaaurich.locadora.service.dto.ClasseDTO;
import com.liviaaurich.locadora.service.dto.DiretorDTO;
import com.liviaaurich.locadora.service.dto.ItemDTO;
import com.liviaaurich.locadora.service.dto.dropdown.DropdownDTO;
import com.liviaaurich.locadora.service.impl.ItemService;
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
@RequestMapping("/api/v1/itens")
@RequiredArgsConstructor
public class ItemResource {

    private static final String API_ITEM = "/itens";

    private static final String APP_NAME = "LocadoraPassaTempo";

    private static final String ENTITY_NAME = "item";

    private final BaseService<ItemDTO> baseService;

    private final ItemService itemService;

    @PostMapping
    @Timed
    public ResponseEntity<ItemDTO> salvar(@Valid @RequestBody ItemDTO itemDTO) throws URISyntaxException {
        ItemDTO result = baseService.salvar(itemDTO);

        return ResponseEntity.created(new URI(API_ITEM + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(null, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping
    @Timed
    public ResponseEntity<ItemDTO> atualizar(@Valid @RequestBody ItemDTO itemDTO) throws URISyntaxException {
        ItemDTO result = baseService.salvar(itemDTO);

        return ResponseEntity.created(new URI(API_ITEM + result.getId()))
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
    public ResponseEntity<ItemDTO> obterPorId(@PathVariable Long id) {
        Optional<ItemDTO> result = baseService.obterPorId(id);
        return ResponseUtil.wrapOrNotFound(result);
    }

    @GetMapping
    @Timed
    public ResponseEntity<Page<ItemDTO>> obterTodos(@ModelAttribute ItemDTO filtro, Pageable pageable) {
        Page<ItemDTO> page = baseService.obterTodos(filtro, pageable);

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(UriComponentsBuilder.newInstance(), page);
        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }

    @GetMapping("/dropdown/")
    @Timed
    public ResponseEntity<List<DropdownDTO>> obterTitulosDropdown() {
        List<DropdownDTO> result = baseService.obterDropdown();
        return new ResponseEntity<>(result, null, HttpStatus.OK);
    }

    @GetMapping("/{idItem}/classe")
    @Timed
    public ResponseEntity<ClasseDTO> obterClassePorItem(@PathVariable Long idItem) {
        ClasseDTO classeDTO = this.itemService.obterClasseByItem(idItem);

        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(classeDTO));
    }
}
