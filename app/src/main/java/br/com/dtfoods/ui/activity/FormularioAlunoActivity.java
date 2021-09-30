package br.com.dtfoods.ui.activity;

import static br.com.dtfoods.ui.activity.ConstantesActivities.CHAVE_ALUNO;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import br.com.dtfoods.R;
import br.com.dtfoods.database.AgendaDatabase;
import br.com.dtfoods.database.dao.AlunoDAO;
import br.com.dtfoods.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

   private static final String TITULO_APPBAR_NOVO_ALUNO = "Novo aluno";
   private static final String TITULO_APPBAR_EDITA_ALUNO = "Edita aluno";
   private EditText campoNome;
   private EditText campoSobrenome;
   private EditText campoTelefoneFixo;
   private EditText campoTelefoneCelular;
   private EditText campoEmail;
   private AlunoDAO dao;
   private Aluno aluno;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_formulario_aluno);
      dao = AgendaDatabase.getInstance(this).getRoomAlunoDAO();
      inicializacaoCampos();
      carregarAluno();
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      getMenuInflater().inflate(R.menu.activity_formulario_aluno_menu, menu);
      return super.onCreateOptionsMenu(menu);
   }

   @Override
   public boolean onOptionsItemSelected(@NonNull MenuItem item) {
      int itemId = item.getItemId();
      if (itemId == R.id.activity_formulario_aluno_menu_salvar) {
         finalizaFormulario();
      }
      return super.onOptionsItemSelected(item);
   }

   private void carregarAluno() {
      Intent dados = getIntent();
      if (dados.hasExtra(CHAVE_ALUNO)){
         setTitle(TITULO_APPBAR_EDITA_ALUNO);
         aluno = (Aluno) dados.getSerializableExtra(CHAVE_ALUNO);
         if (aluno != null) {
            preencherCampos();
         }
      } else {
         setTitle(TITULO_APPBAR_NOVO_ALUNO);
         aluno = new Aluno();
      }
   }

   private void preencherCampos() {
      campoNome.setText(aluno.getNome());
      campoSobrenome.setText(aluno.getSobrenome());
      campoTelefoneFixo.setText(aluno.getTelefoneFixo());
      campoTelefoneCelular.setText(aluno.getTelefoneCelular());
      campoEmail.setText(aluno.getEmail());
   }

   private void inicializacaoCampos() {
      campoNome = findViewById(R.id.activity_formulario_aluno_nome);
      campoSobrenome = findViewById(R.id.activity_formulario_aluno_sobrenome);
      campoTelefoneFixo = findViewById(R.id.activity_formulario_aluno_telefone_fixo);
      campoTelefoneCelular = findViewById(R.id.activity_formulario_aluno_telefone_celular);
      campoEmail = findViewById(R.id.activity_formulario_aluno_email);
   }

   private void finalizaFormulario() {
      preencherAluno();
      if (aluno.temIdValido()) {
         dao.editar(aluno);
      } else {
         dao.salvar(aluno);
      }
      finish();
   }

   private void preencherAluno() {
      String nome = campoNome.getText().toString();
      String sobrenome = campoSobrenome.getText().toString();
      String telefoneFixo = campoTelefoneFixo.getText().toString();
      String telefoneCelular = campoTelefoneCelular.getText().toString();
      String email = campoEmail.getText().toString();

      aluno.setNome(nome);
      aluno.setSobrenome(sobrenome);
      aluno.setTelefoneFixo(telefoneFixo);
      aluno.setTelefoneCelular(telefoneCelular);
      aluno.setEmail(email);
      aluno.setMomentoDeCadastro(Calendar.getInstance());
   }
}