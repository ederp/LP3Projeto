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
		synchronized (this) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			StringBuilder sbLinha = new StringBuilder();
			sbLinha.append(orcamento.getId()+"|");
			sbLinha.append(sdf.format(orcamento.getData())+"|");
			sbLinha.append(orcamento.getDescricao()+"|");
			sbLinha.append(orcamento.getCategoria()+ "|");
			sbLinha.append(orcamento.getValor());
			
			try(BufferedWriter bw = new BufferedWriter(new FileWriter("OrcamentoBackup.txt", true))) {
				bw.write(sbLinha.toString());
				bw.newLine();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}	
	}
	
	public List<Orcamento> read() {
			List<Orcamento> lista = new ArrayList<>();
			try(Scanner sc = new Scanner(new FileReader("OrcamentoBackup.txt"))
					.useDelimiter("\\||\\n")) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				while(sc.hasNext()) {
					try {
						Orcamento orc = new Orcamento();
						orc.setId(sc.nextInt());
						orc.setData(sdf.parse(sc.next()));
						orc.setDescricao(sc.next());
						orc.setCategoria(sc.next());
						orc.setValor(Double.parseDouble(sc.next()));
						lista.add(orc);
					} catch (ParseException e) {
						System.out.println(e.getMessage());
					}
				}
			} catch (FileNotFoundException e1) {
				System.out.println(e1.getMessage());
			}
			return lista;
	}
	
	public void update(Orcamento orcamento) {
			List<Orcamento> lista = read();
			
			int index = lista.indexOf(orcamento);
			if(index > -1){
				lista.set(index, orcamento);
	        }
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
	
	protected void createNew() {
		synchronized (this) {
			try(FileWriter fw = new FileWriter("OrcamentoBackup.txt")) {
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	private void rewrite(List<Orcamento> orcamento) {
		Thread tCreateNew = new Thread(new CreateNew(this));
		tCreateNew.start();
		try {
			tCreateNew.join();
			orcamento.stream()
				.forEach(e -> create(e));
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
}