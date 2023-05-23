/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.parking;

import java.util.ArrayList;

public class Parking {
    private ArrayList<String> matriculas;
    private String nombre;
    
    public Parking(String nombre, int numPlazas) {
        this.nombre = nombre;
        this.matriculas = new ArrayList<>(numPlazas);
        for (int i = 0; i < numPlazas; i++) {
            this.matriculas.add(null);
        }
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void entrada(String matricula, int plaza) throws Exception {
        if (matricula == null || matricula.length() < 4) {
            throw new Exception("Matrícula incorrecta");
        }
        
        if (plaza < 0 || plaza >= matriculas.size()) {
            throw new Exception("Plaza inválida");
        }
        
        if (matriculas.get(plaza) != null) {
            throw new Exception("Plaza ocupada");
        }
        
        if (matriculas.contains(matricula)) {
            throw new Exception("Matrícula repetida");
        }
        
        matriculas.set(plaza, matricula);
    }
    
    public int salida(String matricula) throws Exception {
        int plazaLiberada = -1;
        for (int i = 0; i < matriculas.size(); i++) {
            if (matriculas.get(i) != null && matriculas.get(i).equals(matricula)) {
                plazaLiberada = i;
                matriculas.set(i, null);
                break;
            }
        }
        
        if (plazaLiberada == -1) {
            throw new Exception("Matrícula no encontrada");
        }
        
        return plazaLiberada;
    }
    
    public int getPlazasTotales() {
        return matriculas.size();
    }
    
    public int getPlazasOcupadas() {
        int plazasOcupadas = 0;
        for (String matricula : matriculas) {
            if (matricula != null) {
                plazasOcupadas++;
            }
        }
        return plazasOcupadas;
    }
    
    public int getPlazasLibres() {
        return matriculas.size() - getPlazasOcupadas();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nombre).append("\n");
        sb.append("Plazas totales: ").append(getPlazasTotales()).append("\n");
        sb.append("Plazas ocupadas: ").append(getPlazasOcupadas()).append("\n");
        sb.append("----------------------\n");
        
        for (int i = 0; i < matriculas.size(); i++) {
            sb.append("Plaza ").append(i).append(": ");
            if (matriculas.get(i) != null) {
                sb.append(matriculas.get(i));
            } else {
                sb.append("Libre");
            }
            sb.append("\n");
        }
        
        return sb.toString();
    }
}
