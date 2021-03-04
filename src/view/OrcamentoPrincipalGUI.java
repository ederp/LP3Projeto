package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
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
	private JLabel lbMes;
	private JLabel lbAno;
	private JComboBox<String> cbMeses;
	private JComboBox<Integer> cbAnos;
	private static Integer anoAtual = LocalDateTime.now().getYear();
	private static final String valMeses[] = 
		{"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", 
				"Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
	private JButton btPesquisar, btCriarNovo, btAtualizar, btExcluir;
	private Container cp;
	
	public OrcamentoPrincipalGUI() {
		//instanciando os componentes de interface
		painel = new JPanel();
		lbMes = new JLabel("Selecione o mês ");
		cbMeses = new JComboBox<String>(valMeses);
		lbAno = new JLabel("Selecione o ano ");
		cbAnos = new JComboBox<Integer>();
		IntStream.rangeClosed(2010, anoAtual).forEach(cbAnos::addItem);
		btPesquisar = new JButton("Pesquisar");
		btCriarNovo = new JButton("Criar Novo");
		btAtualizar = new JButton("Atualizar");
		btExcluir = new JButton("Excluir");
		
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
		
		//definindo o posicionamento dos componentes
	    painel.setBounds(30, 30, 700, 200);
	    lbMes.setBounds(30, 350, 100, 25);
	    cbMeses.setBounds(150, 350, 100, 25);
	    lbAno.setBounds(300, 350, 200, 25);
	    cbAnos.setBounds(400, 350, 60, 25);
	    btPesquisar.setBounds(600, 350, 100, 25);
	    btCriarNovo.setBounds(600, 390, 100, 25);
	    btAtualizar.setBounds(600, 430, 100, 25);
	    btExcluir.setBounds(600, 470, 100, 25);
	    
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
	    
	    btPesquisar.addActionListener(e -> pesquisarAction());
	    btCriarNovo.addActionListener(e -> cadastrarAction());
	    btAtualizar.addActionListener(e -> atualizarAction());
	}
	
	private void pesquisarAction() {
		//pegar os dados do arquivo e mostrar a tabela
		List<Orcamento> lista = new OrcamentoController()
				.pesquisa(cbMeses.getSelectedItem().toString(), cbAnos.getSelectedItem().toString());
		if(lista.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Não há despesas cadastradas para o período selecionado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
		} else {
			String colunaNomes[] = {"Data", "Descrição", "Categoria", "Valor"};
			String dados[][] = new String[lista.size()][4];
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			NumberFormat nf = NumberFormat.getCurrencyInstance();
			
			for(int i = 0; i < lista.size(); i++) {
				for(int j = 0; j < 4; j++) {
					dados[i][j] = (j == 0) ? sdf.format(lista.get(i).getData()) :
						(j == 1) ? lista.get(i).getDescricao() :
							(j == 2) ? lista.get(i).getCategoria() :
								nf.format(lista.get(i).getValor());
				}
			}
			
			tabela = new JTable(dados, colunaNomes);
			painel.setLayout(new BorderLayout());
			painel.add(tabela.getTableHeader(), BorderLayout.PAGE_START);
			painel.add(tabela, BorderLayout.CENTER);
			painel.setVisible(true);
		}
	}
	
	private void cadastrarAction() {	
		SwingUtilities.invokeLater(() -> new OrcamentoNovoGUI().setVisible(true));
	}
	
	private void atualizarAction() {
		SwingUtilities.invokeLater(() -> new OrcamentoAtualizacaoGUI().setVisible(true));
	}
	
	private void excluirAction() {
		//mostrar um modal perguntando se deseja excluir o dado selecionado
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new OrcamentoPrincipalGUI().setVisible(true));
	}
}
