package realfractalfun;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import bdv.util.BdvHandle;

public class ColorSchemeAction extends AbstractAction
{
	private static final long serialVersionUID = 1L;

	private JuliaConverter myFracConv;

	private BdvHandle myViewerHandle;

	private ColorScheme myColorScheme;

	public ColorSchemeAction( final JuliaConverter fracConv, final BdvHandle viewerHandle, final ColorScheme colorScheme )
	{
		myFracConv = fracConv;
		myViewerHandle = viewerHandle;
		myColorScheme = colorScheme;
	}

	public void actionPerformed( ActionEvent e )
	{
		myFracConv.setScheme( myColorScheme );
		myViewerHandle.getViewerPanel().requestRepaint();
	}
}
