package br.com.dtfoods.asynctask;

import android.os.AsyncTask;

import br.com.dtfoods.database.dao.AlunoDAO;
import br.com.dtfoods.model.Aluno;
import br.com.dtfoods.ui.adapter.ListaAlunosAdapter;

public class RemoveAlunoTask extends AsyncTask<Void, Void, Void> {
   private final AlunoDAO dao;
   private final ListaAlunosAdapter adapter;
   private final Aluno aluno;

   public RemoveAlunoTask(AlunoDAO dao, ListaAlunosAdapter adapter, Aluno aluno) {
      this.dao = dao;
      this.adapter = adapter;
      this.aluno = aluno;
   }

   @Override
   protected Void doInBackground(Void... voids) {
      dao.remover(aluno);
      return null;
   }

   @Override
   protected void onPostExecute(Void unused) {
      super.onPostExecute(unused);
      adapter.remove(aluno);
   }
}
