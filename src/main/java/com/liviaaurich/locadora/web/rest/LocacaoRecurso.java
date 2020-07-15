package com.liviaaurich.locadora.web.rest;

import com.liviaaurich.locadora.service.BaseService;
import com.liviaaurich.locadora.service.ClienteService;
import com.liviaaurich.locadora.service.LocacaoService;
import com.liviaaurich.locadora.service.dto.ClasseDTO;
import com.liviaaurich.locadora.service.dto.DependenteDTO;
import com.liviaaurich.locadora.service.dto.LocacaoDTO;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/locacoes")
@RequiredArgsConstructor
public class LocacaoRecurso {

    private static final String API_LOCACAO = "/locacoes";

    private static final String ENTITY_NAME = "locacao";

//    private final BaseService<LocacaoDTO> baseService;

    private final LocacaoService locacaoService;

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

    @GetMapping("/{idItem}/classe")
    @Timed
    public ResponseEntity<ClasseDTO> obterClassePorItem(@PathVariable Long idItem) {
        ClasseDTO classeDTO = this.locacaoService.obterClasseByItem(idItem);

        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(classeDTO));
    }
}
