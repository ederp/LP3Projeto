package view;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class OrcamentoGUI extends JFrame{
	private JTable tabela;
	//private String colunaNomes[] = {"Data", "Descrição", "Categoria", "Valor"};
	//private String dados[][] = {}; //recebe os dados do controller/arquivo
	private JLabel lbMes;
	private JLabel lbAno;
	private JTextField tfAno;
	private JComboBox<String> cbMeses;
	private static final String valMeses[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
	private JButton btPesquisar;
	private Container cp;
	
	public OrcamentoGUI() {
		//instanciando os componentes de interface
		//tabela = new JTable(dados, colunaNomes); 
		lbMes = new JLabel("Selecione o mês ");
		cbMeses = new JComboBox<String>(valMeses);
		lbAno = new JLabel("Digite o ano (no formato AAAA)");
		tfAno = new JTextField();
		btPesquisar = new JButton("Pesquisar");
		
		//configurando os componentes
		setTitle("Programa de Orçamento Mensal");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//JScrollPane sp = new JScrollPane(tabela);
		//tabela.setVisible(false);
		cp = getContentPane();
		cp.setLayout(null);
		cp.setBackground(new Color(225, 255, 255));
		
		//definindo o posicionamento dos componentes
	    //tabela.setBounds(30, 40, 600, 300);
	    lbMes.setBounds(30, 350, 100, 25);
	    cbMeses.setBounds(150, 350, 100, 25);
	    lbAno.setBounds(300, 350, 200, 25);
	    tfAno.setBounds(500, 350, 50, 25);
	    btPesquisar.setBounds(600, 350, 100, 25);
	    
	    //adicionando os componentes ao container
	    //cp.add(sp);
	    cp.add(lbMes);
	    cp.add(cbMeses); 
	    cp.add(lbAno);
	    cp.add(tfAno);
	    cp.add(btPesquisar);
	}


	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new OrcamentoGUI().setVisible(true);
			}
		});
	}
}
