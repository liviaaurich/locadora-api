package com.liviaaurich.locadora.web.rest;

import com.liviaaurich.locadora.service.BaseService;
import com.liviaaurich.locadora.service.dto.AtorDTO;
import com.liviaaurich.locadora.service.dto.ClasseDTO;
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
@RequestMapping("/api/v1/classes")
@RequiredArgsConstructor
public class ClasseResource {

    private static final String API_CLASSE = "/classes";

    private static final String ENTITY_NAME = "classe";

    private static final String APP_NAME = "LocadoraPassaTempo";

    private final BaseService<ClasseDTO> classeServico;

    @PostMapping
    @Timed
    public ResponseEntity<ClasseDTO> salvar(@Valid @RequestBody ClasseDTO classeDTO) throws URISyntaxException {
        ClasseDTO result = classeServico.salvar(classeDTO);

        return ResponseEntity.created(new URI(API_CLASSE + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(APP_NAME, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping
    @Timed
    public ResponseEntity<ClasseDTO> atualizar(@Valid @RequestBody ClasseDTO classeDTO) throws URISyntaxException {
        ClasseDTO result = classeServico.salvar(classeDTO);

        return ResponseEntity.created(new URI(API_CLASSE + result.getId()))
            .headers(HeaderUtil.createEntityUpdateAlert(APP_NAME, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @DeleteMapping("/{id}")
    @Timed
    public ResponseEntity excluir(@PathVariable Long id) {
        classeServico.excluir(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(APP_NAME, false, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClasseDTO> obterPorId(@PathVariable Long id) {
        Optional<ClasseDTO> result = classeServico.obterPorId(id);
        return ResponseUtil.wrapOrNotFound(result);
    }

    @GetMapping
    @Timed
    public ResponseEntity<Page<ClasseDTO>> obterTodos(@ModelAttribute ClasseDTO filtro, Pageable pageable) {
        Page<ClasseDTO> page = this.classeServico.obterTodos(filtro, pageable);

        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("/dropdown/")
    @Timed
    public ResponseEntity<List<DropdownDTO>> obterClassesDropdown() {
        List<DropdownDTO> result = classeServico.obterDropdown();
        return new ResponseEntity<>(result, null, HttpStatus.OK);
    }
}
