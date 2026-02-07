package com.leodev.icompras.produtos.repository;

import com.leodev.icompras.produtos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
