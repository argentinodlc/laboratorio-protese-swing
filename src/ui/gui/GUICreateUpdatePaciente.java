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

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Cursor;

public class GUICreateUpdatePaciente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private ILaboratorio lab;
	private long cod;
	
	public GUICreateUpdatePaciente(final int modo, ILaboratorio lab) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/icon.png")));
		this.lab = lab;
		if (modo != -1)
			setTitle("Alterar paciente");
		else
			setTitle("Cadastrar paciente");
		setModal(true);
		setBounds(100, 100, 452, 191);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 416, 210);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNome.setBounds(10, 10, 128, 13);
		panel.add(lblNome);
		
		textField = new JTextField();
		textField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		textField.setBounds(10, 33, 334, 19);
		textField.addKeyListener(new KeyAdapter() {
			@Override
				public void keyTyped(KeyEvent ev) {
				// código do evento:
					String caracteres = "0987654321";
					if(caracteres.contains(ev.getKeyChar()+"")){
						ev.consume();
					}
				}	
			});
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("*");
		label_1.setForeground(Color.RED);
		label_1.setBounds(60, 1, 45, 23);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("* - Obrigat\u00F3rio");
		label_2.setForeground(Color.RED);
		label_2.setBounds(0, 92, 86, 20);
		panel.add(label_2);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (modo == -1) 
							novo();
						else 
							alterar();
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
			textField.setText(lab.getPacientes().get(modo).getNome());
			cod = lab.getPacientes().get(modo).getCod();
		}
	}
	protected void alterar() {
		String nome = textField.getText();
		if (verificarCampo(nome)) {
			if (lab.alterPacientes(cod, nome)) {
				JOptionPane.showMessageDialog(this, "Paciente alterado com sucesso", "Alteração de paciente", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			} else {
				JOptionPane.showMessageDialog(this, "Ocorreu um erro no sistema de alteração, tente novamente", "Alteração de paciente", JOptionPane.ERROR_MESSAGE);
			}
		} else 
			JOptionPane.showMessageDialog(this, "Preencha todos os campos corretamente", "Alteração de paciente", JOptionPane.WARNING_MESSAGE);
		
	}
	private boolean verificarCampo (String nome) {
		if (nome.length() > 1 && nome != null) {
			textField.setBackground(Color.WHITE);
			return true;
		} else {
			textField.setBackground(Color.PINK);
			return false;
		}
	}
	private void novo() {
		String nome = textField.getText();
		if (verificarCampo(nome)) {
			if(lab.addPaciente(nome)) {
				JOptionPane.showMessageDialog(this, "Paciente cadastrado com sucesso", "Cadastro de paciente", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			} else {
				JOptionPane.showMessageDialog(this, "Ocorreu um erro no sistema de cadastro, tente novamente", "Cadastro de paciente", JOptionPane.ERROR_MESSAGE);
			}
		}
		else {
			JOptionPane.showMessageDialog(this, "Preencha todos os campos corretamente", "Cadastro de paciente", JOptionPane.WARNING_MESSAGE);
		}
		
	}
}
