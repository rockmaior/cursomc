package com.nelioalves.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.services.exceptions.DataIntegrityException;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository repo;

	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto nao encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	/* Aula 33
	 * Criar o metodo delete repo.deletebyId dentro de um trycatch em
	 * CategoriaService chamando antes o find(); No catch capturar um
	 * DataIntegratyViolationExpection throw new DataIntegrity… a ser criado no
	 * pacote de exceptions para tratar categorias deletadas que possuem produtos
	 * dependentes. Nao ele possível excluir uma categoria que possui produtos.
	 */
	
	public void delete(Integer id) {
		repo.findById(id);
		try {
			repo.deleteById(id);
			
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Nao é possível excluir uma categoria que possui produtos.");
		}
	}

	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}

	public List<Categoria> findAll() {
		List<Categoria> lista = repo.findAll();
		return lista;
	}

}
