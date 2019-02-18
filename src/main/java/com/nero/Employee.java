package com.nero;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 雇员
 * <p>
 * date : 2019-02-18
 * time : 15:04
 * </p>
 *
 * @author Nero
 */
public class Employee extends AbstractActor {

    private Logger logger = LoggerFactory.getLogger(Employee.class);

    private String name;

    private ActorRef printer;

    public Employee(String name, ActorRef printer) {
        this.name = name;
        this.printer = printer;
    }

    static public Props props(String name, ActorRef printer) {
        return Props.create(Employee.class, () -> new Employee(name, printer));
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchEquals("order", wtg -> {
                    printer.tell(name + ":" + "订单信息打印中", getSelf());
                })
                .matchEquals("delivery", wtg -> {
                    printer.tell(name + ":" + "物流信息打印中", getSelf());
                })
                .matchEquals("stop", wtg -> {
                    printer.tell("stop", getSelf());
                })
                .build();
    }
}
