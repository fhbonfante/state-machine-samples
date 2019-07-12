package com.fhbonfante.enumstate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;


@Slf4j
public class EnumStateMachineListener implements StateMachineListener<BookStates,BookEvents> {

    @Override
    public void stateChanged(State<BookStates, BookEvents> from, State<BookStates, BookEvents> to) {
        log.info("State changed from {} to {}", getStateInfo(from), getStateInfo(to));
    }

    @Override
    public void stateEntered(State<BookStates, BookEvents> state) {
        log.info("Entered state {}", getStateInfo(state));
    }

    @Override
    public void stateExited(State<BookStates, BookEvents> state) {
        log.info("Exited state {}", getStateInfo(state));
    }

    @Override
    public void eventNotAccepted(Message<BookEvents> event) {
        log.error("Event not accepted: {}", event.getPayload());
    }

    @Override
    public void transition(Transition<BookStates, BookEvents> transition) {
    }

    @Override
    public void transitionStarted(Transition<BookStates, BookEvents> transition) {

    }

    @Override
    public void transitionEnded(Transition<BookStates, BookEvents> transition) {

    }

    @Override
    public void stateMachineStarted(StateMachine<BookStates, BookEvents> stateMachine) {
        log.info("Machine started: {}", stateMachine);
    }

    @Override
    public void stateMachineStopped(StateMachine<BookStates, BookEvents> stateMachine) {
        log.info("Machine stopped: {}", stateMachine);
    }

    @Override
    public void stateMachineError(StateMachine<BookStates, BookEvents> stateMachine, Exception exception) {
        log.error("Machine error: {}", stateMachine);
    }

    @Override
    public void extendedStateChanged(Object key, Object value) {
        log.info("Extended state changed: [{}: {}]", key, value);
    }

    @Override
    public void stateContext(StateContext<BookStates, BookEvents> stateContext) {

    }


    private String getStateInfo(State<BookStates, BookEvents> state) {
        return state.getId().toString();
    }
}
