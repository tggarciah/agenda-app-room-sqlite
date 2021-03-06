package br.com.dtfoods.asynctask;

import android.os.AsyncTask;

import br.com.dtfoods.model.Telefone;

public abstract class BaseAlunoComTelefoneTask extends AsyncTask<Void, Void, Void> {

   private FinalizadaListener listener;

   protected BaseAlunoComTelefoneTask(FinalizadaListener listener) {
      this.listener = listener;
   }

   protected void vinculaAlunoComTelefone(int alunoId, Telefone... telefones) {
      for (Telefone telefone : telefones) {
         telefone.setAlunoId(alunoId);
      }
   }

   @Override
   protected void onPostExecute(Void unused) {
      super.onPostExecute(unused);
      listener.quandoFinalizada();
   }

   public interface FinalizadaListener {
      void quandoFinalizada();
   }

}
