package com.liviaaurich.locadora.web.rest;

import com.liviaaurich.locadora.service.dto.ClasseDTO;
import com.liviaaurich.locadora.service.impl.LocacaoService;
import io.github.jhipster.web.util.ResponseUtil;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/locacoes")
@RequiredArgsConstructor
public class LocacaoResource {

    private static final String API_LOCACAO = "/locacoes";

    private static final String ENTITY_NAME = "locacao";

    private final LocacaoService locacaoService;

//    private final BaseService<LocacaoDTO> baseService;


//    @PostMapping
//    @Timed
//    public ResponseEntity<LocacaoDTO> salvar(@Valid @RequestBody LocacaoDTO locacaoDTO) throws URISyntaxException {
//        LocacaoDTO result = baseService.salvar(locacaoDTO);
//
//        return ResponseEntity.created(new URI(API_LOCACAO + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(null, false, ENTITY_NAME, result.getId().toString()))
//            .body(result);
//    }
//
//    @DeleteMapping("/{id}")
//    @Timed
//    public ResponseEntity excluir(@PathVariable Long id) {
//        baseService.excluir(id);
//        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(null, false, ENTITY_NAME, id.toString())).build();
//    }
}
