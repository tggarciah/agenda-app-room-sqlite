package br.com.dtfoods.database;

import static br.com.dtfoods.database.AgendaMigrations.TODAS_MIGRATIONS;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import br.com.dtfoods.database.converter.ConversorCalendar;
import br.com.dtfoods.database.dao.AlunoDAO;
import br.com.dtfoods.model.Aluno;

@Database(entities = {Aluno.class}, version = 3, exportSchema = true)
@TypeConverters(ConversorCalendar.class)
public abstract class AgendaDatabase extends RoomDatabase {
   private static final String NOME_BANCO_DADOS = "agenda.db";

   public static AgendaDatabase getInstance(Context context) {
      return Room.databaseBuilder(context, AgendaDatabase.class, NOME_BANCO_DADOS)
              .allowMainThreadQueries()
              .addMigrations(TODAS_MIGRATIONS)
              .build();
   }

   public abstract AlunoDAO getRoomAlunoDAO();
}
