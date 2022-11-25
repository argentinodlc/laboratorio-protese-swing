package ui.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

import sistema.Laboratorio;
import sistema.classes.Cliente;
import sistema.classes.Clinica;
import sistema.classes.Compra;
import sistema.classes.Dentista;
import sistema.classes.Despesa;
import sistema.classes.Funcionario;
import sistema.classes.Servicos;
import sistema.interfaces.ILaboratorio;
import ui.gui.relatorios.FuncRelatorio;
import ui.gui.relatorios.RelatorioGastos;
import ui.gui.relatorios.RelatorioExtrato;
import ui.gui.relatorios.RelatorioFuncionario;
import ui.gui.relatorios.RelatorioServico;
import ui.gui.relatorios.ResumoFinanceiro;
import ui.tables.ClinicaTableModel;
import ui.tables.CompraTableModel;
import ui.tables.DentistaTableModel;
import ui.tables.DespesaFixaTableModel;
import ui.tables.DespesaTableModel;
import ui.tables.FornTableModel;
import ui.tables.FuncionarioTableModel;
import ui.tables.PacienteTableModel;
import ui.tables.ProdutoTableModel;
import ui.tables.ServicosTableModel;
import ui.tables.TipoTableModel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import java.awt.Toolkit;

public class Principal {
	private ILaboratorio lab;
	public JFrame frame;
	private GUIReadDeleteClinica cp;
	private GUIReadDeleteDentista dp;
	private GUIReadDeletePaciente pp;
	private GUIReadDeleteFuncionario fp;
	private GUIReadDeleteTipoServicos tp;
	private GUIReadDeleteServicos sp;
	private TelaFinanceiro tf;
	private TelaRelatorios tr;
	private TelaInicio ti;
	private TelaCliente tc;
	private TelaCompras tcompras;
	private TelaProdutos tprodutos;
	private TelaFornecedores tforn;
	private TelaDespesa tdespesa;
	private TelaDespesaFixa tdespesaFixa;
	private TelaResumo tresumo;
	private Login login;
	private JPanel cards;
	private JMenu mnServios;
	private JMenuItem mntmNovoServio;
	private JMenuItem mntmTodosTiposDe;
	private JMenu mnRelatrios;
	private JMenuItem mntmRelatriosMensais;
	private JMenuItem mntmExtratosMensais;
	private JMenu mnDespesas;
	private JMenuItem mntmVerTodasDespesas;
	private JMenuItem mntmDespesasFixas;
	private JMenu mnClientes;
	private JMenuItem mntmVerDentistas;
	private JMenuItem mntmVerClnicas;
	private JMenuItem mntmVerPacientes;
	private JMenu mnCompras;
	private JMenuItem mntmVerTodasAs;
	private JMenuItem mntmTodosOsFornecedores;
	private JMenuItem mntmTodosProdutos;
	private JMenuItem mntmTodosFuncionrios;
	private JMenu mnSistema;
	private JMenuItem mntmIncio;
	private JMenuItem mntmMudarSenha;
	private JMenuItem mntmSair;
	private FuncionarioTableModel ftm;
	private PacienteTableModel ptm;
	private DentistaTableModel dtm;
	private ClinicaTableModel ctm;
	private ServicosTableModel stm;
	private TipoTableModel ttm;
	private CompraTableModel compratm;
	private ProdutoTableModel produtotm;
	private FornTableModel forntm;
	private DespesaTableModel despesatm;
	private JSeparator separator_3;
	private DespesaFixaTableModel despesafixatm;
	private ResumoFinanceiro rf = new ResumoFinanceiro();
	private JMenuBar menuBar;
	private JMenuItem mntmInformarProblema;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void cardInicio() {
		CardLayout cl = (CardLayout) cards.getLayout();
		cl.show(cards, "inicio");
	}

	private void cardCliente() {
		CardLayout cl = (CardLayout) cards.getLayout();
		cl.show(cards, "cliente");
	}

	private void cardDentista() {
		CardLayout cl = (CardLayout) cards.getLayout();
		cl.show(cards, "dentista");
		dtm.updateTable(lab.getArrayDentistas());
	}

	private void cardClinica() {
		CardLayout cl = (CardLayout) cards.getLayout();
		cl.show(cards, "clinica");
		ctm.updateTable(lab.getClinicas());
	}

	private void cardPaciente() {
		CardLayout cl = (CardLayout) cards.getLayout();
		cl.show(cards, "paciente");
		ptm.updateTable(lab.getPacientes());
	}

	private void cardFuncionario() {
		CardLayout cl = (CardLayout) cards.getLayout();
		cl.show(cards, "funcionario");
		ftm.updateTable(lab.getArrayFunc());
	}

	private void cardServico() {
		CardLayout cl = (CardLayout) cards.getLayout();
		cl.show(cards, "servico");
		stm.updateTable(lab.getArrayServicos());
	}

	private void cardTipo() {
		CardLayout cl = (CardLayout) cards.getLayout();
		cl.show(cards, "tipo");
		ttm.updateTable(lab.getArrayTipos());
	}

	private void cardFinanceiro() {
		CardLayout cl = (CardLayout) cards.getLayout();
		cl.show(cards, "financeiro");
	}

	private void cardCompra() {
		CardLayout cl = (CardLayout) cards.getLayout();
		cl.show(cards, "compra");
		compratm.updateTable(lab.getArrayCompras());
	}

	private void cardProduto() {
		CardLayout cl = (CardLayout) cards.getLayout();
		cl.show(cards, "produto");
		produtotm.updateTable(lab.getArrayProdutos());
	}

	private void cardForn() {
		CardLayout cl = (CardLayout) cards.getLayout();
		cl.show(cards, "forn");
		forntm.updateTable(lab.getArrayFornecedores());
	}

	private void cardDespesa() {
		CardLayout cl = (CardLayout) cards.getLayout();
		cl.show(cards, "despesa");
		despesatm.updateTable(lab.getArrayDespesa());
	}

	private void cardDespesaFixa() {
		CardLayout cl = (CardLayout) cards.getLayout();
		cl.show(cards, "despesaFixa");
		despesafixatm.updateTable(lab.getArrayDespesaFixa());
	}

	private void cardResumo() {
		CardLayout cl = (CardLayout) cards.getLayout();
		cl.show(cards, "resumo");
	}

	private void cardRelatorio() {
		CardLayout cl = (CardLayout) cards.getLayout();
		cl.show(cards, "relatorio");
	}

	public Principal() {
		initialize();
	}

	private void initialize() {
		lab = Laboratorio.getInstance();
		try {
			lab.carregar();
		} catch (IndexOutOfBoundsException | IOException | ParseException e1) {
			JOptionPane.showMessageDialog(null, "Ocorreu um problema ao tentar carregar os arquivos", "Aviso",
					JOptionPane.WARNING_MESSAGE);
		}
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/icon.png")));
		frame.setBounds(100, 100, 1366, 768);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Sistema Dent Art");
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				int botao = JOptionPane.YES_NO_OPTION;
				if (JOptionPane.showConfirmDialog(null, "Deseja sair?", "Sair", botao,
						JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
					try {
						lab.escrever();
					} catch (IndexOutOfBoundsException | IOException e) {
						JOptionPane.showMessageDialog(null, "Ocorreu um problema ao salvar arquivos", "Aviso",
								JOptionPane.WARNING_MESSAGE);
					}
					System.exit(0);
				}
			}
		});

		cards = new JPanel();
		frame.getContentPane().add(cards, BorderLayout.CENTER);
		cards.setLayout(new CardLayout(0, 0));

		tr = new TelaRelatorios(lab);
		cards.add(tr, "relatorio");
		cp = new GUIReadDeleteClinica(lab);
		cards.add(cp, "clinica");
		dp = new GUIReadDeleteDentista(lab);
		cards.add(dp, "dentista");
		pp = new GUIReadDeletePaciente(lab);
		cards.add(pp, "paciente");
		fp = new GUIReadDeleteFuncionario(lab);
		cards.add(fp, "funcionario");
		tp = new GUIReadDeleteTipoServicos(lab);
		cards.add(tp, "tipo");
		ti = new TelaInicio();
		cards.add(ti, "inicio");
		tc = new TelaCliente();
		cards.add(tc, "cliente");
		sp = new GUIReadDeleteServicos(lab);
		cards.add(sp, "servico");
		tf = new TelaFinanceiro();
		cards.add(tf, "financeiro");
		tcompras = new TelaCompras(lab);
		cards.add(tcompras, "compra");
		tprodutos = new TelaProdutos(lab);
		cards.add(tprodutos, "produto");
		tforn = new TelaFornecedores(lab);
		cards.add(tforn, "forn");
		tdespesa = new TelaDespesa(lab);
		cards.add(tdespesa, "despesa");
		tdespesaFixa = new TelaDespesaFixa(lab);
		cards.add(tdespesaFixa, "despesaFixa");
		tresumo = new TelaResumo(lab);
		cards.add(tresumo, "resumo");
		try {
			login = new Login();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Ocorreu um erro com o sistema de login, por favor tente reinstalar o programa!",
					"Erro no sistema de login", JOptionPane.ERROR_MESSAGE);
		}
		cards.add(login, "login");
		CardLayout cl = (CardLayout) cards.getLayout();
		cl.show(cards, "login");

		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		menuBar.setVisible(false);

		mnSistema = new JMenu("Sistema");
		menuBar.add(mnSistema);

		mntmIncio = new JMenuItem("In\u00EDcio");
		mntmIncio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardInicio();
			}
		});
		mnSistema.add(mntmIncio);

		mntmMudarSenha = new JMenuItem("Mudar senha");
		mntmMudarSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					login.ChangePasswd();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null,
							"Ocorreu um erro com o sistema de login, por favor tente reinstalar o programa!",
							"Erro no sistema de login", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnSistema.add(mntmMudarSenha);

		mntmInformarProblema = new JMenuItem("Informar problema");
		mntmInformarProblema.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				try {
					URLConnection connection = new URL("http://www.google.com.br").openConnection();
					connection.connect();
					JLabel lbl = new JLabel("Por favor, descreva o problema ocorrido de forma detalhada");
					lbl.setHorizontalAlignment(SwingConstants.CENTER);
					JTextArea tpDesc = new JTextArea();
					tpDesc.setWrapStyleWord(true);
					tpDesc.setLineWrap(true);
					tpDesc.setBorder(new LineBorder(Color.BLACK));
					JPanel panel = new JPanel(new BorderLayout());
					panel.add(lbl, BorderLayout.NORTH);
					panel.add(tpDesc, BorderLayout.CENTER);
					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setViewportView(panel);
					scrollPane.setPreferredSize(new Dimension(450, 135));
					if (JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(null, scrollPane, "Informar problema",
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE)) {
						Email.sentEmail(tpDesc.getText());
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "� necess�rio estar conectado � internet enviar uma mensagem",
							"Informar problema", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnSistema.add(mntmInformarProblema);

		separator_3 = new JSeparator();
		mnSistema.add(separator_3);

		mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int botao = JOptionPane.YES_NO_OPTION;
				if (JOptionPane.showConfirmDialog(null, "Deseja sair?", "Sair", botao,
						JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
					try {
						lab.escrever();
					} catch (IndexOutOfBoundsException | IOException e) {
						JOptionPane.showMessageDialog(null, "Ocorreu um problema ao salvar arquivos", "Aviso",
								JOptionPane.WARNING_MESSAGE);
					}
					System.exit(0);
				}
			}
		});

		mnSistema.add(mntmSair);

		mnServios = new JMenu("Servi\u00E7os");
		menuBar.add(mnServios);

		mntmNovoServio = new JMenuItem("Gerenciar servi\u00E7os");
		mntmNovoServio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardServico();
			}
		});
		mnServios.add(mntmNovoServio);

		mntmTodosTiposDe = new JMenuItem("Gerenciar tipos de servi\u00E7os");
		mntmTodosTiposDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardTipo();
			}
		});
		mnServios.add(mntmTodosTiposDe);

		mntmTodosFuncionrios = new JMenuItem("Gerenciar funcion\u00E1rios");
		mnServios.add(mntmTodosFuncionrios);
		mntmTodosFuncionrios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardFuncionario();
			}
		});

		mnRelatrios = new JMenu("Relat\u00F3rios");
		menuBar.add(mnRelatrios);

		mntmRelatriosMensais = new JMenuItem("Relat\u00F3rios mensais");
		mntmRelatriosMensais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardRelatorio();
				tr.carregarCb();
			}
		});
		mnRelatrios.add(mntmRelatriosMensais);

		mntmExtratosMensais = new JMenuItem("Resumo financeiro");
		mntmExtratosMensais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardResumo();
			}
		});
		mnRelatrios.add(mntmExtratosMensais);

		mnClientes = new JMenu("Clientes");
		menuBar.add(mnClientes);

		mntmVerDentistas = new JMenuItem("Gerenciar dentistas");
		mnClientes.add(mntmVerDentistas);
		mntmVerDentistas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardDentista();
			}
		});

		mntmVerClnicas = new JMenuItem("Gerenciar cl\u00EDnicas");
		mnClientes.add(mntmVerClnicas);
		mntmVerClnicas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardClinica();
			}
		});

		mntmVerPacientes = new JMenuItem("Gerenciar pacientes");
		mnClientes.add(mntmVerPacientes);
		mntmVerPacientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardPaciente();
			}
		});

		mnDespesas = new JMenu("Despesas");
		menuBar.add(mnDespesas);

		mntmVerTodasDespesas = new JMenuItem("Gerenciar despesas");
		mntmVerTodasDespesas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardDespesa();
			}
		});
		mnDespesas.add(mntmVerTodasDespesas);

		mntmDespesasFixas = new JMenuItem("Gerenciar despesas fixas");
		mntmDespesasFixas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardDespesaFixa();
			}
		});
		mnDespesas.add(mntmDespesasFixas);

		mnCompras = new JMenu("Compras");
		menuBar.add(mnCompras);

		mntmVerTodasAs = new JMenuItem("Gerenciar compras");
		mntmVerTodasAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardCompra();
			}
		});
		mnCompras.add(mntmVerTodasAs);

		mntmTodosOsFornecedores = new JMenuItem("Gerenciar fornecedores");
		mntmTodosOsFornecedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardForn();
			}
		});

		mntmTodosProdutos = new JMenuItem("Gerenciar produtos");
		mntmTodosProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardProduto();
			}
		});
		mnCompras.add(mntmTodosProdutos);
		mnCompras.add(mntmTodosOsFornecedores);
	}

	private class GUIReadDeleteTipoServicos extends JPanel {
		private JTable tbTipo;
		private JTextField txtPesquisa;
		private JButton btnExcluir;
		private JButton btnAlterar;
		private JRadioButtonMenuItem rdbtnmntmNome;
		private RadioButtonHandler handler;
		private int filtro = 0;
		private JRadioButtonMenuItem rdbtnmntmPreco;

		public GUIReadDeleteTipoServicos(final ILaboratorio lab) {
			setBounds(100, 100, 1366, 768);
			setLayout(null);

			JSeparator separator1 = new JSeparator();
			separator1.setBounds(0, 137, 1460, 622);
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

			final JMenuItem mntmServios = new JMenuItem("Servi\u00E7os");
			mntmServios.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent arg0) {
					mntmServios.setForeground(Color.LIGHT_GRAY);
					getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					mntmServios.setForeground(Color.black);
					getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			});
			mntmServios.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					cardServico();
				}
			});
			menuBar.add(mntmServios);

			JMenuItem mntmTiposDeServios = new JMenuItem("Tipos de servi\u00E7os");
			mntmTiposDeServios.setForeground(Color.BLUE);
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
					cardInicio();
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

			rdbtnmntmNome = new JRadioButtonMenuItem("Nome", true);
			popupMenu.add(rdbtnmntmNome);

			rdbtnmntmPreco = new JRadioButtonMenuItem("Preco", false);
			popupMenu.add(rdbtnmntmPreco);

			handler = new RadioButtonHandler();

			filtro.add(rdbtnmntmNome);
			filtro.add(rdbtnmntmPreco);

			rdbtnmntmPreco.addItemListener(handler);
			rdbtnmntmNome.addItemListener(handler);

			JPanel tablePanel = new JPanel();
			tablePanel.setBounds(10, 139, 1348, 501);
			add(tablePanel);
			tablePanel.setLayout(null);

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 78, 1338, 329);
			tablePanel.add(scrollPane);

			tbTipo = new JTable();
			tbTipo.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			tbTipo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scrollPane.setViewportView(tbTipo);
			tbTipo.getTableHeader().setReorderingAllowed(false);
			tbTipo.setAutoCreateRowSorter(true);

			ttm = new TipoTableModel(lab.getArrayTipos());
			tbTipo.setModel(ttm);

			JLabel lblPesquisar = new JLabel("Pesquisar:");
			lblPesquisar.setBounds(25, 51, 77, 17);
			tablePanel.add(lblPesquisar);
			lblPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 16));

			txtPesquisa = new JTextField();
			txtPesquisa.setBounds(105, 47, 446, 21);
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
			tablePanel.add(txtPesquisa);

			final JButton btnFiltrar = new JButton("Filtrar");
			btnFiltrar.setBounds(553, 47, 85, 21);
			tablePanel.add(btnFiltrar);

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
			tbTipo.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if (tbTipo.getSelectedRow() != -1) {
						btnExcluir.setEnabled(true);
						btnAlterar.setEnabled(true);
					} else {
						btnExcluir.setEnabled(false);
						btnAlterar.setEnabled(false);
					}
				}
			});
			tbTipo.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

				@Override
				public void valueChanged(ListSelectionEvent e) {
					if (tbTipo.getSelectedRow() != -1) {
						btnExcluir.setEnabled(true);
						btnAlterar.setEnabled(true);
					} else {
						btnExcluir.setEnabled(false);
						btnAlterar.setEnabled(false);
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
					if (tbTipo.getSelectedRow() != -1) {
						int rowSel = tbTipo.getSelectedRow();
						int indexRowModel;
						if (tbTipo.getRowSorter() == null)
							indexRowModel = tbTipo.getSelectedRow();
						else
							indexRowModel = tbTipo.getRowSorter().convertRowIndexToModel(rowSel);
						if (ttm.getTipoServico(indexRowModel).getCod() == 1) {
							JOptionPane.showMessageDialog(GUIReadDeleteTipoServicos.this,
									"N�o � poss�vel excluir Roach", "A��o n�o permitida", JOptionPane.ERROR_MESSAGE);
						} else {
							int opcao = JOptionPane.showConfirmDialog(GUIReadDeleteTipoServicos.this,
									"Tem certeza que deseja excluir " + ttm.getTipoServico(indexRowModel).getNome()
											+ "?",
									"Excluir tipo de servi�o", JOptionPane.OK_CANCEL_OPTION,
									JOptionPane.QUESTION_MESSAGE);
							if (opcao == JOptionPane.OK_OPTION) {
								if (lab.excluirTipoServico(ttm.getTipoServico(indexRowModel).getCod())) {
									JOptionPane.showMessageDialog(GUIReadDeleteTipoServicos.this,
											"Tipo de servi�o exclu�do com sucesso", "Excluir tipo de servi�o",
											JOptionPane.INFORMATION_MESSAGE);
								} else {
									JOptionPane.showMessageDialog(GUIReadDeleteTipoServicos.this,
											"Ocorreu um erro no sistema de exclus�o", "Excluir tipo de servi�o",
											JOptionPane.ERROR_MESSAGE);
								}
								ttm.updateTable(lab.getArrayTipos());
							}
						}
					} else {
						JOptionPane.showMessageDialog(GUIReadDeleteTipoServicos.this, "N�o foi poss�vel excluir",
								"Por favor selecione uma linha", JOptionPane.WARNING_MESSAGE);
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

			JButton btnNovo = new JButton("+ Novo");
			btnNovo.setBounds(87, 10, 85, 21);
			bottomPanel.add(btnNovo);

			JSeparator separator = new JSeparator();
			separator.setBounds(10, 639, 1449, 75);
			add(separator);

			btnNovo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					GUICreateUpdateTipoServicos novo = new GUICreateUpdateTipoServicos(-1, lab);
					novo.setVisible(true);
					ttm.updateTable(lab.getArrayTipos());
				}
			});
			btnAlterar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (tbTipo.getSelectedRow() != -1) {
						int rowSel = tbTipo.getSelectedRow();
						int indexRowModel;
						if (tbTipo.getRowSorter() == null)
							indexRowModel = tbTipo.getSelectedRow();
						else
							indexRowModel = tbTipo.getRowSorter().convertRowIndexToModel(rowSel);

						GUICreateUpdateTipoServicos alterar = new GUICreateUpdateTipoServicos(indexRowModel, lab);
						alterar.setVisible(true);
						ttm.updateTable(lab.getArrayTipos());
					} else {
						JOptionPane.showMessageDialog(GUIReadDeleteTipoServicos.this, "Por favor selecione uma linha",
								"N�o foi poss�vel alterar", JOptionPane.WARNING_MESSAGE);
					}
				}
			});

		}

		private void pesquisar(java.awt.event.KeyEvent evt) {
			final TableRowSorter<TipoTableModel> sorter = new TableRowSorter<TipoTableModel>(ttm);
			tbTipo.setRowSorter(sorter);
			String text = txtPesquisa.getText();
			if (text.length() == 0) {
				sorter.setRowFilter(null);
			} else {
				try {
					sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, filtro));
				} catch (PatternSyntaxException pse) {
					JOptionPane.showMessageDialog(GUIReadDeleteTipoServicos.this, "Erro",
							"Ocorreu um erro durante a pesquisa", JOptionPane.ERROR_MESSAGE);
					txtPesquisa.setText("");
				}
			}
		}

		private class RadioButtonHandler implements ItemListener {

			@Override
			public void itemStateChanged(ItemEvent event) {
				String coluna = null;
				if (rdbtnmntmNome.isSelected())
					coluna = "NOME";
				if (rdbtnmntmPreco.isSelected())
					coluna = "PRE�O";
				for (int i = 0; i < ttm.getColumnCount(); i++) {
					if (ttm.getColumnName(i).equals(coluna))
						filtro = i;
				}
			}
		}
	}

	private class TelaInicio extends JPanel {
		private JLabel lblClock;
		private JLabel lblData;

		private TelaInicio() {
			setBounds(100, 100, 1366, 768);
			setLayout(null);
			JPanel panel = new JPanel();
			panel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
			panel.setBackground(new Color(255, 255, 255));
			panel.setBounds(0, 0, 1368, 176);
			add(panel);
			panel.setLayout(null);
			Timer timer = new Timer(1000, new ClockListener());
			timer.start();

			JLabel lblSistema = new JLabel("Sistema");
			lblSistema.setFont(new Font("Century Gothic", Font.PLAIN, 20));
			lblSistema.setBounds(16, 18, 74, 26);
			panel.add(lblSistema);

			JLabel lblDen = new JLabel("DEN");
			lblDen.setForeground(new Color(0, 0, 0));
			lblDen.setBounds(10, 31, 147, 91);
			panel.add(lblDen);
			lblDen.setFont(new Font("Century Gothic", Font.PLAIN, 65));

			JLabel lblAr = new JLabel("AR");
			lblAr.setForeground(new Color(0, 0, 0));
			lblAr.setBounds(63, 87, 97, 91);
			panel.add(lblAr);
			lblAr.setFont(new Font("Century Gothic", Font.PLAIN, 65));

			JLabel lblT = new JLabel("T");
			lblT.setForeground(new Color(0, 0, 0));
			lblT.setBounds(122, -21, 232, 225);
			panel.add(lblT);
			lblT.setFont(new Font("Dialog", Font.PLAIN, 185));
			lblClock = new JLabel();
			lblClock.setHorizontalAlignment(SwingConstants.RIGHT);
			lblClock.setBounds(1047, 147, 292, 19);
			panel.add(lblClock);

			lblData = new JLabel();
			lblData.setHorizontalAlignment(SwingConstants.RIGHT);
			lblData.setBounds(1217, 127, 121, 19);
			panel.add(lblData);

			JButton btnClientes = new JButton("");
			btnClientes.setIcon(
					new ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/customer.png")))));
			btnClientes.setBounds(707, 270, 256, 256);
			add(btnClientes);
			btnClientes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cardCliente();
				}
			});
			btnClientes.setFocusPainted(false);
			btnClientes.setContentAreaFilled(false);
			btnClientes.setBorderPainted(false);
			btnClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

			JLabel lblClientes = new JLabel("Clientes e");
			lblClientes.setFont(new Font("Century Gothic", Font.PLAIN, 41));
			lblClientes.setBounds(738, 506, 225, 72);
			add(lblClientes);

			JButton btnServicos = new JButton("");
			btnServicos.setIcon(
					new ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/services.png")))));
			btnServicos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cardServico();
				}
			});
			btnServicos.setBounds(101, 270, 256, 256);
			add(btnServicos);
			btnServicos.setFocusPainted(false);
			btnServicos.setContentAreaFilled(false);
			btnServicos.setBorderPainted(false);
			btnServicos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

			JLabel lblServios = new JLabel("Servi\u00E7os");
			lblServios.setFont(new Font("Century Gothic", Font.PLAIN, 41));
			lblServios.setBounds(151, 517, 256, 51);
			add(lblServios);

			JLabel lblFuncionrios = new JLabel("Funcion\u00E1rios");
			lblFuncionrios.setFont(new Font("Century Gothic", Font.PLAIN, 41));
			lblFuncionrios.setBounds(717, 546, 256, 72);
			add(lblFuncionrios);

			JButton btnFinanceiros = new JButton("");
			btnFinanceiros.setIcon(new ImageIcon(
					(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/financial.png")))));
			btnFinanceiros.setBounds(998, 270, 256, 256);
			btnFinanceiros.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					cardFinanceiro();
				}
			});
			btnFinanceiros.setFocusPainted(false);
			btnFinanceiros.setContentAreaFilled(false);
			btnFinanceiros.setBorderPainted(false);
			btnFinanceiros.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			add(btnFinanceiros);

			JLabel lblFinanceiro = new JLabel("Financeiro");
			lblFinanceiro.setFont(new Font("Century Gothic", Font.PLAIN, 41));
			lblFinanceiro.setBounds(1024, 517, 214, 51);
			add(lblFinanceiro);

			JButton btRelatorios = new JButton("");
			btRelatorios.setIcon(
					new ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/report.png")))));
			btRelatorios.setBounds(404, 270, 256, 256);
			btRelatorios.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					cardRelatorio();
					tr.carregarCb();

				}
			});
			btRelatorios.setFocusPainted(false);
			btRelatorios.setContentAreaFilled(false);
			btRelatorios.setBorderPainted(false);
			btRelatorios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			add(btRelatorios);

			JLabel lblRelatrios = new JLabel("Relat\u00F3rios");
			lblRelatrios.setFont(new Font("Century Gothic", Font.PLAIN, 41));
			lblRelatrios.setBounds(431, 506, 256, 72);
			add(lblRelatrios);

		}

		private class ClockListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				Calendar now = Calendar.getInstance();
				String saudacoes = null;
				Date primeiro = new Date();
				primeiro.setHours(5);
				primeiro.setMinutes(59);
				primeiro.setSeconds(59);
				Date segundo = new Date();
				segundo.setHours(12);
				segundo.setMinutes(0);
				segundo.setSeconds(0);
				Date terceiro = new Date();
				terceiro.setHours(11);
				terceiro.setMinutes(59);
				terceiro.setSeconds(59);
				Date quarto = new Date();
				quarto.setHours(18);
				quarto.setMinutes(0);
				quarto.setSeconds(0);
				Date quinto = new Date();
				quinto.setHours(17);
				quinto.setMinutes(59);
				quinto.setSeconds(59);
				Date sexto = new Date();
				sexto.setDate(sexto.getDate() + 1);
				sexto.setHours(0);
				sexto.setMinutes(0);
				sexto.setSeconds(0);
				Date setimo = new Date();
				setimo.setDate(setimo.getDate() - 1);
				setimo.setHours(23);
				setimo.setMinutes(59);
				setimo.setSeconds(59);
				Date oitavo = new Date();
				oitavo.setDate(oitavo.getDate() + 1);
				oitavo.setHours(6);
				oitavo.setMinutes(0);
				oitavo.setSeconds(0);

				if (now.getTime().after(primeiro) && now.getTime().before(segundo))
					saudacoes = "Bom dia, administrador!";
				else if (now.getTime().after(terceiro) && now.getTime().before(quarto))
					saudacoes = "Boa tarde, administrador!";
				else if (now.getTime().after(quinto) && now.getTime().before(sexto))
					saudacoes = "Boa noite, administrador!";
				else if (now.getTime().after(setimo) && now.getTime().before(oitavo))
					saudacoes = "Boa madrugada, administrador!";
				lblClock.setText(saudacoes + " " + String.format("%1$tH:%1$tM:%1$tS", now));
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				lblData.setText(sdf.format(now.getTime()));
			}
		}
	}

	private class TelaCliente extends JPanel {

		public TelaCliente() {
			setBounds(0, 0, 1366, 768);
			setLayout(null);

			JPanel headPanel = new JPanel();
			headPanel.setBackground(Color.WHITE);
			headPanel.setBounds(0, 0, 1368, 137);
			add(headPanel);
			headPanel.setLayout(null);

			JButton btBack = new JButton("");
			btBack.setBounds(0, 5, 64, 40);
			headPanel.add(btBack);
			btBack.setFocusPainted(false);
			btBack.setContentAreaFilled(false);
			btBack.setBorderPainted(false);
			btBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cardInicio();
				}
			});
			btBack.setIcon(
					new ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/back.png")))));

			JLabel lblClientesEFuncionrios = new JLabel("Clientes e Funcion\u00E1rios");

			lblClientesEFuncionrios.setBounds(27, 34, 696, 54);
			headPanel.add(lblClientesEFuncionrios);
			lblClientesEFuncionrios.setForeground(new Color(102, 102, 255));
			lblClientesEFuncionrios.setFont(lblClientesEFuncionrios.getFont()
					.deriveFont(lblClientesEFuncionrios.getFont().getStyle() | Font.BOLD, 54f));

			JPanel panel = new JPanel();
			panel.setBounds(0, 0, 1368, 759);
			add(panel);
			panel.setLayout(null);

			JButton btDentista = new JButton("");
			btDentista.setIcon(
					new ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/dentist.png")))));
			btDentista.setFocusPainted(false);
			btDentista.setBounds(112, 259, 256, 256);
			btDentista.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cardDentista();
				}
			});
			btDentista.setFocusPainted(false);
			btDentista.setContentAreaFilled(false);
			btDentista.setBorderPainted(false);
			btDentista.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			panel.add(btDentista);

			JButton btClinica = new JButton("");
			btClinica.setIcon(
					new ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/clinic.png")))));
			btClinica.setFocusPainted(false);
			btClinica.setBounds(408, 259, 256, 256);
			btClinica.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cardClinica();
				}
			});
			btClinica.setFocusPainted(false);
			btClinica.setContentAreaFilled(false);
			btClinica.setBorderPainted(false);
			btClinica.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			panel.add(btClinica);

			JButton btPaciente = new JButton("");
			btPaciente.setIcon(
					new ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/patient.png")))));
			btPaciente.setFocusPainted(false);
			btPaciente.setBounds(704, 259, 256, 256);
			btPaciente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cardPaciente();
				}
			});
			btPaciente.setFocusPainted(false);
			btPaciente.setContentAreaFilled(false);
			btPaciente.setBorderPainted(false);
			btPaciente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			panel.add(btPaciente);

			JButton btFuncionario = new JButton("");
			btFuncionario.setIcon(
					new ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/employee.png")))));
			btFuncionario.setFocusPainted(false);
			btFuncionario.setBounds(1000, 259, 256, 256);
			btFuncionario.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cardFuncionario();
				}
			});
			btFuncionario.setFocusPainted(false);
			btFuncionario.setContentAreaFilled(false);
			btFuncionario.setBorderPainted(false);
			btFuncionario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			panel.add(btFuncionario);

			JLabel label = new JLabel("Funcion\u00E1rios");
			label.setFont(new Font("Century Gothic", Font.PLAIN, 41));
			label.setBounds(1002, 500, 256, 72);
			panel.add(label);

			JLabel lblPacientes = new JLabel("Pacientes");
			lblPacientes.setFont(new Font("Century Gothic", Font.PLAIN, 41));
			lblPacientes.setBounds(734, 500, 256, 72);
			panel.add(lblPacientes);

			JLabel lblClnicas = new JLabel("Cl\u00EDnicas");
			lblClnicas.setFont(new Font("Century Gothic", Font.PLAIN, 41));
			lblClnicas.setBounds(457, 500, 256, 72);
			panel.add(lblClnicas);

			JLabel lblDentistas = new JLabel("Dentistas");
			lblDentistas.setFont(new Font("Century Gothic", Font.PLAIN, 41));
			lblDentistas.setBounds(155, 500, 256, 72);
			panel.add(lblDentistas);

			JSeparator separator = new JSeparator();
			separator.setBounds(0, 137, 1460, 622);
			panel.add(separator);
		}
	}

	private class GUIReadDeleteFuncionario extends JPanel {
		private JTable tbFunc;
		private JTextField txtPesquisa;
		private JButton btnExcluir;
		private JButton btnAlterar;
		private JRadioButtonMenuItem rdbtnmntmNome;
		private RadioButtonHandler handler;
		private int filtro = 0;
		private JRadioButtonMenuItem rdbtnmntmComissao;
		private JRadioButtonMenuItem rdbtnmntmCPF;

		public GUIReadDeleteFuncionario(final ILaboratorio lab) {
			setBounds(100, 100, 1366, 768);
			setLayout(null);

			JSeparator separator1 = new JSeparator();
			separator1.setBounds(0, 137, 1460, 622);
			add(separator1);

			JPanel headPanel = new JPanel();
			headPanel.setBackground(Color.WHITE);
			headPanel.setBounds(0, 0, 1368, 137);
			add(headPanel);
			headPanel.setLayout(null);

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
					cardCliente();
				}
			});
			headPanel.add(btBack);

			JLabel lblFunc = new JLabel("Funcion\u00E1rios");
			lblFunc.setForeground(new Color(102, 102, 255));
			lblFunc.setFont(lblFunc.getFont().deriveFont(lblFunc.getFont().getStyle() | Font.BOLD, 54f));
			lblFunc.setBounds(27, 34, 399, 54);
			headPanel.add(lblFunc);
			ButtonGroup filtro = new ButtonGroup();
			final JPopupMenu popupMenu = new JPopupMenu();

			rdbtnmntmNome = new JRadioButtonMenuItem("Nome", true);
			popupMenu.add(rdbtnmntmNome);

			rdbtnmntmCPF = new JRadioButtonMenuItem("CPF", false);
			popupMenu.add(rdbtnmntmCPF);

			rdbtnmntmComissao = new JRadioButtonMenuItem("Comiss�o", false);
			popupMenu.add(rdbtnmntmComissao);

			handler = new RadioButtonHandler();

			filtro.add(rdbtnmntmNome);
			filtro.add(rdbtnmntmComissao);
			filtro.add(rdbtnmntmCPF);

			rdbtnmntmCPF.addItemListener(handler);
			rdbtnmntmComissao.addItemListener(handler);
			rdbtnmntmNome.addItemListener(handler);

			JPanel tablePanel = new JPanel();
			tablePanel.setBounds(10, 139, 1348, 501);
			add(tablePanel);
			tablePanel.setLayout(null);

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 78, 1338, 329);
			tablePanel.add(scrollPane);

			tbFunc = new JTable();
			tbFunc.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			tbFunc.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scrollPane.setViewportView(tbFunc);
			tbFunc.getTableHeader().setReorderingAllowed(true);

			ftm = new FuncionarioTableModel(lab.getArrayFunc());
			tbFunc.setModel(ftm);
			tbFunc.setAutoCreateRowSorter(true);

			JLabel lblPesquisar = new JLabel("Pesquisar:");
			lblPesquisar.setBounds(25, 51, 77, 17);
			tablePanel.add(lblPesquisar);
			lblPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 16));

			txtPesquisa = new JTextField();
			txtPesquisa.setBounds(105, 47, 446, 21);
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
			tablePanel.add(txtPesquisa);

			final JButton btnFiltrar = new JButton("Filtrar");
			btnFiltrar.setBounds(553, 47, 85, 21);
			tablePanel.add(btnFiltrar);

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
			tbFunc.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if (tbFunc.getSelectedRow() != -1) {
						btnExcluir.setEnabled(true);
						btnAlterar.setEnabled(true);
					} else {
						btnExcluir.setEnabled(false);
						btnAlterar.setEnabled(false);
					}
				}
			});
			tbFunc.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

				@Override
				public void valueChanged(ListSelectionEvent e) {
					if (tbFunc.getSelectedRow() != -1) {
						btnExcluir.setEnabled(true);
						btnAlterar.setEnabled(true);
					} else {
						btnExcluir.setEnabled(false);
						btnAlterar.setEnabled(false);
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
					if (tbFunc.getSelectedRow() != -1) {
						int rowSel = tbFunc.getSelectedRow();
						int indexRowModel;
						if (tbFunc.getRowSorter() == null)
							indexRowModel = tbFunc.getSelectedRow();
						else
							indexRowModel = tbFunc.getRowSorter().convertRowIndexToModel(rowSel);
						int opcao = JOptionPane.showConfirmDialog(GUIReadDeleteFuncionario.this, "Excluir",
								"Tem certeza que deseja excluir " + ftm.getFunc(indexRowModel).getNome() + "?",
								JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (opcao == JOptionPane.OK_OPTION) {
							if (lab.excluirFuncionario(ftm.getFunc(indexRowModel).getCod())) {
								JOptionPane.showMessageDialog(GUIReadDeleteFuncionario.this,
										"Funcion�rio exclu�do com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(GUIReadDeleteFuncionario.this,
										"Ocorreu um erro no sistema de exclus�o", "Erro", JOptionPane.ERROR_MESSAGE);
							}
							ftm.updateTable(lab.getArrayFunc());
						}
					} else {
						JOptionPane.showMessageDialog(GUIReadDeleteFuncionario.this, "Por favor selecione uma linha",
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

			JButton btnNovo = new JButton("+ Novo");
			btnNovo.setBounds(87, 10, 85, 21);
			bottomPanel.add(btnNovo);

			JSeparator separator = new JSeparator();
			separator.setBounds(10, 639, 1449, 75);
			add(separator);

			btnNovo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					GUICreateUpdateFuncionario novo = new GUICreateUpdateFuncionario(-1, lab);
					novo.setVisible(true);
					ftm.updateTable(lab.getArrayFunc());
				}
			});
			btnAlterar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (tbFunc.getSelectedRow() != -1) {
						int rowSel = tbFunc.getSelectedRow();
						int indexRowModel;
						if (tbFunc.getRowSorter() == null)
							indexRowModel = tbFunc.getSelectedRow();
						else
							indexRowModel = tbFunc.getRowSorter().convertRowIndexToModel(rowSel);

						GUICreateUpdateFuncionario alterar = new GUICreateUpdateFuncionario(indexRowModel, lab);
						alterar.setVisible(true);
						ftm.updateTable(lab.getArrayFunc());
					} else {
						JOptionPane.showMessageDialog(GUIReadDeleteFuncionario.this, "Por favor selecione uma linha",
								"N�o foi poss�vel alterar", JOptionPane.WARNING_MESSAGE);
					}
				}
			});

		}

		private void pesquisar(java.awt.event.KeyEvent evt) {
			final TableRowSorter<FuncionarioTableModel> sorter = new TableRowSorter<FuncionarioTableModel>(ftm);
			tbFunc.setRowSorter(sorter);
			String text = txtPesquisa.getText();
			if (text.length() == 0) {
				sorter.setRowFilter(null);
			} else {
				try {
					sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, filtro));
				} catch (PatternSyntaxException pse) {
					JOptionPane.showMessageDialog(GUIReadDeleteFuncionario.this, "Erro",
							"Ocorreu um erro durante a pesquisa", JOptionPane.ERROR_MESSAGE);
					txtPesquisa.setText("");
				}
			}
		}

		private class RadioButtonHandler implements ItemListener {

			@Override
			public void itemStateChanged(ItemEvent event) {
				String coluna = null;
				if (rdbtnmntmNome.isSelected())
					coluna = "NOME";
				if (rdbtnmntmCPF.isSelected())
					coluna = "CPF";
				if (rdbtnmntmComissao.isSelected())
					coluna = "COMISS�O";
				for (int i = 0; i < ftm.getColumnCount(); i++) {
					if (ftm.getColumnName(i).equals(coluna))
						filtro = i;
				}
			}
		}
	}

	private class TelaRelatorios extends JPanel {
		private ILaboratorio lab;
		private int filtro = 0;
		private DespesaTableModel despesatm;
		private JMonthChooser monthChooser;
		private JYearChooser yearChooser;
		private JButton btCompras;
		private JButton btFuncionario;
		private JButton btInterno;
		private JCheckBox chckPgmto;
		private JCheckBox chckSaida;
		private JCheckBox chckFinalizado;
		private JRadioButton rbParticular;
		private JRadioButton rbConvenio;
		private JComboBox comboBox;
		private JCheckBox chckbxPagamentoRecebido;

		public TelaRelatorios(final ILaboratorio lab) {
			this.lab = lab;
			setBounds(0, 0, 1366, 768);
			setLayout(null);

			JPanel headPanel = new JPanel();
			headPanel.setBackground(Color.WHITE);
			headPanel.setBounds(0, 0, 1368, 137);
			add(headPanel);
			headPanel.setLayout(null);

			JLabel lbResumo = new JLabel("Relat\u00F3rios");
			lbResumo.setBounds(27, 45, 634, 66);
			headPanel.add(lbResumo);
			lbResumo.setForeground(new Color(102, 102, 255));
			lbResumo.setFont(new Font("Tahoma", Font.BOLD, 54));

			JButton btBack = new JButton("");
			btBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cardInicio();
				}
			});
			btBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btBack.setBounds(0, 5, 64, 40);
			headPanel.add(btBack);
			btBack.setFocusPainted(false);
			btBack.setContentAreaFilled(false);
			btBack.setBorderPainted(false);
			btBack.setIcon(
					new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/back.png"))));

			JSeparator separator_1 = new JSeparator();
			separator_1.setBounds(0, 136, 1419, 1);
			headPanel.add(separator_1);

			JPanel tablePanel = new JPanel();
			tablePanel.setBounds(0, 136, 1368, 623);
			add(tablePanel);
			tablePanel.setLayout(null);

			ButtonGroup gp = new ButtonGroup();

			despesatm = new DespesaTableModel(lab.getArrayDespesa());

			JLabel lblFuncionrios = new JLabel("Funcion\u00E1rios");
			lblFuncionrios.setForeground(Color.DARK_GRAY);
			lblFuncionrios.setFont(new Font("MS PGothic", Font.PLAIN, 33));
			lblFuncionrios.setBounds(743, 251, 186, 48);
			tablePanel.add(lblFuncionrios);
			lblFuncionrios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

			JLabel lblDespesas = new JLabel("Despesas");
			lblDespesas.setForeground(Color.DARK_GRAY);
			lblDespesas.setFont(new Font("MS PGothic", Font.PLAIN, 45));
			lblDespesas.setBounds(1058, 247, 186, 48);
			tablePanel.add(lblDespesas);
			lblDespesas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

			JLabel lblInterno = new JLabel("Interno");
			lblInterno.setForeground(Color.DARK_GRAY);
			lblInterno.setFont(new Font("MS PGothic", Font.PLAIN, 45));
			lblInterno.setBounds(424, 247, 186, 48);
			tablePanel.add(lblInterno);
			lblInterno.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

			JLabel lblCliente = new JLabel("Cliente");
			lblCliente.setForeground(Color.DARK_GRAY);
			lblCliente.setFont(new Font("MS PGothic", Font.PLAIN, 45));
			lblCliente.setBounds(100, 247, 186, 48);
			tablePanel.add(lblCliente);
			lblCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

			JPanel panelPeriodo = new JPanel();
			panelPeriodo.setBounds(10, 10, 210, 54);
			tablePanel.add(panelPeriodo);
			panelPeriodo.setBorder(
					new TitledBorder(null, "Per\u00EDodo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelPeriodo.setLayout(null);

			monthChooser = new JMonthChooser();
			monthChooser.setBounds(10, 20, 190, 22);
			panelPeriodo.add(monthChooser);

			yearChooser = new JYearChooser();
			yearChooser.setBounds(136, 23, 46, 19);
			panelPeriodo.add(yearChooser);

			btInterno = new JButton("");
			btInterno.addActionListener(new ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent arg0) {
					int qtd = 0;
					ArrayList<Servicos> s = lab.getArrayServicos();
					ArrayList<Servicos> servicos = new ArrayList<Servicos>();
					SimpleDateFormat sdf = new SimpleDateFormat("MM");
					SimpleDateFormat ano = new SimpleDateFormat("yyyy");
					if (s != null) {
						if (s.size() > 0) {

							boolean finalizado = chckFinalizado.isSelected();
							boolean saida = chckSaida.isSelected();
							boolean pgmto = chckPgmto.isSelected();
							boolean pago = chckbxPagamentoRecebido.isSelected();

							for (int i = 0; i < s.size(); i++) {
								boolean test = true;
								if (pago) {
									if (!(s.get(i).isPago())) {
										test = false;
									}
								}

								if (finalizado) {
									if (!(s.get(i).getSituacao().equals("FINALIZADO"))) {
										test = false;
									}
								}
								if (pgmto) {
									if (s.get(i).isPagamento() == false)
										test = false;
								}
								if (test) {
									if (saida) {
										if (s.get(i).getSituacao().equals("FINALIZADO")
												| s.get(i).getSituacao().equals("PROVA")) {
											if ((Integer.parseInt(sdf.format(s.get(i).getSaida())) - 1) == monthChooser
													.getMonth()
													&& ((Integer
															.parseInt(ano.format(s.get(i).getSaida()))) == yearChooser
																	.getYear())) {
												qtd = qtd + 1;
												servicos.add(s.get(i));
											}
										}
									} else {
										if ((Integer.parseInt(sdf.format(s.get(i).getEntrada())) - 1) == monthChooser
												.getMonth()
												&& ((Integer.parseInt(ano.format(s.get(i).getEntrada()))) == yearChooser
														.getYear())) {
											qtd = qtd + 1;
											servicos.add(s.get(i));
										}
									}
								}
							}
							if (qtd > 0) {
								RelatorioExtrato r = new RelatorioExtrato();
								int modo;
								if (chckSaida.isSelected())
									modo = 1;
								else
									modo = 2;
								r.relatorio(servicos, modo);
							} else {
								JOptionPane.showMessageDialog(TelaRelatorios.this,
										"N�o foi encontrado nenhum servi�o no per�odo especificado",
										"N�o foi poss�vel gerar relat�rio", JOptionPane.WARNING_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(TelaRelatorios.this,
									"N�o foi encontrado nenhum servi�o no sistema", "N�o foi poss�vel gerar relat�rio",
									JOptionPane.WARNING_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(TelaRelatorios.this,
								"N�o foi encontrado nenhum servi�o no sistema", "N�o foi poss�vel gerar relat�rio",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			});
			btInterno.setFocusPainted(false);
			btInterno.setContentAreaFilled(false);
			btInterno.setBorderPainted(false);
			btInterno.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btInterno.setVisible(true);
			btInterno.setBounds(390, 150, 256, 256);
			tablePanel.add(btInterno);
			ImageIcon imgCustomer = (new ImageIcon(
					Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/pdf.png"))));
			Image img = imgCustomer.getImage().getScaledInstance(256, 256, java.awt.Image.SCALE_DEFAULT);
			btInterno.setIcon(new ImageIcon(img));

			JLabel lblRelatrioInterno = new JLabel("Relat\u00F3rio interno");
			lblRelatrioInterno.setFont(new Font("Century Gothic", Font.PLAIN, 21));
			lblRelatrioInterno.setBounds(436, 404, 210, 27);
			tablePanel.add(lblRelatrioInterno);

			btFuncionario = new JButton("");
			btFuncionario.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ArrayList<Funcionario> f = lab.getArrayFunc();
					if (f != null) {
						if (f.size() > 0) {
							ArrayList<Servicos> s = lab.getArrayServicos();
							if (s != null) {
								if (s.size() > 0) {
									int qtd = 0;
									ArrayList<FuncRelatorio> array = new ArrayList<FuncRelatorio>();
									SimpleDateFormat mes = new SimpleDateFormat("MM");
									SimpleDateFormat ano = new SimpleDateFormat("yyyy");

									boolean finalizado = chckFinalizado.isSelected();
									boolean saida = chckSaida.isSelected();
									boolean pgmto = chckPgmto.isSelected();
									boolean pago = chckbxPagamentoRecebido.isSelected();

									for (int i = 0; i < f.size(); i++) {
										double c = 0;
										int q = 0;
										for (int j = 0; j < s.size(); j++) {
											boolean test = true;
											if (pago) {
												if (!(s.get(i).isPago())) {
													test = false;
												}
											}

											if (finalizado) {
												if (!(s.get(j).getSituacao().equals("FINALIZADO"))) {
													test = false;
												}
											}
											if (pgmto) {
												if (s.get(j).isPagamento() == false)
													test = false;
											}
											if (test) {
												if (saida) {
													if (s.get(j).getSituacao().equals("FINALIZADO")
															|| s.get(j).getSituacao().equals("PROVA")) {
														if ((Integer.parseInt(mes.format(s.get(j).getSaida()))
																- 1) == monthChooser.getMonth()
																&& ((Integer.parseInt(
																		ano.format(s.get(j).getSaida()))) == yearChooser
																				.getYear())) {
															if (s.get(j).getFuncionario().getCod() == f.get(i)
																	.getCod()) {
																qtd = qtd + 1;
																if (s.get(j).getTipo().getCod() == 1) {
																	c = c + (s.get(j).getTipo().getValorComissao()
																			* (f.get(i).getComissao() / 100));
																} else {
																	c = c + (s.get(j).getValorFinal()
																			* (f.get(i).getComissao() / 100));
																}
																q = q + 1;
															}
														}

													}
												} else {
													if ((Integer.parseInt(mes.format(s.get(j).getEntrada()))
															- 1) == monthChooser.getMonth()
															&& (Integer.parseInt(
																	ano.format(s.get(j).getEntrada()))) == yearChooser
																			.getYear()) {
														if (s.get(j).getFuncionario().getCod() == f.get(i).getCod()) {
															qtd = qtd + 1;
															if (s.get(j).getTipo().getCod() == 1) {
																c = c + (s.get(j).getTipo().getValorComissao()
																		* (f.get(i).getComissao() / 100));
															} else {
																c = c + (s.get(j).getValorFinal()
																		* (f.get(i).getComissao() / 100));
															}
															q = q + 1;
														}
													}
												}
											}

										}
										array.add(new FuncRelatorio(f.get(i), c, q));
									}
									if (qtd > 0) {
										RelatorioFuncionario rf = new RelatorioFuncionario();
										rf.relatorio(array);

									} else {
										JOptionPane.showMessageDialog(TelaRelatorios.this,
												"N�o foi encontrado nenhum funcion�rio que realizou servi�os nesse per�odo",
												"N�o foi poss�vel gerar relat�rio", JOptionPane.WARNING_MESSAGE);
									}
								} else {
									JOptionPane.showMessageDialog(TelaRelatorios.this,
											"N�o foi encontrado nenhum servi�o no sistema",
											"N�o foi poss�vel gerar relat�rio", JOptionPane.WARNING_MESSAGE);
								}
							} else {
								JOptionPane.showMessageDialog(TelaRelatorios.this,
										"N�o foi encontrado nenhum servi�o no sistema",
										"N�o foi poss�vel gerar relat�rio", JOptionPane.WARNING_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(TelaRelatorios.this,
									"N�o foi encontrado nenhum funcion�rio no sistema",
									"N�o foi poss�vel gerar relat�rio", JOptionPane.WARNING_MESSAGE);
						}
					} else {

						JOptionPane.showMessageDialog(TelaRelatorios.this,
								"N�o foi encontrado nenhum funcion�rio no sistema", "N�o foi poss�vel gerar relat�rio",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			});
			btFuncionario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btFuncionario.setFocusPainted(false);
			btFuncionario.setContentAreaFilled(false);
			btFuncionario.setBorderPainted(false);
			btFuncionario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btFuncionario.setBounds(711, 150, 256, 256);
			tablePanel.add(btFuncionario);
			btFuncionario.setIcon(new ImageIcon(img));

			JLabel lblRelatrioFuncionrios = new JLabel("Relat\u00F3rio de funcion\u00E1rios");
			lblRelatrioFuncionrios.setFont(new Font("Century Gothic", Font.PLAIN, 21));
			lblRelatrioFuncionrios.setBounds(711, 404, 256, 27);
			tablePanel.add(lblRelatrioFuncionrios);

			btCompras = new JButton("");
			btCompras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ArrayList<Compra> c = lab.getArrayCompras();
					ArrayList<Compra> compras = null;
					ArrayList<Despesa> f = lab.getArrayDespesaFixa();
					ArrayList<Despesa> fixas = null;
					ArrayList<Despesa> d = lab.getArrayDespesa();
					ArrayList<Despesa> despesas = null;
					ArrayList<Funcionario> func = lab.getArrayFunc();
					ArrayList<Servicos> s = lab.getArrayServicos();
					ArrayList<FuncRelatorio> array = null;
					boolean temCompra;
					boolean temFixa;
					boolean temDespesa;
					boolean temFunc;
					boolean temServ = false;

					if (func != null) {
						if (func.size() > 0) {
							temFunc = true;
							if (s != null) {
								if (s.size() > 0) {
									temServ = true;
								} else {
									JOptionPane.showMessageDialog(TelaRelatorios.this,
											"N�o foi encontrado nenhum servi�o no sistema", "Aviso",
											JOptionPane.WARNING_MESSAGE);
									temServ = false;
								}
							} else {
								JOptionPane.showMessageDialog(TelaRelatorios.this,
										"N�o foi encontrado nenhum servi�o no sistema", "Aviso",
										JOptionPane.WARNING_MESSAGE);
								temServ = false;
							}
						} else {
							JOptionPane.showMessageDialog(TelaRelatorios.this,
									"N�o foi encontrado nenhum funcion�rio no sistema", "Aviso",
									JOptionPane.WARNING_MESSAGE);
							temFunc = false;
						}
					} else {
						JOptionPane.showMessageDialog(TelaRelatorios.this,
								"N�o foi encontrado nenhum funcion�rio no sistema", "Aviso",
								JOptionPane.WARNING_MESSAGE);
						temFunc = false;
					}

					if (c == null) {
						JOptionPane.showMessageDialog(TelaRelatorios.this,
								"N�o foi encontrado nenhuma compra no sistema", "Aviso", JOptionPane.WARNING_MESSAGE);
						temCompra = false;
					} else {
						if (c.size() == 0) {
							JOptionPane.showMessageDialog(TelaRelatorios.this,
									"N�o foi encontrado nenhuma compra no sistema", "Aviso",
									JOptionPane.WARNING_MESSAGE);
							temCompra = false;
						} else {
							temCompra = true;
						}
					}
					if (f == null) {
						JOptionPane.showMessageDialog(TelaRelatorios.this,
								"N�o foi encontrado nenhuma despesa fixa no sistema", "Aviso",
								JOptionPane.WARNING_MESSAGE);
						temFixa = false;
					} else {
						if (f.size() == 0) {
							JOptionPane.showMessageDialog(TelaRelatorios.this,
									"N�o foi encontrado nenhuma despesa fixa no sistema", "Aviso",
									JOptionPane.WARNING_MESSAGE);
							temFixa = false;
						} else {
							temFixa = true;
						}
					}
					if (d == null) {
						JOptionPane.showMessageDialog(TelaRelatorios.this,
								"N�o foi encontrado nenhuma despesa no sistema", "Aviso", JOptionPane.WARNING_MESSAGE);
						temDespesa = false;
					} else {
						if (d.size() == 0) {
							JOptionPane.showMessageDialog(TelaRelatorios.this,
									"N�o foi encontrado nenhuma despesa no sistema", "Aviso",
									JOptionPane.WARNING_MESSAGE);
							temDespesa = false;
						} else {
							temDespesa = true;
						}
					}
					if (temCompra == false && temDespesa == false && temFixa == false && temServ == false
							&& temFunc == false) {
					} else {
						if (temCompra) {
							compras = new ArrayList<Compra>();
							int qtd = 0;
							for (int i = 0; i < c.size(); i++) {
								SimpleDateFormat mes = new SimpleDateFormat("MM");
								SimpleDateFormat ano = new SimpleDateFormat("yyyy");
								if ((Integer.valueOf(mes.format(c.get(i).getData())) - 1) == monthChooser.getMonth()
										&& Integer.valueOf(ano.format(c.get(i).getData())) == yearChooser.getYear()) {
									qtd = qtd + 1;
									compras.add(c.get(i));
								}
							}
							if (qtd > 0) {
							} else {
								JOptionPane.showMessageDialog(TelaRelatorios.this,
										"N�o foi encontrado nenhuma compra nesse per�odo", "Aviso",
										JOptionPane.WARNING_MESSAGE);
							}
						}
						if (temDespesa) {
							despesas = new ArrayList<Despesa>();
							int qtd = 0;
							for (int i = 0; i < d.size(); i++) {
								SimpleDateFormat mes = new SimpleDateFormat("MM");
								SimpleDateFormat ano = new SimpleDateFormat("yyyy");
								if ((Integer.valueOf(mes.format(d.get(i).getData())) - 1) == monthChooser.getMonth()
										&& Integer.valueOf(ano.format(d.get(i).getData())) == yearChooser.getYear()) {
									qtd = qtd + 1;
									despesas.add(d.get(i));
								}
							}
							if (qtd > 0) {
							} else {
								JOptionPane.showMessageDialog(TelaRelatorios.this,
										"N�o foi encontrado nenhuma despesa nesse per�odo", "Aviso",
										JOptionPane.WARNING_MESSAGE);
							}
						}

						if (temFixa) {
							fixas = f;
						}

						if (temFunc && temServ) {
							int qtd = 0;

							SimpleDateFormat mes = new SimpleDateFormat("MM");
							SimpleDateFormat ano = new SimpleDateFormat("yyyy");

							boolean finalizado = chckFinalizado.isSelected();
							boolean saida = chckSaida.isSelected();
							boolean pgmto = chckPgmto.isSelected();
							boolean pago = chckbxPagamentoRecebido.isSelected();

							for (int i = 0; i < func.size(); i++) {
								double comissao = 0;
								int q = 0;
								for (int j = 0; j < s.size(); j++) {
									boolean test = true;
									if (pago) {
										if (!(s.get(i).isPago())) {
											test = false;
										}
									}

									if (finalizado) {
										if (!(s.get(j).getSituacao().equals("FINALIZADO"))) {
											test = false;
										}
									}
									if (pgmto) {
										if (s.get(j).isPagamento() == false)
											test = false;
									}
									if (test) {
										if (saida) {
											if (s.get(j).getSituacao().equals("FINALIZADO")
													|| s.get(j).getSituacao().equals("PROVA")) {
												if ((Integer.parseInt(mes.format(s.get(j).getSaida()))
														- 1) == monthChooser.getMonth()
														&& ((Integer.parseInt(
																ano.format(s.get(j).getSaida()))) == yearChooser
																		.getYear())) {
													if (s.get(j).getFuncionario().getCod() == func.get(i).getCod()) {
														if (array == null) {
															array = new ArrayList<FuncRelatorio>();
														}
														qtd = qtd + 1;
														if (s.get(j).getTipo().getCod() == 1) {
															comissao = comissao + (s.get(j).getTipo().getValorComissao()
																	* (func.get(i).getComissao() / 100));
														} else {
															comissao = comissao + (s.get(j).getValorFinal()
																	* (func.get(i).getComissao() / 100));
														}
														q = q + 1;
													}
												}

											}
										} else {
											if ((Integer.parseInt(mes.format(s.get(j).getEntrada()))
													- 1) == monthChooser.getMonth()
													&& (Integer
															.parseInt(ano.format(s.get(j).getEntrada()))) == yearChooser
																	.getYear()) {
												if (s.get(j).getFuncionario().getCod() == func.get(i).getCod()) {
													if (array == null) {
														array = new ArrayList<FuncRelatorio>();
													}
													qtd = qtd + 1;
													if (s.get(j).getTipo().getCod() == 1) {
														comissao = comissao + (s.get(j).getTipo().getValorComissao()
																* (func.get(i).getComissao() / 100));
													} else {
														comissao = comissao + (s.get(j).getValorFinal()
																* (func.get(i).getComissao() / 100));
													}
													q = q + 1;
												}
											}
										}
									}

								}
								array.add(new FuncRelatorio(func.get(i), comissao, q));
							}
						}

						RelatorioGastos rg = new RelatorioGastos();
						rg.relatorio(compras, despesas, fixas, array);

					}
				}
			});
			btCompras.setFocusPainted(false);
			btCompras.setContentAreaFilled(false);
			btCompras.setBorderPainted(false);
			btCompras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btCompras.setBounds(1027, 150, 256, 256);
			tablePanel.add(btCompras);
			btCompras.setIcon(new ImageIcon(img));

			JLabel lblRelatrioDeCompras = new JLabel("Relat\u00F3rio de despesas");
			lblRelatrioDeCompras.setFont(new Font("Century Gothic", Font.PLAIN, 21));
			lblRelatrioDeCompras.setBounds(1048, 404, 256, 27);
			tablePanel.add(lblRelatrioDeCompras);

			JPanel panelServico = new JPanel();
			panelServico.setLayout(null);
			panelServico.setBorder(new TitledBorder(
					new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
					"Servi\u00E7os", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelServico.setBounds(230, 10, 737, 54);
			tablePanel.add(panelServico);

			chckPgmto = new JCheckBox("Pagamento cobrado");
			chckPgmto.setSelected(true);
			chckPgmto.setFont(new Font("Tahoma", Font.PLAIN, 15));
			chckPgmto.setBounds(171, 21, 163, 21);
			panelServico.add(chckPgmto);

			chckSaida = new JCheckBox("Servi\u00E7o teve sa\u00EDda");
			chckSaida.setSelected(true);
			chckSaida.setFont(new Font("Tahoma", Font.PLAIN, 15));
			chckSaida.setBounds(561, 21, 163, 21);
			panelServico.add(chckSaida);

			chckFinalizado = new JCheckBox("Servi\u00E7o finalizado");
			chckFinalizado.setSelected(true);
			chckFinalizado.setFont(new Font("Tahoma", Font.PLAIN, 15));
			chckFinalizado.setBounds(6, 23, 163, 21);
			panelServico.add(chckFinalizado);

			chckbxPagamentoRecebido = new JCheckBox("Pagamento recebido");
			chckbxPagamentoRecebido.setSelected(true);
			chckbxPagamentoRecebido.setFont(new Font("Tahoma", Font.PLAIN, 15));
			chckbxPagamentoRecebido.setBounds(366, 21, 163, 21);
			panelServico.add(chckbxPagamentoRecebido);

			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(977, 10, 306, 54);
			tablePanel.add(panel);
			panel.setLayout(null);

			comboBox = new JComboBox();
			comboBox.setBounds(10, 23, 181, 21);
			panel.add(comboBox);

			rbParticular = new JRadioButton("Particular");
			rbParticular.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					carregarCb();
				}
			});
			rbParticular.setBounds(197, 10, 103, 21);
			rbParticular.setSelected(true);
			panel.add(rbParticular);

			rbConvenio = new JRadioButton("Conv\u00EAnio");
			rbConvenio.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					carregarCb();
				}
			});
			rbConvenio.setBounds(197, 27, 103, 21);
			panel.add(rbConvenio);

			ButtonGroup rbgp = new ButtonGroup();
			rbgp.add(rbParticular);
			rbgp.add(rbConvenio);

			JButton button = new JButton("");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int qtd = 0;
					ArrayList<Servicos> s = lab.getArrayServicos();
					ArrayList<Servicos> servicos = new ArrayList<Servicos>();
					SimpleDateFormat sdf = new SimpleDateFormat("MM");
					SimpleDateFormat ano = new SimpleDateFormat("yyyy");
					if (s != null) {
						if (s.size() > 0) {

							boolean pago = chckbxPagamentoRecebido.isSelected();
							boolean finalizado = chckFinalizado.isSelected();
							boolean saida = chckSaida.isSelected();
							boolean pgmto = chckPgmto.isSelected();

							int index;
							if (comboBox.getSelectedIndex() != -1) {
								index = comboBox.getSelectedIndex();
								for (int i = 0; i < s.size(); i++) {
									boolean test = true;

									Cliente c;
									if (rbConvenio.isSelected()) {
										c = lab.getClinicas().get(index);
									} else {
										c = lab.getArrayDentistas().get(index);
									}

									if (!(c.getCod() == s.get(i).getCliente().getCod())) {
										test = false;
									}

									if (pago) {
										if (!(s.get(i).isPago())) {
											test = false;
										}
									}

									if (finalizado) {
										if (!(s.get(i).getSituacao().equals("FINALIZADO"))) {
											test = false;
										}
									}
									if (pgmto) {
										if (s.get(i).isPagamento() == false)
											test = false;
									}
									if (test) {
										if (saida) {
											if (s.get(i).getSituacao().equals("FINALIZADO")
													|| s.get(i).getSituacao().equals("PROVA")) {
												if ((Integer.parseInt(sdf.format(s.get(i).getSaida()))
														- 1) == monthChooser.getMonth()
														&& ((Integer.parseInt(
																ano.format(s.get(i).getSaida()))) == yearChooser
																		.getYear())) {
													qtd = qtd + 1;
													servicos.add(s.get(i));
												}
											}
										} else {
											if ((Integer.parseInt(sdf.format(s.get(i).getEntrada()))
													- 1) == monthChooser.getMonth()
													&& ((Integer
															.parseInt(ano.format(s.get(i).getEntrada()))) == yearChooser
																	.getYear())) {
												qtd = qtd + 1;
												servicos.add(s.get(i));
											}
										}
									}
								}
								if (qtd > 0) {
									RelatorioExtrato r = new RelatorioExtrato();
									int modo;
									if (chckSaida.isSelected())
										modo = 1;
									else
										modo = 2;
									r.relatorio(servicos, modo);
								} else {
									JOptionPane.showMessageDialog(TelaRelatorios.this,
											"N�o foi encontrado nenhum servi�o com este cliente no per�odo especificado",
											"N�o foi poss�vel gerar relat�rio", JOptionPane.WARNING_MESSAGE);
								}
							} else {
								JOptionPane.showMessageDialog(TelaRelatorios.this,
										"N�o foi encontrado nenhum cliente no sistema",
										"N�o foi poss�vel gerar relat�rio", JOptionPane.WARNING_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(TelaRelatorios.this,
									"N�o foi encontrado nenhum servi�o no sistema", "N�o foi poss�vel gerar relat�rio",
									JOptionPane.WARNING_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(TelaRelatorios.this,
								"N�o foi encontrado nenhum servi�o no sistema", "N�o foi poss�vel gerar relat�rio",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			});
			button.setBounds(73, 150, 256, 256);
			tablePanel.add(button);
			button.setIcon(new ImageIcon(img));
			button.setFocusPainted(false);
			button.setContentAreaFilled(false);
			button.setBorderPainted(false);
			button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

			JLabel lblRelatrioCl = new JLabel("Relat\u00F3rio cliente");
			lblRelatrioCl.setFont(new Font("Century Gothic", Font.PLAIN, 21));
			lblRelatrioCl.setBounds(114, 404, 256, 27);
			tablePanel.add(lblRelatrioCl);

			carregarCb();

		}

		private void carregarCb() {
			comboBox.removeAllItems();
			if (rbParticular.isSelected()) {
				ArrayList<Dentista> d = lab.getArrayDentistas();
				if (d != null) {
					if (d.size() > 0) {
						for (int i = 0; i < d.size(); i++) {
							comboBox.addItem(d.get(i).getNome());
						}
						comboBox.setSelectedIndex(d.size() - 1);
					}
				}
			} else {
				ArrayList<Clinica> c = lab.getClinicas();
				if (c != null) {
					if (c.size() > 0) {
						for (int i = 0; i < c.size(); i++) {
							comboBox.addItem(c.get(i).getNome());
						}
						comboBox.setSelectedIndex(c.size() - 1);
					}
				}
			}
		}

	}

	private class GUIReadDeleteClinica extends JPanel {
		private JTable tbClin;
		private DentistaTableModel dtmClin;
		private JTextField txtPesquisa;
		private JButton btnExcluir;
		private JButton btnAlterar;
		private JRadioButtonMenuItem rdbtnmntmNome;
		private JRadioButtonMenuItem rdbtnmntmCnpj;
		private JRadioButtonMenuItem rdbtnmntmTelefone;
		private JRadioButtonMenuItem rdbtnmntmEndereo;
		private RadioButtonHandler handler;
		private int filtro = 0;
		private JTable tbDent;
		private ILaboratorio lab;
		private JComboBox cbDentistas;
		private JButton btnRemoverDaClnica;

		public GUIReadDeleteClinica(final ILaboratorio lab) {
			this.lab = lab;
			setBounds(100, 100, 1366, 768);
			setLayout(null);

			JPanel headPanel = new JPanel();
			headPanel.setBackground(Color.WHITE);
			headPanel.setBounds(0, 0, 1368, 137);
			add(headPanel);
			headPanel.setLayout(null);

			JButton btBack = new JButton("");
			btBack.setIcon(
					new ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/back.png")))));
			btBack.setHorizontalAlignment(SwingConstants.CENTER);
			btBack.setFocusPainted(false);
			btBack.setContentAreaFilled(false);
			btBack.setBorderPainted(false);
			btBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btBack.setBounds(0, 5, 64, 40);
			btBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cardCliente();
				}
			});
			headPanel.add(btBack);

			JLabel lblClientes = new JLabel("Clientes");
			lblClientes.setForeground(new Color(102, 102, 255));
			lblClientes.setFont(lblClientes.getFont().deriveFont(lblClientes.getFont().getStyle() | Font.BOLD, 54f));
			lblClientes.setBounds(27, 34, 355, 54);
			headPanel.add(lblClientes);
			ButtonGroup filtro = new ButtonGroup();
			final JPopupMenu popupMenu = new JPopupMenu();

			rdbtnmntmNome = new JRadioButtonMenuItem("Nome", true);
			popupMenu.add(rdbtnmntmNome);

			rdbtnmntmCnpj = new JRadioButtonMenuItem("CNPJ", false);
			popupMenu.add(rdbtnmntmCnpj);

			rdbtnmntmTelefone = new JRadioButtonMenuItem("Telefone", false);
			popupMenu.add(rdbtnmntmTelefone);

			rdbtnmntmEndereo = new JRadioButtonMenuItem("Endere\u00E7o", false);
			popupMenu.add(rdbtnmntmEndereo);

			handler = new RadioButtonHandler();

			filtro.add(rdbtnmntmNome);
			filtro.add(rdbtnmntmEndereo);
			filtro.add(rdbtnmntmCnpj);
			filtro.add(rdbtnmntmTelefone);

			rdbtnmntmCnpj.addItemListener(handler);
			rdbtnmntmEndereo.addItemListener(handler);
			rdbtnmntmTelefone.addItemListener(handler);
			rdbtnmntmNome.addItemListener(handler);

			JToolBar toolBar = new JToolBar();
			toolBar.setFloatable(false);
			toolBar.setBounds(25, 94, 333, 28);
			headPanel.add(toolBar);

			JMenuBar menuBar = new JMenuBar();
			toolBar.add(menuBar);

			final JMenuItem mntmDentistas = new JMenuItem("Dentistas");
			mntmDentistas.setForeground(Color.BLACK);
			menuBar.add(mntmDentistas);
			mntmDentistas.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent arg0) {
					mntmDentistas.setForeground(Color.LIGHT_GRAY);
					getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					mntmDentistas.setForeground(Color.black);
					getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			});
			mntmDentistas.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					cardDentista();
				}
			});

			JMenuItem mntmClnicas = new JMenuItem("Cl\u00EDnicas");
			mntmClnicas.setForeground(Color.BLUE);
			menuBar.add(mntmClnicas);

			final JMenuItem mntmPacientes = new JMenuItem("Pacientes");
			mntmPacientes.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent arg0) {
					mntmPacientes.setForeground(Color.LIGHT_GRAY);
					getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					mntmPacientes.setForeground(Color.black);
					getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			});
			mntmPacientes.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					cardPaciente();
				}
			});
			menuBar.add(mntmPacientes);

			JPanel tablePanel = new JPanel();
			tablePanel.setBounds(10, 139, 1348, 501);
			add(tablePanel);
			tablePanel.setLayout(null);

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 41, 660, 426);
			tablePanel.add(scrollPane);

			tbClin = new JTable();
			tbClin.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scrollPane.setViewportView(tbClin);
			tbClin.getTableHeader().setReorderingAllowed(false);
			tbClin.setAutoCreateRowSorter(true);

			ctm = new ClinicaTableModel(lab.getClinicas());
			tbClin.setModel(ctm);

			JLabel lblPesquisar = new JLabel("Pesquisar:");
			lblPesquisar.setBounds(25, 14, 77, 17);
			tablePanel.add(lblPesquisar);
			lblPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 16));

			txtPesquisa = new JTextField();
			txtPesquisa.setBounds(105, 10, 446, 21);
			tablePanel.add(txtPesquisa);
			txtPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 16));
			txtPesquisa.setToolTipText("");
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

			final JButton btnFiltrar = new JButton("Filtrar");
			btnFiltrar.setBounds(553, 10, 85, 21);
			tablePanel.add(btnFiltrar);

			JScrollPane spDent = new JScrollPane();
			spDent.setBounds(677, 41, 638, 426);
			tablePanel.add(spDent);

			dtmClin = new DentistaTableModel(null);
			tbDent = new JTable(dtmClin);
			tbDent.getTableHeader().setReorderingAllowed(false);
			tbDent.setAutoCreateRowSorter(true);
			tbDent.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if (tbDent.getSelectedRow() != -1)
						btnRemoverDaClnica.setEnabled(true);
					else
						btnRemoverDaClnica.setEnabled(false);
				}
			});
			tbDent.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

				@Override
				public void valueChanged(ListSelectionEvent e) {
					if (tbDent.getSelectedRow() != -1)
						btnRemoverDaClnica.setEnabled(true);
					else
						btnRemoverDaClnica.setEnabled(false);
				}
			});
			spDent.setViewportView(tbDent);

			JLabel lblDentistas = new JLabel("Dentistas");
			lblDentistas.setBounds(677, 18, 96, 13);
			tablePanel.add(lblDentistas);

			cbDentistas = new JComboBox();
			cbDentistas.setEnabled(false);
			cbDentistas.setBounds(737, 14, 190, 21);
			tablePanel.add(cbDentistas);
			carregarCb();

			final JButton btnAdicionarClnica = new JButton("Adicionar \u00E0 cl\u00EDnica");
			btnAdicionarClnica.setEnabled(false);
			btnAdicionarClnica.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (cbDentistas.getSelectedItem() == null) {
						JOptionPane.showMessageDialog(GUIReadDeleteClinica.this, "Nenhum dentista selecionado(a)",
								"Adiconar � clinica", JOptionPane.WARNING_MESSAGE);
					} else {
						ArrayList<Dentista> ds = lab.getArrayDentistas();
						int indexRowModel = getRow();
						if (lab.addDentistaNaClinica(ctm.getClin(indexRowModel),
								ds.get(cbDentistas.getSelectedIndex()))) {
							JOptionPane.showMessageDialog(GUIReadDeleteClinica.this,
									"Dentista adicionado(a) � cl�nica com sucesso", "Adiocionar � cl�nica",
									JOptionPane.INFORMATION_MESSAGE);
							dtmClin.updateTable(
									(ArrayList<Dentista>) lab.getDentistasDaClinica(ctm.getClin(indexRowModel)));
						} else {
							JOptionPane.showMessageDialog(GUIReadDeleteClinica.this,
									"Erro ao tentar adicionar dentista � cl�nica", "Adicionar � cl�nica",
									JOptionPane.ERROR_MESSAGE);
						}

					}
				}
			});
			btnAdicionarClnica.setBounds(939, 14, 152, 21);
			tablePanel.add(btnAdicionarClnica);

			btnRemoverDaClnica = new JButton("Remover da cl\u00EDnica");
			btnRemoverDaClnica.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int indexRowModel = getRow();
					if (indexRowModel == -1) {
						JOptionPane.showMessageDialog(GUIReadDeleteClinica.this, "Selecione uma cl�nica primeiro",
								"Remover dentista da cl�nica", JOptionPane.WARNING_MESSAGE);
					} else {
						long index = dtmClin.getDent(tbDent.getSelectedRow()).getCod();
						String nomeClin = ctm.getClin(indexRowModel).getNome();
						String nomeDent = dtmClin.getDent(tbDent.getSelectedRow()).getNome();
						int opc = JOptionPane.showConfirmDialog(GUIReadDeleteClinica.this,
								"Tem certeza que deseja remover " + nomeDent + " da cl�nica " + nomeClin + "?",
								"Remover dentista da cl�nica", JOptionPane.OK_CANCEL_OPTION,
								JOptionPane.QUESTION_MESSAGE);
						if (opc == JOptionPane.OK_OPTION) {
							if (lab.removeDentistaDaClinica(ctm.getClin(indexRowModel).getCod(), index)) {
								JOptionPane.showMessageDialog(GUIReadDeleteClinica.this,
										"Dentista removido com sucesso da cl�nica " + nomeClin,
										"Remover dentista da cl�nica", JOptionPane.INFORMATION_MESSAGE);
								dtmClin.updateTable(
										(ArrayList<Dentista>) ctm.getClin(indexRowModel).getDentistasDaClinica());
							} else
								JOptionPane.showMessageDialog(GUIReadDeleteClinica.this,
										"Ocorreu um erro ao tentar remover " + nomeDent + " da cl�nica",
										"Remover dentista da cl�nica", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			});
			btnRemoverDaClnica.setEnabled(false);
			btnRemoverDaClnica.setBounds(1101, 14, 162, 21);
			tablePanel.add(btnRemoverDaClnica);

			JButton button = new JButton("+");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					GUICreateUpdateDentista novo = new GUICreateUpdateDentista(-1, lab);
					novo.setVisible(true);
					carregarCb();
					cbDentistas.setSelectedIndex(lab.getArrayDentistas().size() - 1);
				}
			});
			button.setBounds(1273, 14, 52, 21);
			tablePanel.add(button);
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
			tbClin.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if (tbClin.getSelectedRow() != -1) {
						cbDentistas.setEnabled(true);
						btnAdicionarClnica.setEnabled(true);
						btnExcluir.setEnabled(true);
						btnAlterar.setEnabled(true);
						int indexRowModel = getRow();
						dtmClin.updateTable(
								(ArrayList<Dentista>) lab.getDentistasDaClinica(ctm.getClin(indexRowModel)));
						carregarCb();
					} else {
						cbDentistas.setEnabled(false);
						btnAdicionarClnica.setEnabled(false);
						btnExcluir.setEnabled(false);
						btnAlterar.setEnabled(false);
						carregarCb();
					}
				}
			});
			tbClin.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

				@Override
				public void valueChanged(ListSelectionEvent e) {
					if (tbClin.getSelectedRow() != -1) {
						cbDentistas.setEnabled(true);
						btnAdicionarClnica.setEnabled(true);
						btnExcluir.setEnabled(true);
						btnAlterar.setEnabled(true);
						int indexRowModel = getRow();
						dtmClin.updateTable(
								(ArrayList<Dentista>) lab.getDentistasDaClinica(ctm.getClin(indexRowModel)));
						carregarCb();
					} else {
						cbDentistas.setEnabled(false);
						btnAdicionarClnica.setEnabled(false);
						btnExcluir.setEnabled(false);
						btnAlterar.setEnabled(false);
						carregarCb();
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
					if (tbClin.getSelectedRow() != -1) {
						int indexRowModel = getRow();
						int opcao = JOptionPane.showConfirmDialog(GUIReadDeleteClinica.this,
								"Tem certeza que deseja excluir " + ctm.getClin(indexRowModel).getNome() + "?",
								"Excluir", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (opcao == JOptionPane.OK_OPTION) {
							if (lab.excluirClinica(ctm.getClin(indexRowModel).getCod())) {
								JOptionPane.showMessageDialog(GUIReadDeleteClinica.this, "Cl�nica exclu�da com sucesso",
										"Sucesso", JOptionPane.INFORMATION_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(GUIReadDeleteClinica.this,
										"Ocorreu um erro no sistema de exclus�o", "Erro", JOptionPane.ERROR_MESSAGE);
							}
							ctm.updateTable(lab.getClinicas());
						}
					} else {
						JOptionPane.showMessageDialog(GUIReadDeleteClinica.this, "Por favor" + "selecione uma linha",
								"N�o foi poss�vel excluir", JOptionPane.ERROR_MESSAGE);
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

			JButton btnNovo = new JButton("+ Novo");
			btnNovo.setBounds(87, 10, 85, 21);
			bottomPanel.add(btnNovo);

			JSeparator separator = new JSeparator();
			separator.setBounds(10, 639, 1449, 75);
			add(separator);

			JSeparator separator_1 = new JSeparator();
			separator_1.setBounds(-1, 137, 1460, 503);
			add(separator_1);
			btnNovo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					GUICreateUpdateClinica novo = new GUICreateUpdateClinica(-1, lab);
					novo.setVisible(true);
					ctm.updateTable(lab.getClinicas());
				}
			});
			btnAlterar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (tbClin.getSelectedRow() != -1) {
						int indexRowModel = getRow();
						GUICreateUpdateClinica alterar = new GUICreateUpdateClinica(indexRowModel, lab);
						alterar.setVisible(true);
						ctm.updateTable(lab.getClinicas());
					} else {
						JOptionPane.showMessageDialog(GUIReadDeleteClinica.this, "Por favor selecione uma linha",
								"N�o foi poss�vel alterar", JOptionPane.WARNING_MESSAGE);
					}
				}
			});

		}

		protected int getRow() {
			int rowSel = tbClin.getSelectedRow();
			int indexRowModel;
			if (tbClin.getRowSorter() == null)
				indexRowModel = tbClin.getSelectedRow();
			else
				indexRowModel = tbClin.getRowSorter().convertRowIndexToModel(rowSel);
			return indexRowModel;
		}

		private void pesquisar(java.awt.event.KeyEvent evt) {
			final TableRowSorter<ClinicaTableModel> sorter = new TableRowSorter<ClinicaTableModel>(ctm);
			tbClin.setRowSorter(sorter);
			String text = txtPesquisa.getText();
			if (text.length() == 0) {
				sorter.setRowFilter(null);
			} else {
				try {
					sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, filtro));
				} catch (PatternSyntaxException pse) {
					JOptionPane.showMessageDialog(GUIReadDeleteClinica.this, "Erro",
							"Ocorreu um erro durante a pesquisa", JOptionPane.ERROR_MESSAGE);
					txtPesquisa.setText("");
				}
			}
		}

		private class RadioButtonHandler implements ItemListener {

			@Override
			public void itemStateChanged(ItemEvent event) {
				if (rdbtnmntmNome.isSelected())
					filtro = 0;
				if (rdbtnmntmCnpj.isSelected())
					filtro = 1;
				if (rdbtnmntmTelefone.isSelected())
					filtro = 2;
				if (rdbtnmntmEndereo.isSelected())
					filtro = 3;
			}
		}

		private void carregarCb() {
			cbDentistas.removeAllItems();
			ArrayList<Dentista> dentistas = lab.getArrayDentistas();
			;
			try {
				for (int i = 0; i < dentistas.size(); i++) {
					if (dentistas.get(i) != null)
						cbDentistas.addItem(dentistas.get(i).getNome());
				}
			} catch (NullPointerException ex) {
				JOptionPane.showMessageDialog(this,
						"Ocorreu um erro desconhecido ao tentar carregar todos os dentistas", "Erro",
						JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	private class GUIReadDeleteDentista extends JPanel {
		private JTable table;
		private JTextField txtPesquisa;
		private JButton btnExcluir;
		private JButton btnAlterar;
		private JRadioButtonMenuItem rdbtnmntmNome;
		private JRadioButtonMenuItem rdbtnmntmCro;
		private JRadioButtonMenuItem rdbtnmntmTelefone;
		private JRadioButtonMenuItem rdbtnmntmEndereo;
		private RadioButtonHandler handler;
		private int filtro = 0;

		public GUIReadDeleteDentista(final ILaboratorio lab) {
			setBounds(100, 100, 1366, 768);
			setLayout(null);

			JPanel headPanel = new JPanel();
			headPanel.setBackground(Color.WHITE);
			headPanel.setBounds(0, 0, 1368, 137);
			add(headPanel);
			headPanel.setLayout(null);

			JButton btBack = new JButton("");
			btBack.setIcon(
					new ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/back.png")))));
			btBack.setHorizontalAlignment(SwingConstants.CENTER);
			btBack.setFocusPainted(false);
			btBack.setContentAreaFilled(false);
			btBack.setBorderPainted(false);
			btBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btBack.setBounds(0, 5, 64, 40);
			btBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cardCliente();
				}
			});
			headPanel.add(btBack);

			JLabel lblClientes = new JLabel("Clientes");
			lblClientes.setForeground(new Color(102, 102, 255));
			lblClientes.setFont(lblClientes.getFont().deriveFont(lblClientes.getFont().getStyle() | Font.BOLD, 54f));
			lblClientes.setBounds(27, 34, 355, 54);
			headPanel.add(lblClientes);
			ButtonGroup filtro = new ButtonGroup();
			final JPopupMenu popupMenu = new JPopupMenu();

			JSeparator separator_1 = new JSeparator();
			separator_1.setBounds(-1, 137, 1460, 503);
			add(separator_1);

			rdbtnmntmNome = new JRadioButtonMenuItem("Nome", true);
			popupMenu.add(rdbtnmntmNome);

			rdbtnmntmCro = new JRadioButtonMenuItem("CRO", false);
			popupMenu.add(rdbtnmntmCro);

			rdbtnmntmTelefone = new JRadioButtonMenuItem("Telefone", false);
			popupMenu.add(rdbtnmntmTelefone);

			rdbtnmntmEndereo = new JRadioButtonMenuItem("Endere\u00E7o", false);
			popupMenu.add(rdbtnmntmEndereo);

			handler = new RadioButtonHandler();

			filtro.add(rdbtnmntmNome);
			filtro.add(rdbtnmntmEndereo);
			filtro.add(rdbtnmntmCro);
			filtro.add(rdbtnmntmTelefone);

			rdbtnmntmCro.addItemListener(handler);
			rdbtnmntmEndereo.addItemListener(handler);
			rdbtnmntmTelefone.addItemListener(handler);
			rdbtnmntmNome.addItemListener(handler);

			JToolBar toolBar = new JToolBar();
			toolBar.setBounds(25, 94, 333, 28);
			toolBar.setFloatable(false);
			headPanel.add(toolBar);

			JMenuBar menuBar = new JMenuBar();
			toolBar.add(menuBar);

			JMenuItem mntmDentistas = new JMenuItem("Dentistas");
			mntmDentistas.setForeground(Color.BLUE);
			menuBar.add(mntmDentistas);

			final JMenuItem mntmClnicas = new JMenuItem("Cl\u00EDnicas");
			mntmClnicas.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent arg0) {
					mntmClnicas.setForeground(Color.LIGHT_GRAY);
					getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					mntmClnicas.setForeground(Color.black);
					getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			});
			mntmClnicas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cardClinica();
					ctm.updateTable(lab.getClinicas());
				}
			});
			menuBar.add(mntmClnicas);

			final JMenuItem mntmPacientes = new JMenuItem("Pacientes");
			mntmPacientes.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent arg0) {
					mntmPacientes.setForeground(Color.LIGHT_GRAY);
					getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					mntmPacientes.setForeground(Color.black);
					getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			});
			mntmPacientes.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					cardPaciente();
					ptm.updateTable(lab.getPacientes());
				}
			});
			menuBar.add(mntmPacientes);

			JPanel tablePanel = new JPanel();
			tablePanel.setBounds(10, 139, 1348, 501);
			add(tablePanel);
			tablePanel.setLayout(null);

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 78, 1325, 329);
			tablePanel.add(scrollPane);

			table = new JTable();
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scrollPane.setViewportView(table);
			table.getTableHeader().setReorderingAllowed(false);
			table.setAutoCreateRowSorter(true);

			dtm = new DentistaTableModel(lab.getArrayDentistas());
			table.setModel(dtm);

			JLabel lblPesquisar = new JLabel("Pesquisar:");
			lblPesquisar.setBounds(25, 14, 77, 17);
			tablePanel.add(lblPesquisar);
			lblPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 16));

			txtPesquisa = new JTextField();
			txtPesquisa.setBounds(105, 10, 446, 21);
			tablePanel.add(txtPesquisa);
			txtPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 16));
			txtPesquisa.setToolTipText("");
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

			final JButton btnFiltrar = new JButton("Filtrar");
			btnFiltrar.setBounds(553, 10, 85, 21);
			tablePanel.add(btnFiltrar);
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
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if (table.getSelectedRow() != -1) {
						btnExcluir.setEnabled(true);
						btnAlterar.setEnabled(true);
					} else {
						btnExcluir.setEnabled(false);
						btnAlterar.setEnabled(false);
					}
				}
			});
			table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

				@Override
				public void valueChanged(ListSelectionEvent e) {
					if (table.getSelectedRow() != -1) {
						btnExcluir.setEnabled(true);
						btnAlterar.setEnabled(true);
					} else {
						btnExcluir.setEnabled(false);
						btnAlterar.setEnabled(false);
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
					if (table.getSelectedRow() != -1) {
						int rowSel = table.getSelectedRow();
						int indexRowModel;
						if (table.getRowSorter() == null)
							indexRowModel = table.getSelectedRow();
						else
							indexRowModel = table.getRowSorter().convertRowIndexToModel(rowSel);
						int opcao = JOptionPane.showConfirmDialog(GUIReadDeleteDentista.this, "Excluir",
								"Tem certeza que deseja excluir " + dtm.getDent(indexRowModel).getNome() + "?",
								JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (opcao == JOptionPane.OK_OPTION) {
							if (lab.excluirDentista(dtm.getDent(indexRowModel).getCod())) {
								JOptionPane.showMessageDialog(GUIReadDeleteDentista.this, "Sucesso",
										"Dentista exclu�do com sucesso", JOptionPane.INFORMATION_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(GUIReadDeleteDentista.this, "Erro",
										"Ocorreu um erro no sistema de exclus�o", JOptionPane.ERROR_MESSAGE);
							}
							dtm.updateTable(lab.getArrayDentistas());
						}
					} else {
						JOptionPane.showMessageDialog(GUIReadDeleteDentista.this, "Por favor selecione uma linha",
								"N�o foi poss�vel excluir", JOptionPane.ERROR_MESSAGE);
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

			JButton btnNovo = new JButton("+ Novo");
			btnNovo.setBounds(87, 10, 85, 21);
			bottomPanel.add(btnNovo);

			JSeparator separator = new JSeparator();
			separator.setBounds(10, 639, 1449, 75);
			add(separator);
			btnNovo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					GUICreateUpdateDentista novo = new GUICreateUpdateDentista(-1, lab);
					novo.setVisible(true);
					dtm.updateTable(lab.getArrayDentistas());
				}
			});
			btnAlterar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (table.getSelectedRow() != -1) {
						int rowSel = table.getSelectedRow();
						int indexRowModel;
						if (table.getRowSorter() == null)
							indexRowModel = table.getSelectedRow();
						else
							indexRowModel = table.getRowSorter().convertRowIndexToModel(rowSel);

						GUICreateUpdateDentista alterar = new GUICreateUpdateDentista(indexRowModel, lab);
						alterar.setVisible(true);
						dtm.updateTable(lab.getArrayDentistas());
					} else {
						JOptionPane.showMessageDialog(GUIReadDeleteDentista.this, "Por favor selecione uma linha",
								"N�o foi poss�vel alterar", JOptionPane.ERROR_MESSAGE);
					}
				}
			});

		}

		private void pesquisar(java.awt.event.KeyEvent evt) {
			final TableRowSorter<DentistaTableModel> sorter = new TableRowSorter<DentistaTableModel>(dtm);
			table.setRowSorter(sorter);
			String text = txtPesquisa.getText();
			if (text.length() == 0) {
				sorter.setRowFilter(null);
			} else {
				try {
					sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, filtro));
				} catch (PatternSyntaxException pse) {
					JOptionPane.showMessageDialog(GUIReadDeleteDentista.this, "Erro",
							"Ocorreu um erro durante a pesquisa", JOptionPane.ERROR_MESSAGE);
					txtPesquisa.setText("");
				}
			}
		}

		private class RadioButtonHandler implements ItemListener {

			@Override
			public void itemStateChanged(ItemEvent event) {
				if (rdbtnmntmNome.isSelected())
					filtro = 0;
				if (rdbtnmntmCro.isSelected())
					filtro = 1;
				if (rdbtnmntmTelefone.isSelected())
					filtro = 2;
				if (rdbtnmntmEndereo.isSelected())
					filtro = 3;
			}
		}
	}

	private class GUIReadDeletePaciente extends JPanel {
		private JTable tbPaciente;
		private JTextField txtPesquisa;
		private JButton btnExcluir;
		private JButton btnAlterar;
		private int filtro = 0;
		private ILaboratorio lab;

		public GUIReadDeletePaciente(final ILaboratorio lab) {
			this.lab = lab;
			setBounds(100, 100, 1366, 768);
			setLayout(null);

			JPanel headPanel = new JPanel();
			headPanel.setBackground(Color.WHITE);
			headPanel.setBounds(0, 0, 1368, 137);
			add(headPanel);
			headPanel.setLayout(null);

			JButton btBack = new JButton("");
			btBack.setIcon(
					new ImageIcon((Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/back.png")))));
			btBack.setHorizontalAlignment(SwingConstants.CENTER);
			btBack.setFocusPainted(false);
			btBack.setContentAreaFilled(false);
			btBack.setBorderPainted(false);
			btBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btBack.setBounds(0, 5, 64, 40);
			btBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cardCliente();
				}
			});
			headPanel.add(btBack);

			JLabel lblClientes = new JLabel("Clientes");
			lblClientes.setForeground(new Color(102, 102, 255));
			lblClientes.setFont(lblClientes.getFont().deriveFont(lblClientes.getFont().getStyle() | Font.BOLD, 54f));
			lblClientes.setBounds(27, 34, 355, 54);
			headPanel.add(lblClientes);

			JToolBar toolBar = new JToolBar();
			toolBar.setFloatable(false);
			toolBar.setBounds(25, 94, 333, 28);
			headPanel.add(toolBar);

			JMenuBar menuBar = new JMenuBar();
			toolBar.add(menuBar);

			final JMenuItem mntmDentistas = new JMenuItem("Dentistas");
			mntmDentistas.setForeground(Color.BLACK);
			menuBar.add(mntmDentistas);
			mntmDentistas.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent arg0) {
					mntmDentistas.setForeground(Color.LIGHT_GRAY);
					getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					mntmDentistas.setForeground(Color.black);
					getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			});
			mntmDentistas.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					cardDentista();
					dtm.updateTable(lab.getArrayDentistas());
				}
			});

			final JMenuItem mntmClnicas = new JMenuItem("Cl\u00EDnicas");
			mntmClnicas.setForeground(Color.BLACK);
			mntmClnicas.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent arg0) {
					mntmClnicas.setForeground(Color.LIGHT_GRAY);
					getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					mntmClnicas.setForeground(Color.black);
					getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			});
			mntmClnicas.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					cardClinica();
				}
			});
			menuBar.add(mntmClnicas);

			JMenuItem mntmPacientes = new JMenuItem("Pacientes");
			mntmPacientes.setForeground(Color.BLUE);
			menuBar.add(mntmPacientes);

			JPanel tablePanel = new JPanel();
			tablePanel.setBounds(10, 139, 1348, 501);
			add(tablePanel);
			tablePanel.setLayout(null);

			JScrollPane spPaciente = new JScrollPane();
			spPaciente.setBounds(0, 41, 1338, 426);
			tablePanel.add(spPaciente);

			ptm = new PacienteTableModel(lab.getPacientes());
			tbPaciente = new JTable(ptm);
			tbPaciente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			spPaciente.setViewportView(tbPaciente);
			tbPaciente.getTableHeader().setReorderingAllowed(false);
			tbPaciente.setAutoCreateRowSorter(true);

			JLabel lblPesquisar = new JLabel("Pesquisar:");
			lblPesquisar.setBounds(25, 14, 77, 17);
			tablePanel.add(lblPesquisar);
			lblPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 16));

			txtPesquisa = new JTextField();
			txtPesquisa.setBounds(105, 10, 368, 21);
			tablePanel.add(txtPesquisa);
			txtPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 16));
			txtPesquisa.setToolTipText("");
			txtPesquisa.setColumns(10);

			txtPesquisa.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent evt) {
					pesquisar(evt);
				}
			});
			tbPaciente.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if (tbPaciente.getSelectedRow() != -1) {
						btnExcluir.setEnabled(true);
						btnAlterar.setEnabled(true);
					} else {
						btnExcluir.setEnabled(false);
						btnAlterar.setEnabled(false);
					}
				}
			});
			tbPaciente.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

				@Override
				public void valueChanged(ListSelectionEvent e) {
					if (tbPaciente.getSelectedRow() != -1) {
						btnExcluir.setEnabled(true);
						btnAlterar.setEnabled(true);
					} else {
						btnExcluir.setEnabled(false);
						btnAlterar.setEnabled(false);
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
					if (tbPaciente.getSelectedRow() != -1) {
						int indexRowModel = getRow();
						String nome = ptm.getPaciente(indexRowModel).getNome();
						int opcao = JOptionPane.showConfirmDialog(GUIReadDeletePaciente.this,
								"Tem certeza que deseja excluir " + nome + "?", "Excluir", JOptionPane.OK_CANCEL_OPTION,
								JOptionPane.QUESTION_MESSAGE);
						if (opcao == JOptionPane.OK_OPTION) {
							if (lab.excluirPaciente(ptm.getPaciente(indexRowModel).getCod())) {
								JOptionPane.showMessageDialog(GUIReadDeletePaciente.this,
										"Paciente exclu�do com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(GUIReadDeletePaciente.this,
										"Ocorreu um erro no sistema de exclus�o", "Erro", JOptionPane.ERROR_MESSAGE);
							}
							ptm.updateTable(lab.getPacientes());
						}
					} else {
						JOptionPane.showMessageDialog(GUIReadDeletePaciente.this, "Por favor" + "selecione uma linha",
								"N�o foi poss�vel excluir", JOptionPane.ERROR_MESSAGE);
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

			JButton btnNovo = new JButton("+ Novo");
			btnNovo.setBounds(87, 10, 85, 21);
			bottomPanel.add(btnNovo);

			JSeparator separator = new JSeparator();
			separator.setBounds(10, 639, 1449, 75);
			add(separator);

			JSeparator separator_1 = new JSeparator();
			separator_1.setBounds(-1, 137, 1460, 503);
			add(separator_1);
			btnNovo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					GUICreateUpdatePaciente novo = new GUICreateUpdatePaciente(-1, lab);
					novo.setVisible(true);
					ptm.updateTable(lab.getPacientes());
				}
			});
			btnAlterar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (tbPaciente.getSelectedRow() != -1) {
						int indexRowModel = getRow();
						GUICreateUpdatePaciente alterar = new GUICreateUpdatePaciente(indexRowModel, lab);
						alterar.setVisible(true);
						ptm.updateTable(lab.getPacientes());
					} else {
						JOptionPane.showMessageDialog(GUIReadDeletePaciente.this, "Por favor selecione uma linha",
								"N�o foi poss�vel alterar", JOptionPane.WARNING_MESSAGE);
					}
				}
			});

		}

		private int getRow() {
			int rowSel = tbPaciente.getSelectedRow();
			int indexRowModel;
			if (tbPaciente.getRowSorter() == null)
				indexRowModel = tbPaciente.getSelectedRow();
			else
				indexRowModel = tbPaciente.getRowSorter().convertRowIndexToModel(rowSel);
			return indexRowModel;
		}

		private void pesquisar(java.awt.event.KeyEvent evt) {
			final TableRowSorter<PacienteTableModel> sorter = new TableRowSorter<PacienteTableModel>(ptm);
			tbPaciente.setRowSorter(sorter);
			String text = txtPesquisa.getText();
			if (text.length() == 0) {
				sorter.setRowFilter(null);
			} else {
				try {
					sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, filtro));
				} catch (PatternSyntaxException pse) {
					JOptionPane.showMessageDialog(GUIReadDeletePaciente.this, "Erro",
							"Ocorreu um erro durante a pesquisa", JOptionPane.ERROR_MESSAGE);
					txtPesquisa.setText("");
				}
			}
		}

	}

	private class GUIReadDeleteServicos extends JPanel {
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
		private JYearChooser yearChooser;
		private JMonthChooser monthChooser;
		private JToggleButton tglbtnFiltrar;
		private JButton btPagamento;
		private JButton btSaida;
		private JRadioButtonMenuItem rdbtnmntmPagamento;

		public GUIReadDeleteServicos(final ILaboratorio lab) {
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
					cardTipo();

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
					cardInicio();
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

			rdbtnmntmPagamento = new JRadioButtonMenuItem("Pagamento", true);
			popupMenu.add(rdbtnmntmPagamento);
			rdbtnmntmPagamento.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					filtrar(3);
				}
			});

			rdbtnmntmPreco = new JRadioButtonMenuItem("Preco", false);
			popupMenu.add(rdbtnmntmPreco);
			rdbtnmntmPreco.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					filtrar(4);
				}
			});

			rdbtnmntmEntrada = new JRadioButtonMenuItem("Data de entrada", true);
			popupMenu.add(rdbtnmntmEntrada);
			rdbtnmntmEntrada.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					filtrar(5);
				}
			});

			rdbtnmntmSaida = new JRadioButtonMenuItem("Data de sa�da", true);
			popupMenu.add(rdbtnmntmSaida);
			rdbtnmntmSaida.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					filtrar(6);
				}
			});

			rdbtnmntmCliente = new JRadioButtonMenuItem("Cliente", true);
			popupMenu.add(rdbtnmntmCliente);
			rdbtnmntmCliente.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					filtrar(7);
				}
			});

			rdbtnmntmFunc = new JRadioButtonMenuItem("Funcion�rio", true);
			popupMenu.add(rdbtnmntmFunc);
			rdbtnmntmFunc.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					filtrar(8);
				}
			});

			rdbtnmntmPaciente = new JRadioButtonMenuItem("Paciente", true);
			popupMenu.add(rdbtnmntmPaciente);
			rdbtnmntmPaciente.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					filtrar(9);
				}
			});

			handler = new RadioButtonHandler();

			filtro.add(rdbtnmntmTipo);
			filtro.add(rdbtnmntmDescricao);
			filtro.add(rdbtnmntmSituacao);
			filtro.add(rdbtnmntmPagamento);
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
			tbServicos.getColumnModel().getColumn(4).setPreferredWidth(10);
			tbServicos.getColumnModel().getColumn(5).setPreferredWidth(10);
			tbServicos.getColumnModel().getColumn(6).setPreferredWidth(10);

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
			tablePanel.add(txtPesquisa);

			yearChooser = new JYearChooser();
			yearChooser.setBounds(1176, 10, 46, 19);
			tablePanel.add(yearChooser);

			monthChooser = new JMonthChooser();
			monthChooser.setBounds(1061, 10, 110, 19);
			tablePanel.add(monthChooser);

			tglbtnFiltrar = new JToggleButton("Filtrar");
			tglbtnFiltrar.setBounds(1232, 10, 90, 21);
			tablePanel.add(tglbtnFiltrar);
			tglbtnFiltrar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (tglbtnFiltrar.isSelected()) {
						yearChooser.setEnabled(false);
						monthChooser.setEnabled(false);
						int mes = monthChooser.getMonth();
						String ano = String.valueOf(yearChooser.getYear());
						SimpleDateFormat mesFormat = new SimpleDateFormat("MM");
						SimpleDateFormat anoFormat = new SimpleDateFormat("yyyy");
						ArrayList<Servicos> s = lab.getArrayServicos();
						ArrayList<Servicos> array = new ArrayList<Servicos>();
						if (s != null) {
							if (s.size() > 0) {
								for (int i = 0; i < s.size(); i++) {
									if ((Integer.parseInt(mesFormat.format(s.get(i).getEntrada())) - 1) == mes
											&& anoFormat.format(s.get(i).getEntrada()).equals(ano)) {
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

			final JButton btnFiltrar = new JButton("Filtrar");
			btnFiltrar.setBounds(553, 10, 85, 21);
			tablePanel.add(btnFiltrar);
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
						btSaida.setEnabled(true);
						btPagamento.setEnabled(true);
					} else {
						btnExcluir.setEnabled(false);
						btnAlterar.setEnabled(false);
						btnExtrato.setEnabled(false);
						btSaida.setEnabled(false);
						btPagamento.setEnabled(false);
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
						btSaida.setEnabled(true);
						btPagamento.setEnabled(true);
					} else {
						btnExcluir.setEnabled(false);
						btnAlterar.setEnabled(false);
						btnExtrato.setEnabled(false);
						btSaida.setEnabled(false);
						btPagamento.setEnabled(false);
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
						int opcao = JOptionPane.showConfirmDialog(GUIReadDeleteServicos.this,
								"Tem certeza que deseja excluir " + stm.getServicos(indexRowModel).getTipo().getNome()
										+ "?",
								"Excluir servi�o", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (opcao == JOptionPane.OK_OPTION) {
							if (lab.excluirServico(stm.getServicos(indexRowModel).getCod())) {
								JOptionPane.showMessageDialog(GUIReadDeleteServicos.this,
										"Servi�o exclu�do com sucesso", "Excluir servi�o",
										JOptionPane.INFORMATION_MESSAGE);
								stm.updateTable(lab.getArrayServicos());
							} else {
								JOptionPane.showMessageDialog(GUIReadDeleteServicos.this,
										"Ocorreu um erro no sistema de exclus�o", "Excluir servi�o",
										JOptionPane.ERROR_MESSAGE);
							}
							stm.updateTable(lab.getArrayServicos());
						}
					} else {
						JOptionPane.showMessageDialog(GUIReadDeleteServicos.this, "N�o foi poss�vel excluir",
								"Por favor selecione uma linha", JOptionPane.WARNING_MESSAGE);
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
			btPagamento.setBounds(992, 10, 160, 21);
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
					if (s.isPago()) {
						JOptionPane.showMessageDialog(null, "Pagamento j� registrado", "Registrar pagamento",
								JOptionPane.WARNING_MESSAGE);
					} else {
						if (lab.setPago(s.getCod(), true)) {
							JOptionPane.showMessageDialog(null, "Pagamento do servi�o registrado com sucesso",
									"Registrar pagamento", JOptionPane.PLAIN_MESSAGE);
							stm.updateTable(lab.getArrayServicos());
						} else {
							JOptionPane.showMessageDialog(null, "Ocorreu um problema ao registrar o pagamento",
									"Registrar pagamento", JOptionPane.PLAIN_MESSAGE);
						}
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
					if (s.getSituacao().equals("FINALIZADO") || s.getSituacao().equals("PROVA")) {
						JOptionPane.showMessageDialog(null, "Sa�da j� registrada", "Registrar sa�da",
								JOptionPane.WARNING_MESSAGE);
					} else {
						JLabel lb = new JLabel("Selecione como o servi�o saiu: ");
						JComboBox cb = new JComboBox();
						cb.addItem("FINALIZADO");
						cb.addItem("PROVA");
						JPanel p = new JPanel();
						p.add(lb);
						p.add(cb);
						JOptionPane.showMessageDialog(null, p, "Registrar sa�da", JOptionPane.PLAIN_MESSAGE);
						if (lab.alterServico(s.getCod(), s.isPagamento(), (String) cb.getSelectedItem(),
								s.getDescricao(), s.getValorFinal(), s.getCliente(), s.getEntrada(), s.getSaida(),
								s.getFuncionario(), s.getPaciente(), s.getTipo())) {
							JOptionPane.showMessageDialog(null, "Sa�da registrada com sucesso", "Registrar sa�da",
									JOptionPane.PLAIN_MESSAGE);
							stm.updateTable(lab.getArrayServicos());
						} else {
							JOptionPane.showMessageDialog(null,
									"Ocorreu um problema ao registrar a sa�da, tente novamente", "Registrar sa�da",
									JOptionPane.PLAIN_MESSAGE);
						}
					}
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
					RelatorioServico rs = new RelatorioServico();
					rs.relatorio(s);
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
					ctm.updateTable(lab.getClinicas());
					dtm.updateTable(lab.getArrayDentistas());
					ptm.updateTable(lab.getPacientes());
					ftm.updateTable(lab.getArrayFunc());
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
						ctm.updateTable(lab.getClinicas());
						dtm.updateTable(lab.getArrayDentistas());
						ptm.updateTable(lab.getPacientes());
						ftm.updateTable(lab.getArrayFunc());
					} else {
						JOptionPane.showMessageDialog(GUIReadDeleteServicos.this, "Por favor selecione uma linha",
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
					JOptionPane.showMessageDialog(GUIReadDeleteServicos.this, "Erro",
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

	private class TelaFinanceiro extends JPanel {

		public TelaFinanceiro() {
			setBounds(0, 0, 1366, 768);
			setLayout(null);

			JPanel headPanel = new JPanel();
			headPanel.setBackground(Color.WHITE);
			headPanel.setBounds(0, 0, 1368, 137);
			add(headPanel);
			headPanel.setLayout(null);

			JButton btBack = new JButton("");
			btBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btBack.setBounds(0, 5, 64, 40);
			headPanel.add(btBack);
			btBack.setFocusPainted(false);
			btBack.setContentAreaFilled(false);
			btBack.setBorderPainted(false);
			btBack.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					cardInicio();
				}
			});
			btBack.setIcon(
					new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/back.png"))));

			JLabel lblClientesEFuncionrios = new JLabel("Financeiro");
			lblClientesEFuncionrios.setBounds(27, 45, 634, 66);
			headPanel.add(lblClientesEFuncionrios);
			lblClientesEFuncionrios.setForeground(new Color(102, 102, 255));
			lblClientesEFuncionrios.setFont(new Font("Tahoma", Font.BOLD, 54));

			JPanel panel = new JPanel();
			panel.setBounds(0, 0, 1368, 759);
			add(panel);
			panel.setLayout(null);

			JButton btResumo = new JButton("");
			btResumo.setIcon(
					new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/pie-chart.png"))));
			btResumo.setFocusPainted(false);
			btResumo.setBounds(261, 256, 256, 256);
			panel.add(btResumo);
			btResumo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					cardResumo();

				}
			});
			btResumo.setFocusPainted(false);
			btResumo.setContentAreaFilled(false);
			btResumo.setBorderPainted(false);
			btResumo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

			JButton btDespesa = new JButton("");
			btDespesa.setIcon(
					new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/receipt.png"))));
			btDespesa.setFocusPainted(false);
			btDespesa.setBounds(557, 256, 256, 256);
			panel.add(btDespesa);
			btDespesa.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					cardDespesa();

				}
			});
			btDespesa.setFocusPainted(false);
			btDespesa.setContentAreaFilled(false);
			btDespesa.setBorderPainted(false);
			btDespesa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

			JButton btCompra = new JButton("");
			btCompra.setIcon(
					new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/cart.png"))));
			btCompra.setFocusPainted(false);
			btCompra.setBounds(853, 256, 256, 256);
			btCompra.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					cardCompra();

				}
			});
			btCompra.setFocusPainted(false);
			btCompra.setContentAreaFilled(false);
			btCompra.setBorderPainted(false);
			btCompra.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			panel.add(btCompra);

			JLabel lblPacientes = new JLabel("Compras");
			lblPacientes.setFont(new Font("Century Gothic", Font.PLAIN, 41));
			lblPacientes.setBounds(883, 497, 256, 72);
			panel.add(lblPacientes);

			JLabel lblClnicas = new JLabel("Despesas");
			lblClnicas.setFont(new Font("Century Gothic", Font.PLAIN, 41));
			lblClnicas.setBounds(594, 497, 256, 72);
			panel.add(lblClnicas);

			JLabel lblDentistas = new JLabel("Resumo");
			lblDentistas.setFont(new Font("Century Gothic", Font.PLAIN, 41));
			lblDentistas.setBounds(304, 497, 256, 72);
			panel.add(lblDentistas);

			JSeparator separator_1 = new JSeparator();
			separator_1.setBounds(-12, 137, 1419, 2);
			panel.add(separator_1);
		}

	}

	private class TelaCompras extends JPanel {

		private JButton btnExcluir;
		private JTextField txtPesquisa;
		private JTable table;
		private ILaboratorio lab;
		private int filtro = 0;

		public TelaCompras(final ILaboratorio lab) {
			this.lab = lab;
			setBounds(0, 0, 1366, 768);
			setLayout(null);

			JPanel headPanel = new JPanel();
			headPanel.setBackground(Color.WHITE);
			headPanel.setBounds(0, 0, 1368, 137);
			add(headPanel);
			headPanel.setLayout(null);

			JButton btBack = new JButton("");
			btBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btBack.setBounds(0, 5, 64, 40);
			headPanel.add(btBack);
			btBack.setFocusPainted(false);
			btBack.setContentAreaFilled(false);
			btBack.setBorderPainted(false);
			btBack.setIcon(
					new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/back.png"))));
			btBack.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					cardFinanceiro();
				}
			});

			JLabel lblClientesEFuncionrios = new JLabel("Compras");
			lblClientesEFuncionrios.setBounds(27, 45, 634, 66);
			headPanel.add(lblClientesEFuncionrios);
			lblClientesEFuncionrios.setForeground(new Color(102, 102, 255));
			lblClientesEFuncionrios.setFont(new Font("Tahoma", Font.BOLD, 54));

			JToolBar toolBar = new JToolBar();
			toolBar.setFloatable(false);
			toolBar.setBounds(289, 79, 416, 25);
			headPanel.add(toolBar);

			JMenuBar menuBar = new JMenuBar();
			toolBar.add(menuBar);

			JMenuItem mntmCompras = new JMenuItem("Compras");
			mntmCompras.setForeground(Color.BLUE);
			menuBar.add(mntmCompras);

			final JMenuItem mntmProdutos = new JMenuItem("Produtos");
			mntmProdutos.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent arg0) {
					getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					mntmProdutos.setForeground(Color.GRAY);
				}

				public void mouseExited(MouseEvent arg0) {
					getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					mntmProdutos.setForeground(Color.BLACK);
				}
			});
			mntmProdutos.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					cardProduto();

				}
			});
			menuBar.add(mntmProdutos);

			final JMenuItem mntmFornecedores = new JMenuItem("Fornecedores");
			mntmFornecedores.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					cardForn();
				}
			});
			mntmFornecedores.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent arg0) {
					getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					mntmFornecedores.setForeground(Color.GRAY);
				}

				public void mouseExited(MouseEvent arg0) {
					getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					mntmFornecedores.setForeground(Color.BLACK);
				}
			});
			menuBar.add(mntmFornecedores);

			JSeparator separator_1 = new JSeparator();
			separator_1.setBounds(0, 136, 1419, 1);
			headPanel.add(separator_1);

			JPanel tablePanel = new JPanel();
			tablePanel.setBounds(0, 136, 1368, 623);
			add(tablePanel);
			tablePanel.setLayout(null);

			JLabel lblPesquisar = new JLabel("Pesquisar:");
			lblPesquisar.setBounds(25, 14, 77, 17);
			tablePanel.add(lblPesquisar);
			lblPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 16));

			txtPesquisa = new JTextField();
			txtPesquisa.setBounds(105, 10, 446, 21);
			tablePanel.add(txtPesquisa);
			txtPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 16));
			txtPesquisa.setToolTipText("");
			txtPesquisa.setColumns(10);
			txtPesquisa.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent evt) {
					pesquisar(evt);
				}

				public void keyPressed(KeyEvent evt) {
					pesquisar(evt);
				}
			});

			final JButton btnFiltrar = new JButton("Filtrar");
			btnFiltrar.setBounds(553, 10, 85, 21);
			tablePanel.add(btnFiltrar);

			final JPopupMenu popupMenu = new JPopupMenu();
			addPopup(btnFiltrar, popupMenu);
			btnFiltrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					popupMenu.show(btnFiltrar, btnFiltrar.getWidth(), btnFiltrar.getHeight());
				}
			});
			JRadioButtonMenuItem rdbtnmntmDescrio = new JRadioButtonMenuItem("Descri\u00E7\u00E3o");
			rdbtnmntmDescrio.setSelected(true);
			rdbtnmntmDescrio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					filtro = 0;
				}
			});
			popupMenu.add(rdbtnmntmDescrio);

			JRadioButtonMenuItem rdbtnmntmValor = new JRadioButtonMenuItem("Valor");
			rdbtnmntmValor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					filtro = 1;
				}
			});
			popupMenu.add(rdbtnmntmValor);

			JRadioButtonMenuItem rdbtnmntmData = new JRadioButtonMenuItem("Data");
			rdbtnmntmData.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					filtro = 2;
				}
			});
			popupMenu.add(rdbtnmntmData);

			JRadioButtonMenuItem rdbtnmntmFornecedor = new JRadioButtonMenuItem("Fornecedor");
			rdbtnmntmFornecedor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					filtro = 3;
				}
			});
			popupMenu.add(rdbtnmntmFornecedor);

			ButtonGroup gp = new ButtonGroup();

			gp.add(rdbtnmntmDescrio);
			gp.add(rdbtnmntmValor);
			gp.add(rdbtnmntmData);
			gp.add(rdbtnmntmFornecedor);

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 36, 1325, 454);
			tablePanel.add(scrollPane);

			compratm = new CompraTableModel(lab.getArrayCompras());
			table = new JTable(compratm);
			scrollPane.setViewportView(table);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.getTableHeader().setReorderingAllowed(false);
			table.setAutoCreateRowSorter(true);
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if (table.getSelectedRow() != -1) {
						btnExcluir.setEnabled(true);
					} else {
						btnExcluir.setEnabled(false);
					}
				}
			});
			table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

				@Override
				public void valueChanged(ListSelectionEvent e) {
					if (table.getSelectedRow() != -1) {
						btnExcluir.setEnabled(true);
					} else {
						btnExcluir.setEnabled(false);
					}
				}
			});

			JPanel panel = new JPanel();
			panel.setBounds(10, 500, 1348, 113);
			tablePanel.add(panel);
			panel.setLayout(null);

			JButton btnRegistrar = new JButton("Registrar");
			btnRegistrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					GUICreateUpdateCompra novo = new GUICreateUpdateCompra(lab);
					novo.setVisible(true);
					compratm.updateTable(lab.getArrayCompras());
				}
			});
			btnRegistrar.setBounds(87, 10, 90, 21);
			panel.add(btnRegistrar);

			btnExcluir = new JButton("Excluir");
			btnExcluir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (table.getSelectedRow() != -1) {
						int rowSel = table.getSelectedRow();
						int indexRowModel;
						if (table.getRowSorter() == null)
							indexRowModel = table.getSelectedRow();
						else
							indexRowModel = table.getRowSorter().convertRowIndexToModel(rowSel);
						int opcao = JOptionPane.showConfirmDialog(TelaCompras.this,
								"Tem certeza que deseja excluir a compra"
										+ compratm.getCompra(indexRowModel).getDescricao() + "?",
								"Excluir", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (opcao == JOptionPane.OK_OPTION) {
							if (lab.excluirCompra(compratm.getCompra(indexRowModel).getCod())) {
								JOptionPane.showMessageDialog(TelaCompras.this, "Compra exclu�da com sucesso",
										"Sucesso", JOptionPane.INFORMATION_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(TelaCompras.this,
										"Ocorreu um erro no sistema de exclus�o", "Erro", JOptionPane.ERROR_MESSAGE);
							}
							compratm.updateTable(lab.getArrayCompras());
						}
					} else {
						JOptionPane.showMessageDialog(TelaCompras.this, "Por favor selecione uma linha",
								"N�o foi poss�vel excluir", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			btnExcluir.setEnabled(false);
			btnExcluir.setBounds(187, 10, 85, 21);
			panel.add(btnExcluir);
		}

		private void pesquisar(java.awt.event.KeyEvent evt) {
			final TableRowSorter<CompraTableModel> sorter = new TableRowSorter<CompraTableModel>(compratm);
			table.setRowSorter(sorter);
			String text = txtPesquisa.getText();
			if (text.length() == 0) {
				sorter.setRowFilter(null);
			} else {
				try {
					sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, filtro));
				} catch (PatternSyntaxException pse) {
					JOptionPane.showMessageDialog(TelaCompras.this, "Erro", "Ocorreu um erro durante a pesquisa",
							JOptionPane.ERROR_MESSAGE);
					txtPesquisa.setText("");
				}
			}
		}

		private void addPopup(Component component, final JPopupMenu popup) {
			component.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					if (e.isPopupTrigger()) {
						showMenu(e);
					}
				}

				public void mouseReleased(MouseEvent e) {
					if (e.isPopupTrigger()) {
						showMenu(e);
					}
				}

				private void showMenu(MouseEvent e) {
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			});
		}
	}

	private class TelaProdutos extends JPanel {
		private JTextField txtPesquisa;
		private JTable table;
		private ILaboratorio lab;
		private int filtro = 0;
		private JButton btnAlterar;
		private JButton btnExcluir;

		public TelaProdutos(final ILaboratorio lab) {
			this.lab = lab;
			setBounds(0, 0, 1366, 768);
			setLayout(null);

			JPanel headPanel = new JPanel();
			headPanel.setBackground(Color.WHITE);
			headPanel.setBounds(0, 0, 1368, 137);
			add(headPanel);
			headPanel.setLayout(null);

			JButton btBack = new JButton("");
			btBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cardFinanceiro();
				}
			});
			btBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btBack.setBounds(0, 5, 64, 40);
			headPanel.add(btBack);
			btBack.setFocusPainted(false);
			btBack.setContentAreaFilled(false);
			btBack.setBorderPainted(false);
			btBack.setIcon(
					new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/back.png"))));

			JLabel lblClientesEFuncionrios = new JLabel("Produtos");
			lblClientesEFuncionrios.setBounds(27, 45, 634, 66);
			headPanel.add(lblClientesEFuncionrios);
			lblClientesEFuncionrios.setForeground(new Color(102, 102, 255));
			lblClientesEFuncionrios.setFont(new Font("Tahoma", Font.BOLD, 54));

			JToolBar toolBar = new JToolBar();
			toolBar.setFloatable(false);
			toolBar.setBounds(289, 79, 416, 25);
			headPanel.add(toolBar);

			JMenuBar menuBar = new JMenuBar();
			toolBar.add(menuBar);

			final JMenuItem mntmCompras = new JMenuItem("Compras");
			mntmCompras.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent arg0) {
					getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					mntmCompras.setForeground(Color.GRAY);
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					mntmCompras.setForeground(Color.BLACK);
				}
			});
			mntmCompras.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					cardCompra();

				}
			});

			JMenuItem mntmProdutos = new JMenuItem("Produtos");
			mntmProdutos.setForeground(Color.BLUE);
			menuBar.add(mntmCompras);
			menuBar.add(mntmProdutos);

			final JMenuItem mntmFornecedores = new JMenuItem("Fornecedores");
			mntmFornecedores.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					cardForn();
				}
			});
			mntmFornecedores.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent arg0) {
					getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					mntmFornecedores.setForeground(Color.GRAY);
				}

				public void mouseExited(MouseEvent arg0) {
					getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					mntmFornecedores.setForeground(Color.BLACK);
				}
			});

			menuBar.add(mntmFornecedores);

			JSeparator separator_1 = new JSeparator();
			separator_1.setBounds(0, 136, 1419, 1);
			headPanel.add(separator_1);

			JPanel tablePanel = new JPanel();
			tablePanel.setBounds(0, 136, 1368, 623);
			add(tablePanel);
			tablePanel.setLayout(null);

			JLabel lblPesquisar = new JLabel("Pesquisar:");
			lblPesquisar.setBounds(25, 14, 77, 17);
			tablePanel.add(lblPesquisar);
			lblPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 16));

			txtPesquisa = new JTextField();
			txtPesquisa.setBounds(105, 10, 446, 21);
			tablePanel.add(txtPesquisa);
			txtPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 16));
			txtPesquisa.setToolTipText("");
			txtPesquisa.setColumns(10);
			txtPesquisa.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent evt) {
					pesquisar(evt);
				}

				public void keyPressed(KeyEvent evt) {
					pesquisar(evt);
				}
			});

			final JButton btnFiltrar = new JButton("Filtrar");
			btnFiltrar.setBounds(553, 10, 85, 21);
			tablePanel.add(btnFiltrar);

			final JPopupMenu popupMenu = new JPopupMenu();
			addPopup(btnFiltrar, popupMenu);
			btnFiltrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					popupMenu.show(btnFiltrar, btnFiltrar.getWidth(), btnFiltrar.getHeight());
				}
			});

			JRadioButtonMenuItem rdNome = new JRadioButtonMenuItem("Nome");
			rdNome.setSelected(true);
			rdNome.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					filtro = 0;
				}
			});
			popupMenu.add(rdNome);

			JRadioButtonMenuItem rdValor = new JRadioButtonMenuItem("Valor");
			rdValor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					filtro = 1;
				}
			});
			popupMenu.add(rdValor);

			ButtonGroup gp = new ButtonGroup();

			gp.add(rdNome);
			gp.add(rdValor);

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 36, 1325, 454);
			tablePanel.add(scrollPane);

			produtotm = new ProdutoTableModel(lab.getArrayProdutos());
			table = new JTable(produtotm);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setAutoCreateRowSorter(true);
			scrollPane.setViewportView(table);

			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if (table.getSelectedRow() != -1) {
						btnExcluir.setEnabled(true);
						btnAlterar.setEnabled(true);
					} else {
						btnExcluir.setEnabled(false);
						btnAlterar.setEnabled(false);
					}
				}
			});
			table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

				@Override
				public void valueChanged(ListSelectionEvent e) {
					if (table.getSelectedRow() != -1) {
						btnExcluir.setEnabled(true);
						btnAlterar.setEnabled(true);
					} else {
						btnExcluir.setEnabled(false);
						btnAlterar.setEnabled(false);
					}
				}
			});

			JPanel panel = new JPanel();
			panel.setBounds(10, 500, 1348, 113);
			tablePanel.add(panel);
			panel.setLayout(null);

			JButton btnNovo = new JButton("+ Novo");
			btnNovo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					GUICreateUpdateProduto novo = new GUICreateUpdateProduto(-1, lab);
					novo.setVisible(true);
					produtotm.updateTable(lab.getArrayProdutos());
				}
			});
			btnNovo.setBounds(87, 10, 85, 21);
			panel.add(btnNovo);

			btnAlterar = new JButton("Alterar");
			btnAlterar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (table.getSelectedRow() != -1) {
						int rowSel = table.getSelectedRow();
						int indexRowModel;
						if (table.getRowSorter() == null)
							indexRowModel = table.getSelectedRow();
						else
							indexRowModel = table.getRowSorter().convertRowIndexToModel(rowSel);

						GUICreateUpdateProduto alterar = new GUICreateUpdateProduto(indexRowModel, lab);
						alterar.setVisible(true);
						produtotm.updateTable(lab.getArrayProdutos());
					} else {
						JOptionPane.showMessageDialog(TelaProdutos.this, "Por favor selecione uma linha",
								"N�o foi poss�vel alterar", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
			btnAlterar.setEnabled(false);
			btnAlterar.setBounds(182, 10, 85, 21);
			panel.add(btnAlterar);

			btnExcluir = new JButton("Excluir");
			btnExcluir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (table.getSelectedRow() != -1) {
						int rowSel = table.getSelectedRow();
						int indexRowModel;
						if (table.getRowSorter() == null)
							indexRowModel = table.getSelectedRow();
						else
							indexRowModel = table.getRowSorter().convertRowIndexToModel(rowSel);
						int opcao = JOptionPane.showConfirmDialog(TelaProdutos.this,
								"Tem certeza que deseja excluir o produto"
										+ produtotm.getProduto(indexRowModel).getNome() + "?",
								"Excluir", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (opcao == JOptionPane.OK_OPTION) {
							if (lab.excluirProduto(produtotm.getProduto(indexRowModel).getCod())) {
								JOptionPane.showMessageDialog(TelaProdutos.this, "Produto exclu�do com sucesso",
										"Sucesso", JOptionPane.INFORMATION_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(TelaProdutos.this,
										"Ocorreu um erro no sistema de exclus�o", "Erro", JOptionPane.ERROR_MESSAGE);
							}
							produtotm.updateTable(lab.getArrayProdutos());
						}
					} else {
						JOptionPane.showMessageDialog(TelaProdutos.this, "Por favor selecione uma linha",
								"N�o foi poss�vel excluir", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			btnExcluir.setEnabled(false);
			btnExcluir.setBounds(273, 10, 85, 21);
			panel.add(btnExcluir);
		}

		private void pesquisar(java.awt.event.KeyEvent evt) {
			final TableRowSorter<ProdutoTableModel> sorter = new TableRowSorter<ProdutoTableModel>(produtotm);
			table.setRowSorter(sorter);
			String text = txtPesquisa.getText();
			if (text.length() == 0) {
				sorter.setRowFilter(null);
			} else {
				try {
					sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, filtro));
				} catch (PatternSyntaxException pse) {
					JOptionPane.showMessageDialog(TelaProdutos.this, "Erro", "Ocorreu um erro durante a pesquisa",
							JOptionPane.ERROR_MESSAGE);
					txtPesquisa.setText("");
				}
			}
		}

		private void addPopup(Component component, final JPopupMenu popup) {
			component.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					if (e.isPopupTrigger()) {
						showMenu(e);
					}
				}

				public void mouseReleased(MouseEvent e) {
					if (e.isPopupTrigger()) {
						showMenu(e);
					}
				}

				private void showMenu(MouseEvent e) {
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			});
		}
	}

	private class TelaFornecedores extends JPanel {

		private JButton btnExcluir;
		private JTextField txtPesquisa;
		private JTable table;
		private ILaboratorio lab;
		private int filtro = 0;
		private JButton btnAlterar;

		public TelaFornecedores(final ILaboratorio lab) {
			this.lab = lab;
			setBounds(0, 0, 1366, 768);
			setLayout(null);

			JPanel headPanel = new JPanel();
			headPanel.setBackground(Color.WHITE);
			headPanel.setBounds(0, 0, 1368, 137);
			add(headPanel);
			headPanel.setLayout(null);

			JButton btBack = new JButton("");
			btBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cardFinanceiro();
				}
			});
			btBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btBack.setBounds(0, 5, 64, 40);
			headPanel.add(btBack);
			btBack.setFocusPainted(false);
			btBack.setContentAreaFilled(false);
			btBack.setBorderPainted(false);
			btBack.setIcon(
					new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/back.png"))));

			JLabel lblClientesEFuncionrios = new JLabel("Fornecedores");
			lblClientesEFuncionrios.setBounds(27, 45, 634, 66);
			headPanel.add(lblClientesEFuncionrios);
			lblClientesEFuncionrios.setForeground(new Color(102, 102, 255));
			lblClientesEFuncionrios.setFont(new Font("Tahoma", Font.BOLD, 37));

			JToolBar toolBar = new JToolBar();
			toolBar.setFloatable(false);
			toolBar.setBounds(289, 79, 416, 25);
			headPanel.add(toolBar);

			JMenuBar menuBar = new JMenuBar();
			toolBar.add(menuBar);

			final JMenuItem btComp = new JMenuItem("Compras");
			btComp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cardCompra();
				}
			});
			btComp.setForeground(Color.BLACK);
			menuBar.add(btComp);
			btComp.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent arg0) {
					getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					btComp.setForeground(Color.GRAY);
				}

				public void mouseExited(MouseEvent arg0) {
					getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					btComp.setForeground(Color.BLACK);
				}
			});

			final JMenuItem btProd = new JMenuItem("Produtos");
			btProd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cardProduto();
				}
			});
			btProd.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent arg0) {
					getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					btProd.setForeground(Color.GRAY);
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					btProd.setForeground(Color.BLACK);
				}
			});

			menuBar.add(btProd);

			JMenuItem mntmFornecedores = new JMenuItem("Fornecedores");
			mntmFornecedores.setForeground(Color.BLUE);
			menuBar.add(mntmFornecedores);

			JSeparator separator_1 = new JSeparator();
			separator_1.setBounds(0, 136, 1419, 1);
			headPanel.add(separator_1);

			JPanel tablePanel = new JPanel();
			tablePanel.setBounds(0, 136, 1368, 623);
			add(tablePanel);
			tablePanel.setLayout(null);

			JLabel lblPesquisar = new JLabel("Pesquisar:");
			lblPesquisar.setBounds(25, 14, 77, 17);
			tablePanel.add(lblPesquisar);
			lblPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 16));

			txtPesquisa = new JTextField();
			txtPesquisa.setBounds(105, 10, 446, 21);
			tablePanel.add(txtPesquisa);
			txtPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 16));
			txtPesquisa.setToolTipText("");
			txtPesquisa.setColumns(10);
			txtPesquisa.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent evt) {
					pesquisar(evt);
				}
			});

			final JButton btnFiltrar = new JButton("Filtrar");
			btnFiltrar.setBounds(553, 10, 85, 21);
			tablePanel.add(btnFiltrar);

			final JPopupMenu popupMenu = new JPopupMenu();
			addPopup(btnFiltrar, popupMenu);
			btnFiltrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					popupMenu.show(btnFiltrar, btnFiltrar.getWidth(), btnFiltrar.getHeight());
				}
			});

			JRadioButtonMenuItem fNome = new JRadioButtonMenuItem("Nome");
			fNome.setSelected(true);
			fNome.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					filtro = 0;
				}
			});
			popupMenu.add(fNome);

			JRadioButtonMenuItem fCNPJ = new JRadioButtonMenuItem("CNPJ");
			fCNPJ.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					filtro = 1;
				}
			});
			popupMenu.add(fCNPJ);

			JRadioButtonMenuItem fTel = new JRadioButtonMenuItem("Telefone");
			fTel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					filtro = 2;
				}
			});
			popupMenu.add(fTel);

			JRadioButtonMenuItem fEndereco = new JRadioButtonMenuItem("Endere\u00E7o");
			fEndereco.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					filtro = 3;
				}
			});
			popupMenu.add(fEndereco);

			ButtonGroup gp = new ButtonGroup();

			gp.add(fNome);
			gp.add(fCNPJ);
			gp.add(fTel);
			gp.add(fEndereco);

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 36, 1325, 454);
			tablePanel.add(scrollPane);

			forntm = new FornTableModel(lab.getArrayFornecedores());
			table = new JTable(forntm);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setAutoCreateRowSorter(true);
			scrollPane.setViewportView(table);

			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if (table.getSelectedRow() != -1) {
						btnExcluir.setEnabled(true);
						btnAlterar.setEnabled(true);
					} else {
						btnExcluir.setEnabled(false);
						btnAlterar.setEnabled(false);
					}
				}
			});
			table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

				@Override
				public void valueChanged(ListSelectionEvent e) {
					if (table.getSelectedRow() != -1) {
						btnExcluir.setEnabled(true);
						btnAlterar.setEnabled(true);
					} else {
						btnExcluir.setEnabled(false);
						btnAlterar.setEnabled(false);
					}
				}
			});

			JPanel panel = new JPanel();
			panel.setBounds(10, 500, 1348, 113);
			tablePanel.add(panel);
			panel.setLayout(null);

			JButton btnNovo = new JButton("+ Novo");
			btnNovo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					GUICreateUpdateFornecedor novo = new GUICreateUpdateFornecedor(-1, lab);
					novo.setVisible(true);
					forntm.updateTable(lab.getArrayFornecedores());
				}
			});
			btnNovo.setBounds(87, 10, 85, 21);
			panel.add(btnNovo);

			btnAlterar = new JButton("Alterar");
			btnAlterar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (table.getSelectedRow() != -1) {
						int rowSel = table.getSelectedRow();
						int indexRowModel;
						if (table.getRowSorter() == null)
							indexRowModel = table.getSelectedRow();
						else
							indexRowModel = table.getRowSorter().convertRowIndexToModel(rowSel);

						GUICreateUpdateFornecedor alterar = new GUICreateUpdateFornecedor(indexRowModel, lab);
						alterar.setVisible(true);
						forntm.updateTable(lab.getArrayFornecedores());
					} else {
						JOptionPane.showMessageDialog(TelaFornecedores.this, "Por favor selecione uma linha",
								"N�o foi poss�vel alterar", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
			btnAlterar.setEnabled(false);
			btnAlterar.setBounds(182, 10, 85, 21);
			panel.add(btnAlterar);

			btnExcluir = new JButton("Excluir");
			btnExcluir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (table.getSelectedRow() != -1) {
						int rowSel = table.getSelectedRow();
						int indexRowModel;
						if (table.getRowSorter() == null)
							indexRowModel = table.getSelectedRow();
						else
							indexRowModel = table.getRowSorter().convertRowIndexToModel(rowSel);
						int opcao = JOptionPane.showConfirmDialog(
								TelaFornecedores.this, "Tem certeza que deseja excluir o fornecedor"
										+ forntm.getForn(indexRowModel).getNome() + "?",
								"Excluir", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (opcao == JOptionPane.OK_OPTION) {
							if (lab.excluirFornecedor(forntm.getForn(indexRowModel).getCod())) {
								JOptionPane.showMessageDialog(TelaFornecedores.this, "Fornecedor exclu�do com sucesso",
										"Sucesso", JOptionPane.INFORMATION_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(TelaFornecedores.this,
										"Ocorreu um erro no sistema de exclus�o", "Erro", JOptionPane.ERROR_MESSAGE);
							}
							forntm.updateTable(lab.getArrayFornecedores());
						}
					} else {
						JOptionPane.showMessageDialog(TelaFornecedores.this, "Por favor selecione uma linha",
								"N�o foi poss�vel excluir", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			btnExcluir.setEnabled(false);
			btnExcluir.setBounds(273, 10, 85, 21);
			panel.add(btnExcluir);
		}

		private void pesquisar(java.awt.event.KeyEvent evt) {
			final TableRowSorter<FornTableModel> sorter = new TableRowSorter<FornTableModel>(forntm);
			table.setRowSorter(sorter);
			String text = txtPesquisa.getText();
			if (text.length() == 0) {
				sorter.setRowFilter(null);
			} else {
				try {
					sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, filtro));
				} catch (PatternSyntaxException pse) {
					JOptionPane.showMessageDialog(TelaFornecedores.this, "Erro", "Ocorreu um erro durante a pesquisa",
							JOptionPane.ERROR_MESSAGE);
					txtPesquisa.setText("");
				}
			}
		}

		private void addPopup(Component component, final JPopupMenu popup) {
			component.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					if (e.isPopupTrigger()) {
						showMenu(e);
					}
				}

				public void mouseReleased(MouseEvent e) {
					if (e.isPopupTrigger()) {
						showMenu(e);
					}
				}

				private void showMenu(MouseEvent e) {
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			});
		}
	}

	private class TelaDespesa extends JPanel {

		private JButton btnExcluir;
		private JTextField txtPesquisa;
		private JTable table;
		private ILaboratorio lab;
		private int filtro = 0;

		public TelaDespesa(final ILaboratorio lab) {
			this.lab = lab;
			setBounds(0, 0, 1366, 768);
			setLayout(null);

			JPanel headPanel = new JPanel();
			headPanel.setBackground(Color.WHITE);
			headPanel.setBounds(0, 0, 1368, 137);
			add(headPanel);
			headPanel.setLayout(null);

			JButton btBack = new JButton("");
			btBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cardFinanceiro();
				}
			});
			btBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btBack.setBounds(0, 5, 64, 40);
			headPanel.add(btBack);
			btBack.setFocusPainted(false);
			btBack.setContentAreaFilled(false);
			btBack.setBorderPainted(false);
			btBack.setIcon(
					new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/back.png"))));

			JLabel lbDespesas = new JLabel("Despesas");
			lbDespesas.setBounds(27, 45, 634, 66);
			headPanel.add(lbDespesas);
			lbDespesas.setForeground(new Color(102, 102, 255));
			lbDespesas.setFont(new Font("Tahoma", Font.BOLD, 54));

			JToolBar toolBar = new JToolBar();
			toolBar.setFloatable(false);
			toolBar.setBounds(289, 79, 416, 25);
			headPanel.add(toolBar);

			JMenuBar menuBar = new JMenuBar();
			toolBar.add(menuBar);

			JMenuItem btDespesas = new JMenuItem("Despesas");
			btDespesas.setForeground(Color.BLUE);
			menuBar.add(btDespesas);
			final JMenuItem btFixas = new JMenuItem("Despesas fixas");
			btFixas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cardDespesaFixa();
				}
			});
			btFixas.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent arg0) {
					getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					btFixas.setForeground(Color.GRAY);
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					btFixas.setForeground(Color.BLACK);
				}
			});

			menuBar.add(btFixas);

			JSeparator separator_1 = new JSeparator();
			separator_1.setBounds(0, 136, 1419, 1);
			headPanel.add(separator_1);

			JPanel tablePanel = new JPanel();
			tablePanel.setBounds(0, 136, 1368, 623);
			add(tablePanel);
			tablePanel.setLayout(null);

			JLabel lblPesquisar = new JLabel("Pesquisar:");
			lblPesquisar.setBounds(25, 14, 77, 17);
			tablePanel.add(lblPesquisar);
			lblPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 16));

			txtPesquisa = new JTextField();
			txtPesquisa.setBounds(105, 10, 446, 21);
			tablePanel.add(txtPesquisa);
			txtPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 16));
			txtPesquisa.setToolTipText("");
			txtPesquisa.setColumns(10);
			txtPesquisa.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent evt) {
					pesquisar(evt);
				}
			});

			final JButton btnFiltrar = new JButton("Filtrar");
			btnFiltrar.setBounds(553, 10, 85, 21);
			tablePanel.add(btnFiltrar);

			final JPopupMenu popupMenu = new JPopupMenu();
			addPopup(btnFiltrar, popupMenu);
			btnFiltrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					popupMenu.show(btnFiltrar, btnFiltrar.getWidth(), btnFiltrar.getHeight());
				}
			});

			JRadioButtonMenuItem fNome = new JRadioButtonMenuItem("Nome");
			fNome.setSelected(true);
			fNome.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					filtro = 0;
				}
			});
			popupMenu.add(fNome);

			JRadioButtonMenuItem fData = new JRadioButtonMenuItem("Data");
			fData.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					filtro = 1;
				}
			});
			popupMenu.add(fData);

			JRadioButtonMenuItem fValor = new JRadioButtonMenuItem("Valor");
			fValor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					filtro = 2;
				}
			});
			popupMenu.add(fValor);

			ButtonGroup gp = new ButtonGroup();

			gp.add(fNome);
			gp.add(fData);
			gp.add(fValor);

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 36, 1325, 454);
			tablePanel.add(scrollPane);

			despesatm = new DespesaTableModel(lab.getArrayDespesa());
			table = new JTable(despesatm);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setAutoCreateRowSorter(true);
			scrollPane.setViewportView(table);

			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if (table.getSelectedRow() != -1) {
						btnExcluir.setEnabled(true);
					} else {
						btnExcluir.setEnabled(false);
					}
				}
			});
			table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

				@Override
				public void valueChanged(ListSelectionEvent e) {
					if (table.getSelectedRow() != -1) {
						btnExcluir.setEnabled(true);
					} else {
						btnExcluir.setEnabled(false);
					}
				}
			});

			JPanel panel = new JPanel();
			panel.setBounds(10, 500, 1348, 113);
			tablePanel.add(panel);
			panel.setLayout(null);

			JButton btnRegistrar = new JButton("Registrar");
			btnRegistrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					GUICreateUpdateDespesa novo = new GUICreateUpdateDespesa(lab);
					novo.setVisible(true);
					despesatm.updateTable(lab.getArrayDespesa());
				}
			});
			btnRegistrar.setBounds(87, 10, 90, 21);
			panel.add(btnRegistrar);

			btnExcluir = new JButton("Excluir");
			btnExcluir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (table.getSelectedRow() != -1) {
						int rowSel = table.getSelectedRow();
						int indexRowModel;
						if (table.getRowSorter() == null)
							indexRowModel = table.getSelectedRow();
						else
							indexRowModel = table.getRowSorter().convertRowIndexToModel(rowSel);
						int opcao = JOptionPane.showConfirmDialog(TelaDespesa.this,
								"Tem certeza que deseja excluir a despesa "
										+ despesatm.getDespesa(indexRowModel).getNome() + "?",
								"Excluir", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (opcao == JOptionPane.OK_OPTION) {
							if (lab.removeDespesa(despesatm.getDespesa(indexRowModel).getCod())) {
								JOptionPane.showMessageDialog(TelaDespesa.this, "Despesa exclu�da com sucesso",
										"Sucesso", JOptionPane.INFORMATION_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(TelaDespesa.this,
										"Ocorreu um erro no sistema de exclus�o", "Erro", JOptionPane.ERROR_MESSAGE);
							}
							despesatm.updateTable(lab.getArrayDespesa());
						}
					} else {
						JOptionPane.showMessageDialog(TelaDespesa.this, "Por favor selecione uma linha",
								"N�o foi poss�vel excluir", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			btnExcluir.setEnabled(false);
			btnExcluir.setBounds(187, 10, 85, 21);
			panel.add(btnExcluir);
		}

		private void pesquisar(java.awt.event.KeyEvent evt) {
			final TableRowSorter<DespesaTableModel> sorter = new TableRowSorter<DespesaTableModel>(despesatm);
			table.setRowSorter(sorter);
			String text = txtPesquisa.getText();
			if (text.length() == 0) {
				sorter.setRowFilter(null);
			} else {
				try {
					sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, filtro));
				} catch (PatternSyntaxException pse) {
					JOptionPane.showMessageDialog(TelaDespesa.this, "Erro", "Ocorreu um erro durante a pesquisa",
							JOptionPane.ERROR_MESSAGE);
					txtPesquisa.setText("");
				}
			}
		}

		private void addPopup(Component component, final JPopupMenu popup) {
			component.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					if (e.isPopupTrigger()) {
						showMenu(e);
					}
				}

				public void mouseReleased(MouseEvent e) {
					if (e.isPopupTrigger()) {
						showMenu(e);
					}
				}

				private void showMenu(MouseEvent e) {
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			});
		}
	}

	private class TelaDespesaFixa extends JPanel {

		private JButton btnExcluir;
		private JTextField txtPesquisa;
		private JTable table;
		private ILaboratorio lab;
		private int filtro = 0;

		public TelaDespesaFixa(final ILaboratorio lab) {
			this.lab = lab;
			setBounds(0, 0, 1366, 768);
			setLayout(null);

			JPanel headPanel = new JPanel();
			headPanel.setBackground(Color.WHITE);
			headPanel.setBounds(0, 0, 1368, 137);
			add(headPanel);
			headPanel.setLayout(null);

			JButton btBack = new JButton("");
			btBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cardFinanceiro();
				}
			});
			btBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btBack.setBounds(0, 5, 64, 40);
			headPanel.add(btBack);
			btBack.setFocusPainted(false);
			btBack.setContentAreaFilled(false);
			btBack.setBorderPainted(false);
			btBack.setIcon(
					new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/back.png"))));

			JLabel lbDespesas = new JLabel("Despesas");
			lbDespesas.setBounds(27, 45, 634, 66);
			headPanel.add(lbDespesas);
			lbDespesas.setForeground(new Color(102, 102, 255));
			lbDespesas.setFont(new Font("Tahoma", Font.BOLD, 54));

			JToolBar toolBar = new JToolBar();
			toolBar.setFloatable(false);
			toolBar.setBounds(289, 79, 416, 25);
			headPanel.add(toolBar);

			JMenuBar menuBar = new JMenuBar();
			toolBar.add(menuBar);

			final JMenuItem btDespesas = new JMenuItem("Despesas");
			btDespesas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cardDespesa();
				}
			});
			btDespesas.setForeground(Color.BLACK);
			menuBar.add(btDespesas);
			btDespesas.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent arg0) {
					getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					btDespesas.setForeground(Color.GRAY);
				}

				public void mouseExited(MouseEvent arg0) {
					getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					btDespesas.setForeground(Color.BLACK);
				}
			});

			JMenuItem btFixas = new JMenuItem("Despesas fixas");
			btFixas.setForeground(Color.BLUE);

			menuBar.add(btFixas);

			JSeparator separator_1 = new JSeparator();
			separator_1.setBounds(0, 136, 1419, 1);
			headPanel.add(separator_1);

			JPanel tablePanel = new JPanel();
			tablePanel.setBounds(0, 136, 1368, 623);
			add(tablePanel);
			tablePanel.setLayout(null);

			JLabel lblPesquisar = new JLabel("Pesquisar:");
			lblPesquisar.setBounds(25, 14, 77, 17);
			tablePanel.add(lblPesquisar);
			lblPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 16));

			txtPesquisa = new JTextField();
			txtPesquisa.setBounds(105, 10, 446, 21);
			tablePanel.add(txtPesquisa);
			txtPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 16));
			txtPesquisa.setToolTipText("");
			txtPesquisa.setColumns(10);
			txtPesquisa.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent evt) {
					pesquisar(evt);
				}
			});

			final JButton btnFiltrar = new JButton("Filtrar");
			btnFiltrar.setBounds(553, 10, 85, 21);
			tablePanel.add(btnFiltrar);

			final JPopupMenu popupMenu = new JPopupMenu();
			addPopup(btnFiltrar, popupMenu);
			btnFiltrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					popupMenu.show(btnFiltrar, btnFiltrar.getWidth(), btnFiltrar.getHeight());
				}
			});

			JRadioButtonMenuItem fNome = new JRadioButtonMenuItem("Nome");
			fNome.setSelected(true);
			fNome.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					filtro = 0;
				}
			});
			popupMenu.add(fNome);

			JRadioButtonMenuItem fValor = new JRadioButtonMenuItem("Valor");
			fValor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					filtro = 1;
				}
			});
			popupMenu.add(fValor);

			ButtonGroup gp = new ButtonGroup();

			gp.add(fNome);
			gp.add(fValor);

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 36, 1325, 454);
			tablePanel.add(scrollPane);

			despesafixatm = new DespesaFixaTableModel(lab.getArrayDespesaFixa());
			table = new JTable(despesafixatm);
			table.setAutoCreateRowSorter(true);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scrollPane.setViewportView(table);

			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if (table.getSelectedRow() != -1) {
						btnExcluir.setEnabled(true);
					} else {
						btnExcluir.setEnabled(false);
					}
				}
			});
			table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

				@Override
				public void valueChanged(ListSelectionEvent e) {
					if (table.getSelectedRow() != -1) {
						btnExcluir.setEnabled(true);
					} else {
						btnExcluir.setEnabled(false);
					}
				}
			});

			JPanel panel = new JPanel();
			panel.setBounds(10, 500, 1348, 113);
			tablePanel.add(panel);
			panel.setLayout(null);

			JButton btnRegistrar = new JButton("Registrar");
			btnRegistrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					GUICreateUpdateDespesaFixa novo = new GUICreateUpdateDespesaFixa(lab);
					novo.setVisible(true);
					despesafixatm.updateTable(lab.getArrayDespesaFixa());
				}
			});
			btnRegistrar.setBounds(87, 10, 90, 21);
			panel.add(btnRegistrar);

			btnExcluir = new JButton("Excluir");
			btnExcluir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (table.getSelectedRow() != -1) {
						int rowSel = table.getSelectedRow();
						int indexRowModel;
						if (table.getRowSorter() == null)
							indexRowModel = table.getSelectedRow();
						else
							indexRowModel = table.getRowSorter().convertRowIndexToModel(rowSel);
						int opcao = JOptionPane.showConfirmDialog(TelaDespesaFixa.this,
								"Tem certeza que deseja excluir a despesa fixa "
										+ despesafixatm.getDespesa(indexRowModel).getNome() + "?",
								"Excluir", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (opcao == JOptionPane.OK_OPTION) {
							if (lab.removeDespesaFixa(despesafixatm.getDespesa(indexRowModel).getCod())) {
								JOptionPane.showMessageDialog(TelaDespesaFixa.this, "Despesa fixa exclu�da com sucesso",
										"Sucesso", JOptionPane.INFORMATION_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(TelaDespesaFixa.this,
										"Ocorreu um erro no sistema de exclus�o", "Erro", JOptionPane.ERROR_MESSAGE);
							}
							despesafixatm.updateTable(lab.getArrayDespesaFixa());
						}
					} else {
						JOptionPane.showMessageDialog(TelaDespesaFixa.this, "Por favor selecione uma linha",
								"N�o foi poss�vel excluir", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			btnExcluir.setEnabled(false);
			btnExcluir.setBounds(187, 10, 85, 21);
			panel.add(btnExcluir);
		}

		private void pesquisar(java.awt.event.KeyEvent evt) {
			final TableRowSorter<DespesaFixaTableModel> sorter = new TableRowSorter<DespesaFixaTableModel>(
					despesafixatm);
			table.setRowSorter(sorter);
			String text = txtPesquisa.getText();
			if (text.length() == 0) {
				sorter.setRowFilter(null);
			} else {
				try {
					sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, filtro));
				} catch (PatternSyntaxException pse) {
					JOptionPane.showMessageDialog(TelaDespesaFixa.this, "Erro", "Ocorreu um erro durante a pesquisa",
							JOptionPane.ERROR_MESSAGE);
					txtPesquisa.setText("");
				}
			}
		}

		private void addPopup(Component component, final JPopupMenu popup) {
			component.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					if (e.isPopupTrigger()) {
						showMenu(e);
					}
				}

				public void mouseReleased(MouseEvent e) {
					if (e.isPopupTrigger()) {
						showMenu(e);
					}
				}

				private void showMenu(MouseEvent e) {
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			});
		}
	}

	private class TelaResumo extends JPanel {
		private ILaboratorio lab;
		private int filtro = 0;
		private DespesaTableModel despesatm;
		private JCheckBox cbSituacao;
		private JDateChooser dtInicio;
		private JDateChooser dtFim;
		private JCheckBox chckPgmto;
		private JCheckBox chckSaida;
		private JCheckBox chckDespesas;
		private JCheckBox chckDFixas;
		private AbstractButton chckCompras;
		private JLabel lblResumo;

		private String pPeriodo;
		private String pServicos;
		private String pDespesa;
		private String pFixo;
		private String pCompra;
		private String pLucro;
		private JButton btnGerarRelatrio;

		public TelaResumo(ILaboratorio lab) {
			this.lab = lab;
			setBounds(0, 0, 1366, 768);
			setLayout(null);

			JPanel headPanel = new JPanel();
			headPanel.setBackground(Color.WHITE);
			headPanel.setBounds(0, 0, 1368, 137);
			add(headPanel);
			headPanel.setLayout(null);

			JLabel lbResumo = new JLabel("Resumo financeiro");
			lbResumo.setBounds(27, 45, 634, 66);
			headPanel.add(lbResumo);
			lbResumo.setForeground(new Color(102, 102, 255));
			lbResumo.setFont(new Font("Tahoma", Font.BOLD, 54));

			JButton btBack = new JButton("");
			btBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cardFinanceiro();
				}
			});
			btBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btBack.setBounds(0, 5, 64, 40);
			headPanel.add(btBack);
			btBack.setFocusPainted(false);
			btBack.setContentAreaFilled(false);
			btBack.setBorderPainted(false);
			btBack.setIcon(
					new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/back.png"))));

			JSeparator separator_1 = new JSeparator();
			separator_1.setBounds(0, 136, 1419, 1);
			headPanel.add(separator_1);

			JPanel tablePanel = new JPanel();
			tablePanel.setBounds(0, 136, 1368, 623);
			add(tablePanel);
			tablePanel.setLayout(null);

			ButtonGroup gp = new ButtonGroup();

			despesatm = new DespesaTableModel(lab.getArrayDespesa());

			JPanel panelFiltros = new JPanel();
			panelFiltros
					.setBorder(new TitledBorder(null, "Filtrar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelFiltros.setLayout(null);
			panelFiltros.setBounds(10, 10, 1317, 113);
			tablePanel.add(panelFiltros);

			JPanel panelPeriodo = new JPanel();
			panelPeriodo.setBorder(
					new TitledBorder(null, "Per\u00EDodo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelPeriodo.setBounds(10, 22, 302, 81);
			panelFiltros.add(panelPeriodo);
			panelPeriodo.setLayout(null);

			dtFim = new JDateChooser();
			dtFim.setBounds(165, 52, 127, 19);
			panelPeriodo.add(dtFim);
			dtFim.setDateFormatString("dd/MM/yyyy");
			dtFim.setToolTipText("Data final (dia/m�s/ano");

			JLabel lblDataFinal = new JLabel("Data final");
			lblDataFinal.setBounds(165, 22, 127, 19);
			panelPeriodo.add(lblDataFinal);
			lblDataFinal.setFont(new Font("Tahoma", Font.PLAIN, 15));

			dtInicio = new JDateChooser();
			dtInicio.setBounds(10, 52, 127, 19);
			panelPeriodo.add(dtInicio);
			dtInicio.setDateFormatString("dd/MM/yyyy");
			dtInicio.setToolTipText("Data inicial (dia/m�s/ano");

			JLabel lblDataInicial = new JLabel("Data inicial");
			lblDataInicial.setBounds(10, 22, 127, 19);
			panelPeriodo.add(lblDataInicial);
			lblDataInicial.setFont(new Font("Tahoma", Font.PLAIN, 15));

			JPanel panelServicos = new JPanel();
			panelServicos.setLayout(null);
			panelServicos.setBorder(new TitledBorder(
					new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
					"Servi\u00E7os", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelServicos.setBounds(322, 22, 469, 81);
			panelFiltros.add(panelServicos);

			JLabel lblSituaoDosServios = new JLabel("Situa\u00E7\u00E3o dos servi\u00E7os");
			lblSituaoDosServios.setBounds(10, 21, 173, 21);
			panelServicos.add(lblSituaoDosServios);
			lblSituaoDosServios.setFont(new Font("Tahoma", Font.PLAIN, 15));

			cbSituacao = new JCheckBox("Servi�o finalizado");
			cbSituacao.setBounds(10, 50, 142, 21);
			cbSituacao.setFont(new Font("Tahoma", Font.PLAIN, 15));
			cbSituacao.setSelected(true);
			panelServicos.add(cbSituacao);

			chckPgmto = new JCheckBox("Pagamento recebido");
			chckPgmto.setSelected(true);
			chckPgmto.setFont(new Font("Tahoma", Font.PLAIN, 15));
			chckPgmto.setBounds(189, 21, 163, 21);
			panelServicos.add(chckPgmto);

			chckSaida = new JCheckBox("Servi\u00E7o teve sa\u00EDda");
			chckSaida.setSelected(true);
			chckSaida.setFont(new Font("Tahoma", Font.PLAIN, 15));
			chckSaida.setBounds(189, 50, 163, 21);
			panelServicos.add(chckSaida);

			JPanel panelGastos = new JPanel();
			panelGastos.setLayout(null);
			panelGastos.setBorder(new TitledBorder(
					new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
					"Gastos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelGastos.setBounds(801, 22, 369, 81);
			panelFiltros.add(panelGastos);

			chckDespesas = new JCheckBox("Incluir despesas");
			chckDespesas.setSelected(true);
			chckDespesas.setFont(new Font("Tahoma", Font.PLAIN, 15));
			chckDespesas.setBounds(6, 19, 163, 21);
			panelGastos.add(chckDespesas);

			chckDFixas = new JCheckBox("Incluir despesas fixas");
			chckDFixas.setSelected(true);
			chckDFixas.setFont(new Font("Tahoma", Font.PLAIN, 15));
			chckDFixas.setBounds(6, 43, 281, 21);
			panelGastos.add(chckDFixas);

			chckCompras = new JCheckBox("Incluir compras");
			chckCompras.setSelected(true);
			chckCompras.setFont(new Font("Tahoma", Font.PLAIN, 15));
			chckCompras.setBounds(180, 21, 163, 21);
			panelGastos.add(chckCompras);

			JButton btnGerarResumo = new JButton("Gerar resumo");
			btnGerarResumo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					validaCampos();
				}
			});
			btnGerarResumo.setBounds(1180, 82, 127, 21);
			panelFiltros.add(btnGerarResumo);

			btnGerarRelatrio = new JButton("Gerar relat\u00F3rio");
			btnGerarRelatrio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					rf.relatorio(lblResumo, pPeriodo, pServicos, pDespesa, pFixo, pCompra, pLucro);
				}
			});
			btnGerarRelatrio.setBounds(1180, 18, 127, 21);
			panelFiltros.add(btnGerarRelatrio);
			btnGerarRelatrio.setEnabled(false);

			lblResumo = new JLabel("");
			lblResumo.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblResumo.setVerticalAlignment(SwingConstants.TOP);
			lblResumo.setBounds(10, 10, 1287, 462);

			JScrollPane panelResumo = new JScrollPane(lblResumo, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			panelResumo.setBounds(20, 131, 1307, 400);
			tablePanel.add(panelResumo);
			panelResumo.setLayout(null);
			panelResumo.add(lblResumo);

		}

		private void validaCampos() {
			if (dtFim.getDate() != null && dtInicio.getDate() != null) {
				if (dtFim.getDate().after(dtInicio.getDate()) && dtInicio.getDate().before(dtFim.getDate())) {
					gerarResumo();
				} else
					JOptionPane.showMessageDialog(TelaResumo.this, "Insira um per�odo v�lido", "Aviso",
							JOptionPane.WARNING_MESSAGE);
			} else
				JOptionPane.showMessageDialog(TelaResumo.this, "Insira um per�odo v�lido", "Aviso",
						JOptionPane.WARNING_MESSAGE);
		}

		private void gerarResumo() {
			btnGerarRelatrio.setEnabled(true);
			int qtdServicos = 0;
			double valorServicos = 0;

			int qtdDespesas = 0;
			double valorDespesas = 0;

			int qtdFixas = 0;
			double valorFixas = 0;

			int qtdCompras = 0;
			double valorCompras = 0;

			ArrayList<Servicos> s = lab.getArrayServicos();
			boolean saida = chckSaida.isSelected();
			boolean pagamento = chckPgmto.isSelected();
			boolean situacao = cbSituacao.isSelected();

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String dataInicio = sdf.format(dtInicio.getDate());
			String dataFim = sdf.format(dtFim.getDate());

			if (s != null) {
				if (s.size() > 0) {
					boolean test;
					for (int i = 0; i < s.size(); i++) {
						test = true;
						if (pagamento)
							if (!(s.get(i).isPago()))
								test = false;
						if (saida) {
							if (!(s.get(i).getSituacao().equals("FINALIZADO")
									|| s.get(i).getSituacao().equals("PROVA"))) {
								test = false;
							} else {
								if (test) {
									String s1 = sdf.format(s.get(i).getSaida());
									if ((s.get(i).getSaida().after(dtInicio.getDate()) || s1.equals(dataInicio))
											&& (s.get(i).getSaida().before(dtFim.getDate()) || s1.equals(dataFim))) {
										if (situacao) {
											if (s.get(i).getSituacao().equals("FINALIZADO")) {
												qtdServicos = qtdServicos + 1;
												valorServicos = valorServicos + s.get(i).getValorFinal();
											}
										} else {
											qtdServicos = qtdServicos + 1;
											valorServicos = valorServicos + s.get(i).getValorFinal();
										}
									}
								}
							}
						} else {
							if (test) {
								String s1 = sdf.format(s.get(i).getEntrada());
								if ((s.get(i).getEntrada().after(dtInicio.getDate()) || s1.equals(dataInicio))
										&& (s.get(i).getEntrada().before(dtFim.getDate()) || s1.equals(dataFim))) {
									if (situacao) {
										if (s.get(i).getSituacao().equals("FINALIZADO")) {
											qtdServicos = qtdServicos + 1;
											valorServicos = valorServicos + s.get(i).getValorFinal();
										}
									} else {
										qtdServicos = qtdServicos + 1;
										valorServicos = valorServicos + s.get(i).getValorFinal();
									}
								}
							}
						}
					}
				} else {
					JOptionPane.showMessageDialog(TelaResumo.this, "N�o foi encontrado nenhum servi�o no sistema",
							"Aviso", JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(TelaResumo.this, "N�o foi encontrado nenhum servi�o no sistema", "Aviso",
						JOptionPane.WARNING_MESSAGE);
			}

			ArrayList<Despesa> d = lab.getArrayDespesa();

			if (chckDespesas.isSelected()) {
				if (d != null) {
					if (d.size() > 0) {
						for (int i = 0; i < d.size(); i++) {
							String d1 = sdf.format(d.get(i).getData());
							if (((d.get(i).getData().after(dtInicio.getDate())) || (d1.equals(dataInicio)))
									&& (d.get(i).getData().before(dtFim.getDate()) || d1.equals(dataFim))) {
								qtdDespesas = qtdDespesas + 1;
								valorDespesas = valorDespesas + d.get(i).getValor();
							}
						}
					} else {
						JOptionPane.showMessageDialog(TelaResumo.this, "N�o foi encontrado nenhuma despesa no sistema",
								"Aviso", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(TelaResumo.this, "N�o foi encontrado nenhuma despesa no sistema",
							"Aviso", JOptionPane.WARNING_MESSAGE);
				}
			}

			ArrayList<Despesa> f = lab.getArrayDespesaFixa();

			if (chckDFixas.isSelected()) {
				if (f != null) {
					if (f.size() > 0) {
						for (int i = 0; i < f.size(); i++) {
							qtdFixas = qtdFixas + 1;
							valorFixas = valorFixas + f.get(i).getValor();
						}
					} else {
						JOptionPane.showMessageDialog(TelaResumo.this,
								"N�o foi encontrado nenhuma despesa fixa no sistema", "Aviso",
								JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(TelaResumo.this, "N�o foi encontrado nenhuma despesa fixa no sistema",
							"Aviso", JOptionPane.WARNING_MESSAGE);
				}
			}

			ArrayList<Compra> c = lab.getArrayCompras();
			if (chckCompras.isSelected()) {
				if (c != null) {
					if (c.size() > 0) {
						for (int i = 0; i < c.size(); i++) {
							String c1 = sdf.format(c.get(i).getData());
							if ((c.get(i).getData().after(dtInicio.getDate()) || c1.equals(dataInicio))
									&& (c.get(i).getData().before(dtFim.getDate()) || c1.equals(dataFim))) {
								qtdCompras = qtdCompras + 1;
								valorCompras = valorCompras + c.get(i).getValor();
							}
						}
					} else {
						JOptionPane.showMessageDialog(TelaResumo.this, "N�o foi encontrado nenhuma compra no sistema",
								"Aviso", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(TelaResumo.this, "N�o foi encontrado nenhuma compra no sistema",
							"Aviso", JOptionPane.WARNING_MESSAGE);
				}
			}

			escrever(qtdServicos, valorServicos, qtdDespesas, valorDespesas, qtdFixas, valorFixas, qtdCompras,
					valorCompras);
		}

		private void escrever(int qtdServicos, double vServicos, int qtdDespesas, double vDespesas, int qtdFixas,
				double vFixas, int qtdCompras, double vCompras) {
			DecimalFormat df = new DecimalFormat("0.##");
			String valorServicos = df.format(vServicos);
			String valorDespesas = df.format(vDespesas);
			String valorFixas = df.format(vFixas);
			String valorCompras = df.format(vCompras);
			pPeriodo = null;
			pServicos = null;
			pDespesa = null;
			pFixo = null;
			pCompra = null;
			pLucro = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			lblResumo.setText("<html>");
			lblResumo.setText(lblResumo.getText() + "<b> Per�odo de " + sdf.format(dtInicio.getDate()) + " at� "
					+ sdf.format(dtFim.getDate()) + "</b><br>");
			pPeriodo = "Per�odo de " + sdf.format(dtInicio.getDate()) + " at� " + sdf.format(dtFim.getDate());
			if (qtdServicos > 0) {
				lblResumo.setText(lblResumo.getText() + "<b><font color=blue>Servi�os</font></b>");
				pServicos = "Servi�os";
				if (chckPgmto.isSelected()) {
					pServicos = pServicos + " [PAGAMENTO COBRADO]";
					lblResumo.setText(lblResumo.getText() + " [PAGAMENTO COBRADO]");
				}
				if (chckSaida.isSelected()) {
					lblResumo.setText(lblResumo.getText() + " [SERVI�O COM SA�DA] ");
					pServicos = pServicos + " [SERVI�O COM SA�DA]";
				}
				lblResumo.setText(lblResumo.getText() + "<br>");
				lblResumo.setText(lblResumo.getText() + "<b>  Quantidade de servi�os nesse per�odo: </b>" + qtdServicos
						+ "<br><b>  Valor total dos servi�os: </b>R$" + valorServicos + "<br>");
				pServicos = pServicos + "\nQuantidade de servi�os nesse per�odo: " + qtdServicos
						+ "\nValor total dos servi�os: R$" + valorServicos;
			} else {
				lblResumo.setText(
						lblResumo.getText() + "<br><font color=red>  Nenhum servi�o no per�odo selecionado</font><br>");
				pServicos = "Nenhum servi�o no per�odo selecionado";
			}
			if (chckDespesas.isSelected()) {
				if (qtdDespesas > 0) {
					lblResumo.setText(lblResumo.getText() + "<br><b><font color=blue>Despesas</font></b><br>");
					lblResumo.setText(lblResumo.getText() + "<b>  Quantidade de despesas nesse per�odo: </b>"
							+ qtdDespesas + "<br><b>  Valor total das despesas: </b>R$" + valorDespesas + "<br>");
					pDespesa = "Despesas\nQuantidade de despesas nesse per�odo: " + qtdDespesas
							+ "\nValor total das despesas: R$" + valorDespesas;
				} else {
					lblResumo.setText(lblResumo.getText()
							+ "<br><font color=red>  Nenhuma despesa no per�odo selecionado</font><br>");
					pDespesa = "Nenhuma despesa no per�odo selecionado";
				}
			}
			if (chckDFixas.isSelected()) {
				if (qtdFixas > 0) {
					pFixo = "Despesas fixas\nQuantidade de despesas fixas nesse per�odo: " + qtdFixas
							+ "\n Valor total das despesas fixas: R$" + valorFixas;
					lblResumo.setText(lblResumo.getText() + "<br><b><font color=blue>Despesas fixas</font></b><br>");
					lblResumo.setText(lblResumo.getText() + "<b>  Quantidade de despesas fixas nesse per�odo: </b>"
							+ qtdFixas + "<br><b>  Valor total das despesas fixas: </b>R$" + valorFixas + "<br>");
				} else {
					pFixo = "Nenhuma despesa fixa no per�odo selecionado";
					lblResumo.setText(lblResumo.getText()
							+ "<br><font color=red>  Nenhuma despesa fixa no per�odo selecionado</font><br>");
				}
			}
			if (chckCompras.isSelected()) {
				if (qtdCompras > 0) {
					pCompra = "Compras\nQuantidade de compras nesse per�odo: " + qtdCompras
							+ "\nValor total das compras: R$" + valorCompras;
					lblResumo.setText(lblResumo.getText() + "<br><b><font color=blue>Compras</font></b><br>");
					lblResumo.setText(lblResumo.getText() + "<b>  Quantidade de compras nesse per�odo: </b>"
							+ qtdCompras + "<br><b>  Valor total das compras: </b>R$" + valorCompras + "<br>");
				} else {
					pCompra = "Nenhuma compra no per�odo selecionado";
					lblResumo.setText(lblResumo.getText()
							+ "<br><font color=red>  Nenhuma compra no per�odo selecionado</font></p><br>");
				}
			}
			double gasto = vCompras + vDespesas + vFixas;
			double lucro = vServicos - gasto;
			String lu = df.format(lucro);
			String l;
			if (lucro <= 0) {
				l = "<font color=red>R$" + lu + "</font>";
			} else {
				l = "R$" + lu;
			}
			String g = df.format(gasto);
			pLucro = "Gasto total nesse per�odo: R$" + g + "\nLucro: R$" + df.format(lucro);
			lblResumo.setText(lblResumo.getText() + "<br><b>Gasto total nesse per�odo: </b>R$" + g
					+ "<br><b>Lucro: </b>" + l + "<br>");
		}

		private void addPopup(Component component, final JPopupMenu popup) {
		}
	}

	private class Login extends JPanel {
		private JPasswordField passwordField;
		private String passwd;

		public Login() throws IOException {
			passwd = carregarPassword();
			setBounds(100, 100, 1366, 768);
			setLayout(null);

			JPanel panel = new JPanel();
			panel.setBounds(10, 10, 1348, 749);
			add(panel);
			panel.setLayout(null);

			JLabel label = new JLabel("AR");
			label.setForeground(Color.BLACK);
			label.setFont(new Font("Century Gothic", Font.PLAIN, 65));
			label.setBounds(602, 259, 97, 91);
			panel.add(label);

			JLabel label_1 = new JLabel("T");
			label_1.setForeground(Color.BLACK);
			label_1.setFont(new Font("Dialog", Font.PLAIN, 185));
			label_1.setBounds(661, 151, 232, 225);
			panel.add(label_1);

			JLabel label_2 = new JLabel("DEN");
			label_2.setForeground(Color.BLACK);
			label_2.setFont(new Font("Century Gothic", Font.PLAIN, 65));
			label_2.setBounds(549, 203, 147, 91);
			panel.add(label_2);

			JLabel label_3 = new JLabel("Sistema");
			label_3.setFont(new Font("Century Gothic", Font.PLAIN, 20));
			label_3.setBounds(555, 190, 74, 26);
			panel.add(label_3);

			passwordField = new JPasswordField();
			passwordField.setBounds(545, 349, 243, 30);
			passwordField.addKeyListener(new KeyListener() {
				@Override
				public void keyPressed(KeyEvent evt) {
					if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
						logar();
					}
				}

				@Override
				public void keyReleased(KeyEvent arg0) {
				}

				@Override
				public void keyTyped(KeyEvent arg0) {
				}
			});
			panel.add(passwordField);

			JButton btnEntrar = new JButton("Entrar");
			btnEntrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					logar();

				}
			});
			btnEntrar.setBounds(625, 386, 85, 21);
			panel.add(btnEntrar);
		}

		protected void logar() {
			if (String.valueOf(passwordField.getPassword()).length() > 0)
				if (String.valueOf(passwordField.getPassword()).equals(fromHexString(passwd))) {
					menuBar.setVisible(true);
					cardInicio();

				} else {
					JOptionPane.showMessageDialog(Login.this, "Senha incorreta, tente novamente",
							"N�o foi poss�vel realizar login", JOptionPane.ERROR_MESSAGE);
				}

		}

		private String carregarPassword() throws IOException {
			String user = System.getProperty("user.home");
			List<String> linhas = null;
			File diretorio = new File(user + "\\.sistemaDentArt");
			if (!diretorio.exists()) {
				diretorio.mkdirs();
			}
			File arq = new File(diretorio, "passwd.txt");
			if (!arq.exists())
				arq.createNewFile();
			else {
				Scanner leitor = new Scanner(arq);
				linhas = new ArrayList<>();
				while (leitor.hasNextLine()) {
					linhas.add(leitor.nextLine());
				}

			}
			return linhas.get(0);
		}

		public void escreverPassword() throws IOException {
			String user = System.getProperty("user.home");
			File diretorio = new File(user + "\\.sistemaDentArt");
			if (!diretorio.exists()) {
				diretorio.mkdirs();
			}
			File arq = new File(diretorio, "passwd.txt");
			if (!arq.exists())
				arq.createNewFile();
			String path = arq.getAbsolutePath();
			BufferedWriter arqPasswd = new BufferedWriter(new FileWriter(path));
			arqPasswd.write(passwd);
			arqPasswd.close();
		}

		private String toHexString(String input) {
			return String.format("%x", new BigInteger(1, input.getBytes()));
		}

		private String fromHexString(String hex) {
			StringBuilder str = new StringBuilder();
			for (int i = 0; i < hex.length(); i += 2) {
				str.append((char) Integer.parseInt(hex.substring(i, i + 2), 16));
			}
			return str.toString();
		}

		protected void ChangePasswd() throws IOException {
			JPasswordField password = new JPasswordField(10);
			password.setRequestFocusEnabled(true);
			JLabel rotulo = new JLabel("Digite a senha atual:");
			JPanel entUsuario = new JPanel();
			entUsuario.add(rotulo);
			entUsuario.add(password);
			JOptionPane.showMessageDialog(null, entUsuario, "Mudar senha", JOptionPane.PLAIN_MESSAGE);
			if (!(String.valueOf(password.getPassword()).equals(""))) {
				if (String.valueOf(password.getPassword()).equals(fromHexString(passwd))) {
					JPasswordField senha1 = new JPasswordField(10);
					senha1.setRequestFocusEnabled(true);
					JLabel rotulo1 = new JLabel("Digite a nova senha:");
					JPanel entUsuario1 = new JPanel();
					entUsuario1.add(rotulo1);
					entUsuario1.add(senha1);
					JOptionPane.showMessageDialog(null, entUsuario1, "Mudar senha", JOptionPane.PLAIN_MESSAGE);
					JPasswordField senha2 = new JPasswordField(10);
					senha2.setRequestFocusEnabled(true);
					JLabel rotulo2 = new JLabel("Confirme a nova senha:");
					JPanel entUsuario2 = new JPanel();
					entUsuario2.add(rotulo2);
					entUsuario2.add(senha2);
					JOptionPane.showMessageDialog(null, entUsuario2, "Mudar senha", JOptionPane.PLAIN_MESSAGE);
					if (String.valueOf(senha1.getPassword()).length() > 0
							&& String.valueOf(senha1.getPassword()).equals(String.valueOf(senha2.getPassword()))) {
						passwd = toHexString(String.valueOf(senha1.getPassword()));
						escreverPassword();
						JOptionPane.showMessageDialog(Login.this, "Senha alterada com sucesso", "Mudar senha",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(Login.this, "N�o foi poss�vel continuar: Senhas n�o coincidem",
								"Mudar senha", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(Login.this, "N�o foi poss�vel continuar: Senha incorreta",
							"Mudar senha", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}