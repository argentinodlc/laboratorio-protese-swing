package ui.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;

import sistema.classes.Fornecedor;
import sistema.classes.Produto;
import sistema.interfaces.ILaboratorio;
import ui.tables.ProdutoCompraTable;
import javax.swing.ListSelectionModel;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;

public class GUICreateUpdateCompra extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfValor;
	private JTextField tfQtd;
	private JTable table;
	private ProdutoCompraTable ptm;
	private JTextField tfDesc;
	private JTextField tfCompra;
	private JComboBox cbProduto;
	private ILaboratorio lab;
	private JComboBox cbFornecedor;
	private JDateChooser dtCompra;

	public GUICreateUpdateCompra(final ILaboratorio lab) {
		setTitle("Registrar compra");
		this.lab = lab;
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/icon.png")));
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 569, 408);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
				JLabel label_3 = new JLabel("*");
				label_3.setBounds(72, 62, 63, 13);
				contentPanel.add(label_3);
				label_3.setFont(new Font("Tahoma", Font.PLAIN, 10));
				label_3.setForeground(Color.RED);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Produtos",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 63, 227, 159);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel label = new JLabel("Valor");
		label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label.setBounds(10, 59, 96, 13);
		panel.add(label);

		cbProduto = new JComboBox();
		cbProduto.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					DecimalFormat df = new DecimalFormat("0.##");
					tfValor.setText(
							String.valueOf(df.format(lab.getArrayProdutos().get(cbProduto.getSelectedIndex()).getValorMedio())));
				} catch (Exception e) {
				}
			}
		});
		cbProduto.setBounds(10, 28, 167, 21);
		panel.add(cbProduto);
		carregarCbProduto();

		JButton btAddProduto = new JButton("");
		btAddProduto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btAddProduto
				.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/plus.png"))));
		btAddProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GUICreateUpdateProduto novo = new GUICreateUpdateProduto(-1, lab);
				novo.setVisible(true);
				carregarCbProduto();
				if (lab.getArrayProdutos().size() > 0)
					cbProduto.setSelectedIndex(lab.getArrayProdutos().size() - 1);
			}
		});
		btAddProduto.setBounds(182, 22, 32, 32);
		panel.add(btAddProduto);

		tfValor = new JTextField();
		tfValor.setEditable(false);
		tfValor.setBounds(10, 76, 96, 19);
		tfValor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ev) {
			// código do evento:
				String caracteres = "0987654321,.";
				if(!(caracteres.contains(ev.getKeyChar()+""))){
					ev.consume();
				}
			}	
		});
		panel.add(tfValor);
		tfValor.setColumns(10);
		try {
			DecimalFormat df = new DecimalFormat("0.##");
			tfValor.setText(
					String.valueOf(df.format(lab.getArrayProdutos().get(cbProduto.getSelectedIndex()).getValorMedio())));
		} catch (Exception e) {
		}

		final JToggleButton btAjustar = new JToggleButton("Ajustar");
		btAjustar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (btAjustar.isSelected()) {
					tfValor.setEditable(true);
				} else
					tfValor.setEditable(false);
			}
		});
		btAjustar.setBounds(112, 75, 102, 21);
		panel.add(btAjustar);

		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblQuantidade.setBounds(8, 104, 108, 15);
		panel.add(lblQuantidade);

		tfQtd = new JTextField();
		tfQtd.setText("1");
		tfQtd.setColumns(10);
		tfQtd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ev) {
			// código do evento:
				String caracteres = "0987654321";
				if(!(caracteres.contains(ev.getKeyChar()+""))){
					ev.consume();
				}
			}	
		});
		tfQtd.setBounds(10, 125, 96, 19);
		panel.add(tfQtd);

		JButton btAdicionarProdutoTb = new JButton("Adicionar produto");
		btAdicionarProdutoTb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (cbProduto.getSelectedItem() != null && Integer.parseInt(tfQtd.getText()) > 0) {
						double preco;
						if (tfValor.getText().contains(","))
							preco = NumberFormat.getInstance().parse(tfValor.getText()).doubleValue();
						else
							preco = Double.parseDouble(tfValor.getText());
						if (preco > 0) {
							ptm.addRow((String) cbProduto.getSelectedItem(), preco, Integer.parseInt(tfQtd.getText()));
						} else {
							JOptionPane.showMessageDialog(GUICreateUpdateCompra.this,
									"Preencha os campos corretamente e tente novamente",
									"Não foi possível adicionar o produto", JOptionPane.WARNING_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(GUICreateUpdateCompra.this,
								"Preencha os campos corretamente e tente novamente",
								"Não foi possível adicionar o produto", JOptionPane.WARNING_MESSAGE);
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(GUICreateUpdateCompra.this,
							"Preencha os campos corretamente e tente novamente", "Não foi possível adicionar o produto",
							JOptionPane.WARNING_MESSAGE);
				}
				
				try {
					if (ptm.getRowCount() > 0) {
						double total = 0;
						for (int i = 0; i < ptm.getRowCount(); i++) {
							total = total + NumberFormat.getInstance().parse((String) ptm.getValueAt(i, 3)).doubleValue();
						}
						DecimalFormat df = new DecimalFormat("0.##");
						String d = df.format(total);
						tfCompra.setText(d);
					} else {
						tfCompra.setText("");
					}
				} catch (Exception e1) {
				}
			}
		});
		btAdicionarProdutoTb.setBounds(247, 63, 142, 21);
		contentPanel.add(btAdicionarProdutoTb);

		final JButton btRemoverProdutoTb = new JButton("Remover produto");
		btRemoverProdutoTb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (table.getSelectedRow() != -1) {
					ptm.removeRow(table.getSelectedRow());
				} else {
					JOptionPane.showMessageDialog(GUICreateUpdateCompra.this, "Selecione uma linha e tente novamente",
							"Não foi possível remover o produto", JOptionPane.WARNING_MESSAGE);
				}
		
				try {
					if (ptm.getRowCount() > 0) {
						double total = 0;
						for (int i = 0; i < ptm.getRowCount(); i++) {
							total = total + NumberFormat.getInstance().parse((String) ptm.getValueAt(i, 3)).doubleValue();
						}
						DecimalFormat df = new DecimalFormat("0.##");
						tfCompra.setText(df.format(total));
					} else {
						tfCompra.setText("");
					}
				} catch (Exception e1) {
				}
			}
		});
		btRemoverProdutoTb.setEnabled(false);
		btRemoverProdutoTb.setBounds(399, 63, 146, 21);
		contentPanel.add(btRemoverProdutoTb);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(247, 94, 298, 125);
		contentPanel.add(scrollPane);

		ptm = new ProdutoCompraTable();
		table = new JTable(ptm);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (table.getSelectedRow() == -1)
					btRemoverProdutoTb.setEnabled(false);
				else
					btRemoverProdutoTb.setEnabled(true);
			}
		});
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (table.getSelectedRow() == -1)
					btRemoverProdutoTb.setEnabled(false);
				else
					btRemoverProdutoTb.setEnabled(true);

				try {
					if (ptm.getRowCount() > 0) {
						double total = 0;
						for (int i = 0; i < ptm.getRowCount(); i++) {
							total = total + NumberFormat.getInstance().parse((String) ptm.getValueAt(i, 3)).doubleValue();
						}
						DecimalFormat df = new DecimalFormat("0.##");
						tfCompra.setText(df.format(total));
					} else {
						tfCompra.setText("");
					}
				} catch (Exception e1) {
				}
			}
		});
		scrollPane.setViewportView(table);
				
						JLabel label_2 = new JLabel("*");
						label_2.setForeground(Color.RED);
						label_2.setBounds(98, 232, 40, 13);
						contentPanel.add(label_2);
		
				JLabel label_4 = new JLabel("*");
				label_4.setForeground(Color.RED);
				label_4.setBounds(86, 12, 26, 13);
				contentPanel.add(label_4);

		JLabel lblFornecedor = new JLabel("Fornecedor");
		lblFornecedor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFornecedor.setBounds(20, 232, 167, 13);
		contentPanel.add(lblFornecedor);

		cbFornecedor = new JComboBox();
		cbFornecedor.setBounds(20, 250, 167, 21);
		contentPanel.add(cbFornecedor);
		carregarCbFornecedor();

		JButton btAddFornecedor = new JButton("");
		btAddFornecedor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btAddFornecedor
				.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/plus.png"))));
		btAddFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GUICreateUpdateFornecedor novo = new GUICreateUpdateFornecedor(-1, lab);
				novo.setVisible(true);
				carregarCbFornecedor();
				if (lab.getArrayFornecedores().size() > 0)
					cbProduto.setSelectedIndex(lab.getArrayFornecedores().size() - 1);
			}
		});
		btAddFornecedor.setBounds(192, 244, 32, 32);
		contentPanel.add(btAddFornecedor);

		JLabel lblValorTotal = new JLabel("Valor total: ");
		lblValorTotal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblValorTotal.setBounds(363, 229, 132, 19);
		contentPanel.add(lblValorTotal);

		dtCompra = new JDateChooser();
		dtCompra.setBounds(20, 299, 106, 19);
		dtCompra.setDateFormatString("dd/MM/yyyy");
		contentPanel.add(dtCompra);

		JLabel lblDataDaCompra = new JLabel("Data da compra");
		lblDataDaCompra.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDataDaCompra.setBounds(20, 281, 167, 13);
		contentPanel.add(lblDataDaCompra);

		JLabel lblObrigatrio = new JLabel("* - Obrigat\u00F3rio");
		lblObrigatrio.setForeground(Color.RED);
		lblObrigatrio.setBounds(460, 315, 167, 13);
		contentPanel.add(lblObrigatrio);

		JLabel lblNewLabel = new JLabel("Descri\u00E7\u00E3o");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(20, 10, 458, 21);
		contentPanel.add(lblNewLabel);

		tfDesc = new JTextField();
		tfDesc.setBounds(20, 33, 458, 19);
		contentPanel.add(tfDesc);
		tfDesc.setColumns(10);

		tfCompra = new JTextField();
		tfCompra.setEditable(false);
		tfCompra.setBounds(449, 229, 96, 19);
		contentPanel.add(tfCompra);
		tfCompra.setColumns(10);
		
				JLabel label_1 = new JLabel("*");
				label_1.setBounds(131, 281, 106, 13);
				contentPanel.add(label_1);
				label_1.setForeground(Color.RED);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						novaCompra();
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

	private void novaCompra() {
		try {
			if (cbProduto.getSelectedItem() != null && cbFornecedor.getSelectedItem() != null) {
				if (verificaCampos()) {
					String descricao = tfDesc.getText();
					double valor;
					if (tfCompra.getText().contains(","))
						valor = NumberFormat.getInstance().parse(tfCompra.getText()).doubleValue();
					else
						valor = Double.parseDouble(tfCompra.getText());
					Date data = dtCompra.getDate();
					Fornecedor fornecedor = lab.getArrayFornecedores().get(cbFornecedor.getSelectedIndex());
					if (lab.newCompra(descricao, valor, data, fornecedor)) {
						JOptionPane.showMessageDialog(GUICreateUpdateCompra.this,
								"Compra registrada com sucesso",
								"Sucesso", JOptionPane.INFORMATION_MESSAGE);
							dispose();
					} else {
						JOptionPane.showMessageDialog(GUICreateUpdateCompra.this,
								"Ocorreu um problema com o sistema de cadastro",
								"Não foi possível registrar a compra", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(GUICreateUpdateCompra.this,
							"Preencha os campos corretamente e tente novamente",
							"Não foi possível registrar a compra", JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(GUICreateUpdateCompra.this,
						"Verifique os produtos e/ou fornecedor e tente novamente",
						"Não foi possível registrar a compra", JOptionPane.WARNING_MESSAGE);
			}
		} catch (Exception err) {
			JOptionPane.showMessageDialog(GUICreateUpdateCompra.this,
					"Preencha os campos corretamente e tente novamente",
					"Não foi possível registrar a compra", JOptionPane.WARNING_MESSAGE);
		}
	}

	private boolean verificaCampos() {
		boolean test = true;
		double preco;
		if (tfDesc.getText().equals("")) {
			tfDesc.setBackground(Color.PINK);
			test = false;
		} else {
			tfDesc.setBackground(Color.WHITE);
		}
		try {
			double d;
			if (tfCompra.getText().contains(",")) {
				DecimalFormat df = new DecimalFormat("0.##");
				d = (double) df.parse(tfCompra.getText());
			} else {
				d = Double.parseDouble(tfCompra.getText());
			}
				preco = d;
			if (preco > 0) {
				tfCompra.setBackground(Color.WHITE);
			} else {
				tfCompra.setBackground(Color.PINK);
				test = false;
			}
			if (dtCompra.getDate() == null) 
				test = false;
				try {
					if (Integer.parseInt(tfQtd.getText()) > 0) {
						tfQtd.setBackground(Color.WHITE);
						try {
							if (d > 0) {
								tfCompra.setBackground(Color.WHITE);
							} else {
								tfCompra.setBackground(Color.PINK);
								test = false;
							}
						} catch (Exception outra) {
							tfCompra.setBackground(Color.PINK);
							test = false;
						}
					}
				} catch (Exception ep) {
					test = false;
					tfQtd.setBackground(Color.PINK);
				}
		} catch (Exception ex) {
			test = false;
			tfCompra.setBackground(Color.PINK);
		}
		return test;
	}

	private void carregarCbProduto() {
		ArrayList<Produto> p = lab.getArrayProdutos();
		if (p != null) {
			if (p.size() > 0) {
				cbProduto.removeAllItems();
				for (int i = 0; i < p.size(); i++) {
					cbProduto.addItem(p.get(i).getNome());
				}
			}
		}
	}

	private void carregarCbFornecedor() {
		ArrayList<Fornecedor> f = lab.getArrayFornecedores();
		if (f != null) {
			if (f.size() > 0) {
				cbFornecedor.removeAllItems();
				for (int i = 0; i < f.size(); i++) {
					cbFornecedor.addItem(f.get(i).getNome());
				}
			}
		}
	}
}
