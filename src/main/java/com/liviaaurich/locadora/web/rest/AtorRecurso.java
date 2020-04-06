package com.liviaaurich.locadora.web.rest;

import com.liviaaurich.locadora.service.AtorServico;
import com.liviaaurich.locadora.service.dto.AtorDTO;
import io.github.jhipster.web.util.HeaderUtil;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/ator")
@RequiredArgsConstructor
public class AtorRecurso {

    private static final String API_ATOR = "/ator";

    private static final String ENTITY_NAME = "ator";

    private static final String APP_NAME = "Locadora PassaTempo";

    private final AtorServico atorServico;

    @PostMapping
    @Timed
    public ResponseEntity<AtorDTO> salvar(@Valid @RequestBody AtorDTO atorDTO) throws URISyntaxException {
        AtorDTO result = atorServico.salvar(atorDTO);

        return ResponseEntity.created(new URI(API_ATOR + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(APP_NAME, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @DeleteMapping("/{id}")
    @Timed
    public ResponseEntity excluir(@PathVariable Long id) {
        atorServico.excluir(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(APP_NAME, false, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping
    @Timed
    public ResponseEntity<Page<AtorDTO>> obterTodos(@RequestBody AtorDTO filtro, Pageable pageable) {
        Page<AtorDTO> page = this.atorServico.obterTodos(filtro, pageable);

        return new ResponseEntity<>(page, HttpStatus.OK);
    }

}
