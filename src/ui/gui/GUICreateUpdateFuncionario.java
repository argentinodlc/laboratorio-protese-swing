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

import sistema.classes.Funcionario;
import sistema.interfaces.ILaboratorio;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class GUICreateUpdateFuncionario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfNome;
	private JTextField tfCpf;
	private JTextField tfComissao;
	private ILaboratorio lab;
	private long cod;
	private int modo;
	private JTextField tfSalario;

	public GUICreateUpdateFuncionario(int m, ILaboratorio lab) {
		setResizable(false);
		modo = m;
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/icon.png")));
		this.lab = lab;
		getContentPane().setBackground(SystemColor.window);
		setBackground(SystemColor.window);
		setModal(true);
		if (modo != -1) 
			setTitle("Alterar Funcionário");
		else
			setTitle("Cadastrar Funcionário");
		setBounds(100, 100, 581, 368);
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
		tfNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ev) {
			// código do evento:
				String caracteres = "0987654321";
				if((caracteres.contains(ev.getKeyChar()+""))){
					ev.consume();
				}
			}	
		});
		{
			tfCpf = new JTextField();
			tfCpf.setColumns(10);
			tfCpf.setBounds(10, 107, 261, 23);
			tfCpf.addKeyListener(new KeyAdapter() {
			@Override
				public void keyTyped(KeyEvent ev) {
				// código do evento:
					String caracteres = "0987654321";
					if(!(caracteres.contains(ev.getKeyChar()+""))){
						ev.consume();
					}
				}	
			});
			contentPanel.add(tfCpf);
		}
		{
			JLabel lblCpf = new JLabel("CPF:");
			lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 17));
			lblCpf.setBounds(10, 76, 261, 21);
			contentPanel.add(lblCpf);
		}
		{
			tfComissao = new JTextField();
			tfComissao.setColumns(10);
			tfComissao.setBounds(10, 173, 116, 23);
			tfComissao.addKeyListener(new KeyAdapter() {
			@Override
				public void keyTyped(KeyEvent ev) {
				// código do evento:
					String caracteres = "0987654321,.";
					if(!(caracteres.contains(ev.getKeyChar()+""))){
						ev.consume();
					}
				}	
			});
			contentPanel.add(tfComissao);
		}
		{
			JLabel lbComissao = new JLabel("Comiss\u00E3o: ");
			lbComissao.setFont(new Font("Tahoma", Font.PLAIN, 17));
			lbComissao.setBounds(10, 142, 261, 21);
			contentPanel.add(lbComissao);
		}
		
		JLabel label = new JLabel("*");
		label.setForeground(Color.RED);
		label.setBounds(62, 10, 45, 20);
		contentPanel.add(label);
		
		JLabel label_1 = new JLabel("*");
		label_1.setForeground(Color.RED);
		label_1.setBounds(46, 74, 45, 23);
		contentPanel.add(label_1);
		
		JLabel label_2 = new JLabel("*");
		label_2.setForeground(Color.RED);
		label_2.setBounds(90, 142, 45, 20);
		contentPanel.add(label_2);
		
		JLabel lblObrigatrio = new JLabel("* - Obrigat\u00F3rio");
		lblObrigatrio.setForeground(Color.RED);
		lblObrigatrio.setBounds(10, 264, 86, 20);
		contentPanel.add(lblObrigatrio);
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(-121, 294, 704, 31);
			contentPanel.add(buttonPane);
			buttonPane.setLayout(null);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (modo == -1) {
							adicionarFuncionario();
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
		
		tfSalario = new JTextField();
		tfSalario.setColumns(10);
		tfSalario.setBounds(10, 237, 116, 23);
		tfSalario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ev) {
			// código do evento:
				String caracteres = "0987654321,.";
				if(!(caracteres.contains(ev.getKeyChar()+""))){
					ev.consume();
				}
			}	
		});
		contentPanel.add(tfSalario);
		
		JLabel lblSalrio = new JLabel("Sal\u00E1rio:");
		lblSalrio.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSalrio.setBounds(10, 206, 261, 21);
		contentPanel.add(lblSalrio);
		
		JLabel label_4 = new JLabel("*");
		label_4.setForeground(Color.RED);
		label_4.setBounds(71, 206, 45, 20);
		contentPanel.add(label_4);
		if (modo != -1) {
			ArrayList<Funcionario> funcionarios =  lab.getArrayFunc();	
			tfNome.setText(funcionarios.get(modo).getNome());
			tfCpf.setText(String.valueOf(funcionarios.get(modo).getCpf()));
			tfComissao.setText(String.valueOf(funcionarios.get(modo).getComissao()));
			cod = funcionarios.get(modo).getCod();
			DecimalFormat df = new DecimalFormat("0.##");
			tfSalario.setText(df.format(funcionarios.get(modo).getSalario()));
		}
	}
	
	private boolean verificarCampos() {
		boolean test = true;
		String nome = tfNome.getText();
		String comissao = tfComissao.getText();
		String cpf = tfCpf.getText();
		String salario = tfSalario.getText();
		
		if (nome.equals(null) || nome.equals("") || nome.isEmpty()) {
			tfNome.setBackground(Color.PINK);
			test = false;
		} else {
			tfNome.setBackground(Color.WHITE);
		}
		try {
			if (comissao.equals(null) || comissao.equals("") || comissao.isEmpty() || Double.parseDouble(comissao) < 0) {
				tfComissao.setBackground(Color.PINK);
				test = false;
			} else {
				tfComissao.setBackground(Color.WHITE);
			}
			if (salario.equals(null) || salario.equals("") || salario.isEmpty() || Double.parseDouble(salario) < 0) {
				tfSalario.setBackground(Color.PINK);
				test = false;
			} else {
				tfSalario.setBackground(Color.WHITE);
			}
		} catch (Exception e) {
			test = false;
		}
		if (!(isCPF(cpf))) {
			tfCpf.setBackground(Color.PINK);
			test = false;
		} else {
			tfCpf.setBackground(Color.WHITE);
		}
		return test;
	}
	
	private void alterarFuncionario() {
		String nome = tfNome.getText();
		String com = tfComissao.getText();
		String cpf = tfCpf.getText();
		if (!(verificarCampos())) {
			JOptionPane.showMessageDialog(this, "Preencha todos os campos corretamente", "Não foi possivel completar a alteração", JOptionPane.ERROR_MESSAGE);
		} else {
			boolean finalTest = true;
			try {
				double salario = Double.parseDouble(tfSalario.getText());
				double comissao = Double.parseDouble(com);
				if(!(lab.alterFuncionario(cod, nome, cpf, comissao, salario)))
					finalTest = false;
				if (finalTest) {
					JOptionPane.showMessageDialog(this, "Funcionário alterado com sucesso", "Alteração de funcionário", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(this, "Ocorreu um erro no sistema de alteração, tente novamente", "Não foi possivel completar a alteração", JOptionPane.ERROR_MESSAGE);
				}
					
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Ocorreu um erro com o campo de comissão, tente novamente", "Não foi possivel completar a alteração", JOptionPane.ERROR_MESSAGE);
				finalTest = false;
			}
		}
		
	}

	private void adicionarFuncionario() {
		String nome = tfNome.getText();
		String com = tfComissao.getText();
		String cpf = tfCpf.getText();
		if (!(verificarCampos())) {
			JOptionPane.showMessageDialog(this, "Preencha todos os campos corretamente", "Não foi possivel completar o cadastro", JOptionPane.ERROR_MESSAGE);
		} else {
			boolean finalTest = true;
			try {
				double salario = Double.parseDouble(tfSalario.getText());
				double comissao = Double.parseDouble(com);
				if(!(lab.newFuncionario(nome, cpf, comissao, salario)))
					finalTest = false;
				if (finalTest) {
					JOptionPane.showMessageDialog(this, "Funcionário cadastrado com sucesso", "Cadastro de funcionário", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(this, "Ocorreu um erro no sistema de cadastro, tente novamente", "Não foi possivel completar o cadastro", JOptionPane.ERROR_MESSAGE);
				}
					
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Ocorreu um erro com o campo de comissão, tente novamente", "Não foi possivel completar o cadastro", JOptionPane.ERROR_MESSAGE);
				finalTest = false;
			}
		}
		
	}
	
	private static boolean isCPF(String CPF) {
		// considera-se erro CPF's formados por uma sequencia de numeros iguais
		    if (CPF.equals("00000000000") || CPF.equals("11111111111") ||
		        CPF.equals("22222222222") || CPF.equals("33333333333") ||
		        CPF.equals("44444444444") || CPF.equals("55555555555") ||
		        CPF.equals("66666666666") || CPF.equals("77777777777") ||
		        CPF.equals("88888888888") || CPF.equals("99999999999") ||
		       (CPF.length() != 11))
		       return(false);

		    char dig10, dig11;
		    int sm, i, r, num, peso;

		// "try" - protege o codigo para eventuais erros de conversao de tipo (int)
		    try {
		// Calculo do 1o. Digito Verificador
		      sm = 0;
		      peso = 10;
		      for (i=0; i<9; i++) {              
		// converte o i-esimo caractere do CPF em um numero:
		// por exemplo, transforma o caractere '0' no inteiro 0         
		// (48 eh a posicao de '0' na tabela ASCII)         
		        num = (int)(CPF.charAt(i) - 48); 
		        sm = sm + (num * peso);
		        peso = peso - 1;
		      }

		      r = 11 - (sm % 11);
		      if ((r == 10) || (r == 11))
		         dig10 = '0';
		      else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

		// Calculo do 2o. Digito Verificador
		      sm = 0;
		      peso = 11;
		      for(i=0; i<10; i++) {
		        num = (int)(CPF.charAt(i) - 48);
		        sm = sm + (num * peso);
		        peso = peso - 1;
		      }

		      r = 11 - (sm % 11);
		      if ((r == 10) || (r == 11))
		         dig11 = '0';
		      else dig11 = (char)(r + 48);

		// Verifica se os digitos calculados conferem com os digitos informados.
		      if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
		         return(true);
		      else return(false);
		    } catch (InputMismatchException erro) {
		        return(false);
		    }
		  }
}
