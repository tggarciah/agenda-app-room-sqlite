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

   private static final Migration MIGRATION_3_4 = new Migration(3, 4) {
      @Override
      public void migrate(@NonNull SupportSQLiteDatabase database) {
         // Criar uma nova tabela
         database.execSQL("CREATE TABLE IF NOT EXISTS `Aluno_3_4` (" +
                 "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                 "`nome` TEXT, " +
                 "`sobrenome` TEXT, " +
                 "`telefoneFixo` TEXT, " +
                 "`telefoneCelular` TEXT, " +
                 "`email` TEXT, " +
                 "`momentoDeCadastro` INTEGER)");

         // Migrar os dados da tabela antiga para a nova
         database.execSQL("INSERT INTO Aluno_3_4 (id, nome, sobrenome, telefoneFixo, telefoneCelular, email, momentoDeCadastro)" +
                 "SELECT id, nome, sobrenome, telefone, null, email, momentoDeCadastro FROM aluno");

         // Apagar a tabela antiga
         database.execSQL("DROP TABLE aluno");

         // Renomear a tabela nova
         database.execSQL("ALTER TABLE Aluno_3_4 RENAME TO Aluno");
      }
   };

   public static final Migration[] TODAS_MIGRATIONS = {MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4};
}
