package dead_code_analyser.handlers;

import javax.swing.tree.TreePath;
import org.eclipse.core.resources;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class SampleHandler extends AbstractHandler {
	/**
	 * The constructor.
	 */
	public SampleHandler() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window1 = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		MessageDialog.openInformation(
				window1.getShell(),
				"Dead_Code_Analyser",
				"Hello, Eclipse world");
		
		 IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		    IWorkbenchPage activePage = window.getActivePage();
		    ISelection selection = activePage.getSelection();
		    if (selection != null) {
		        System.out.println("Got selection");
		        if (selection instanceof IStructuredSelection) {
		            System.out.println("Got a structured selection");
		            if (selection instanceof ITreeSelection) {
		                TreeSelection treeSelection = (TreeSelection) selection;
		                TreePath[] treePaths = treeSelection.getPaths();
		                TreePath treePath = treePaths[0];

		                System.out.println("Last");
		                Object lastSegmentObj = treePath.getLastSegment();
		                Class currClass = lastSegmentObj.getClass();
		                while(currClass != null) {
		                    System.out.println("  Class=" + currClass.getName());
		                    Class[] interfaces = currClass.getInterfaces();
		                    for(Class interfacey : interfaces) {
		                        System.out.println("   I=" + interfacey.getName());
		                    }
		                    currClass = currClass.getSuperclass();
		                }
		                if(lastSegmentObj instanceof IAdaptable) {
		                    IFile file = (IFile) ((IAdaptable) lastSegmentObj).getAdapter(IFile.class);
		                    if(file != null) {
		                        System.out.println("File=" + file.getName());
		                        String path = file.getRawLocation().toOSString();
		                        System.out.println("path: " + path);
		                    }
		                }
		            }
		        }
		    }
		    
		return null;
	}
}




