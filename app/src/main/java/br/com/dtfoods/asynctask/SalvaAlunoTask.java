package br.com.dtfoods.asynctask;

import br.com.dtfoods.database.dao.AlunoDAO;
import br.com.dtfoods.database.dao.TelefoneDAO;
import br.com.dtfoods.model.Aluno;
import br.com.dtfoods.model.Telefone;

public class SalvaAlunoTask extends BaseAlunoComTelefoneTask {
   public static final String TAG = "SalvaAlunoTask1";

   private final Aluno aluno;
   private final Telefone telefoneFixo;
   private final Telefone telefoneCelular;
   private final AlunoDAO alunoDAO;
   private final TelefoneDAO telefoneDAO;

   public SalvaAlunoTask(AlunoDAO alunoDAO, Aluno aluno, Telefone telefoneFixo, Telefone telefoneCelular, TelefoneDAO telefoneDAO, FinalizadaListener listener) {
      super(listener);
      this.alunoDAO = alunoDAO;
      this.aluno = aluno;
      this.telefoneFixo = telefoneFixo;
      this.telefoneCelular = telefoneCelular;
      this.telefoneDAO = telefoneDAO;
   }

   @Override
   protected Void doInBackground(Void... voids) {
      int alunoId = alunoDAO.salvar(aluno).intValue();
      vinculaAlunoComTelefone(alunoId, telefoneFixo, telefoneCelular);
      telefoneDAO.salvar(telefoneFixo, telefoneCelular);
      return null;
   }
}
