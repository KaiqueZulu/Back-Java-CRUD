package com.example.primeiroexemplo.repository;

import com.example.primeiroexemplo.model.Produto;
import com.example.primeiroexemplo.model.exception.ResourceNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProdutoRepository_old {
    /*obterTodos, obterPorId, adicionar, atualizar, deletar*/
    private List<Produto> produtos = new ArrayList<Produto>();
    private Integer ultimoId = 0;

    /**
     * Metodo para retornar uma lista de produtos.
     * @return Lista de Produtos.
     */
    public List<Produto> obterTodos(){
        return produtos;
    }

    /**
     * Metodo que retorna o produto encontrado pelo seu id.
     * @param id do produto que sera localizado.
     * @return Retorna um produto caso encontrado.
     */
    public Optional<Produto> obterPorId(Integer id) {
        return produtos
                .stream()
                .filter(produto -> produto.getId() == id)
                .findFirst();
    }

    /**
     * Metodo para adicionar produto na lista.
     * @param produto que sera adicionado.
     * @return Retorna o produto que foi adicionado na lista.
     */
    public Produto adicionar(Produto produto){
        ultimoId++;
        produto.setId(ultimoId);
        produtos.add(produto);
        return produto;
    }

    /**
     * Metodo para deletar o produto por id.
     * @param id do produto a ser deletado.
     */
    public void deletar(Integer id){
        produtos.removeIf(produto -> produto.getId().equals(id));
    }

    /**
     * Metodo para atualizar o produto na lista
     * @param produto que sera atualizado.
     * @return Retorna produto apos atualizar a lista
     * */
    public Produto atualizar(Produto produto){
        Optional<Produto> produtoEncontrado = obterPorId(produto.getId());
        if(produtoEncontrado.isEmpty()){
            throw new ResourceNotFoundException("Produto não pode ser atualizado porque não existe.");
        }

        deletar(produto.getId());
        produtos.add(produto);
        return produto;
    }
}
