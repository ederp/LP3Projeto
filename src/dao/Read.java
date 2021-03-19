package dao;

import java.util.ArrayList;
import java.util.List;

import model.Orcamento;

public class Read implements Runnable{

	//private OrcamentoDao orcamentoDao = new OrcamentoDao();
	private List<Orcamento> lista = new ArrayList<>();
	
	public Read(OrcamentoDao orcamentoDao) {
		lista.addAll(orcamentoDao.read());
	}

	@Override
	public void run() {
		
	}
	
	public List<Orcamento> getLista(){
		return lista;
	}

}
