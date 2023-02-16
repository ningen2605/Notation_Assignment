package notation_assignment2.notation_assignment;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NotationGFATests {
	public MyQueue<String> stringQ;
	public MyStack<String> stringS;
	
	@Before
	public void setUp() throws Exception {
		stringQ = new MyQueue<String>(5);
		stringQ.enqueue("a");
		stringS = new MyStack<String>(2);
		stringS.push("a");
	}

	@After
	public void tearDown() throws Exception {
	}

	 
	@Test
	public void testIsEmptyQueue() {
		assertEquals(false,stringQ.isEmpty());
		try {
			stringQ.dequeue();
		} catch (QueueUnderflowException e) {
			throw new RuntimeException(e);
		}
		assertEquals(true, stringQ.isEmpty());
	}
	@Test
	public void testDequeue() {
		try {
			assertEquals("a", stringQ.dequeue());
			 
			//Queue is empty, next statement should cause QueueUnderFlowException
			stringQ.dequeue();
			assertTrue("This should have caused an QueueUnderflowException", false);
		}
		catch (QueueUnderflowException e){
			assertTrue("This should have caused an QueueUnderflowException", true);
		}
		catch (Exception e){
			assertTrue("This should have caused an QueueUnderflowException", false);
		}
	}
	
	@Test
	public void testIsEmptyStack() throws StackUnderflowException {
		assertEquals(false,stringS.isEmpty());
		stringS.pop();
		assertEquals(true, stringS.isEmpty());
	}

	@Test
	public void testIsFull() throws StackOverflowException {
		assertEquals(false, stringS.isFull());
		stringS.push("b");
		assertEquals(true, stringS.isFull());
	}


}
