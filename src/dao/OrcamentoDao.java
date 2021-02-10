package dao;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Orcamento;

public class OrcamentoDao {
	public void create(Orcamento orcamento) {
		StringBuilder sbLinha = new StringBuilder();
		sbLinha.append(orcamento.getId()+"|");
		sbLinha.append(orcamento.getData()+"|");
		sbLinha.append(orcamento.getDescricao()+"|");
		sbLinha.append(orcamento.getCategoria()+ "|");
		sbLinha.append(orcamento.getValor()+ "\n");
		try {
			FileWriter fw = new FileWriter("OrcamentoBackup.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(sbLinha.toString());
			bw.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}	
	}
	
	public List<Orcamento> read() {
		List<Orcamento> lista = new ArrayList<>();
		Orcamento orc = new Orcamento();
		try {
			Scanner sc = new Scanner(new FileReader("OrcamentoBackup.txt"))
					.useDelimiter("\\||\\n");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			while(sc.hasNext()) {
				try {
					orc.setId(Integer.parseInt(sc.next()));
					orc.setData(sdf.parse(sc.next()));
					orc.setDescricao(sc.next());
					orc.setCategoria(sc.next());
					orc.setValor(Double.parseDouble(sc.next()));
					lista.add(orc);
				} catch (ParseException e) {
					System.out.println(e.getMessage());
				}
			}
			sc.close();
		} catch (FileNotFoundException e1) {
			System.out.println(e1.getMessage());
		}
		return lista;
	}
	
	public void update(Orcamento orcamento) {
		List<Orcamento> lista = read();
		lista.stream()
			.filter(e -> e.getId() == orcamento.getId())
			.forEach(e ->{
				e.setData(orcamento.getData());
				e.setDescricao(orcamento.getDescricao());
				e.setCategoria(orcamento.getCategoria());
				e.setValor(orcamento.getValor());
			});
		rewrite(lista);
	}
	
	public boolean delete(Orcamento orcamento) {
		List<Orcamento> lista = read();
		if(lista.indexOf(orcamento) != -1) {
			lista.remove(orcamento);
			rewrite(lista);
			return true;
		}
		return false;
	}
	
	protected void rewrite(List<Orcamento> orcamento) {
		orcamento.stream()
			.forEach(e -> create(e));
	}
}
