package br.com.dtfoods.ui;

import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import br.com.dtfoods.asynctask.BuscaAlunosTask;
import br.com.dtfoods.asynctask.RemoveAlunoTask;
import br.com.dtfoods.database.AgendaDatabase;
import br.com.dtfoods.database.dao.AlunoDAO;
import br.com.dtfoods.model.Aluno;
import br.com.dtfoods.ui.adapter.ListaAlunosAdapter;

public class ListaAlunoView {

   private final ListaAlunosAdapter adapter;
   private final AlunoDAO dao;
   private final Context context;

   public ListaAlunoView(Context context) {
      this.context = context;
      this.adapter = new ListaAlunosAdapter(this.context);
      this.dao = AgendaDatabase.getInstance(context).getAlunoDAO();
   }

   public void confirmarRemocaoAluno(@NonNull final MenuItem item) {
      new AlertDialog.Builder(context)
              .setTitle("Removendo o aluno")
              .setMessage("Tem certeza que quer remover o aluno?")
              .setPositiveButton("Sim", (dialogInterface, i) -> {
                 AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                 Aluno aluno = adapter.getItem(menuInfo.position);
                 remover(aluno);
              })
              .setNegativeButton("Não", (dialogInterface, i) -> Toast.makeText(context, "Obrigado por não me remover! :)", Toast.LENGTH_LONG).show())
              .show();
   }

   public void configuraAdapter(@NonNull ListView listaAlunos) {
      listaAlunos.setAdapter(adapter);
   }

   public void atualizaAlunos() {
      new BuscaAlunosTask(dao, adapter).execute();
   }

   private void remover(Aluno aluno) {
      new RemoveAlunoTask(dao, adapter, aluno).execute();
   }
}
