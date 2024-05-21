package br.com.fiap;

import java.time.LocalDate;

import br.com.fiap.dao.GameDao;
import br.com.fiap.model.Game;
import br.com.fiap.utils.Conexao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {

	public static void main(String[] args) {
		EntityManager em = Conexao.getEntityManager();
		pesquisar(em);
		/*
		//Metodo atualizar
		em.getTransaction().begin();
		gameDao.atualizar(game1);
		em.getTransaction().commit();
		em.close();
		
		//Metodo de excluir
		em.getTransaction().begin();
		gameDao.remover(game1);
		em.getTransaction().commit();
		em.close();*/
		
		
	}
	
	public static void pesquisar(EntityManager em) {
		
		GameDao gameDao = new GameDao(em);
		
		em.getTransaction().begin();
		
		Game gameEncontrado = gameDao.buscarGamePeloId(2L);
		
		if(gameEncontrado != null) {
			System.out.println(gameEncontrado.toString());
		} else {
			System.out.println("Game não encontrado");
		}
		
		em.getTransaction().commit();
		em.close();
	}

	
	public static void cadastrar(EntityManager em) {
		Game game1 = new Game();
		game1.setTitulo("mega Man 1");
		game1.setCategoria("Plataforma");
		game1.setDataLancamento(LocalDate.of(1987, 12, 1));
		game1.setFinalizado(true);
		game1.setProdutora("capcom");
		game1.setValor(128.0);
		
		GameDao gameDao = new GameDao(em);
				
		//Iniciando uma transação
		em.getTransaction().begin();
		gameDao.salvar(game1); //execução do método de persistência
		em.getTransaction().commit(); //efetiva a transação
		em.close(); //libera recursos não necessarios
	}
	
}
