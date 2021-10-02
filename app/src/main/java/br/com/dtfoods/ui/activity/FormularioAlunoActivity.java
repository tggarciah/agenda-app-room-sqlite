package br.com.dtfoods.ui.activity;

import static br.com.dtfoods.ui.activity.ConstantesActivities.CHAVE_ALUNO;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.List;

import br.com.dtfoods.R;
import br.com.dtfoods.asynctask.BuscaTodosTelefonesDoAlunoTask;
import br.com.dtfoods.asynctask.EditaAlunoTask;
import br.com.dtfoods.asynctask.SalvaAlunoTask;
import br.com.dtfoods.database.AgendaDatabase;
import br.com.dtfoods.database.dao.AlunoDAO;
import br.com.dtfoods.database.dao.TelefoneDAO;
import br.com.dtfoods.model.Aluno;
import br.com.dtfoods.model.Telefone;
import br.com.dtfoods.model.TipoTelefone;

public class FormularioAlunoActivity extends AppCompatActivity {

   private static final String TITULO_APPBAR_NOVO_ALUNO = "Novo aluno";
   private static final String TITULO_APPBAR_EDITA_ALUNO = "Edita aluno";
   public static final String TAG = "FormularioAlunoActivity";
   private EditText campoNome;
   private EditText campoSobrenome;
   private EditText campoTelefoneFixo;
   private EditText campoTelefoneCelular;
   private EditText campoEmail;
   private AlunoDAO alunoDAO;
   private TelefoneDAO telefoneDAO;
   private Aluno aluno;
   private List<Telefone> telefones;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_formulario_aluno);
      alunoDAO = AgendaDatabase.getInstance(this).getAlunoDAO();
      telefoneDAO = AgendaDatabase.getInstance(this).getTelefoneDAO();
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
      campoEmail.setText(aluno.getEmail());
      preencherCamposTelefone();
   }

   private void preencherCamposTelefone() {
      new BuscaTodosTelefonesDoAlunoTask(telefoneDAO, aluno, telefones -> {
         this.telefones = telefones;
         for (Telefone telefone : telefones) {
            if (telefone.getTipo() == TipoTelefone.FIXO) {
               campoTelefoneFixo.setText(telefone.getNumero());
            } else {
               campoTelefoneCelular.setText(telefone.getNumero());
            }
         }
      }).execute();
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

      Telefone telefoneFixo = criaTelefone(campoTelefoneFixo, TipoTelefone.FIXO);
      Telefone telefoneCelular = criaTelefone(campoTelefoneCelular, TipoTelefone.CELULAR);

      if (aluno.temIdValido()) {
         editarAluno(telefoneFixo, telefoneCelular);
      } else {
         salvarAluno(telefoneFixo, telefoneCelular);
      }
   }

   @NonNull
   private Telefone criaTelefone(EditText campoTelefone, TipoTelefone tipo) {
      String auxTelefoneNumero = campoTelefone.getText().toString();
      return new Telefone(auxTelefoneNumero, tipo);
   }

   private void salvarAluno(Telefone telefoneFixo, Telefone telefoneCelular) {
      new SalvaAlunoTask(alunoDAO, aluno, telefoneFixo, telefoneCelular, telefoneDAO, this::finish).execute();
   }

   private void editarAluno(Telefone telefoneFixo, Telefone telefoneCelular) {
      new EditaAlunoTask(alunoDAO, aluno, telefoneFixo, telefoneCelular, telefoneDAO, telefones, this::finish).execute();
   }

   private void preencherAluno() {
      String nome = campoNome.getText().toString();
      String sobrenome = campoSobrenome.getText().toString();
      String email = campoEmail.getText().toString();

      aluno.setNome(nome);
      aluno.setSobrenome(sobrenome);
      aluno.setEmail(email);
      aluno.setMomentoDeCadastro(Calendar.getInstance());
   }
}