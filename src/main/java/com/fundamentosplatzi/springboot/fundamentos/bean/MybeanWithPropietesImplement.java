package com.fundamentosplatzi.springboot.fundamentos.bean;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWitchPropietes;

public class MybeanWithPropietesImplement implements MyBeanWitchPropietes {
    private String nombre;
    private String apellido;
    private String ramdom            ;

    public MybeanWithPropietesImplement(String nombre, String apellido, String ramdom) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.ramdom = ramdom;
    }

    @Override
    public void imprimirValues() {
        System.out.println("El nombre es:"+nombre+" el apellido es "+apellido);
    }
}
