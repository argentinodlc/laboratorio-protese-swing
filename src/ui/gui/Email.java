package ui.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Toolkit;
import java.io.File;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class Email {
	public static void sentEmail(String texto) {

		String to = "filipeadominguesa@gmail.com";
		String from = System.getenv("LAB_EMAIL");
		String host = "smtp.gmail.com";
		String msgText1 = texto + "\n <" + Calendar.getInstance().getTime() + ">";
		;
		String subject = "Problema no sistema Dent Art - "
				+ (new SimpleDateFormat("dd/MM/yyyy")).format(Calendar.getInstance().getTime());

		JFrame jfrProgress = new JFrame("Informar problema");
		Container contentPane = jfrProgress.getContentPane();
		
		contentPane.setLayout(null);
		jfrProgress.setLocation(420, 350);
		jfrProgress.setSize(600, 90);
		jfrProgress.setLocationRelativeTo(null);
		jfrProgress.setAlwaysOnTop(true);
		jfrProgress.setUndecorated(true);
		jfrProgress.setVisible(true);
		
		int progress = 100;

		JProgressBar progressBar = new JProgressBar(0, progress);
		String doing = "Configurar host";
		jfrProgress.getContentPane().add(progressBar, BorderLayout.CENTER);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		progressBar.setString("0%");

		progressBar.setSize(600, 60);
		progressBar.setVisible(true);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		progressBar.paintImmediately(0, 0, jfrProgress.getWidth(), jfrProgress.getHeight());

		Properties props = System.getProperties();
		props.put("mail.smtps.host", host);
		props.put("mail.smtps.auth", "true");

		props.put("mail.smtp.port", "25");
		Session session = Session.getInstance(props, null);

		for (int i = 0; i < 1500000000; i++) {
			;	
		}

		// Progress ++;
		progressBar.setValue(30);
		doing = "Construir mensagem";
		progressBar.setString("30%");
		progressBar.paintImmediately(0, 0, jfrProgress.getWidth(), jfrProgress.getHeight());

		try {
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			InternetAddress[] address = { new InternetAddress(to) };
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject(subject);

			MimeBodyPart mbp1 = new MimeBodyPart();
			mbp1.setText(msgText1);

			Multipart mp = new MimeMultipart();
			mp.addBodyPart(mbp1);

			MimeBodyPart func = new MimeBodyPart();

			DataSource f = new FileDataSource(
					new File(System.getProperty("user.home") + "\\.sistemaDentArt\\funcionarios.txt"));
			func.setDisposition(Part.ATTACHMENT);
			func.setDataHandler(new DataHandler(f));
			func.setFileName(f.getName());

			mp.addBodyPart(func);

			MimeBodyPart clinicas = new MimeBodyPart();
			DataSource c = new FileDataSource(
					new File(System.getProperty("user.home") + "\\.sistemaDentArt\\clinicas.txt"));
			clinicas.setDisposition(Part.ATTACHMENT);
			clinicas.setDataHandler(new DataHandler(c));
			clinicas.setFileName(c.getName());

			mp.addBodyPart(clinicas);

			MimeBodyPart compras = new MimeBodyPart();
			DataSource c2 = new FileDataSource(
					new File(System.getProperty("user.home") + "\\.sistemaDentArt\\compras.txt"));
			compras.setDisposition(Part.ATTACHMENT);
			compras.setDataHandler(new DataHandler(c2));
			compras.setFileName(c2.getName());

			mp.addBodyPart(compras);

			MimeBodyPart dentistas = new MimeBodyPart();
			DataSource d = new FileDataSource(
					new File(System.getProperty("user.home") + "\\.sistemaDentArt\\dentistas.txt"));
			dentistas.setDisposition(Part.ATTACHMENT);
			dentistas.setDataHandler(new DataHandler(d));
			dentistas.setFileName(d.getName());

			mp.addBodyPart(dentistas);

			MimeBodyPart despesas = new MimeBodyPart();
			DataSource d2 = new FileDataSource(
					new File(System.getProperty("user.home") + "\\.sistemaDentArt\\despesas.txt"));
			despesas.setDisposition(Part.ATTACHMENT);
			despesas.setDataHandler(new DataHandler(d2));
			despesas.setFileName(d2.getName());

			mp.addBodyPart(despesas);

			MimeBodyPart fixas = new MimeBodyPart();
			DataSource f2 = new FileDataSource(
					new File(System.getProperty("user.home") + "\\.sistemaDentArt\\fixas.txt"));
			fixas.setDisposition(Part.ATTACHMENT);
			fixas.setDataHandler(new DataHandler(f2));
			fixas.setFileName(f2.getName());

			mp.addBodyPart(fixas);

			MimeBodyPart fornecedores = new MimeBodyPart();
			DataSource f3 = new FileDataSource(
					new File(System.getProperty("user.home") + "\\.sistemaDentArt\\fornecedores.txt"));
			fornecedores.setDisposition(Part.ATTACHMENT);
			fornecedores.setDataHandler(new DataHandler(f3));
			fornecedores.setFileName(f3.getName());

			mp.addBodyPart(fornecedores);

			MimeBodyPart pacientes = new MimeBodyPart();
			DataSource p = new FileDataSource(
					new File(System.getProperty("user.home") + "\\.sistemaDentArt\\pacientes.txt"));
			pacientes.setDisposition(Part.ATTACHMENT);
			pacientes.setDataHandler(new DataHandler(p));
			pacientes.setFileName(p.getName());

			mp.addBodyPart(pacientes);

			MimeBodyPart passwd = new MimeBodyPart();
			DataSource pswrd = new FileDataSource(
					new File(System.getProperty("user.home") + "\\.sistemaDentArt\\passwd.txt"));
			passwd.setDisposition(Part.ATTACHMENT);
			passwd.setDataHandler(new DataHandler(pswrd));
			passwd.setFileName(pswrd.getName());

			mp.addBodyPart(passwd);

			MimeBodyPart produtos = new MimeBodyPart();
			DataSource p2 = new FileDataSource(
					new File(System.getProperty("user.home") + "\\.sistemaDentArt\\produtos.txt"));
			produtos.setDisposition(Part.ATTACHMENT);
			produtos.setDataHandler(new DataHandler(p2));
			produtos.setFileName(p2.getName());

			mp.addBodyPart(produtos);

			MimeBodyPart servicos = new MimeBodyPart();
			DataSource s = new FileDataSource(
					new File(System.getProperty("user.home") + "\\.sistemaDentArt\\servicos.txt"));
			servicos.setDisposition(Part.ATTACHMENT);
			servicos.setDataHandler(new DataHandler(s));
			servicos.setFileName(s.getName());

			mp.addBodyPart(servicos);

			MimeBodyPart tipos = new MimeBodyPart();
			DataSource t = new FileDataSource(
					new File(System.getProperty("user.home") + "\\.sistemaDentArt\\tipos.txt"));
			tipos.setDisposition(Part.ATTACHMENT);
			tipos.setDataHandler(new DataHandler(t));
			tipos.setFileName(t.getName());

			mp.addBodyPart(tipos);

			msg.setContent(mp);

			msg.setSentDate(new Date());

			for (int i = 0; i < 1500000000; i++) {
				;
			}

			// Progress ++;
			progressBar.setValue(60);
			doing = "Enviar mensagem";
			progressBar.setString("60%");
			progressBar.paintImmediately(0, 0, jfrProgress.getWidth(), jfrProgress.getHeight());
			
			Transport transport = session.getTransport("smtps");
			//thisisanencryptedpassword
			transport.connect(host, "biyumee", System.getenv("LAB_SENHA"));
			transport.sendMessage(msg, msg.getAllRecipients());
			
			for (int i = 0; i < 1500000000; i++) {
				;
			}
			
			// Progress ++;
			progressBar.setValue(100);
			progressBar.paintImmediately(0, 0, jfrProgress.getWidth(), jfrProgress.getHeight());

			// Close
			jfrProgress.dispose();
			
			JOptionPane.showMessageDialog(null,
					"Obrigado pelo contato, sua mensagem foi enviada e o problema ser� resolvido em breve",
					"Informar problema", JOptionPane.INFORMATION_MESSAGE);

		} catch (MessagingException mex) {

			String error;
			error = mex.getMessage();
			System.out.println(error);
			jfrProgress.dispose();
			JOptionPane.showMessageDialog(null,
					"Ocorreu algum problema no processo de\n" + doing + "\nPor favor, tente novamente.\n",
					"Informar um problema", JOptionPane.ERROR_MESSAGE);
			Exception ex = null;
			if ((ex = mex.getNextException()) != null) {
				ex.printStackTrace();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
				"Ocorreu algum erro desconhecido no processo de\n" + doing +"\n"+e.getMessage(),
					"Informar um problema", JOptionPane.ERROR_MESSAGE);
		}

	}

}