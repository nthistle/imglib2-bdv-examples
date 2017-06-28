package juliasetexample;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import bdv.util.BdvHandle;

public class IncreaseIterations extends AbstractAction
{

	private JuliaFunction myFuncParent;

	private BdvHandle myViewerHandle;

	public IncreaseIterations( JuliaFunction funcParent, BdvHandle viewerHandle )
	{
		super();
		myFuncParent = funcParent;
		myViewerHandle = viewerHandle;
	}

	public void actionPerformed( ActionEvent arg0 )
	{
		myFuncParent.increaseIts( 100 );
		myViewerHandle.getViewerPanel().requestRepaint();
	}

}
