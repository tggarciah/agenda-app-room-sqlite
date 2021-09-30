package br.com.dtfoods.ui;

import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

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
      adapter = new ListaAlunosAdapter(this.context);
      dao = AgendaDatabase.getInstance(context).getRoomAlunoDAO();
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

   public void atualizaAlunos() {
      adapter.atualiza(dao.todos());
   }

   public void configuraAdapter(@NonNull ListView listaAlunos) {
      listaAlunos.setAdapter(adapter);
   }

   private void remover(Aluno aluno) {
      dao.remover(aluno);
      adapter.remove(aluno);
   }
}
