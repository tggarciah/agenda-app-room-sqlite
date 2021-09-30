package br.com.dtfoods.database;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class AgendaMigrations {
   private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
      @Override
      public void migrate(@NonNull SupportSQLiteDatabase database) {
         database.execSQL("ALTER TABLE aluno ADD COLUMN sobrenome TEXT");
      }
   };

   private static final Migration MIGRATION_2_3 = new Migration(2, 3) {
      @Override
      public void migrate(@NonNull SupportSQLiteDatabase database) {
         database.execSQL("ALTER TABLE aluno ADD COLUMN momentoDeCadastro INTEGER");
      }
   };

   public static final Migration[] TODAS_MIGRATIONS = {MIGRATION_1_2,
           MIGRATION_2_3};
}
