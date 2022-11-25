package ui.gui;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import sistema.classes.TipoServico;
import sistema.interfaces.ILaboratorio;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class GUICreateUpdateTipoServicos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfNome;
	private JTextField tfPreco;
	private ILaboratorio lab;
	private long cod;

	public GUICreateUpdateTipoServicos(final int modo, ILaboratorio lab) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/icon.png")));
		this.lab = lab;
		getContentPane().setBackground(SystemColor.window);
		setBackground(SystemColor.window);
		setModal(true);
		if (modo != -1)
			setTitle("Alterar tipo de serviço");
		else
			setTitle("Cadastrar tipo de serviço");
		setBounds(100, 100, 583, 239);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 704, 412);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNome.setBounds(10, 10, 116, 21);
		contentPanel.add(lblNome);

		tfNome = new JTextField();
		tfNome.setBounds(10, 41, 261, 23);
		contentPanel.add(tfNome);
		tfNome.setColumns(10);
		{
			tfPreco = new JTextField();
			tfPreco.setColumns(10);
			tfPreco.setBounds(10, 107, 131, 23);
			tfPreco.addKeyListener(new KeyAdapter() {
			@Override
				public void keyTyped(KeyEvent ev) {
				// código do evento:
					String caracteres = "0987654321,.";
					if(!(caracteres.contains(ev.getKeyChar()+""))){
						ev.consume();
					}
				}	
			});
			contentPanel.add(tfPreco);
		}
		{
			JLabel lblPreco = new JLabel("Pre\u00E7o:");
			lblPreco.setFont(new Font("Tahoma", Font.PLAIN, 17));
			lblPreco.setBounds(10, 76, 47, 21);
			contentPanel.add(lblPreco);
		}

		JLabel label = new JLabel("*");
		label.setForeground(Color.RED);
		label.setBounds(62, 10, 45, 20);
		contentPanel.add(label);

		JLabel label_1 = new JLabel("*");
		label_1.setForeground(Color.RED);
		label_1.setBounds(62, 74, 45, 23);
		contentPanel.add(label_1);

		JLabel lblObrigatrio = new JLabel("* - Obrigat\u00F3rio");
		lblObrigatrio.setForeground(Color.RED);
		lblObrigatrio.setBounds(10, 140, 86, 20);
		contentPanel.add(lblObrigatrio);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(-117, 170, 704, 31);
			contentPanel.add(buttonPane);
			buttonPane.setLayout(null);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (modo == -1) {
							adicionarTipo();
						} else {
							alterarFuncionario();
						}
					}
				});
				okButton.setBounds(493, 5, 79, 21);
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
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
				cancelButton.setBounds(582, 5, 97, 21);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		if (modo != -1) {
			ArrayList<TipoServico> tipos = lab.getArrayTipos();
			tfNome.setText(tipos.get(modo).getNome());
			tfPreco.setText(String.valueOf(tipos.get(modo).getPreco()));
			cod = tipos.get(modo).getCod();
		}
	}

	private boolean verificarCampos() {
		boolean test = true;
		String nome = tfNome.getText();
		String preco = tfPreco.getText();

		if (nome.equals(null) || nome.equals("") || nome.isEmpty()) {
			tfNome.setBackground(Color.PINK);
			test = false;
		} else {
			tfNome.setBackground(Color.WHITE);
		}
		double x = 0;
		try {
			if (preco.contains(","))
				x = NumberFormat.getInstance().parse(preco).doubleValue();
			else
				x = Double.parseDouble(preco);
			if (x >= 0)
				tfPreco.setBackground(Color.WHITE);
			else {
				tfPreco.setBackground(Color.PINK);
				test = false;
			}

		} catch (Exception e) {
			tfPreco.setBackground(Color.PINK);
			test = false;
		}
		return test;
	}

	private void alterarFuncionario() {
		String nome = tfNome.getText();
		String preco = tfPreco.getText();
		 if (!(verificarCampos())) {
			 JOptionPane.showMessageDialog(this, "Preencha todos os campos corretamente",
					 "Não foi possivel completar a alteração", JOptionPane.ERROR_MESSAGE);
		 } else {
			 boolean finalTest = true;
			 try {
				 double x = 0;
				 if(preco.contains(","))
						x = NumberFormat.getInstance().parse(preco).doubleValue();
					else
						x = Double.parseDouble(preco);
				 if(!(lab.alterarTipoServico(cod, nome, x, 100)))
					 finalTest = false;
				 if (finalTest) {
					 JOptionPane.showMessageDialog(this, "Tipo de serviço alterado com sucesso",
							 "Alteração de tipo de serviço", JOptionPane.INFORMATION_MESSAGE);
					 dispose();
				 }
				 else {
					 JOptionPane.showMessageDialog(this, "Ocorreu um erro no sistema de alteração,tente novamente", "Não foi possivel completar a alteração",
							 JOptionPane.ERROR_MESSAGE);
				 }
		
			 } catch (Exception e) {
				 JOptionPane.showMessageDialog(this, "Ocorreu um erro com o campo do preço,tente novamente", "Não foi possivel completar a alteração",
						 JOptionPane.ERROR_MESSAGE);
				 finalTest = false;
		 }
		 }

	}

	private void adicionarTipo() {
		String nome = tfNome.getText();
		String preco = tfPreco.getText();
		if (!(verificarCampos())) {
			JOptionPane.showMessageDialog(this, "Preencha todos os campos corretamente",
					"Não foi possivel completar o cadastro", JOptionPane.ERROR_MESSAGE);
		} else {
			boolean finalTest = true;
			try {
				double x = 0;
				if (preco.contains(","))
					x = NumberFormat.getInstance().parse(preco).doubleValue();
				else
					x = Double.parseDouble(preco);
				if (!(lab.newTipoServico(nome, x)))
					finalTest = false;
				if (finalTest) {
					JOptionPane.showMessageDialog(this, "Tipo de serviço cadastrado com sucesso",
							"Cadastro de tipo de serviço", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				} else {
					JOptionPane.showMessageDialog(this, "Ocorreu um erro no sistema de cadastro, tente novamente",
							"Não foi possivel completar o cadastro", JOptionPane.ERROR_MESSAGE);
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Ocorreu um erro com o campo do preço, tente novamente",
						"Não foi possivel completar o cadastro", JOptionPane.ERROR_MESSAGE);
				finalTest = false;
			}
		}

	}

	private static boolean isCPF(String CPF) {
		// considera-se erro CPF's formados por uma sequencia de numeros iguais
		if (CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222")
				|| CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555")
				|| CPF.equals("66666666666") || CPF.equals("77777777777") || CPF.equals("88888888888")
				|| CPF.equals("99999999999") || (CPF.length() != 11))
			return (false);

		char dig10, dig11;
		int sm, i, r, num, peso;

		// "try" - protege o codigo para eventuais erros de conversao de tipo (int)
		try {
			// Calculo do 1o. Digito Verificador
			sm = 0;
			peso = 10;
			for (i = 0; i < 9; i++) {
				// converte o i-esimo caractere do CPF em um numero:
				// por exemplo, transforma o caractere '0' no inteiro 0
				// (48 eh a posicao de '0' na tabela ASCII)
				num = (int) (CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig10 = '0';
			else
				dig10 = (char) (r + 48); // converte no respectivo caractere numerico

			// Calculo do 2o. Digito Verificador
			sm = 0;
			peso = 11;
			for (i = 0; i < 10; i++) {
				num = (int) (CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig11 = '0';
			else
				dig11 = (char) (r + 48);

			// Verifica se os digitos calculados conferem com os digitos informados.
			if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
				return (true);
			else
				return (false);
		} catch (InputMismatchException erro) {
			return (false);
		}
	}
}
