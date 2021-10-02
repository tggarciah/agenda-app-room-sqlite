package br.com.dtfoods.database;

import static br.com.dtfoods.database.AgendaMigrations.TODAS_MIGRATIONS;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import br.com.dtfoods.database.converter.ConversorCalendar;
import br.com.dtfoods.database.converter.ConversorTipoTelefone;
import br.com.dtfoods.database.dao.AlunoDAO;
import br.com.dtfoods.database.dao.TelefoneDAO;
import br.com.dtfoods.model.Aluno;
import br.com.dtfoods.model.Telefone;

@Database(entities = {Aluno.class, Telefone.class}, version = 5, exportSchema = true)
@TypeConverters({ConversorCalendar.class, ConversorTipoTelefone.class})
public abstract class AgendaDatabase extends RoomDatabase {
   private static final String NOME_BANCO_DADOS = "agenda.db";

   public static AgendaDatabase getInstance(Context context) {
      return Room.databaseBuilder(context, AgendaDatabase.class, NOME_BANCO_DADOS)
              .addMigrations(TODAS_MIGRATIONS)
              .build();
   }

   public abstract AlunoDAO getAlunoDAO();

   public abstract TelefoneDAO getTelefoneDAO();
}
