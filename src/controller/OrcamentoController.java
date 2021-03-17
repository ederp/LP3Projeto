package controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dao.Create;
import dao.OrcamentoDao;
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
		Thread tCreate = new Thread(new Create(orcamentoDao, novoOrc));
		tCreate.start();
		try {
			tCreate.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public List<Orcamento> pesquisa(String mes, String ano) {
		int m = converteMes(mes);
		int a = Integer.parseInt(ano);
		Calendar cal = Calendar.getInstance();
		List<Orcamento> saida = new ArrayList<>();
		orcamentoDao.read().stream()
				.peek(e->{
					cal.setTime(e.getData());
				})
				.filter(e -> cal.get(Calendar.MONTH) == m - 1)
				.filter(e -> cal.get(Calendar.YEAR) == a)
				.forEach(saida::add); //fazer uma stream pra pegar a data desejada
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
		
	private int converteMes(String mes) {
		switch (mes) {
		case "janeiro":
			return 1;
		case "fevereiro":
			return 2;
		case "março":
			return 3;
		case "abril":
			return 4;
		case "maio":
			return 5;
		case "junho":
			return 6;
		case "julho":
			return 7;
		case "agosto":
			return 8;
		case "setembro":
			return 9;
		case "outubro":
			return 10;
		case "novembro":
			return 11;
		default:
			return 12;
		}
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
