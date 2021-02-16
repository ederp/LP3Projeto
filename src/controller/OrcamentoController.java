package controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dao.OrcamentoDao;
import model.Orcamento;

public class OrcamentoController {
	private OrcamentoDao orcamentoDao = new OrcamentoDao();

	public void cadastro(Date data, String desc, String categoria, double valor) {
		Orcamento novoOrc = new Orcamento();
		novoOrc.setId(geraId(data)); //gera novo id
		novoOrc.setDescricao(desc);
		novoOrc.setData(data);
		novoOrc.setCategoria(categoria);
		novoOrc.setValor(valor);
		orcamentoDao.create(novoOrc);
	}
	
	public List<Orcamento> pesquisa(String mes, String ano) {
		return orcamentoDao.read(); //fazer uma stream pra pegar a data desejada
	}
	
	private List<Orcamento> pesquisa(){
		return orcamentoDao.read();
	}
	
	public void atualiza(int id, Date data, String desc, String categoria, double valor) {
		Orcamento novoOrc = new Orcamento();
		novoOrc.setId(id); //pega o id existente na gui de atualização
		novoOrc.setDescricao(desc);
		novoOrc.setData(data);
		novoOrc.setCategoria(categoria);
		novoOrc.setValor(valor);
		orcamentoDao.update(novoOrc);
	}
	
	public void exclui(int id, Date data, String desc, String categoria, double valor) {
		Orcamento novoOrc = new Orcamento();
		novoOrc.setId(id); //pega o id existente na gui de atualização
		novoOrc.setDescricao(desc);
		novoOrc.setData(data);
		novoOrc.setCategoria(categoria);
		novoOrc.setValor(valor);
		orcamentoDao.delete(novoOrc);
	}
	
	private int geraId(Date data) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		StringBuilder sbData = new StringBuilder();
		sbData.append(cal.get(Calendar.YEAR));
		sbData.append(cal.get(Calendar.MONTH));
		sbData.append(cal.get(Calendar.DAY_OF_MONTH));
		sbData.append(pesquisa().size());
		int id = Integer.parseInt(sbData.toString());
		return id;
	}
}
