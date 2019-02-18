package com.nero;

import akka.actor.AbstractActor;
import akka.actor.Props;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 打印机
 * <p>
 * date : 2019-02-18
 * time : 15:10
 * </p>
 *
 * @author Nero
 */
public class Printer extends AbstractActor {

    private Logger logger = LoggerFactory.getLogger(Printer.class);

    static public Props props() {
        return Props.create(Printer.class, Printer::new);
    }

    @Override
    public void preStart() {
        logger.info("打印机开始工作...");
    }

    @Override
    public void postStop() {
        logger.info("打印机结束工作...");
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchEquals(
                        "stop",
                        s -> {
                            getContext().stop(getSelf());
                        })
                .match(String.class, message -> {
                    logger.info(message);
                })

                .build();
    }
}
