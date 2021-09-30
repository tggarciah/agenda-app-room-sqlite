package br.com.dtfoods.ui.activity;

import static br.com.dtfoods.ui.activity.ConstantesActivities.CHAVE_ALUNO;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.dtfoods.R;
import br.com.dtfoods.model.Aluno;
import br.com.dtfoods.ui.ListaAlunoView;

public class ListaAlunosActivity extends AppCompatActivity {

   public static final String TITULO_APPBAR = "Lista de alunos com SQLite";
   public static final String TAG = "ListaAlunosActivity";
   private ListaAlunoView listaAlunoView;

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_lista_alunos);
      setTitle(TITULO_APPBAR);

      listaAlunoView = new ListaAlunoView(this);
      configuraFabNovoAluno();
      configuraLista();
   }

   @Override
   protected void onResume() {
      super.onResume();
      listaAlunoView.atualizaAlunos();
   }

   @Override
   public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
      super.onCreateContextMenu(menu, v, menuInfo);
      getMenuInflater().inflate(R.menu.activity_lista_alunos_menu, menu);
   }

   @Override
   public boolean onContextItemSelected(@NonNull MenuItem item) {
      int itemId = item.getItemId();
      if (itemId == R.id.activity_lista_alunos_menu_remover) {
         listaAlunoView.confirmarRemocaoAluno(item);
      }
      return super.onContextItemSelected(item);
   }

   private void configuraFabNovoAluno() {
      FloatingActionButton botaoNovoAluno = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
      botaoNovoAluno.setOnClickListener(view -> abreFormularioModoInsereAluno());
   }

   private void abreFormularioModoInsereAluno() {
      startActivity(new Intent(this, FormularioAlunoActivity.class));
   }

   private void configuraLista() {
      ListView listaAlunos = findViewById(R.id.activity_lista_alunos_listview);
      listaAlunoView.configuraAdapter(listaAlunos);
      configuraListenerDeCliquePorItem(listaAlunos);
      registerForContextMenu(listaAlunos);
   }

   private void configuraListenerDeCliquePorItem(@NonNull ListView listaAlunos) {
      listaAlunos.setOnItemClickListener((adapterView, view, posicao, id) -> {
         Aluno aluno = (Aluno) adapterView.getItemAtPosition(posicao);

         // atalho para o código abaixo logi
         Log.i(TAG, "aluno: " + aluno);

         abreFormularioModoEditaAluno(aluno);
      });
   }

   private void abreFormularioModoEditaAluno(Aluno aluno) {
      Intent vaiParaFormularioActivity = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
      vaiParaFormularioActivity.putExtra(CHAVE_ALUNO, aluno);
      startActivity(vaiParaFormularioActivity);
   }
}
