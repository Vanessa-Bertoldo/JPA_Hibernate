package br.com.fiap.dao;

import java.util.List;

import br.com.fiap.model.Game;
import jakarta.persistence.EntityManager;

public class GameDao {
	private EntityManager em;
	
	public GameDao(EntityManager em) {
		this.em = em;
	}
	
	public void salvar (Game game) {
		em.persist(game);
	}
	
	public void atualizar(Game game) {
		em.merge(game);
	}
	
	public void remover(Game game) {
		Game gameExcluir = em.find(Game.class, game.getId());
		em.remove(gameExcluir);
	}
	
	public Game buscarGamePeloId(Long Id) {
		return em.find(Game.class, Id);
	}
	
	public List<Game> listarTodosOsGames(){
		//Usamos o nome da classe ao invés do nome da tabela
		String jpqlQuery = "SELECT g FROM Game g ORDER BY g.titulo ASC";
		return em.createQuery(jpqlQuery, Game.class).getResultList();
	}
}
