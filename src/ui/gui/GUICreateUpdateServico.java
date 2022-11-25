package ui.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JToggleButton;
import javax.swing.JTextField;
import com.toedter.calendar.JDayChooser;

import sistema.classes.Cliente;
import sistema.classes.Dentista;
import sistema.classes.Funcionario;
import sistema.classes.Paciente;
import sistema.classes.Servicos;
import sistema.classes.TipoServico;
import sistema.interfaces.ILaboratorio;

import com.toedter.calendar.JDateChooser;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.ComponentOrientation;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.awt.event.ItemEvent;

public class GUICreateUpdateServico extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfValor;
	private JButton okButton;
	private JButton cancelButton;
	private ILaboratorio lab;
	private JComboBox cbCliente;
	private JComboBox cbTipo;
	private JComboBox cbPaciente;
	private JComboBox cbFuncionario;
	private int modo;
	private int x = 1;
	private JDateChooser dtEntrada;
	private JDateChooser dtSaida;
	private JTextArea tpDesc;
	private JComboBox cbSituacao;
	private JToggleButton btPagamento;
	private JRadioButton rbConvenio;
	private JRadioButton rbParticular;
	private long cod;
	private JCheckBox chckbxServioEntregue;
	private JComboBox comboBox;
	private JCheckBox chckbxPagamentoRecebido;
	private boolean pagou = false;
	private JTextField tfComissao;
	private JLabel lblComissao;
	private JLabel lblObrigatrio;
	private JPanel buttonPane;

	public GUICreateUpdateServico(final int modo, final ILaboratorio lab) {
		this.lab = lab;
		this.modo = modo;
		if (modo == -1)
			setTitle("Novo servi\u00E7o");
		else
			setTitle("Alterar serviço");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/icon.png")));
		setModal(true);
//		if (modo == -1)
//			setBounds(100, 100, 651, 497);
//		else
		setBounds(100, 100, 652, 558);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panelServico = new JPanel();
		panelServico
				.setBorder(new TitledBorder(null, "Servi\u00E7o", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelServico.setBounds(10, 10, 311, 402);
		contentPanel.add(panelServico);
		panelServico.setLayout(null);

		JLabel label = new JLabel("*");
		label.setForeground(Color.RED);
		label.setBounds(111, 32, 37, 13);
		panelServico.add(label);
		{
			JLabel lbTipo = new JLabel("Tipo de servi\u00E7o");
			lbTipo.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lbTipo.setBounds(10, 28, 144, 25);
			panelServico.add(lbTipo);
		}

		cbTipo = new JComboBox();
		cbTipo.setFont(new Font("Tahoma", Font.PLAIN, 10));
		cbTipo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					DecimalFormat df = new DecimalFormat("0.##");
					tfValor.setText(
							String.valueOf(df.format(lab.getArrayTipos().get(cbTipo.getSelectedIndex()).getPreco())));
					if (cbTipo.getSelectedIndex() == 0) {
						tfComissao.setVisible(true);
						lblComissao.setVisible(true);
						tfComissao.setText(String.valueOf(df.format(lab.getArrayTipos().get(cbTipo.getSelectedIndex()).getPreco())));
						setBounds(100, 100, 652, 558);
						buttonPane.setBounds(10, 480, 612, 31);
						lblObrigatrio.setBounds(10, 468, 107, 13);
					} else {
						tfComissao.setVisible(false);
						lblComissao.setVisible(false);
						if (modo == -1) {
							setBounds(100, 100, 651, 497);
							buttonPane.setBounds(10, 422, 612, 31);
							lblObrigatrio.setBounds(13, 413, 107, 13);
						}
					}
				} catch (Exception e) {
					
				}
			}
		});
		cbTipo.setBounds(10, 54, 236, 21);
		panelServico.add(cbTipo);

		JButton btNovoTipo = new JButton("");
		btNovoTipo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btNovoTipo
				.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/plus.png"))));
		btNovoTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GUICreateUpdateTipoServicos novo = new GUICreateUpdateTipoServicos(-1, lab);
				novo.setVisible(true);
				carregarCbTipo();
				if (cbTipo.getItemCount() > 0) {
					cbTipo.setSelectedIndex(cbTipo.getItemCount() - 1);
				}
			}
		});
		btNovoTipo.setBounds(251, 48, 32, 32);
		panelServico.add(btNovoTipo);

		JLabel lbDescricao = new JLabel("Descri\u00E7\u00E3o e observa\u00E7\u00F5es");
		lbDescricao.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbDescricao.setBounds(10, 85, 236, 25);
		panelServico.add(lbDescricao);

		JLabel lblValor = new JLabel("Valor");
		lblValor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblValor.setBounds(10, 281, 87, 25);
		panelServico.add(lblValor);

		final JToggleButton btAjustarValor = new JToggleButton("Ajustar");
		btAjustarValor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (btAjustarValor.isSelected()) {
					tfValor.setEditable(true);
				} else {
					tfValor.setEditable(false);
				}
			}
		});
		btAjustarValor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btAjustarValor.setBounds(117, 308, 115, 21);
		panelServico.add(btAjustarValor);

		tfValor = new JTextField();
		tfValor.setEditable(false);
		tfValor.setBounds(10, 309, 96, 19);
		panelServico.add(tfValor);
		tfValor.setColumns(10);
		tfValor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ev) {
				// código do evento:
				String caracteres = "0987654321,.";
				if (!(caracteres.contains(ev.getKeyChar() + ""))) {
					ev.consume();
				}
			}
		});

		dtEntrada = new JDateChooser();
		dtEntrada.getCalendarButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		dtEntrada.setBounds(10, 362, 126, 19);
		dtEntrada.setDateFormatString("dd/MM/yyyy");
		dtEntrada.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent ev) {
				// código do evento:
				String caracteres = "0987654321/";
				if (!(caracteres.contains(ev.getKeyChar() + ""))) {
					ev.consume();
				}
			}
		});
		panelServico.add(dtEntrada);

		dtSaida = new JDateChooser();
		dtSaida.getCalendarButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		dtSaida.setBounds(162, 362, 126, 19);
		dtSaida.setEnabled(true);
		dtSaida.setDateFormatString("dd/MM/yyyy");
		dtSaida.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ev) {
				// código do evento:
				String caracteres = "0987654321/";
				if (!(caracteres.contains(ev.getKeyChar() + ""))) {
					ev.consume();
				}
			}
		});
		panelServico.add(dtSaida);

		JLabel lblDataDeEntrada = new JLabel("Data de Entrada");
		lblDataDeEntrada.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDataDeEntrada.setBounds(10, 332, 126, 32);
		panelServico.add(lblDataDeEntrada);

		JLabel lblDataDeSada = new JLabel("Data de Sa\u00EDda");
		lblDataDeSada.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDataDeSada.setBounds(162, 339, 94, 22);
		panelServico.add(lblDataDeSada);

		JLabel label_1 = new JLabel("*");
		label_1.setForeground(Color.RED);
		label_1.setBounds(45, 284, 37, 13);
		panelServico.add(label_1);

		JLabel label_2 = new JLabel("*");
		label_2.setForeground(Color.RED);
		label_2.setBounds(126, 342, 37, 13);
		panelServico.add(label_2);

		tpDesc = new JTextArea();
		tpDesc.setWrapStyleWord(true);
		tpDesc.setLineWrap(true);
		tpDesc.setBounds(10, 108, 291, 166);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 108, 291, 166);
		panelServico.add(scrollPane);
		scrollPane.setViewportView(tpDesc);

		JLabel label_7 = new JLabel("*");
		label_7.setForeground(Color.RED);
		label_7.setBounds(266, 344, 37, 13);
		panelServico.add(label_7);

		JPanel panelSituacao = new JPanel();
		panelSituacao.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Situa\u00E7\u00E3o e cobran\u00E7a", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panelSituacao.setBounds(331, 10, 311, 143);
		contentPanel.add(panelSituacao);
		panelSituacao.setLayout(null);

		cbSituacao = new JComboBox();
		cbSituacao.setFont(new Font("Tahoma", Font.PLAIN, 10));
		cbSituacao.addItem("RECEBIDO");
		cbSituacao.addItem("RETORNADO");
		cbSituacao.setBounds(10, 48, 236, 21);
		panelSituacao.add(cbSituacao);

		btPagamento = new JToggleButton("Pagamento n\u00E3o cobrado");
		btPagamento.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btPagamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (pagou) {
					btPagamento.setSelected(true);
					JOptionPane.showMessageDialog(null, "Pagamento já foi recebido", "Aviso", JOptionPane.WARNING_MESSAGE);
				} else {
					if (btPagamento.isSelected()) {
						btPagamento.setText("Pagamento já cobrado");
						btPagamento.setForeground(Color.BLACK);
					} else {
						btPagamento.setText("Pagamento não cobrado");
						btPagamento.setForeground(Color.RED);
					}
				}
			}
		});
		btPagamento.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (btPagamento.isSelected()) {
					btPagamento.setText("Pagamento já cobrado");
					btPagamento.setForeground(Color.BLACK);
				} else {
					btPagamento.setText("Pagamento não cobrado");
					btPagamento.setForeground(Color.RED);
				}
			}
		});
		btPagamento.setForeground(Color.RED);
		btPagamento.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btPagamento.setBounds(10, 105, 236, 21);
		panelSituacao.add(btPagamento);

		JLabel lblEstado = new JLabel("Estado do servi\u00E7o");
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEstado.setBounds(10, 23, 144, 25);
		panelSituacao.add(lblEstado);

		JLabel lblPagamento = new JLabel("Pagamento");
		lblPagamento.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPagamento.setBounds(10, 79, 144, 25);
		panelSituacao.add(lblPagamento);

		JLabel label_5 = new JLabel("*");
		label_5.setForeground(Color.RED);
		label_5.setBounds(127, 26, 37, 13);
		panelSituacao.add(label_5);

		JLabel label_6 = new JLabel("*");
		label_6.setForeground(Color.RED);
		label_6.setBounds(86, 83, 37, 13);
		panelSituacao.add(label_6);

		JPanel panelCliente = new JPanel();
		panelCliente.setLayout(null);
		panelCliente.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Cliente e funcion\u00E1rio", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelCliente.setBounds(331, 163, 311, 249);
		contentPanel.add(panelCliente);

		cbCliente = new JComboBox();
		cbCliente.setFont(new Font("Tahoma", Font.PLAIN, 10));
		cbCliente.setBounds(10, 78, 236, 21);
		panelCliente.add(cbCliente);

		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCliente.setBounds(10, 27, 144, 25);
		panelCliente.add(lblCliente);

		ButtonGroup grupo = new ButtonGroup();
		rbParticular = new JRadioButton("Particular");
		rbParticular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				x = 1;
				carregarCbCliente(x);
			}
		});
		rbParticular.setSelected(true);
		rbParticular.setBounds(10, 51, 103, 21);
		panelCliente.add(rbParticular);

		rbConvenio = new JRadioButton("Conv\u00EAnio");
		rbConvenio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				x = 2;
				carregarCbCliente(x);
			}
		});
		rbConvenio.setBounds(115, 51, 103, 21);
		panelCliente.add(rbConvenio);

		grupo.add(rbConvenio);
		grupo.add(rbParticular);

		JLabel lblPaciente = new JLabel("Paciente");
		lblPaciente.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPaciente.setBounds(10, 109, 144, 25);
		panelCliente.add(lblPaciente);

		JButton btNovoCliente = new JButton("");
		btNovoCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (x == 1) {
					GUICreateUpdateDentista novo = new GUICreateUpdateDentista(-1, lab);
					novo.setVisible(true);
					carregarCbCliente(x);
					if (cbCliente.getItemCount() > 0) {
						cbCliente.setSelectedIndex(cbCliente.getItemCount() - 1);
					}
				} else {
					if (x == 2) {
						GUICreateUpdateClinica novo = new GUICreateUpdateClinica(-1, lab);
						novo.setVisible(true);
						carregarCbCliente(x);
						if (cbCliente.getItemCount() > 0) {
							cbCliente.setSelectedIndex(cbCliente.getItemCount() - 1);
						}
					}
				}
			}
		});
		btNovoCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btNovoCliente
				.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/plus.png"))));
		btNovoCliente.setBounds(251, 72, 32, 32);
		panelCliente.add(btNovoCliente);

		cbPaciente = new JComboBox();
		cbPaciente.setFont(new Font("Tahoma", Font.PLAIN, 10));
		cbPaciente.setBounds(10, 138, 236, 21);
		panelCliente.add(cbPaciente);

		JButton btNovoPaciente = new JButton("");
		btNovoPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GUICreateUpdatePaciente novo = new GUICreateUpdatePaciente(-1, lab);
				novo.setVisible(true);
				carregarCbPaciente();
				;
				if (cbPaciente.getItemCount() > 0) {
					cbPaciente.setSelectedIndex(cbPaciente.getItemCount() - 1);
				}
			}
		});
		btNovoPaciente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btNovoPaciente
				.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/plus.png"))));
		btNovoPaciente.setBounds(251, 132, 32, 32);
		panelCliente.add(btNovoPaciente);

		JLabel lblFuncionrio = new JLabel("Funcion\u00E1rio");
		lblFuncionrio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFuncionrio.setBounds(10, 169, 144, 25);
		panelCliente.add(lblFuncionrio);

		cbFuncionario = new JComboBox();
		cbFuncionario.setFont(new Font("Tahoma", Font.PLAIN, 10));
		cbFuncionario.setBounds(10, 201, 236, 21);
		panelCliente.add(cbFuncionario);

		JButton btNovoFuncionario = new JButton("");
		btNovoFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GUICreateUpdateFuncionario novo = new GUICreateUpdateFuncionario(-1, lab);
				novo.setVisible(true);
				carregarCbFuncionarios();
				if (cbFuncionario.getItemCount() > 0) {
					cbFuncionario.setSelectedIndex(cbFuncionario.getItemCount() - 1);
				}
			}
		});
		btNovoFuncionario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btNovoFuncionario
				.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/plus.png"))));
		btNovoFuncionario.setBounds(251, 195, 32, 32);
		panelCliente.add(btNovoFuncionario);

		JLabel label_3 = new JLabel("*");
		label_3.setForeground(Color.RED);
		label_3.setBounds(53, 29, 37, 13);
		panelCliente.add(label_3);

		JLabel label_4 = new JLabel("*");
		label_4.setForeground(Color.RED);
		label_4.setBounds(90, 170, 37, 13);
		panelCliente.add(label_4);

		lblObrigatrio = new JLabel("* - Obrigat\u00F3rio");
		lblObrigatrio.setForeground(Color.RED);
//		 if (modo == -1)
//		 lblObrigatrio.setBounds(13, 413, 107, 13);
//		 else
		lblObrigatrio.setBounds(10, 468, 107, 13);
		contentPanel.add(lblObrigatrio);
		{
			buttonPane = new JPanel();
			if (modo == -1)
				buttonPane.setBounds(10, 422, 612, 31);
			else
			buttonPane.setBounds(10, 480, 612, 31);
			contentPanel.add(buttonPane);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						newAlterServico();
					}
				});
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
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			
		
			chckbxPagamentoRecebido = new JCheckBox("Pagamento recebido");
			chckbxPagamentoRecebido.setBounds(473, 418, 192, 21);
			chckbxPagamentoRecebido.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if (chckbxPagamentoRecebido.isSelected()) {
						if (!(btPagamento.isSelected())) {
							chckbxPagamentoRecebido.setSelected(false);
							JOptionPane.showMessageDialog(null, "Por favor, cobre o pagamento primeiro", "Aviso", JOptionPane.WARNING_MESSAGE);
						}	else {
							pagou = true;
						}
					} else {
						pagou = false;
					}
				}
			});
			contentPanel.add(chckbxPagamentoRecebido);
			if (modo == - 1) {
			 chckbxPagamentoRecebido.setVisible(false);	
			}
			
			chckbxServioEntregue = new JCheckBox("Servi\u00E7o entregue");
			chckbxServioEntregue.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
						if (chckbxServioEntregue.isSelected()) {
							cbSituacao.setEnabled(false);
							cbSituacao.setFocusable(false);
							comboBox.setEnabled(true);
						} else {
							cbSituacao.setEnabled(true);
							cbSituacao.setFocusable(true);
							comboBox.setEnabled(true);
						}
				}
			});
			chckbxServioEntregue.setBounds(331, 418, 140, 21);
			contentPanel.add(chckbxServioEntregue);
			if (modo == - 1) {
				chckbxServioEntregue.setVisible(false);
			}
			
			comboBox = new JComboBox();
			comboBox.addItem("FINALIZADO");
			comboBox.addItem("PROVA");
			comboBox.setFont(new Font("Tahoma", Font.PLAIN, 10));
			comboBox.setBounds(331, 445, 236, 21);
			contentPanel.add(comboBox);
			
			lblComissao = new JLabel("Valor da Comiss\u00E3o");
			lblComissao.setVisible(false);
			lblComissao.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblComissao.setBounds(20, 414, 311, 25);
			contentPanel.add(lblComissao);
			
			tfComissao = new JTextField();
			tfComissao.setVisible(false);
			tfComissao.setColumns(10);
			tfComissao.setBounds(21, 439, 96, 19);
			tfComissao.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent ev) {
					// código do evento:
					String caracteres = "0987654321,.";
					if (!(caracteres.contains(ev.getKeyChar() + ""))) {
						ev.consume();
					}
				}
			});
			contentPanel.add(tfComissao);
			
			if (modo == - 1) {
				comboBox.setVisible(false);
			}
			
			getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { cbTipo, btNovoTipo,
					btAjustarValor, tfValor, dtEntrada, dtEntrada.getCalendarButton(), dtSaida,
					dtSaida.getCalendarButton(), cbSituacao, btPagamento, rbParticular, rbConvenio, cbCliente,
					btNovoCliente, cbPaciente, btNovoPaciente, cbFuncionario, btNovoFuncionario }));
		}
		
		carregarCbCliente(x);
		carregarCbFuncionarios();
		carregarCbPaciente();
		carregarCbTipo();
		
		if (modo != -1) {
			ArrayList<Servicos> s = lab.getArrayServicos();
			for (int i = 0; i < cbTipo.getItemCount(); i++) {
				if (cbTipo.getItemAt(i).equals(s.get(modo).getTipo().getNome())) {
					cbTipo.setSelectedIndex(i);
					break;
				}

			}
			tpDesc.setText(s.get(modo).getDescricao());
			DecimalFormat df = new DecimalFormat("0.##");
			tfValor.setText(String.valueOf(df.format(s.get(modo).getValorFinal())));
			btAjustarValor.setEnabled(true);
			tfValor.setEditable(true);
			dtEntrada.setDate(s.get(modo).getEntrada());
			if (s.get(modo).getSaida() != null) {
				dtSaida.setDate(s.get(modo).getSaida());
				dtSaida.setEnabled(true);
			}
			cbSituacao.setSelectedItem(s.get(modo).getSituacao());
			if (s.get(modo).isPagamento()) {
				btPagamento.setSelected(true);
				btPagamento.setText("Pagamento já cobrado");
				btPagamento.setForeground(Color.BLACK);
			} else {
				btPagamento.setSelected(false);
				btPagamento.setText("Pagamento não cobrado");
				btPagamento.setForeground(Color.RED);
			}
			if (s.get(modo).getCliente() instanceof Dentista) {
				cbCliente.setSelectedItem(s.get(modo).getCliente().getNome());
			} else {
				x = 2;
				carregarCbCliente(x);
				cbCliente.setSelectedItem(s.get(modo).getCliente().getNome());
				rbParticular.setSelected(false);
				rbConvenio.setSelected(true);
			}
			if (s.get(modo).getPaciente() != null)
				cbPaciente.setSelectedItem(s.get(modo).getPaciente().getNome());
			cbFuncionario.setSelectedItem(s.get(modo).getFuncionario().getNome());
			
			if (s.get(modo).isPago()) {
				chckbxPagamentoRecebido.setSelected(true);
				pagou = true;
			}
			if(s.get(modo).getSituacao().equals("FINALIZADO") || (s.get(modo).getSituacao().equals("PROVA"))) {
				chckbxServioEntregue.setSelected(true);
				comboBox.setSelectedItem(s.get(modo).getSituacao());
				cbSituacao.setEnabled(false);
				cbSituacao.setFocusable(false);
				comboBox.setEnabled(true);
			}
			
			cod = s.get(modo).getCod();
		}

	}

	private void newAlterServico() {
		String tipo = (String) cbTipo.getSelectedItem();
		String desc = tpDesc.getText();
		String valor = tfValor.getText();
		Date entrada = dtEntrada.getDate();
		Date saida = dtSaida.getDate();
		String situacao = (String) cbSituacao.getSelectedItem();
		if (chckbxServioEntregue.isSelected()) {
			situacao = (String) comboBox.getSelectedItem();
		}
		boolean pagamento = btPagamento.isSelected();
		String cliente = (String) cbCliente.getSelectedItem();
		String paciente = (String) cbPaciente.getSelectedItem();
		String func = (String) cbFuncionario.getSelectedItem();
		if (verificarCampos(tipo, valor, entrada, saida, cliente, func)) {
			double preco = 0;
			try {
				if (valor.contains(","))
					preco = NumberFormat.getInstance().parse(valor).doubleValue();
				else
					preco = Double.parseDouble(valor);
				Cliente c;
				if (rbParticular.isSelected())
					c = lab.getArrayDentistas().get(cbCliente.getSelectedIndex());
				else
					c = lab.getClinicas().get(cbCliente.getSelectedIndex());
				Paciente p;
				if (paciente.equals("Sem paciente"))
					p = null;
				else
					p = lab.getPacientes().get(cbPaciente.getSelectedIndex() - 1);
				Funcionario f = lab.getArrayFunc().get(cbFuncionario.getSelectedIndex());
				TipoServico s = lab.getArrayTipos().get(cbTipo.getSelectedIndex());
				if (modo == -1) {
					if (lab.newServico(desc, preco, c, entrada, f, p, s, situacao, pagamento, saida)) {
						if (tfComissao.isVisible())
							lab.alterarTipoServico(s.getCod(), s.getNome(), s.getPreco(), Double.parseDouble(tfComissao.getText()));
						JOptionPane.showMessageDialog(GUICreateUpdateServico.this, "Serviço registrado com sucesso",
								"Novo serviço", JOptionPane.INFORMATION_MESSAGE);
						dispose();
					} else {
						JOptionPane.showMessageDialog(GUICreateUpdateServico.this,
								"Ocorreu algum problema com o sistema de registro",
								"Não foi possível completar a operação", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					if (lab.alterServico(cod, pagamento, situacao, desc, preco, c, entrada, saida, f, p, s)) {
						if (lab.setPago(cod, chckbxPagamentoRecebido.isSelected())) {
							if (tfComissao.isVisible())
								lab.alterarTipoServico(s.getCod(), s.getNome(), s.getPreco(), Double.parseDouble(tfComissao.getText()));
							JOptionPane.showMessageDialog(GUICreateUpdateServico.this, "Serviço alterado com sucesso",
									"Alterar serviço", JOptionPane.INFORMATION_MESSAGE);
							dispose();
						} else {
							JOptionPane.showMessageDialog(GUICreateUpdateServico.this,
									"Serviço alterado, porém ocorreu algum problema com o sistema de alterar o pagamento",
									"Alterar serviço", JOptionPane.WARNING_MESSAGE);
						}
					} else
						JOptionPane.showMessageDialog(GUICreateUpdateServico.this,
								"Ocorreu algum problema com o sistema de alteração",
								"Não foi possível completar a operação", JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(GUICreateUpdateServico.this,
						"Ocorreu algum problema com o campo de valor", "Não foi possível realizar a operação",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(GUICreateUpdateServico.this,
					"Preencha todos os campos corretamente e tente novamente", "Não foi possível realizar a operação",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	private boolean verificarCampos(String tipo, String valor, Date entrada, Date saida, String cliente, String func) {
		boolean test = true;
		if (tipo == null) {
			test = false;
		}
		double x = 0;
		try {
			if (valor.contains(","))
				x = NumberFormat.getInstance().parse(valor).doubleValue();
			else
				x = Double.parseDouble(valor);
			if (x >= 0)
				tfValor.setBackground(Color.WHITE);
			else {
				tfValor.setBackground(Color.PINK);
				test = false;
			}
		} catch (Exception e) {
			tfValor.setBackground(Color.PINK);
			test = false;
		}
		if (entrada != null)
			dtEntrada.setBackground(Color.WHITE);
		else {
			dtEntrada.setBackground(Color.PINK);
			test = false;
		}
		if (entrada == null) {
			test = false;
		} else {
			if (saida != null) {
				if (saida.after(entrada))
					dtSaida.setBackground(Color.WHITE);
				else {
					dtSaida.setBackground(Color.PINK);
					test = false;
				}
			} else {
				test = false;
			}
		}

		if (cliente == null) {
			test = false;
		}
		if (func == null) {
			test = false;
		}
		if (tfComissao.isVisible()) {
			try {
				if (Double.parseDouble(tfComissao.getText()) > 0) {
					tfComissao.setBackground(Color.WHITE);
				} else {
					tfComissao.setBackground(Color.PINK);
					test = false;
				}
			} catch (Exception e) {
				test = false;
				tfComissao.setBackground(Color.PINK);
			}
		}
		return test;
	}

	private void preencherPreco() {
		ArrayList<TipoServico> s = lab.getArrayTipos();
		if (s.size() > 0) {
			DecimalFormat df = new DecimalFormat("0.##");
			tfValor.setText(String.valueOf(df.format(s.get(cbTipo.getSelectedIndex()).getPreco())));
		}
	}

	private void carregarCbTipo() {
		ArrayList<TipoServico> s = lab.getArrayTipos();
		cbTipo.removeAllItems();
		if (s.size() > 0) {
			for (int i = 0; i < s.size(); i++) {
				cbTipo.addItem(s.get(i).getNome());
			}
		}

	}

	private void carregarCbCliente(int x) {
		List y = new ArrayList<>();
		if (x == 1)
			y = lab.getArrayDentistas();
		else if (x == 2)
			y = lab.getClinicas();

		cbCliente.removeAllItems();
		if (y.size() > 0) {
			for (int i = 0; i < y.size(); i++)
				cbCliente.addItem(((Cliente) y.get(i)).getNome());
		}

	}

	private void carregarCbPaciente() {
		ArrayList<Paciente> p = lab.getPacientes();

		cbPaciente.removeAllItems();
		cbPaciente.addItem("Sem paciente");
		if (p.size() > 0) {
			for (int i = 0; i < p.size(); i++) {
				cbPaciente.addItem(p.get(i).getNome());
			}
		}

	}

	private void carregarCbFuncionarios() {
		ArrayList<Funcionario> f = lab.getArrayFunc();

		cbFuncionario.removeAllItems();
		if (f.size() > 0) {
			for (int i = 0; i < f.size(); i++) {
				cbFuncionario.addItem(f.get(i).getNome());
			}
		}

	}
}
