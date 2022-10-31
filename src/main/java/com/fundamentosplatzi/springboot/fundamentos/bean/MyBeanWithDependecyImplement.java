package com.fundamentosplatzi.springboot.fundamentos.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyBeanWithDependecyImplement implements MyBeanWithDependency {
    Log LOGGER = LogFactory.getLog(MyBeanWithDependecyImplement.class);
    private MyOperation myOperationm;
    private MyImplementForMe myImplementForMe;

    public MyBeanWithDependecyImplement(MyOperation myOperationm,MyImplementForMe myImplementForMe) {
        this.myOperationm = myOperationm;
        this.myImplementForMe=myImplementForMe;
    }

    @Override
    public void printWithDpendency() {

        System.out.println(myOperationm.suma( 100));;
        System.out.println("Hola desde la implementacion de un bean con dependencia");
        String mensaje="";

        int numeroFinal=1,numeroSiguiente=myImplementForMe.MySumas();
        LOGGER.info("El numero final a ver es :"+numeroFinal);
        for (int i=0;i<10;i++){
            numeroFinal=numeroSiguiente+numeroFinal;

            numeroSiguiente=numeroFinal;
            mensaje+=", "+numeroSiguiente;
        }
        System.out.println(mensaje);
        LOGGER.debug("Se ha terminado de ejecutar el ciclo, debo revisar la parte de asignación de las dependencias");


    }
}
