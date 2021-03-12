package dao;

import model.Orcamento;

public class Create implements Runnable{
	
	private OrcamentoDao orcamentoDao;
	private Orcamento orcamento;
	
	public Create(OrcamentoDao orcamentoDao, Orcamento orcamento) {
		super();
		this.orcamentoDao = orcamentoDao;
		this.orcamento = orcamento;
	}

	@Override
	public void run() {
		this.orcamentoDao.create(orcamento);
	}

}
