package com.example.app_logginuser;

public class User {
    int Id;
    String Nombre, Apellidos, Usuario, Password;

    public boolean isNull(){
        if (Nombre.equals("") && Apellidos.equals("") && Usuario.equals("") && Password.equals("")){
            return false;
        }
        else{
            return true;
        }
    }

    public User() {
    }

    public User(String nombre, String apellidos, String usuario, String password) {
        Nombre = nombre;
        Apellidos = apellidos;
        Usuario = usuario;
        Password = password;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getId() {
        return Id;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public String getUsuario() {
        return Usuario;
    }

    public String getPassword() {
        return Password;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", Nombre='" + Nombre + '\'' +
                ", Apellidos='" + Apellidos + '\'' +
                ", Usuario='" + Usuario + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }
}
