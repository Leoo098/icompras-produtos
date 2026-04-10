package com.leodev.icompras.produtos.service;

import com.leodev.icompras.produtos.model.Produto;
import com.leodev.icompras.produtos.repository.ProdutoRepository;
import com.leodev.icompras.produtos.service.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;

    public Produto salvar(Produto produto){
        if (repository.existsByNome(produto.getNome())){
            throw new ValidationException("Já existe um produto cadastrado com este nome.");
        }

        return repository.save(produto);
    }

    public Optional<Produto> obterPorCodigo(Long codigo){
        return repository.findById(codigo);
    }

    public void deletar(Produto produto) {
        produto.setAtivo(false);
        repository.save(produto);
    }
}
