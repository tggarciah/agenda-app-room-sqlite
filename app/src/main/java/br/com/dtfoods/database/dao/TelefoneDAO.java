package br.com.dtfoods.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.dtfoods.model.Telefone;

@Dao
public interface TelefoneDAO {
   @Query("SELECT * FROM Telefone t WHERE t.alunoId = :alunoId LIMIT 1")
   Telefone buscaPrimeiroTelefone(int alunoId);

   @Insert
   void salvar(Telefone... telefones);

   @Query("SELECT * FROM Telefone t WHERE t.alunoId = :alunoId")
   List<Telefone> buscaTodosTelefonesDo(int alunoId);

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   void atualizar(Telefone... telefones);
}
