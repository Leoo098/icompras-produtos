package com.leodev.icompras.produtos.controller;

import com.leodev.icompras.produtos.model.Produto;
import com.leodev.icompras.produtos.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("produtos")
@RequiredArgsConstructor
@Tag(name = "Produtos")
public class ProdutoController {

    private final ProdutoService service;

    @PostMapping
    @Operation(summary = "Salvar", description = "Cadastrar novo produto")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produto cadastrado com sucesso."),
            @ApiResponse(responseCode = "409", description = "Conflito - Um produto com este nome já existe.")
    })
    public ResponseEntity<Produto> salvar(@RequestBody Produto produto){
        service.salvar(produto);
        return ResponseEntity.ok(produto);
    }

    @GetMapping("{codigo}")
    @PostMapping
    @Operation(summary = "Obter detalhes", description = "Retorna os dados de um produto pelo código")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produto encontrado."),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado.")
    })
    public ResponseEntity<Produto> obterDados(@PathVariable("codigo") Long codigo){
        return service.obterPorCodigo(codigo)
                .map(ResponseEntity::ok)
                .orElseGet( () -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{codigo}")
    @Operation(summary = "Deletar", description = "Deleta o produto pelo código")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produto encontrado."),
            @ApiResponse(responseCode = "404", description = "Produto inexistente.")
    })
    public ResponseEntity<Void> deletar(@PathVariable("codigo") Long codigo){
        var produto = service.obterPorCodigo(codigo)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Produto inexistente"
                ));

        service.deletar(produto);
        return ResponseEntity.noContent().build();
    }
}
