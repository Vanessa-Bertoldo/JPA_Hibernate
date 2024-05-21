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
		Game game1 = new Game();
		game1.setTitulo("mega Man 1");
		game1.setCategoria("Plataforma");
		game1.setDataLancamento(LocalDate.of(1987, 12, 1));
		game1.setFinalizado(true);
		game1.setProdutora("capcom");
		game1.setValor(128.0);
		
		EntityManager em = Conexao.getEntityManager();
		GameDao gameDao = new GameDao(em);
				
		//Iniciando uma transação
		em.getTransaction().begin();
		gameDao.salvar(game1); //execução do método de persistência
		em.getTransaction().commit(); //efetiva a transação
		em.close(); //libera recursos não necessarios
		
		//Metodo atualizar
		em.getTransaction().begin();
		gameDao.atualizar(game1);
		em.getTransaction().commit();
		em.close();
	}

}
