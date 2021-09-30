package br.com.dtfoods.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.dtfoods.R;
import br.com.dtfoods.model.Aluno;

public class ListaAlunosAdapter extends BaseAdapter {
   private final List<Aluno> alunos = new ArrayList<>();
   private final Context context;

   public ListaAlunosAdapter(Context context) {
      this.context = context;
   }

   @Override
   public int getCount() {
      return alunos.size();
   }

   @Override
   public Aluno getItem(int i) {
      return alunos.get(i);
   }

   @Override
   public long getItemId(int i) {
      return alunos.get(i).getId();
   }

   @Override
   public View getView(int posicao, View view, ViewGroup viewGroup) {
      View viewCriada = getInflate(viewGroup);
      Aluno aluno = alunos.get(posicao);
      vincula(viewCriada, aluno);
      return viewCriada;
   }

   private void vincula(View view, Aluno aluno) {
      TextView campoNome = view.findViewById(R.id.item_aluno_nome);
      TextView campoTelefone = view.findViewById(R.id.item_aluno_telefone);

      campoNome.setText(aluno.getNomeCompleto() + " " + aluno.dataFormadata());
      campoTelefone.setText(aluno.getTelefoneFixo());
   }

   private View getInflate(ViewGroup viewGroup) {
      return LayoutInflater.from(context).inflate(R.layout.item_aluno, viewGroup, false);
   }

   public void atualiza(List<Aluno> alunos) {
      this.alunos.clear();
      this.alunos.addAll(alunos);
      notifyDataSetChanged();
   }

   public void remove(Aluno aluno) {
      alunos.remove(aluno);
      notifyDataSetChanged();
   }
}