package ui.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sistema.interfaces.ILaboratorio;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Toolkit;
import java.text.NumberFormat;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

public class GUICreateUpdateDespesa extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfNome;
	private JTextField tfValor;
	private ILaboratorio lab;
	private long cod;
	private JDateChooser dt;

	public GUICreateUpdateDespesa(ILaboratorio l) {
		lab = l;
		setTitle("Registrar despesa");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/icon.png")));
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 448, 248);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("*");
		label.setForeground(Color.RED);
		label.setBounds(57, 7, 45, 13);
		contentPanel.add(label);
		
		JLabel lbNome = new JLabel("Nome:");
		lbNome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbNome.setBounds(10, 10, 175, 13);
		contentPanel.add(lbNome);
		
		tfNome = new JTextField();
		tfNome.setBounds(10, 25, 175, 19);
		contentPanel.add(tfNome);
		tfNome.setColumns(10);
		
		JLabel lblValorMdio = new JLabel("Valor:");
		lblValorMdio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblValorMdio.setBounds(10, 55, 175, 13);
		contentPanel.add(lblValorMdio);
		
		tfValor = new JTextField();
		tfValor.setColumns(10);
		tfValor.setBounds(10, 70, 88, 19);
		tfValor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ev) {
			// c�digo do evento:
				String caracteres = "0987654321,.";
				if(!(caracteres.contains(ev.getKeyChar()+""))){
					ev.consume();
				}
			}	
		});
		contentPanel.add(tfValor);
		
		dt = new JDateChooser();
		dt.setBounds(10, 116, 112, 19);
		dt.setDateFormatString("dd/MM/yyyy");
		contentPanel.add(dt);
		
		JLabel lblData = new JLabel("Data:");
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblData.setBounds(10, 99, 175, 13);
		contentPanel.add(lblData);
		
		JLabel label_1 = new JLabel("*");
		label_1.setForeground(Color.RED);
		label_1.setBounds(50, 52, 45, 13);
		contentPanel.add(label_1);
		
		JLabel label_2 = new JLabel("*");
		label_2.setForeground(Color.RED);
		label_2.setBounds(47, 97, 45, 13);
		contentPanel.add(label_2);
		
		JLabel lblObrigatrio = new JLabel("* - Obrigat\u00F3rio");
		lblObrigatrio.setForeground(Color.RED);
		lblObrigatrio.setBounds(10, 155, 112, 13);
		contentPanel.add(lblObrigatrio);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						newDespesa();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private void newDespesa() {
		String nome = tfNome.getText();
		String valor = tfValor.getText();
		Date data = dt.getDate();
		if (verificaCampos(nome, valor)) {
			double valorMedio = 0;
			try {
				if (valor.contains(","))
					valorMedio = NumberFormat.getInstance().parse(valor).doubleValue();
				else
					valorMedio = Double.parseDouble(valor);
					if (lab.newDespesa(nome, data, valorMedio)) {
						JOptionPane.showMessageDialog(GUICreateUpdateDespesa.this, "Despesa registrada com sucesso",
								"Registrar despesa", JOptionPane.INFORMATION_MESSAGE);
						dispose();
					} else {
						JOptionPane.showMessageDialog(GUICreateUpdateDespesa.this,
								"Ocorreu algum problema com o sistema de cadastro",
								"N�o foi poss�vel completar a opera��o", JOptionPane.ERROR_MESSAGE);
					}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(GUICreateUpdateDespesa.this,
						"Ocorreu algum problema com o campo de valor", "N�o foi poss�vel realizar a opera��o",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(GUICreateUpdateDespesa.this,
					"Preencha todos os campos corretamente e tente novamente", "N�o foi poss�vel realizar a opera��o",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	private boolean verificaCampos(String nome, String valor) {
		boolean test = true;
		if (nome == null || nome.equals("")) {
			tfNome.setBackground(Color.PINK);
			test = false;
		} else {
			tfNome.setBackground(Color.WHITE);
		}
		double x = 0;
		try {
			if (valor.contains(","))
				x = NumberFormat.getInstance().parse(valor).doubleValue();
			else
				x = Double.parseDouble(valor);
			if (x > 0)
				tfValor.setBackground(Color.WHITE);
			else {
				tfValor.setBackground(Color.PINK);
				test = false;
			}
		} catch (Exception e) {
			tfValor.setBackground(Color.PINK);
			test = false;
		}
		
		if (dt.getDate()==null) {
			test = false;
		}
		
		return test;
	}
}
