package com.liviaaurich.locadora.web.rest;

import com.liviaaurich.locadora.service.BaseService;
import com.liviaaurich.locadora.service.dto.DiretorDTO;
import io.github.jhipster.web.util.HeaderUtil;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/v1/diretores")
@RequiredArgsConstructor
public class DiretorRecurso {

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

    @DeleteMapping("/{id}")
    @Timed
    public ResponseEntity excluir(@PathVariable Long id) {
        diretorServico.excluir(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(APP_NAME, false, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping
    @Timed
    public ResponseEntity<Page<DiretorDTO>> obterTodos(@ModelAttribute DiretorDTO filtro, Pageable pageable) {
        Page<DiretorDTO> page = this.diretorServico.obterTodos(filtro, pageable);

        return new ResponseEntity<>(page, HttpStatus.OK);
    }
}
