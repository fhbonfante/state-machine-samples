package com.fhbonfante;

import com.fhbonfante.enumstate.BookEvents;
import com.fhbonfante.enumstate.BookStates;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;

@SpringBootApplication
@Slf4j
public class StateMachineApplication implements CommandLineRunner {

	private final StateMachineFactory<String,String> stringStateMachineFactory;
	private final StateMachineFactory<BookStates, BookEvents> enumStateMachineFactory;

	@Autowired
	public StateMachineApplication(StateMachineFactory<String, String> stringStateMachine, StateMachineFactory<BookStates, BookEvents> enumStateMachine) {
		this.stringStateMachineFactory = stringStateMachine;
		this.enumStateMachineFactory = enumStateMachine;
	}

	public static void main(String[] args) {
		SpringApplication.run(StateMachineApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		runStringStateMachineDemo();
		runEnumStateMachineDemo();
	}

	private void runStringStateMachineDemo() {
		StateMachine<String,String> stringStateMachine = stringStateMachineFactory.getStateMachine();
		stringStateMachine.start();
		log.info(stringStateMachine.getState().getId());
		stringStateMachine.sendEvent("E1");
		log.info(stringStateMachine.getState().getId());
		stringStateMachine.sendEvent("E2");
		log.info(stringStateMachine.getState().getId());
		stringStateMachine.sendEvent("end");
		log.info(stringStateMachine.getState().getId());
	}


	private void runEnumStateMachineDemo() {
		StateMachine<BookStates, BookEvents> stateMachine = enumStateMachineFactory.getStateMachine();
		boolean returnAccepted = stateMachine.sendEvent(BookEvents.RETURN);
		log.info("return accepted: " + returnAccepted);
		boolean borrowAccepted = stateMachine.sendEvent(BookEvents.BORROW);
		log.info("borrow accepted: " + borrowAccepted);
	}

}
