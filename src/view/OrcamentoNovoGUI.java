package view;

import java.awt.Color;
import java.awt.Container;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

@SuppressWarnings("serial")
public class OrcamentoNovoGUI extends JDialog{
	private JLabel lbData, lbDescricao, lbCategoria, lbValor;
	private JTextField tfDescricao, tfValor;
	private JComboBox<String> cbCategoria;
	private static final String[] valCategoria = {"Salário", "Alimentação", "Casa", "Serviços", "Despesas pessoais", "Contas a pagar", "Impostos", 
			"Saúde", "Lazer", "Transporte", "Educação", "Outras despesas", "Outros recebimentos"};
	private JButton btCadastrar;
	private Container cp;

	public OrcamentoNovoGUI() {
		//instanciando os componentes de interface
		lbData = new JLabel("Data");
		lbDescricao = new JLabel("Descrição");
		tfDescricao = new JTextField();
		lbCategoria = new JLabel("Categoria");
		cbCategoria = new JComboBox<String>(valCategoria);
		lbValor = new JLabel("Valor");
		tfValor = new JTextField();
		btCadastrar = new JButton("Cadastrar");
		
		//instanciando os componentes do DatePicker
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Hoje");
		p.put("text.month", "Mês");
		p.put("text.year", "Ano");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		
		//configurando os componentes
		setTitle("Cadastro de Novo Orçamento Mensal");
		setSize(400, 300);
		setLocationRelativeTo(null);
		cp = getContentPane();
		cp.setLayout(null);
		cp.setBackground(new Color(225, 255, 255));
		
		//definindo o posicionamento dos componentes
		lbData.setBounds(30, 30, 150, 25);
		datePicker.setBounds(100, 30, 150, 25);
		lbDescricao.setBounds(30, 60, 100, 25);
		tfDescricao.setBounds(100, 60, 150, 25);
		lbCategoria.setBounds(30, 90, 100, 25);
		cbCategoria.setBounds(100, 90, 150, 25);
		lbValor.setBounds(30, 120, 100, 25);
		tfValor.setBounds(100, 120, 100, 25);
		btCadastrar.setBounds(150, 200, 100, 25);
		
		//adicionando os componentes ao container
		cp.add(lbData);
		cp.add(datePicker);
		cp.add(lbDescricao);
		cp.add(tfDescricao);
		cp.add(lbCategoria);
		cp.add(cbCategoria);
		cp.add(lbValor);
		cp.add(tfValor);
		cp.add(btCadastrar);
		
	}

}
