package com.nero;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AkkaDemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(AkkaDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        // new a common parent
        final ActorSystem actorSystem = ActorSystem.create("orderakka");

        // new printer actor
        final ActorRef printer = actorSystem.actorOf(Printer.props());

        // new employeeA actor
        final ActorRef employeeA = actorSystem.actorOf(Employee.props("employee A", printer));

        // new employeeB actor
        final ActorRef employeeB = actorSystem.actorOf(Employee.props("employee B", printer));

        // new employeeC actor
        final ActorRef employeeC = actorSystem.actorOf(Employee.props("employee C", printer));

        // send order message
        employeeA.tell("order", ActorRef.noSender());

        // send delivery message
        employeeB.tell("delivery", ActorRef.noSender());

        // send close message
        employeeC.tell("stop", ActorRef.noSender());
    }
}
