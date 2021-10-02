package br.com.dtfoods.database.converter;

import androidx.room.TypeConverter;

import br.com.dtfoods.model.TipoTelefone;

public class ConversorTipoTelefone {

   @TypeConverter
   public String paraString(TipoTelefone tipo) {
      return tipo.name();
   }

   @TypeConverter
   public TipoTelefone paraTipoTelefone(String valor) {
      if (valor != null) {
         return TipoTelefone.valueOf(valor);
      }
      return null;
   }
}
