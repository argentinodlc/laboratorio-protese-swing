package ui.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;

import sistema.classes.Servicos;
import sistema.interfaces.ILaboratorio;
import ui.gui.relatorios.RelatorioServico;
import ui.tables.ServicosTableModel;


public class CRUDServicos extends JPanel {

		private JTable tbServicos;
		private JTextField txtPesquisa;
		private JButton btnExcluir;
		private JButton btnAlterar;
		private JRadioButtonMenuItem rdbtnmntmTipo;
		private RadioButtonHandler handler;
		private int filtro = 0;
		private JRadioButtonMenuItem rdbtnmntmPreco;
		private JRadioButtonMenuItem rdbtnmntmDescricao;
		private JRadioButtonMenuItem rdbtnmntmEntrada;
		private JRadioButtonMenuItem rdbtnmntmSaida;
		private JRadioButtonMenuItem rdbtnmntmCliente;
		private JRadioButtonMenuItem rdbtnmntmFunc;
		private JRadioButtonMenuItem rdbtnmntmPaciente;
		private JRadioButtonMenuItem rdbtnmntmSituacao;
		private JButton btnExtrato;
		private ServicosTableModel stm;
		private JYearChooser yearChooser;
		private JMonthChooser monthChooser;
		private JButton btnFiltrar;
		private JToggleButton tglbtnFiltrar;
		private JButton btPagamento;
		private JButton btSaida;

		public CRUDServicos(final ILaboratorio lab) {
			setBounds(100, 100, 1366, 768);
			setLayout(null);

			JSeparator separator1 = new JSeparator();
			separator1.setBounds(0, 137, 1460, 2);
			add(separator1);

			JPanel headPanel = new JPanel();
			headPanel.setBackground(Color.WHITE);
			headPanel.setBounds(0, 0, 1368, 137);
			add(headPanel);
			headPanel.setLayout(null);

			JToolBar toolBar = new JToolBar();
			toolBar.setFloatable(false);
			toolBar.setBounds(25, 94, 333, 28);
			headPanel.add(toolBar);

			JMenuBar menuBar = new JMenuBar();
			toolBar.add(menuBar);

			JMenuItem mntmServios = new JMenuItem("Servi\u00E7os");
			mntmServios.setForeground(Color.BLUE);
			menuBar.add(mntmServios);

			final JMenuItem mntmTiposDeServios = new JMenuItem("Tipos de servi\u00E7os");
			mntmTiposDeServios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			mntmTiposDeServios.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent arg0) {
					mntmTiposDeServios.setForeground(Color.GRAY);
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					mntmTiposDeServios.setForeground(Color.BLACK);
				}
			});
			mntmTiposDeServios.setForeground(Color.BLACK);
			mntmTiposDeServios.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
//					cardTipo();

				}
			});
			menuBar.add(mntmTiposDeServios);

			JButton btBack = new JButton("");
			btBack.setIcon(
					new ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/back.png")))));
			btBack.setHorizontalAlignment(SwingConstants.CENTER);
			btBack.setFocusPainted(false);
			btBack.setContentAreaFilled(false);
			btBack.setBorderPainted(false);
			btBack.setBounds(0, 5, 64, 40);
			btBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
//					cardInicio();
				}
			});
			headPanel.add(btBack);

			JLabel lblServicos = new JLabel("Servi\u00E7os");
			lblServicos.setForeground(new Color(102, 102, 255));
			lblServicos.setFont(lblServicos.getFont().deriveFont(lblServicos.getFont().getStyle() | Font.BOLD, 54f));
			lblServicos.setBounds(25, 26, 351, 66);
			headPanel.add(lblServicos);
			ButtonGroup filtro = new ButtonGroup();
			final JPopupMenu popupMenu = new JPopupMenu();

			rdbtnmntmTipo = new JRadioButtonMenuItem("Tipo", true);
			popupMenu.add(rdbtnmntmTipo);
			rdbtnmntmTipo.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					filtrar(0);
				}
			});

			rdbtnmntmDescricao = new JRadioButtonMenuItem("Descri��o", true);
			popupMenu.add(rdbtnmntmDescricao);
			rdbtnmntmDescricao.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					filtrar(1);
				}
			});

			rdbtnmntmSituacao = new JRadioButtonMenuItem("Situa��o", true);
			popupMenu.add(rdbtnmntmSituacao);
			rdbtnmntmSituacao.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					filtrar(2);
				}
			});

			rdbtnmntmPreco = new JRadioButtonMenuItem("Pre�o", false);
			popupMenu.add(rdbtnmntmPreco);
			rdbtnmntmPreco.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					filtrar(3);
				}
			});

			rdbtnmntmEntrada = new JRadioButtonMenuItem("Data de entrada", true);
			popupMenu.add(rdbtnmntmEntrada);
			rdbtnmntmEntrada.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					filtrar(4);
				}
			});

			rdbtnmntmSaida = new JRadioButtonMenuItem("Data de sa�da", true);
			popupMenu.add(rdbtnmntmSaida);
			rdbtnmntmSaida.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					filtrar(5);
				}
			});

			rdbtnmntmCliente = new JRadioButtonMenuItem("Cliente", true);
			popupMenu.add(rdbtnmntmCliente);
			rdbtnmntmCliente.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					filtrar(6);
				}
			});

			rdbtnmntmFunc = new JRadioButtonMenuItem("Funcion�rio", true);
			popupMenu.add(rdbtnmntmFunc);
			rdbtnmntmFunc.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					filtrar(7);
				}
			});

			rdbtnmntmPaciente = new JRadioButtonMenuItem("Paciente", true);
			popupMenu.add(rdbtnmntmPaciente);
			rdbtnmntmPaciente.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					filtrar(8);
				}
			});

			handler = new RadioButtonHandler();

			filtro.add(rdbtnmntmTipo);
			filtro.add(rdbtnmntmDescricao);
			filtro.add(rdbtnmntmSituacao);
			filtro.add(rdbtnmntmPreco);
			filtro.add(rdbtnmntmEntrada);
			filtro.add(rdbtnmntmSaida);
			filtro.add(rdbtnmntmCliente);
			filtro.add(rdbtnmntmPaciente);
			filtro.add(rdbtnmntmFunc);

			rdbtnmntmPreco.addItemListener(handler);
			rdbtnmntmTipo.addItemListener(handler);

			JPanel tablePanel = new JPanel();
			tablePanel.setBounds(10, 139, 1348, 501);
			add(tablePanel);
			tablePanel.setLayout(null);

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 37, 1338, 464);
			tablePanel.add(scrollPane);

			tbServicos = new JTable();
			tbServicos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			tbServicos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scrollPane.setViewportView(tbServicos);
			tbServicos.getTableHeader().setReorderingAllowed(false);
			tbServicos.setAutoCreateRowSorter(true);

			stm = new ServicosTableModel(lab.getArrayServicos());
			tbServicos.setModel(stm);

			JLabel lblPesquisar = new JLabel("Pesquisar:");
			lblPesquisar.setBounds(10, 10, 77, 17);
			tablePanel.add(lblPesquisar);
			lblPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 16));

			txtPesquisa = new JTextField();
			txtPesquisa.setBounds(97, 7, 446, 21);
			txtPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 16));
			txtPesquisa.setColumns(10);
			txtPesquisa.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent arg0) {
					getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			});
			
			yearChooser = new JYearChooser();
			btnFiltrar.setBounds(553, 10, 85, 21);
			tablePanel.add(btnFiltrar);
			
			monthChooser = new JMonthChooser();
			monthChooser.setBounds(654, 10, 93, 19);
			tablePanel.add(monthChooser);
			
			tglbtnFiltrar = new JToggleButton("Filtrar");
			tglbtnFiltrar.setBounds(1232, 10, 106, 21);
			tablePanel.add(tglbtnFiltrar);
			tglbtnFiltrar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(tglbtnFiltrar.isSelected()) {
						yearChooser.setEnabled(false);
						monthChooser.setEnabled(false);
						int mes = monthChooser.getMonth();
						String ano = String.valueOf(yearChooser.getYear());
						SimpleDateFormat mesFormat = new SimpleDateFormat("MM");
						SimpleDateFormat anoFormat = new SimpleDateFormat("yyyy");
						ArrayList<Servicos> s = lab.getArrayServicos();
						ArrayList<Servicos> array = new ArrayList<Servicos>();
						if (s != null) {
							if (s.size()>0) {
								for (int i = 0; i < s.size(); i++) {
									if ( (Integer.parseInt(mesFormat.format(s.get(i).getEntrada())) - 1) == mes &&
											anoFormat.format(s.get(i).getEntrada()).equals(ano)) {
										array.add(s.get(i));
									}
								}
							}
						}
						stm.updateTable(array);
					} else {
						yearChooser.setEnabled(true);
						monthChooser.setEnabled(true);
						stm.updateTable(lab.getArrayServicos());
					}
				}
			});
			
			btnFiltrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					popupMenu.show(btnFiltrar, btnFiltrar.getWidth(), btnFiltrar.getHeight());
				}
			});
			txtPesquisa.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent evt) {
					pesquisar(evt);
				}
			});
			tbServicos.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if (tbServicos.getSelectedRow() != -1) {
						btnExcluir.setEnabled(true);
						btnAlterar.setEnabled(true);
						btnExtrato.setEnabled(true);
					} else {
						btnExcluir.setEnabled(false);
						btnAlterar.setEnabled(false);
						btnExtrato.setEnabled(false);
					}
				}
			});
			tbServicos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

				@Override
				public void valueChanged(ListSelectionEvent e) {
					if (tbServicos.getSelectedRow() != -1) {
						btnExcluir.setEnabled(true);
						btnAlterar.setEnabled(true);
						btnExtrato.setEnabled(true);
					} else {
						btnExcluir.setEnabled(false);
						btnAlterar.setEnabled(false);
						btnExtrato.setEnabled(false);
					}
				}
			});

			JPanel bottomPanel = new JPanel();
			bottomPanel.setBounds(0, 650, 1348, 64);
			add(bottomPanel);
			bottomPanel.setLayout(null);

			btnExcluir = new JButton("Excluir");
			btnExcluir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (tbServicos.getSelectedRow() != -1) {
						int rowSel = tbServicos.getSelectedRow();
						int indexRowModel;
						if (tbServicos.getRowSorter() == null)
							indexRowModel = tbServicos.getSelectedRow();
						else
							indexRowModel = tbServicos.getRowSorter().convertRowIndexToModel(rowSel);
						int opcao = JOptionPane.showConfirmDialog(
								CRUDServicos.this, "Excluir", "Tem certeza que deseja excluir "
										+ stm.getServicos(indexRowModel).getTipo().getNome() + "?",
								JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (opcao == JOptionPane.OK_OPTION) {
							if (lab.excluirServico(stm.getServicos(indexRowModel).getCod())) {
								JOptionPane.showMessageDialog(CRUDServicos.this,
										"Servi�o exclu�do com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
								stm.updateTable(lab.getArrayServicos());
							} else {
								JOptionPane.showMessageDialog(CRUDServicos.this,
										"Ocorreu um erro no sistema de exclus�o", "Algo deu errado",
										JOptionPane.ERROR_MESSAGE);
							}
							stm.updateTable(lab.getArrayServicos());
						}
					} else {
						JOptionPane.showMessageDialog(CRUDServicos.this, "Por favor selecione uma linha",
								"N�o foi poss�vel excluir", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
			btnExcluir.setBounds(273, 10, 85, 21);
			bottomPanel.add(btnExcluir);
			btnExcluir.setEnabled(false);

			btnAlterar = new JButton("Alterar");
			btnAlterar.setBounds(182, 10, 85, 21);
			bottomPanel.add(btnAlterar);
			btnAlterar.setEnabled(false);
			
			btPagamento = new JButton("Registrar pagamento");
			btPagamento.setEnabled(false);
			btPagamento.setBounds(992, 10, 125, 21);
			bottomPanel.add(btPagamento);
			btPagamento.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					int rowSel = tbServicos.getSelectedRow();
					int indexRowModel;
					if (tbServicos.getRowSorter() == null)
						indexRowModel = tbServicos.getSelectedRow();
					else
						indexRowModel = tbServicos.getRowSorter().convertRowIndexToModel(rowSel);
					Servicos s = stm.getServicos(indexRowModel);
					if (lab.setPago(s.getCod(), true)) {
						JOptionPane.showMessageDialog(null, "Pagamento do servi�o registrado com sucesso", "Registrar pagamento", JOptionPane.PLAIN_MESSAGE);
						stm.updateTable(lab.getArrayServicos());
					} else {
						JOptionPane.showMessageDialog(null, "Ocorreu um problema ao registrar o pagamento", "Registrar pagamento", JOptionPane.PLAIN_MESSAGE);
					}
				}
			});

			
			btSaida = new JButton("Registrar sa\u00EDda");
			btSaida.setEnabled(false);
			btSaida.setBounds(857, 10, 125, 21);
			bottomPanel.add(btSaida);
			btSaida.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					int rowSel = tbServicos.getSelectedRow();
					int indexRowModel;
					if (tbServicos.getRowSorter() == null)
						indexRowModel = tbServicos.getSelectedRow();
					else
						indexRowModel = tbServicos.getRowSorter().convertRowIndexToModel(rowSel);
					Servicos s = stm.getServicos(indexRowModel);
					JLabel lb = new JLabel("Selecione como o servi�o saiu: ");
					JComboBox cb = new JComboBox();
					cb.addItem("PROVA");
					cb.addItem("FINALIZADO");
					JPanel p = new JPanel();
					p.add(lb);
					p.add(cb);
					JOptionPane.showMessageDialog(null,p, "Registrar sa�da", JOptionPane.PLAIN_MESSAGE);
					lab.alterServico(s.getCod(), s.isPagamento(), (String) cb.getSelectedItem(), s.getDescricao(), s.getValorFinal(), s.getCliente(), s.getEntrada(), s.getSaida(), s.getFuncionario(), s.getPaciente(), s.getTipo());
					JOptionPane.showMessageDialog(null, "Sa�da registrada com sucesso", "Registrar sa�da", JOptionPane.PLAIN_MESSAGE);
					stm.updateTable(lab.getArrayServicos());
				}
			});

			
			btnExtrato = new JButton("Extrato");
			btnExtrato.setBounds(1253, 10, 85, 21);
			bottomPanel.add(btnExtrato);
			btnExtrato.setEnabled(false);
			btnExtrato.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					int rowSel = tbServicos.getSelectedRow();
					int indexRowModel;
					if (tbServicos.getRowSorter() == null)
						indexRowModel = tbServicos.getSelectedRow();
					else
						indexRowModel = tbServicos.getRowSorter().convertRowIndexToModel(rowSel);
					Servicos s = stm.getServicos(indexRowModel);
					
				}
			});

			JButton btnNovo = new JButton("+ Novo");
			btnNovo.setBounds(87, 10, 85, 21);
			bottomPanel.add(btnNovo);

			btnNovo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					GUICreateUpdateServico novo = new GUICreateUpdateServico(-1, lab);
					novo.setVisible(true);
					stm.updateTable(lab.getArrayServicos());
				}
			});

			JSeparator separator = new JSeparator();
			separator.setBounds(10, 639, 1449, 75);
			add(separator);
			btnAlterar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (tbServicos.getSelectedRow() != -1) {
						int rowSel = tbServicos.getSelectedRow();
						int indexRowModel;
						if (tbServicos.getRowSorter() == null)
							indexRowModel = tbServicos.getSelectedRow();
						else
							indexRowModel = tbServicos.getRowSorter().convertRowIndexToModel(rowSel);

						GUICreateUpdateServico alterar = new GUICreateUpdateServico(indexRowModel, lab);
						alterar.setVisible(true);
						stm.updateTable(lab.getArrayServicos());
					} else {
						JOptionPane.showMessageDialog(CRUDServicos.this, "Por favor selecione uma linha",
								"N�o foi poss�vel alterar", JOptionPane.WARNING_MESSAGE);
					}
				}
			});

		}

		private void pesquisar(java.awt.event.KeyEvent evt) {
			final TableRowSorter<ServicosTableModel> sorter = new TableRowSorter<ServicosTableModel>(stm);
			tbServicos.setRowSorter(sorter);
			String text = txtPesquisa.getText();
			if (text.length() == 0) {
				sorter.setRowFilter(null);
			} else {
				try {
					sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, filtro));
				} catch (PatternSyntaxException pse) {
					JOptionPane.showMessageDialog(CRUDServicos.this, "Erro",
							"Ocorreu um erro durante a pesquisa", JOptionPane.ERROR_MESSAGE);
					txtPesquisa.setText("");
				}
			}
		}

		private class RadioButtonHandler implements ItemListener {

			@Override
			public void itemStateChanged(ItemEvent event) {
				String coluna = null;
				if (rdbtnmntmTipo.isSelected())
					coluna = "NOME";
				if (rdbtnmntmPreco.isSelected())
					coluna = "PRE�O";
				for (int i = 0; i < stm.getColumnCount(); i++) {
					if (stm.getColumnName(i).equals(coluna))
						filtro = i;
				}
			}
		}

		private void filtrar(int i) {
			filtro = i;
		}
	}
