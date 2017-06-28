package juliasetexample;

import org.scijava.ui.behaviour.DragBehaviour;

import bdv.util.BdvHandle;

public class JuliaMouse implements DragBehaviour
{
	private JuliaFunction myFuncParent;

	private BdvHandle myViewerHandle;

	public JuliaMouse( final JuliaFunction funcParent, final BdvHandle viewerHandle )
	{
		myFuncParent = funcParent;
		myViewerHandle = viewerHandle;
	}

	public void drag( final int x, final int y )
	{
		updateCoords( x, y );
	}

	public void end( final int x, final int y )
	{
		updateCoords( x, y );
	}

	public void init( final int x, final int y )
	{
		updateCoords( x, y );
	}

	private void updateCoords( final int x, final int y )
	{
		double realPart = 4.0 * ( ( double ) x / myViewerHandle.getViewerPanel().getWidth() ) - 2.0;
		double imagPart = 4.0 * ( ( double ) y / myViewerHandle.getViewerPanel().getHeight() ) - 2.0;
		myFuncParent.updateC( realPart, imagPart );
		myViewerHandle.getViewerPanel().requestRepaint();
	}

}
