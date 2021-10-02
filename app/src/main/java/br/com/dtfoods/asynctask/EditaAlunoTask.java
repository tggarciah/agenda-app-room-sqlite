package br.com.dtfoods.asynctask;

import java.util.List;

import br.com.dtfoods.database.dao.AlunoDAO;
import br.com.dtfoods.database.dao.TelefoneDAO;
import br.com.dtfoods.model.Aluno;
import br.com.dtfoods.model.Telefone;
import br.com.dtfoods.model.TipoTelefone;

public class EditaAlunoTask extends BaseAlunoComTelefoneTask {
   private final AlunoDAO alunoDAO;
   private final Aluno aluno;
   private final Telefone telefoneFixo;
   private final Telefone telefoneCelular;
   private final TelefoneDAO telefoneDAO;
   private final List<Telefone> telefones;

   public EditaAlunoTask(AlunoDAO alunoDAO, Aluno aluno, Telefone telefoneFixo, Telefone telefoneCelular, TelefoneDAO telefoneDAO, List<Telefone> telefones, FinalizadaListener listener) {
      super(listener);
      this.alunoDAO = alunoDAO;
      this.aluno = aluno;
      this.telefoneFixo = telefoneFixo;
      this.telefoneCelular = telefoneCelular;
      this.telefoneDAO = telefoneDAO;
      this.telefones = telefones;
   }

   @Override
   protected Void doInBackground(Void... voids) {
      alunoDAO.editar(aluno);
      vinculaAlunoComTelefone(aluno.getId(), telefoneFixo, telefoneCelular);
      atualizaIdsDosTelefones(telefoneFixo, telefoneCelular);
      telefoneDAO.atualizar(telefoneFixo, telefoneCelular);
      return null;
   }

   private void atualizaIdsDosTelefones(Telefone telefoneFixo, Telefone telefoneCelular) {
      for (Telefone telefone : telefones) {
         if (telefone.getTipo() == TipoTelefone.FIXO) {
            telefoneFixo.setId(telefone.getId());
         } else {
            telefoneCelular.setId(telefone.getId());
         }
      }
   }
}
