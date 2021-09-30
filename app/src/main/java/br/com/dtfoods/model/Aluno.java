package br.com.dtfoods.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Entity
public class Aluno implements Serializable {
   @PrimaryKey(autoGenerate = true)
   private int id = 0;
   private String nome;
   private String sobrenome;
   private String telefoneFixo;
   private String telefoneCelular;
   private String email;
   private Calendar momentoDeCadastro = Calendar.getInstance();

   public String getNome() {
      return nome;
   }

   public void setNome(String nome) {
      this.nome = nome;
   }

   public String getTelefoneFixo() {
      return telefoneFixo;
   }

   public void setTelefoneFixo(String telefoneFixo) {
      this.telefoneFixo = telefoneFixo;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getSobrenome() {
      return sobrenome != null? sobrenome : "";
   }

   public void setSobrenome(String sobrenome) {
      this.sobrenome = sobrenome;
   }

   public Calendar getMomentoDeCadastro() {
      return momentoDeCadastro;
   }

   public void setMomentoDeCadastro(Calendar momentoDeCadastro) {
      this.momentoDeCadastro = momentoDeCadastro;
   }

   public String dataFormadata(){
      SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
      return formatador.format(momentoDeCadastro.getTime());
   }

   @NonNull
   @Override
   public String toString() { return nome; }

   public void setId(int id) {
      this.id = id;
   }

   public int getId() {
      return id;
   }

   public boolean temIdValido() {
      return id > 0;
   }

   public String getNomeCompleto() {
      return getNome() + " " + getSobrenome();
   }

   public String getTelefoneCelular() {
      return telefoneCelular;
   }

   public void setTelefoneCelular(String telefoneCelular) {
      this.telefoneCelular = telefoneCelular;
   }
}
