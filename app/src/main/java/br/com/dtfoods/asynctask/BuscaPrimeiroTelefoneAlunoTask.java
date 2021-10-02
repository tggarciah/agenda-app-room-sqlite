package br.com.dtfoods.asynctask;

import android.os.AsyncTask;

import br.com.dtfoods.database.dao.TelefoneDAO;
import br.com.dtfoods.model.Telefone;

public class BuscaPrimeiroTelefoneAlunoTask extends AsyncTask<Void, Void, Telefone> {
   private final TelefoneDAO dao;
   private final int alunoId;
   private final PrimeiroTelefoneEncontradoListener listener;

   public BuscaPrimeiroTelefoneAlunoTask(TelefoneDAO dao, int alunoId, PrimeiroTelefoneEncontradoListener listener) {
      this.dao = dao;
      this.alunoId = alunoId;
      this.listener = listener;
   }

   @Override
   protected Telefone doInBackground(Void... voids) {
      return dao.buscaPrimeiroTelefone(alunoId);
   }

   @Override
   protected void onPostExecute(Telefone telefone) {
      super.onPostExecute(telefone);
      if (telefone != null) {
         listener.quandoEncontrado(telefone);
      }
   }

   public interface PrimeiroTelefoneEncontradoListener {
      void quandoEncontrado(Telefone telefone);
   }
}
