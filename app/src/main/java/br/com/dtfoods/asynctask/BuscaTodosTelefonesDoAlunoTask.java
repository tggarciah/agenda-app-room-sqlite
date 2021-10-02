package br.com.dtfoods.asynctask;

import android.os.AsyncTask;

import java.util.List;

import br.com.dtfoods.database.dao.TelefoneDAO;
import br.com.dtfoods.model.Aluno;
import br.com.dtfoods.model.Telefone;

public class BuscaTodosTelefonesDoAlunoTask extends AsyncTask<Void, Void, List<Telefone>> {
   private final TelefoneDAO telefoneDAO;
   private final Aluno aluno;
   private final TelefonesDoAlunoEncontradosListener listener;

   public BuscaTodosTelefonesDoAlunoTask(TelefoneDAO telefoneDAO, Aluno aluno, TelefonesDoAlunoEncontradosListener listener) {
      this.telefoneDAO = telefoneDAO;
      this.aluno = aluno;
      this.listener = listener;
   }

   @Override
   protected List<Telefone> doInBackground(Void... voids) {
      return telefoneDAO.buscaTodosTelefonesDo(aluno.getId());
   }

   @Override
   protected void onPostExecute(List<Telefone> telefones) {
      super.onPostExecute(telefones);
      listener.quandoEncontrados(telefones);
   }

   public interface TelefonesDoAlunoEncontradosListener {
      void quandoEncontrados(List<Telefone> telefones);
   }
}
