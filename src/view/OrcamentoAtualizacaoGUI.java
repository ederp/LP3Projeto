package view;

import java.awt.Color;
import java.awt.Container;
import java.util.Date;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import controller.OrcamentoController;

@SuppressWarnings("serial")
public class OrcamentoAtualizacaoGUI extends JDialog{
	private JDatePickerImpl datePicker;
	private JLabel lbData, lbDescricao, lbCategoria, lbValor;
	private JTextField tfDescricao, tfValor;
	private JComboBox<String> cbCategoria;
	private static final String[] valCategoria = {"Salário", "Alimentação", "Casa", "Serviços", "Despesas pessoais", "Contas a pagar", "Impostos", 
			"Saúde", "Lazer", "Transporte", "Educação", "Outras despesas", "Outros recebimentos"};
	private JButton btAtualizar;
	private Container cp;
	private int id;
	
	public OrcamentoAtualizacaoGUI(int id, String data, String descricao, String categoria, String valor) {
		//instanciando os componentes de interface
		lbData = new JLabel("Data");
		lbDescricao = new JLabel("Descrição");
		tfDescricao = new JTextField(); 
		lbCategoria = new JLabel("Categoria");
		cbCategoria = new JComboBox<String>(valCategoria);
		lbValor = new JLabel("Valor");
		tfValor = new JTextField();
		btAtualizar = new JButton("Atualizar");
		this.id = id;

		//configurando os componentes
		setTitle("Atualização de Orçamento Mensal");
		setSize(400, 300);
		setLocationRelativeTo(null);
		cp = getContentPane();
		cp.setLayout(null);
		cp.setBackground(new Color(225, 255, 255));
		
		//instanciando os componentes do DatePicker
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Hoje");
		p.put("text.month", "Mês");
		p.put("text.year", "Ano");
		//adicionando a data da tabela no date picker
		int ano = Integer.parseInt(data.substring(6, 10));
		int mes = Integer.parseInt(data.substring(3, 5));
		int dia = Integer.parseInt(data.substring(0, 2));
		model.setDate(ano, (mes - 1), dia);
		model.setSelected(true);
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

		//definindo o posicionamento dos componentes
		lbData.setBounds(30, 30, 150, 25);
		datePicker.setBounds(100, 30, 150, 25);
		lbDescricao.setBounds(30, 60, 100, 25);
		tfDescricao.setBounds(100, 60, 150, 25);
		lbCategoria.setBounds(30, 90, 100, 25);
		cbCategoria.setBounds(100, 90, 150, 25);
		lbValor.setBounds(30, 120, 100, 25);
		tfValor.setBounds(100, 120, 100, 25);
		btAtualizar.setBounds(150, 200, 100, 25);
		
		//adicionando os demais dados da tabela nos labels
		tfDescricao.setText(descricao);
		cbCategoria.getModel().setSelectedItem(categoria);
		valor = valor.substring(3);
		valor = valor.replace(',', '.');
		tfValor.setText(valor);

		//adicionando os componentes ao container
		cp.add(lbData);
		cp.add(datePicker);
		cp.add(lbDescricao);
		cp.add(tfDescricao);
		cp.add(lbCategoria);
		cp.add(cbCategoria);
		cp.add(lbValor);
		cp.add(tfValor);
		cp.add(btAtualizar);

		btAtualizar.addActionListener(e -> atualizarAction());
	}
	
	private void atualizarAction() {
		//chamar o método do controller pra pegar os dados passados na gui e jogar no arquivo
		if(datePicker.getModel().getValue() == null
				|| tfDescricao.getText().isEmpty() 
				|| tfValor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Existem campos a serem preenchidos", "Aviso", JOptionPane.ERROR_MESSAGE);
		} else {
			OrcamentoController oc = new OrcamentoController();
			Date data = (Date) datePicker.getModel().getValue();
			String desc = tfDescricao.getText();
			String categoria = cbCategoria.getSelectedItem().toString();
			double valor = Double.parseDouble(tfValor.getText());
			oc.atualiza(this.id, data, desc, categoria, valor);
			JOptionPane.showMessageDialog(this, "Atualização realizada com sucesso", "Atualização", JOptionPane.INFORMATION_MESSAGE);
			this.setVisible(false);
		}
	}
}
