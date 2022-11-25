package ui.gui;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.InputMismatchException;

import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import sistema.classes.Clinica;
import sistema.interfaces.ILaboratorio;

import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

public class GUICreateUpdateClinica extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfNome;
	private JTextField tfCNPJ;
	private JTextField tfTelefone;
	private JTextField tfBairro;
	private JTextField tfNum;
	private JTextField tfLogra;
	private JTextField tfCidade;
	private JTextField tfComp;
	private JCheckBox chckbxComplemento;
	private ILaboratorio lab;
	private long cod;
	private JButton okButton;
	private JButton cancelButton;

	public GUICreateUpdateClinica(final int modo, ILaboratorio lab) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/icon.png")));
		this.lab = lab;
		setModal(true);
		if (modo == -1)
			setTitle("Cadastrar Cl\u00EDnica");
		else
			setTitle("Alterar Clínicia");
		setBounds(100, 100, 720, 480);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 704, 412);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNome.setBounds(10, 10, 261, 21);
		contentPanel.add(lblNome);
		
		tfNome = new JTextField();
		tfNome.setBounds(10, 41, 261, 19);
		contentPanel.add(tfNome);
		tfNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ev) {
			// código do evento:
				String caracteres = "0987654321";
				if(caracteres.contains(ev.getKeyChar()+"")){
					ev.consume();
				}
			}	
		});
		tfNome.setColumns(10);
		{
			tfCNPJ = new JTextField();
			tfCNPJ.setColumns(10);
			tfCNPJ.setBounds(10, 107, 261, 19);
			tfCNPJ.addKeyListener(new KeyAdapter() {
			@Override
				public void keyTyped(KeyEvent ev) {
				// código do evento:
					String caracteres = "0987654321";
					if(!(caracteres.contains(ev.getKeyChar()+""))){
						ev.consume();
					}
				}	
			});
			contentPanel.add(tfCNPJ);
		}
		{
			JLabel lblCNPJ = new JLabel("CNPJ:");
			lblCNPJ.setFont(new Font("Tahoma", Font.PLAIN, 17));
			lblCNPJ.setBounds(10, 76, 261, 21);
			contentPanel.add(lblCNPJ);
		}
		{
			tfTelefone = new JTextField();
			tfTelefone.setColumns(10);
			tfTelefone.setBounds(10, 173, 261, 19);
			tfTelefone.addKeyListener(new KeyAdapter() {
			@Override
				public void keyTyped(KeyEvent ev) {
				// código do evento:
					String caracteres = "0987654321";
					if(!(caracteres.contains(ev.getKeyChar()+""))){
						ev.consume();
					}
				}	
			});
			contentPanel.add(tfTelefone);
		}
		{
			JLabel lblTelefone = new JLabel("Telefone:");
			lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 17));
			lblTelefone.setBounds(10, 142, 261, 21);
			contentPanel.add(lblTelefone);
		}
		
		JPanel enderecoPanel = new JPanel();
		enderecoPanel.setBorder(new TitledBorder(null, "Endere\u00E7o", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		enderecoPanel.setBounds(305, 10, 389, 392);
		contentPanel.add(enderecoPanel);
		enderecoPanel.setLayout(null);
		{
			tfBairro = new JTextField();
			tfBairro.setColumns(10);
			tfBairro.setBounds(10, 187, 261, 19);
			tfBairro.addKeyListener(new KeyAdapter() {
			@Override
				public void keyTyped(KeyEvent ev) {
				// código do evento:
					String caracteres = "0987654321";
					if(caracteres.contains(ev.getKeyChar()+"")){
						ev.consume();
					}
				}	
			});
			enderecoPanel.add(tfBairro);
		}
		{
			JLabel lblBairro = new JLabel("Bairro:");
			lblBairro.setFont(new Font("Tahoma", Font.PLAIN, 17));
			lblBairro.setBounds(10, 156, 261, 21);
			enderecoPanel.add(lblBairro);
		}
		{
			tfNum = new JTextField();
			tfNum.setColumns(10);
			tfNum.setBounds(10, 121, 69, 19);
			tfNum.addKeyListener(new KeyAdapter() {
			@Override
				public void keyTyped(KeyEvent ev) {
				// código do evento:
					String caracteres = "0987654321";
					if(!(caracteres.contains(ev.getKeyChar()+""))){
						ev.consume();
					}
				}	
			});
			enderecoPanel.add(tfNum);
		}
		{
			JLabel lblNum = new JLabel("N\u00FAmero:");
			lblNum.setFont(new Font("Tahoma", Font.PLAIN, 17));
			lblNum.setBounds(10, 90, 261, 21);
			enderecoPanel.add(lblNum);
		}
		{
			tfLogra = new JTextField();
			tfLogra.setColumns(10);
			tfLogra.setBounds(10, 55, 261, 19);
			tfLogra.addKeyListener(new KeyAdapter() {
			@Override
				public void keyTyped(KeyEvent ev) {
				// código do evento:
					String caracteres = "0987654321";
					if((caracteres.contains(ev.getKeyChar()+""))){
						ev.consume();
					}
				}	
			});
			enderecoPanel.add(tfLogra);
		}
		{
			JLabel lblLogra = new JLabel("Logradouro:");
			lblLogra.setFont(new Font("Tahoma", Font.PLAIN, 17));
			lblLogra.setBounds(10, 24, 261, 21);
			enderecoPanel.add(lblLogra);
		}
		{
			tfCidade = new JTextField();
			tfCidade.setColumns(10);
			tfCidade.setBounds(10, 251, 261, 19);
			tfCidade.addKeyListener(new KeyAdapter() {
			@Override
				public void keyTyped(KeyEvent ev) {
				// código do evento:
					String caracteres = "0987654321";
					if((caracteres.contains(ev.getKeyChar()+""))){
						ev.consume();
					}
				}	
			});
			enderecoPanel.add(tfCidade);
		}
		{
			JLabel lblCidade = new JLabel("Cidade:");
			lblCidade.setFont(new Font("Tahoma", Font.PLAIN, 17));
			lblCidade.setBounds(10, 220, 261, 21);
			enderecoPanel.add(lblCidade);
		}
		
		chckbxComplemento = new JCheckBox("Complemento:");
		chckbxComplemento.setFont(new Font("Tahoma", Font.PLAIN, 17));
		chckbxComplemento.setBounds(153, 93, 142, 21);
		enderecoPanel.add(chckbxComplemento);
		chckbxComplemento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (chckbxComplemento.isSelected()) {
					tfComp.setEditable(true);
					tfComp.setEnabled(true);
				} else {
					tfComp.setEditable(false);
					tfComp.setEnabled(false);
				}
			}
		});
		
		tfComp = new JTextField();
		tfComp.setEditable(false);
		tfComp.setEnabled(false);
		tfComp.setColumns(10);
		tfComp.setBounds(153, 121, 69, 19);
		enderecoPanel.add(tfComp);
		{
			JLabel label = new JLabel("*");
			label.setForeground(Color.RED);
			label.setBounds(101, 22, 45, 20);
			enderecoPanel.add(label);
		}
		{
			JLabel label = new JLabel("*");
			label.setForeground(Color.RED);
			label.setBounds(77, 88, 45, 20);
			enderecoPanel.add(label);
		}
		{
			JLabel label = new JLabel("*");
			label.setForeground(Color.RED);
			label.setBounds(62, 155, 45, 20);
			enderecoPanel.add(label);
		}
		{
			JLabel label = new JLabel("*");
			label.setForeground(Color.RED);
			label.setBounds(69, 217, 45, 20);
			enderecoPanel.add(label);
		}
		{
			JLabel label = new JLabel("*");
			label.setForeground(Color.RED);
			label.setBounds(62, 10, 45, 20);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("*");
			label.setForeground(Color.RED);
			label.setBounds(55, 74, 45, 20);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("*");
			label.setForeground(Color.RED);
			label.setBounds(84, 141, 45, 20);
			contentPanel.add(label);
		}
		{
			JLabel lblObrigatrio = new JLabel("* - Obrigat\u00F3rio");
			lblObrigatrio.setForeground(Color.RED);
			lblObrigatrio.setBounds(10, 382, 98, 20);
			contentPanel.add(lblObrigatrio);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 410, 704, 31);
			getContentPane().add(buttonPane);
			buttonPane.setLayout(null);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (modo == -1) 
							novo();
						else 
							alterar();
					}
				});
				okButton.setBounds(493, 5, 79, 21);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancelar");
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
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{tfNome, tfCNPJ, tfTelefone, tfLogra, tfNum, chckbxComplemento, tfComp, tfBairro, tfCidade, okButton, cancelButton}));
		if (modo != -1) {
			ArrayList<Clinica> cs = lab.getClinicas();
			tfNome.setText(cs.get(modo).getNome());
			tfCNPJ.setText(cs.get(modo).getCNPJ());
			tfTelefone.setText(String.valueOf(cs.get(modo).getTelefone()));
			tfLogra.setText(String.valueOf(cs.get(modo).getRua()));
			tfNum.setText(String.valueOf(cs.get(modo).getNumero()));
			tfComp.setText(cs.get(modo).getComp());
			tfBairro.setText(cs.get(modo).getBairro());
			tfCidade.setText(cs.get(modo).getCidade());
			cod = cs.get(modo).getCod();
			if (cs.get(modo).getComp()!=null) {
				chckbxComplemento.setSelected(true);
				tfComp.setEnabled(true);
				tfComp.setEditable(true);
				tfComp.setText(cs.get(modo).getComp());
			}
		}
	}
	protected void alterar() {
		String comp = null;
		if (chckbxComplemento.isSelected()) 
			comp = tfComp.getText();
		String nome = tfNome.getText();
		String tel = tfTelefone.getText();
		String cnpj = tfCNPJ.getText();
		String logra = tfLogra.getText();
		String numero = tfNum.getText();
		String bairro = tfBairro.getText();
		String cidade = tfCidade.getText();
		if (verificarCampos()) {
			boolean finalTest = true;
			try {
				long telefone = Long.parseLong(tel);
				long num = Long.parseLong(numero);
				if(!(lab.alterClinica(cod, nome, cnpj, telefone, logra, num, comp, bairro, cidade)))
					finalTest = false;
				if (finalTest) {
					JOptionPane.showMessageDialog(this, "Clínica alterada com sucesso", "Alteração de clínica", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(this, "Ocorreu um erro no sistema de alteração, tente novamente", "Não foi possivel completar a alteração", JOptionPane.ERROR_MESSAGE);
				}
					
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Ocorreu um erro com os campos de número e/ou telefone, tente novamente", "Não foi possivel completar a alteração", JOptionPane.ERROR_MESSAGE);
				finalTest = false;
			}
		} else {
			JOptionPane.showMessageDialog(this, "Preencha todos os campos corretamente", "Não foi possivel completar a alteração", JOptionPane.ERROR_MESSAGE);
		}
	}
	protected void novo() {
		String comp = null;
		if (chckbxComplemento.isSelected()) 
			comp = tfComp.getText();
		String nome = tfNome.getText();
		String tel = tfTelefone.getText();
		String cnpj = tfCNPJ.getText();
		String logra = tfLogra.getText();
		String numero = tfNum.getText();
		String bairro = tfBairro.getText();
		String cidade = tfCidade.getText();
		if (verificarCampos()) {
			boolean finalTest = true;
			try {
				long telefone = Long.parseLong(tel);
				long num = Long.parseLong(numero);
				if(!(lab.newClinica(nome, cnpj, telefone, logra, num, comp, bairro, cidade)))
					finalTest = false;
				if (finalTest) {
					JOptionPane.showMessageDialog(this, "Clínica cadastrado com sucesso", "Cadastro de clínica", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(this, "Ocorreu um erro no sistema de cadastro, tente novamente", "Não foi possivel completar o cadastro", JOptionPane.ERROR_MESSAGE);
				}
					
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Ocorreu um erro com os campos de número e/ou telefone, tente novamente", "Não foi possivel completar o cadastro", JOptionPane.ERROR_MESSAGE);
				finalTest = false;
			}
		} else {
			JOptionPane.showMessageDialog(this, "Preencha todos os campos corretamente", "Não foi possivel completar o cadastro", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	private boolean verificarCampos() {
		boolean test = true;
		String comp = null;
		String nome = tfNome.getText();
		String tel = tfTelefone.getText();
		String cnpj = tfCNPJ.getText();
		String logra = tfLogra.getText();
		String numero = tfNum.getText();
		String bairro = tfBairro.getText();
		String cidade = tfCidade.getText();
		if (chckbxComplemento.isSelected()) {
			comp = tfComp.getText();
			if (comp.equals(null) || comp.equals("") || comp.isEmpty()) {
				tfComp.setBackground(Color.PINK);
				test = false;
			} else {
				tfComp.setBackground(Color.WHITE);
			
			}
		} else {
			tfComp.setBackground(Color.WHITE);
		}
		if (nome.equals(null) || nome.equals("") || nome.isEmpty()) {
			tfNome.setBackground(Color.PINK);
			test = false;
		} else {
			tfNome.setBackground(Color.WHITE);
		}
		if (tel.equals(null) || tel.equals("") || tel.isEmpty() || tel.length() < 8) {
			tfTelefone.setBackground(Color.PINK);
			test = false;
		} else {
			tfTelefone.setBackground(Color.WHITE);
		}
		if (!(isCNPJ(cnpj))) {
			tfCNPJ.setBackground(Color.PINK);
			test = false;
		} else {
			tfCNPJ.setBackground(Color.WHITE);
		}
		if (logra.equals(null) || logra.equals("") || logra.isEmpty()) {
			tfLogra.setBackground(Color.PINK);
			test = false;
		} else {
			tfLogra.setBackground(Color.WHITE);
		}
		if (numero.equals(null) || numero.equals("") || numero.isEmpty()) {
			tfNum.setBackground(Color.PINK);
			test = false;
		} else {
			tfNum.setBackground(Color.WHITE);
		}
		if (bairro.equals(null) || bairro.equals("") || bairro.isEmpty()) {
			tfBairro.setBackground(Color.PINK);
			test = false;
		} else {
			tfBairro.setBackground(Color.WHITE);
		}
		if (cidade.equals(null) || cidade.equals("") || cidade.isEmpty()) {
			tfCidade.setBackground(Color.PINK);
			test = false;
		} else {
			tfCidade.setBackground(Color.WHITE);
		}
		return test;
	}
	
	private static boolean isCNPJ(String CNPJ) {
	    if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111") ||
	        CNPJ.equals("22222222222222") || CNPJ.equals("33333333333333") ||
	        CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555") ||
	        CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777") ||
	        CNPJ.equals("88888888888888") || CNPJ.equals("99999999999999") ||
	       (CNPJ.length() != 14))
	       return(false);

	    char dig13, dig14;
	    int sm, i, r, num, peso;

	    try {

	      sm = 0;
	      peso = 2;
	      for (i=11; i>=0; i--) {
	        num = (int)(CNPJ.charAt(i) - 48);
	        sm = sm + (num * peso);
	        peso = peso + 1;
	        if (peso == 10)
	           peso = 2;
	      }

	      r = sm % 11;
	      if ((r == 0) || (r == 1))
	         dig13 = '0';
	      else dig13 = (char)((11-r) + 48);

	      sm = 0;
	      peso = 2;
	      for (i=12; i>=0; i--) {
	        num = (int)(CNPJ.charAt(i)- 48);
	        sm = sm + (num * peso);
	        peso = peso + 1;
	        if (peso == 10)
	           peso = 2;
	      }

	      r = sm % 11;
	      if ((r == 0) || (r == 1))
	         dig14 = '0';
	      else dig14 = (char)((11-r) + 48);

	      if ((dig13 == CNPJ.charAt(12)) && (dig14 == CNPJ.charAt(13)))
	         return(true);
	      else return(false);
	    } catch (InputMismatchException erro) {
	        return(false);
	    }
	  }
}
