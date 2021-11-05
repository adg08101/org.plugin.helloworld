package org.plugin.helloworld.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.datatransfer.StringSelection;

public class SampleHandler extends AbstractHandler {
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		copy();
		Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable t = cb.getContents(this);

		DataFlavor dataFlavorStringJava = null;
		try {
			dataFlavorStringJava = new DataFlavor(
					"application/x-java-serialized-object; class=java.lang.String");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (t.isDataFlavorSupported(dataFlavorStringJava)) {
		   String text = null;
		try {
			text = (String) t.getTransferData(dataFlavorStringJava);
		} catch (UnsupportedFlavorException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			String[] output = text.split("\n");
			String text_with_comments = "";
			
		   	for (int i = 0;i < output.length;i++)
		   		text_with_comments += "# " + output[i] + "\n";
		   	
		   	StringSelection final_output = new StringSelection(text_with_comments);
		   	cb.setContents(final_output, final_output);
		   	delete_eat();
		   	paste();
		}
		
		return null;
	}
	
	public void copy() {
		Robot robot;
		try {
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.delay(100);
			robot.keyPress(KeyEvent.VK_C);
			robot.delay(100);
			robot.keyRelease(KeyEvent.VK_C);
			robot.delay(100);
			robot.keyRelease(KeyEvent.VK_CONTROL);
		} catch (AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}			
	}
	
	public void paste() {
		Robot robot;
		try {
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.delay(100);
			robot.keyPress(KeyEvent.VK_V);
			robot.delay(100);
			robot.keyRelease(KeyEvent.VK_V);
			robot.delay(100);
			robot.keyRelease(KeyEvent.VK_CONTROL);
		} catch (AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void delete_eat() {
		Robot robot;
		try {
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_BACK_SPACE);
			robot.delay(100);
			robot.keyRelease(KeyEvent.VK_BACK_SPACE);
		} catch (AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
