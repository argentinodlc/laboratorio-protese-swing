package ui.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import sistema.classes.Dentista;
import sistema.interfaces.ILaboratorio;

import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Toolkit;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.JSeparator;

public class GUICreateUpdateDentista extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfNome;
	private JTextField tfCro;
	private JTextField tfTel;
	private JTextField tfBairro;
	private JTextField tfNumero;
	private JTextField tfLogra;
	private JTextField tfCidade;
	private JTextField tfComp;
	private JCheckBox checkComp;
	private ILaboratorio lab;
	private long cod;
	private JLabel lblCro;
	private JLabel lblTel;
	private JLabel lblBairro;
	private JLabel lblNumero;
	private JLabel lblLogra;
	private JLabel lblCidade;
	private JButton okButton;
	private JButton cancelButton;

	public GUICreateUpdateDentista(final int modo, ILaboratorio lab) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/icon.png")));
		this.lab = lab;
		getContentPane().setBackground(SystemColor.window);
		setBackground(SystemColor.window);
		setModal(true);
		setResizable(false);
		if (modo != -1) 
			setTitle("Alterar Dentista");
		else
			setTitle("Cadastrar Dentista");
		setBounds(100, 100, 720, 480);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 704, 441);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNome.setBounds(10, 10, 116, 21);
		contentPanel.add(lblNome);
		
		tfNome = new JTextField();
		tfNome.setBounds(10, 41, 261, 23);
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
		contentPanel.add(tfNome);
		tfNome.setColumns(10);
		{
			tfCro = new JTextField();
			tfCro.setColumns(10);
			tfCro.setBounds(10, 107, 261, 23);
			tfCro.addKeyListener(new KeyAdapter() {
			@Override
				public void keyTyped(KeyEvent ev) {
				// código do evento:
					String caracteres = "0987654321";
					if(!(caracteres.contains(ev.getKeyChar()+""))){
						ev.consume();
					}
				}	
			});
			contentPanel.add(tfCro);
		}
		{
			lblCro = new JLabel("CRO:");
			lblCro.setFont(new Font("Tahoma", Font.PLAIN, 17));
			lblCro.setBounds(10, 76, 261, 21);
			contentPanel.add(lblCro);
		}
		{
			tfTel = new JTextField();
			tfTel.setColumns(10);
			tfTel.setBounds(10, 173, 261, 23);
			tfTel.addKeyListener(new KeyAdapter() {
			@Override
				public void keyTyped(KeyEvent ev) {
				// código do evento:
					String caracteres = "0987654321";
					if(!(caracteres.contains(ev.getKeyChar()+""))){
						ev.consume();
					}
				}	
			});
			contentPanel.add(tfTel);
		}
		{
			lblTel = new JLabel("Telefone:");
			lblTel.setFont(new Font("Tahoma", Font.PLAIN, 17));
			lblTel.setBounds(10, 142, 261, 21);
			contentPanel.add(lblTel);
		}
		
		JPanel enderecoPanel = new JPanel();
		enderecoPanel.setBorder(new TitledBorder(null, "Endere\u00E7o", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		enderecoPanel.setBounds(305, 10, 389, 392);
		contentPanel.add(enderecoPanel);
		enderecoPanel.setLayout(null);
		{
			tfBairro = new JTextField();
			tfBairro.setColumns(10);
			tfBairro.setBounds(10, 187, 261, 23);
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
			lblBairro = new JLabel("Bairro:");
			lblBairro.setFont(new Font("Tahoma", Font.PLAIN, 17));
			lblBairro.setBounds(10, 156, 261, 21);
			enderecoPanel.add(lblBairro);
		}
		{
			tfNumero = new JTextField();
			tfNumero.setColumns(10);
			tfNumero.setBounds(10, 121, 69, 23);
			tfNumero.addKeyListener(new KeyAdapter() {
			@Override
				public void keyTyped(KeyEvent ev) {
				// código do evento:
					String caracteres = "0987654321";
					if(!(caracteres.contains(ev.getKeyChar()+""))){
						ev.consume();
					}
				}	
			});


			enderecoPanel.add(tfNumero);
		}
		{
			lblNumero = new JLabel("N\u00FAmero:");
			lblNumero.setFont(new Font("Tahoma", Font.PLAIN, 17));
			lblNumero.setBounds(10, 90, 261, 21);
			enderecoPanel.add(lblNumero);
		}
		{
			tfLogra = new JTextField();
			tfLogra.setColumns(10);
			tfLogra.setBounds(10, 55, 261, 23);
			tfLogra.addKeyListener(new KeyAdapter() {
			@Override
				public void keyTyped(KeyEvent ev) {
				// código do evento:
					String caracteres = "0987654321";
					if(caracteres.contains(ev.getKeyChar()+"")){
						ev.consume();
					}
				}	
			});
			enderecoPanel.add(tfLogra);
		}
		{
			lblLogra = new JLabel("Logradouro:");
			lblLogra.setFont(new Font("Tahoma", Font.PLAIN, 17));
			lblLogra.setBounds(10, 24, 261, 21);
			enderecoPanel.add(lblLogra);
		}
		{
			tfCidade = new JTextField();
			tfCidade.setColumns(10);
			tfCidade.setBounds(10, 251, 261, 23);
			tfCidade.addKeyListener(new KeyAdapter() {
			@Override
				public void keyTyped(KeyEvent ev) {
				// código do evento:
					String caracteres = "0987654321";
					if(caracteres.contains(ev.getKeyChar()+"")){
						ev.consume();
					}
				}	
			});
			enderecoPanel.add(tfCidade);
		}
		{
			lblCidade = new JLabel("Cidade:");
			lblCidade.setFont(new Font("Tahoma", Font.PLAIN, 17));
			lblCidade.setBounds(10, 220, 261, 21);
			enderecoPanel.add(lblCidade);
		}
		
		checkComp = new JCheckBox("Complemento:");
		checkComp.addActionListener(new ActionListener() {

	public void actionPerformed(ActionEvent arg0) {
		if (checkComp.isSelected()) {
			tfComp.setEditable(true);
			tfComp.setEnabled(true);
		} else {
			tfComp.setEditable(false);
			tfComp.setEnabled(false);
		}
	}});checkComp.setFont(new Font("Tahoma",Font.PLAIN,17));checkComp.setBounds(153,93,142,21);enderecoPanel.add(checkComp);

	tfComp=new JTextField();tfComp.setEditable(false);tfComp.setEnabled(false);tfComp.setColumns(10);tfComp.setBounds(153,121,69,23);enderecoPanel.add(tfComp);

	JLabel label_3 = new JLabel(
			"*");label_3.setForeground(Color.RED);label_3.setBounds(101,24,45,20);enderecoPanel.add(label_3);

	JLabel label_4 = new JLabel(
			"*");label_4.setForeground(Color.RED);label_4.setBounds(77,90,45,20);enderecoPanel.add(label_4);

	JLabel label_5 = new JLabel(
			"*");label_5.setForeground(Color.RED);label_5.setBounds(62,157,45,20);enderecoPanel.add(label_5);

	JLabel label_6 = new JLabel(
			"*");label_6.setForeground(Color.RED);label_6.setBounds(69,219,45,20);enderecoPanel.add(label_6);

	JLabel label = new JLabel("*");label.setForeground(Color.RED);label.setBounds(62,10,45,20);contentPanel.add(label);

	JLabel label_1 = new JLabel(
			"*");label_1.setForeground(Color.RED);label_1.setBounds(51,74,45,23);contentPanel.add(label_1);

	JLabel label_2 = new JLabel(
			"*");label_2.setForeground(Color.RED);label_2.setBounds(81,143,45,20);contentPanel.add(label_2);

	JLabel lblObrigatrio = new JLabel(
			"* - Obrigat\u00F3rio");lblObrigatrio.setForeground(Color.RED);lblObrigatrio.setBounds(10,412,86,20);contentPanel.add(lblObrigatrio);
	{
		okButton = new JButton("OK");
		okButton.setBounds(511, 412, 79, 21);
		contentPanel.add(okButton);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (modo == -1) {
					adicionarDentista();
				} else {
					alterarDentista();
				}
			}
		});
		okButton.setActionCommand("OK");
		getRootPane().setDefaultButton(okButton);
	}
	{
		cancelButton = new JButton("Cancelar");
		cancelButton.setBounds(597, 412, 97, 21);
		contentPanel.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		cancelButton.setActionCommand("Cancel");
	}

	setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{tfNome, tfCro, tfTel, tfLogra, tfNumero, checkComp, tfComp, tfBairro, tfCidade, okButton, cancelButton}));if(modo!=-1)

	{
		ArrayList<Dentista> dentistas = (ArrayList<Dentista>) lab.getArrayDentistas();
		tfNome.setText(dentistas.get(modo).getNome());
		tfCro.setText(String.valueOf(dentistas.get(modo).getCro()));
		tfTel.setText(String.valueOf(dentistas.get(modo).getTelefone()));
		tfLogra.setText(String.valueOf(dentistas.get(modo).getRua()));
		tfNumero.setText(String.valueOf(dentistas.get(modo).getNumero()));
		tfComp.setText(dentistas.get(modo).getComp());
		tfBairro.setText(dentistas.get(modo).getBairro());
		tfCidade.setText(dentistas.get(modo).getCidade());
		cod = dentistas.get(modo).getCod();
		if (dentistas.get(modo).getComp() != null) {
			checkComp.setSelected(true);
			tfComp.setEnabled(true);
			tfComp.setEditable(true);
			tfComp.setText(dentistas.get(modo).getComp());
		}
	}
	}

	private boolean verificarCampos() {
		boolean test = true;
		String comp = null;
		String nome = tfNome.getText();
		String tel = tfTel.getText();
		String cro = tfCro.getText();
		String logra = tfLogra.getText();
		String numero = tfNumero.getText();
		String bairro = tfBairro.getText();
		String cidade = tfCidade.getText();
		if (checkComp.isSelected()) {
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
			tfTel.setBackground(Color.PINK);
			test = false;
		} else {
			tfTel.setBackground(Color.WHITE);
		}
		if (cro.equals(null) || cro.equals("") || cro.isEmpty()) {
			tfCro.setBackground(Color.PINK);
			test = false;
		} else {
			tfCro.setBackground(Color.WHITE);
		}
		if (logra.equals(null) || logra.equals("") || logra.isEmpty()) {
			tfLogra.setBackground(Color.PINK);
			test = false;
		} else {
			tfLogra.setBackground(Color.WHITE);
		}
		if (numero.equals(null) || numero.equals("") || numero.isEmpty()) {
			tfNumero.setBackground(Color.PINK);
			test = false;
		} else {
			tfNumero.setBackground(Color.WHITE);
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

	private void alterarDentista() {
		String comp = null;
		if (checkComp.isSelected())
			comp = tfComp.getText();
		String nome = tfNome.getText();
		String tel = tfTel.getText();
		String cro = tfCro.getText();
		String logra = tfLogra.getText();
		String numero = tfNumero.getText();
		String bairro = tfBairro.getText();
		String cidade = tfCidade.getText();
		if (!(verificarCampos())) {
			JOptionPane.showMessageDialog(this, "Preencha todos os campos corretamente",
					"Não foi possivel completar a alteração", JOptionPane.ERROR_MESSAGE);
		} else {
			boolean finalTest = true;
			try {
				long telefone = Long.parseLong(tel);
				long num = Long.parseLong(numero);
				if (!(lab.alterDentista(cod, nome, cro, telefone, logra, num, comp, bairro, cidade)))
					finalTest = false;
				if (finalTest) {
					JOptionPane.showMessageDialog(this, "Dentista alterado com sucesso", "Alterção de dentista",
							JOptionPane.INFORMATION_MESSAGE);
					dispose();
				} else {
					JOptionPane.showMessageDialog(this, "Ocorreu um erro no sistema de alteração, tente novamente",
							"Não foi possivel completar a alteração", JOptionPane.ERROR_MESSAGE);
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(this,
						"Ocorreu um erro com os campos de número e/ou telefone, tente novamente",
						"Não foi possivel completar a alteração", JOptionPane.ERROR_MESSAGE);
				finalTest = false;
			}
		}

	}

	private void adicionarDentista() {
		String comp = null;
		if (checkComp.isSelected())
			comp = tfComp.getText();
		String nome = tfNome.getText();
		String tel = tfTel.getText();
		String cro = tfCro.getText();
		String logra = tfLogra.getText();
		String numero = tfNumero.getText();
		String bairro = tfBairro.getText();
		String cidade = tfCidade.getText();
		if (!(verificarCampos())) {
			JOptionPane.showMessageDialog(this, "Preencha todos os campos corretamente",
					"Não foi possivel completar o cadastro", JOptionPane.ERROR_MESSAGE);
		} else {
			boolean finalTest = true;
			try {
				long telefone = Long.parseLong(tel);
				long num = Long.parseLong(numero);
				if (!(lab.newDentista(nome, cro, telefone, logra, num, comp, bairro, cidade)))
					finalTest = false;
				if (finalTest) {
					JOptionPane.showMessageDialog(this, "Dentista cadastrado com sucesso", "Cadastro de dentista",
							JOptionPane.INFORMATION_MESSAGE);
					dispose();
				} else {
					JOptionPane.showMessageDialog(this, "Ocorreu um erro no sistema de cadastro, tente novamente",
							"Não foi possivel completar o cadastro", JOptionPane.ERROR_MESSAGE);
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(this,
						"Ocorreu um erro com os campos de número e/ou telefone, tente novamente",
						"Não foi possivel completar o cadastro", JOptionPane.ERROR_MESSAGE);
				finalTest = false;
			}
		}

	}
}
