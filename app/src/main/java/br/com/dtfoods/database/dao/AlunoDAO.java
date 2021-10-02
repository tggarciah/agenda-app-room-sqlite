package br.com.dtfoods.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.dtfoods.model.Aluno;

@Dao
public interface AlunoDAO {
   @Insert
   Long salvar(Aluno aluno);

   @Query("SELECT * FROM aluno")
   List<Aluno> todos();

   @Delete
   void remover(Aluno aluno);

   @Update
   void editar(Aluno aluno);
}
