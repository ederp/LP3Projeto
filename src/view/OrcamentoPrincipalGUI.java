package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.IntStream;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import controller.OrcamentoController;
import model.Orcamento;

@SuppressWarnings("serial")
public class OrcamentoPrincipalGUI extends JFrame{
	private JTable tabela;
	private JPanel painel;
	private JLabel lbMes, lbAno, lbRecebimentos, lbSomaRecebimentos, lbGastos, lbSomaGastos;
	private JComboBox<String> cbMeses;
	private JComboBox<Integer> cbAnos;
	private static Integer anoAtual = LocalDateTime.now().getYear();
	private JButton btPesquisar, btCriarNovo, btAtualizar, btExcluir;
	private Container cp;
	private List<Orcamento> lista;
	private List<Integer> listaIds = new ArrayList<>();
	
	public OrcamentoPrincipalGUI() {
		//instanciando os componentes de interface
		painel = new JPanel();
		lbMes = new JLabel("Selecione o mês ");
		cbMeses = new JComboBox<String>();
		IntStream.rangeClosed(1, 12)
			.forEach(i -> cbMeses.addItem(Month.of(i).getDisplayName(TextStyle.FULL, Locale.getDefault())));
		lbAno = new JLabel("Selecione o ano ");
		cbAnos = new JComboBox<Integer>();
		IntStream.rangeClosed(2010, anoAtual).forEach(cbAnos::addItem);
		btPesquisar = new JButton("Pesquisar");
		btCriarNovo = new JButton("Criar Novo");
		btAtualizar = new JButton("Atualizar");
		btExcluir = new JButton("Excluir");
		lbRecebimentos = new JLabel("Soma dos recebimentos no mês: ");
		lbGastos = new JLabel("Soma dos gastos no mês: ");
		lbSomaRecebimentos = new JLabel();
		lbSomaGastos = new JLabel();
		
		//configurando os componentes
		setTitle("Programa de Orçamento Mensal");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cbAnos.setSelectedItem(anoAtual);
		cp = getContentPane();
		cp.setLayout(null);
		cp.setBackground(new Color(225, 255, 255));
		painel.setVisible(false);
		btAtualizar.setToolTipText("Atualiza um registro selecionado da tabela");
		btExcluir.setToolTipText("Exclui um registro selecionado da tabela");
		lbRecebimentos.setForeground(Color.BLUE);
		lbRecebimentos.setVisible(false);
		lbSomaRecebimentos.setForeground(Color.BLUE);
		lbGastos.setForeground(Color.RED);
		lbGastos.setVisible(false);
		lbSomaGastos.setForeground(Color.RED);
		
		//definindo o posicionamento dos componentes
	    painel.setBounds(30, 30, 700, 200);
	    lbMes.setBounds(30, 350, 100, 25);
	    cbMeses.setBounds(140, 350, 90, 25);
	    lbAno.setBounds(300, 350, 200, 25);
	    cbAnos.setBounds(400, 350, 60, 25);
	    btPesquisar.setBounds(600, 350, 100, 25);
	    btCriarNovo.setBounds(600, 390, 100, 25);
	    btAtualizar.setBounds(600, 430, 100, 25);
	    btExcluir.setBounds(600, 470, 100, 25);
	    lbRecebimentos.setBounds(30, 250, 200, 25);
	    lbSomaRecebimentos.setBounds(220, 250, 100, 25);
	    lbGastos.setBounds(30, 280, 175, 25);
	    lbSomaGastos.setBounds(180, 280, 100, 25);
	    
	    //adicionando os componentes ao container
	    cp.add(painel);
	    cp.add(lbMes);
	    cp.add(cbMeses); 
	    cp.add(lbAno);
	    cp.add(cbAnos);
	    cp.add(btPesquisar);
	    cp.add(btCriarNovo);
	    cp.add(btAtualizar);
	    cp.add(btExcluir);
	    cp.add(lbRecebimentos);
	    cp.add(lbGastos);
	    cp.add(lbSomaRecebimentos);
	    cp.add(lbSomaGastos);
	    
	    //define as ações para os botões
	    btPesquisar.addActionListener(e -> pesquisarAction());
	    btCriarNovo.addActionListener(e -> cadastrarAction());
	    btAtualizar.addActionListener(e -> atualizarAction());
	    btExcluir.addActionListener(e -> excluirAction());
	}
	
	private void pesquisarAction() {
		if(painel.getComponents().length > 0) {
			painel.removeAll();
			painel.revalidate();
		}
		
		//pegar os dados do arquivo e mostrar a tabela
		lista = new OrcamentoController()
				.pesquisa(cbMeses.getSelectedItem().toString(), 
						cbAnos.getSelectedItem().toString());
		
		if(lista.isEmpty()) {
			JOptionPane.showMessageDialog(this, 
					"Não há despesas cadastradas para o período selecionado", 
					"Aviso", JOptionPane.INFORMATION_MESSAGE);
		} else {
			String colunaNomes[] = {"Data", "Descrição", "Categoria", "Valor"};
			String dados[][] = new String[lista.size()][4];
			//stream que itera sobre a matriz dos dados e popula a tabela
			IntStream.range(0, lista.size())
				.forEach(e -> IntStream.range(0, 4)
					.forEach(f -> populaDadosTabela(lista, dados, e, f)));
			
						
			tabela = new JTable(dados, colunaNomes);
			painel.setLayout(new BorderLayout());
			painel.add(tabela.getTableHeader(), BorderLayout.PAGE_START);
			painel.add(tabela, BorderLayout.CENTER);
			painel.setVisible(true);
			
			NumberFormat nf = NumberFormat.getCurrencyInstance();
			
			double somaRecebimentos = lista.stream()
				.filter(e -> e.getCategoria().contentEquals("Salário") 
						|| e.getCategoria().contentEquals("Outros recebimentos"))
				.reduce(0.0, (a, b) -> a + b.getValor(), Double::sum);
			
			double somaGastos = lista.stream()
					.filter(e -> ! e.getCategoria().contentEquals("Salário"))
					.filter(e -> ! e.getCategoria().contentEquals("Outros recebimentos"))
					.reduce(0.0, (a, b) -> a + b.getValor(), Double::sum);
			
			lbSomaRecebimentos.setText(nf.format(somaRecebimentos));
			lbSomaGastos.setText(nf.format(somaGastos));
			lbRecebimentos.setVisible(true);
			lbGastos.setVisible(true);
		}
	}
	
	private void populaDadosTabela(List<Orcamento> lista, String dados[][], int i, int j) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		Collections.sort(lista, Orcamento.getComparatorData());
		if(j == 0) listaIds.add(lista.get(i).getId());
		
		dados[i][j] = (j == 0) ? sdf.format(lista.get(i).getData()) :
			(j == 1) ? lista.get(i).getDescricao() :
				(j == 2) ? lista.get(i).getCategoria() :
					nf.format(lista.get(i).getValor());
	}
	
	private void cadastrarAction() {	
		SwingUtilities.invokeLater(() -> new OrcamentoNovoGUI().setVisible(true));
	}
	
	private void atualizarAction() {
		Optional<JTable> tabelaOpcional = Optional.ofNullable(tabela);
		if(tabelaOpcional.isPresent()) {
			if(tabela.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "Selecione uma linha da tabela", 
						"Erro", JOptionPane.ERROR_MESSAGE);
			} else {
				int row = tabela.getSelectedRow();
				int id = listaIds.get(row);
				String data = tabela.getModel().getValueAt(row, 0).toString();
				String descricao = tabela.getModel().getValueAt(row, 1).toString();
				String categoria = tabela.getModel().getValueAt(row, 2).toString();
				String valor = tabela.getModel().getValueAt(row, 3).toString();
				SwingUtilities.invokeLater(() -> 
					new OrcamentoAtualizacaoGUI(id, data, descricao, categoria, valor).setVisible(true));
			}
		}
	}
	
	private void excluirAction() {
		Optional<JTable> tabelaOpcional = Optional.ofNullable(tabela);
		if(tabelaOpcional.isPresent()) {
			if(tabela.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "Selecione uma linha da tabela", 
						"Erro", JOptionPane.ERROR_MESSAGE);
			} else {
				int row = tabela.getSelectedRow();
				int id = listaIds.get(row);
				String data = tabela.getModel().getValueAt(row, 0).toString();
				String descricao = tabela.getModel().getValueAt(row, 1).toString();
				String categoria = tabela.getModel().getValueAt(row, 2).toString();
				String valor = tabela.getModel().getValueAt(row, 3).toString();
				int resposta = JOptionPane.showConfirmDialog(this, 
						"Deseja excluir a linha com as seguintes informações? \n"
						+ "Data: "+ data + "\n"
						+ "Descrição: "+ descricao + "\n"
						+"Categoria: "+ categoria + "\n"
						+"Valor: "+ valor + "\n", 
						"Informação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(resposta == 0) {
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					valor = valor.substring(3);
					valor = valor.replace(',', '.');
					double val = Double.parseDouble(valor);
					try {
						OrcamentoController oc  = new OrcamentoController();
						if(oc.exclui(id, sdf.parse(data), descricao, categoria, val)) {
							JOptionPane.showMessageDialog(this, "Exclusão feita com sucesso", "Exclusão", JOptionPane.INFORMATION_MESSAGE);
							pesquisarAction();
						} else {
							JOptionPane.showMessageDialog(this, "A exclusão não pode ser feita", "Exclusão", JOptionPane.ERROR_MESSAGE);
						}
							
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new OrcamentoPrincipalGUI().setVisible(true));
	}
}