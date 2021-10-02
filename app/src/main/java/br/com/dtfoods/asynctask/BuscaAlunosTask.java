package br.com.dtfoods.asynctask;

import android.os.AsyncTask;

import java.util.List;

import br.com.dtfoods.database.dao.AlunoDAO;
import br.com.dtfoods.model.Aluno;
import br.com.dtfoods.ui.adapter.ListaAlunosAdapter;

public class BuscaAlunosTask extends AsyncTask<Void, Void, List<Aluno>> {
   
   private final AlunoDAO dao;
   private final ListaAlunosAdapter adapter;

   public BuscaAlunosTask(AlunoDAO dao, ListaAlunosAdapter adapter) {
      this.dao = dao;
      this.adapter = adapter;
   }

   @Override
   protected List<Aluno> doInBackground(Void... voids) {
      return dao.todos();
   }

   @Override
   protected void onPostExecute(List<Aluno> alunos) {
      super.onPostExecute(alunos);
      adapter.atualiza(alunos);
   }
}
