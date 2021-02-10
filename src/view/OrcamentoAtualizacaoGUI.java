package view;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class OrcamentoAtualizacaoGUI extends JDialog{
	private JLabel lbData, lbDescricao, lbCategoria, lbValor;
	private JTextField tfData, tfDescricao, tfCategoria, tfValor;
	private JButton btCadastrar;
	private Container cp;
	public OrcamentoAtualizacaoGUI() {
		//instanciando os componentes de interface
		lbData = new JLabel("Data (do tipo dd/mm/aaaa)");
		tfData = new JTextField(); //adicionar ao textfield os dados da tabela
		lbDescricao = new JLabel("Descrição");
		tfDescricao = new JTextField(); //adicionar ao textfield os dados da tabela
		lbCategoria = new JLabel("Categoria");
		tfCategoria = new JTextField(); //adicionar ao textfield os dados da tabela
		lbValor = new JLabel("Valor");
		tfValor = new JTextField(); //adicionar ao textfield os dados da tabela
		btCadastrar = new JButton("Cadastrar");

		//configurando os componentes
		setTitle("Atualização de Orçamento Mensal");
		setSize(400, 300);
		setLocationRelativeTo(null);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cp = getContentPane();
		cp.setLayout(null);
		cp.setBackground(new Color(225, 255, 255));

		//definindo o posicionamento dos componentes
		lbData.setBounds(30, 30, 150, 25);
		tfData.setBounds(200, 30, 100, 25);
		lbDescricao.setBounds(30, 60, 100, 25);
		tfDescricao.setBounds(100, 60, 150, 25);
		lbCategoria.setBounds(30, 90, 100, 25);
		tfCategoria.setBounds(100, 90, 150, 25);
		lbValor.setBounds(30, 120, 100, 25);
		tfValor.setBounds(100, 120, 100, 25);
		btCadastrar.setBounds(150, 200, 100, 25);

		//adicionando os componentes ao container
		cp.add(lbData);
		cp.add(tfData);
		cp.add(lbDescricao);
		cp.add(tfDescricao);
		cp.add(lbCategoria);
		cp.add(tfCategoria);
		cp.add(lbValor);
		cp.add(tfValor);
		cp.add(btCadastrar);
	}
	
	
}
