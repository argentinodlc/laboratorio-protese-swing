package dados;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import dados.interfaces.IRepoServicos;
import sistema.classes.Cliente;
import sistema.classes.Clinica;
import sistema.classes.Compra;
import sistema.classes.Dentista;
import sistema.classes.Fornecedor;
import sistema.classes.Funcionario;
import sistema.classes.Paciente;
import sistema.classes.Servicos;
import sistema.classes.TipoServico;

public class RepoServicos implements IRepoServicos {
	private ArrayList<Servicos> servicos = new ArrayList<Servicos>();

	@Override
	public ArrayList<Servicos> getArrayServicos() {
		return servicos;
	}

	@Override
	public boolean newServico(String descricao, double valorFinal, Cliente cliente, Date entrada,
			Funcionario funcionario, Paciente paciente, TipoServico tipo, String situacao, boolean pagamento,
			Date saida) {
		Servicos s = Servicos.newInstance(descricao, valorFinal, cliente, entrada, funcionario, paciente, tipo,
				situacao, pagamento, saida);
		if (s != null) {
			servicos.add(s);
			return true;
		}
		return false;
	}

	@Override
	public boolean alterServico(long cod, boolean pagamento, String situacao, String descricao, double valorFinal,
			Cliente cliente, Date entrada, Date saida, Funcionario funcionario, Paciente paciente, TipoServico tipo) {
		if (!(servicos.isEmpty())) {
			for (int i = 0; i < servicos.size(); i++) {
				if (servicos.get(i).getCod() == cod) {
					servicos.get(i).setCliente(cliente);
					servicos.get(i).setDescricao(descricao);
					servicos.get(i).setEntrada(entrada);
					servicos.get(i).setFuncionario(funcionario);
					servicos.get(i).setPaciente(paciente);
					servicos.get(i).setPagamento(pagamento);
					servicos.get(i).setSaida(saida);
					servicos.get(i).setSituacao(situacao);
					servicos.get(i).setValorFinal(valorFinal);
					servicos.get(i).setTipo(tipo);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean excluirServico(long cod) {
		if (!(servicos.isEmpty())) {
			for (int i = 0; i < servicos.size(); i++) {
				if (servicos.get(i).getCod() == cod) {
					servicos.remove(i);
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean isPago(long cod) {
		if (!(servicos.isEmpty())) {
			for (int i = 0; i < servicos.size(); i++) {
				if (servicos.get(i).getCod() == cod) {	
					return servicos.get(i).isPago();
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean setPago(long cod, boolean pago) {
		if (!(servicos.isEmpty())) {
			for (int i = 0; i < servicos.size(); i++) {
				if (servicos.get(i).getCod() == cod) {	
					servicos.get(i).setPago(pago);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void carregar(ArrayList<Dentista> d, ArrayList<Clinica> c, ArrayList<Funcionario> f, ArrayList<Paciente> p,
			ArrayList<TipoServico> t) throws IOException, ParseException, IndexOutOfBoundsException {
		String user = System.getProperty("user.home");
		File diretorio = new File(user + "\\.sistemaDentArt");
		if (!diretorio.exists()) {
			diretorio.mkdirs();
		}
		File arq = new File(diretorio, "servicos.txt");
		if (!arq.exists())
			arq.createNewFile();
		else {
			Scanner leitor = new Scanner(arq);
			List<String> linhas = new ArrayList<>();
			while (leitor.hasNextLine()) {
				linhas.add(leitor.nextLine());
			}
			if (linhas.size() > 0) {
				Servicos.setGeraCod(Long.parseLong(linhas.get(0)));
				for (int i = 1; i < linhas.size(); i++) {
					String[] array = linhas.get(i).split(";");
					long cod = Long.parseLong(array[0]);
					String pag = array[1];
					boolean pagamento;
					if (pag.equals("true"))
						pagamento = true;
					else
						pagamento = false;
					String situacao = array[2];
					String descricao = array[3];
					double valorFinal = Double.parseDouble(array[4]);
					long cdC = Long.parseLong(array[5]);
					Cliente cliente = null;
					for (int j = 0; j < c.size(); j++) {
						if (cdC == c.get(j).getCod()) {
							cliente = c.get(j);
						}
					}
					for (int j = 0; j < d.size(); j++) {
						if (cdC == d.get(j).getCod()) {
							cliente = d.get(j);
						}
					}
					Date entrada = new SimpleDateFormat("MMM dd HH:mm:ss zzz yyyy").parse(array[6]);
					Date saida;
					if (array[7].equals("null"))
						saida = null;
					else
						saida = new SimpleDateFormat("MMM dd HH:mm:ss zzz yyyy").parse(array[7]);

					long codF = Long.parseLong(array[8]);
					Funcionario funcionario = null;
					for (int j = 0; j < f.size(); j++) {
						if (f.get(j).getCod() == codF) {
							funcionario = f.get(j);
						}
					}
					Paciente paciente = null;
					if (array[9].equals("null"))
						paciente = null;
					else {
						for (int j = 0; j < p.size(); j++) {
							if (p.get(j).getCod() == Long.parseLong(array[9])) {
								paciente = p.get(j);
							}
						}
					}
					TipoServico tipo = null;
					for (int j = 0; j < t.size(); j++) {
						if (t.get(j).getCod() == Long.parseLong(array[10])) {
							tipo = t.get(j);
						}
					}
					boolean pago = Boolean.valueOf(array[11]);
					Servicos servico = Servicos.newInstance(cod, descricao, valorFinal, cliente, entrada, funcionario,
							paciente, tipo, situacao, pagamento, saida, pago);
					servicos.add(servico);
				}
				leitor.close();
			}
		}
	}

	@Override
	public void escrever() throws IOException {
		String user = System.getProperty("user.home");
		File diretorio = new File(user + "\\.sistemaDentArt");
		if (!diretorio.exists()) {
			diretorio.mkdirs();
		}
		File arq = new File(diretorio, "servicos.txt");
		if (!arq.exists())
			arq.createNewFile();
		String path = arq.getAbsolutePath();
		String linha = "";
		BufferedWriter arqD = new BufferedWriter(new FileWriter(path));
		arqD.write(String.valueOf(Servicos.getGeraCod()) + "\n");
		for (int i = 0; i < servicos.size(); i++) {
			linha = "";
			if (servicos.get(i) == null) {
			} else {
				String p;
				if (servicos.get(i).getPaciente() == null)
					p = "null";
				else
					p = String.valueOf(servicos.get(i).getPaciente().getCod());
				String s;
				if (servicos.get(i).getSaida() == null)
					s = "null";
				else
					s = (new SimpleDateFormat("MMM dd HH:mm:ss zzz yyyy")).format(servicos.get(i).getSaida());
				linha = servicos.get(i).getCod() + ";" + servicos.get(i).isPagamento() + ";"
						+ servicos.get(i).getSituacao() + ";" + servicos.get(i).getDescricao() + ";"
						+ servicos.get(i).getValorFinal() + ";" + servicos.get(i).getCliente().getCod() + ";"
						+ (new SimpleDateFormat("MMM dd HH:mm:ss zzz yyyy")).format(servicos.get(i).getEntrada()) + ";"
						+ s + ";" + servicos.get(i).getFuncionario().getCod() + ";" + p + ";"
						+ servicos.get(i).getTipo().getCod() + ";" + servicos.get(i).isPago();
				arqD.write(linha + "\n");
			}
		}
		arqD.close();
	}
}
