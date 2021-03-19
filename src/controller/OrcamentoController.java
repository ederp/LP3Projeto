package controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dao.OrcamentoDao;
import dao.Read;
import model.Orcamento;
import model.OrcamentoReflexao;

public class OrcamentoController {
	private OrcamentoDao orcamentoDao = new OrcamentoDao();
	
	public void cadastro(Date data, String desc, String categoria, double valor) {
		Orcamento novoOrc = new Orcamento();
		novoOrc.setId(geraId(data)); //gera novo id
		novoOrc.setDescricao(desc);
		novoOrc.setData(data);
		novoOrc.setCategoria(categoria);
		novoOrc.setValor(valor);
		new OrcamentoReflexao(novoOrc, Orcamento.class);
		orcamentoDao.create(novoOrc);
	}
	
	public List<Orcamento> pesquisa(String mes, String ano){
		int m = numeroMes(mes);
		int a = Integer.parseInt(ano);
		List<Orcamento> saida = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		Read read = new Read(orcamentoDao);
		Thread tRead = new Thread(read);
		tRead.start();
		read.getLista().stream()
				.peek(e->{
					cal.setTime(e.getData());
				})
				.filter(e -> cal.get(Calendar.MONTH) == m - 1)
				.filter(e -> cal.get(Calendar.YEAR) == a)
				.forEach(saida::add);
		//return saida;
		return saida;
	}
	
	public void atualiza(int id, Date data, String desc, String categoria, double valor) {
		Orcamento novoOrc = new Orcamento();
		novoOrc.setId(id);
		novoOrc.setDescricao(desc);
		novoOrc.setData(data);
		novoOrc.setCategoria(categoria);
		novoOrc.setValor(valor);
		orcamentoDao.update(novoOrc);
	}
	
	public boolean exclui(int id, Date data, String desc, String categoria, double valor) {
		Orcamento novoOrc = new Orcamento();
		novoOrc.setId(id); 
		novoOrc.setDescricao(desc);
		novoOrc.setData(data);
		novoOrc.setCategoria(categoria);
		novoOrc.setValor(valor);
		return orcamentoDao.delete(novoOrc);
	}
	
	private int numeroMes(String mes) {
		for(Meses m: Meses.values()) {
			if(mes.equalsIgnoreCase(m.toString()))
				return m.getNumeroMes();
		}
		return -1;
	}
		
	private List<Orcamento> pesquisa(){
		return orcamentoDao.read();
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
