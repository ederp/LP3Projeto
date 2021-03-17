package dao;

public class CreateNew implements Runnable{
	private OrcamentoDao orcamentoDao;

	public CreateNew(OrcamentoDao orcamentoDao) {
		super();
		this.orcamentoDao = orcamentoDao;
	}

	@Override
	public void run() {
		this.orcamentoDao.createNew();
	}
}
