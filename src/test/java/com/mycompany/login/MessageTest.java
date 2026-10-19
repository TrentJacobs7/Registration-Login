package com.mycompany.login;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    // tests that a normal message is under 250 characters
    @Test
    public void testMessageLengthSuccess() {
        Message m = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertTrue(m.getMessageText().length() <= 250);
    }

    // tests that a message longer than 250 characters is detected
    @Test
    public void testMessageLengthFailure() {
        String longText = "a".repeat(251);
        Message m = new Message("+27718693002", longText);
        assertTrue(m.getMessageText().length() > 250);
    }

    // tests a correctly formatted recipient number
    @Test
    public void testRecipientSuccess() {
        Message m = new Message("+27718693002", "Hello");
        assertEquals("Cell phone number successfully captured.", m.checkRecipientCell());
    }

    // tests an incorrectly formatted recipient number
    @Test
    public void testRecipientFailure() {
        Message m = new Message("08575975889", "Hello");
        assertTrue(m.checkRecipientCell().contains("incorrectly formatted"));
    }

    // tests that the message hash is created correctly
    @Test
    public void testMessageHash() {
        Message m = new Message("+27718693002", "Hi thanks");
        String hash = m.getMessageHash();
        assertTrue(hash.contains(":"));
        assertEquals(hash, hash.toUpperCase());
    }

    // tests the send option
    @Test
    public void testSentMessageSend() {
        Message m = new Message("+27718693002", "Test message");
        assertEquals("Message successfully sent.", m.sentMessage("1"));
    }

    // tests the disregard option
    @Test
    public void testSentMessageDisregard() {
        Message m = new Message("+27718693002", "Test message");
        assertEquals("Press 0 to delete message.", m.sentMessage("2"));
    }

    // tests the store option
    @Test
    public void testSentMessageStore() {
        Message m = new Message("+27718693002", "Test message");
        assertEquals("Message successfully stored.", m.sentMessage("3"));
    }

    // tests that the message id is not longer than 10 characters
    @Test
    public void testMessageIDLength() {
        Message m = new Message("+27718693002", "Hello");
        assertTrue(m.checkMessageID());
    }
}