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
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class GUICreateUpdateProduto extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfProduto;
	private JTextField tfValorMedio;
	private int modo;
	private ILaboratorio lab;
	private long cod;

	public GUICreateUpdateProduto(int m, ILaboratorio l) {
		modo = m;
		lab = l;
		if (modo == -1)
			setTitle("Cadastrar produto");
		else
			setTitle("Alterar produto");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/icon.png")));
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 446, 192);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblProduto = new JLabel("Produto:");
		lblProduto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblProduto.setBounds(10, 10, 175, 13);
		contentPanel.add(lblProduto);
		
		tfProduto = new JTextField();
		tfProduto.setBounds(10, 25, 175, 19);
		contentPanel.add(tfProduto);
		tfProduto.setColumns(10);
		
		JLabel lblValorMdio = new JLabel("Valor m\u00E9dio:");
		lblValorMdio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblValorMdio.setBounds(10, 55, 175, 13);
		contentPanel.add(lblValorMdio);
		
		tfValorMedio = new JTextField();
		tfValorMedio.setColumns(10);
		tfValorMedio.setBounds(10, 70, 88, 19);
		tfValorMedio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ev) {
			// código do evento:
				String caracteres = "0987654321,.";
				if(!(caracteres.contains(ev.getKeyChar()+""))){
					ev.consume();
				}
			}	
		});
		contentPanel.add(tfValorMedio);
		
		JLabel label = new JLabel("*");
		label.setForeground(Color.RED);
		label.setBounds(69, 4, 45, 20);
		contentPanel.add(label);
		
		JLabel label_1 = new JLabel("*");
		label_1.setForeground(Color.RED);
		label_1.setBounds(94, 46, 45, 23);
		contentPanel.add(label_1);
		
		JLabel label_2 = new JLabel("* - Obrigat\u00F3rio");
		label_2.setForeground(Color.RED);
		label_2.setBounds(10, 102, 86, 20);
		contentPanel.add(label_2);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						alterNewProduto();
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
		if (modo != -1) {
			tfProduto.setText(lab.getArrayProdutos().get(modo).getNome());
			tfValorMedio.setText(String.valueOf(lab.getArrayProdutos().get(modo).getValorMedio()));
			cod = lab.getArrayProdutos().get(modo).getCod();
		}
	}
	
	private void alterNewProduto() {
		String nome = tfProduto.getText();
		String valor = tfValorMedio.getText();
		if (verificaCampos(nome, valor)) {
			double valorMedio = 0;
			try {
				if (valor.contains(","))
					valorMedio = NumberFormat.getInstance().parse(valor).doubleValue();
				else
					valorMedio = Double.parseDouble(valor);
				if (modo == -1) {
					if (lab.newProduto(nome, valorMedio)) {
						JOptionPane.showMessageDialog(GUICreateUpdateProduto.this, "Produto cadastrado com sucesso",
								"Novo produto", JOptionPane.INFORMATION_MESSAGE);
						dispose();
					} else {
						JOptionPane.showMessageDialog(GUICreateUpdateProduto.this,
								"Ocorreu algum problema com o sistema de cadastro",
								"Não foi possível completar a operação", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					if (lab.alterProduto(cod, nome, valorMedio)) {
						JOptionPane.showMessageDialog(GUICreateUpdateProduto.this, "Produto alterado com sucesso",
								"Alterar produto", JOptionPane.INFORMATION_MESSAGE);
						dispose();
					} else {
						JOptionPane.showMessageDialog(GUICreateUpdateProduto.this,
								"Ocorreu algum problema com o sistema de alteração",
								"Não foi possível completar a operação", JOptionPane.ERROR_MESSAGE);
					}
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(GUICreateUpdateProduto.this,
						"Ocorreu algum problema com o campo de valor", "Não foi possível realizar a operação",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(GUICreateUpdateProduto.this,
					"Preencha todos os campos corretamente e tente novamente", "Não foi possível realizar a operação",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	private boolean verificaCampos(String nome, String valor) {
		boolean test = true;
		if (nome == null || nome.equals("")) {
			tfProduto.setBackground(Color.PINK);
			test = false;
		} else {
			tfProduto.setBackground(Color.WHITE);
		}
		double x = 0;
		try {
			if (valor.contains(","))
				x = NumberFormat.getInstance().parse(valor).doubleValue();
			else
				x = Double.parseDouble(valor);
			if (x > 0)
				tfValorMedio.setBackground(Color.WHITE);
			else {
				tfValorMedio.setBackground(Color.PINK);
				test = false;
			}
		} catch (Exception e) {
			tfValorMedio.setBackground(Color.PINK);
			test = false;
		}
		return test;
	}
}
